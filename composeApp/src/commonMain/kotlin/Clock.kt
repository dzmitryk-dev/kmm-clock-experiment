import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun Clock() {
    Box(modifier = Modifier.fillMaxSize()) {
        val date = remember { Clock.System.todayIn(TimeZone.currentSystemDefault()) }
        Text(modifier = Modifier, text = "${date.dayOfMonth}-${date.monthNumber}-${date.year}")
    }

}