package io.github.josebatista.translator.translate.presentation

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import io.github.josebatista.translator.core.presentation.UiLanguage
import io.github.josebatista.translator.translate.data.local.FakeHistoryDataSource
import io.github.josebatista.translator.translate.data.remote.FakeTranslateClient
import io.github.josebatista.translator.translate.domain.history.HistoryItem
import io.github.josebatista.translator.translate.domain.translate.TranslateUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class TranslateViewModelTest {

    private lateinit var viewModel: TranslateViewModel
    private lateinit var translateClient: FakeTranslateClient
    private lateinit var dataSource: FakeHistoryDataSource

    @BeforeTest
    fun setUp() {
        translateClient = FakeTranslateClient()
        dataSource = FakeHistoryDataSource()
        val translateUseCase = TranslateUseCase(
            client = translateClient,
            historyDataSource = dataSource
        )

        viewModel = TranslateViewModel(
            translateUseCase = translateUseCase,
            historyDataSource = dataSource,
            coroutineScope = CoroutineScope(Dispatchers.Default)
        )
    }

    @Test
    fun `State and history items are properly combined`() = runBlocking {
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(TranslateState())
            val item = HistoryItem(
                id = 0,
                fromText = "from",
                toText = "to",
                fromLanguageCode = "en",
                toLanguageCode = "de"
            )
            dataSource.insertHistoryItem(item)
            val state = awaitItem()
            val expected = UiHistoryItem(
                id = item.id!!,
                fromText = item.fromText,
                toText = item.toText,
                fromLanguage = UiLanguage.byCode(item.fromLanguageCode),
                toLanguage = UiLanguage.byCode(item.toLanguageCode)
            )
            assertThat(state.history.first()).isEqualTo(expected)
        }
    }

    @Test
    fun `Translate success - state properly updated`() = runBlocking {
        viewModel.state.test {
            awaitItem()
            viewModel.onEvent(event = TranslateEvent.ChangeTranslationText("test"))
            awaitItem()
            viewModel.onEvent(event = TranslateEvent.Translate)
            val loadingState = awaitItem()
            assertThat(loadingState.isTranslating).isTrue()
            val resultState = awaitItem()
            assertThat(resultState.isTranslating).isFalse()
            assertThat(resultState.toText).isEqualTo(translateClient.translatedText)
        }
    }

}
