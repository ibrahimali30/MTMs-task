package com.ibrahim.mtms_task.base.extensions

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout


fun View.gone() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}


fun Context.setViewMargin(db: Int): Int {
    val r = getResources()
    val px = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        db.toFloat(),
        r.getDisplayMetrics()
    ).toInt()
    return px
}


fun View.setViewMargin(
    context: Context,
    left: Int = 0,
    top: Int = 0,
    right: Int = 0,
    bottom: Int = 0
){
    val params = LinearLayout.LayoutParams(
        ConstraintLayout.LayoutParams.MATCH_PARENT,
        ConstraintLayout.LayoutParams.WRAP_CONTENT
    )
    params.setMargins(
        context.setViewMargin(left),
        context.setViewMargin(top),
        context.setViewMargin(right),
        context.setViewMargin(bottom))
    this.layoutParams = params
}

