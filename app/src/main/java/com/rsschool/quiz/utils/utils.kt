package com.rsschool.quiz.utils

import android.content.Context
import android.graphics.drawable.TransitionDrawable
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import com.rsschool.quiz.R

const val ENABLED = "enabled"
const val DISABLED = "disabled"
const val NEXT = "next_fragment"
const val PREVIOUS = "previous_fragment"
const val SUBMIT = "submit_answers"
const val ANIMATION_DURATION = 360

fun Context.getStringResource(@StringRes resId: Int) =
    this.resources.getString(resId)

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
        1 -> Colors.ORANGE.resId
        2 -> Colors.TURQUOISE.resId
        3 -> Colors.GREEN.resId
        4 -> Colors.CYAN.resId
        5 -> Colors.PURPLE.resId
        else -> Colors.DEFAULT.resId
    }
}

enum class Colors(@ColorRes val resId: Int) {
    DEFAULT(R.color.yellow_100),
    ORANGE(R.color.deep_orange_100),
    TURQUOISE(R.color.turquoise_100),
    GREEN(R.color.light_green_100),
    CYAN(R.color.cyan_100),
    PURPLE(R.color.deep_purple_100)
}

