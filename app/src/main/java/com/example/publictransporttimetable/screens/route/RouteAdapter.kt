package com.example.publictransporttimetable.screens.route

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.publictransporttimetable.R
import com.example.publictransporttimetable.model.entity.Point

class RouteAdapter(
    val deleteCallback: (index: Int) -> Unit,
    val goToBusStopСallback: (index: Int) -> Unit
) : RecyclerView.Adapter<RouteAdapter.PointViewHolder>() {

    private var items: List<Point> = mutableListOf()

    fun updateData(list: List<Point>) {
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RouteAdapter.PointViewHolder =
        PointViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RouteAdapter.PointViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class PointViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.name)
        private val description: TextView = itemView.findViewById(R.id.description)
        private val deleteItem: ImageView = itemView.findViewById(R.id.delete_item)
        private val busStopItem: androidx.constraintlayout.widget.ConstraintLayout =
            itemView.findViewById(R.id.item_body)

        fun bind(item: Point) {
            name.text = item.stopName
            description.text = item.time
            deleteItem.setOnClickListener {
                deleteCallback(adapterPosition)
            }
            busStopItem.setOnClickListener {
                goToBusStopСallback(adapterPosition)
            }
        }
    }
}
