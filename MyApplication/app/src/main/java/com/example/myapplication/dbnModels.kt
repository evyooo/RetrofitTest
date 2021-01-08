package com.example.myapplication

object dbnModels {
    data class Result(
        val result: Int,
        val userinfo: ArrayList<USERINFO>
    )

    data class USERINFO(
        val animaltestflag: Int,
        val countarray: String,
        val userpw: String
    )
}