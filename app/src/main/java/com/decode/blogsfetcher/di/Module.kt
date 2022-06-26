package com.decode.blogsfetcher.di


import android.content.Context
import androidx.room.Room
import com.decode.blogsfetcher.dao.BlogsFetcherDatabase
import com.decode.blogsfetcher.dao.PostDao
import com.decode.blogsfetcher.repository.PostDataFetcher
import com.decode.blogsfetcher.repository.Repo
import com.decode.blogsfetcher.retrofit.TumblrAPI
import com.decode.blogsfetcher.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideRetrofitInstance(): TumblrAPI = Retrofit.Builder()
        .baseUrl(Constants.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TumblrAPI::class.java)

    @Singleton
    @Provides
    fun provideRepo(tumblrAPI: TumblrAPI, postDao: PostDao): Repo {
        return Repo(tumblrAPI, postDao)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            BlogsFetcherDatabase::class.java,
            "app.db"
        )
            .build()

    @Singleton
    @Provides
    fun providePostDao(blogsFetcherDatabase: BlogsFetcherDatabase) = blogsFetcherDatabase.postDao()

    @Singleton
    @Provides
    fun providePostDataFetcher(tumblrAPI: TumblrAPI, postDao: PostDao): PostDataFetcher {
        return PostDataFetcher(tumblrAPI, postDao)
    }
}