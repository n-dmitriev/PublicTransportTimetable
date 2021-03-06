package com.example.publictransporttimetable.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.publictransporttimetable.model.entity.Point

@Dao
interface PointDao {

    @Insert
    fun insert(point: Point)

    @Update
    fun update(point: Point)

    @Delete
    fun delete(point: Point)

    @Query("SELECT * FROM points ORDER BY id DESC")
    fun getAllPoints(): LiveData<List<Point>>

    @Query("SELECT * FROM points WHERE id = :id")
    fun getPointById(id: Long): Point?

    @Query("SELECT * FROM points WHERE route_id == :id")
    fun getPointsByRouteId(id: Long): MutableList<Point>
}