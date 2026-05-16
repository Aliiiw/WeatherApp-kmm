# WeatherApp-kmm

WeatherApp-kmm is a Kotlin Multiplatform Mobile weather app learning project. The shared module contains the weather networking, DTO models, repository, use case, ViewModel, and logging setup, while Android and iOS provide platform entry points.

The current shared layer fetches current weather and forecasts from OpenWeatherMap. The Android UI currently displays a greeting and logs weather loading/result states through Napier; the weather presentation UI is not fully implemented yet.

## Features

- Kotlin Multiplatform shared module for Android and iOS.
- Ktor client configured for OpenWeatherMap requests.
- Kotlinx Serialization DTOs for current weather and forecast responses.
- Repository abstraction for current weather and forecast calls.
- Use case that requests current weather and forecast data in parallel.
- Shared `ForecastsViewModel` with `StateFlow` UI state.
- Weather UI model mapping for current conditions and forecast rows.
- Napier logging helper for shared/platform logging.
- BuildKonfig setup for API key configuration.
- Android app built with Jetpack Compose and Material 3.
- iOS app entry point using SwiftUI and the shared framework.

## Tech Stack

- Kotlin Multiplatform
- Kotlin Coroutines
- Ktor Client
- Kotlinx Serialization
- BuildKonfig
- Napier
- AndroidX Lifecycle ViewModel
- Jetpack Compose
- Material 3
- SwiftUI

## Project Structure

```text
.
├── shared
│   ├── src/commonMain/kotlin/ir/alirahimi/weatherapp
│   │   ├── base/Network.kt                # Ktor client setup
│   │   ├── repository                     # Weather repository and DTOs
│   │   ├── usecase/GetWeatherForecastsUseCase.kt
│   │   ├── viewmodel/ForecastsViewModel.kt
│   │   ├── viewmodel/model/WeatherModel.kt
│   │   ├── NapierHelper.kt
│   │   ├── Greeting.kt
│   │   └── Platform.kt
│   ├── src/androidMain                    # Android platform code
│   └── src/iosMain                        # iOS platform code
├── androidApp                             # Android Compose app
├── iosApp                                 # SwiftUI iOS app
├── gradle/libs.versions.toml
└── README.md
```

## Data Flow

1. `ForecastsViewModel` starts loading in `init`.
2. `GetWeatherForecastsUseCase` calls the repository for current weather and forecasts in parallel.
3. `WeatherRepository` uses the shared Ktor client to call OpenWeatherMap endpoints.
4. DTO responses are mapped into `WeatherModel` items.
5. The UI state exposes loading, success items, or failure through `StateFlow`.

## API Configuration

The project is designed to use OpenWeatherMap API configuration. Keep API keys out of public source code in production by storing them in `local.properties`, environment variables, or CI secrets and passing them through BuildKonfig/build configuration.

Example local configuration:

```properties
API_KEY=your_openweathermap_api_key
```

## Current UI State

- Android: initializes `ForecastsViewModel`, logs loading/failure/items with Napier, and displays a greeting.
- iOS: displays a shared greeting and calls the shared Napier helper.

## Getting Started

1. Clone the repository.
2. Add the required API key configuration.
3. Open the project in Android Studio.
4. Sync Gradle.
5. Run `androidApp` for Android.
6. Open `iosApp/iosApp.xcodeproj` in Xcode to run the iOS app.

## Build Examples

```bash
./gradlew :androidApp:assembleDebug
./gradlew :shared:compileKotlinAndroid
```

## Notes

This repository is a KMM learning project based on weather networking and shared business logic. The shared data layer is more complete than the UI layer, so future work would likely focus on rendering the weather models on Android and iOS.
