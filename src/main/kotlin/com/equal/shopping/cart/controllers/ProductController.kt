package com.equal.shopping.cart.controllers

import com.equal.shopping.cart.models.entities.Product
import com.equal.shopping.cart.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(
    private val productService: ProductService
) {
    @GetMapping("/products/{productName}")
    fun getProduct(@PathVariable productName: String): Product {
        return productService.getProduct(productName)
    }
}