package com.example.publictransporttimetable.screens.locationentry

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LocationViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationEntryViewModel::class.java)) {
            return LocationEntryViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}