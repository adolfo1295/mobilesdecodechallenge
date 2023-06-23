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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.fofito.codeexcerciseapp.presentation.lists.viewmodel.SuitableScoreState

@Composable
fun ListScreen(
    suitableScoreState: SuitableScoreState,
    onCloseDialog: () -> Unit,
    showSuitableScoreDialog: (String, String, String) -> Unit
) {


    val openDialog = remember { mutableStateOf(suitableScoreState.openDialog) }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Drivers List Screen")
            })
        }
    ) { paddingValues ->

        LocalContext.current


        if (openDialog.value) {
            val driver = suitableScoreState.driver
            if (driver != null) {
                SuitableScoreDialog(
                    shipment = driver.shipment,
                    ss = driver.ss.toString(),
                    driverName = driver.name,
                    onCloseDialog = {
                        onCloseDialog()
                        openDialog.value = false
                    }, openDialog
                )
            }
        }

        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            itemsIndexed(suitableScoreState.driversMap.keys.toList()) { index, name ->
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
                        suitableScoreState.driversMap[name]?.mapKeys {
                            showSuitableScoreDialog(it.key, it.value.toString(), name)
                        }
                        openDialog.value = true
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
    shipment: String,
    ss: String,
    driverName: String,
    onCloseDialog: () -> Unit,
    openDialog: MutableState<Boolean>,

    ) {
    AlertDialog(
        title = {
            Text(text = "Calculated Suitable Score for $driverName")
        },
        text = {
            Text(text = "The best destination is $shipment with suitable score: $ss")
        },
        onDismissRequest = {
            openDialog.value = false
            onCloseDialog()
        }, confirmButton = {
            TextButton(onClick = {
                onCloseDialog()
            }) {
                Text(text = "Ok")
            }
        })
}