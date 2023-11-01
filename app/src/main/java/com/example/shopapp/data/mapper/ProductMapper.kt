package com.example.shopapp.data.mapper

import com.example.shopapp.data.remote.dto.ProductDto
import com.example.shopapp.data.remote.dto.RatingDto
import com.example.shopapp.domain.model.Product
import com.example.shopapp.domain.model.Rating

fun ProductDto.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        category = category,
        image = image,
        rating = rating.toRating()
    )
}

fun RatingDto.toRating(): Rating {
    return Rating(
        rate, count
    )
}