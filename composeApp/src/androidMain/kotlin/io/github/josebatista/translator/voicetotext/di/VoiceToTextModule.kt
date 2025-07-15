package io.github.josebatista.translator.voicetotext.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.josebatista.translator.voicetotext.data.AndroidVoiceToTextParser
import io.github.josebatista.translator.voicetotext.domain.VoiceToTextParser
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object VoiceToTextModule {

    @Provides
    @Singleton
    fun provideVoiceToTextParser(app: Application): VoiceToTextParser {
        return AndroidVoiceToTextParser(app)
    }
}
