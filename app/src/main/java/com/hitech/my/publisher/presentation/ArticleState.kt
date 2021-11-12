package com.hitech.my.publisher.presentation

import com.hitech.my.publisher.domain.model.ArticleInfo

data class ArticleState(
    val isLoading: Boolean = false,
    val articles: List<ArticleInfo> = emptyList(),
    val error: String = ""
)
