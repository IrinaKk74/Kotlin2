fun minutesText(minutes: Int): String {
    return when {
        minutes % 10 == 1 && minutes % 100 != 11 -> "$minutes минуту назад"
        minutes % 10 in 2..4 && minutes % 100 !in 12..14 -> "$minutes минуты назад"
        else -> "$minutes минут назад"
    }
}

fun hoursText(hours: Int): String {
    return when {
        hours % 10 == 1 && hours % 100 != 11 -> "$hours час назад"
        hours % 10 in 2..4 && hours % 100 !in 12..14 -> "$hours часа назад"
        else -> "$hours часов назад"
    }
}

fun agoToText(seconds: Int): String {
    val prefix = "был(а) "

    return prefix + when {
        seconds in 0..60 -> "только что"

        seconds in 61..3600 -> {
            val minutes = seconds / 60
            "в сети " + minutesText(minutes)
        }

        seconds in 3601..86400 -> {
            val hours = seconds / 3600
            "в сети " + hoursText(hours)
        }

        seconds in 86401..172800 -> "в сети вчера"

        seconds in 172801..259200 -> "в сети позавчера"

        else -> "в сети давно"
    }
}

fun main() {
    println(agoToText(30))
    println(agoToText(120))
    println(agoToText(3600))
    println(agoToText(7200))
    println(agoToText(90000))
    println(agoToText(200000))
    println(agoToText(300000))
}