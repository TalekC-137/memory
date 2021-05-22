package com.scollon.markk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sequence.*
import java.util.*
import kotlin.concurrent.schedule

class sequence : AppCompatActivity() {


    private val guziki by lazy { arrayOf(
        findViewById<Button>(R.id.button21),  findViewById(R.id.button22),
        findViewById(R.id.button23),  findViewById(R.id.button24),
        findViewById(R.id.button25),  findViewById(R.id.button26),
        findViewById(R.id.button27),  findViewById(R.id.button28),
        findViewById(R.id.button29)
    )}

    var seq = listOf<Int>();



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sequence)

zamienNaPodstawe()

    buttons.visibility = View.GONE;




        btn_start.setOnClickListener(){
            buttons.visibility = View.VISIBLE
          //  btn_start.visibility = View.GONE


            seq+= 2
            seq+=random()
            kolorowanie(seq)
           // Toast.makeText(this, seq[0].toString(), Toast.LENGTH_LONG).show()

        }




    }
    fun random():Int{
        val rand1 = (0..9).random()
        return rand1
    }

    fun zamienNaPodstawe(){

        for(i in guziki.indices){
            guziki[i].setBackgroundResource(R.drawable.kafelek)
        }
    }


    fun wybieranie(view: View){

        when(view.id){

            R.id.button21 -> TODO()
            R.id.button22 -> TODO()
            R.id.button23 -> TODO()
            R.id.button24 -> TODO()
            R.id.button25 -> TODO()
            R.id.button26 -> TODO()
            R.id.button27 -> TODO()
            R.id.button28 -> TODO()
            R.id.button29 -> TODO()


        }




        for(i in guziki.indices){
            guziki[i].setBackgroundResource(R.drawable.kafelek)
        }
    }


    fun kolorowanie(seq: List<Int>) {


        for(i in guziki.indices) { // 1,2,3,4,5,6,7,8,9


            for (j in seq.indices){ ///1, 4, 8

                if(i == seq[j]){

                    guziki[i].setBackgroundResource(R.drawable.kafelek2)

                    Timer("waiting", false).schedule(2000) {
                        zamienNaPodstawe()
                        }


               }

            }

        }

    }


}