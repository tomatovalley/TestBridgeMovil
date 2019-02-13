package com.jorgesoto.testbridge

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class AdaptadorPersonalizado(var contexto:Context, items: ArrayList<Project>, var listener:ClickListener): RecyclerView.Adapter<AdaptadorPersonalizado.ViewHolder>() {


    //Almacenar los elementos que se van a mostrar en el ReyclerView
    var proyectos:ArrayList<Project>? = null
    var copiaProyectos:ArrayList<Project>? = null

    init {
        this.proyectos = items //DeepCopy o copia completamente nueva
        this.copiaProyectos = items  //Copia intacta o inmutable
    }
    var viewHolder:ViewHolder? = null

    class ViewHolder(vista:View, listener: ClickListener):RecyclerView.ViewHolder(vista),View.OnClickListener {

        var vista = vista
        var cardView:CardView? = null
        var proyectoID:TextView? = null
        var proyectoNombre:TextView? = null
        var listener:ClickListener? = null


        init {
            proyectoID = vista.findViewById(R.id.tvProyectoID)
            proyectoNombre = vista.findViewById(R.id.tvNombreProyecto)
            cardView =  vista.findViewById(R.id.cvTarjeta)
            this.listener = listener
            vista.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            this.listener?.onClick(v!!,adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorPersonalizado.ViewHolder {
        val vista = LayoutInflater.from(contexto).inflate(R.layout.plantilla_proyectos_tester,parent,false)
        viewHolder = ViewHolder(vista,listener)
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return proyectos?.count()!!
    }

    override fun onBindViewHolder(holder: AdaptadorPersonalizado.ViewHolder, position: Int) {
        val item = proyectos?.get(position)
        holder.proyectoID?.text = item?.id.toString()
        holder.proyectoNombre?.text = item?.project

        //Tarjetas de proyecto con fondo intercalado
        if(position % 2 == 0 ){
            holder?.cardView?.setBackgroundResource(R.drawable.proyecto_tarjeta_uno)
        } else {
            holder?.cardView?.setBackgroundResource(R.drawable.proyecto_tarjeta_dos)
        }

    }

}
