package com.example.devnologyteste.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devnologyteste.R
import com.example.devnologyteste.data.*
import kotlinx.coroutines.launch

class DetailViewModel(val index: Int) : ViewModel() {
    private val itemRepository = ItemRepository.getInstance()
    private val cartRepository = CartRepository.getInstance()

    private val _detailItem = MutableLiveData<DetailItem>()
    val detailItem: LiveData<DetailItem> = _detailItem

    init {
        viewModelScope.launch {
            _detailItem.value = itemRepository.getDetailItem(index)
        }
    }
    fun addItemToCart(index: Int) {
        val item = itemRepository.getItem(index)
        cartRepository.addItemToCart(item)
    }
}