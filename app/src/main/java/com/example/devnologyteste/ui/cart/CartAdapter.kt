package com.example.devnologyteste.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.devnologyteste.data.CartItem
import com.example.devnologyteste.databinding.RowCartBinding

class CartAdapter(val cartViewModel: CartViewModel): RecyclerView.Adapter<CartAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: RowCartBinding) : RecyclerView.ViewHolder(binding.root)

    private var cartItems = mutableListOf<CartItem>()

    fun setCartItemList(cartItems: List<CartItem>) {
        this.cartItems = cartItems.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowCartBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.binding.imageRowCart.setImageResource(cartItem.item.image)
        holder.binding.descriptionRowCart.text = cartItem.item.description
        holder.binding.priceRowCart.text = "\$ ${cartItem.item.price * cartItem.quantity}"
        holder.binding.quantityRowCart.text = cartItem.quantity.toString()

        holder.binding.removeBtn.setOnClickListener {
            if(cartItem.quantity > 1) {
                cartItem.quantity -= 1
                notifyItemChanged(position)
            } else {
                cartItems.removeAt(position)
                notifyItemRemoved(position)
                cartViewModel.removeItemFromCart(cartItem)
            }
            cartViewModel.calculateTotal(cartItems)
        }

        holder.binding.addBtn.setOnClickListener {
                cartItem.quantity += 1
                notifyItemChanged(position)
            cartViewModel.calculateTotal(cartItems)
        }
    }

    override fun getItemCount() = cartItems.size
}