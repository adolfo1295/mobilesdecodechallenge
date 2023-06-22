package com.fofito.codeexcerciseapp.presentation.lists.viewmodel

import androidx.lifecycle.ViewModel
import com.fofito.codeexcerciseapp.models.ListModel
import com.fofito.codeexcerciseapp.presentation.lists.isVowel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ListViewModel : ViewModel() {

    private var _suitableScoreState = MutableStateFlow(SuitableScoreState())
    val suitableScoreState = _suitableScoreState.asStateFlow()

    fun calculateSS(index: Int, list: ListModel) {
        var ss: Double
        val separatedShipment = list.shipments[index].split(" ").toList()
        val streetName = separatedShipment[1]
        val driverSeparatedName = list.drivers[index].split(" ").toList()
        val driverName = driverSeparatedName.first()
        if (streetName.length % 2 == 0) {
            var numberOfVowels = 0
            streetName.forEach { char ->
                if (char.isVowel()) {
                    numberOfVowels++
                }
            }
            ss = numberOfVowels * 1.5
        } else {
            var numberOfConsonants = 0
            streetName.forEach { char ->
                if (!char.isVowel()) {
                    numberOfConsonants++
                }
            }
            ss = (numberOfConsonants * 1).toDouble()
        }
        if (streetName.length == driverName.length) {
            val percent = ss / 2
            ss += percent
        }
        _suitableScoreState.value = SuitableScoreState(suitableScore = ss, openDialog = true)
    }

    fun closeDialog() {
        _suitableScoreState.update { it.copy(openDialog = false, suitableScore = 0.0) }
    }

}
