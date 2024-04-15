import java.text.SimpleDateFormat
import java.util.*

actual fun getFormatedDate(): String = getJvmFormatedDate()

internal fun getJvmFormatedDate(): String =
    SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault()).format(Date())
