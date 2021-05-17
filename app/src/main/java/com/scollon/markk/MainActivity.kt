package com.scollon.markk

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    var playerChoosen = listOf<Int>()
    var liczbaBloczkow = 3
    var points = 0; var kliknięcia = 1; var limit = 0;
    var doLosowani = 16;
    var bloczki1 = listOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16)
    private val guziki by lazy { arrayOf(
        findViewById<Button>(R.id.button),  findViewById(R.id.button2),
        findViewById(R.id.button3),  findViewById(R.id.button4),
        findViewById(R.id.button5),  findViewById(R.id.button6),
        findViewById(R.id.button7),  findViewById(R.id.button8),
        findViewById(R.id.button9),  findViewById(R.id.button10),
        findViewById(R.id.button11),  findViewById(R.id.button12),
        findViewById(R.id.button13),  findViewById(R.id.button14),
        findViewById(R.id.button15),  findViewById(R.id.button16),
    )}

     var generatedBlocks = listOf<Int>()


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
              //  var rolledBlocks = losowanko(liczbaBloczkow)
                kolorowanie(generatedBlocks)

                liczbaBloczkow++

                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, generatedBlocks)
                    lista.adapter = adapter
            }else {
                Toast.makeText(this, "można losować tylko raz", Toast.LENGTH_LONG).show()
            }
        }
        btn_submit.setOnClickListener(){





        }

        // once every 100 ms the arrays are comprared to determine wheter the user checked boxes are the same as the generated ones
var ir = 1
        val handler: Handler = Handler()
        val run = object : Runnable {
            override fun run() {
                val message: String = ir.toString()


                handler.postDelayed(this, 100)  // delay 2 sec
                // do shit here

              //  Toast.makeText(this@MainActivity,message,Toast.LENGTH_SHORT).show()
        //     tekst.setText(message)

                ir++
            }

        }
        handler.post(run)
    }


// HOW TF DO I LOOP THAT SHIT!?!?!!?!?
     fun ClickMuch(view: View){
        when(view.id){
                    R.id.button  ->  {
                        if(generatedBlocks.contains(1)){
                        button.setBackgroundResource(R.drawable.kafelek2)
                    playerChoosen += 1}else{}   }
                    R.id.button2 ->  {
                        if(generatedBlocks.contains(2)){
                        button2.setBackgroundResource(R.drawable.kafelek2)
                        playerChoosen += 2}else{} }
                    R.id.button3 ->  {
                        if(generatedBlocks.contains(3)){
                        button3.setBackgroundResource(R.drawable.kafelek2)
                        playerChoosen += 3 }else {}}
                    R.id.button4 ->  {
                        if(generatedBlocks.contains(4)){
                        button4.setBackgroundResource(R.drawable.kafelek2)
                        playerChoosen += 4 }else {}}
                    R.id.button5 ->   {
                        if(generatedBlocks.contains(5)){
                        button5.setBackgroundResource(R.drawable.kafelek2)
                        playerChoosen += 5 }else{}}
                    R.id.button6 ->   {
                        if(generatedBlocks.contains(6)){
                        button6.setBackgroundResource(R.drawable.kafelek2)
                        playerChoosen += 6 }else{}}
                    R.id.button7 ->   {
                        if(generatedBlocks.contains(7)){
                        button7.setBackgroundResource(R.drawable.kafelek2)
                        playerChoosen += 7 }else{}}
                    R.id.button8 ->   {
                        if(generatedBlocks.contains(8)){
                        button8.setBackgroundResource(R.drawable.kafelek2)
                        playerChoosen += 8 }else{}}
                    R.id.button9 ->   {
                        if(generatedBlocks.contains(9)){
                        button9.setBackgroundResource(R.drawable.kafelek2)
                        playerChoosen += 9 }else{}}
                    R.id.button10->  {
                        if(generatedBlocks.contains(10)){
                        button10.setBackgroundResource(R.drawable.kafelek2)
                        playerChoosen += 10 }else{}}
                    R.id.button11->  {
                        if(generatedBlocks.contains(11)){
                        button11.setBackgroundResource(R.drawable.kafelek2)
                        playerChoosen += 11 }else{}}
                    R.id.button12->  {
                        if(generatedBlocks.contains(12)){
                        button12.setBackgroundResource(R.drawable.kafelek2)
                        playerChoosen += 12 }else{}}
                    R.id.button13->  {
                        if(generatedBlocks.contains(13)){
                        button13.setBackgroundResource(R.drawable.kafelek2)
                        playerChoosen += 13 }else{}}
                    R.id.button14->  {
                        if(generatedBlocks.contains(14)){
                        button14.setBackgroundResource(R.drawable.kafelek2)
                        playerChoosen += 14 }else{}}
                    R.id.button15->  {
                        if(generatedBlocks.contains(15)){
                        button15.setBackgroundResource(R.drawable.kafelek2)
                        playerChoosen += 15 }else{}}
                    R.id.button16->  {
                        if(generatedBlocks.contains(16)){
                        button16.setBackgroundResource(R.drawable.kafelek2)
                        playerChoosen += 16 }else{}}

        }


       }

    fun kolorowanie(bloczki: List<Int>) {


        for(i in guziki.indices) {
            if(bloczki.contains(i)) { guziki[i-1].setBackgroundResource(R.drawable.kafelek2)}
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
    fun losowanko(n: Int) {
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
    generatedBlocks = wylosowane
}






}



