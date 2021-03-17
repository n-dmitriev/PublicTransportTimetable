package com.example.publictransporttimetable.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.publictransporttimetable.model.entity.BusStop

@Dao
interface BusStopDao {

    @Insert
    fun insert(bus_stop: BusStop)

    @Update
    fun update(bus_stop: BusStop)

    @Delete
    fun delete(bus_stop: BusStop)

    @Query("SELECT * FROM bus_stops WHERE id = :key")
    fun get(key: Long): BusStop?

    @Query("SELECT * FROM bus_stops ORDER BY id DESC")
    fun getAllBusStops(): LiveData<List<BusStop>>

    @Query("SELECT * FROM bus_stops ORDER BY id DESC LIMIT 1")
    fun getBusStopById(): BusStop?
}