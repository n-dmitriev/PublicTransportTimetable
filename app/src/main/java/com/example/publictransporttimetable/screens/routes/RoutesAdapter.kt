package com.example.publictransporttimetable.screens.routes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.publictransporttimetable.R
import com.example.publictransporttimetable.model.entity.Route


class RoutesAdapter(
    val deleteCallback: (index: Int) -> Unit,
    val goToRouteCallback: (index: Int) -> Unit
) : RecyclerView.Adapter<RoutesAdapter.RouteViewHolder>() {

    var data = listOf<Route>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder =
        RouteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )

    inner class RouteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.name)
        private val description: TextView = itemView.findViewById(R.id.description)
        private val deleteItem: ImageView = itemView.findViewById(R.id.delete_item)
        private val routeItem: androidx.constraintlayout.widget.ConstraintLayout =
            itemView.findViewById(R.id.item_body)

        fun bind(item: Route) {
            name.text = item.name
            description.text = item.type.toString()
            deleteItem.setOnClickListener {
                deleteCallback(adapterPosition)
            }
            routeItem.setOnClickListener {
                goToRouteCallback(adapterPosition)
            }
        }
    }
}
