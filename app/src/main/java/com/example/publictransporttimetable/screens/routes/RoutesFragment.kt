package com.example.publictransporttimetable.screens.routes

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.publictransporttimetable.R
import com.example.publictransporttimetable.model.TimeTableDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.lifecycle.Observer

class RoutesFragment : Fragment() {
    private lateinit var viewModel: RoutesViewModel

    private val adapter = RoutesAdapter(deleteCallback = { i ->
        viewModel.deleteRoute(i)
        Toast.makeText(
            this.requireContext(),
            getText(R.string.sucess_delete_route),
            Toast.LENGTH_SHORT
        ).show()
    }, goToRouteCallback = { i ->
        this.findNavController().navigate(
            RoutesFragmentDirections
                .actionRoutesToRoute(viewModel.route.value!![i].id, true)
        )
    })

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

        rootView.findViewById<RecyclerView>(R.id.routes_list).adapter = adapter

        viewModel.route.observe(this.requireActivity(), { routes ->
            adapter.updateData(routes)
        })

        rootView.findViewById<FloatingActionButton>(R.id.create_route).setOnClickListener {
            this.findNavController().navigate(
                RoutesFragmentDirections
                    .actionRoutesToRoute(-1, false)
            )
        }

        return rootView
    }

    override fun onStart() {
        super.onStart()
        viewModel.getRoutes()
    }
}