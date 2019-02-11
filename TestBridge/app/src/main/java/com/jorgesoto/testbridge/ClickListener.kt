package com.jorgesoto.testbridge


import android.view.View

interface ClickListener {
    //Recibe el view al que se le hizo click
    //y que posicion tiene en el recyclerview
    fun onClick(vista:View, position:Int)
}