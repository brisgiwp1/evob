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
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        id_login.setOnClickListener(){
        CekLogin()
        }

    }

    private fun CekLogin(){
        val loading = ProgressDialog(this)
        loading.setMessage("Memeriksa Data...")
        loading.show()

        val user = euser.getText().toString()
        val pass = epass.getText().toString()

        AndroidNetworking.post(Api.READ1)
            .addBodyParameter("email",user)
            .addBodyParameter("password",pass)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?){

                    val jsonArray = response?.optJSONArray("result")
                    loading.dismiss()

                    for(i in 0 until jsonArray?.length()!!) {
                        val jsonObject = jsonArray?.optJSONObject(i)
                        val id_pengguna = jsonObject.getString("id_daftar").toString()

                        if (jsonArray?.length()-1 == i) {
                            id_ambil.setText(id_pengguna)
                            val id_kirim = id_ambil.text
                            intent = Intent(this@Login, Menu_Utama::class.java)
                            intent.putExtra("id_daftar", id_kirim)
                            startActivity(intent)
                        }
                    }
                    if(id_ambil.getText() == "0"){
                        Toast.makeText(this@Login, "Gagal Login", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(anError: ANError?){
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
