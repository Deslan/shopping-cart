package com.equal.shopping.cart.service

import com.equal.shopping.cart.models.entities.Product
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ProductServiceImpl(
    private val restTemplate: RestTemplate,
    @Value("\${external.api.baseurl}")
    private val baseUrl: String
) : ProductService {
    override fun getProduct(productName: String): Product {
        val url = "$baseUrl/backend-take-home-test-data/$productName.json"
        return restTemplate.getForObject(url, Product::class.java)
            ?: throw IllegalStateException("Product not found")
    }
}