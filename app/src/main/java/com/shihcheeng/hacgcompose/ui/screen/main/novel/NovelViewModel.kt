package com.shihcheeng.hacgcompose.ui.screen.main.novel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.shihcheeng.hacgcompose.repository.NovelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NovelViewModel @Inject constructor(
    repository: NovelRepository
) : ViewModel() {

    val data = repository.load().cachedIn(viewModelScope)

}