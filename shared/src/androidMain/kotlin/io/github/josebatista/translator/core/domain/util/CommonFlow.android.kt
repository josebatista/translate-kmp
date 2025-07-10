package io.github.josebatista.translator.core.domain.util

import kotlinx.coroutines.flow.Flow

actual class CommonFlow<T> actual constructor(flow: Flow<T>) : Flow<T> by flow
