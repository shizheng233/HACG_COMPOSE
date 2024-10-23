package com.shihcheeng.hacgcompose.ui.screen.main.comic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.shihcheeng.hacgcompose.repository.ComicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val repository: ComicRepository
) : ViewModel() {

    val data = repository.load().cachedIn(viewModelScope)

}