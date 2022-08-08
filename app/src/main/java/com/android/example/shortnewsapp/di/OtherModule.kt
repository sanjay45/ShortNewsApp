package com.android.example.shortnewsapp.di

import com.android.example.shortnewsapp.utils.dispatcher.DispatcherProvider
import com.android.example.shortnewsapp.utils.dispatcher.DispatcherProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object OtherModule {

    @Provides
    fun provideDispatchProvider(): DispatcherProvider = DispatcherProviderImpl()
}