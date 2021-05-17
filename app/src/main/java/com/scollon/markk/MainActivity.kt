package com.scollon.markk

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    var liczbaBloczkow = 3
    var points = 0; var kliknięcia = 1; var limit = 0;
    var doLosowani = 16;
    var bloczki1 = listOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16)
    private val guziki by lazy { arrayOf(
        findViewById<Button>(R.id.button),  findViewById(R.id.button9),
        findViewById(R.id.button2),  findViewById(R.id.button10),
        findViewById(R.id.button3),  findViewById(R.id.button11),
        findViewById(R.id.button4),  findViewById(R.id.button12),
        findViewById(R.id.button5),  findViewById(R.id.button13),
        findViewById(R.id.button6),  findViewById(R.id.button14),
        findViewById(R.id.button7),  findViewById(R.id.button15),
        findViewById(R.id.button8),  findViewById(R.id.button16),
    )}

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


            zamienNaPodstawe()

        button_akt.setOnClickListener(){
           // losują się bloczki powstaje ich pseudo animacja
            if(kliknięcia ==1){
             //   losowanie()
                losowanko(liczbaBloczkow)
                 kliknięcia++
                test(losowanko(liczbaBloczkow))
            //    kolorowanie((losowanko(liczbaBloczkow)))
                liczbaBloczkow++
            }else {
                Toast.makeText(this, "można losować tylko raz", Toast.LENGTH_LONG).show()
            }
        }
        btn_submit.setOnClickListener(){





        }



    }

     fun ClickMuch(view: View){

        when(view.id){
                  R.id.button -> {button.setBackgroundResource(R.drawable.kafelek2) }
                  R.id.button2 ->  {button2.setBackgroundResource(R.drawable.kafelek2) }
                  R.id.button3 -> {button3.setBackgroundResource(R.drawable.kafelek2) }
                  R.id.button4 ->  {button4.setBackgroundResource(R.drawable.kafelek2) }
                    R.id.button5->  {button5.setBackgroundResource(R.drawable.kafelek2) }
                    R.id.button6->   {button6.setBackgroundResource(R.drawable.kafelek2) }
                    R.id.button7->   {button7.setBackgroundResource(R.drawable.kafelek2) }
                    R.id.button8->   {button8.setBackgroundResource(R.drawable.kafelek2) }
                    R.id.button9->   {button9.setBackgroundResource(R.drawable.kafelek2) }
                    R.id.button10->  {button10.setBackgroundResource(R.drawable.kafelek2) }
                    R.id.button11-> { button11.setBackgroundResource(R.drawable.kafelek2) }
                    R.id.button12->  {button12.setBackgroundResource(R.drawable.kafelek2) }
                    R.id.button13->  {button13.setBackgroundResource(R.drawable.kafelek2) }
                    R.id.button14->  {button14.setBackgroundResource(R.drawable.kafelek2) }
                    R.id.button15->  {button15.setBackgroundResource(R.drawable.kafelek2) }
                    R.id.button16->  {button16.setBackgroundResource(R.drawable.kafelek2) }

        }


       }

//losowanie 4 kafelków
    // jebać optymalizację, w wakację zamknę to w jakiegoś fajnego loopa żeby nie wyglądało jak cegła




    fun test( bloczki: List<Int>) {


        for(i in guziki.indices) {
            if(bloczki.contains(i)) { guziki[i].setBackgroundResource(R.drawable.kafelek2)}
        }
        Timer("waiting", false).schedule(2000) {

            zamienNaPodstawe()
        }
    }


fun random(liczba: Int):Int{
    val rand1 = (0..liczba).random()
    return rand1
}



    fun zamienNaPodstawe(){

            for(i in guziki.indices){
                guziki[i].setBackgroundResource(R.drawable.kafelek)
            }
    }

// n equal to number of blocks to choose and equal to (n of rounds +2)
    fun losowanko(n: Int): List<Int> {
    var pozostałe = bloczki1.toMutableList()
    var wylosowane = listOf<Int>()

    for (i in 1..n) {
        try {
            var index = random(pozostałe.size)
            wylosowane += pozostałe[index]
            pozostałe.removeAt(index)
         //   val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, wylosowane)
        //    lista.adapter = adapter

        }catch (e: Exception){
            liczbaBloczkow--
        }
    }
    return wylosowane
}






}



