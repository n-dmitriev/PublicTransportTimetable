package com.example.publictransporttimetable.screens.route

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
import com.example.publictransporttimetable.databinding.RouteBinding
import com.example.publictransporttimetable.model.TimeTableDatabase

class RouteFragment : Fragment() {
    private lateinit var viewModel: RouteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: RouteBinding = DataBindingUtil.inflate(
            inflater, R.layout.route, container, false)

        val application = requireNotNull(this.activity).application
        val pointDao = TimeTableDatabase.getInstance(application).getPointDatabaseDao()
        val busStopDao = TimeTableDatabase.getInstance(application).getStopDatabaseDao()
        val routeDao = TimeTableDatabase.getInstance(application).getRouteDatabaseDao()

        val viewModelFactory = RouteViewModelFactory(pointDao, busStopDao, routeDao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(RouteViewModel::class.java)

        val adapter = RouteAdapter()
        binding.pointsList.adapter = adapter


        return binding.root
    }
}