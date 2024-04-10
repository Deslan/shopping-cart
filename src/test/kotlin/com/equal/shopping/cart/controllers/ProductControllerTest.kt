package com.equal.shopping.cart.controllers

import com.equal.shopping.cart.models.entities.Product
import com.equal.shopping.cart.service.ProductService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.http.HttpStatus

class ProductControllerTest {

    @Mock
    lateinit var productService: ProductService

    @InjectMocks
    lateinit var productController: ProductController

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getProduct() {
        // Mock data
        val productName = "cheerios"
        val price = 2.5
        val expectedProduct = Product(productName, price)

        // Mock service response
        `when`(productService.getProduct(productName)).thenReturn(expectedProduct)

        // Call the controller method
        val responseEntity = productController.getProduct(productName)

        // Verify service method called
        verify(productService).getProduct(productName)

        // Check response entity
        assertEquals(productName, responseEntity.title)
        assertEquals(price, responseEntity.price)
    }
}
