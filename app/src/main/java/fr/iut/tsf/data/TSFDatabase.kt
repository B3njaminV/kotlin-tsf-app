package fr.iut.tsf.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.iut.tsf.TSFApplication
import fr.iut.tsf.model.Film

private const val DB_FILENAME = "tsf.db"

@Database(entities = [Film::class], version = 1, exportSchema = false)
abstract class TSFDatabase : RoomDatabase() {
    abstract fun tsfDao(): TSFDao
    companion object {
        private lateinit var application: Application

        @Volatile
        private var instance: TSFDatabase? = null

        fun getInstance(): TSFDatabase {
            return instance ?: synchronized(this) {
                val inst = Room.databaseBuilder(
                    application.applicationContext,
                    TSFDatabase::class.java,
                    DB_FILENAME
                ).build();
                inst.tsfDao().let {
                    if (it.getAll().equals(null)) emptyDatabaseStub(it)
                }
                instance = inst
                return inst
            }
        }

        @Synchronized
        fun initialize(app: TSFApplication) {
            if (::application.isInitialized)
                throw RuntimeException("the database must not be initialized twice")
            application = app
        }

        private fun emptyDatabaseStub(tsfDao: TSFDao) = with(tsfDao) {
            insertAll(Stub().filmStub())
        }
    }
}
