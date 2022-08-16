package com.android.example.shortnewsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.example.shortnewsapp.databinding.LayoutNewsItemBinding
import com.android.example.shortnewsapp.models.Article
import com.bumptech.glide.Glide

class NewsListAdapter(private val onClickListener: (Article) -> Unit): ListAdapter<Article, RecyclerView.ViewHolder>(NEWS_ARTICLE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = LayoutNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsListViewHolder).bind(getItem(position))
    }

    inner class NewsListViewHolder(private val binding: LayoutNewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClickListener(getItem(adapterPosition))
            }
        }

        fun bind(article: Article) {
            binding.tvTitle.text = article.title
            Glide.with(binding.root).load(article.imageUrl).into(binding.ivArticleImage)

        }
    }

    companion object {
        private val NEWS_ARTICLE_COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem.imageUrl == newItem.imageUrl
            override fun areContentsTheSame(oldItem: Article, newItem: Article) = oldItem == newItem
        }
    }
}