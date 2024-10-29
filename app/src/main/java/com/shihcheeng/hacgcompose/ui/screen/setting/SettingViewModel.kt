package com.shihcheeng.hacgcompose.ui.screen.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shihcheeng.hacgcompose.components.setting.AppSetting
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val appSetting: AppSetting
) : ViewModel() {

    val host get() = appSetting.host.stateIn(viewModelScope)

    fun setHost(value: String) {
        appSetting.host.set(value)
    }

}