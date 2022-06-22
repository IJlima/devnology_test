package com.example.devnologyteste.ui.detail

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.devnologyteste.BottomNavigationActivity
import com.example.devnologyteste.MobileNavigationDirections
import com.example.devnologyteste.data.CartRepository
import com.example.devnologyteste.databinding.FragmentDetailBinding
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val args by navArgs<DetailFragmentArgs>()
    private lateinit var viewModelFactory: DetailViewModelFactory
    private val viewModel by viewModels<DetailViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        viewModelFactory = DetailViewModelFactory(args.index)
        viewModel.detailItem.observe(viewLifecycleOwner) {
            binding.descriptionDetail.text = it.fullName
            binding.priceDetail.text = "\$ ${it.price}"
            binding.aboutDetail.text = it.fullDescription
            binding.imageDetail.setImageResource(it.images[0])
        }
        binding.addBtnDetail.setOnClickListener {
            viewModel.addItemToCart(args.index)
            val bottom = activity as BottomNavigationActivity
            bottom.updateBadge()
            val action = MobileNavigationDirections.actionGlobalNavigationCart()
            it.findNavController().navigate(action)
        }
        val activity = activity as BottomNavigationActivity
        activity.setSupportActionBar(binding.toolbarDetail)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val badge = BadgeDrawable.create(context!!)
        val number = CartRepository.getInstance().getItemCount()
        if (number > 0) {
            badge.number = number
            badge.isVisible = true
        } else {
            badge.isVisible = false
        }
        binding.cartFrame.foreground = badge
        binding.cartFrame.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            BadgeUtils.attachBadgeDrawable(badge, binding.cartIcon, binding.cartFrame)
        }

        badge.badgeGravity = BadgeDrawable.BOTTOM_END
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}