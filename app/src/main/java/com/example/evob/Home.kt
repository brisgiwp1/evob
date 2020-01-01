package com.example.evob

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        id_log.setOnClickListener(){
            intent = Intent (this, Login::class.java)
            startActivity(intent)
        }

        id_daf.setOnClickListener(){
            intent = Intent(this, Daftar::class.java)
            startActivity(intent)
    }
  }
}
