package com.fofito.codeexcerciseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.fofito.codeexcerciseapp.presentation.lists.ListScreen
import com.fofito.codeexcerciseapp.presentation.lists.viewmodel.ListViewModel
import com.fofito.codeexcerciseapp.ui.theme.CodeExcerciseAppTheme
import com.fofito.codeexcerciseapp.utils.JsonReaderHelper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeExcerciseAppTheme {
                val context = LocalContext.current
                val list = JsonReaderHelper().getDataFromJson(context)
                val viewModel: ListViewModel by viewModels()
                val suitableScoreState by viewModel.suitableScoreState.collectAsState()
                ListScreen(list, suitableScoreState, onCalculateSS = { index, listModel ->
                    viewModel.calculateSS(index = index, list = listModel)
                }, onCloseDialog = {
                    viewModel.closeDialog()
                })
            }
        }
    }
}
