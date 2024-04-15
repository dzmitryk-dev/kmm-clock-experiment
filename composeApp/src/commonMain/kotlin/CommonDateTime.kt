import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.todayIn

fun getKotlinxDatetimeFormatedDate(): String {
    val date = kotlinx.datetime.Clock.System.todayIn(TimeZone.currentSystemDefault())
    return date.format(LocalDate.Format {
        dayOfWeek(DayOfWeekNames.ENGLISH_FULL)
        chars(", ")
        dayOfMonth(padding = Padding.ZERO)
        char(' ')
        monthName(MonthNames.ENGLISH_FULL)
        char(' ')
        year()
    })
}