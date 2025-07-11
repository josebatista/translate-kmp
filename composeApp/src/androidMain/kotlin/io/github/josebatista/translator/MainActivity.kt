package io.github.josebatista.translator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.josebatista.translator.translate.core.presentation.Routes
import io.github.josebatista.translator.translate.core.theme.TranslatorTheme
import io.github.josebatista.translator.translate.presentation.AndroidTranslateViewModel
import io.github.josebatista.translator.translate.presentation.TranslateScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            TranslatorTheme {
                TranslateRoot()
            }
        }
    }
}

@Composable
private fun TranslateRoot() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.TRANSLATE
    ) {
        composable(route = Routes.TRANSLATE) {
            val viewModel = hiltViewModel<AndroidTranslateViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            TranslateScreen(
                state = state,
                onEvent = viewModel::onEvent
            )
        }
    }

}
