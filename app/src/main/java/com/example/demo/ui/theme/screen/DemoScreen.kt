package com.example.demo.ui.theme.screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.demo.data.DataSource
import com.example.demo.data.Item


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoScreen(
    viewModel: DemoViewModel= viewModel()
){
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar( title = { Text("Loading Data") })
        }
    ) { innerPadding ->
        DemoContent(uiState = uiState, modifier = Modifier.padding(innerPadding))
    }

}

@Composable
fun DemoContent(
    modifier: Modifier = Modifier,
    uiState: DemoUiState
) {

    var showDialog by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(DataSource.data[0]) }

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
    ) {
        items(uiState.items) { item ->
            ContentCard(
                listId = item.first,
                names = item.second,
                item = item,
                onItemSelect = { item ->
                    selectedItem = item
                    showDialog = true
                }
            )
        }
    }

    if(showDialog) {
        ShowDialog(
            itemId = selectedItem.first,
            items = selectedItem.second,
            onDismissRequest = { showDialog = false },
            onConfirmation = { showDialog = false }
        )
    }
}

@Composable
fun ContentCard(
    item: Pair<Int, List<Item>>,
    listId: Int,
    names: List<Item>,
    onItemSelect: (Pair<Int, List<Item>>) -> Unit = {},
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemSelect(item)}
        ,
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "List Id: $listId",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold)

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
            // display the names
            names.chunked(3).forEach { row ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp
                        )
                ) {
                    row.forEach {
                        Text(
                            text = it.name,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun ShowDialog(
    itemId: Int,
    items: List<Item>,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
){
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        },
        title = {
            Text(text = "Id $itemId") },
        text = {
            Column {
                items.forEach { item -> Text(text = item.name) }
            }
        }
    )
}

