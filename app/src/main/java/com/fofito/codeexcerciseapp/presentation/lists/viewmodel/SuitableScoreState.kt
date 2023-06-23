package com.fofito.codeexcerciseapp.presentation.lists.viewmodel

import com.fofito.codeexcerciseapp.models.Driver

data class SuitableScoreState(
    val openDialog: Boolean = false,
    val driversMap: HashMap<String, HashMap<String, Double>> = hashMapOf(),
    val driver: Driver? = null
)