@file:OptIn(ExperimentalMaterial3Api::class)

package com.fofito.codeexcerciseapp.presentation.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fofito.codeexcerciseapp.models.ListModel
import com.fofito.codeexcerciseapp.presentation.lists.viewmodel.SuitableScoreState

@Composable
fun ListScreen(
    list: ListModel,
    suitableScore: SuitableScoreState,
    onCalculateSS: (Int, ListModel) -> Unit,
    onCloseDialog: () -> Unit
) {

    val openDialog = remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Drivers List Screen")
            })
        }
    ) { paddingValues ->
        if (suitableScore.openDialog) {
            SuitableScoreDialog(
                suitableScore = suitableScore,
                openDialog = openDialog,
                onCloseDialog = onCloseDialog
            )
        }
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            itemsIndexed(list.drivers) { index, name ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    ),
                    onClick = {
                        openDialog.value = true
                        onCalculateSS(index, list)
                    }) {
                    Text(
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 4.dp),
                        text = "Driver Name: $name"
                    )
                }
            }
        }
    }
}

@Composable
fun SuitableScoreDialog(
    suitableScore: SuitableScoreState,
    openDialog: MutableState<Boolean>,
    onCloseDialog: () -> Unit
) {
    AlertDialog(
        title = {
            Text(text = "Calculated Suitable Score")
        },
        text = {
            Text(text = "${suitableScore.suitableScore}")
        },
        onDismissRequest = {
            openDialog.value = false
            onCloseDialog()
        }, confirmButton = {
            TextButton(onClick = {
                openDialog.value = false
                onCloseDialog()
            }) {
                Text(text = "Ok")
            }
        })
}