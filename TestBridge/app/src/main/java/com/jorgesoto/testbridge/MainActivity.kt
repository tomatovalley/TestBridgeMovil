package com.jorgesoto.testbridge

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)

//Remove notification bar
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

//set content view AFTER ABOVE sequence (to avoid crash)
        this.setContentView(R.layout.activity_main)

        val background = object :Thread() {
            override fun run() {
                super.run()
                try {
                    //Esperamos 3 segundos antes de mostrar la actividad del login
                    Thread.sleep(3000)
                    val intent = Intent(baseContext,Login::class.java)
                    startActivity(intent)
                } catch (e:Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()

    }


}
