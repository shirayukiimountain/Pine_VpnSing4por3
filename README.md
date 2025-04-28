# Pine Hook for VPN Singapore

This project provides a **hook implementation** for the [VPN Singapore app](https://play.google.com/store/apps/details?id=com.privatevpn.internetaccess). The hook leverages the **Pine framework** to modify the application's behavior programmatically.

## Features

- **Premium Unlock**: Enables premium features without requiring a subscription.
- **Ad Blocker**: Disables all advertisements in the app.
- **Root Detection Bypass**: Prevents the app from detecting rooted devices.
- **Update Dialog Suppression**: Disables update checks and dialogs.
- **Custom Toast Message**: Displays a toast message when the app launches.

## How It Works

This hook is loaded into the app using the `ContentProvider` mechanism. The `TakeMeOut.java` file implements method hooks using the Pine library to override specific functionality in the app.

## Usage

1. Add the following `ContentProvider` configuration to your `AndroidManifest.xml`:

    ```xml
    <provider
        android:name="com.shi.vpnsingapore.TakeMe"
        android:exported="false"
        android:authorities="com.shi.vpnsingapore.TakeMe"
        android:initOrder="5000" />
    ```

2. Build and integrate the project into the targeted app environment.

3. The hooks will activate automatically when the app runs.

## Requirements

- **Android SDK**: Ensure you have the latest Android SDK installed.
- **Pine Framework**: This project uses the [Pine framework](https://github.com/canyie/pine) for method hooking.

## Disclaimer

This project is for educational and research purposes only. Unauthorized use of this software to modify or alter third-party apps is strictly prohibited and may violate terms of service or applicable laws.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.
