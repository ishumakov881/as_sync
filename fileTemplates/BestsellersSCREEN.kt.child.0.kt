#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "") package ${PACKAGE_NAME} #end


import com.lds.data.model.CategorieItem
import moe.tlaster.precompose.viewmodel.ViewModel

import com.lds.domain.repository.PlaybackMediaItemRepository

import com.lds.data.repository.state.ResultState
import com.lds.domain.model.MovieItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.viewModelScope


class ${NAME}ViewModel(private val playbackMediaItemRepository: PlaybackMediaItemRepository) :
    ViewModel() {
    private val homeDataState =
        MutableStateFlow<ResultState<Map<CategorieItem, MutableList<MovieItem>>>>(ResultState.Loading)
    val homeDataUIState = homeDataState.asStateFlow()

    fun fetch${NAME}Data() {

        viewModelScope.launch {
            val data = playbackMediaItemRepository.get${NAME}()
            homeDataState.value = when (data) {
                is ResultState.Success -> ResultState.Success(data.data.mapValues { entry ->
                    entry.value.map { item ->
                        item.isBestseller = true
                        item
                    }.toMutableList()
                })

                is ResultState.Error -> ResultState.Error(data.exception)
                ResultState.Loading -> ResultState.Loading
            }
        }
    }

//    fun fromDomain(item: PlaybackMediaItem): PlaybackMediaItemUI {
//        return PlaybackMediaItemUI(
//            id = item.id,
//            title = item.title,
//            isDownloaded = item.isDownloaded,
//            streamUrl = item.streamUrl,
//            downloadUrl = item.downloadUrl,
//            files = item.files,
//            artworkUrl = item.posterUrl,
//            seasons = emptyList()
//        )
//    }
}