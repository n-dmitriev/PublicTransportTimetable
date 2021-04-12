package com.example.publictransporttimetable.screens.busstop

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.publictransporttimetable.model.dao.PointDao
import com.example.publictransporttimetable.model.entity.Point
import kotlinx.coroutines.*

class BusStopViewModel(
    private val pointId: Long,
    private val routeId: Long,
    private val isEdit: Boolean,
    private val pointDao: PointDao,
    application: Application
) : AndroidViewModel(application) {
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _point = MutableLiveData<Point?>()
    val point: LiveData<Point?> get() = _point

    init {
        if (isEdit && pointId != -1L) {
            getPoint()
        } else {
            _point.value = null
        }
    }

    private fun getPoint() {
        uiScope.launch {
            _point.value = getPointFromDB(pointId)
        }
    }

    private suspend fun getPointFromDB(pointId: Long): Point? {
        return withContext(Dispatchers.IO) {
            val gr = pointDao.getPointById(pointId)
            gr
        }
    }

    fun updatePoint(name: String, time: String) {
        uiScope.launch {
            if (_point.value == null) {
                val r = Point(0L, routeId, name, time)
                insertPoint(r)
                _point.value = r
            } else {
                val r = Point(0L, routeId, name, time)
                updatePoint(r)
                _point.value = r
            }
        }
    }

    private suspend fun updatePoint(point: Point) {
        withContext(Dispatchers.IO) {
            pointDao.update(point)
        }
    }

    private suspend fun insertPoint(point: Point) {
        withContext(Dispatchers.IO) {
            pointDao.insert(point)
        }
    }
}