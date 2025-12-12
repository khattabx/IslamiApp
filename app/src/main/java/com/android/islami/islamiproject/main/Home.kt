package com.android.islami.islamiproject.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.islami.R
import com.android.islami.islamiproject.projectfragment.Hadethfragment
import com.android.islami.islamiproject.projectfragment.QuranFragment
import com.android.islami.islamiproject.projectfragment.Radiofragment
import com.android.islami.islamiproject.projectfragment.Tasbehfragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class Home : AppCompatActivity() {
    lateinit var bottomnavigation: BottomNavigationView
    var Tasbehfragment = Tasbehfragment()
    var Hadethfragment = Hadethfragment()
    var quranFragment = QuranFragment()
    var radiofragment = Radiofragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        init()
        bottomnavigation.selectedItemId = R.id.quran
    }

    fun init() {
        bottomnavigation = findViewById(R.id.buttonnavigation)
        bottomnavigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {

            // if quran or radio media_player is play on any fragment other than this own , media_player.stop()

            if (it.itemId == R.id.quran) {
                open_fragment(quranFragment)
                if (radiofragment.radio_mediaPlayer.isPlaying) radiofragment.radio_mediaPlayer.stop()

            } else if (it.itemId == R.id.radio) {
                open_fragment(radiofragment)
                if (quranFragment.quran_mediaPlayer.isPlaying) quranFragment.quran_mediaPlayer.stop()

            } else if (it.itemId == R.id.hadeth) {
                open_fragment(Hadethfragment)
                if (quranFragment.quran_mediaPlayer.isPlaying) quranFragment.quran_mediaPlayer.stop()
                else if (radiofragment.radio_mediaPlayer.isPlaying) radiofragment.radio_mediaPlayer.stop()

            } else if (it.itemId == R.id.sebha) {
                open_fragment(Tasbehfragment)
                if (quranFragment.quran_mediaPlayer.isPlaying) quranFragment.quran_mediaPlayer.stop()
                else if (radiofragment.radio_mediaPlayer.isPlaying) radiofragment.radio_mediaPlayer.stop()

            }
            return@OnItemSelectedListener true
        })
    }

    fun open_fragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.homefragment, fragment).commit()
    }
}
