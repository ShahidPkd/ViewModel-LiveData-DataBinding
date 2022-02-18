package com.demo.viewmodellivedatadatabinding

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.demo.viewmodellivedatadatabinding.databinding.ActivityMainBinding
import java.lang.Math.abs

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener  {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    // Declaring gesture detector, swipe threshold, and swipe velocity threshold
    private lateinit var gestureDetector: GestureDetector
    private val swipeThreshold = 100
    private val swipeVelocityThreshold = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.listAdd()
        mainViewModel.onLoading()
        binding.mainViewModelName = mainViewModel
        binding.lifecycleOwner = this



        // this is for swip left and right to change data
        gestureDetector = GestureDetector(this)
    }

    // Override this method to recognize touch event
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (gestureDetector.onTouchEvent(event)) {
            true
        }
        else {
            super.onTouchEvent(event)
        }
    }

    // All the below methods are GestureDetector.OnGestureListener members
    // Except onFling, all must "return false" if Boolean return type
    // and "return" if no return type
    override fun onDown(e: MotionEvent?): Boolean {
        return false
    }

    override fun onShowPress(e: MotionEvent?) {
        return
    }

    override fun onSingleTapUp(e: MotionEvent?) : Boolean {
//        Toast.makeText(applicationContext, "Tap Tap", Toast.LENGTH_SHORT).show()
        return false

    }


    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
//        Toast.makeText(applicationContext, "Scrolled", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
        Toast.makeText(applicationContext, "Long Press", Toast.LENGTH_SHORT).show()
    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        try {
            val diffY = e2.y - e1.y
            val diffX = e2.x - e1.x
            if (abs(diffX) > abs(diffY)) {
                if (abs(diffX) > swipeThreshold && abs(velocityX) > swipeVelocityThreshold) {
                    if (diffX > 0) {
//                        Toast.makeText(applicationContext, "Left to Right swipe gesture", Toast.LENGTH_SHORT).show()
                        mainViewModel.preQuotes()
                    }

                    else {
//                        Toast.makeText(applicationContext, "Right to Left swipe gesture", Toast.LENGTH_SHORT).show()
                        mainViewModel.updateQuotes()
                    }
                }
            }
        }
        catch (exception: Exception) {
            exception.printStackTrace()
        }
        return true
    }
}