package com.jorgesoto.testbridge


import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_informacion_proyecto.view.*



class InformacionProyectoFragment : Fragment() {


    companion object {
        fun datosProyecto(idProy:String, nomProye:String, tipProye:String, codigoProy:String, carctProye:String, pagBugProye:String):InformacionProyectoFragment {
            val fragment = InformacionProyectoFragment()
            val infoProyecto = Bundle()
            infoProyecto.putString("idProyecto",idProy)
            infoProyecto.putString("nombreProyecto",nomProye)
            infoProyecto.putString("tipoProyecto",tipProye)
            infoProyecto.putString("codigoProyecto",codigoProy)
            infoProyecto.putString("caracteristicasProyecto",carctProye)
            infoProyecto.putString("pagoBugProyecto",pagBugProye)
            fragment.arguments = infoProyecto
            return  fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_informacion_proyecto, container, false)
        //Por medio del value view podemos acceder a los elementos/vistas del fragmento
        val args = arguments
        view.tvIdProyecto.text = args?.getString("idProyecto","")
        view.tvNombreProyecto.text = args?.getString("nombreProyecto","")
        view.tvTipoProyecto.text = args?.getString("tipoProyecto","")
        view.tvEnlaceProyecto.text = args?.getString("codigoProyecto","")
        view.tvCaracteristicasProyecto.text = args?.getString("caracteristicasProyecto","")
        view.tvPagoPorBug.text = args?.getString("pagoBugProyecto","")
        view.btnAceptarProyecto.setOnClickListener {
            //AGREGAR QUE CAMBIE DE FRAGMENT DINAMICAMENTE
            val fragmentTransaction = fragmentManager?.beginTransaction()
            val fragmentoNuevo = RegistroBugFragmento()
            fragmentTransaction?.replace(R.id.contenedorFragmento,fragmentoNuevo)
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()

        }
        return view
    }


}
