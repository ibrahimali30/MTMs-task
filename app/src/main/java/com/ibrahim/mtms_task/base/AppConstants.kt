package com.ibrahim.mtms_task.base

import android.os.Handler
import android.util.Log

const val PLACES_API_KEY = "AIzaSyAC_fz18EQHQzt-3YNop1jC0VfSW4LA_mQ"


















fun printLog(time:String) {
    Log.d("TAGTAGTAG", "printLog: $time")
}


fun delay(time:Int, function: () -> Unit) {
    Handler().postDelayed({
        function()
    },time*1000L)
}