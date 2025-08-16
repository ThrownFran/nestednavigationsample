# NestedNavigationSample

A minimal, clear Jetpack Compose sample demonstrating nested navigation with:
- One app-wide NavHost (Home, Search, Settings)
- A Settings graph with 3 tabs (General, Account, About)
- Independent back stacks per tab with save/restore when switching
- Clean tab re-select behavior (tap current tab = pop to that tab’s root)
- Built-in deep links to open Settings, tabs, and details

## Navigation overview

```
App NavHost (start = Home)
├─ Home
├─ Search
└─ Settings (graph)        ← parent nested graph for the whole Settings area
   ├─ General (graph)      ← nested graph per tab
   │  ├─ General (root)
   │  └─ General • Details
   ├─ Account (graph)      ← nested graph per tab
   │  ├─ Account (root)
   │  └─ Account • Details
   └─ About (graph)        ← nested graph per tab
      ├─ About (root)
      └─ About • Details
```

- We use two levels of nesting:
  1) Settings parent graph groups all Settings destinations under a single route and entry point (for deep links and app navigation).
  2) A child nested graph per tab (General, Account, About) encapsulates that tab’s own back stack (root + details).
- Benefits of grouping this way:
  - Encapsulation: each tab owns its routes and stack independently.
  - Simpler tab switching: we navigate to a tab graph route to switch and preserve/restore its stack.
  - Clear deep linking: links can target the parent graph (open Settings) or a specific tab/destination.
  - Maintainability: adding/removing a tab is localized to its nested graph and screens.

## Key behaviors

- Back inside Settings
  - Pops within the currently selected tab’s stack.
  - When a tab is at root, the screen’s TopAppBar back exits the Settings graph (returns to the previous app destination).

- Save/restore when switching tabs
  - Tab switches use `popUpTo(settings) { saveState = true }` and `restoreState = true`.
  - Each tab’s stack (and UI state) is preserved and restored.

- Re-selecting the current tab
  - If you’re deep in a tab (e.g., General • Details) and tap that same tab again, it pops to the tab’s root (General).
  - If you’re already at the tab root, re-select is a no-op.

## Window insets (edge‑to‑edge)

- Edge‑to‑edge is enabled in `MainActivity` via `enableEdgeToEdge()`. Theme uses `NoActionBar` and Compose provides app bars.
- Every Settings destination is wrapped with a small shared container composable named `BottomBarScreen` (see `ui/settings/components/BottomBarScreen.kt`).
  - Where it’s used: in the Settings graph builder (`ui/settings/navigation/SettingsGraph.kt`), each composable inside Settings (General, Account, About and their Details) is placed inside `BottomBarScreen`.
  - What it does: renders the bottom navigation bar once and hosts the screen content in its slot.
- Each screen owns its TopAppBar (defined inside the screen), avoiding double insets between parent and child scaffolds.
- The shared container consumes the parent `Scaffold` insets:
  - `Modifier.padding(innerPadding).consumeWindowInsets(innerPadding)` is applied to the content Box, so system bar insets are applied once at the parent and won’t be reapplied by children.

## Deep links

- Custom scheme/host: `app://nestednavigationsample`
- The Activity manifest accepts any `/settings` path via `android:pathPrefix="/settings"`.
- Destinations register deep links so Navigation routes directly into the right screen.

Supported patterns (examples):
- `app://nestednavigationsample/settings` → Settings (defaults to General tab)
- `app://nestednavigationsample/settings/general`
- `app://nestednavigationsample/settings/general/details`
- `app://nestednavigationsample/settings/account`
- `app://nestednavigationsample/settings/account/details`
- `app://nestednavigationsample/settings/about`
- `app://nestednavigationsample/settings/about/details`

## Try it

Build and install:

```bash
./gradlew :app:assembleDebug
./gradlew :app:installDebug
```

Launch the app:

```bash
adb shell am start -n com.example.nestednavigationsample/.MainActivity
```

Deep-link to Settings and specific screens:

```bash
# Open Settings (General tab by default)
adb shell am start -a android.intent.action.VIEW \
  -d "app://nestednavigationsample/settings" \
  com.example.nestednavigationsample

# Open General root
adb shell am start -a android.intent.action.VIEW \
  -d "app://nestednavigationsample/settings/general" \
  com.example.nestednavigationsample

# Open General • Details
adb shell am start -a android.intent.action.VIEW \
  -d "app://nestednavigationsample/settings/general/details" \
  com.example.nestednavigationsample

# Open Account root
adb shell am start -a android.intent.action.VIEW \
  -d "app://nestednavigationsample/settings/account" \
  com.example.nestednavigationsample

# Open Account • Details
adb shell am start -a android.intent.action.VIEW \
  -d "app://nestednavigationsample/settings/account/details" \
  com.example.nestednavigationsample

# Open About root
adb shell am start -a android.intent.action.VIEW \
  -d "app://nestednavigationsample/settings/about" \
  com.example.nestednavigationsample

# Open About • Details
adb shell am start -a android.intent.action.VIEW \
  -d "app://nestednavigationsample/settings/about/details" \
  com.example.nestednavigationsample
```

## Notes

- Each screen owns its TopAppBar; the shared container only renders the bottom bar.
- Tabbing logic uses a centralized lambda so behavior is consistent and easy to read.
- This project uses Navigation-Compose and Material 3.
