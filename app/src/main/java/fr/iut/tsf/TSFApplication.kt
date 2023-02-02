package fr.iut.tsf

import android.app.Application
import android.view.WindowManager
import fr.iut.tsf.api.TSFManager
import fr.iut.tsf.data.TSFDatabase
import fr.iut.tsf.data.TSFRepository


class TSFApplication : Application() {

    val database by lazy {
        TSFDatabase.initialize(this)
        TSFDatabase.getInstance()
    }
    val tsfAPIManager by lazy { TSFManager() }
    val repository by lazy{
        TSFRepository(database.tsfDao(), tsfAPIManager)
    }
}