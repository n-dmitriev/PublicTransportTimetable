package com.example.publictransporttimetable.screens.route

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.publictransporttimetable.R
import com.example.publictransporttimetable.databinding.RouteBinding
import com.example.publictransporttimetable.model.TimeTableDatabase

class RouteFragment : Fragment() {
    private lateinit var viewModel: RouteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: RouteBinding = DataBindingUtil.inflate(
            inflater, R.layout.route, container, false
        )

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
        }, goToBusStopСallback = { i ->
            this.findNavController().navigate(
                RouteFragmentDirections
                    .actionRouteToBusStop(viewModel.point.value!![i].id, true)
            )
        })

        binding.addBusStop.setOnClickListener {
            this.findNavController().navigate(
                RouteFragmentDirections
                    .actionRouteToBusStop(-1L, false)
            )
        }

        binding.deleteRoute.setOnClickListener {
            viewModel.deleteRoute()
            this.findNavController().popBackStack()
        }

        binding.editNameHandler.setOnClickListener {
            if (binding.routeNumberInput.text.isEmpty()) {
                Toast.makeText(
                    this.requireContext(),
                    getText(R.string.empty_name),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.updateRoute(binding.routeNumberInput.text.toString())
            }
        }

        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.bus_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.busTypesSpinner.adapter = adapter
        }



        viewModel.route.observe(this.requireActivity(), { route ->
            if (route != null) {
                binding.routeNumberInput.setText(route.name)
                binding.deleteRoute.visibility = View.VISIBLE
                binding.addBusStop.visibility = View.VISIBLE
                binding.pointsList.visibility = View.VISIBLE
            } else {
                binding.deleteRoute.visibility = View.INVISIBLE
                binding.addBusStop.visibility = View.INVISIBLE
                binding.pointsList.visibility = View.INVISIBLE
            }
        })

        viewModel.point.observe(this.requireActivity(), { points ->
            adapter.data = points
        })



        return binding.root
    }
}