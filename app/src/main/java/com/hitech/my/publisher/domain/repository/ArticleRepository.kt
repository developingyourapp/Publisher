package com.hitech.my.publisher.domain.repository

import com.hitech.my.publisher.data.dto.ArticleDto

interface ArticleRepository {

    suspend fun getArticles(range: Int) : ArticleDto
}