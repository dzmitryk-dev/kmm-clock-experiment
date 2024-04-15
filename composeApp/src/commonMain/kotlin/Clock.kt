import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import kotlinx.datetime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun Clock() {
    Box(modifier = Modifier.fillMaxSize()) {
        val date = remember { Clock.System.todayIn(TimeZone.currentSystemDefault()) }
        Column {
            Text(modifier = Modifier, text = "${date.dayOfWeek}, ${date.dayOfMonth}-${date.monthNumber}-${date.year}")
            Text(date.toString())
            Text(date.format(LocalDate.Formats.ISO))
            Text(getFormatedDate())
        }

    }

}