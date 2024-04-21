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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.Typeface
import androidx.compose.ui.text.rememberTextMeasurer
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.time.Duration.Companion.seconds

@Preview
@Composable
fun Clock() {
    val date = remember { getFormatedDate() }
    val time = remember { mutableStateOf(getTime()) }
    val textMeasurer = rememberTextMeasurer()

    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(color = Color.Black, center = size.center, radius = (size.minDimension / 2.0f - 8))
            drawCircle(color = Color.White, center = size.center, radius = (size.minDimension / 2.0f - 10))
            drawCircle(color = Color.DarkGray, center = size.center, radius = 20f)
            repeat(60) { second ->
                rotate(degrees = second * 6f) {
                    drawLine(
                        color = Color.Black,
                        start = Offset(size.width / 2, size.height - size.minDimension + 30),
                        end = Offset(
                            size.width / 2, size.height - size.minDimension + when {
                                second % 15 == 0 -> 60
                                second % 5 == 0 -> 45
                                else -> 40
                            }
                        ),
                        strokeWidth = when {
                            second % 15 == 0 -> 4f
                            second % 5 == 0 -> 2f
                            else -> 1f
                        }
                    )
                }
            }

//            val angleStep = 2 * PI / 12
//            val radius = size.minDimension / 2.0f - 70
//            repeat(12) { i ->
//                val n = i + 1
//                val angle = angleStep * n
//                val x = center.x + radius * cos(angle - PI / 2)  // Subtract PI/2 to align number 12 to the top
//                val y = center.y + radius * sin(angle - PI / 2)
//                drawText(textMeasurer, "$n", topLeft = Offset(x.toFloat(), y.toFloat()))
//            }
            val hour = time.value.hour % 12
            val hourAngle = (hour * 60 + time.value.minute) / 2f
            rotate(hourAngle) {
                drawLine(
                    color = Color.DarkGray,
                    start = size.center,
                    end = size.center + Offset(0f, -size.minDimension / 4.0f),
                    strokeWidth = 20f
                )
            }
            rotate(time.value.minute * 6f) {
                drawLine(
                    color = Color.DarkGray,
                    start = size.center,
                    end = size.center + Offset(0f, -size.minDimension / 3.0f),
                    strokeWidth = 10f
                )
            }
            rotate(time.value.second * 6f) {
                drawLine(
                    color = Color.DarkGray,
                    start = size.center,
                    end = size.center + Offset(0f, -size.minDimension / 2.5f),
                    strokeWidth = 5f
                )
            }
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