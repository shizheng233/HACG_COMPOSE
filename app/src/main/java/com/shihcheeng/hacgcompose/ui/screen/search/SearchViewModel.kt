package com.shihcheeng.hacgcompose.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.shihcheeng.hacgcompose.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    private val _searchWord = MutableStateFlow("")
    val searchWord get() = _searchWord.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val emitSearch = _searchWord.filterNotNull()
        .filter { x -> x.isNotBlank() && x.isNotEmpty() }
        .flatMapConcat {
            repository.pagingSearch(it)
        }.cachedIn(viewModelScope)

    fun search(word: String) = viewModelScope.launch {
        _searchWord.emit(word)
    }


}