package ir.alirahimi.weatherapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.aakira.napier.Napier
import ir.alirahimi.weatherapp.Greeting
import ir.alirahimi.weatherapp.doSomething
import ir.alirahimi.weatherapp.usecase.GetWeatherForecastsUseCase
import ir.alirahimi.weatherapp.viewmodel.ForecastsViewModel
import ir.alirahimi.weatherapp.viewmodel.UIState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val viewModel: ForecastsViewModel by viewModels()
                    val uiState by viewModel.uiState.collectAsState()

                    if (uiState.loading){
                        Napier.d("loading", tag = "2323")
                    }
                    if (uiState.failed != null){
                        Napier.d("failed", tag = "2323")
                    }
                    uiState.items.forEach {
                        Napier.d("items $it", tag = "2323")
                    }

                    GreetingView(Greeting().greet())
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
