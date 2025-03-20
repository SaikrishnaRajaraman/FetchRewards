package com.saikrishna.fetch.domain.usecase

import com.saikrishna.fetch.domain.model.Item
import com.saikrishna.fetch.domain.repository.ItemRepository
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val repository: ItemRepository
) {

    suspend operator fun invoke() : List<Item> {
        return repository.getItems()
    }
}