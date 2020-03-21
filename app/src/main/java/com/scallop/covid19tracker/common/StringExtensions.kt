package com.scallop.covid19tracker.common

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color

@SuppressLint("ApplySharedPref")
fun String.save(applicationContext: Context, value: Map<String, Any>, clear: Boolean = false, now: Boolean = false) {
    val sp = applicationContext.getSharedPreferences(this, Context.MODE_PRIVATE).edit()
    if (clear)
        sp.clear()
    value.keys.forEach { key ->
        val v = value[key]
        if (v != null) {
            when (v) {
                is String -> sp.putString(key, v)
                is Float -> sp.putFloat(key, v)
                is Long -> sp.putLong(key, v)
                is Int -> sp.putInt(key, v)
                is Boolean -> sp.putBoolean(key, v)
            }
        }
    }
    if (now)
        sp.commit()
    else
        sp.apply()
}

fun String.load(applicationContext: Context): Map<String, Any> {
    val sp = applicationContext.getSharedPreferences(this, Context.MODE_PRIVATE)
    val keys = sp.all.keys
    val result = hashMapOf<String, Any>()
    keys.map { key ->
        val v = sp.all[key]
        if (v != null)
            result[key] = v
    }
    return result
}

val String.asColor: Int?
    get() = try {
        Color.parseColor(this)
    } catch (e: java.lang.IllegalArgumentException) {
        null
    }

