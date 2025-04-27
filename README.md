# News App

A Kotlin-based news app built using **Jetpack Compose** with **MVVM architecture**, **Coroutines**, **LiveData**, **Retrofit**, **Room**, and **Dagger Hilt**. The app allows users to view a list of news articles, read detailed articles, and supports pagination and offline caching.

## Features

- **News Feed**: Displays a list of news articles with smooth pagination.
- **News Details**: Allows users to view full details of each news article.
- **Offline Caching**: News articles are cached locally using **Room** for offline viewing.
- **Navigation**: Seamless navigation between the news list and news details using **Navigation Component**.
- **Asynchronous Data Handling**: Utilizes **Coroutines** and **LiveData** to handle data synchronously and update the UI efficiently.
- **Dependency Injection**: Uses **Dagger Hilt** for managing dependencies and simplifying testing.

## Tech Stack

- **Jetpack Compose**: UI toolkit for building native Android apps with a declarative approach.
- **MVVM Architecture**: Separates UI (View) from business logic (ViewModel) and data handling (Model).
- **Retrofit**: A type-safe HTTP client for Android and Java to handle API requests and responses.
- **Room**: A local database for caching news articles for offline use.
- **Coroutines**: Asynchronous programming to manage background tasks like API calls.
- **LiveData**: Observables to sync the UI with data updates.
- **Dagger Hilt**: Dependency Injection library to manage object creation and lifecycle.
- **Navigation Component**: Manages app navigation between different screens.

## Architecture

The app follows the **MVVM** architecture and is structured as follows:

1. **Presentation Layer**: 
   - **Jetpack Compose** UI components.
   - **ViewModel** for handling UI logic and interacting with UseCases.

2. **Domain Layer**:
   - **UseCases** for business logic.
   - **Repository Interfaces** for abstracting the data operations.

3. **Data Layer**:
   - **Repository Implementation**: Handles network calls via **Retrofit** and local caching via **Room**.
   - **API Service**: Fetches news articles from the remote API.
   - **Room Database**: Stores cached news data for offline access.

## Flow

1. **Home Screen**: Displays a list of news articles using **LazyColumn** and supports pagination. The data is fetched from an API using **Retrofit** and is cached locally using **Room**.
2. **News Detail Screen**: Displays the full article details when a user taps on a news item in the list.
3. **Data Sync**: **Coroutines** are used for background operations (API calls and database queries), while **LiveData** updates the UI when the data is fetched or changed.

## Dependencies

- **Jetpack Compose**: `androidx.compose.ui:ui:1.0.0`
- **Retrofit**: `com.squareup.retrofit2:retrofit:2.9.0`
- **Room**: `androidx.room:room-runtime:2.3.0`
- **Dagger Hilt**: `com.google.dagger:hilt-android:2.35`
- **Coroutines**: `org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0`
- **LiveData**: `androidx.lifecycle:lifecycle-livedata-ktx:2.3.1`
- **Navigation**: `androidx.navigation:navigation-compose:2.4.0-beta01`

## How It Works

1. **Fetching Data**: When the app loads, it calls the API through **Retrofit** to fetch the list of news articles.
2. **Pagination**: The app supports pagination, loading additional news articles as the user scrolls down the list.
3. **Caching**: Once articles are fetched, they are saved locally using **Room** so that users can access them offline.
4. **UI Updates**: **LiveData** observes the data and automatically updates the UI when new data is available or changes occur.
5. **Navigation**: The user can navigate between the list of articles and the detailed view of a specific article.

## SOLID Principles

The app follows **SOLID principles** for clean, maintainable, and testable code:

- **Single Responsibility Principle**: Each class has a distinct role (e.g., ViewModel handles UI logic, Repository handles data operations).
- **Open/Closed Principle**: The code is open for extension (adding new features) but closed for modification.
- **Liskov Substitution Principle**: Derived classes (e.g., repositories) can be substituted without affecting the system.
- **Interface Segregation Principle**: Interfaces are small and specific (e.g., separate interfaces for network operations and database operations).
- **Dependency Inversion Principle**: High-level modules depend on abstractions (e.g., repository interfaces), not concrete implementations.
