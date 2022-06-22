package com.example.devnologyteste.data

import com.example.devnologyteste.R
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ItemRepository private constructor() {
    companion object {
        @Volatile
        private var INSTANCE: ItemRepository? = null
        fun getInstance() = INSTANCE ?: synchronized(this) {
            INSTANCE ?: ItemRepository().also {
                INSTANCE = it
            }
        }

        const val TAG = "ItemRepository"
    }

    private val allItems: List<Item> = listOf(
        Item(R.drawable.item_1, "Lenovo - IdeaPad L340 15 Gaming", 717.80),
        Item(R.drawable.item_2, "Lenovo 15.6\" ThinkPad P15s Gen 1", 1519.00),
        Item(R.drawable.item_3, "Notebook Lenovo 2 em 1", 4699.00),
    )
    private val fullDescriptionItems: List<DetailItem> = listOf(
        DetailItem("Lenovo - IdeaPad L340 15 Gaming, Intel Core i7-10510U Quad-Core, 16GB DDR4 RAM, 512GB SSD, NVIDIA Quadro P520, Windows 10 Pro (20T4001VUS)",
            listOf(R.drawable.item_1), 717.80, "20 GHz Intel Core i7-10510U Quad-Core Processor\n" +
                    "16GB of DDR4 RAM | 512GB SSD\n" +
                    "15.6\" 1920 x 1080 IPS Display\n" +
                    "NVIDIA Quadro P520\n" +
                    "Windows 10 Pro 64-Bit Edition\n" +
                    "1.8 GHz Intel Core i7-10510U Quad-Core Processor\n" +
                    "16GB of DDR4 RAM | 512GB SSD\n" +
                    "15.6\" 1920 x 1080 IPS Display\n" +
                    "NVIDIA Quadro P520"),
        DetailItem("Lenovo 15.6\" ThinkPad P15s Gen 1 Laptop, Intel Core i7-10510U Quad-Core, 16GB DDR4 RAM, 512GB SSD, NVIDIA Quadro P520, Windows 10 Pro (20T4001VUS)",
            listOf(R.drawable.item_2), 1519.00, "1.8 GHz Intel Core i7-10510U Quad-Core Processor\n" +
                    "16GB of DDR4 RAM | 512GB SSD\n" +
                    "15.6\" 1920 x 1080 IPS Display\n" +
                    "NVIDIA Quadro P520\n" +
                    "Windows 10 Pro 64-Bit Edition\n" +
                    "1.8 GHz Intel Core i7-10510U Quad-Core Processor\n" +
                    "16GB of DDR4 RAM | 512GB SSD\n" +
                    "15.6\" 1920 x 1080 IPS Display\n" +
                    "NVIDIA Quadro P520"),
        DetailItem("Notebook Lenovo 2 em 1, Intel Core i7-10510U Quad-Core, 16GB DDR4 RAM, 512GB SSD, NVIDIA Quadro P520, Windows 10 Pro (20T4001VUS)",
            listOf(R.drawable.item_3), 4699.00, "30 GHz Intel Core i7-10510U Quad-Core Processor\n" +
                    "16GB of DDR4 RAM | 512GB SSD\n" +
                    "15.6\" 1920 x 1080 IPS Display\n" +
                    "NVIDIA Quadro P520\n" +
                    "Windows 10 Pro 64-Bit Edition\n" +
                    "1.8 GHz Intel Core i7-10510U Quad-Core Processor\n" +
                    "16GB of DDR4 RAM | 512GB SSD\n" +
                    "15.6\" 1920 x 1080 IPS Display\n" +
                    "NVIDIA Quadro P520"),
    )

    suspend fun getAllItems(): List<Item> = suspendCoroutine {
        it.resume(allItems)
    }
    fun getItem(index: Int): Item  {
        return allItems[index]
    }
    suspend fun getDetailItem(index: Int): DetailItem = suspendCoroutine {
        it.resume(fullDescriptionItems[index])
    }
}