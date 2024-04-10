package com.equal.shopping.cart.models.entities

data class CartItem(
    val productName: String,
    var quantity: Int,
    var total: Double
)
