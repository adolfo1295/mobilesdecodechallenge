@file:OptIn(ExperimentalMaterial3Api::class)

package com.fofito.codeexcerciseapp.presentation.lists.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fofito.codeexcerciseapp.models.Driver

@Composable
fun DriverItem(driver: Driver, onDriverClick: (Driver) -> Unit) {
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
            onDriverClick(driver)
        }) {
        Column {
            Text(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 4.dp),
                text = "Driver Name: ${driver.name}"
            )
            if (driver.isSelected) {
                Text(
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 4.dp),
                    text = "Driver Selected: ${driver.name}"
                )
            }
        }
    }
}