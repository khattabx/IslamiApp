package com.android.islami.islamiproject.projectfragment

import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.islami.Constent
import com.android.islami.R
import com.android.islami.islamiproject.Api.BuildRetrofit
import com.android.islami.islamiproject.Api.Constant
import com.android.islami.islamiproject.Api.DataItem
import com.android.islami.islamiproject.Api.QuranResponse
import com.android.islami.islamiproject.main.SuraDetailes
import com.android.islami.islamiproject.recyclerview.QranAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class QuranFragment: Fragment() {
    var quran_mediaPlayer = MediaPlayer()
    lateinit var recyclerview: RecyclerView
    lateinit var quranadapter: QranAdapter
    var quran_list: List<DataItem?>? = null
    var is_play = 0

    val datalist = arrayListOf(
        "الفاتحه",
        "البقرة",
        "آل عمران",
        "النساء",
        "المائدة",
        "الأنعام",
        "الأعراف",
        "الأنفال",
        "التوبة",
        "يونس",
        "هود",
        "يوسف",
        "الرعد",
        "إبراهيم",
        "الحجر",
        "النحل",
        "الإسراء",
        "الكهف",
        "مريم",
        "طه",
        "الأنبياء",
        "الحج",
        "المؤمنون",
        "النّور",
        "الفرقان",
        "الشعراء",
        "النّمل",
        "القصص",
        "العنكبوت",
        "الرّوم",
        "لقمان",
        "السجدة",
        "الأحزاب",
        "سبأ",
        "فاطر",
        "يس",
        "الصافات",
        "ص",
        "الزمر",
        "غافر",
        "فصّلت",
        "الشورى",
        "الزخرف",
        "الدّخان",
        "الجاثية",
        "الأحقاف",
        "محمد",
        "الفتح",
        "الحجرات",
        "ق",
        "الذاريات",
        "الطور",
        "النجم",
        "القمر",
        "الرحمن",
        "الواقعة",
        "الحديد",
        "المجادلة",
        "الحشر",
        "الممتحنة",
        "الصف",
        "الجمعة",
        "المنافقون",
        "التغابن",
        "الطلاق",
        "التحريم",
        "الملك",
        "القلم",
        "الحاقة",
        "المعارج",
        "نوح",
        "الجن",
        "المزّمّل",
        "المدّثر",
        "القيامة",
        "الإنسان",
        "المرسلات",
        "النبأ",
        "النازعات",
        "عبس",
        "التكوير",
        "الإنفطار",
        "المطفّفين",
        "الإنشقاق",
        "البروج",
        "الطارق",
        "الأعلى",
        "الغاشية",
        "الفجر",
        "البلد",
        "الشمس",
        "الليل",
        "الضحى",
        "الشرح",
        "التين",
        "العلق",
        "القدر",
        "البينة",
        "الزلزلة",
        "العاديات",
        "القارعة",
        "التكاثر",
        "العصر",
        "الهمزة",
        "الفيل",
        "قريش",
        "الماعون",
        "الكوثر",
        "الكافرون",
        "النصر",
        "المسد",
        "الإخلاص",
        "الفلق",
        "الناس"
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.quranfragment, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()


    }

    fun init() {

        //get quran audio api
        get_quran_audio()

        recyclerview = requireView().findViewById(R.id.quranrecycler)
        quranadapter = QranAdapter(datalist)


        //call back when click on sura_name
        quranadapter.suranameonclick = object : QranAdapter.Suranameclicklistener {
            override fun suraonclick(
                pos: Int,
                suraname: String,
                quranPlay: ImageView,
                quranProgress: ProgressBar
            ) {
                suradetailes(pos, suraname)
            }
        }
        recyclerview.adapter = quranadapter


        // to transfer to sura details that is verses
        quranadapter.suranameonclick = object : QranAdapter.Suranameclicklistener {
            override fun suraonclick(
                pos: Int,
                suraname: String,
                quranPlay: ImageView,
                quranProgress: ProgressBar
            ) {
                suradetailes(pos, suraname)
            }
        }


        // open quran audio
        quranadapter.quran_playclick = object : QranAdapter.Suranameclicklistener {
            override fun suraonclick(
                pos: Int,
                suraname: String,
                quranPlay: ImageView,
                quranProgress: ProgressBar
            ) {
                if (isNetworkConnected()) {
                    if (quran_mediaPlayer.isPlaying || is_play == 1) {
                        quranProgress.isVisible = false
                        quranPlay.setImageResource(R.drawable.ic_play_radio)
                        quran_mediaPlayer.stop()
                        is_play = 0

                    } else {
                        quranPlay.setImageResource(R.drawable.ic_pause_radio)
                        quran_sound(pos, quranProgress)
                        is_play = 1
                    }
                } else {
                    quranProgress.isVisible = true

                }
            }

        }
    }

    fun suradetailes(pos: Int, suraname: String) {
        val intent = Intent(context, SuraDetailes::class.java)
        intent.putExtra(Constent.name, suraname)
        intent.putExtra(Constent.pos, pos)
        startActivity(intent)
    }

    fun get_quran_audio() {
        BuildRetrofit.get_api(Constant.quran_base_url).get_quran_audio()
            .enqueue(object : Callback<QuranResponse> {
                override fun onResponse(
                    call: Call<QuranResponse>,
                    response: Response<QuranResponse>
                ) {
                    quran_list = response.body()?.data
                }

                override fun onFailure(call: Call<QuranResponse>, t: Throwable) {
                }

            })
    }

    fun quran_sound(position: Int, quranProgress: ProgressBar) {
        quranProgress.isVisible = true
        val url = quran_list?.get(position)?.recitation?.full
        quran_mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            try {
                setDataSource(url.toString())
                prepareAsync() // might take long! (for buffering, etc)
            } catch (e: IOException) {
                e.printStackTrace()
            }


        }
        quran_mediaPlayer.setOnPreparedListener(object : MediaPlayer.OnPreparedListener {
            override fun onPrepared(mp: MediaPlayer?) {
                quranProgress.isVisible = false
                mp?.start()
            }

        })
    }

    fun isNetworkConnected(): Boolean {
        val wifi_manager =
            requireActivity().applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifi_is_enabled = wifi_manager.isWifiEnabled

        if (!wifi_is_enabled) {
            Toast.makeText(context, "للاسف ليس لديك اتصال بالانترنت", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }


}