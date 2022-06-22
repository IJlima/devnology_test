package com.example.devnologyteste.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devnologyteste.R
import com.example.devnologyteste.data.CartRepository
import com.example.devnologyteste.data.Item
import com.example.devnologyteste.data.ItemRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val itemRepository = ItemRepository.getInstance()

    private val _latest = MutableLiveData<List<Int>>()
    val latest: LiveData<List<Int>> = _latest

    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> = _items

    init {
        viewModelScope.launch {
            _latest.value = listOf(
                R.drawable.banner_1,
                R.drawable.banner_2
            )
            _items.value = itemRepository.getAllItems()
        }
    }
}