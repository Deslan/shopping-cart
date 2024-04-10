package com.equal.shopping.cart.controllers

import com.equal.shopping.cart.models.entities.CartItem
import com.equal.shopping.cart.models.entities.CartState
import com.equal.shopping.cart.service.CartService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cart")
class CartController(
    private val cartService: CartService
) {

    @PostMapping("/add-product")
    fun addProductToCart(
        @RequestParam("productName") productName: String,
        @RequestParam("quantity") quantity: Int
    ): List<CartItem> {
        val lowerCaseProductName = productName.lowercase()
        return cartService.addProductToCart(lowerCaseProductName, quantity)
    }

    @GetMapping("/state")
    fun getCartState(): CartState {
        return cartService.calculateCartState()
    }
}