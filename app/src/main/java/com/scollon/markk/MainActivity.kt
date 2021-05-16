package com.scollon.markk

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    var liczbaBloczkow = 3
    var points = 0; var kliknięcia = 1; var limit = 0;
    var doLosowani = 16;
    var bloczki1 = listOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16)

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
                kolorowanie((losowanko(liczbaBloczkow)))
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




    fun kolorowanie( bloczki: List<Int>) {


        if(bloczki.contains(1)){ button.setBackgroundResource(R.drawable.kafelek2) }
        if(bloczki.contains(2)){ button2.setBackgroundResource(R.drawable.kafelek2) }
        if(bloczki.contains(3)){ button3.setBackgroundResource(R.drawable.kafelek2) }
        if(bloczki.contains(4)){ button4.setBackgroundResource(R.drawable.kafelek2) }
        if(bloczki.contains(5)){ button5.setBackgroundResource(R.drawable.kafelek2) }
        if(bloczki.contains(6)){ button6.setBackgroundResource(R.drawable.kafelek2) }
        if(bloczki.contains(7)){ button7.setBackgroundResource(R.drawable.kafelek2) }
        if(bloczki.contains(8)){ button8.setBackgroundResource(R.drawable.kafelek2) }
        if(bloczki.contains(9)){ button9.setBackgroundResource(R.drawable.kafelek2) }
        if(bloczki.contains(10)){ button10.setBackgroundResource(R.drawable.kafelek2) }
        if(bloczki.contains(11)){ button11.setBackgroundResource(R.drawable.kafelek2) }
        if(bloczki.contains(12)){ button12.setBackgroundResource(R.drawable.kafelek2) }
        if(bloczki.contains(13)){ button13.setBackgroundResource(R.drawable.kafelek2) }
        if(bloczki.contains(14)){ button14.setBackgroundResource(R.drawable.kafelek2) }
        if(bloczki.contains(15)){ button15.setBackgroundResource(R.drawable.kafelek2) }
        if(bloczki.contains(16)){ button16.setBackgroundResource(R.drawable.kafelek2) }

        Timer("waiting", false).schedule(2000) {

            zamienNaPodstawe()
        }
    }
fun random(liczba: Int):Int{
    val rand1 = (0..liczba).random()
    return rand1
}

    fun zamienNaPodstawe(){
        button.setBackgroundResource(R.drawable.kafelek)
        button2.setBackgroundResource(R.drawable.kafelek)
        button3.setBackgroundResource(R.drawable.kafelek)
        button4.setBackgroundResource(R.drawable.kafelek)
        button5.setBackgroundResource(R.drawable.kafelek)
        button6.setBackgroundResource(R.drawable.kafelek)
        button7.setBackgroundResource(R.drawable.kafelek)
        button8.setBackgroundResource(R.drawable.kafelek)
        button9.setBackgroundResource(R.drawable.kafelek)
        button10.setBackgroundResource(R.drawable.kafelek)
        button11.setBackgroundResource(R.drawable.kafelek)
        button12.setBackgroundResource(R.drawable.kafelek)
        button13.setBackgroundResource(R.drawable.kafelek)
        button14.setBackgroundResource(R.drawable.kafelek)
        button15.setBackgroundResource(R.drawable.kafelek)
        button16.setBackgroundResource(R.drawable.kafelek)

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



