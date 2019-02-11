package com.jorgesoto.testbridge

import android.support.v7.app.AppCompatActivity

class TokenTB(var activity: AppCompatActivity) {

    private val SETTINGS = "settings"

    init {

    }

    //Guardamos el token en las sharedpreferences
     fun guardarToken(token:String) {
        val settings =  activity.getSharedPreferences(SETTINGS,0)
        val editor = settings.edit()
        editor.putString("accessToken",token)
        editor.commit()
    }

    //Regresamos el token que esta en las sharedpreferences
    fun obtenerToken():String {
        val settings =  activity.getSharedPreferences(SETTINGS,0)
        val token = settings.getString("accessToken","")
        return token
    }

    fun hayToken(): Boolean {
        if (obtenerToken() == "") { return false } else { return true }
    }
}