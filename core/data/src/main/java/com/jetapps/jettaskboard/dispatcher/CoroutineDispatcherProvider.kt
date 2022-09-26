package com.jetapps.jettaskboard.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfirmed: CoroutineDispatcher
}
