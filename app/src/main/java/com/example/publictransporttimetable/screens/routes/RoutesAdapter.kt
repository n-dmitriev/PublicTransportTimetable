package com.example.publictransporttimetable.screens.routes

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.publictransporttimetable.R
import com.example.publictransporttimetable.model.entity.Route


class RoutesAdapter(
    var items: List<Route> = mutableListOf(),
    val deleteCallback: (index: Int) -> Unit,
    val goToRouteCallback: (index: Int) -> Unit
) : RecyclerView.Adapter<RoutesAdapter.RouteViewHolder>() {

    fun updateData(list: List<Route>) {
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder =
        RouteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
        Log.d(ContentValues.TAG, holder.toString() + position.toString())
        holder.bind(items[position])
    }

    inner class RouteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.name)
        private val description: TextView = itemView.findViewById(R.id.description)
        private val deleteItem: ImageView = itemView.findViewById(R.id.delete_item)
        private val routeItem: ConstraintLayout = itemView.findViewById(R.id.item_body)

        fun bind(item: Route) {
            name.text = item.name
            description.text = item.type
            deleteItem.setOnClickListener {
                deleteCallback(adapterPosition)
            }
            routeItem.setOnClickListener {
                goToRouteCallback(adapterPosition)
            }
        }
    }
}
