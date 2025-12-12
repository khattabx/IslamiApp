package com.android.islami.islamiproject.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.android.islami.R

class QranAdapter:RecyclerView.Adapter<QranAdapter.QranViewHolder> {
    var datalist: ArrayList<String>

    constructor(datalist: ArrayList<String>) : super() {
        this.datalist = datalist
    }

    class QranViewHolder : RecyclerView.ViewHolder {
        var suraname: TextView? = null
        var quran_play: ImageView? = null
        var quran_progress: ProgressBar? = null

        constructor(itemView: View) : super(itemView) {
            suraname = itemView.findViewById(R.id.suraname)
            quran_play = itemView.findViewById(R.id.quran_play)
            quran_progress = itemView.findViewById(R.id.quran_progress)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QranViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quranitem, parent, false)
        return QranViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }
    override fun onBindViewHolder(holder: QranViewHolder, position: Int) {
        val data = datalist.get(position)
        holder.suraname!!.setText(data)
        holder.quran_progress?.isVisible = false

        if (suranameonclick != null) {
            holder.suraname?.setOnClickListener {
                suranameonclick?.suraonclick(
                    position,
                    data,
                    holder.quran_play!!,
                    holder.quran_progress!!
                )

            }
        }
        if (quran_playclick != null) {
            holder.quran_play?.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    quran_playclick?.suraonclick(
                        position,
                        data,
                        holder.quran_play!!,
                        holder.quran_progress!!
                    )

                }


            })

        }
    }

    var suranameonclick: Suranameclicklistener? = null
    var quran_playclick: Suranameclicklistener? = null

    interface Suranameclicklistener {
        fun suraonclick(
            pos: Int,
            suraname: String,
            quranPlay: ImageView,
            quranProgress: ProgressBar
        )
    }

}