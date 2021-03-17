package com.example.publictransporttimetable.screens.routes

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
import com.example.publictransporttimetable.databinding.RoutesBinding
import com.example.publictransporttimetable.model.TimeTableDatabase

class RoutesFragment : Fragment() {
    private lateinit var viewModel: RoutesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: RoutesBinding = DataBindingUtil.inflate(
            inflater, R.layout.route, container, false)

        val application = requireNotNull(this.activity).application
        val routeDao = TimeTableDatabase.getInstance(application).getRouteDatabaseDao()

        val viewModelFactory = RoutesViewModelFactory(routeDao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(RoutesViewModel::class.java)

        val adapter = RoutesAdapter()
        binding.routesList.adapter = adapter


        return binding.root
    }
}