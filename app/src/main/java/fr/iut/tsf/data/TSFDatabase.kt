package fr.iut.tsf.data

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.iut.tsf.TSFApplication
import fr.iut.tsf.model.Film

private const val DB_FILENAME = "tsf.db"

@Database(entities = [Film::class], version = 1)
abstract class TSFDatabase : RoomDatabase() {

    abstract fun TSFDao(): TSFDao

    companion object {
        private lateinit var application: Application

        @Volatile
        private var INSTANCE: TSFDatabase? = null

        fun getInstance(): TSFDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    application.applicationContext,
                    TSFDatabase::class.java,
                    DB_FILENAME
                ).build();
                INSTANCE = instance

                return instance
            }
        }


        @Synchronized
        fun initialize(app: TSFApplication) {
            if (::application.isInitialized)
                throw RuntimeException("the database must not be initialized twice")

            application = app
        }


        private fun emptyDatabaseStub(TSFDao: TSFDao) = with(TSFDao) {
            insert(Film(0, "Test", "./test.png", 10.0))
        }
    }
}
