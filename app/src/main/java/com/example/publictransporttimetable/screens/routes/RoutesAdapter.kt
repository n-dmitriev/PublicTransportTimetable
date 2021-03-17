package com.example.publictransporttimetable.screens.routes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.publictransporttimetable.R
import com.example.publictransporttimetable.model.entity.Route

class RouteViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.name)
    private val description: TextView = itemView.findViewById(R.id.description)
    private val deleteItem: ImageView = itemView.findViewById(R.id.delete_item)

    fun bind(item: Route) {
        name.text = item.name
        description.text = item.type.toString()
    }

    companion object {
        fun from(parent: ViewGroup): RouteViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                    .inflate(R.layout.list_item, parent, false)
            return RouteViewHolder(view)
        }
    }
}

class RoutesAdapter : RecyclerView.Adapter<RouteViewHolder>() {

    private var data = listOf<Route>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
        return RouteViewHolder.from(parent)
    }
}
