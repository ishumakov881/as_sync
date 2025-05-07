#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "") package ${PACKAGE_NAME} #end

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ${NAME}Screen(
    onBackPressed: () -> Unit,
    onNavigateTo${NAME}Details: () -> Unit = {},
    viewModel: ${NAME}ViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    ${NAME}Body(
        state = state,
        onAction = { /* TODO */ },
        onNavigateToDetails = onNavigateTo${NAME}Details
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview${NAME}Body() {
    val mockState = remember {
        ${NAME}State(
            // Заполни поля под свой случай
            isLoading = false,
            data = listOf("Item 1", "Item 2"),
            error = null
        )
    }

    ${NAME}Body(
        state = mockState,
        onAction = {},
        onNavigateToDetails = {}
    )
}

@Composable
private fun ${NAME}Body(
    state: ${NAME}State,
    onAction: () -> Unit,
    onNavigateToDetails: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Вставь сюда разметку на основе state
        Text(text = "Экран ${NAME}", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateToDetails) {
            Text("Перейти к деталям")
        }
    }
}