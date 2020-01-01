package com.example.evob.adaper

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.evob.Event
import com.example.evob.Histori
import com.example.evob.R

class AdapterEvent (private val context: Event,
                    private val id: ArrayList<String>,
                    private val namm: ArrayList<String>,
                    private val tempp: ArrayList<String>,
                    private val waktt: ArrayList<String>) : ArrayAdapter<String>(context, R.layout.levent, id) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.levent, null, true)

        val idnya = rowView.findViewById(R.id.txt_id) as TextView
        val merkk = rowView.findViewById(R.id.txt_nama) as TextView
        val kapasi = rowView.findViewById(R.id.txt_tempat) as TextView
        val harrr = rowView.findViewById(R.id.txt_waktu) as TextView

        idnya.text = id[position]
        merkk.text = namm[position]
        kapasi.text = tempp[position]
        harrr.text = waktt[position]

        return rowView
    }

    class AdapterHistori (private val context: Histori,
                        private val id: ArrayList<String>,
                        private val namm: ArrayList<String>,
                        private val tempp: ArrayList<String>,
                        private val waktt: ArrayList<String>) : ArrayAdapter<String>(context, R.layout.levent, id) {

        override fun getView(position: Int, view: View?, parent: ViewGroup): View {
            val inflater = context.layoutInflater
            val rowView = inflater.inflate(R.layout.levent, null, true)

            val idnya = rowView.findViewById(R.id.txt_id) as TextView
            val merkk = rowView.findViewById(R.id.txt_nama) as TextView
            val kapasi = rowView.findViewById(R.id.txt_tempat) as TextView
            val harrr = rowView.findViewById(R.id.txt_waktu) as TextView

            idnya.text = id[position]
            merkk.text = namm[position]
            kapasi.text = tempp[position]
            harrr.text = waktt[position]

            return rowView
        }
    }
 }
