package com.scallop.data.commons

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

val String.jsonObject: JSONObject?
    get() = try {
        JSONObject(this)
    } catch (e: JSONException) {
        null
    }

val String.jsonArray: JSONArray?
    get() = try {
        JSONArray(this)
    } catch (e: JSONException) {
        null
    }

fun JSONObject.getIntOrNull(name: String): Int? =
    try {
        getInt(name)
    } catch (e: JSONException) {
        val strValue = getStringOrNull(name)
        strValue?.toIntOrNull()
    }

fun JSONObject.getDoubleOrNull(name: String): Double? =
    try {
        getDouble(name)
    } catch (e: JSONException) {
        null
    }

fun JSONObject.getLongOrNull(name: String): Long? =
    try {
        getLong(name)
    } catch (e: JSONException) {
        null
    }

fun JSONObject.getStringOrNull(name: String): String? {
    try {
        val str = getString(name).trim()
        if (str == "null") {
            return null
        }
        return str
    } catch (e: JSONException) {
        return null
    }
}

fun JSONObject.getBooleanOrNull(name: String): Boolean? =
    try {
        getBoolean(name)
    } catch (e: JSONException) {
        null
    }

fun JSONObject.getObjectOrNull(name: String): JSONObject? =
    try {
        getJSONObject(name)
    } catch (e: JSONException) {
        null
    }

fun JSONObject.getArrayOrNull(name: String): JSONArray? =
    try {
        getJSONArray(name)
    } catch (e: JSONException) {
        null
    }

fun JSONObject.getArrayOrEmpty(name: String): JSONArray =
    try {
        getJSONArray(name)
    } catch (e: JSONException) {
        JSONArray()
    }