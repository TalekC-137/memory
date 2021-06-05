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
import kotlinx.coroutines.*
import java.lang.Exception
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    var playerChoosen = listOf<Int>()
    var liczbaBloczkow = 3
    var points = 0;
    var doLosowani = 16;
    var mistakes = 0
    var pickedGood = 0
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
        findViewById(R.id.btn_fake) // I added the fake because the list starts from index of 0 and iterating through them (i  in guziki.indecies)
                                    //  starts from i = 1 so to show the block i need to chose as (i-1) and the 16th block would never be shown
                                    // even if generated because i would stop at 16 and 16-1 = 15. i know i could just do (i in 0..17) but then it will not scale
                                    // if i decide to add more blocks later (now it's at 16 only)
    )}

     var generatedBlocks : MutableList<Int> = mutableListOf()


    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            zamienNaPodstawe()

        button_akt.setOnClickListener(){

                losowanko(liczbaBloczkow)
                kolorowanie(generatedBlocks)

                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, generatedBlocks)
                    lista.adapter = adapter
        }

        btn_submit.setOnClickListener(){


            if(isEqual(playerChoosen, generatedBlocks)){

                Toast.makeText(this, "giiit", Toast.LENGTH_LONG).show()


            }else{
                Toast.makeText(this, "nie git", Toast.LENGTH_LONG).show()
            }



        }
    }

     fun ClickMuch(view: View){

        when(view.id){
            R.id.button ->   pickedBlocks(1)
            R.id.button2 ->  pickedBlocks(2)
            R.id.button3 ->  pickedBlocks(3)
            R.id.button4 ->  pickedBlocks(4)
            R.id.button5->   pickedBlocks(5)
            R.id.button6->   pickedBlocks(6)
            R.id.button7->   pickedBlocks(7)
            R.id.button8->   pickedBlocks(8)
            R.id.button9->   pickedBlocks(9)
            R.id.button10->  pickedBlocks(10)
            R.id.button11->  pickedBlocks(11)
            R.id.button12->  pickedBlocks(12)
            R.id.button13->  pickedBlocks(13)
            R.id.button14->  pickedBlocks(14)
            R.id.button15->  pickedBlocks(15)
            R.id.button16->  pickedBlocks(16)
        }
     }

    fun kolorowanie(bloczki: List<Int>) {


        for(i in guziki.indices) {
            if(bloczki.contains(i)) { guziki[i-1].setBackgroundResource(R.drawable.blue)}
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
                guziki[i].setBackgroundResource(R.drawable.white)
            }
    }

// n is equal to number of blocks to generate
    fun losowanko(n: Int) {
    var pozostałe = bloczki1.toMutableList()
    val wylosowane: MutableList<Int> = mutableListOf()

    for (i in 1..n) {
        try {
            var index = random(pozostałe.size)
            wylosowane += pozostałe[index]
            pozostałe.removeAt(index)
         //   val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, wylosowane)
        //    lista.adapter = adapter

        }catch (e: Exception){

        }
    }
    generatedBlocks = wylosowane
}

    inline fun<reified T> isEqual(first: List<T>, second: List<T>): Boolean {
        if (first.size != second.size) {
            return false
        }
        return first.toTypedArray() contentEquals second.toTypedArray()
    }

    fun pickedBlocks(block: Int){

        for(i in guziki.indices){
            if(i == block){
                guziki[i-1].setBackgroundResource(R.drawable.blue)

                //if player chose a block that wasn't correct
               if(!generatedBlocks.contains(block)){
                   CoroutineScope(Dispatchers.IO).launch {
                       delay(TimeUnit.MILLISECONDS.toMillis(100))
                       withContext(Dispatchers.Main) {
                           // this is called after 3 secs
                           guziki[i-1].setBackgroundResource(R.drawable.white)
                       }
                   }

                   Toast.makeText(this, "nah boi", Toast.LENGTH_SHORT).show()

                   // after 3 mistakes the game ends
                   mistakes++
                   if(mistakes>=3){
                       Toast.makeText(this, "you lost", Toast.LENGTH_LONG).show()
                       mistakes = 0

               }
                 //  playerChoosen+=block
               }else if(generatedBlocks.contains(block)){
                    pickedGood++
                   playerChoosen+=block

                  if(generatedBlocks.size == pickedGood){

                      Toast.makeText(this, "you won", Toast.LENGTH_LONG).show()
                      liczbaBloczkow++     // number of block to show in the next round increase by one

                      generatedBlocks.clear()
                      mistakes = 0
                      pickedGood = 0

                        zamienNaPodstawe()

                      CoroutineScope(Dispatchers.IO).launch {
                          delay(TimeUnit.MILLISECONDS.toMillis(300))
                          withContext(Dispatchers.Main) {
                              // this is called after 3 secs

                              losowanko(liczbaBloczkow)
                              kolorowanie(generatedBlocks)

                          }
                      }



                      val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, generatedBlocks)
                      lista.adapter = adapter


                  }

               }
            }
        }


    }



}



