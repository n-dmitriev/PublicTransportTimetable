package com.example.publictransporttimetable.screens.busstop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.publictransporttimetable.R
import com.example.publictransporttimetable.databinding.BusStopBinding
import com.example.publictransporttimetable.model.TimeTableDatabase

class BusStopFragment : Fragment() {
    private lateinit var viewModel: BusStopViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: BusStopBinding = DataBindingUtil.inflate(
            inflater, R.layout.bus_stop, container, false
        )

        val application = requireNotNull(this.activity).application
        val pointDao = TimeTableDatabase.getInstance(application).getPointDatabaseDao()

        val args = BusStopFragmentArgs.fromBundle(requireArguments())
        val viewModelFactory =
            BusStopViewModelFactory(args.pointId, args.routeId, args.isEdit, pointDao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(BusStopViewModel::class.java)


        binding.busStopAccept.setOnClickListener {
            when {
                binding.busStopNameInput.text.isEmpty() -> {
                    Toast.makeText(
                        this.requireContext(),
                        getText(R.string.empty_name),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                binding.busStopTimeInput.text.isEmpty() -> {
                    Toast.makeText(
                        this.requireContext(),
                        getText(R.string.empty_time),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    viewModel.updatePoint(
                        binding.busStopNameInput.text.toString(),
                        binding.busStopTimeInput.text.toString()
                    )
                    this.findNavController().popBackStack()
                }
            }
        }

        binding.busStopCancel.setOnClickListener {
            this.findNavController().popBackStack()
        }

        return binding.root
    }
}