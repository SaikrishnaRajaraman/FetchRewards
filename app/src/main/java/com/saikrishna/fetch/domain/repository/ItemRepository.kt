package com.saikrishna.fetch.domain.repository

import com.saikrishna.fetch.domain.model.Item

interface ItemRepository {

    suspend fun getItems() : List<Item>
}