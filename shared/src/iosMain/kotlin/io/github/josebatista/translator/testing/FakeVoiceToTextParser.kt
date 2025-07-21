package io.github.josebatista.translator.testing

import io.github.josebatista.translator.core.domain.util.CommonStateFlow
import io.github.josebatista.translator.core.domain.util.toCommonStateFlow
import io.github.josebatista.translator.voicetotext.domain.VoiceToTextParser
import io.github.josebatista.translator.voicetotext.domain.VoiceToTextParserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class FakeVoiceToTextParser : VoiceToTextParser {
    private val _state = MutableStateFlow(VoiceToTextParserState())

    var voiceResult = "text result"

    override val state: CommonStateFlow<VoiceToTextParserState>
        get() = _state.toCommonStateFlow()

    override fun startListening(languageCode: String) {
        _state.update {
            it.copy(
                result = "",
                isSpeaking = true
            )
        }
    }

    override fun stopListening() {
        _state.update {
            it.copy(
                result = voiceResult,
                isSpeaking = false
            )
        }
    }

    override fun cancel() = Unit

    override fun reset() {
        _state.update { VoiceToTextParserState() }
    }
}
