package io.github.josebatista.translator.translate.data.history

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import io.github.josebatista.translator.core.domain.util.CommonFlow
import io.github.josebatista.translator.core.domain.util.toCommonFlow
import io.github.josebatista.translator.database.TranslateDatabase
import io.github.josebatista.translator.translate.domain.history.HistoryDataSource
import io.github.josebatista.translator.translate.domain.history.HistoryItem
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class SqlDelightHistoryDataSource(
    db: TranslateDatabase
) : HistoryDataSource {
    private val queries = db.translateQueries
    override fun getHistory(context: CoroutineContext): CommonFlow<List<HistoryItem>> {
        return queries
            .getHistoryEntities()
            .asFlow()
            .mapToList(context)
            .map { historyEntities ->
                historyEntities.map { it.toHistoryItem() }
            }
            .toCommonFlow()
    }

    @OptIn(ExperimentalTime::class)
    override suspend fun insertHistoryItem(item: HistoryItem) {
        queries.insertHistoryEntity(
            id = item.id,
            fromLanguageCode = item.fromLanguageCode,
            fromText = item.fromText,
            toLanguageCode = item.toLanguageCode,
            toText = item.toText,
            timestamp = Clock.System.now().toEpochMilliseconds()
        )
    }
}
