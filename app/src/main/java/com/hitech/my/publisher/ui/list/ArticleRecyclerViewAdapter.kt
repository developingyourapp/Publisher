package com.hitech.my.publisher.ui.list

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.hitech.my.publisher.databinding.FragmentItemBinding
import com.hitech.my.publisher.domain.model.ArticleInfo

class ArticleRecyclerViewAdapter(val callback: (String) -> Unit) : RecyclerView.Adapter<ArticleRecyclerViewAdapter.ViewHolder>() {

    private var articleList = listOf<ArticleInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setArticles(list: List<ArticleInfo>) {
        articleList = list
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(articleList[position])
    }

    override fun getItemCount(): Int = articleList.size

    inner class ViewHolder(private val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(article: ArticleInfo) {
           binding.apply {
               title.text = article.title
               date.text = article.publishedDate
               name.text = article.byline
               Glide.with(thumbnail.context).load(article.thumbnailUrl).into(thumbnail)
               binding.root.setOnClickListener {
                   callback.invoke(article.url)
               }
           }
        }
    }

}