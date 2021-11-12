package com.hitech.my.publisher.domain.usecase

import com.hitech.my.publisher.common.Result
import com.hitech.my.publisher.data.dto.toArticleInfo
import com.hitech.my.publisher.domain.model.ArticleInfo
import com.hitech.my.publisher.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetArticleUseCase @Inject constructor(
    private val repository: ArticleRepository) {

    operator fun invoke(range: Int) : Flow<Result<List<ArticleInfo>>> = flow {
        runCatching {
            emit(Result.Loading())
            val articles = repository.getArticles(range).results.map { it.toArticleInfo() }
            emit(Result.Success(articles))
        }.onFailure {  error ->
            emit(Result.Error<List<ArticleInfo>>(error.localizedMessage ?: "unexpected error!"))
        }
    }
}