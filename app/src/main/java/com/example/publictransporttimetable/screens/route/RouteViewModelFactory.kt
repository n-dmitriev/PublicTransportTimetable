package com.example.publictransporttimetable.screens.route

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.publictransporttimetable.model.dao.BusStopDao
import com.example.publictransporttimetable.model.dao.PointDao
import com.example.publictransporttimetable.model.dao.RouteDao

class RouteViewModelFactory(
    private val pointDao: PointDao,
    private val busStopDao: BusStopDao,
    private val routeDao: RouteDao,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RouteViewModel::class.java)) {
            return RouteViewModel(pointDao, busStopDao, routeDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}