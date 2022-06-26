package com.decode.blogsfetcher.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decode.blogsfetcher.R
import com.decode.blogsfetcher.dao.PostDaoModel
import com.decode.blogsfetcher.databinding.ItemBinding
import com.decode.blogsfetcher.model.Post
import com.decode.blogsfetcher.model.PostTransferModel
import com.decode.blogsfetcher.ui.PostsFragmentDirections



class PostsRecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<PostsRecyclerViewAdapter.PostsViewHolder>() {

    private  var _postItems: List<PostDaoModel> = arrayListOf()

    inner class PostsViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post : PostDaoModel) {
            Glide.with(context)
                .load(post.imgUrl)
                .placeholder(R.drawable.img_not_found)
                .error(R.drawable.img_not_found)
                .into(binding.ivAvatar)
            binding.ivAvatar.setTag(R.id.ivAvatar,post.imgUrl)
            binding.tvBlog.text = post.blog
            binding.tvType.text = post.type
            binding.tvSummary.text=post.summary
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(context), parent, false)
        binding.root.setOnClickListener{
            val blog=binding.tvBlog.text.toString()
            val type=binding.tvType.text.toString()
            val summary=binding.tvSummary.text.toString()
            val imageUrl=binding.ivAvatar.getTag(R.id.ivAvatar)?:""

            val post = PostTransferModel(blog,type,summary,imageUrl as String)

            val action =PostsFragmentDirections.actionPostsFragmentToDetailsFragment(post)
            parent.findNavController().navigate(action)


        }
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(_postItems[position])

    }

    override fun getItemCount(): Int = _postItems.count()


    fun setAdapter(postItems: List<PostDaoModel>) {
        _postItems = postItems
        notifyDataSetChanged()
    }
}