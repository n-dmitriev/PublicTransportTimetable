package com.example.publictransporttimetable.screens.busstop

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.publictransporttimetable.model.dao.BusStopDao
import com.example.publictransporttimetable.model.dao.PointDao
import com.example.publictransporttimetable.model.dao.RouteDao

class BusStopViewModelFactory(
    private val pointDao: PointDao,
    private val busStopDao: BusStopDao,
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BusStopViewModel::class.java)) {
            return BusStopViewModel(pointDao, busStopDao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}