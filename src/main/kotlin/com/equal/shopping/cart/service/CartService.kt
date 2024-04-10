package com.equal.shopping.cart.service

import com.equal.shopping.cart.models.entities.CartItem
import com.equal.shopping.cart.models.entities.CartState

interface CartService {
    fun addProductToCart(productName: String, quantity: Int): List<CartItem>
    fun getAllCartItems(): List<CartItem>
    fun calculateCartState(): CartState
}