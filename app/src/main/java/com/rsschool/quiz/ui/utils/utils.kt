package com.rsschool.quiz.ui.utils

import android.content.Context
import androidx.annotation.StyleRes
import com.rsschool.quiz.R
import kotlin.math.roundToInt

const val PREPOSITION = "of"
const val RATE_CORRECT = "\u2B50"
const val RATE_INCORRECT = "\u2605"

fun Context.toDp(value: Int): Int {
    return (value * resources.displayMetrics.density + 0.5f).roundToInt()
}

fun Int.provideTheme(): Themes {
    return when (this) {
        1 -> Themes.ORANGE
        2 -> Themes.TURQUOISE
        3 -> Themes.GREEN
        4 -> Themes.CYAN
        5 -> Themes.PURPLE
        else -> Themes.DEFAULT
    }
}

enum class Themes(@StyleRes val themeId: Int) {
    DEFAULT(R.style.Theme_Quiz),
    ORANGE(R.style.Theme_Quiz_First),
    TURQUOISE(R.style.Theme_Quiz_Second),
    GREEN(R.style.Theme_Quiz_Third),
    CYAN(R.style.Theme_Quiz_Fourth),
    PURPLE(R.style.Theme_Quiz_Fifth)
}

enum class Action {
    NEXT, PREVIOUS, SHARE, REPEAT, EXIT, SUBMIT
}