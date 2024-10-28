package com.shihcheeng.hacgcompose.ui.screen.main.music

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.shihcheeng.hacgcompose.repository.MusicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    repository: MusicRepository
) : ViewModel() {

    val data = repository.load().cachedIn(viewModelScope)

}