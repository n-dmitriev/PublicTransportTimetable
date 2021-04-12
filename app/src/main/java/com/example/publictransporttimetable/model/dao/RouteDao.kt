package com.example.publictransporttimetable.model.dao

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

    @Query("SELECT * FROM routes ORDER BY id DESC")
    fun getAllRoutes(): MutableList<Route>

    @Query("SELECT * FROM routes WHERE id = :id")
    fun getRouteById(id: Long): Route

    @Query("SELECT * FROM routes WHERE name LIKE '%' || :search || '%'")
    fun findRoute(search: String): MutableList<Route>
}