import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.apiintegration.presentation.cart.CartCard
import com.example.apiintegration.presentation.cart.CartViewModel
import com.example.apiintegration.presentation.cart.CartsUiState

//@Composable
//fun CartScreen(
//    navController: NavController,
//    cartViewModel: CartViewModel = hiltViewModel()
//) {
//    val uiState by cartViewModel.uiState.collectAsState()
//
//    // 🔥 Trigger API call ONCE when screen opens
//    LaunchedEffect(Unit) {
//        cartViewModel.getCarts()
//    }
//
//    when (uiState) {
//        is CartsUiState.Loading -> {
//            CircularProgressIndicator(
//                modifier = Modifier.fillMaxWidth()
//            )
//        }
//
//        is CartsUiState.Success -> {
//            val carts = (uiState as CartsUiState.Success).data
//
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(12.dp)
//            ) {
//                items(carts) { cart ->
//                    CartCard(cart, navController)
//                    Spacer(modifier = Modifier.height(12.dp))
//                }
//            }
//        }
//
//        is CartsUiState.Error -> {
//            Text("Error is Going")
//        }
//
//        CartsUiState.Idle -> {
//            // optional: show placeholder instead of blank
//            Text("Preparing carts...")
//        }
//    }
//}


@Composable
fun CartScreen(
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val uiState by cartViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        cartViewModel.getCarts()
    }

    when (uiState) {
        CartsUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is CartsUiState.Success -> {
            val carts = (uiState as CartsUiState.Success).data
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {
                items(carts) { cart ->
                    CartCard(cart, navController)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }

        is CartsUiState.Error -> {
            Text(
                text = (uiState as CartsUiState.Error).message,
                modifier = Modifier.padding(16.dp)
            )
        }

        CartsUiState.Idle -> {
            Text("Preparing carts…", modifier = Modifier.padding(16.dp))
        }
    }
}

