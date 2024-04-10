package com.equal.shopping.cart.service

import com.equal.shopping.cart.models.entities.CartItem
import com.equal.shopping.cart.models.entities.CartState
import com.equal.shopping.cart.models.entities.Product
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.text.DecimalFormat

class CartServiceImplTest {

    @Mock
    lateinit var productServiceImpl: ProductServiceImpl

    @InjectMocks
    lateinit var cartService: CartServiceImpl

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun addProductToCart() {
        // Mock data
        val productName = "cheerios"
        val quantity = 2
        val product = Product("cheerios", 2.5)
        val cartItem = CartItem("cheerios", 2, 5.0)

        // Mock service response
        `when`(productServiceImpl.getProduct(productName)).thenReturn(product)

        // Call the service method
        cartService.addProductToCart(productName, quantity)

        // Verify the cart state
        val cartItems = cartService.getAllCartItems()
        assertEquals(1, cartItems.size)
        assertEquals(cartItem, cartItems[0])
    }

    @Test
    fun getAllCartItems() {
        // Mock data
        val productName = "cheerios"
        val quantity = 2
        val product = Product(productName, 2.5)
        val cartItems = listOf(CartItem(productName, quantity, 5.0))

        // Mock service response
        `when`(productServiceImpl.getProduct(productName)).thenReturn(product)

        // Call the service method
        cartService.addProductToCart(productName, quantity)

        // Call the getAllCartItems method
        val result = cartService.getAllCartItems()

        // Verify result
        assertEquals(cartItems, result)
    }

    @Test
    fun calculateCartState() {
        // Mock data
        val productName = "cheerios"
        val quantity = 2
        val product = Product(productName, 2.5)
        val cartItems = listOf(CartItem(productName, quantity, 5.0))

        // Mock service response
        `when`(productServiceImpl.getProduct(productName)).thenReturn(product)

        // Set cart items in service
        cartService.addProductToCart(productName, quantity)

        // Call the service method
        val result = cartService.calculateCartState()

        // Verify result
        val subtotal = cartItems.sumOf { it.total }
        val tax = (subtotal * 0.125).roundToTwoDecimalPlaces()
        val total = (subtotal + tax).roundToTwoDecimalPlaces()
        val decimalFormat = DecimalFormat("0.00")
        val expectedCartState = CartState(cartItems, decimalFormat.format(subtotal), decimalFormat.format(tax), decimalFormat.format(total))

        assertEquals(expectedCartState, result)
    }

    private fun Double.roundToTwoDecimalPlaces(): Double {
        val decimalFormat = DecimalFormat("0.00")
        return decimalFormat.format(this).toDouble()
    }
}
