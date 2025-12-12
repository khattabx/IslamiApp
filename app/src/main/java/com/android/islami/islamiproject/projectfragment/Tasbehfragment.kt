package com.android.islami.islamiproject.projectfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.android.islami.R

class Tasbehfragment:Fragment() {
    lateinit var buttonnumbertasbeh: Button
    lateinit var buttonTasbeh: Button
    lateinit var sebha: ImageView
    val Doaa = arrayListOf(
        "سبحان الله",
        "الحمدالله",
        "لا اله الا الله",
        "الله أكبر",
        "استغفر الله العظيم",
        "اللهم صل وسلم علي سيدنا محمد"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tasbehfragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonnumbertasbeh = view.findViewById(R.id.numberoftasbeh)
        sebha = view.findViewById(R.id.sebha)
        buttonTasbeh = view.findViewById(R.id.doaa)
        var num = 0
        var i=1
      sebha.setOnClickListener {
          val clk_rotate = AnimationUtils.loadAnimation(view.context, R.anim.sebha_rotat)

          // assigning that animation to
          // the image and start animation
          sebha.startAnimation(clk_rotate)
          num++
           buttonnumbertasbeh.setText(num.toString())
           if (num == 34) {
               num = 0
               buttonnumbertasbeh.setText("0")
               buttonTasbeh.setText(Doaa[i])
               i++
               if(i==6)i=0
           }
       }

    }
}