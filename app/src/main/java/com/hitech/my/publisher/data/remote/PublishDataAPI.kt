package com.hitech.my.publisher.data.remote

import com.hitech.my.publisher.data.dto.ArticleDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PublishDataAPI {

    @GET("mostpopular/v2/viewed/{range}.json?api-key=HaHg7rdRMp1kVpU3GJmdIviaZa3Udk2f")
    suspend fun getArticles(@Path("range") range: Int) : ArticleDto
}