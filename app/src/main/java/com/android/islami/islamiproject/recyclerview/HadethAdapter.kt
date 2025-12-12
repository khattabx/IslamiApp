package com.android.islami.islamiproject.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.islami.R
import com.android.islami.islamiproject.recyclerview.HadethWrittenAdapter

class HadethAdapter: RecyclerView.Adapter<HadethAdapter.Hadethviewholder>{

    var datalist:ArrayList<String>
    constructor(datalist: ArrayList<String>) : super() {
        this.datalist = datalist
    }

    class Hadethviewholder:RecyclerView.ViewHolder{
        var hadethname:TextView?=null
        constructor(itemView: View) : super(itemView){

            hadethname=itemView.findViewById(R.id.texthadeth)

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Hadethviewholder {

        var view=LayoutInflater.from(parent.context).inflate(R.layout.hadethitem,parent,false)
        return Hadethviewholder(view)
    }

    override fun getItemCount(): Int {
     return datalist.size
    }

    override fun onBindViewHolder(holder: Hadethviewholder, position: Int) {

        var data= datalist?.get(position)
        holder.hadethname?.setText(data)

        if(hadethnameonclicklistener!=null){
            holder.hadethname?.setOnClickListener {
                if (data != null) {
                    hadethnameonclicklistener?.hadethonclick(position,data)
                }
            }
        }

    }

    var hadethnameonclicklistener:HadethOnClickListener?=null
    interface HadethOnClickListener{
        fun hadethonclick(pos:Int,hadethname:String)
    }

}