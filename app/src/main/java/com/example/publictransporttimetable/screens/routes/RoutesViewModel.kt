package com.example.publictransporttimetable.screens.routes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.publictransporttimetable.model.dao.RouteDao
import com.example.publictransporttimetable.model.entity.Route
import kotlinx.coroutines.*

class RoutesViewModel(
    private val dao: RouteDao,
    application: Application
) : AndroidViewModel(application) {
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _route = MutableLiveData<MutableList<Route>>()

    val route: LiveData<MutableList<Route>> get() = _route

    init {
        getRoutes()
    }

    private fun getRoutes() {
        uiScope.launch {
            _route.value = getRouteFromDB()
        }
    }

    private suspend fun getRouteFromDB(): MutableList<Route> {
        return withContext(Dispatchers.IO) {
            val gr = dao.getAllRoutes()
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
            dao.delete(route)
        }
    }
}
