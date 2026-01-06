package com.example.apiintegration.di

import com.example.apiintegration.data.remote.api.AuthApi
import com.example.apiintegration.data.remote.api.CartApi
import com.example.apiintegration.data.remote.api.GeminiApi
import com.example.apiintegration.data.remote.api.PostApi
import com.example.apiintegration.data.remote.api.ProductApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // ---------- COMMON ----------

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        logging: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(logging).connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    // ---------- DummyJSON Retrofit ----------

    @Provides
    @Singleton
    @DummyRetrofit
    fun provideDummyRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): Retrofit {
        return Retrofit.Builder().baseUrl("https://dummyjson.com/").client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
    }

    // ---------- Gemini Retrofit ----------

    @Provides
    @Singleton
    @GeminiRetrofit
    fun provideGeminiRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): Retrofit {
        return Retrofit.Builder().baseUrl("https://generativelanguage.googleapis.com/")
            .client(okHttpClient).addConverterFactory(MoshiConverterFactory.create(moshi)).build()
    }

    // ---------- APIs ----------

    @Provides
    @Singleton
    fun provideAuthApi(
        @DummyRetrofit retrofit: Retrofit,
    ): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideGeminiApi(
        @GeminiRetrofit retrofit: Retrofit,
    ): GeminiApi = retrofit.create(GeminiApi::class.java)

    @Provides
    @Singleton
    fun provideCartApi(
        @DummyRetrofit retrofit: Retrofit,
    ): CartApi = retrofit.create(CartApi::class.java)

    @Provides
    @Singleton
    fun provideProductApi(
        @DummyRetrofit retrofit: Retrofit,
    ): ProductApi = retrofit.create(ProductApi::class.java)

    @Provides
    @Singleton
    fun providePostApi(
        @DummyRetrofit retrofit: Retrofit,
    ): PostApi = retrofit.create(PostApi::class.java)
}
