# SecureWire NewsPortal

[![Kotlin](https://img.shields.io/badge/Kotlin-100%25-blue.svg)](https://kotlinlang.org/)
[![Version](https://img.shields.io/badge/version-1.0-green.svg)](https://github.com/SidqiRaafi/SecureWire_NewsPortal)
[![License](https://img.shields.io/badge/license-MIT-orange.svg)](LICENSE)

**SecureWire NewsPortal** is an Android news application built with Kotlin that delivers the latest news articles with a focus on security and user privacy. This application provides users with an intuitive interface to browse, search, and read news from various sources while maintaining data security.

---

## 🚀 Features

- **Real-Time News Updates**: Fetch and display the latest news articles from multiple sources
- **Category-Based Browsing**: Browse news by categories (Technology, Business, Sports, Entertainment, Health, Science)
- **Article Search**: Search for specific news topics and articles
- **Offline Reading**: Save articles locally for offline access
- **Bookmark Functionality**: Save favorite articles for later reading
- **Secure Data Storage**: Encrypted local storage for user data and preferences
- **Clean UI/UX**: Modern Material Design interface for seamless user experience
- **Share Articles**: Share interesting articles with friends and family
- **Dark Mode Support**: Eye-friendly reading experience in low-light conditions

---

## 🏗️ Architecture

This project follows the **MVVM (Model-View-ViewModel)** architecture pattern with Clean Architecture principles:

```
SecureWire_NewsPortal/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/securewire/newsportal/
│   │   │   │   ├── data/
│   │   │   │   │   ├── local/
│   │   │   │   │   │   ├── dao/
│   │   │   │   │   │   ├── database/
│   │   │   │   │   │   └── entities/
│   │   │   │   │   ├── remote/
│   │   │   │   │   │   ├── api/
│   │   │   │   │   │   └── dto/
│   │   │   │   │   └── repository/
│   │   │   │   ├── domain/
│   │   │   │   │   ├── model/
│   │   │   │   │   ├── repository/
│   │   │   │   │   └── usecase/
│   │   │   │   ├── presentation/
│   │   │   │   │   ├── ui/
│   │   │   │   │   │   ├── home/
│   │   │   │   │   │   ├── detail/
│   │   │   │   │   │   ├── search/
│   │   │   │   │   │   └── bookmarks/
│   │   │   │   │   └── viewmodel/
│   │   │   │   └── utils/
│   │   │   └── res/
│   │   └── test/
│   └── build.gradle
└── build.gradle
```

### Architecture Layers

**Presentation Layer**: Contains UI components (Activities, Fragments, Composables) and ViewModels
- Displays data to users
- Handles user interactions
- Observes data changes from ViewModel

**Domain Layer**: Contains business logic and use cases
- Defines business rules
- Independent of frameworks
- Contains repository interfaces

**Data Layer**: Manages data sources (API, Database)
- Implements repository interfaces
- Handles data operations
- Manages caching and synchronization

---

## 🛠️ Tech Stack

### Core Technologies
- **Language**: Kotlin 100%
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)

### Android Architecture Components
- **ViewModel**: Manages UI-related data lifecycle-aware manner
- **LiveData/Flow**: Observable data holder for reactive programming
- **Room Database**: Local data persistence with SQLite
- **Navigation Component**: Fragment navigation and deep linking
- **DataBinding/ViewBinding**: Binds UI components to data sources

### Networking & API
- **Retrofit**: Type-safe HTTP client for API calls
- **OkHttp**: HTTP & HTTP/2 client with interceptors
- **Gson/Moshi**: JSON serialization and deserialization
- **News API**: External news API integration ([NewsAPI.org](https://newsapi.org/))

### Dependency Injection
- **Hilt/Dagger**: Dependency injection framework
- **Koin** (Alternative): Lightweight dependency injection

### Asynchronous Programming
- **Kotlin Coroutines**: Asynchronous programming and concurrency
- **Flow**: Cold asynchronous data streams

### UI & Design
- **Material Design 3**: Modern Android UI components
- **Jetpack Compose** (Optional): Modern declarative UI toolkit
- **Glide/Coil**: Image loading and caching
- **RecyclerView**: Efficient list display
- **CardView**: Material design card layout

### Security
- **EncryptedSharedPreferences**: Secure local data storage
- **SSL Pinning**: Prevent man-in-the-middle attacks
- **ProGuard/R8**: Code obfuscation and shrinking

### Testing
- **JUnit**: Unit testing framework
- **Mockito**: Mocking framework for unit tests
- **Espresso**: UI testing framework
- **Truth**: Fluent assertion library

### Build & CI/CD
- **Gradle**: Build automation
- **GitHub Actions** (Optional): Continuous integration

---

## 📋 Prerequisites

Before running this project, ensure you have:

- **Android Studio**: Arctic Fox (2020.3.1) or newer
- **JDK**: Java Development Kit 11 or higher
- **Android SDK**: API Level 24 or higher
- **News API Key**: Register at [NewsAPI.org](https://newsapi.org/) for free API access

---

## 🔧 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/SidqiRaafi/SecureWire_NewsPortal.git
cd SecureWire_NewsPortal
```

### 2. Get Your News API Key

1. Visit [NewsAPI.org](https://newsapi.org/)
2. Sign up for a free account
3. Generate your API key from the dashboard

### 3. Configure API Key

Create a `local.properties` file in the root directory (if it doesn't exist) and add your API key:

```properties
NEWS_API_KEY="your_api_key_here"
```

Or add it to `gradle.properties`:

```properties
NEWS_API_KEY=your_api_key_here
```

### 4. Sync Project with Gradle Files

Open the project in Android Studio and let Gradle sync all dependencies.

### 5. Build and Run

- Connect an Android device or start an emulator
- Click **Run** (Shift + F10) or use the play button in Android Studio

---

## 🔑 API Configuration

The application uses **News API** to fetch news articles. Configure the API endpoints in `ApiService.kt`:

```kotlin
interface NewsApiService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("category") category: String? = null,
        @Query("apiKey") apiKey: String
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int = 1
    ): Response<NewsResponse>
}
```

---

## 📱 Screenshots

> **Note**: Add screenshots of your application here

| Home Screen | Article Detail | Bookmarks |
|-------------|---------------|-----------|
| ![Home](screenshots/home.png) | ![Detail](screenshots/detail.png) | ![Bookmarks](screenshots/bookmarks.png) |

---

## 🧪 Testing

### Run Unit Tests

```bash
./gradlew test
```

### Run Instrumented Tests

```bash
./gradlew connectedAndroidTest
```

### Code Coverage

```bash
./gradlew jacocoTestReport
```

---

## 🔐 Security Features

**SecureWire** prioritizes user privacy and data security:

1. **Encrypted Local Storage**: User preferences and cached data are encrypted using Android's EncryptedSharedPreferences
2. **SSL Pinning**: Prevents man-in-the-middle attacks by validating server certificates
3. **ProGuard/R8**: Code obfuscation to protect against reverse engineering
4. **Secure API Communication**: All API calls use HTTPS protocol
5. **No Personal Data Collection**: The app doesn't collect or store personal user information
6. **Minimal Permissions**: Only requests necessary permissions for core functionality

---

## 🗺️ Roadmap

### Version 1.0 (Current)
- ✅ Basic news browsing functionality
- ✅ Category-based filtering
- ✅ Article search
- ✅ Bookmark feature
- ✅ Offline reading

### Version 1.1 (Upcoming)
- 🔄 Push notifications for breaking news
- 🔄 Multiple language support
- 🔄 Personalized news feed based on preferences
- 🔄 Social media integration
- 🔄 Article comments and discussions

### Version 2.0 (Future)
- 📅 AI-powered news recommendations
- 📅 Audio article playback (Text-to-Speech)
- 📅 Video news integration
- 📅 Podcast support
- 📅 User authentication and sync across devices

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. **Fork the repository**
2. **Create a feature branch**
   ```bash
   git checkout -b feature/AmazingFeature
   ```
3. **Commit your changes**
   ```bash
   git commit -m 'Add some AmazingFeature'
   ```
4. **Push to the branch**
   ```bash
   git push origin feature/AmazingFeature
   ```
5. **Open a Pull Request**

### Code Style Guidelines

- Follow [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use meaningful variable and function names
- Write unit tests for new features
- Document complex logic with comments
- Keep functions small and focused

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2025 Sidqi Raafi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

---

## 👤 Author

**Sidqi Raafi**

- GitHub: [@SidqiRaafi](https://github.com/SidqiRaafi)
- Project Link: [SecureWire_NewsPortal](https://github.com/SidqiRaafi/SecureWire_NewsPortal)

---

## 🙏 Acknowledgments

- [News API](https://newsapi.org/) for providing news data
- [Android Developers](https://developer.android.com/) for excellent documentation
- [Kotlin Documentation](https://kotlinlang.org/docs/home.html) for language reference
- Open source community for various libraries and tools
- All contributors who help improve this project

---

## 📞 Support

If you encounter any issues or have questions:

- **Open an Issue**: [GitHub Issues](https://github.com/SidqiRaafi/SecureWire_NewsPortal/issues)
- **Discussions**: [GitHub Discussions](https://github.com/SidqiRaafi/SecureWire_NewsPortal/discussions)

---
