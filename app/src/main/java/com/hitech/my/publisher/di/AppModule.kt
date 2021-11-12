package com.hitech.my.publisher.di

import com.hitech.my.publisher.common.Constants
import com.hitech.my.publisher.data.remote.PublishDataAPI
import com.hitech.my.publisher.data.repository.ArticleRepositoryImpl
import com.hitech.my.publisher.domain.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideArticleApi(): PublishDataAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PublishDataAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideArticleRepository(api: PublishDataAPI): ArticleRepository {
        return ArticleRepositoryImpl(api)
    }
}