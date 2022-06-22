package com.example.devnologyteste.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.devnologyteste.databinding.RowLatestBinding

class LatestAdapter: RecyclerView.Adapter<LatestAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: RowLatestBinding) : RecyclerView.ViewHolder(binding.root)

    private var latest = listOf<Int>()

    fun setLatestList(latest: List<Int>) {
        this.latest = latest
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowLatestBinding.inflate(inflater, parent, false)
        binding.root.layoutParams.width = (parent.width * 0.9).toInt()
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val late = latest[position]
        holder.binding.imageRowLatest.setImageResource(late)
    }
    override fun getItemCount() = latest.size
}