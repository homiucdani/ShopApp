package com.example.shopapp.domain.services

interface ShopNotification {

    fun addToCartNotification(productName: String, message: String)
}