package com.android.islami.islamiproject.projectfragment

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.android.islami.R
import com.android.islami.islamiproject.Api.BuildRetrofit
import com.android.islami.islamiproject.Api.Constant
import com.android.islami.islamiproject.Api.RadioResponse
import com.android.islami.islamiproject.Api.RadiosItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class Radiofragment:Fragment() {

    lateinit var radio_text: TextView
    lateinit var buttonStop: ImageButton
    lateinit var buttonnext: ImageButton
    lateinit var buttonprev: ImageButton
    lateinit var progressBar: ProgressBar
    var radio_item: List<RadiosItem?>? = null
    var position = 0
    var radio_mediaPlayer = MediaPlayer()
    var is_play = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.radiofragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        progressBar.isVisible = false

    }

    fun init() {

        get_radio_api()

        radio_text = requireView().findViewById(R.id.ezaa)
        progressBar = requireView().findViewById(R.id.progress)
        buttonStop = requireView().findViewById(R.id.play)
        buttonnext = requireView().findViewById(R.id.next)
        buttonprev = requireView().findViewById(R.id.prev)

        if (!isNetworkConnected() && !(radio_mediaPlayer.isPlaying)) progressBar.isVisible = true


        buttonStop.setOnClickListener {
            if (isNetworkConnected()) {

                radio_text.text = radio_item?.get(position)?.name

                if (is_play == 0) {

                    play_sound(position)
                    buttonStop.setImageResource(R.drawable.ic_pause_radio)
                    is_play = 1
                } else {

                    progressBar.isVisible = false
                    radio_mediaPlayer.stop()
                    buttonStop.setImageResource(R.drawable.ic_play_radio)
                    is_play = 0

                }
            } else {
                progressBar.isVisible = true
            }

        }
        buttonnext.setOnClickListener {
            if (isNetworkConnected()) {
                position++

                radio_text.text = radio_item?.get(position)?.name

                if (radio_mediaPlayer.isPlaying) {
                    radio_mediaPlayer.stop()

                }
                play_sound(position)
            }
        }
       buttonprev.setOnClickListener {
           if (isNetworkConnected()) {
               position--

               //first audio
               if (position < 0) {
                   radio_mediaPlayer.stop()
               } else {

                   if (radio_mediaPlayer.isPlaying) radio_mediaPlayer.stop()
                   radio_text.text = radio_item?.get(position)?.name
                   play_sound(position)
               }
           }
        }

    }

    fun get_radio_api(){

        BuildRetrofit.get_api(Constant.radio_base_url).get_radio()
            .enqueue(object : Callback<RadioResponse> {
                override fun onResponse(
                    call: Call<RadioResponse>,
                    response: Response<RadioResponse>
                ) {
                    radio_item = response.body()?.radios
                    // Log.e("onResponse: ", response.body()?.radios.toString())
                }

                override fun onFailure(call: Call<RadioResponse>, t: Throwable) {


                }

        })
    }

    fun play_sound(position: Int) {
        progressBar.isVisible = true
        val url = radio_item?.get(position)?.uRL
        radio_mediaPlayer = MediaPlayer().apply {
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
        radio_mediaPlayer.setOnPreparedListener(object : MediaPlayer.OnPreparedListener {
            override fun onPrepared(mp: MediaPlayer?) {
                progressBar.isVisible = false
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