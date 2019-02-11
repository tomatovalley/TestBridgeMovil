package com.jorgesoto.testbridge


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_registro_dispositivo.view.*
import org.json.JSONObject


class RegistroBugFragmento : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //Por medio del value view podemos acceder a los elementos/vistas del fragmento
        val view = inflater.inflate(R.layout.fragment_registro_bug_fragmento, container, false)
        return view
    }


}
