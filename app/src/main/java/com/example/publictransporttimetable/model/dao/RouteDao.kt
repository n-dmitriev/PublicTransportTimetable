package com.example.publictransporttimetable.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.publictransporttimetable.model.entity.Route

@Dao
interface RouteDao {

    @Insert
    fun insert(route: Route)

    @Update
    fun update(route: Route)

    @Delete
    fun delete(route: Route)

    @Query("SELECT * FROM routes WHERE id = :key")
    fun get(key: Long): Route?

    @Query("SELECT * FROM routes ORDER BY id DESC")
    fun getAllRoutes(): LiveData<List<Route>>

    @Query("SELECT * FROM routes ORDER BY id DESC LIMIT 1")
    fun getRouteById(): Route?
}