package com.android.islami.islamiproject.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.islami.R

class HadethWrittenAdapter: RecyclerView.Adapter<HadethWrittenAdapter.HadethwrittenViewholder>() {

    var list:List<String>?=null
    class HadethwrittenViewholder: RecyclerView.ViewHolder {
        var content: TextView?=null
        constructor(itemView: View) : super(itemView){
            content= itemView.findViewById(R.id.hadethwritten)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HadethwrittenViewholder {
        var view= LayoutInflater.from(parent.context).inflate(R.layout.hadethweittenitem,parent,false)
        return HadethwrittenViewholder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?:0
    }

    override fun onBindViewHolder(holder: HadethwrittenViewholder, position: Int) {
        var data=list?.get(position)
        holder.content?.text=data
    }
    fun changedata(list:List<String>){
        this.list=list
        notifyDataSetChanged()  //reload data

    }
}