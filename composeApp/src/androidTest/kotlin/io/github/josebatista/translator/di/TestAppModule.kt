package io.github.josebatista.translator.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.josebatista.translator.translate.data.local.FakeHistoryDataSource
import io.github.josebatista.translator.translate.data.remote.FakeTranslateClient
import io.github.josebatista.translator.translate.domain.history.HistoryDataSource
import io.github.josebatista.translator.translate.domain.translate.TranslateClient
import io.github.josebatista.translator.translate.domain.translate.TranslateUseCase
import io.github.josebatista.translator.voicetotext.data.FakeVoiceToTextParser
import io.github.josebatista.translator.voicetotext.domain.VoiceToTextParser
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideFakeTranslateClient(): TranslateClient = FakeTranslateClient()

    @Provides
    @Singleton
    fun provideFakeHistoryDataSource(): HistoryDataSource = FakeHistoryDataSource()

    @Provides
    @Singleton
    fun provideTranslateUseCase(
        client: TranslateClient,
        dataSource: HistoryDataSource
    ): TranslateUseCase = TranslateUseCase(client = client, historyDataSource = dataSource)

    @Provides
    @Singleton
    fun provideFakeVoiceToTextParser(): VoiceToTextParser = FakeVoiceToTextParser()

}
