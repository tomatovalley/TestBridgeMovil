package com.jorgesoto.testbridge

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import org.json.JSONObject



class Login : AppCompatActivity() {

    var tkn:TokenTB? = null
    var url = "http://178.128.152.204:8080/api-auth/login/"
    val jsonObjs = JSONObject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tkn = TokenTB(this)

        btnEntrar.setOnClickListener {


            //Autenticacion
            jsonObjs.put("username",etUsuario.text.toString())
            jsonObjs.put("email","")
            jsonObjs.put("password",etContrasena.text.toString())

            /*
            * Usuario cliente
            * User: ray
            * Pass: nomelose
            * Usuario tester
            * User: tester
            * Pass: pruebas123
            * */

            val que = Volley.newRequestQueue(this@Login)
            val req = JsonObjectRequest(Request.Method.POST,url,jsonObjs,
                Response.Listener {
                    response ->

                    val token = response.get("key")
                    Log.d("ACCESS_TOKEN",token.toString())
                        tkn?.guardarToken(token.toString())
                        // Guardar el usuario
                        UserModel.username = etUsuario.text.toString()
                        startActivity(Intent(this,Dashboard::class.java))
                        finish() //Terminamos esta actividad y ya no podemos regresar a ella


                }, Response.ErrorListener {
                    alert("Algo salio mal Login") {
                        title = "Alert"
                        yesButton { toast("No!!!") }
                        noButton { }
                    }.show()
                })
            que.add(req)
        }
    }
}
