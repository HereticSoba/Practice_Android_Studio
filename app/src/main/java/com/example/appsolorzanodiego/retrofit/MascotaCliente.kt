package com.example.appsolorzanodiego.retrofit
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit


object MascotaCliente {
    private var retrofitCliente = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(15, TimeUnit.MINUTES)
        .writeTimeout(15, TimeUnit.MINUTES)
        //.addInterceptor(ApiInterceptor())
        .build()
    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(retrofitCliente)
        .build()

    val retrofitService: MascotaServicio by lazy {
        buildRetrofit().create(MascotaServicio::class.java)
    }
}