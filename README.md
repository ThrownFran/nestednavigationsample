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
└─ Settings (graph)
   ├─ General (graph)
   │  ├─ General (root)
   │  └─ General • Details
   ├─ Account (graph)
   │  ├─ Account (root)
   │  └─ Account • Details
   └─ About (graph)
      ├─ About (root)
      └─ About • Details
```

- Each Settings tab is its own nested graph with an independent stack.
- The bottom bar switches tabs; each screen defines its own TopAppBar.

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

- Edge‑to‑edge is enabled in `MainActivity` with `enableEdgeToEdge()` so content can render behind system bars.
- Each screen uses `Scaffold` and applies `Modifier.padding(innerPadding)` to respect system bar insets and app bars.
- The shared bottom-bar container only renders the bottom bar; it applies `innerPadding.calculateBottomPadding()` so content isn’t covered by the bar while avoiding double top padding (top/start/end set to 0 and handled per screen).
- TopAppBar lives in each screen (not the container), which prevents double insets and keeps responsibilities clear.

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
