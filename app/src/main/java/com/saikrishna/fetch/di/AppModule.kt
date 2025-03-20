package com.saikrishna.fetch.di

import com.saikrishna.fetch.data.remote.FetchApiService
import com.saikrishna.fetch.data.remote.ItemRepositoryImpl
import com.saikrishna.fetch.domain.repository.ItemRepository
import com.saikrishna.fetch.domain.usecase.GetItemsUseCase
import com.saikrishna.fetch.utils.APIConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  AppModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(APIConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : FetchApiService {
        return retrofit.create(FetchApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideItemRepository(apiService: FetchApiService) : ItemRepository {
        return ItemRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideGetItemsUseCase(repository: ItemRepository) : GetItemsUseCase {
        return GetItemsUseCase(repository)
    }


}