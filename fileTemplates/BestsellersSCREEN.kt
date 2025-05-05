#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "") package ${PACKAGE_NAME} #end



import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier

import androidx.compose.ui.Alignment

import com.lds.cinema.ui.viewmodel.${NAME}ViewModel


import com.lds.cinema.ui.view.ErrorScreen

import com.lds.data.repository.state.ResultState
import com.lds.domain.model.MovieItem
import com.lds.data.model.CategorieItem
import moe.tlaster.precompose.koin.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun ${NAME}Screen(
    viewModel: ${NAME}ViewModel = koinViewModel(),
    onNavigateToTv: () -> Unit,
    onNavigateToPlay: (MovieItem) -> Unit,
    onCategoryClick: (CategorieItem) -> Unit

) {
    val state = viewModel.homeDataUIState.collectAsState()
    when (state.value) {
        is ResultState.Error -> {
            ErrorScreen(onRetry = {
                viewModel.fetch${NAME}Data()
            })
        }

        ResultState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is ResultState.Success -> {
            val data = (state.value as ResultState.Success<Map<CategorieItem, List<MovieItem>>>).data
            MediaItemCategoryListView(
                mediaItemCategories = data,
                onNavigateToPlay = {
                    onNavigateToPlay(it)
                }, onCategoryClick = onCategoryClick)
        }
    }
    LaunchedEffect(Unit) {
        viewModel.fetch${NAME}Data()
    }
}