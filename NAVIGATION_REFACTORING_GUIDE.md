# Navigation Refactoring Guide: Passing ID Through Navigation

This guide documents the refactoring of product detail navigation to follow Android architecture best practices: **passing only IDs through navigation** and **fetching full objects via ViewModel/Repository**.

## 📋 Table of Contents

1. [Overview](#overview)
2. [Why This Pattern?](#why-this-pattern)
3. [Architecture Layers](#architecture-layers)
4. [Step-by-Step Implementation](#step-by-step-implementation)
5. [Code Changes Summary](#code-changes-summary)
6. [Testing the Changes](#testing-the-changes)
7. [Best Practices](#best-practices)

---

## 🎯 Overview

### Before
- Product object was passed directly through navigation
- Screen received the full Product object as a parameter
- No separation between navigation and data fetching

### After
- Only product ID is passed through navigation
- ViewModel fetches the product using the ID
- Repository handles data fetching from API
- Clean separation of concerns following MVVM architecture

---

## 🤔 Why This Pattern?

### Industry Standards & Benefits

1. **Single Source of Truth**: Data is fetched fresh from the repository, ensuring consistency
2. **Parcelable Limitations**: Large objects can't be passed through navigation safely
3. **State Management**: ViewModel manages state, making it easier to handle loading/error states
4. **Testability**: Easier to test ViewModels and Repositories independently
5. **Memory Efficiency**: Only IDs are stored in navigation backstack, not entire objects
6. **Data Freshness**: Always fetches latest data from API/repository
7. **Separation of Concerns**: Navigation layer doesn't need to know about domain models

### Android Architecture Guidelines

This pattern aligns with:
- **Google's Architecture Components** recommendations
- **MVVM (Model-View-ViewModel)** architecture
- **Clean Architecture** principles
- **Jetpack Navigation** best practices

---

## 🏗️ Architecture Layers

```
┌─────────────────────────────────────────┐
│         UI Layer (Compose)              │
│  ProductDetailsScreen                   │
│  - Receives productId from navigation   │
│  - Observes ViewModel state             │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│      Presentation Layer (ViewModel)      │
│  ProductViewModel                       │
│  - Receives productId                   │
│  - Calls UseCase to fetch product      │
│  - Manages UI state (Loading/Success/Error)│
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│         Domain Layer (UseCase)          │
│  GetProductByIdUseCase                  │
│  - Business logic                       │
│  - Calls Repository                     │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│         Data Layer (Repository)         │
│  ProductRepository                      │
│  - Fetches from API                     │
│  - Returns Result<Product>              │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────▼───────────────────────┐
│         Data Source (API)               │
│  GeminiApi.getProductById(id)            │
└─────────────────────────────────────────┘
```

---

## 📝 Step-by-Step Implementation

### Step 1: Add API Endpoint

**File**: `app/src/main/java/com/example/apiintegration/data/remote/GeminiApi.kt`

```kotlin
@GET("https://dummyjson.com/products/{id}")
suspend fun getProductById(@retrofit2.http.Path("id") id: Long): Product
```

**Why**: Defines the API endpoint to fetch a single product by ID.

---

### Step 2: Update Repository Interface

**File**: `app/src/main/java/com/example/apiintegration/domain/repository/ProductRepository.kt`

```kotlin
interface ProductRepository {
    suspend fun getProducts(): Result<ProductResponse>
    suspend fun getProductById(id: Long): Result<Product>  // ✅ Added
}
```

**Why**: Repository interface defines the contract for data operations.

---

### Step 3: Implement Repository Method

**File**: `app/src/main/java/com/example/apiintegration/data/repository/ProductRepositoryImpl.kt`

```kotlin
override suspend fun getProductById(id: Long): Result<Product> {
    return try {
        val response = geminiApi.getProductById(id)
        Result.success(response)
    } catch (e: Exception) {
        Result.failure(e)
    }
}
```

**Why**: Implements the repository interface, handles API calls and error handling.

---

### Step 4: Create UseCase

**File**: `app/src/main/java/com/example/apiintegration/domain/usecase/product/GetProductByIdUseCase.kt`

```kotlin
class GetProductByIdUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(id: Long): Result<Product> {
        return productRepository.getProductById(id)
    }
}
```

**Why**: UseCase encapsulates business logic and provides a clean interface for ViewModel.

---

### Step 5: Update ViewModel

**File**: `app/src/main/java/com/example/apiintegration/presentation/products/ProductViewModel.kt`

**Changes**:
1. Inject `GetProductByIdUseCase`
2. Add `ProductDetailUiState` sealed class
3. Add `productDetailState` StateFlow
4. Add `getProductById(id: Long)` method

```kotlin
@HiltViewModel
class ProductViewModel @Inject constructor(
    val allProductUseCase: AllProductUseCase,
    private val getProductByIdUseCase: GetProductByIdUseCase  // ✅ Added
): ViewModel() {

    private val _productDetailState = MutableStateFlow<ProductDetailUiState>(ProductDetailUiState.Idle)
    val productDetailState: StateFlow<ProductDetailUiState> = _productDetailState.asStateFlow()

    fun getProductById(id: Long) {
        _productDetailState.value = ProductDetailUiState.Loading
        viewModelScope.launch {
            try {
                val result = getProductByIdUseCase(id)
                result.onSuccess { product ->
                    _productDetailState.value = ProductDetailUiState.Success(product)
                }.onFailure { error ->
                    _productDetailState.value = ProductDetailUiState.Error(
                        error.message ?: "Unknown error occurred"
                    )
                }
            } catch (e: Exception) {
                if (e !is kotlinx.coroutines.CancellationException) {
                    _productDetailState.value = ProductDetailUiState.Error(
                        e.message ?: "Unknown error occurred"
                    )
                }
            }
        }
    }
}

sealed class ProductDetailUiState {
    object Idle : ProductDetailUiState()
    object Loading : ProductDetailUiState()
    data class Success(val product: Product) : ProductDetailUiState()
    data class Error(val message: String) : ProductDetailUiState()
}
```

**Why**: ViewModel manages UI state and coordinates data fetching.

---

### Step 6: Update Navigation Screen Definition

**File**: `app/src/main/java/com/example/apiintegration/navigation/Screen.kt`

```kotlin
object ProductDetailScreen : Screen("product_details/{productId}") {
    fun createRoute(productId: Long) = "product_details/$productId"
}
```

**Why**: Defines the navigation route with productId as a parameter.

---

### Step 7: Update Navigation Graph

**File**: `app/src/main/java/com/example/apiintegration/navigation/NavGraph.kt`

```kotlin
composable(
    route = Screen.ProductDetailScreen.route,
    arguments = listOf(navArgument("productId") { type = NavType.LongType })
) { backStackEntry ->
    val productId = backStackEntry.arguments?.getLong("productId") ?: 0L
    ProductDetailScreen(productId = productId, navController = navController)
}
```

**Why**: Extracts productId from navigation arguments and passes it to the screen.

---

### Step 8: Update Product List Navigation

**File**: `app/src/main/java/com/example/apiintegration/presentation/products/ProductList.kt`

```kotlin
.clickable {
    navController.navigate(Screen.ProductDetailScreen.createRoute(product.id))
}
```

**Why**: Navigates to product detail screen passing only the product ID.

**Don't forget to import**:
```kotlin
import com.example.apiintegration.navigation.Screen
```

---

### Step 9: Update Product Details Screen

**File**: `app/src/main/java/com/example/apiintegration/presentation/products/ProductDetailsScreen.kt`

**Key Changes**:
1. Receives `productId: Long` instead of `product: Product`
2. Uses `hiltViewModel()` to get ViewModel instance
3. Observes `productDetailState` using `collectAsState()`
4. Calls `viewModel.getProductById(productId)` in `LaunchedEffect`
5. Handles all UI states: Loading, Success, Error

```kotlin
@Composable
fun ProductDetailScreen(
    productId: Long,
    navController: NavController? = null,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val productDetailState by viewModel.productDetailState.collectAsState()

    LaunchedEffect(productId) {
        if (productId > 0) {
            viewModel.getProductById(productId)
        }
    }

    // Handle different states: Loading, Success, Error
    when (val state = productDetailState) {
        is ProductDetailUiState.Loading -> { /* Show loading */ }
        is ProductDetailUiState.Success -> { /* Show product */ }
        is ProductDetailUiState.Error -> { /* Show error */ }
    }
}
```

**Why**: Screen is now a pure UI component that observes ViewModel state.

---

## 📊 Code Changes Summary

| File | Change Type | Description |
|------|------------|-------------|
| `GeminiApi.kt` | Added | `getProductById(id: Long)` endpoint |
| `ProductRepository.kt` | Modified | Added `getProductById(id: Long)` method |
| `ProductRepositoryImpl.kt` | Modified | Implemented `getProductById` |
| `GetProductByIdUseCase.kt` | Created | New use case for fetching product by ID |
| `ProductViewModel.kt` | Modified | Added product detail state and `getProductById` method |
| `Screen.kt` | Modified | Updated route to include `{productId}` parameter |
| `NavGraph.kt` | Modified | Extract productId from navigation arguments |
| `ProductList.kt` | Modified | Navigate with product ID using `createRoute` |
| `ProductDetailsScreen.kt` | Refactored | Receives ID, uses ViewModel to fetch product |

---

## 🧪 Testing the Changes

### Manual Testing Steps

1. **Navigate to Product List**
   - Open the app
   - Navigate to product list screen

2. **Click on a Product**
   - Tap any product card
   - Verify navigation to product detail screen

3. **Verify Loading State**
   - Should see loading indicator briefly

4. **Verify Product Display**
   - Product details should display correctly
   - All product information should be visible

5. **Test Error Handling**
   - Test with invalid product ID
   - Verify error message is displayed

6. **Test Back Navigation**
   - Press back button
   - Should return to product list

### Unit Testing (Recommended)

```kotlin
// Example test for ViewModel
@Test
fun `getProductById should update state to Success when product is fetched`() = runTest {
    // Given
    val productId = 1L
    val expectedProduct = Product(...)
    coEvery { getProductByIdUseCase(productId) } returns Result.success(expectedProduct)
    
    // When
    viewModel.getProductById(productId)
    
    // Then
    val state = viewModel.productDetailState.value
    assertTrue(state is ProductDetailUiState.Success)
    assertEquals(expectedProduct, (state as ProductDetailUiState.Success).product)
}
```

---

## ✅ Best Practices

### 1. **Always Pass Primitives Through Navigation**
   - ✅ Pass: `id: Long`, `userId: Int`, `name: String`
   - ❌ Avoid: Complex objects, data classes, Parcelable objects

### 2. **Use ViewModel for State Management**
   - ViewModel should manage all UI state
   - Use StateFlow or LiveData for reactive state
   - Handle loading, success, and error states

### 3. **Repository Pattern**
   - Repository is the single source of truth
   - Handles data fetching from API/database
   - Returns `Result<T>` for error handling

### 4. **UseCase for Business Logic**
   - Encapsulate business logic in UseCases
   - Makes code more testable and reusable
   - Follows Single Responsibility Principle

### 5. **Error Handling**
   - Always handle errors gracefully
   - Show user-friendly error messages
   - Handle cancellation exceptions properly

### 6. **Loading States**
   - Always show loading indicators
   - Improve user experience
   - Prevent multiple simultaneous requests

### 7. **Navigation Arguments**
   - Use type-safe navigation arguments
   - Validate arguments before use
   - Provide default values when appropriate

---

## 🔍 Key Takeaways

1. **Navigation Layer**: Only passes primitive types (IDs)
2. **ViewModel Layer**: Fetches data and manages state
3. **Repository Layer**: Single source of truth for data
4. **UseCase Layer**: Encapsulates business logic
5. **UI Layer**: Observes state and displays data

---

## 📚 Additional Resources

- [Android Navigation Component](https://developer.android.com/guide/navigation)
- [Android Architecture Components](https://developer.android.com/topic/architecture)
- [MVVM Pattern](https://developer.android.com/topic/architecture/ui-layer)
- [StateFlow and SharedFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)

---

## 🎉 Conclusion

This refactoring follows Android architecture best practices and industry standards. By passing only IDs through navigation and fetching objects via ViewModel/Repository, we achieve:

- ✅ Better separation of concerns
- ✅ Improved testability
- ✅ More maintainable code
- ✅ Better state management
- ✅ Alignment with Android guidelines

The code is now more scalable, testable, and follows the MVVM architecture pattern correctly.

