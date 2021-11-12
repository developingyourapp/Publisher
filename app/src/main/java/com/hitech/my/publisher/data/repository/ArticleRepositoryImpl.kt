package com.hitech.my.publisher.data.repository

import com.hitech.my.publisher.data.dto.ArticleDto
import com.hitech.my.publisher.data.remote.PublishDataAPI
import com.hitech.my.publisher.domain.repository.ArticleRepository
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(private val api: PublishDataAPI) : ArticleRepository{

    override suspend fun getArticles(range: Int): ArticleDto {
        return api.getArticles(range)
    }
}