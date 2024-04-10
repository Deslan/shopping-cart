package com.equal.shopping.cart.service

interface HttpService {
    fun <T> getForObject(url: String, responseType: Class<T>): T
}
