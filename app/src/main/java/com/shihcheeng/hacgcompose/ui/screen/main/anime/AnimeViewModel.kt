package com.shihcheeng.hacgcompose.ui.screen.main.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.shihcheeng.hacgcompose.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    repository: AnimeRepository
) : ViewModel() {

    val data = repository.load().cachedIn(viewModelScope)

}