package com.jorgesoto.testbridge

data class Info(
    val total: Int,
    val page: Int,
    val data: ArrayList<Data>
)

data class Data(
    val id: Int,
    val name: String,
    val year: Int
)