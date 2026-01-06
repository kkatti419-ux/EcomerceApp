package com.example.apiintegration.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DummyRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GeminiRetrofit
