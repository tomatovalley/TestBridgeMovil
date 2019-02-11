package com.jorgesoto.testbridge


import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_informacion_proyecto.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import org.json.JSONArray
import org.json.JSONObject


class Dashboard : AppCompatActivity() {

    var tkn:TokenTB? = null
    var url = "http://178.128.152.204:8080/api-projects/projectsUser/"
    var layoutManager: RecyclerView.LayoutManager? = null
    var adaptador:AdaptadorPersonalizado? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        textView.setText(UserModel.username)

        tkn = TokenTB(this)
        rvListaProyectos.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this,OrientationHelper.HORIZONTAL,false)
        rvListaProyectos.layoutManager = layoutManager

        val queue = Volley.newRequestQueue(this@Dashboard)
        val request: StringRequest = object : StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> {
                    response ->
                Log.d("Respuesta",response)
                val gosn = Gson()
                val pt = gosn.fromJson(response,TesterProyecto::class.java)

                //Enviar datos al fragment
                val fragmentPrincipal = InformacionProyectoFragment.datosProyecto(
                    pt.User.Project.get(0).id.toString(),
                    pt.User.Project.get(0).project,
                    pt.User.Project.get(0).type,
                    pt.User.Project.get(0).location,
                    pt.User.Project.get(0).features,
                    pt.User.Project.get(0).payPerBug.toString()
                )
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.contenedorFragmento,fragmentPrincipal)
                fragmentTransaction.commit()
                adaptador = AdaptadorPersonalizado(this, pt.User.Project,object :ClickListener {
                    override fun onClick(vista: View, position: Int) {
                        //Toast.makeText(applicationContext, pt.User.Project.get(position).id.toString(),Toast.LENGTH_SHORT).show()
                        //Cambiar el fragmento con informacion de la tarjeta del proyecto
                        val fragmentInfoNueva = InformacionProyectoFragment.datosProyecto(
                            pt.User.Project.get(position).id.toString(),
                            pt.User.Project.get(position).project,
                            pt.User.Project.get(position).type,
                            pt.User.Project.get(position).location,
                            pt.User.Project.get(position).features,
                            pt.User.Project.get(position).payPerBug.toString()
                        )
                        val fragmentManager = supportFragmentManager
                        val fragmentTransaction = fragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.contenedorFragmento,fragmentInfoNueva)
                        fragmentTransaction.commit()
                    }

                })
                rvListaProyectos.adapter = adaptador
            }, Response.ErrorListener {
                alert("Algo salio mal Dashboard") {
                    title = "Alert"
                    yesButton { toast("No!!!") }
                    noButton { }
                }.show()
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): MutableMap<String, String>? {
                var params: MutableMap<String, String>? = HashMap()
                params?.put("Authorization", "Token "+tkn?.obtenerToken()!!)
                return params
            }
        }
        queue.add(request)

        btnAbrirRegistroDispositivo.setOnClickListener {
            val intent = Intent(this,RegistroDispositivo::class.java)
            startActivity(intent)
        }

    }
}
