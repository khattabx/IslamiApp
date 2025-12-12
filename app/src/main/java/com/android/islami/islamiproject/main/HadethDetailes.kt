package com.android.islami.islamiproject.main

import android.content.Intent
import android.content.res.AssetManager
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.android.islami.Constent
import com.android.islami.R
import com.android.islami.databinding.ActivityHadethDetailesBinding
import com.android.islami.islamiproject.projectfragment.Hadethfragment
import com.android.islami.islamiproject.recyclerview.AyatquranAdapter
import com.android.islami.islamiproject.recyclerview.HadethWrittenAdapter

class HadethDetailes : AppCompatActivity() {
    lateinit var hadethname:TextView
    lateinit var recyclerView: RecyclerView
    lateinit var hadethwrittenadapter:HadethWrittenAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hadeth_detailes)

        hadethname=findViewById(R.id.hadethdetailes)
        setrecyclerview()
        var posofhadeth=intent.getIntExtra(Constent.pos,-1)
        var titleofhadeth=intent.getStringExtra(Constent.name)

        hadethname.setText( titleofhadeth)
        readsuracontentfromassets(posofhadeth)

        }
    fun setrecyclerview(){
        recyclerView=findViewById(R.id.recycleOfhadethwritten)
        hadethwrittenadapter= HadethWrittenAdapter()
        recyclerView.adapter= hadethwrittenadapter

    }
    fun readsuracontentfromassets(pos:Int){
        var filename="h${pos+1}.txt"
        var filecontent=assets.open(filename).bufferedReader().use { it.readText() }
        var datalist=filecontent.split("\n")
        hadethwrittenadapter.changedata(datalist)

    }

}