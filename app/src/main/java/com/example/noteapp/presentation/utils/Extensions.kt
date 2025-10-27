package com.example.noteapp.presentation.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun getDateTime(ts: Long?): String {
    if (ts == null) return ""
    val sdf = SimpleDateFormat("dd/MM/yy hh:mm a")
    val netDate = Date(ts)
    return sdf.format(netDate)
}

fun showLog(tag: String, message: Any) = Log.d(tag, message.toString())

fun Context.showToast(message: Any) = Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show()

inline fun executionTime(taskName: String, block: () -> Unit ) {
    val start = System.currentTimeMillis()
    block()
    val end = System.currentTimeMillis()
    showLog("executionTime12345", "$taskName took ${end - start} ms")
}