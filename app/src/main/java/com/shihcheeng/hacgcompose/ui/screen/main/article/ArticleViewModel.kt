package com.shihcheeng.hacgcompose.ui.screen.main.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.shihcheeng.hacgcompose.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    repository: ArticleRepository
) : ViewModel() {

    val data = repository.load().cachedIn(viewModelScope)

}