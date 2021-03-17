package com.example.publictransporttimetable.screens.busstop

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.publictransporttimetable.model.dao.BusStopDao
import com.example.publictransporttimetable.model.dao.PointDao
import com.example.publictransporttimetable.model.dao.RouteDao
import kotlinx.coroutines.*

class BusStopViewModel(
    private val pointDao: PointDao,
    private val busStopDao: BusStopDao,
    application: Application
) : AndroidViewModel(application) {

}