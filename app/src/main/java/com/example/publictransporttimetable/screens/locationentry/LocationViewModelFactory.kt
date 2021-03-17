package com.example.publictransporttimetable.screens.locationentry

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.publictransporttimetable.model.dao.BusStopDao
import com.example.publictransporttimetable.model.dao.PointDao
import com.example.publictransporttimetable.model.dao.RouteDao

class LocationViewModelFactory(
    private val busStopDao: BusStopDao,
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationEntryViewModel::class.java)) {
            return LocationEntryViewModel(busStopDao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}