package com.shihcheeng.hacgcompose.components.setting

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.core.net.toUri
import com.shihcheeng.hacgcompose.components.setting.pref.StringPref
import com.shihcheeng.hacgcompose.utils.BASE_URL
import com.shihcheeng.hacgcompose.utils.BASE_URL_2
import com.shihcheeng.hacgcompose.utils.BASE_URL_3
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppSetting @Inject constructor(
    @ApplicationContext context: Context
) {

    private val preferences = context.getSharedPreferences(
        APP_SETTING_PREF_NAME,
        Context.MODE_PRIVATE
    )

    val host get() = StringPref(PREF_HOST, BASE_URL, preferences)


    companion object {

        private const val APP_SETTING_PREF_NAME = "APP_SETTING_PREF"
        const val PREF_HOST = "PREF_HOST"
        const val PREF_ABOUT = "PREF_ABOUT"

        const val PREF_SUBHEAD_NET = "PREF_SUBHEAD_NET"
        const val PREF_SUBHEAD_ABOUT = "PREF_SUBHEAD_ABOUT"


        @Composable
        fun createHostSelected(): Map<String, String> = remember {
            mapOf(BASE_URL to BASE_URL, BASE_URL_2 to BASE_URL_2, BASE_URL_3 to BASE_URL_3)
                .mapKeys { entry ->
                    entry.key.toUri().host?.split(".")
                        ?.filter { it != "www" }
                        ?.joinToString(".")
                        ?: entry.key
                }
        }

        fun SharedPreferences.observer() = callbackFlow {
            val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
                trySend(key)
            }
            registerOnSharedPreferenceChangeListener(listener)
            awaitClose {
                unregisterOnSharedPreferenceChangeListener(listener)
            }
        }

        override fun toString(): String {
            return "G_APP_SETTING"
        }

    }
}