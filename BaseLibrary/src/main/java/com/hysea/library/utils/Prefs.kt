package com.hysea.library.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.hysea.library.base.appContext

object Prefs {
    val prefs: SharedPreferences =
        appContext.getSharedPreferences("config", Context.MODE_PRIVATE)

    val gson = Gson()

//    fun save(key: String, any: Any) {
//        prefs.edit {
//            when (any) {
//                is String -> putString(key, any)
//                is Int -> putInt(key, any)
//                is Long -> putLong(key, any)
//                is Float -> putFloat(key, any)
//                else -> save(key, gson.toJson(any))
//            }
//        }
//    }

    fun save(key: String, value: Any) = prefs.edit {
        when (value) {
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            is Boolean -> putBoolean(key, value)
            is String -> putString(key, value)
            else -> putString(key, gson.toJson(value))
        }
    }

    inline fun <reified T> get(key: String, default: Any): T = with(prefs) {
        return@with when (default) {
            is Int -> getInt(key, default)
            is Long -> getLong(key, default)
            is Float -> getFloat(key, default)
            is Boolean -> getBoolean(key, default)
            is String -> getString(key, default)
            else -> getString(key, "").run {
                gson.fromJson(this, T::class.java)
            }
        } as T
    }

//    inline fun <reified T> get(key: String): T {
//        prefs.run {
//            val d = when (T::class) {
//                Int::class -> getInt(key, 0)
//                Long::class -> getLong(key, 0)
//                Float::class -> getFloat(key, 0f)
//                String::class -> getString(key, "")
//                else -> {
//                    getString(key, "").run {
//                        gson.fromJson(this, T::class.java)
//                    }
//                }
//            }
//            return d as T
//        }
//    }
}