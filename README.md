# API Integration - Clean Architecture

This project demonstrates a clean architecture implementation for Android with API integration using Retrofit, Hilt, and Jetpack Compose.

## Architecture Overview

The project follows **Clean Architecture** principles with clear separation of concerns:

```
app/
└── src/main/java/com/example/apiintegration/
    ├── data/                    # Data Layer
    │   ├── remote/              # Remote data sources
    │   │   ├── dto/             # Data Transfer Objects (API models)
    │   │   │   ├── AuthModels.kt
    │   │   │   ├── GeminiModels.kt
    │   │   │   └── PostResponse.kt
    │   │   └── GeminiApi.kt     # Retrofit API interface
    │   └── repository/          # Repository implementations
    │       ├── AuthRepositoryImpl.kt
    │       ├── GeminiRepositoryImpl.kt
    │       └── PostRepositoryImpl.kt
    │
    ├── domain/                  # Domain Layer (Business Logic)
    │   ├── model/               # Domain models
    │   │   ├── Post.kt
    │   │   └── User.kt
    │   ├── repository/          # Repository interfaces
    │   │   ├── AuthRepository.kt
    │   │   ├── GeminiRepository.kt
    │   │   └── PostRepository.kt
    │   └── usecase/             # Use cases (business logic)
    │       ├── GenerateContentUseCase.kt
    │       ├── GetPostsUseCase.kt
    │       ├── GetPostUseCase.kt
    │       └── LoginUseCase.kt
    │
    ├── presentation/            # Presentation Layer (UI)
    │   ├── auth/
    │   │   ├── AuthScreen.kt
    │   │   └── AuthViewModel.kt
    │   ├── gemini/
    │   │   ├── GeminiScreen.kt
    │   │   └── GeminiViewModel.kt
    │   └── posts/
    │       ├── PostScreen.kt
    │       └── PostViewModel.kt
    │
    ├── di/                      # Dependency Injection
    │   ├── GeminiModule.kt
    │   ├── NetworkModule.kt
    │   └── RepositoryModule.kt
    │
    ├── navigation/              # Navigation
    │   ├── NavGraph.kt
    │   └── Screen.kt
    │
    ├── ui/theme/                # UI Theme
    │   ├── Color.kt
    │   ├── Theme.kt
    │   └── Type.kt
    │
    ├── ApiIntegrationApp.kt     # Application class
    └── MainActivity.kt          # Main activity
```

## Layer Responsibilities

### 1. Data Layer (`data/`)
**Purpose**: Handles data operations and external data sources.

- **`remote/dto/`**: Data Transfer Objects that match API responses
- **`remote/GeminiApi.kt`**: Retrofit interface defining API endpoints
- **`repository/`**: Concrete implementations of repository interfaces
  - Maps DTOs to domain models
  - Handles API calls and error handling
  - Returns `Result<T>` for safe error handling

### 2. Domain Layer (`domain/`)
**Purpose**: Contains business logic and is independent of frameworks.

- **`model/`**: Domain models (pure Kotlin classes)
  - Represent business entities
  - Independent of API structure
- **`repository/`**: Repository interfaces (contracts)
  - Define what data operations are available
  - No implementation details
- **`usecase/`**: Business logic operations
  - Single responsibility per use case
  - Orchestrate repository calls
  - Can combine multiple repositories if needed

### 3. Presentation Layer (`presentation/`)
**Purpose**: Handles UI and user interactions.

- **ViewModels**: 
  - Manage UI state
  - Inject use cases (not repositories directly)
  - Use `StateFlow` for reactive UI updates
- **Screens**: 
  - Jetpack Compose UI components
  - Observe ViewModel state
  - Trigger ViewModel actions

### 4. Dependency Injection (`di/`)
**Purpose**: Provides dependencies using Hilt.

- **`NetworkModule`**: Provides OkHttp, Moshi, logging interceptor
- **`GeminiModule`**: Provides Retrofit and API interface
- **`RepositoryModule`**: Binds repository interfaces to implementations

## Data Flow

```
UI (Screen) 
    ↓ observes state
ViewModel 
    ↓ calls
UseCase 
    ↓ calls
Repository Interface 
    ↓ implemented by
Repository Implementation 
    ↓ calls
API (Retrofit)
```

## Key Features

### 1. **Separation of Concerns**
- Each layer has a single responsibility
- Easy to test each layer independently
- Changes in one layer don't affect others

### 2. **Dependency Inversion**
- High-level modules (domain) don't depend on low-level modules (data)
- Both depend on abstractions (interfaces)
- ViewModels depend on use cases, not repositories

### 3. **Type Safety**
- DTOs for API responses
- Domain models for business logic
- Mapping between layers prevents leaking implementation details

### 4. **Error Handling**
- Uses `Result<T>` for safe error propagation
- UI states (Idle, Loading, Success, Error) for clear UI feedback

## APIs Integrated

1. **JSONPlaceholder** - Posts API
   - GET /posts - List all posts
   - GET /posts/1 - Get single post

2. **DummyJSON** - Authentication API
   - POST /auth/login - User authentication

3. **Google Gemini** - AI Content Generation
   - POST /v1beta/models/gemini-2.5-flash:generateContent

## Dependencies

- **Hilt**: Dependency injection
- **Retrofit**: HTTP client
- **Moshi**: JSON parsing
- **Jetpack Compose**: Modern UI toolkit
- **Coroutines**: Asynchronous programming
- **StateFlow**: Reactive state management
- **Coil**: Image loading

## How to Use

### Adding a New Feature

1. **Create Domain Model** (`domain/model/`)
   ```kotlin
   data class YourModel(val id: Int, val name: String)
   ```

2. **Create Repository Interface** (`domain/repository/`)
   ```kotlin
   interface YourRepository {
       suspend fun getData(): Result<List<YourModel>>
   }
   ```

3. **Create Use Case** (`domain/usecase/`)
   ```kotlin
   class GetDataUseCase @Inject constructor(
       private val repository: YourRepository
   ) {
       suspend operator fun invoke(): Result<List<YourModel>> {
           return repository.getData()
       }
   }
   ```

4. **Create DTO** (`data/remote/dto/`)
   ```kotlin
   @JsonClass(generateAdapter = true)
   data class YourDto(val id: Int, val name: String)
   ```

5. **Implement Repository** (`data/repository/`)
   ```kotlin
   class YourRepositoryImpl @Inject constructor(
       private val api: YourApi
   ) : YourRepository {
       override suspend fun getData(): Result<List<YourModel>> {
           return try {
               val response = api.getData()
               Result.success(response.map { it.toDomain() })
           } catch (e: Exception) {
               Result.failure(e)
           }
       }
       
       private fun YourDto.toDomain() = YourModel(id, name)
   }
   ```

6. **Bind Repository** (`di/RepositoryModule.kt`)
   ```kotlin
   @Binds
   @Singleton
   abstract fun bindYourRepository(
       impl: YourRepositoryImpl
   ): YourRepository
   ```

7. **Create ViewModel** (`presentation/yourfeature/`)
   ```kotlin
   @HiltViewModel
   class YourViewModel @Inject constructor(
       private val getDataUseCase: GetDataUseCase
   ) : ViewModel() {
       // Implementation
   }
   ```

8. **Create Screen** (`presentation/yourfeature/`)
   ```kotlin
   @Composable
   fun YourScreen(viewModel: YourViewModel = hiltViewModel()) {
       // UI implementation
   }
   ```

## Benefits of This Architecture

✅ **Testability**: Each layer can be tested independently  
✅ **Maintainability**: Clear structure makes code easy to understand  
✅ **Scalability**: Easy to add new features without affecting existing code  
✅ **Flexibility**: Can swap implementations (e.g., change from Retrofit to Ktor)  
✅ **Reusability**: Use cases can be reused across different ViewModels  
✅ **Type Safety**: Compile-time safety with proper typing  

## Notes

- API keys should be stored in `local.properties` and accessed via `BuildConfig` in production
- Currently using hardcoded API key for Gemini (for demo purposes only)
- All network calls use coroutines for asynchronous operations
- UI state management uses `StateFlow` for reactive updates
