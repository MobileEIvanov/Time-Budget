package com.timebudget.utils

import android.text.TextUtils

/**
 * Created by emil.ivanov on 9/9/18.
 */
fun isValidTextInput(input: String): Boolean {
    return !TextUtils.isEmpty(input)
}