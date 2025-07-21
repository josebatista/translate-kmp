package io.github.josebatista.translator.testing

import io.github.josebatista.translator.core.domain.util.CommonFlow
import io.github.josebatista.translator.core.domain.util.toCommonFlow
import io.github.josebatista.translator.translate.domain.history.HistoryDataSource
import io.github.josebatista.translator.translate.domain.history.HistoryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.coroutines.CoroutineContext

class FakeHistoryDataSource : HistoryDataSource {

    private val _data = MutableStateFlow<List<HistoryItem>>(emptyList())

    override fun getHistory(context: CoroutineContext): CommonFlow<List<HistoryItem>> {
        return _data.toCommonFlow()
    }

    override suspend fun insertHistoryItem(item: HistoryItem) {
        _data.value += item
    }
}
