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
import kotlinx.android.synthetic.main.activity_pesan_event.*
import org.json.JSONObject

class PesanEvent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesan_event)
        TampilPesanan()

        pmpesan.setOnClickListener() {
            create2()
        }
    }

//************************************************************************************************//

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        val bundle = intent.extras
        val id_pengguna = bundle?.get("id_daftar").toString()
        val a = bundle?.get("idevent").toString()


        intent = Intent(this, Event::class.java)
        intent.putExtra("id_daftar",id_pengguna)
        intent.putExtra("idevent",a)
        startActivity(intent)
        return true
    }

////************************************************************************************************//

    private fun TampilPesanan(){
        val loading = ProgressDialog(this)
        loading.setMessage("Memuat data...")
        loading.show()

        val bundle = intent.extras
        val a = bundle?.get("idevent").toString()

        AndroidNetworking.post(Api.READ4)//ganti api
            .addBodyParameter("idevent",a)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {

                    val jsonArray = response?.optJSONArray("result")
                    loading.dismiss()

                    for(i in 0 until jsonArray?.length()!!) {
                        val jsonObject = jsonArray?.optJSONObject(i)
                        val eventt = jsonObject.getString("namaevent").toString()
                        val tempatt = jsonObject.getString("tempat").toString()
                        val waktuu =jsonObject.getString("hari").toString()
                        val edevent =jsonObject.getString("idevent").toString()

                        if (jsonArray?.length()-1 == i){
                            loading.dismiss()
                            txtev.text = eventt
                            txttmp.text = tempatt
                            txtwkt.text = waktuu
                            iddeventtt.text = edevent
                        }
                    }
                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()
                }
            })
    }

//************************************************************************************************//

    private fun create2(){
        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan data...")
        loading.show()
        val bundle = intent.extras
        val id_pengguna = bundle?.get("id_daftar").toString()
        val a = bundle?.get("idevent").toString()

        AndroidNetworking.post(Api.CREATE2)//gantii api
            .addBodyParameter("id_daftar",id_pengguna)
            .addBodyParameter("idevent",a)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    loading.dismiss()
                    Toast.makeText(applicationContext,"terpesan", Toast.LENGTH_SHORT).show()
                }
                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()
                }
            })
    }
}