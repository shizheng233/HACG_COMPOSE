package com.shihcheeng.hacgcompose.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shihcheeng.hacgcompose.datamodel.DetailTitleDataModel
import com.shihcheeng.hacgcompose.datamodel.MainDetailComment
import com.shihcheeng.hacgcompose.networkservice.RemoteLoadState
import com.shihcheeng.hacgcompose.repository.DetailRepository
import com.shihcheeng.hacgcompose.utils.extra.magnet
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
    private val id: Flow<String>
        get() = MutableStateFlow(_id)
            .filterNotNull()
            .filter { x -> x.isNotBlank() && x.isNotEmpty() }

    private val _title = MutableStateFlow<RemoteLoadState<DetailTitleDataModel>>(
        RemoteLoadState.Loading
    )
    val title = _title.asStateFlow()

    private val _nodes = MutableStateFlow<RemoteLoadState<List<Node>>>(RemoteLoadState.Loading)
    val nodes = _nodes.asStateFlow()

    private val _comments = MutableStateFlow<RemoteLoadState<List<MainDetailComment>>>(
        RemoteLoadState.Loading
    )
    val comments = _comments.asStateFlow()

    val titlePlain = MutableStateFlow("")
    val magnetList = MutableStateFlow(emptyList<String>())

    init {
        load()
    }

    fun load() = viewModelScope.launch {
        _title.emit(RemoteLoadState.Loading)
        _nodes.emit(RemoteLoadState.Loading)
        _comments.emit(RemoteLoadState.Loading)
        try {
            id.collectLatest {
                val data = detailRepository.data(it)
                val titlePackage = detailRepository.parserTitle(data)
                _title.emit(RemoteLoadState.Success(titlePackage))
                _nodes.emit(RemoteLoadState.Success(detailRepository.parserBody(data)))
                titlePlain.emit(titlePackage.title)
                val comments = detailRepository.parserComments(data) ?: emptyList()
                _comments.emit(RemoteLoadState.Success(comments))
                magnetList.emit(data.text().magnet().toList().map { "magnet:?xt=urn:btih:$it" })
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _title.emit(RemoteLoadState.Error(e))
            _nodes.emit(RemoteLoadState.Error(e))
            _comments.emit(RemoteLoadState.Error(e))
        }
    }

}