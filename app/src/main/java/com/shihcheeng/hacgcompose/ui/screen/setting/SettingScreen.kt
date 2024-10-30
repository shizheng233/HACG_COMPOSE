package com.shihcheeng.hacgcompose.ui.screen.setting

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shihcheeng.hacgcompose.BuildConfig
import com.shihcheeng.hacgcompose.R
import com.shihcheeng.hacgcompose.components.setting.AppSetting
import com.shihcheeng.hacgcompose.components.setting.components.Preference
import com.shihcheeng.hacgcompose.components.setting.components.StringListPreference
import com.shihcheeng.hacgcompose.ui.icons.CiDoubleQuotesR
import com.shihcheeng.hacgcompose.ui.icons.DeviconGithubWordmark
import com.shihcheeng.hacgcompose.ui.icons.Iconify
import com.shihcheeng.hacgcompose.ui.icons.MaterialSymbolsHostOutline
import com.shihcheeng.hacgcompose.utils.GITHUB_URL
import com.shihcheeng.hacgcompose.utils.LLSSQuotes
import com.shihcheeng.hacgcompose.utils.extra.LocalAppSetting
import com.shihcheeng.hacgcompose.utils.extra.findKeyByValue
import com.shihcheeng.hacgcompose.utils.extra.openMagnet


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    viewModel: SettingViewModel = hiltViewModel(),
    onBack: () -> Unit
) {

    val localSetting = LocalAppSetting.current
    val localContext = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val hostMap = AppSetting.createHostSelected()
    val quote = LLSSQuotes.create()

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
            categoryHeader(
                resId = R.string.network_head,
                key = AppSetting.PREF_SUBHEAD_NET,
                contentType = AppSetting.PREF_SUBHEAD_NET,
            )
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
            categoryHeader(
                resId = R.string.about_head,
                key = AppSetting.PREF_SUBHEAD_ABOUT,
                contentType = AppSetting.PREF_SUBHEAD_ABOUT
            )
            item(
                key = AppSetting.PREF_ABOUT,
                contentType = AppSetting.PREF_ABOUT
            ) {
                Preference(
                    title = stringResource(R.string.about_title),
                    summary = "${BuildConfig.VERSION_NAME}(${BuildConfig.VERSION_CODE})",
                    iconProvider = {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.about_title)
                        )
                    }
                )
            }
            item {
                Preference(
                    title = stringResource(R.string.quotes),
                    summary = quote.rememberRandom(),
                    iconProvider = {
                        Icon(
                            imageVector = Iconify.CiDoubleQuotesR,
                            contentDescription = stringResource(R.string.quotes)
                        )
                    }
                )
            }
            item {
                Preference(
                    title = stringResource(R.string.github_local),
                    summary = GITHUB_URL,
                    iconProvider = {
                        Icon(
                            imageVector = Iconify.DeviconGithubWordmark,
                            contentDescription = null
                        )
                    },
                    onClick = {
                        localContext.openMagnet(GITHUB_URL)
                    }
                )
            }
        }
    }
}

private fun LazyListScope.categoryHeader(
    key: Any? = null,
    contentType: Any? = null,
    @StringRes resId: Int
) {
    item(
        key = key,
        contentType = contentType
    ) {
        Text(
            text = stringResource(resId),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 16.dp)
                .padding(vertical = 16.dp),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
    }
}