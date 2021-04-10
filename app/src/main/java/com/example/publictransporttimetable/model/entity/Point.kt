package com.example.publictransporttimetable.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "points")
data class Point(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0L,

    @ColumnInfo(name = "route_id")
    var routeId: Long = 0L,

    @ColumnInfo(name = "stop_name")
    var stopName: String = "",

    @ColumnInfo(name = "time")
    var time: String = "",

//    @ColumnInfo(name = "number")
//    var number: Int = 0
)