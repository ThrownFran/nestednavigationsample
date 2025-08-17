# NestedNavigationSample

A minimal, clear Jetpack Compose sample demonstrating nested navigation with:
- One app-wide NavHost (Home, Search, Settings)
- A Settings graph with 3 tabs (General, Account, About)
- Independent back stacks per tab with save/restore when switching
- Clean tab re-select behavior (tap current tab = pop to that tab’s Main)
- Built-in deep links to open Settings, tabs, and details

## Navigation overview

```
App NavHost (start = Home)
├─ Home
├─ Search
└─ Settings (graph)        ← parent nested graph for the whole Settings area
   ├─ General (graph)      ← nested graph per tab
   │  ├─ General Main
   │  └─ General Detail
   ├─ Account (graph)      ← nested graph per tab
   │  ├─ Account Main
   │  └─ Account Detail
   └─ About (graph)        ← nested graph per tab
      ├─ About Main
      └─ About Detail
```

- We use two levels of nesting:
  1) Settings parent graph groups all Settings destinations under a single route and entry point (for deep links and app navigation).
  2) A child nested graph per tab (General, Account, About) encapsulates that tab’s own back stack (Main + Detail).
- Benefits of grouping this way:
  - Encapsulation: each tab owns its routes and stack independently.
  - Simpler tab switching: we navigate to a tab graph route to switch and preserve/restore its stack.
  - Clear deep linking: links can target the parent graph (open Settings) or a specific tab/destination.
  - Maintainability: adding/removing a tab is localized to its nested graph and screens.

## Key behaviors

- Back inside Settings
  - Pops within the currently selected tab’s stack.
  - When a tab is at Main, the screen’s TopAppBar back exits the Settings graph (returns to the previous app destination).

- Save/restore when switching tabs
  - Tab switches use `popUpTo(settings) { saveState = true }` and `restoreState = true`.
  - Each tab’s stack (and UI state) is preserved and restored.

- Re-selecting the current tab
  - If you’re deep in a tab (e.g., General Detail) and tap that same tab again, it pops to the tab’s Main (General Main).
  - If you’re already at the tab Main, re-select is a no-op.

## Screen responsibilities (modular screens)

- Each screen is self-contained and renders its own Scaffold:
  - Top app bar: every screen provides its own title and icons (e.g., back). This gives flexibility per screen.
  - Bottom bar: every screen decides whether to show the Settings bottom bar.
    - Main and most Detail screens show the bottom bar.
    - Deeply nested screens (if any) can choose to hide the bottom bar when it doesn’t make sense.

## Shared ViewModel (scoped to Settings graph)

A shared ViewModel lets multiple screens within the same navigation graph read/write common state. It:
- Centralizes cross‑screen state (single source of truth)
- Survives tab switches and configuration changes
- Enables simple cross‑tab coordination and events

Use it when state spans multiple screens in the Settings area (e.g., staged user input used across tabs, feature flags, session/account metadata).

Why nested navigation helps: each nested graph has its own NavBackStackEntry, so you can scope a ViewModel to that entry. The ViewModel lives exactly as long as that graph remains on the back stack and is cleared when you leave it.

Implementation in this repo:
- Scope: obtain the parent entry for SettingsRoutes.Graph and call viewModel(parentEntry)
- Access: a small helper retrieves the shared instance inside each destination
- UI: screens receive the same instance of the ViewModel via parameters.
- Example: Account Main and About Main show a shared counter.

## Deep links

- Custom scheme/host: `app://nestednavigationsample`
- The Activity manifest accepts any `/settings` path via `android:pathPrefix="/settings"`.
- Destinations register deep links so Navigation routes directly into the right screen.

Supported patterns (examples):
- `app://nestednavigationsample/settings` → Settings (defaults to General tab)
- `app://nestednavigationsample/settings/general`        ← General Main
- `app://nestednavigationsample/settings/general/details` ← General Detail
- `app://nestednavigationsample/settings/account`        ← Account Main
- `app://nestednavigationsample/settings/account/details` ← Account Detail
- `app://nestednavigationsample/settings/about`          ← About Main
- `app://nestednavigationsample/settings/about/details`   ← About Detail

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

# Open General Main
adb shell am start -a android.intent.action.VIEW \
  -d "app://nestednavigationsample/settings/general" \
  com.example.nestednavigationsample

# Open General Detail
adb shell am start -a android.intent.action.VIEW \
  -d "app://nestednavigationsample/settings/general/details" \
  com.example.nestednavigationsample

# Open Account Main
adb shell am start -a android.intent.action.VIEW \
  -d "app://nestednavigationsample/settings/account" \
  com.example.nestednavigationsample

# Open Account Detail
adb shell am start -a android.intent.action.VIEW \
  -d "app://nestednavigationsample/settings/account/details" \
  com.example.nestednavigationsample

# Open About Main
adb shell am start -a android.intent.action.VIEW \
  -d "app://nestednavigationsample/settings/about" \
  com.example.nestednavigationsample

# Open About Detail
adb shell am start -a android.intent.action.VIEW \
  -d "app://nestednavigationsample/settings/about/details" \
  com.example.nestednavigationsample
```

## Notes

- Screens own their TopAppBar (titles/icons) and decide whether to include the bottom bar.
- Tabbing logic uses a centralized lambda so behavior is consistent and easy to read.
- This project uses Navigation-Compose and Material 3.
