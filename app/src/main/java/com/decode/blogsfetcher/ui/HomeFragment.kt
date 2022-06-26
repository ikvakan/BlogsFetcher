package com.decode.blogsfetcher.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.decode.blogsfetcher.R
import com.decode.blogsfetcher.databinding.FragmentHomeBinding
import com.decode.blogsfetcher.repository.PostDataFetcher
import com.decode.blogsfetcher.utils.Constants
import com.decode.blogsfetcher.viewModels.ViewModelResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: ViewModelResponse by viewModels()
    private lateinit var postDataFetcher: PostDataFetcher


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFetchBlogs.setOnClickListener {
            viewModel.savePostsToDatabase(Constants.BLOG_IDENTIFIER,Constants.LIMIT)
            findNavController().navigate(R.id.action_homeFragment_to_postsFragment)
        }
    }
}