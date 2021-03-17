package com.example.publictransporttimetable.screens.locationentry

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.publictransporttimetable.model.dao.BusStopDao
import com.example.publictransporttimetable.model.dao.PointDao
import com.example.publictransporttimetable.model.dao.RouteDao
import kotlinx.coroutines.*

class LocationEntryViewModel(
    private val busStopDao: BusStopDao,
    application: Application
) : AndroidViewModel(application) {

}