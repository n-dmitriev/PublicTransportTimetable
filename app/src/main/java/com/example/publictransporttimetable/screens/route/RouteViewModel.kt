package com.example.publictransporttimetable.screens.route

import android.app.Application
import androidx.lifecycle.*
import com.example.publictransporttimetable.model.dao.PointDao
import com.example.publictransporttimetable.model.dao.RouteDao
import com.example.publictransporttimetable.model.entity.Point
import com.example.publictransporttimetable.model.entity.Route
import kotlinx.coroutines.*


class RouteViewModel(
    private val routeId: Long,
    private val isEdit: Boolean,
    private val pointDao: PointDao,
    private val routeDao: RouteDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _points = MutableLiveData<MutableList<Point>>()
    val points: LiveData<MutableList<Point>> get() = _points

    private var _route = MutableLiveData<Route?>()
    val route: LiveData<Route?> get() = _route

    init {
        if (isEdit || routeId != -1L) {
            updateData()
        } else {
            _points.value = mutableListOf()
            _route.value = null
        }
    }

    fun updateData() {
        getRoute()
        getPoints()
    }

    fun updateRoute(name: String, type: String) {
        uiScope.launch {
            if (_route.value == null) {
                val r = Route(0L, name, type)
                insertRoute(r)
                _route.value = r

            } else {
                val r = Route(_route.value!!.id, name, type)
                updateRoute(r)
                _route.value = r
            }
        }
    }

    private suspend fun updateRoute(route: Route) {
        withContext(Dispatchers.IO) {
            routeDao.update(route)
        }
    }

    private suspend fun insertRoute(route: Route) {
        withContext(Dispatchers.IO) {
            routeDao.insert(route)
        }
    }

    private fun getPoints() {
        uiScope.launch {
            _points.value = getPointsFromDB(routeId).reversed().toMutableList()
        }
    }

    private suspend fun getPointsFromDB(routeId: Long): MutableList<Point> {
        return withContext(Dispatchers.IO) {
            val gr = pointDao.getPointsByRouteId(routeId)
            gr
        }
    }

    private fun getRoute() {
        uiScope.launch {
            _route.value = getRouteFromDB(routeId)
        }
    }

    private suspend fun getRouteFromDB(routeId: Long): Route {
        return withContext(Dispatchers.IO) {
            val gr = routeDao.getRouteById(routeId)
            gr
        }
    }

    fun deleteRoute() {
        uiScope.launch {
            if (_route.value != null) {
                val route = _route.value!!
                deleteR(route)
                _route.value = null
            }
        }
    }

    private suspend fun deleteR(route: Route) {
        withContext(Dispatchers.IO) {
            routeDao.delete(route)
        }
    }

    fun deletePoint(i: Int) {
        uiScope.launch {
            val point = _points.value!!.removeAt(i)
            deleteP(point)
            _points.value = _points.value
        }
    }

    private suspend fun deleteP(point: Point) {
        withContext(Dispatchers.IO) {
            pointDao.delete(point)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}