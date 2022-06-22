package com.example.devnologyteste.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.devnologyteste.data.Item
import com.example.devnologyteste.databinding.RowItemBinding
import com.example.devnologyteste.MobileNavigationDirections

class ItemAdapter: RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: RowItemBinding) : RecyclerView.ViewHolder(binding.root)

    private var items = listOf<Item>()

    fun setItemList(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowItemBinding.inflate(inflater, parent, false)
       binding.root.layoutParams.width = (parent.width / 3) -20
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.binding.imageRowItem.setImageResource(item.image)
        holder.binding.descriptionRowItem.text = item.description
        holder.binding.priceRowItem.text = "\$ ${item.price}"
        holder.binding.cardRowItem.setOnClickListener {
            val action = MobileNavigationDirections.actionGlobalNavigationDetail(position)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = items.size
}