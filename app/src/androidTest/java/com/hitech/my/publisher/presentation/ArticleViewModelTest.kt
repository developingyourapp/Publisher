package com.hitech.my.publisher.presentation

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.hitech.my.publisher.data.remote.PublishDataAPI
import com.hitech.my.publisher.data.repository.ArticleRepositoryImpl
import com.hitech.my.publisher.domain.repository.ArticleRepository
import com.hitech.my.publisher.domain.usecase.GetArticleUseCase
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ArticleViewModelTest {

    private lateinit var viewModel: ArticleViewModel
    private lateinit var repository: ArticleRepository
    private val api = mockk<PublishDataAPI>()

    @Before
    fun setUp() {
        repository = ArticleRepositoryImpl(api)
        viewModel = ArticleViewModel(GetArticleUseCase(repository))
    }

    @Test
    fun testGetArticle() {
        viewModel.getArticles()
    }
}