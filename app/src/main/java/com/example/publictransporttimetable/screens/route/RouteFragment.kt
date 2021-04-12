package com.example.publictransporttimetable.screens.route

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.publictransporttimetable.R
import com.example.publictransporttimetable.model.TimeTableDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton


class RouteFragment : Fragment() {
    private lateinit var viewModel: RouteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.route, container, false)

        val args = RouteFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application
        val pointDao = TimeTableDatabase.getInstance(application).getPointDatabaseDao()
        val routeDao = TimeTableDatabase.getInstance(application).getRouteDatabaseDao()

        val viewModelFactory =
            RouteViewModelFactory(args.routeId, args.isEdit, pointDao, routeDao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(RouteViewModel::class.java)

        val adapter = RouteAdapter(deleteCallback = { i ->
            viewModel.deletePoint(i)
            Toast.makeText(
                this.requireContext(),
                getText(R.string.success_delete_point),
                Toast.LENGTH_SHORT
            ).show()
        }, goToBusStopСallback = { i ->
            this.findNavController().navigate(
                RouteFragmentDirections
                    .actionRouteToBusStop(viewModel.points.value!![i].id, true, args.routeId)
            )
        })

        rootView.findViewById<RecyclerView>(R.id.points_list).adapter = adapter

        viewModel.points.observe(this.requireActivity(), { points ->
            adapter.updateData(points)
        })

        rootView.findViewById<FloatingActionButton>(R.id.add_bus_stop).setOnClickListener {
            this.findNavController().navigate(
                RouteFragmentDirections
                    .actionRouteToBusStop(-1L, false, args.routeId)
            )
        }

        rootView.findViewById<FloatingActionButton>(R.id.delete_route).setOnClickListener {
            viewModel.deleteRoute()
            this.findNavController().popBackStack()
            Toast.makeText(
                this.requireContext(),
                getText(R.string.sucess_delete_route),
                Toast.LENGTH_SHORT
            ).show()
        }

        rootView.findViewById<Button>(R.id.edit_name_handler).setOnClickListener {
            if (rootView.findViewById<EditText>(R.id.route_number_input).text.isEmpty()) {
                Toast.makeText(
                    this.requireContext(),
                    getText(R.string.empty_name),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.updateRoute(
                    rootView.findViewById<EditText>(R.id.route_number_input).text.toString(),
                    rootView.findViewById<Spinner>(R.id.bus_types_spinner).selectedItem.toString()
                )
                if (!args.isEdit) {
                    this.findNavController().popBackStack()
                    Toast.makeText(
                        this.requireContext(),
                        getText(R.string.success_create_route),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this.requireContext(),
                        getText(R.string.success_edit_route),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.bus_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            rootView.findViewById<Spinner>(R.id.bus_types_spinner).adapter = adapter
        }

        viewModel.route.observe(this.requireActivity(), { route ->
            if (route != null) {
                rootView.findViewById<EditText>(R.id.route_number_input).setText(route.name)
                rootView.findViewById<FloatingActionButton>(R.id.delete_route).visibility =
                    View.VISIBLE
                rootView.findViewById<FloatingActionButton>(R.id.add_bus_stop).visibility =
                    View.VISIBLE
                rootView.findViewById<RecyclerView>(R.id.points_list).visibility = View.VISIBLE
            } else {
                rootView.findViewById<FloatingActionButton>(R.id.delete_route).visibility =
                    View.INVISIBLE
                rootView.findViewById<FloatingActionButton>(R.id.add_bus_stop).visibility =
                    View.INVISIBLE
                rootView.findViewById<RecyclerView>(R.id.points_list).visibility = View.INVISIBLE
            }
        })

        return rootView
    }

    override fun onStart() {
        super.onStart()
        viewModel.updateData()
    }
}