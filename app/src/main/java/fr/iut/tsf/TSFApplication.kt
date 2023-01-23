package fr.iut.tsf

import android.app.Application
import fr.iut.tsf.data.TSFDatabase


class TSFApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        TSFDatabase.initialize(this)
    }
}