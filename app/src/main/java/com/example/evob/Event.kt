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
import com.example.evob.adaper.AdapterEvent
import kotlinx.android.synthetic.main.activity_event.*
import org.json.JSONObject

class Event : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        carievent()
    }
    override fun onResume(){
        super.onResume()
        tampilevent()
    }

//*************************************************************************************************//

    private fun carievent(){
        listviewevent.setOnItemClickListener{adapterView, view, position, id ->

            val idnya = adapterView.getItemAtPosition(position)
            val a = idnya.toString()
            val bundle = intent.extras
            val id_penumpang = bundle?.get("id_daftar").toString()

            intent = Intent(this, PesanEvent::class.java)
            intent.putExtra("idevent",a)
            intent.putExtra("id_daftar",id_penumpang)
            startActivity(intent)
        }
    }
    private fun tampilevent(){
        val loading = ProgressDialog(this)
        loading.setMessage("Memuat data...")
        loading.show()

        AndroidNetworking.post(Api.READ3)//ganti read event
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {

                    val jsonArray = response?.optJSONArray("result")
                    loading.dismiss()

                    val list_a = arrayListOf<String>()
                    val list_b = arrayListOf<String>()
                    val list_c = arrayListOf<String>()
                    val list_d = arrayListOf<String>()

                    for(i in 0 until jsonArray?.length()!!) {
                        val jsonObject = jsonArray?.optJSONObject(i)
                        list_a.add((jsonObject.getString("namaevent")).toString())
                        list_b.add((jsonObject.getString("tempat")).toString())
                        list_c.add((jsonObject.getString("hari")).toString())
                        list_d.add((jsonObject.getString("idevent")).toString())

                        if (jsonArray?.length()-1 == i) {
                            loading.dismiss()
                            val adapter = AdapterEvent(this@Event, list_d, list_a, list_b, list_c)
                            listviewevent.adapter = adapter
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
}