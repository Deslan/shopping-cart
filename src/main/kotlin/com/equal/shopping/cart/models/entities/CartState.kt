package com.equal.shopping.cart.models.entities

data class CartState(
    val cartItems: List<CartItem>,
    val subtotal: String,
    val tax: String,
    var total: String
)