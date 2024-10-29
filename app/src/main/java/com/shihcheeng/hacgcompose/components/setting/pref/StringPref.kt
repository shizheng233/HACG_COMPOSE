package com.shihcheeng.hacgcompose.components.setting.pref

import android.content.SharedPreferences
import androidx.core.content.edit

class StringPref(
    key: String,
    defaultValue: String,
    sharedPreferences: SharedPreferences
) : BasePref<String>(
    key = key,
    defaultValue = defaultValue,
    sharedPreferences = sharedPreferences
) {

    override fun get(): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    override fun set(value: String): Boolean {
        return try {
            sharedPreferences.edit { putString(key, value) }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}