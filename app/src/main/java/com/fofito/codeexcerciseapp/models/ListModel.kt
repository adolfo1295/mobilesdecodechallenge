package com.fofito.codeexcerciseapp.models


import com.google.gson.annotations.SerializedName

data class ListModel(
    @SerializedName("drivers")
    val drivers: List<String>,
    @SerializedName("shipments")
    val shipments: List<String>
)