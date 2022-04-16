package com.example.juaraandroidsubmission.data.remote

import com.example.juaraandroidsubmission.data.local.Pahlawan
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://my-json-server.typicode.com/fadhlialfarisi46/"

val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
val client = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .addInterceptor{ chain ->
        val original = chain.request()
        val requesBuilder = original.newBuilder()
        val request = requesBuilder.build()
        chain.proceed(request)
    }
    .build()

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()

interface PahlawanApiService {
    @GET("free-api-hero/pahlawan")
    suspend fun pahlawans(): List<Pahlawan>
}

object PahlawanApi{
    val retrofitService: PahlawanApiService by lazy { retrofit.create(PahlawanApiService::class.java) }
}