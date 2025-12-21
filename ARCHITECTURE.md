# Clean Architecture Structure

## Directory Structure

```
com.example.apiintegration/
│
├── 📱 MainActivity.kt
├── 🎯 ApiIntegrationApp.kt
│
├── 📊 data/                          # DATA LAYER
│   ├── remote/
│   │   ├── dto/                      # Data Transfer Objects
│   │   │   ├── AuthModels.kt         # LoginRequest, LoginResponse
│   │   │   ├── GeminiModels.kt       # GeminiRequest, GeminiResponse, Content, Part, Candidate
│   │   │   └── PostResponse.kt       # PostResponse
│   │   └── GeminiApi.kt              # Retrofit API interface
│   │
│   └── repository/                   # Repository Implementations
│       ├── AuthRepositoryImpl.kt     # Implements domain.repository.AuthRepository
│       ├── GeminiRepositoryImpl.kt   # Implements domain.repository.GeminiRepository
│       └── PostRepositoryImpl.kt     # Implements domain.repository.PostRepository
│
├── 🎯 domain/                        # DOMAIN LAYER (Business Logic)
│   ├── model/                        # Domain Models
│   │   ├── Post.kt                   # Domain model for Post
│   │   └── User.kt                   # Domain model for User
│   │
│   ├── repository/                   # Repository Interfaces
│   │   ├── AuthRepository.kt         # Contract for authentication
│   │   ├── GeminiRepository.kt       # Contract for Gemini AI
│   │   └── PostRepository.kt         # Contract for posts
│   │
│   └── usecase/                      # Use Cases (Business Logic)
│       ├── GenerateContentUseCase.kt # Generate AI content
│       ├── GetPostsUseCase.kt        # Get list of posts
│       ├── GetPostUseCase.kt         # Get single post
│       └── LoginUseCase.kt           # User login
│
├── 🎨 presentation/                  # PRESENTATION LAYER (UI)
│   ├── auth/
│   │   ├── AuthScreen.kt             # Login UI
│   │   └── AuthViewModel.kt          # Auth state management
│   │
│   ├── gemini/
│   │   ├── GeminiScreen.kt           # Gemini AI UI
│   │   └── GeminiViewModel.kt        # Gemini state management
│   │
│   └── posts/
│       ├── PostScreen.kt             # Posts list UI
│       └── PostViewModel.kt          # Posts state management
│
├── 💉 di/                            # DEPENDENCY INJECTION
│   ├── GeminiModule.kt               # Provides Retrofit & API
│   ├── NetworkModule.kt              # Provides OkHttp, Moshi, Logging
│   └── RepositoryModule.kt           # Binds Repository interfaces to implementations
│
├── 🧭 navigation/                    # NAVIGATION
│   ├── NavGraph.kt                   # Navigation graph
│   └── Screen.kt                     # Screen routes
│
└── 🎨 ui/theme/                      # UI THEME
    ├── Color.kt
    ├── Theme.kt
    └── Type.kt
```

## Layer Dependencies

```
┌─────────────────────────────────────────────────────────┐
│                   PRESENTATION LAYER                    │
│  (UI, ViewModels, Screens)                             │
│  • AuthScreen, AuthViewModel                           │
│  • GeminiScreen, GeminiViewModel                       │
│  • PostScreen, PostViewModel                           │
└────────────────────┬────────────────────────────────────┘
                     │ depends on
                     ↓
┌─────────────────────────────────────────────────────────┐
│                    DOMAIN LAYER                         │
│  (Business Logic - Pure Kotlin)                        │
│  • Models: Post, User                                  │
│  • Repository Interfaces                               │
│  • Use Cases                                           │
└────────────────────┬────────────────────────────────────┘
                     │ implemented by
                     ↓
┌─────────────────────────────────────────────────────────┐
│                     DATA LAYER                          │
│  (Data Sources, API, Database)                         │
│  • DTOs (API Models)                                   │
│  • Repository Implementations                          │
│  • API Interfaces (Retrofit)                           │
└─────────────────────────────────────────────────────────┘
```

## Data Flow

```
User Interaction (Screen)
        ↓
    ViewModel
        ↓
    Use Case
        ↓
Repository Interface (domain)
        ↓
Repository Implementation (data)
        ↓
    API Call (Retrofit)
        ↓
    DTO Response
        ↓
Map DTO → Domain Model
        ↓
Return Result<DomainModel>
        ↓
    Use Case
        ↓
    ViewModel (Update State)
        ↓
    Screen (Observe State)
        ↓
    UI Update
```

## Key Principles

### 1. Dependency Rule
- **Presentation** depends on **Domain**
- **Data** depends on **Domain**
- **Domain** depends on nothing (pure Kotlin)

### 2. Data Mapping
```
API Response (DTO) → Domain Model → UI State
```

Example:
```
LoginResponse (DTO) → User (Domain) → AuthUiState.Success(user)
```

### 3. Single Responsibility
- **ViewModel**: Manages UI state
- **UseCase**: Contains business logic
- **Repository**: Handles data operations
- **Screen**: Displays UI

### 4. Dependency Injection
All dependencies are injected via Hilt:
```kotlin
@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val getPostUseCase: GetPostUseCase
) : ViewModel()
```

## File Naming Conventions

- **DTOs**: `*Response.kt`, `*Request.kt`, `*Models.kt`
- **Domain Models**: Simple names like `Post.kt`, `User.kt`
- **Repositories**: 
  - Interface: `*Repository.kt` (in domain)
  - Implementation: `*RepositoryImpl.kt` (in data)
- **Use Cases**: `*UseCase.kt` (verb-based: Get, Create, Update, Delete)
- **ViewModels**: `*ViewModel.kt`
- **Screens**: `*Screen.kt`

## Benefits

✅ **Testability**: Each layer can be tested independently  
✅ **Maintainability**: Clear structure and separation of concerns  
✅ **Scalability**: Easy to add new features  
✅ **Flexibility**: Can swap implementations without affecting other layers  
✅ **Reusability**: Use cases can be shared across ViewModels  
✅ **Type Safety**: Compile-time safety with proper typing
