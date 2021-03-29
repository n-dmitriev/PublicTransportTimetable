package com.example.publictransporttimetable.screens.route

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.publictransporttimetable.model.dao.PointDao
import com.example.publictransporttimetable.model.dao.RouteDao

class RouteViewModelFactory(
    private val routeId: Long,
    private val isEdit: Boolean,
    private val pointDao: PointDao,
    private val routeDao: RouteDao,
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RouteViewModel::class.java)) {
            return RouteViewModel(routeId, isEdit, pointDao, routeDao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}