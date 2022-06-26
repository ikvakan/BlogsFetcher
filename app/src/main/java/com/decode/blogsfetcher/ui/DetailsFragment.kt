package com.decode.blogsfetcher.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.decode.blogsfetcher.R
import com.decode.blogsfetcher.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)

        Glide.with(requireContext())
            .load(args.postTransferModel.imgUrl)
            .placeholder(R.drawable.img_not_found)
            .error(R.drawable.img_not_found)
            .into(binding.ivAvatarDetails)

        binding.tvBlogDetails.text = args.postTransferModel.blog
        binding.tvTypeDetails.text = args.postTransferModel.type
        binding.tvSummaryDetails.text = args.postTransferModel.summary

        return binding.root
    }


}