package com.android.islami.islamiproject.main

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.islami.Constent
import com.android.islami.R
import com.android.islami.islamiproject.recyclerview.AyatquranAdapter

class SuraDetailes : AppCompatActivity() {
    lateinit var suratitle:TextView
    lateinit var recyclerView: RecyclerView
    lateinit var ayatadapter: AyatquranAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sura_detailes)

        init()


    }

    fun init() {
        suratitle = findViewById(R.id.suradetailes)

        setrecyclerview()

        // receive sura_name to set on title in fragment that is details of ayat_quran
        val posofsura = intent.getIntExtra(Constent.pos, -1)
        val titleofsura = intent.getStringExtra(Constent.name)
        suratitle.text = titleofsura
        readsuracontentfromassets(posofsura)


    }

    fun setrecyclerview() {
        recyclerView = findViewById(R.id.recycleOfAyatAlquran)
        ayatadapter = AyatquranAdapter()
        recyclerView.adapter = ayatadapter
    }


    fun readsuracontentfromassets(pos: Int) {
        val filename = "${pos + 1}.txt"
        val filecontent = assets.open(filename).bufferedReader().use { it.readText() }
        val datalist = filecontent.split("\n")
        ayatadapter.changedata(datalist)

    }


}