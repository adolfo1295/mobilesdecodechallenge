package com.fofito.codeexcerciseapp.utils

import android.content.Context
import com.fofito.codeexcerciseapp.models.ListModel
import com.google.gson.Gson
import java.io.IOException

class JsonReaderHelper {

    fun getDataFromJson(context: Context): ListModel {
        lateinit var jsonString: String
        return try {
            jsonString = context.assets.open("data.json")
                .bufferedReader()
                .use { it.readText() }
            Gson().fromJson(jsonString, ListModel::class.java)
        } catch (e: IOException) {
            e.printStackTrace()
            ListModel(emptyList(), emptyList())
        }
    }
}