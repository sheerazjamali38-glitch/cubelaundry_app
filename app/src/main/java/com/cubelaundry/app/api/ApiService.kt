package com.cubelaundry.app.api

import retrofit2.Call
import retrofit2.http.*
import com.cubelaundry.app.models.*

interface ApiService {
    @GET("api_order.php")
    @Headers("Content-Type: application/json")
    fun getRates(@Query("action") action: String = "rates"): Call<RatesResponse>

    @POST("api_order.php")
    @Headers("Content-Type: application/json")
    fun placeOrder(
        @Query("action") action: String = "place",
        @Body order: OrderRequest
    ): Call<OrderResponse>

    @GET("api_order.php")
    fun getInvoice(
        @Query("action") action: String = "invoice",
        @Query("invoice") invoice: String
    ): Call<InvoiceResponse>

    @GET("api_order.php")
    fun getHistory(
        @Query("action") action: String = "history",
        @Query("mobile") mobile: String
    ): Call<HistoryResponse>
}
