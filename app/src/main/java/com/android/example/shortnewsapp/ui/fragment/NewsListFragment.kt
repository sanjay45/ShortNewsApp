package com.android.example.shortnewsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.android.example.shortnewsapp.R
import com.android.example.shortnewsapp.ui.adapter.NewsListAdapter
import com.android.example.shortnewsapp.databinding.FragmentNewsListBinding
import com.android.example.shortnewsapp.models.Article
import com.android.example.shortnewsapp.ui.viewmodel.NewsViewModel
import com.android.example.shortnewsapp.utils.NetworkResult
import com.android.example.shortnewsapp.utils.extension.gone
import com.android.example.shortnewsapp.utils.extension.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : BaseFragment<FragmentNewsListBinding>() {

    private val viewModel by activityViewModels<NewsViewModel>()

    private lateinit var newsListAdapter: NewsListAdapter

    override fun getLayoutBinding(inflater: LayoutInflater, container: ViewGroup?) = FragmentNewsListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setObservers()
    }

    private fun setUpRecyclerView() {
        newsListAdapter = NewsListAdapter(::navigateToNewsDetailFragment)
        binding.rvNews.adapter = newsListAdapter
    }

    private fun navigateToNewsDetailFragment(article: Article) {
        viewModel.setArticle(article)
        findNavController().navigate(R.id.action_newsListFragment_to_newsDetailFragment)
    }

    private fun setObservers() {
        viewModel.newsListLiveData.observe(viewLifecycleOwner) {
            binding.progressBar.gone()
            when (it) {
                is NetworkResult.Success -> { newsListAdapter.submitList(it.data?.toMutableList()) }
                is NetworkResult.Error -> {  Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_LONG).show() z]
                is NetworkResult.Loading -> { binding.progressBar.visible() }
                is NetworkResult.NetworkError -> {Toast.makeText(requireContext(), "no internet connection", Toast.LENGTH_LONG).show() }
            }
        }
    }


}
