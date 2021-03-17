package com.example.publictransporttimetable.screens.routes

import com.example.publictransporttimetable.screens.routes.RoutesViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.publictransporttimetable.model.dao.RouteDao

class RoutesViewModelFactory(
    private val dao: RouteDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoutesViewModel::class.java)) {
            return RoutesViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}