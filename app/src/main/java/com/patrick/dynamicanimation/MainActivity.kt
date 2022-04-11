package com.patrick.dynamicanimation

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.patrick.dynamicanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var moveX = 0f
    var moveY = 0f

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
    }

    lateinit var xAnimation: SpringAnimation
    lateinit var yAnimation: SpringAnimation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        xAnimation = SpringAnimation(binding.fab, SpringAnimation.X, binding.fab.x)
        yAnimation = SpringAnimation(binding.fab, SpringAnimation.Y, binding.fab.y)

        binding.fab.setOnTouchListener { v, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    moveX = v.x - event.rawX
                    moveY = v.y - event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    v.animate().x(event.rawX + moveX).y(event.rawY + moveY).setDuration(0).start()
                }
                MotionEvent.ACTION_UP -> {
                    xAnimation.start()
                    yAnimation.start()
                }
            }
            true
        }
        startSpringAnimation(binding.fab)
    }

    private fun startSpringAnimation(view: View) {
        // create an animation for your view and set the property you want to animate
        val animation1 = SpringAnimation(view, SpringAnimation.X, 400f)
        val animation2 = SpringAnimation(view, SpringAnimation.Y, 400f)

        val spring = SpringForce().apply {
            finalPosition = view.x
            stiffness = SpringForce.STIFFNESS_VERY_LOW
            dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
        }
        animation1.spring = spring
        animation1.start()
        animation2.spring = spring
        animation2.start()
    }
}