package com.hitech.my.publisher.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hitech.my.publisher.databinding.FragmentArticleDetailBinding

class ArticleDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentArticleDetailBinding.inflate(inflater, container, false)
        val url = arguments?.let { ArticleDetailFragmentArgs.fromBundle(it).detailUrl }
        Toast.makeText(requireContext(), url, Toast.LENGTH_SHORT).show()
        with(binding) {
            webView.settings.javaScriptEnabled = true
            url?.let { webView.loadUrl(url) }
        }
        return binding.root
    }
}