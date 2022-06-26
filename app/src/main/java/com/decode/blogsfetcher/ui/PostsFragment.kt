package com.decode.blogsfetcher.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.decode.blogsfetcher.adapter.PostsRecyclerViewAdapter
import com.decode.blogsfetcher.databinding.FragmentPostsBinding
import com.decode.blogsfetcher.model.Post
import com.decode.blogsfetcher.retrofit.ApiFetcher
import com.decode.blogsfetcher.utils.Constants
import com.decode.blogsfetcher.viewModels.ViewModelResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment() {
    private lateinit var binding: FragmentPostsBinding
    private lateinit var recyclerViewAdapter: PostsRecyclerViewAdapter
    private val viewModel: ViewModelResponse by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPostsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectAllPosts()
        setupRecyclerView()

        viewModel.post.observe(viewLifecycleOwner){
            recyclerViewAdapter.setAdapter(it)
        }

    }

    private fun setupRecyclerView() {
        recyclerViewAdapter = PostsRecyclerViewAdapter(requireContext())
        binding.rvPosts.apply {
            adapter = recyclerViewAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }


}