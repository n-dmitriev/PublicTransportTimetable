package com.example.publictransporttimetable.screens.route

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.publictransporttimetable.model.dao.BusStopDao
import com.example.publictransporttimetable.model.dao.PointDao
import com.example.publictransporttimetable.model.dao.RouteDao
import kotlinx.coroutines.*


class RouteViewModel(
    private val pointDao: PointDao,
    private val busStopDao: BusStopDao,
    private val routeDao: RouteDao,
    application: Application
) : AndroidViewModel(application) {

}