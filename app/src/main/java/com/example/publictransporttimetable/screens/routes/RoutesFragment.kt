package com.example.publictransporttimetable.screens.routes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.publictransporttimetable.R
import com.example.publictransporttimetable.model.TimeTableDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RoutesFragment : Fragment() {
    private lateinit var viewModel: RoutesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.routes, container, false)
        val application = requireNotNull(this.activity).application
        val routeDao = TimeTableDatabase.getInstance(application).getRouteDatabaseDao()

        val viewModelFactory = RoutesViewModelFactory(routeDao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(RoutesViewModel::class.java)

        val adapter = RoutesAdapter(deleteCallback = { i ->
            viewModel.deleteRoute(i)
        }, goToRouteCallback = { i ->
            this.findNavController().navigate(
                RoutesFragmentDirections
                    .actionRoutesToRoute(viewModel.route.value!![i].id, false)
            )
        })

        rootView.findViewById<FloatingActionButton>(R.id.create_route).setOnClickListener {
            this.findNavController().navigate(
                RoutesFragmentDirections
                    .actionRoutesToRoute(-1, true)
            )
        }

        rootView?.findViewById<RecyclerView>(R.id.routes_list)?.adapter = adapter

        viewModel.route.observe(this.requireActivity(), { routes ->
            adapter.data = routes
        })

        return rootView
    }
}