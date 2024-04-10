package com.equal.shopping.cart.controllers

import com.equal.shopping.cart.models.entities.CartItem
import com.equal.shopping.cart.models.entities.CartState
import com.equal.shopping.cart.service.CartService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CartControllerTest {

    @Mock
    lateinit var cartService: CartService

    @InjectMocks
    lateinit var cartController: CartController

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun addProductToCart() {
        // Mock data
        val productName = "cheerios"
        val quantity = 2
        val total = 4.50
        val expectedCartItems = listOf(CartItem(productName, quantity, total))

        // Mock service response
        `when`(cartService.addProductToCart(productName, quantity)).thenReturn(expectedCartItems)

        // Call the controller method
        val responseEntity = cartController.addProductToCart(productName, quantity)

        // Verify service method called
        verify(cartService).addProductToCart(productName, quantity)

        // Check response entity
        assertEquals(1, responseEntity.size)

        val cartItem = responseEntity.first()
        assertEquals(productName, cartItem.productName)
        assertEquals(quantity, cartItem.quantity)
        assertEquals(total, cartItem.total)
    }

    @Test
    fun getCartState() {
        // Mock data
        val subtotal = "1.00"
        val tax = "2.00"
        val total = "3.00"
        val expectedCartState = CartState(emptyList(), subtotal, tax, total)

        // Mock service response
        `when`(cartService.calculateCartState()).thenReturn(expectedCartState)

        // Call the controller method
        val responseEntity = cartController.getCartState()

        // Verify service method called
        verify(cartService).calculateCartState()

        // Check response entity

        assertEquals(subtotal, responseEntity.subtotal)
        assertEquals(tax, responseEntity.tax)
        assertEquals(total, responseEntity.total)
    }
}
