package com.rsschool.quiz.utils

import android.content.Context
import androidx.annotation.StringRes

fun Context.getStringResource(@StringRes resId: Int) =
    this.resources.getString(resId)