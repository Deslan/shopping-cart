package com.equal.shopping.cart.service

import com.equal.shopping.cart.models.entities.CartItem
import com.equal.shopping.cart.models.entities.CartState
import org.springframework.stereotype.Service
import java.text.DecimalFormat

@Service
class CartServiceImpl(
    private val productServiceImpl: ProductServiceImpl
) : CartService{

    private val cartItems: MutableList<CartItem> = mutableListOf()

    override fun addProductToCart(productName: String, quantity: Int): List<CartItem> {
        val product = productServiceImpl.getProduct(productName)

        val total = product.price * quantity
        val roundedTotal = total.roundToTwoDecimalPlaces()

        // Check if an item with the same name already exists in the cart
        val existingCartItem = cartItems.find { it.productName == productName }
        if (existingCartItem != null) {
            // If the item already exists, update the quantity and total
            existingCartItem.quantity += quantity
            existingCartItem.total += roundedTotal
        } else {
            // Otherwise, add a new cart item
            cartItems.add(CartItem(productName, quantity, roundedTotal))
        }

        // Return the updated list of cart items
        return getAllCartItems()
    }

    override fun getAllCartItems(): List<CartItem> {
        return cartItems.toList()
    }

    override fun calculateCartState(): CartState {
        val subtotal = cartItems.sumOf { it.total }

        val tax = (subtotal * 0.125).roundToTwoDecimalPlaces()
        val total = (subtotal + tax)

        val decimalFormat = DecimalFormat("0.00")

        return CartState(cartItems.toList(), decimalFormat.format(subtotal), decimalFormat.format(tax), decimalFormat.format(total))
    }

    private fun Double.roundToTwoDecimalPlaces(): Double {
        val decimalFormat = DecimalFormat("0.00")
        return decimalFormat.format(this).toDouble()
    }

}