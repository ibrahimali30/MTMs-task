package com.ibrahim.mtms_task.base.extensions

import android.R
import android.app.Activity
import android.content.DialogInterface
import android.graphics.Color
import android.icu.text.CaseMap
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog


fun Activity.showAlertDialog(title: String, msg: String) {
    AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(msg)
            .setPositiveButton("OK", null)
            .show()
}

fun Activity.hideKeyboard() {
    val imm: InputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view: View? = currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
}


fun Activity.setActivityFullScreen() {
    val window: Window = window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.setStatusBarColor(Color.TRANSPARENT)
}
