package com.saikrishna.fetch.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saikrishna.fetch.domain.model.Item
import com.saikrishna.fetch.domain.usecase.GetItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(private val getItemsUseCase: GetItemsUseCase) : ViewModel() {

    private val itemsFlow = MutableStateFlow<Map<Int,List<Item>>>(emptyMap())
    val items : StateFlow<Map<Int,List<Item>>> = itemsFlow

    private val isLoadingFlow = MutableStateFlow(false)
    val isLoading : StateFlow<Boolean> = isLoadingFlow


    init {
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch {
            isLoadingFlow.value = true
            try {
                val listItems = getItemsUseCase()
                val groupedItems = listItems
                    .filter { !it.name.isNullOrBlank() }
                    .sortedWith(compareBy({ it.listId }, { it.name }))
                    .groupBy { it.listId }
                itemsFlow.value = groupedItems
            } catch (e : Exception) {
                Log.e("Fetch",e.toString())
                itemsFlow.value = emptyMap()
            } finally {
                isLoadingFlow.value = false
            }
        }
    }
}