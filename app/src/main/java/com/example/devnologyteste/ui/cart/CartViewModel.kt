package com.example.devnologyteste.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devnologyteste.R
import com.example.devnologyteste.data.CartItem
import com.example.devnologyteste.data.CartRepository
import com.example.devnologyteste.data.Item
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    private val cartRepository = CartRepository.getInstance()

    private val _cartItems = MutableLiveData<List<CartItem>>()
    val cartItems: LiveData<List<CartItem>> = _cartItems

    private val _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double> = _totalPrice

    private val _cartCount = MutableLiveData<Int>()
    val cartCount: LiveData<Int> = _cartCount

    init {
        viewModelScope.launch {
            val allItems = cartRepository.getCartItems()
            _cartItems.value = allItems
            var total = 0.0
            for (cartItem in allItems) {
                total += cartItem.quantity * cartItem.item.price
            }
            _totalPrice.value = total
        }
    }

    fun removeItemFromCart(item: CartItem) {
        cartRepository.removeItem(item)
    }

    fun calculateTotal(allItems : List<CartItem>) {
        var total = 0.0
        var count = 0
        for (cartItem in allItems) {
            total += cartItem.quantity * cartItem.item.price
            count += cartItem.quantity
        }
        _totalPrice.value = total
        _cartCount.value = count
    }
}