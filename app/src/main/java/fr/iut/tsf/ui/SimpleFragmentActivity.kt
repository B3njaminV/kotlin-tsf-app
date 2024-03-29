package fr.iut.tsf.ui.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import fr.iut.tsf.R

abstract class SimpleFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutResId())
        setSupportActionBar(findViewById(R.id.toolbar_activity))
        val toolbar = findViewById<Toolbar>(R.id.toolbar_activity)
        if(toolbar != null) {
            toolbar.titleMarginStart = 240
        }
        if (supportFragmentManager.findFragmentById(R.id.container_fragment) == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container_fragment, createFragment())
                .commit()
        }
    }

    protected abstract fun createFragment(): Fragment

    @LayoutRes
    protected abstract fun getLayoutResId(): Int
}
