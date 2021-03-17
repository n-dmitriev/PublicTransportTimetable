package com.example.publictransporttimetable.screens.locationentry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.publictransporttimetable.model.dao.BusStopDao

class LocationViewModelFactory(
    private val dao: BusStopDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationEntryViewModel::class.java)) {
            return LocationEntryViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}