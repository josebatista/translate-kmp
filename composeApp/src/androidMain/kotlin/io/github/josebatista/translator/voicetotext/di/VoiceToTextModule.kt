package io.github.josebatista.translator.voicetotext.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.josebatista.translator.voicetotext.data.AndroidVoiceToTextParser
import io.github.josebatista.translator.voicetotext.domain.VoiceToTextParser

@Module
@InstallIn(ViewModelComponent::class)
object VoiceToTextModule {

    @Provides
    fun provideVoiceToTextParser(app: Application): VoiceToTextParser {
        return AndroidVoiceToTextParser(app)
    }
}
