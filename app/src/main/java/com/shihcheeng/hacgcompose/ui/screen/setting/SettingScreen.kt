package com.shihcheeng.hacgcompose.ui.screen.setting

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shihcheeng.hacgcompose.R
import com.shihcheeng.hacgcompose.components.setting.AppSetting
import com.shihcheeng.hacgcompose.components.setting.components.StringListPreference
import com.shihcheeng.hacgcompose.ui.icons.Iconify
import com.shihcheeng.hacgcompose.ui.icons.MaterialSymbolsHostOutline
import com.shihcheeng.hacgcompose.utils.extra.LocalAppSetting
import com.shihcheeng.hacgcompose.utils.extra.findKeyByValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    viewModel: SettingViewModel = hiltViewModel(),
    onBack: () -> Unit
) {

    val localSetting = LocalAppSetting.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val hostMap = AppSetting.createHostSelected()

    val host by viewModel.host.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.setting))
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_arrow_back_24),
                            contentDescription = stringResource(R.string.back_to_up)
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .fillMaxSize()
        ) {
            item(
                key = AppSetting.PREF_HOST,
                contentType = AppSetting.PREF_HOST
            ) {
                StringListPreference(
                    list = hostMap,
                    selectItem = host,
                    title = stringResource(R.string.select_host_title),
                    summaryText = hostMap.findKeyByValue(host) ?: stringResource(R.string.unknown),
                    leadingIcon = {
                        Icon(
                            imageVector = Iconify.MaterialSymbolsHostOutline,
                            contentDescription = null
                        )
                    },
                    dialogSummary = stringResource(R.string.select_host_summary),
                    onSelected = viewModel::setHost
                )
            }
        }
    }
}