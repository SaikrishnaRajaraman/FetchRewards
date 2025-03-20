package com.saikrishna.fetch.data.remote

import android.util.Log
import com.saikrishna.fetch.data.model.toDomain
import com.saikrishna.fetch.domain.model.Item
import com.saikrishna.fetch.domain.repository.ItemRepository
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val apiService: FetchApiService
) : ItemRepository {
    override suspend fun getItems(): List<Item> {
        val listItems = apiService.getItems()
        return listItems.map { it.toDomain() }
    }
}