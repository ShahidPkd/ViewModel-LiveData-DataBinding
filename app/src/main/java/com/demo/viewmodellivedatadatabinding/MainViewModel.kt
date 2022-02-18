package com.demo.viewmodellivedatadatabinding

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var list: MutableList<String> = mutableListOf<String>()
    var count = 0



    val currentQuotes = MutableLiveData("")

    fun listAdd(){
//        list.add("hi")
        for (i in 0..15){
            list.add("name: "+i)

        }
    }
    fun updateQuotes(){
        if (list.size-1 == count){
            count = 0
            currentQuotes.value = list.get(count)
        }else{

            ++count

            currentQuotes.value = list.get(count)
//            ++count

        }

    }
    fun onLoading(){
        currentQuotes.value = list.get(0)
    }

    fun preQuotes() {
        if (count == 0) {
            count = list.size-1
            currentQuotes.value = list[count]
        } else {
            --count
            currentQuotes.value = list[count]

        }
    }
}