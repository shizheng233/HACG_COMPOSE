package com.shihcheeng.hacgcompose.ui.screen.main.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.shihcheeng.hacgcompose.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    repository: GameRepository
) : ViewModel() {

    val data = repository.load().cachedIn(viewModelScope)

}