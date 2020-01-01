package com.example.evob

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_akun.*
import kotlinx.android.synthetic.main.activity_daftar.*
import org.json.JSONObject

class Akun : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_akun)

        Daftar()

        aupdate.setOnClickListener() {
            update()
        }


        val bundle = intent.extras
        val id_pengguna = bundle?.get("id_daftar").toString()

        aback.setOnClickListener() {
            intent = Intent(this, Menu_Utama::class.java)
            intent.putExtra("id_daftar", id_pengguna)
            startActivity(intent)
        }
    }

    private fun Daftar(){

        val loading = ProgressDialog(this)
        loading.setMessage("Memuat data...")
        loading.show()

        val bundle = intent.extras
        val id_pengguna = bundle?.get("id_daftar").toString()

        AndroidNetworking.post(Api.READ2)
            .addBodyParameter("id_daftar",id_pengguna)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {

                    val jsonArray = response?.optJSONArray("result")
                    loading.dismiss()

                    val jsonObject = jsonArray?.optJSONObject(0)
                    val id_daftar = jsonObject?.getString("id_daftar").toString()
                    val nama = jsonObject?.getString("nama").toString()
                    val ttl = jsonObject?.getString("ttl").toString()
                    val alamat = jsonObject?.getString("alamat").toString()
                    val email= jsonObject?.getString("email").toString()
                    val password = jsonObject?.getString("password").toString()

                    loading.dismiss()
                    aid.setText(id_daftar)
                    anama.setText(nama)
                    attl.setText(ttl)
                    aalamat.setText(alamat)
                    aemail.setText(email)
                    apass.setText(password)
                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun update(){
        val loading = ProgressDialog(this)
        loading.setMessage("Mengupdate data...")
        loading.show()

        val id_daftarr = aid.getText().toString()
        val namaa = anama.getText().toString()
        val ttll = attl.getText().toString()
        val alamatt= aalamat.getText().toString()
        val emaill= aemail.getText().toString()
        val passworddd = apass.getText().toString()

        AndroidNetworking.post(Api.UPDATE)
            .addBodyParameter("id_daftar",id_daftarr)
            .addBodyParameter("nama",namaa)
            .addBodyParameter("ttl",ttll)
            .addBodyParameter("alamat",alamatt)
            .addBodyParameter("email",emaill)
            .addBodyParameter("password",passworddd)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    loading.dismiss()
                    Toast.makeText(applicationContext,"sukses", Toast.LENGTH_SHORT).show()
                }
                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        val bundle = intent.extras
        val id_pengguna = bundle?.get("id_daftar").toString()

        intent = Intent(this, Menu_Utama::class.java)
        intent.putExtra("id_daftar",id_pengguna)
        startActivity(intent)
        return true
    }
}