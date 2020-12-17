package com.mjjang.koreanstylecoordinatenaming

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import com.mjjang.koreanstylecoordinatenaming.databinding.ActivityMainBinding
import com.mjjang.koreanstylecoordinatenaming.util.CoordConverterUtil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        CoordConverterUtil.importWords(this)
    }
}