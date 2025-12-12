package com.android.islami.islamiproject.projectfragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.islami.Constent
import com.android.islami.R
import com.android.islami.islamiproject.main.HadethDetailes
import com.android.islami.islamiproject.recyclerview.HadethAdapter


class Hadethfragment:Fragment() {
    lateinit var datalist: ArrayList<String>
    lateinit var recyclerView: RecyclerView
    lateinit var hadethAdapter: HadethAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.ahadethfragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

    }

    fun init() {
        recyclerView = requireView().findViewById(R.id.hadethrecyclerview)

        creatdata()

        hadethAdapter = HadethAdapter(datalist)
        hadethAdapter.hadethnameonclicklistener = object : HadethAdapter.HadethOnClickListener {
            override fun hadethonclick(pos: Int, hadethname: String) {
                hadethdetailes(pos, hadethname)
            }

        }
        recyclerView.adapter = hadethAdapter

    }

    fun creatdata() {
        datalist = arrayListOf()
        for (i in 1..50) {
            datalist.add("  الحديث رقم$i ")
        }
    }

    fun hadethdetailes(pos: Int, suraname: String) {
        val intent = Intent(context, HadethDetailes::class.java)
        intent.putExtra(Constent.name, suraname)
        intent.putExtra(Constent.pos, pos)
        startActivity(intent)
    }
}