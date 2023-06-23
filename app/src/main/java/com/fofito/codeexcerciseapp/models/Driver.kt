package com.fofito.codeexcerciseapp.models

data class Driver(
    val name: String,
    val shipment: String,
    val ss: Double,
    var isSelected: Boolean = false
)
