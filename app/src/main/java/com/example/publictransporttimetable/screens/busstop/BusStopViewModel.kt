package com.example.publictransporttimetable.screens.busstop

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.publictransporttimetable.model.dao.PointDao
import kotlinx.coroutines.*

class BusStopViewModel(
    private val pointDao: PointDao,
    application: Application
) : AndroidViewModel(application) {

}