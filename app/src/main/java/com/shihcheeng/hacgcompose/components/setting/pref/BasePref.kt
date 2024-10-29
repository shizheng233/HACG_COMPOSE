package com.shihcheeng.hacgcompose.components.setting.pref

import android.content.SharedPreferences
import androidx.core.content.edit
import com.shihcheeng.hacgcompose.components.setting.AppSetting.Companion.observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

abstract class BasePref<T : Any>(
    protected val key: String,
    protected val defaultValue: T,
    protected val sharedPreferences: SharedPreferences
) {


    abstract fun get(): T
    abstract fun set(value: T): Boolean
    fun remove() {
        sharedPreferences.edit {
            remove(key)
        }
    }

    private fun retransformToFlow(): Flow<T> {
        return sharedPreferences.observer()
            .filter { it == key || it == null }
            .onStart { emit("ignition") }
            .map { get() }
            .conflate()
    }

    fun stateIn(scoped: CoroutineScope) = retransformToFlow().stateIn(
        scope = scoped,
        started = SharingStarted.Eagerly,
        initialValue = get()
    )


}