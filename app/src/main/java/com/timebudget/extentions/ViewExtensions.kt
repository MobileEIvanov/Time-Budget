package com.timebudget.extensions

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s?.toString() ?: "")
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

    })
}

fun TextView.updateText(text: String) {
    if (this.text.toString() != text) {
        this.text = text
    }
}

fun TextView.onDoneClicked(onDoneClicked: () -> Unit) {
    this.setOnEditorActionListener { v, actionId, event ->
        if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
            onDoneClicked.invoke()
            true
        }
        false
    }
}

fun EditText.addFilterForDigitsOnly() {
    this.filters += InputFilter { source, _, _, _, _, _ -> source?.replace(Regex("\\D"), "") ?: "" }
}

inline fun View.waitForLayout(crossinline afterLayout: () -> Unit) = with(this) {
    this.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
        afterLayout.invoke()
    }
}

inline fun View.waitForLayoutThenRemoveListener(crossinline afterLayout: (View) -> Unit) {
    addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
        override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
            afterLayout.invoke(v!!)
            this@waitForLayoutThenRemoveListener.removeOnLayoutChangeListener(this)
        }
    })
}

fun View?.getRelativeTop(): Float {
    if (this != null && parent === rootView)
        return this.y
    else if (this != null && parent is View) return y + (parent as View).getRelativeTop()

    return 0f
}

fun RecyclerView.ViewHolder.getString(resId: Int): String = itemView.resources.getString(resId)