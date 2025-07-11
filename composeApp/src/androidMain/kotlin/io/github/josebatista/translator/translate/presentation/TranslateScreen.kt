package io.github.josebatista.translator.translate.presentation

import android.content.ClipData
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import io.github.josebatista.translator.R
import io.github.josebatista.translator.translate.presentation.components.LanguageDropDown
import io.github.josebatista.translator.translate.presentation.components.SwapLanguagesButton
import io.github.josebatista.translator.translate.presentation.components.TranslateTextField
import kotlinx.coroutines.launch

@Composable
fun TranslateScreen(
    state: TranslateState,
    onEvent: (TranslateEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Scaffold(
        floatingActionButton = {}
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    LanguageDropDown(
                        language = state.fromLanguage,
                        isOpen = state.isChoosingFromLanguage,
                        onClick = {
                            onEvent(TranslateEvent.OpenFromLanguageDropDown)
                        },
                        onDismiss = {
                            onEvent(TranslateEvent.StopChoosingLanguage)
                        },
                        onSelectLanguage = {
                            onEvent(TranslateEvent.ChooseFromLanguage(it))
                        }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    SwapLanguagesButton(
                        onClick = { onEvent(TranslateEvent.SwapLanguages) }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    LanguageDropDown(
                        language = state.toLanguage,
                        isOpen = state.isChoosingToLanguage,
                        onClick = {
                            onEvent(TranslateEvent.OpenToLanguageDropDown)
                        },
                        onDismiss = {
                            onEvent(TranslateEvent.StopChoosingLanguage)
                        },
                        onSelectLanguage = {
                            onEvent(TranslateEvent.ChooseToLanguage(it))
                        }
                    )
                }
            }
            item {
                val clipboardManager = LocalClipboard.current
                val keyboardController = LocalSoftwareKeyboardController.current
                val scope = rememberCoroutineScope()
                TranslateTextField(
                    fromText = state.fromText,
                    toText = state.toText,
                    isTranslating = state.isTranslating,
                    fromLanguage = state.fromLanguage,
                    toLanguage = state.toLanguage,
                    onTranslateClick = {
                        keyboardController?.hide()
                        onEvent(TranslateEvent.Translate)
                    },
                    onTextChange = { onEvent(TranslateEvent.ChangeTranslationText(it)) },
                    onCopyClick = { text ->
                        scope.launch {
                            val clipEntry = ClipEntry(ClipData.newPlainText(text, text))
                            clipboardManager.setClipEntry(clipEntry)
                        }
                        Toast.makeText(
                            context,
                            context.getString(R.string.copied_to_clipboard),
                            Toast.LENGTH_LONG
                        ).show()
                    },
                    onCloseClick = { onEvent(TranslateEvent.CloseTranslation) },
                    onSpeakerClick = { },
                    onTextFieldClick = { onEvent(TranslateEvent.EditTranslation) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
