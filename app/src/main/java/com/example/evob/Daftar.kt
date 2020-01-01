package com.example.evob

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.RenderScript
import android.util.Log
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_daftar.*
import org.json.JSONObject

class Daftar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)

        id_daftar.setOnClickListener(){
            OnDaftar()
            intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

    private fun OnDaftar(){

        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan data...")
        loading.show()

        val nnama = bnml.getText().toString()
        val tttl = bttl.getText().toString()
        val aalamat = balamat.getText().toString()
        val emmail = bemail.getText().toString()
        val ppass = bpas.getText().toString()

        AndroidNetworking.post(Api.CREATE)
            .addBodyParameter("nama",nnama)
            .addBodyParameter("ttl",tttl)
            .addBodyParameter("alamat",aalamat)
            .addBodyParameter("email",emmail)
            .addBodyParameter("password",ppass)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    Toast.makeText(applicationContext,"Sukses Terdaftar", Toast.LENGTH_SHORT).show()
                }
                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()
                }
            })
}}
