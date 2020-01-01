package com.example.evob

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu__utama.*

class Menu_Utama : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu__utama)

        val bundle = intent.extras
        val id_pengguna = bundle?.get("id_daftar").toString()

        b_profil.setOnClickListener(){
            intent = Intent(this, Akun::class.java)
            intent.putExtra("id_daftar",id_pengguna)
            startActivity(intent)
        }
            b_app.setOnClickListener(){
                intent = Intent(this, Event::class.java)
                intent.putExtra("id_daftar",id_pengguna)
                startActivity(intent)
            }

                b_event.setOnClickListener(){
                    intent = Intent(this, Histori::class.java)
                    intent.putExtra("id_daftar",id_pengguna)
                    startActivity(intent)
                }
    }
}