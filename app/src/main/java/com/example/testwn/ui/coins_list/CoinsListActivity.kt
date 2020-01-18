package com.example.testwn.ui.coins_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testwn.R
import kotlinx.android.synthetic.main.activity_main_coins.*

class CoinsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_coins)
        setSupportActionBar(toolbar)


        if (savedInstanceState == null) {
            val fmManager = supportFragmentManager.beginTransaction()
            fmManager.replace(R.id.frameContents, CoinsListFragment.newInstance())
            fmManager.commitNow()
        }

    }
}
