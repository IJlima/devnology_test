package com.example.devnologyteste.data

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CartRepository private constructor() {
    companion object {
        @Volatile
        private var INSTANCE: CartRepository? = null
        fun getInstance() = INSTANCE ?: synchronized(this) {
            INSTANCE ?: CartRepository().also {
                INSTANCE = it
            }
        }
        const val TAG = "CartRepository"
    }
    private val cartItems: MutableList<CartItem> = mutableListOf()

    suspend fun getCartItems(): List<CartItem> = suspendCoroutine {
        it.resume(cartItems)
    }
    fun addItemToCart(item: Item) {
        val exist = cartItems.find { cartItem -> cartItem.item == item }
        if(exist == null) {
            cartItems.add(CartItem (item, 1))
        } else {
            exist.quantity++
        }
    }
    fun getItemCount(): Int {
        var count = 0
        for (cartItem in cartItems) {
            count += cartItem.quantity
        }
        return count
    }
    fun removeItem(item: CartItem) {
        cartItems.remove(item)
    }
}