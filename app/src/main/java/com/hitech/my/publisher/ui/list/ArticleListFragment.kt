package com.hitech.my.publisher.ui.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hitech.my.publisher.databinding.FragmentItemListBinding
import com.hitech.my.publisher.presentation.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.recyclerview.widget.DividerItemDecoration
import com.hitech.my.publisher.R


@AndroidEntryPoint
class ArticleListFragment : Fragment() {

    private var _binding: FragmentItemListBinding? = null
    private var articleAdapter: ArticleRecyclerViewAdapter? = null
    private lateinit var viewModel: ArticleViewModel

    private val binding
     get() =  _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articleAdapter = ArticleRecyclerViewAdapter{ url ->
            findNavController().navigate(ArticleListFragmentDirections.actionItemFragmentToArticleDetail(url))
        }
        binding.apply {
            swipeContainer.setOnRefreshListener {
                viewModel.getArticles()
            }
            list.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = articleAdapter
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL
                    )
                )
            }
        }
        addObserver()
    }

    private fun addObserver() {
       viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.apply {
                swipeContainer.isRefreshing = state.isLoading
            }
            when{
                state.error.isNotEmpty() -> {
                  Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    articleAdapter?.setArticles(state.articles)
                }
            }
       }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_option, menu);
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val range = when(item.itemId) {
          R.id.menu_seven ->  7
          R.id.menu_thirty -> 30
          else -> 1
        }
        viewModel.getArticles(range)
        return true
    }
}