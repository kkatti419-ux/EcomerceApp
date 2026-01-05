package com.example.apiintegration.di

import com.example.apiintegration.data.repository.PostRepositoryImpl
import com.example.apiintegration.domain.repository.PostRepository
import com.example.apiintegration.data.repository.AuthRepositoryImpl
import com.example.apiintegration.data.repository.CartRepositoryImpl
import com.example.apiintegration.domain.repository.AuthRepository
import com.example.apiintegration.data.repository.GeminiRepositoryImpl
import com.example.apiintegration.data.repository.LocalPostRepositoryImpl
import com.example.apiintegration.data.repository.LocalUserRepositoryImpl
import com.example.apiintegration.domain.repository.GeminiRepository
import com.example.apiintegration.domain.repository.ProductRepository
import com.example.apiintegration.data.repository.ProductRepositoryImpl
import com.example.apiintegration.data.repository.UserDetailsRepositoryImpl
import com.example.apiintegration.domain.repository.Cartrepository
import com.example.apiintegration.domain.repository.UserDetailReposirotory
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

    @Binds
    @Singleton
    abstract fun bindLocalUserRepository(
        localUserRepositoryImpl: LocalUserRepositoryImpl
    ): com.example.apiintegration.domain.repository.LocalUserRepository

    @Binds
    @Singleton
    abstract fun bindLocalPostRepository(
        localPostRepositoryImpl: LocalPostRepositoryImpl
    ): com.example.apiintegration.domain.repository.LocalPostRepository

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindUserDetailsRepository(
        userDetailsRepositoryImpl: UserDetailsRepositoryImpl
    ): UserDetailReposirotory

    @Binds
    @Singleton
    abstract fun bindCartRepository(
        cartRepositoryImpl: CartRepositoryImpl
    ): Cartrepository


}


/*
*
🧠 Why this is required (simple)
When you write this in ViewModel:

kotlin
Copy code
@Inject
lateinit var productRepository: ProductRepository
*
*
*
* Hilt asks:

“Which class should I create for ProductRepository?”

Your @Binds tells Hilt:

ProductRepository → ProductRepositoryImpl


Without it ❌ → Hilt crash
*
*
*
*
*
* ❌ Common mistakes (avoid)

Forgetting to bind new repository

Binding wrong interface

Missing @Inject constructor in Impl

Mixing @Provides and @Binds unnecessarily

🧠 One-line memory

New repository = new @Binds entry

🎯 Final answer to your question

What do I need to do here?

✅ Add a @Binds method for ProductRepositoryImpl
✅ Make sure Impl has @Inject constructor


* */