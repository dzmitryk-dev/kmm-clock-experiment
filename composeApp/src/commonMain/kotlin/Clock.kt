import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.datetime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Duration.Companion.seconds

@Preview
@Composable
fun Clock() {
    val date = remember { getFormatedDate() }
    val time = remember { mutableStateOf(getTime()) }

    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
        }
        Column {
            Text(date)
            time.value.let { time ->
                Text("${time.hour}:${time.minute}:${time.second}")
            }
        }
    }

    LaunchedEffect(time.value) {
        delay(1.seconds)
        time.value = getTime()
    }
}

private fun getTime(): LocalTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).time