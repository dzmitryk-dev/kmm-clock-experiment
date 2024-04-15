import java.text.SimpleDateFormat
import java.util.*

actual fun getFormatedDate(): String =
    SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault()).format(Date())