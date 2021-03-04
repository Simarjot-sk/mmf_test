package com.simarjot.test.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.simarjot.test.databinding.FragmentFacebookDetailsBinding

class FacebookDetailsFragment : Fragment() {
    private lateinit var binding: FragmentFacebookDetailsBinding
    private val homeViewModel by activityViewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFacebookDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.faceBookUserData.observe(viewLifecycleOwner) { data ->
            if (data == null) {
                showSnackBar("could not load data")
            } else {
                binding.userData = data
                Glide.with(requireContext()).load(data.profileUrl).into(binding.image)
            }
        }
    }
}
