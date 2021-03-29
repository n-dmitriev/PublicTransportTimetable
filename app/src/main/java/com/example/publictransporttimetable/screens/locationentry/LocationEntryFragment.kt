package com.example.publictransporttimetable.screens.locationentry

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.publictransporttimetable.R
import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.publictransporttimetable.databinding.LocationEntryBinding
import com.example.publictransporttimetable.model.TimeTableDatabase

class LocationEntryFragment : Fragment() {
    private lateinit var viewModel: LocationEntryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: LocationEntryBinding = DataBindingUtil.inflate(
            inflater, R.layout.route, container, false)

        val application = requireNotNull(this.activity).application

        val viewModelFactory = LocationViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(LocationEntryViewModel::class.java)

        return binding.root
    }
}