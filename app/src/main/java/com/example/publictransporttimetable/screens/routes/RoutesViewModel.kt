package com.example.publictransporttimetable.screens.routes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.publictransporttimetable.model.dao.RouteDao
import kotlinx.coroutines.*

class RoutesViewModel(
    private val dao: RouteDao,
    application: Application
) : AndroidViewModel(application) {

}
