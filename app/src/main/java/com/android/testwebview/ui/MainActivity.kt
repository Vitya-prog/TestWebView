package com.android.testwebview.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.android.testwebview.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        isAgain = sp.getBoolean("isAgain",false)
        val e = sp.edit()
        e.putBoolean("isAgain",true)
        e.apply()
    }
    companion object{
        var isAgain = false
    }
}