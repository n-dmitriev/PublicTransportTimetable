package com.example.publictransporttimetable.screens.route

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.publictransporttimetable.R
import com.example.publictransporttimetable.model.entity.Point

class PointViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.name)
    private val description: TextView = itemView.findViewById(R.id.description)
    private val deleteItem: ImageView = itemView.findViewById(R.id.delete_item)

    fun bind(item: Point) {
        name.text = item.stopId.toString()
        description.text = item.time
    }

    companion object {
        fun from(parent: ViewGroup): PointViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                    .inflate(R.layout.list_item, parent, false)
            return PointViewHolder(view)
        }
    }
}

class RouteAdapter : RecyclerView.Adapter<PointViewHolder>() {

    private var data = listOf<Point>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: PointViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointViewHolder {
        return PointViewHolder.from(parent)
    }
}
