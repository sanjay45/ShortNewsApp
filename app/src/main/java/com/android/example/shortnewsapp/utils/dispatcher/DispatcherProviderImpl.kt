package com.android.example.shortnewsapp.utils.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatcherProviderImpl : DispatcherProvider() {

    override val main: CoroutineDispatcher
        get() = Dispatchers.Main

    override val background: CoroutineDispatcher
        get() = Dispatchers.IO
}