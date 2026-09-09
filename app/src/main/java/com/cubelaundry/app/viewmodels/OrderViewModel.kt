package com.cubelaundry.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cubelaundry.app.api.RetrofitClient
import com.cubelaundry.app.models.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class OrderUiState(
    val isLoading: Boolean = false,
    val orderResult: OrderResponse? = null,
    val error: String? = null
)

class OrderViewModel : ViewModel() {
    private val _state = MutableStateFlow(OrderUiState())
    val state: StateFlow<OrderUiState> = _state

    fun placeOrder(name: String, phone: String, address: String, lineItems: List<LineItem>) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val customer = Customer(
                    customerName = name,
                    mobileNumber = phone,
                    address = address
                )
                val request = OrderRequest(customer, lineItems)
                val response = RetrofitClient.instance.placeOrder(request)
                if (response.isSuccessful && response.body()?.ok == true) {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        orderResult = response.body()
                    )
                } else {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = response.body()?.message ?: "Order failed"
                    )
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Network error: ${e.message}"
                )
            }
        }
    }
}
