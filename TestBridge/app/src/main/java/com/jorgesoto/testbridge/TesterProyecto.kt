package com.jorgesoto.testbridge

data class TesterProyecto(val User: User)

data class User(val username:String, val Project:ArrayList<Project>)

data class Project(val status:String,val features:String,val project:String, val location: String,val payPerBug:Double, val type: String, val id:Int )
