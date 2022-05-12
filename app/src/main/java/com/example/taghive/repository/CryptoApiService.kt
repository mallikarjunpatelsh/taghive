package com.example.taghive.repository

import com.example.taghive.model.CryptoModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

private const val BASE_URL =
    "https://api.wazirx.com/sapi/v1/"

/**
 * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * The Retrofit object with the Moshi converter.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CryptoApiService {

    @GET("tickers/24hr/")
    suspend fun getAllCrypto():List<CryptoModel>

    @GET
    suspend fun getSymbol(@Url symbol:String): CryptoModel
}

object CryptoApi{
    val cryptoApiService: CryptoApiService by lazy { retrofit.create(CryptoApiService::class.java) }
}