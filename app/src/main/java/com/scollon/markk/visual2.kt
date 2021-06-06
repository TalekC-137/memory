package com.scollon.markk

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.lista
import kotlinx.android.synthetic.main.activity_visual2.*
import kotlinx.coroutines.*
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule

class visual2 : AppCompatActivity() {
    var test = 0
    var points = 0
    var playerChoosen = listOf<Int>()
    var liczbaBloczkow = 3
    var mistakes = 0
    var pickedGood = 0
    var bloczki1 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36)
    private val guziki by lazy { arrayOf(
        findViewById<Button>(R.id.button1), findViewById(R.id.button2),
        findViewById(R.id.button3), findViewById(R.id.button4),
        findViewById(R.id.button5), findViewById(R.id.button6),
        findViewById(R.id.button7), findViewById(R.id.button8),
        findViewById(R.id.button9), findViewById(R.id.button10),
        findViewById(R.id.button11), findViewById(R.id.button12),
        findViewById(R.id.button13), findViewById(R.id.button14),
        findViewById(R.id.button15), findViewById(R.id.button16),
        findViewById(R.id.button17), findViewById(R.id.button18),
        findViewById(R.id.button19), findViewById(R.id.button20),
        findViewById(R.id.button21), findViewById(R.id.button22),
        findViewById(R.id.button23), findViewById(R.id.button24),
        findViewById(R.id.button25), findViewById(R.id.button26),
        findViewById(R.id.button27), findViewById(R.id.button28),
        findViewById(R.id.button29), findViewById(R.id.button30),
        findViewById(R.id.button31), findViewById(R.id.button32),
        findViewById(R.id.button33), findViewById(R.id.button34),
        findViewById(R.id.button35), findViewById(R.id.button36),
        findViewById(R.id.btn_fake)
        // I added the fake because the list starts from index of 0 and iterating through them (i  in guziki.indecies)
        //  starts from i = 1 so to show the block i need to chose as (i-1) and the 16th block would never be shown
        // even if generated because i would stop at 16 and 16-1 = 15. i know i could just do (i in 0..17) but then it will not scale
        // if i decide to add more blocks later (now it's at 16 only)
    )}

    var generatedBlocks : MutableList<Int> = mutableListOf()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visual2)
        zamienNaPodstawe()

        val pkt:Int = intent.getIntExtra("points",0)
        val bloczki:Int = intent.getIntExtra("liczbaBloczkow",0)
        liczbaBloczkow = bloczki

        tv_points2.text = pkt.toString()

        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.MILLISECONDS.toMillis(600))
            withContext(Dispatchers.Main) {
                // this is called after 300 milis
                losowanko(liczbaBloczkow)
                kolorowanie(generatedBlocks)

            }
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, generatedBlocks)
        lista.adapter = adapter
     /*
        button_akt.setOnClickListener(){

            tv_points.text = points.toString() //aka 0 hah
            losowanko(liczbaBloczkow)
            kolorowanie(generatedBlocks)
            button_akt.visibility = View.GONE
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, generatedBlocks)
            lista.adapter = adapter
        }

      */


    }

    fun ClickMuch(view: View){

        for (i in guziki.indices) {

            if(view.id == guziki[i].id){
                pickedBlocks(i+1)
            }
        }

    }

    fun kolorowanie(bloczki: List<Int>) {


        for(i in guziki.indices) {
            if(bloczki.contains(i)) { guziki[i - 1].setBackgroundResource(R.drawable.white)}
        }
        Timer("waiting", false).schedule(1750) {

            zamienNaPodstawe()
        }
    }

    fun random(liczba: Int):Int{
        val rand1 = (0..liczba).random()
        return rand1
    }
    fun zamienNaPodstawe(){

        for(i in guziki.indices){
            guziki[i].setBackgroundResource(R.drawable.blue)
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

    inline fun <reified T> isEqual(first: List<T>, second: List<T>): Boolean {
        if (first.size != second.size) {
            return false
        }
        return first.toTypedArray() contentEquals second.toTypedArray()
    }

    fun pickedBlocks(block: Int){

        if(test == 0){
            points = Integer.parseInt(tv_points2.text.toString())
            test++
        }

        for(i in guziki.indices){
            if(i == block){
                guziki[i - 1].setBackgroundResource(R.drawable.white)
                //if player chose a block that wasn't correct
                if(!generatedBlocks.contains(block)){
                    CoroutineScope(Dispatchers.IO).launch {
                        delay(TimeUnit.MILLISECONDS.toMillis(100))
                        withContext(Dispatchers.Main) {
                            // this is called after 3 secs
                            guziki[i - 1].setBackgroundResource(R.drawable.blue)
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

                        CoroutineScope(Dispatchers.IO).launch {
                            delay(TimeUnit.MILLISECONDS.toMillis(300))
                            withContext(Dispatchers.Main) {
                                // this is called after 3 secs
                                zamienNaPodstawe()
                            }
                        }
                        Toast.makeText(this, "you won", Toast.LENGTH_LONG).show()
                        liczbaBloczkow++     // number of block to show in the next round increase by one
                        points++
                        generatedBlocks.clear()
                        mistakes = 0
                        pickedGood = 0
                        tv_points2.text = points.toString()


                        CoroutineScope(Dispatchers.IO).launch {
                            delay(TimeUnit.MILLISECONDS.toMillis(600))
                            withContext(Dispatchers.Main) {
                                // this is called after 300 milis

                                losowanko(liczbaBloczkow)
                                kolorowanie(generatedBlocks)

                            }
                        }

                        val adapter = ArrayAdapter(
                            this,
                            android.R.layout.simple_list_item_1,
                            generatedBlocks
                        )
                        lista.adapter = adapter

                    }
                }
            }
        }
    }
}

