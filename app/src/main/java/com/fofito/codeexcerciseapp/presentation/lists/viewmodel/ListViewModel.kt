package com.fofito.codeexcerciseapp.presentation.lists.viewmodel

import androidx.lifecycle.ViewModel
import com.fofito.codeexcerciseapp.models.Driver
import com.fofito.codeexcerciseapp.presentation.lists.isVowel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ListViewModel : ViewModel() {

    private var _suitableScoreState = MutableStateFlow(SuitableScoreState())
    val suitableScoreState = _suitableScoreState.asStateFlow()

    fun calculateBestRoutesAndSS(shipments: List<String>, drivers: List<String>) {
        val shipmentArrayList = ArrayList(shipments)
        val driversMap: HashMap<String, HashMap<String, Double>> = hashMapOf()
        drivers.forEach { driver ->
            var bestSS = 0.0
            var bestShip = ""
            val shipMap = HashMap<String, Double>()
            shipmentArrayList.forEachIndexed { shipmentIndex, shipment ->
                val tempSS = calculateSS(shipment, drivers[shipmentIndex])
                if (tempSS > bestSS) {
                    bestSS = tempSS
                    bestShip = shipment
                }
            }
            shipMap[bestShip] = bestSS
            driversMap[driver] = shipMap
            shipmentArrayList.remove(bestShip)
        }

        _suitableScoreState.update {
            it.copy(
                openDialog = false,
                driversMap = driversMap
            )
        }
    }

    private fun calculateSS(shipment: String, driverFullName: String): Double {
        var ss: Double
        val separatedShipment = shipment.split(" ").toList()
        val streetName = separatedShipment[1]
        val driverSeparatedName = driverFullName.split(" ").toList()
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
        return ss
    }

    fun closeDialog() {
        _suitableScoreState.update {
            it.copy(
                openDialog = false,
                driver = null
            )
        }
    }

    fun showDialog(shipment: String, suitableScore: String, driverName: String) {
        _suitableScoreState.update {
            it.copy(
                openDialog = true, driver = Driver(
                    name = driverName, shipment = shipment, ss = suitableScore.toDouble()
                )
            )
        }
    }
}
