package com.example.composepicsartsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.*
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.clip
import androidx.ui.core.setContent
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.example.composepicsartsample.data.DataRepository
import com.example.composepicsartsample.ui.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataRepository = DataRepository(application)
        setContent {
            var showSnackBar by state { false }
            ComposePicsartSampleTheme {
                Scaffold(topBar = {
                    ChallengesAppBar(onClick = { showSnackBar = true })
                }) { innerPadding ->

                    val uiState = getUiStateFrom(dataRepository::getChallengeResponse)

                    if (uiState is UiState.Loading) {
                        LoadingHomeScreen()
                    } else if (uiState is UiState.Success) {
                        val modifier = Modifier.padding(innerPadding)
                        Stack(modifier = Modifier.fillMaxWidth()) {
                            ChallengeScreen(
                                data = (uiState as UiState.Success).data,
                                modifier = modifier
                            )
                            if (showSnackBar) {
                                showSnackBar {
                                    showSnackBar = false
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun getUiStateFrom(call: () -> List<Any>): UiState {
    var state by state<UiState> { UiState.Loading }
    launchInComposition {
        val result = withContext(Dispatchers.IO) {
            delay(1000)
            call()
        }
        state = UiState.Success(result)
    }
    return state
}

@Composable
fun ChallengesAppBar(onClick: () -> Unit) {
    TopAppBar(title = {
        Text("Challenges")
    }, backgroundColor = MaterialTheme.colors.surface, actions = {
        IconButton(onClick = onClick) {
            Icon(vectorResource(R.drawable.ic_android_black_24dp))
        }
    })
}

@Composable
fun StackScope.showSnackBar(onDismiss: () -> Unit) {
    Snackbar(
        text = { Text(text = "Show snackbar") },
        modifier = Modifier.gravity(Alignment.BottomCenter).padding(16.dp),
        action = {
            TextButton(onClick = onDismiss) {
                Text("DISMISS", color = Color.Yellow)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePicsartSampleTheme {
        PicassoImage(
            url = "https://i.pinimg.com/originals/36/b0/a5/36b0a523a62515232cc21d77e604cfc3.jpg",
            modifier = Modifier.fillMaxWidth().aspectRatio(1f).padding(16.dp).clip(
                RoundedCornerShape(4.dp)
            ),
            placeholder = Color.LightGray
        )
    }
}