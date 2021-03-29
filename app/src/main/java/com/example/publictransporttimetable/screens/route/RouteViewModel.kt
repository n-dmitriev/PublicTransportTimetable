package com.example.publictransporttimetable.screens.route

import android.app.Application
import androidx.lifecycle.*
import com.example.publictransporttimetable.model.dao.PointDao
import com.example.publictransporttimetable.model.dao.RouteDao
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

    private var _point = MutableLiveData<MutableList<PointDao>>()
    val point: LiveData<MutableList<PointDao>> get() = _point

    private var _route = MutableLiveData<MutableList<Route>>()
    val route: LiveData<MutableList<Route>> get() = _route

    init {
        getRoute()
    }

    private fun getRoute() {
        uiScope.launch {
            _route.value = getRouteFromDB()
        }
    }

    private suspend fun getRouteFromDB(): MutableList<Route> {
        return withContext(Dispatchers.IO) {
            val gr = routeDao.getAllRoutes()
            gr
        }
    }

    fun deleteRoute(index: Int) {
        uiScope.launch {
            val route = _route.value!!.removeAt(index)
            delete(route)
            _route.value = _route.value
        }
    }

    private suspend fun delete(route: Route) {
        withContext(Dispatchers.IO) {
            routeDao.delete(route)
        }
    }

    private suspend fun addRouteInDB(route: Route) {
        withContext(Dispatchers.IO) {
            routeDao.insert(route)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}