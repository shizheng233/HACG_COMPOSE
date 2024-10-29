package com.shihcheeng.hacgcompose.components.setting.pref

import android.content.SharedPreferences
import androidx.core.content.edit

class BoolPref(
    key: String,
    defaultValue: Boolean = false,
    sharedPreferences: SharedPreferences
):BasePref<Boolean>(key, defaultValue, sharedPreferences) {

    override fun get(): Boolean {
        return sharedPreferences.getBoolean(key,defaultValue)
    }

    override fun set(value: Boolean): Boolean {
        return try {
            sharedPreferences.edit { putBoolean(key,value) }
            true
        }catch (e:Exception){
            false
        }
    }
}