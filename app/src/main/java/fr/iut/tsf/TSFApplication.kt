package fr.iut.tsf

import android.app.Application
import fr.iut.tsf.data.TSFDatabase
import fr.iut.tsf.data.TSFRepository


class TSFApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val database by lazy{
            TSFDatabase.initialize(this)
        }
        
        val repository by lazy{
            TSFRepository(database.TSFDao())
        }
    }
}