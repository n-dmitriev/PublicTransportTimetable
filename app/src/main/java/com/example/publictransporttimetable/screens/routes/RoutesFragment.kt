package com.example.publictransporttimetable.screens.routes

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.publictransporttimetable.R
import com.example.publictransporttimetable.model.TimeTableDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton


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

        rootView.findViewById<EditText>(R.id.route_name_input).addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(editable: Editable?) {
                viewModel.findRoute(editable.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        return rootView
    }

    override fun onStart() {
        super.onStart()
        viewModel.getRoutes()
    }
}