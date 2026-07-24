package com.example.petapp.retrofit.response

data class ProductoResponse(
    val products: List<Producto>
)

data class Producto(
    val title: String,
    val price: Double,
    val category: String,
    val thumbnail: String
)
