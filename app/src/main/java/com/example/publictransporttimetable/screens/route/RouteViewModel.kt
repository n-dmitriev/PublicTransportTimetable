package com.example.publictransporttimetable.screens.route

import android.app.Application
import androidx.lifecycle.*
import com.example.publictransporttimetable.model.dao.BusStopDao
import com.example.publictransporttimetable.model.dao.PointDao
import com.example.publictransporttimetable.model.dao.RouteDao
import com.example.publictransporttimetable.model.entity.BusStop
import kotlinx.coroutines.*


class RouteViewModel(
    private val pointDao: PointDao,
    private val busStopDao: BusStopDao,
    private val routeDao: RouteDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

    private var busStop = MutableLiveData<BusStop?>()

    private var point = MutableLiveData<PointDao?>()

    private var route = MutableLiveData<RouteDao?>()

    init {

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}