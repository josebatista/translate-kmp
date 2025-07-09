package io.github.josebatista.translator.core.domain.util

import kotlinx.coroutines.ExperimentalForInheritanceCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow


@OptIn(ExperimentalForInheritanceCoroutinesApi::class)
actual class CommonMutableStateFlow<T> actual constructor(
    flow: MutableStateFlow<T>
) : MutableStateFlow<T> by flow
