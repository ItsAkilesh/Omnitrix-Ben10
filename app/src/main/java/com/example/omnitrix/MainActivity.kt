package com.example.omnitrix

import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Vibrator
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import androidx.core.view.InputDeviceCompat
import androidx.core.view.MotionEventCompat
import androidx.core.view.ViewConfigurationCompat
import com.example.omnitrix.databinding.ActivityMainBinding







class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding
    private val aliens = ArrayList<Int>()
    private var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        aliens.add(R.drawable.greenface)
        aliens.add(R.drawable.fourarms)
        aliens.add(R.drawable.ripjaws)
        aliens.add(R.drawable.greymatter)
        aliens.add(R.drawable.stinkfly)
        aliens.add(R.drawable.wildmutt)
        aliens.add(R.drawable.heatblast)
        aliens.add(R.drawable.diamondhead)
        aliens.add(R.drawable.cannonbolt)
        aliens.add(R.drawable.xlr8)
        aliens.add(R.drawable.ghostfreak)
        aliens.add(R.drawable.upgrade)

        var mp = MediaPlayer.create(this, R.raw.startup);

        var dialView: View = findViewById(R.id.dial)
        dialView.requestFocus()
        dialView.setOnGenericMotionListener { v, ev ->
            if (ev.action == MotionEvent.ACTION_SCROLL &&
                ev.isFromSource(InputDeviceCompat.SOURCE_ROTARY_ENCODER)
            ) {
                // Don't forget the negation here
                val context:Context = dialView.context
                val delta = -ev.getAxisValue(MotionEventCompat.AXIS_SCROLL) *
                        ViewConfigurationCompat.getScaledVerticalScrollFactor(
                            ViewConfiguration.get(context), context
                        )
                // Swap these axes to scroll horizontally instead
//                v.scrollBy(0, delta.roundToInt())
                if(delta > 0)
                    nextImage()
                if( delta < 0)
                    prevImage()
                false
            } else {
                false
            }
        }
        mp.start()
    }

    fun nextImage(){
        var mp = MediaPlayer.create(this, R.raw.click);
        var img = findViewById<ImageView>(R.id.dial)
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        i = (i +1)% aliens.size
        mp.start()
        img.setImageResource(aliens.get(i))
        vibrator.vibrate(200)
    }
    fun prevImage(){
        var mp = MediaPlayer.create(this, R.raw.click);
        var img = findViewById<ImageView>(R.id.dial)
        val vibrator = applicationContext?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        i = if (i <= 0 ) aliens.size - 1 else i -1
        mp.start()
        img.setImageResource(aliens.get(i))
        vibrator.vibrate(200)

    }
    fun onClick(view :View ){
        var img = findViewById<ImageView>(R.id.dial)
        var mp = MediaPlayer.create(this, R.raw.blast);
        val vibrator = applicationContext?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(500)
        val alphaAnimation = AlphaAnimation(0.0f, 1.0f)
        alphaAnimation.duration = 400
        alphaAnimation.repeatCount = 1
        alphaAnimation.repeatMode = Animation.ABSOLUTE
        img.startAnimation(alphaAnimation)
        mp.start()

    }



}
