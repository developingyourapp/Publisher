package com.hitech.my.publisher.presentation

import androidx.lifecycle.*
import com.hitech.my.publisher.common.Result
import com.hitech.my.publisher.domain.usecase.GetArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val articleUseCase: GetArticleUseCase
): ViewModel() {

    private val _state = MutableLiveData<ArticleState>()
    val state: LiveData<ArticleState>
     get() = _state

    init {
        getArticles()
    }

    fun getArticles(range: Int = 1) {
        articleUseCase(range).onEach{ result ->
            when (result) {
                is Result.Success -> {
                    _state.postValue(ArticleState(articles = result.data ?: emptyList()))
                }
                is Result.Error -> {
                    _state.postValue(ArticleState(
                        error = result.msg ?: "An unexpected error occurred"
                    ))
                }
                is Result.Loading -> {
                    _state.postValue(ArticleState(isLoading = true))
                }
            }
        }.launchIn(viewModelScope)
    }
}