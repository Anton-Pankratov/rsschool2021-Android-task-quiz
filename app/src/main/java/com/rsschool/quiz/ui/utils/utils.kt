package com.rsschool.quiz.ui.utils

import android.content.Context
import android.graphics.drawable.TransitionDrawable
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import com.rsschool.quiz.R
import kotlin.math.roundToInt

const val NEXT = "next_fragment"
const val PREVIOUS = "previous_fragment"
const val SUBMIT = "submit_answers"
const val ANIMATION_DURATION = 360

fun Context.getStringResource(@StringRes resId: Int) =
    this.resources.getString(resId)

fun Context.toDp(value: Int): Int {
    return (value * resources.displayMetrics.density + 0.5f).roundToInt()
}

fun View.setNewBackground(@ColorRes previousColor: Int, @ColorRes color: Int) {
    background = TransitionDrawable(
        arrayOf(
            ContextCompat.getColor(context, previousColor).toDrawable(),
            ContextCompat.getColor(context, color).toDrawable()
        )
    ).apply {
        startTransition(ANIMATION_DURATION)
    }
}

fun View.setAlphaAnimation() =
    startAnimation(AlphaAnimation(0f, 1f)
        .apply {
            duration = ANIMATION_DURATION.toLong()
            startOffset = ANIMATION_DURATION.toLong()
            fillAfter = true
        })

fun Int.provideTheme(): Int {
    return when (this) {
        1 -> Themes.ORANGE.resId
        2 -> Themes.TURQUOISE.resId
        3 -> Themes.GREEN.resId
        4 -> Themes.CYAN.resId
        5 -> Themes.PURPLE.resId
        else -> Themes.DEFAULT.resId
    }
}

enum class Themes(@StyleRes val resId: Int) {
    DEFAULT(R.style.Theme_Quiz),
    ORANGE(R.style.Theme_Quiz_First),
    TURQUOISE(R.style.Theme_Quiz_Second),
    GREEN(R.style.Theme_Quiz_First),
    CYAN(R.style.Theme_Quiz_Fourth),
    PURPLE(R.style.Theme_Quiz_Fifth)
}

enum class Action {
    NEXT, PREVIOUS, SHARE, REPEAT, EXIT
}