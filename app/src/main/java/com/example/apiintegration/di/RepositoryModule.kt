package com.example.apiintegration.di

import com.example.apiintegration.data.repository.PostRepositoryImpl
import com.example.apiintegration.domain.repository.PostRepository
import com.example.apiintegration.data.repository.AuthRepositoryImpl
import com.example.apiintegration.domain.repository.AuthRepository
import com.example.apiintegration.data.repository.GeminiRepositoryImpl
import com.example.apiintegration.domain.repository.GeminiRepository
import com.example.apiintegration.presentation.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPostRepository(
        postRepositoryImpl: PostRepositoryImpl
    ): PostRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindGeminiRepository(
        geminiRepositoryImpl: GeminiRepositoryImpl
    ): GeminiRepository

//    @Binds
//    @Singleton
//    abstract fun bindCountryRepository(
//        impl: AuthRepositoryImpl
//    ): AuthRepository

}
