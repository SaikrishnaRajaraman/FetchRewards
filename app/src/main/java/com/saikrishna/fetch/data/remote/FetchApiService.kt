package com.saikrishna.fetch.data.remote

import com.saikrishna.fetch.data.model.ItemDto
import retrofit2.http.GET

interface FetchApiService {

    @GET("hiring.json")
    suspend fun getItems() : List<ItemDto>
}