package com.example.publictransporttimetable.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.publictransporttimetable.model.entity.Point

@Dao
interface PointDao {

    @Insert
    fun insert(point: Point)

    @Update
    fun update(point: Point)

    @Query("SELECT * FROM points WHERE id = :key")
    fun get(key: Long): Point?

    @Query("DELETE FROM points")
    fun clear()

    @Query("SELECT * FROM points ORDER BY id DESC")
    fun getAllPoints(): LiveData<List<Point>>

    @Query("SELECT * FROM points ORDER BY id DESC LIMIT 1")
    fun getPointById(): Point?
}