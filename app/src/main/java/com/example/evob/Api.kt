package com.example.evob

class Api {


    companion object {

        private val SERVER = "http://192.168.43.151/evob/"
        val CREATE  = SERVER + "createdaftar.php"
        val READ1   = SERVER + "readlogin.php"
        val READ2   = SERVER + "read2.php"
        val UPDATE   = SERVER + "update.php"
        val READ3   = SERVER + "readevent.php"
        val READ4   = SERVER + "readpesan.php"
        val CREATE2   = SERVER + "createevent.php"
        val READ5   = SERVER + "readaku.php"

    }
}