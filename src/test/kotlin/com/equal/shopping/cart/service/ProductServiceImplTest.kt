package com.equal.shopping.cart.service

import com.equal.shopping.cart.models.entities.Product
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.web.client.RestTemplate

@ExtendWith(MockitoExtension::class)
class ProductServiceImplTest {

    @Mock
    private lateinit var restTemplate: RestTemplate

    @Test
    fun `getProduct should return correct product`() {
        // Arrange
        val productName = "cheerios"
        val productPrice = 2.5
        val expectedProduct = Product(productName, productPrice)
        val baseUrl = "https://equalexperts.github.io"
        val productService = ProductServiceImpl(restTemplate, baseUrl)
        val url = "$baseUrl/backend-take-home-test-data/$productName.json"
        `when`(restTemplate.getForObject(url, Product::class.java)).thenReturn(expectedProduct)

        // Act
        val result = productService.getProduct(productName)

        // Assert
        assertEquals(expectedProduct, result)
    }

    @Test
    fun `getProduct should throw IllegalStateException when product not found`() {
        // Arrange
        val productName = "nonexistent"
        val baseUrl = "https://equalexperts.github.io"
        val productService = ProductServiceImpl(restTemplate, baseUrl)
        val url = "$baseUrl/backend-take-home-test-data/$productName.json"
        `when`(restTemplate.getForObject(url, Product::class.java)).thenReturn(null)

        // Act & Assert
        assertThrows<IllegalStateException> {
            productService.getProduct(productName)
        }
    }
}
