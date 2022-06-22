package com.example.devnologyteste.ui.checkout

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.devnologyteste.BottomNavigationActivity
import com.example.devnologyteste.MobileNavigationDirections
import com.example.devnologyteste.R
import com.example.devnologyteste.databinding.FragmentCheckoutBinding
import com.example.devnologyteste.databinding.FragmentDetailBinding
import com.example.devnologyteste.databinding.FragmentHomeBinding
import com.example.devnologyteste.ui.home.HomeViewModel

class CheckoutFragment : Fragment() {

    private var _binding: FragmentCheckoutBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: CheckoutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val checkoutViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)

        binding.ordersBtnDetail.setOnClickListener {
            val action = MobileNavigationDirections.actionGlobalNavigationCart()
            it.findNavController().navigate(action)
        }
        val activity = activity as BottomNavigationActivity
        activity.setSupportActionBar(binding.toolbarCheckout)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}