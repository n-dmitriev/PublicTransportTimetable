package com.example.publictransporttimetable.screens.busstop

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
import com.example.publictransporttimetable.databinding.BusStopBinding
import com.example.publictransporttimetable.model.TimeTableDatabase

class BusStopFragment : Fragment() {
    private lateinit var viewModel: BusStopViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: BusStopBinding = DataBindingUtil.inflate(
            inflater, R.layout.route, container, false)

        val application = requireNotNull(this.activity).application
        val pointDao = TimeTableDatabase.getInstance(application).getPointDatabaseDao()
        val busStopDao = TimeTableDatabase.getInstance(application).getStopDatabaseDao()

        val viewModelFactory = BusStopViewModelFactory(pointDao, busStopDao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(BusStopViewModel::class.java)

        return binding.root
    }
}