package com.example.publictransporttimetable.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.publictransporttimetable.model.dao.PointDao
import com.example.publictransporttimetable.model.dao.RouteDao
import com.example.publictransporttimetable.model.entity.Point
import com.example.publictransporttimetable.model.entity.Route

@Database(
    entities = [Point::class, Route::class],
    version = 1,
    exportSchema = false
)
abstract class TimeTableDatabase : RoomDatabase() {

    abstract fun getPointDatabaseDao(): PointDao
    abstract fun getRouteDatabaseDao(): RouteDao


    companion object {
        @Volatile
        private var INSTANCE: TimeTableDatabase? = null

        fun getInstance(context: Context): TimeTableDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TimeTableDatabase::class.java, "time_table_db"
                    )
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}