package com.example.publictransporttimetable.screens.routes

import android.app.Application
import android.content.ContentValues
import android.util.Log
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

    private var _routes = MutableLiveData<MutableList<Route>>()

    val route: LiveData<MutableList<Route>> get() = _routes

    init {
        getRoutes()
    }

    fun findRoute(search: String) {
        uiScope.launch {
            _routes.value = findRouteFromDB(search)
        }
    }

    private suspend fun findRouteFromDB(search: String): MutableList<Route> {
        return withContext(Dispatchers.IO) {
            if (search == "")
                return@withContext dao.getAllRoutes()
            else
                return@withContext dao.findRoute(search)
        }
    }

    fun getRoutes() {
        uiScope.launch {
            _routes.value = getRouteFromDB().reversed().toMutableList()
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
            val route = _routes.value!!.removeAt(index)
            delete(route)
            _routes.value = _routes.value
        }
    }

    private suspend fun delete(route: Route) {
        withContext(Dispatchers.IO) {
            dao.delete(route)
        }
    }
}
