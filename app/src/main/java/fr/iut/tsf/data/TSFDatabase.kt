package fr.iut.tsf.data

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.iut.tsf.TSFApplication
import fr.iut.tsf.data.entity.FilmEntity

private const val DB_FILENAME = "tsf.db"

@Database(entities = [FilmEntity::class], version = 1, exportSchema = false)
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

            INSTANCE?.let { database ->
                var dao = database.TSFDao()
                dao.insertAll(Stub().filmStub())
            }
        }
    }
}
