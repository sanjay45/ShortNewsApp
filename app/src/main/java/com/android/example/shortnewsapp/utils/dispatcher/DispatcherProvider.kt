package com.android.example.shortnewsapp.utils.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

abstract class DispatcherProvider {
    abstract val main: CoroutineDispatcher
    abstract val background: CoroutineDispatcher
}