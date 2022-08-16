package com.android.example.shortnewsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.android.example.shortnewsapp.databinding.FragmentNewsDetailBinding
import com.android.example.shortnewsapp.ui.viewmodel.NewsViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>() {

    private val viewModel by activityViewModels<NewsViewModel>()

    override fun getLayoutBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentNewsDetailBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContentView()
    }

    private fun setContentView() {
        viewModel.articleLiveData.observe(viewLifecycleOwner) {
            Glide.with(this).load(it.imageUrl).into(binding.ivNews)
        }
    }

}