package com.saikrishna.fetch.data.model

import com.saikrishna.fetch.domain.model.Item

data class ItemDto(
    val id : Int,
    val listId : Int,
    val name: String?
)

fun ItemDto.toDomain() : Item {
    return Item(id, listId, name)
}
