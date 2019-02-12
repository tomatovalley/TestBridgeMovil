package com.jorgesoto.testbridge

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_registro_dispositivo.*
import org.json.JSONObject

class RegistroDispositivo : AppCompatActivity() {



    var tkn:TokenTB? = null
    var url = "http://178.128.152.204:8080/api-devices/devices/"
    val jsonObjs = JSONObject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_dispositivo)

        tkn = TokenTB(this)

        btnRegistrarDispositivo.setOnClickListener {

                    //Crear nuevo dispositivo
                    jsonObjs.put("device",txtRegistroNombreDispositivo.text.toString())
                    jsonObjs.put("category",txtRegistroCategoria.text.toString())
                    jsonObjs.put("os",txtRegistroSistemaOperativo.text.toString())
                    jsonObjs.put("version",txtRegistroVersionSistemaOperativo.text.toString())

                   val queue = Volley.newRequestQueue(this@RegistroDispositivo)
                    val req: JsonObjectRequest = object : JsonObjectRequest(
                        Request.Method.POST, url, jsonObjs,
                        Response.Listener {
                                response ->
                            //Terminamos la actividad y regresamos al dashboard
                            Toast.makeText(this,"Dispositivo agregado",Toast.LENGTH_LONG).show()
                            finish()
                        }, Response.ErrorListener {

                        }) {
                        @Throws(AuthFailureError::class)
                        override fun getHeaders(): MutableMap<String, String>? {
                            var params: MutableMap<String, String>? = HashMap()
                            params?.put("Authorization", "Token "+tkn?.obtenerToken()!!)
                            return params
                        }
                    }
                    queue.add(req)

                }

        }
    }

