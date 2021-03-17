package com.example.publictransporttimetable.screens.busstop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.publictransporttimetable.model.dao.BusStopDao

class BusStopViewModelFactory(
    private val dao: BusStopDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BusStopViewModel::class.java)) {
            return BusStopViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}