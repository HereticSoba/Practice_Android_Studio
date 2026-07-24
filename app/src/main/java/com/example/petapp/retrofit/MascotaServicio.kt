package com.example.petapp.retrofit

import com.example.petapp.retrofit.response.ProductoResponse
import retrofit2.Response
import retrofit2.http.GET

// Interfaz que define los métodos para consumir la API
interface MascotaServicio {
    @GET("products")
    suspend fun obtenerProductos(): Response<ProductoResponse>
}