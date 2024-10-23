package com.shihcheeng.hacgcompose.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shihcheeng.hacgcompose.datamodel.DetailTitleDataModel
import com.shihcheeng.hacgcompose.networkservice.RemoteLoadState
import com.shihcheeng.hacgcompose.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import org.jsoup.nodes.Node
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _id = savedStateHandle.get<String>("detailId")
    val id: Flow<String>
        get() = MutableStateFlow(_id)
            .filterNotNull()
            .filter { x -> x.isNotBlank() && x.isNotEmpty() }

    private val _title = MutableStateFlow<RemoteLoadState<DetailTitleDataModel>>(
        RemoteLoadState.Loading
    )
    val title = _title.asStateFlow()

    val titlePlain = MutableStateFlow("")

    private val _nodes = MutableStateFlow<RemoteLoadState<List<Node>>>(RemoteLoadState.Loading)
    val nodes = _nodes.asStateFlow()

    init {
        load()
    }

    fun load() = viewModelScope.launch {
        _title.emit(RemoteLoadState.Loading)
        _nodes.emit(RemoteLoadState.Loading)
        try {
            id.collectLatest {
                val data = detailRepository.data(it)
                val titlePackage = detailRepository.parserTitle(data)
                _title.emit(RemoteLoadState.Success(titlePackage))
                _nodes.emit(RemoteLoadState.Success(detailRepository.parserBody(data)))
                titlePlain.emit(titlePackage.title)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _title.emit(RemoteLoadState.Error(e))
            _nodes.emit(RemoteLoadState.Error(e))
        }
    }

}