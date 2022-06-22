package com.example.devnologyteste.ui.cart

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.devnologyteste.BottomNavigationActivity
import com.example.devnologyteste.MobileNavigationDirections
import com.example.devnologyteste.databinding.FragmentCartBinding
import com.example.devnologyteste.databinding.FragmentHomeBinding
import com.example.devnologyteste.ui.home.ItemAdapter
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val cartViewModel =
            ViewModelProvider(this).get(CartViewModel::class.java)
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val cartAdapter = CartAdapter(cartViewModel)
        binding.listCart.adapter = cartAdapter

        cartViewModel.cartItems.observe(viewLifecycleOwner) {
            cartAdapter.setCartItemList(it)
            var total = 0.0
            for (cartItem in it) {
                total += cartItem.quantity * cartItem.item.price
            }
        }

        cartViewModel.totalPrice.observe(viewLifecycleOwner) {
            binding.totalPrice.text= "\$ $it"
        }

        binding.checkoutBtnCart.setOnClickListener {
            val action = MobileNavigationDirections.actionGlobalNavigationCheckout()
            it.findNavController().navigate(action)
        }
        val bottom = activity as BottomNavigationActivity
        cartViewModel.cartCount.observe(viewLifecycleOwner) {

            bottom.updateBadge()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}