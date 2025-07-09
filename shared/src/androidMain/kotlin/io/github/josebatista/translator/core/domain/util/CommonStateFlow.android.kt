package io.github.josebatista.translator.core.domain.util

import kotlinx.coroutines.ExperimentalForInheritanceCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalForInheritanceCoroutinesApi::class)
actual class CommonStateFlow<T> actual constructor(flow: StateFlow<T>) : StateFlow<T> by flow
