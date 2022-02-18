package com.demo.viewmodellivedatadatabinding

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var list: MutableList<String> = mutableListOf<String>()
    var count = 0



    val currentQuotes = MutableLiveData("")



    // initializing data
    fun listAdd(){

        for (i in 0..15){
            list.add("name: "+i)

        }
    }

    //increate count value and whenever increase we set value to out live data
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

    // call for setting initial value
    fun onLoading(){
        currentQuotes.value = list.get(0)
    }

    // decrease a value for pre data and call
    fun preQuotes() {
        if (count == 0) {
            count = list.size-1
            currentQuotes.value = list[count]
        } else {
            --count
            currentQuotes.value = list[count]

        }
    }

    fun addingData(){

    }
}