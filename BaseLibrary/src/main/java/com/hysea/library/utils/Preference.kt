package com.hysea.library.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.hysea.library.base.BaseApp

object Preference {
    val sharedPreferences: SharedPreferences =
        BaseApp.instance.getSharedPreferences("global", Context.MODE_PRIVATE)
    val gson = Gson()

    fun save(key: String, any: Any) {
        sharedPreferences.edit {
            when (any) {
                is String -> putString(key, any)
                is Int -> putInt(key, any)
                is Long -> putLong(key, any)
                is Float -> putFloat(key, any)
                else -> save(key, gson.toJson(any))
            }
        }
    }

    inline fun <reified T> get(key: String): T {
        sharedPreferences.run {
            val d = when (T::class) {
                Int::class -> getInt(key, 0)
                Long::class -> getLong(key, 0)
                Float::class -> getFloat(key, 0f)
                String::class -> getString(key, "")
                else -> {
                    getString(key, "").run {
                        gson.fromJson(this, T::class.java)
                    }
                }
            }
            return d as T
        }
    }


}