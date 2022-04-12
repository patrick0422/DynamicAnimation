package com.patrick.dynamicanimation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.patrick.dynamicanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var moveX = 0f
    var moveY = 0f

    private val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val animation = SpringAnimation(binding.fab, SpringAnimation.TRANSLATION_X)
        val xAnimation = SpringAnimation(binding.fab2, SpringAnimation.TRANSLATION_X)
        val yAnimation = SpringAnimation(binding.fab2, SpringAnimation.TRANSLATION_Y)

        binding.fab.setOnTouchListener { v, event ->
            v.performClick()
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    moveX = v.x - event.rawX
                }
                MotionEvent.ACTION_MOVE -> {
                    v.animate().x(event.rawX + moveX).setDuration(0).start()
                }
                MotionEvent.ACTION_UP -> {
                    animation.animateToFinalPosition(0F)
                }
            }
            true
        }
        binding.fab2.setOnTouchListener { v, event ->
            v.performClick()
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    moveX = v.x - event.rawX
                    moveY = v.y - event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    v.animate().x(event.rawX + moveX).y(event.rawY + moveY).setDuration(0).start()
                }
                MotionEvent.ACTION_UP -> {
                    xAnimation.animateToFinalPosition(0F)
                    yAnimation.animateToFinalPosition(0F)
                }
            }
            true
        }
    }
}