package com.equal.shopping.cart.service

import com.equal.shopping.cart.models.entities.Product

interface ProductService {
    fun getProduct(productName: String): Product
}