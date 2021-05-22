package com.scollon.markk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_glowna.*

class Glowna : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glowna)

        btn_vis.setOnClickListener(){

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)

        }
        btn_seq.setOnClickListener(){

            val i = Intent(this, sequence::class.java)
            startActivity(i)
        }

    }
}