package com.android.islami.islamiproject.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.islami.R

class AyatquranAdapter:RecyclerView.Adapter<AyatquranAdapter.AyatquranViewholder> (){

    var list:List<String>?=null
    class AyatquranViewholder: RecyclerView.ViewHolder {
        var content:TextView?=null
        constructor(itemView: View) : super(itemView){
            content= itemView.findViewById(R.id.ayatquran)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyatquranViewholder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.ayatquranitem,parent,false)
        return AyatquranViewholder(view)
    }

    override fun getItemCount(): Int {
       return list?.size ?:0
    }

    override fun onBindViewHolder(holder: AyatquranViewholder, position: Int) {
        var data=list?.get(position)
        holder.content?.text=data
    }
    fun changedata(list:List<String>){
       this.list=list
        notifyDataSetChanged()  //reload data

    }
}