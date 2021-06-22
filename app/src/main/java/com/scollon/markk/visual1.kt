package com.scollon.markk

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btn_againMain
import kotlinx.android.synthetic.main.activity_main.btn_saveMain
import kotlinx.android.synthetic.main.activity_visual1.*
import kotlinx.android.synthetic.main.activity_visual2.*
import kotlinx.android.synthetic.main.activity_visual2.lista
import kotlinx.android.synthetic.main.activity_visual2.tv_points2
import kotlinx.coroutines.*
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule

class visual1: AppCompatActivity()  {

    var test = 0
    var points = 0
    var playerChoosen = listOf<Int>()
    var liczbaBloczkow = 3
    var mistakes = 0
    var pickedGood = 0
    var bloczki1 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25)
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
        findViewById(R.id.button25), findViewById(R.id.btn_fake)
        // I added the fake because the list starts from index of 0 and iterating through them (i  in guziki.indecies)
        //  starts from i = 1 so to show the block i need to chose as (i-1) and the 16th block would never be shown
        // even if generated because i would stop at 16 and 16-1 = 15. i know i could just do (i in 0..17) but then it will not scale
        // if i decide to add more blocks later (now it's at 16 only)
    )}

    var generatedBlocks : MutableList<Int> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visual1)
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

        btn_save1.setOnClickListener {

            addOneRecord() //saves the points to the database

        }
        btn_again1.setOnClickListener {
            finish()
          val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);
        }

    }
    override fun onBackPressed() {
        super.onBackPressed()
        var i = Intent(this, Glowna::class.java)
        startActivity(i)
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

                //    Toast.makeText(this, "nah boi", Toast.LENGTH_SHORT).show()

                    // after 3 mistakes the game ends
                    mistakes++
                    if(mistakes>=3){
                        Toast.makeText(this, "you lost", Toast.LENGTH_LONG).show()
                        mistakes = 0
                        endGameScreen() //loads the endgame screen and load the points from the database
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

                                if(liczbaBloczkow>=12){
                                    val i = Intent(baseContext, visual2::class.java)
                                    i.putExtra("points", points)
                                    i.putExtra("liczbaBloczkow", liczbaBloczkow)
                                    startActivity(i)
                                    overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);

                                }else{
                                    losowanko(liczbaBloczkow)
                                    kolorowanie(generatedBlocks)
                                }


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

    private fun addOneRecord(){

        var recordPoints =  tv_points2.text as String
        var intRecordPoints = Integer.parseInt(recordPoints)

        var databaseHandlerVisual = DatabaseHandlerVisual(this)

        if(!recordPoints.isEmpty()){
            val status = databaseHandlerVisual.addEmployee(RecordModel(0,intRecordPoints))

            if (status > -1) {
                Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(
                applicationContext,
                "you messed up fool",
                Toast.LENGTH_LONG
            ).show()
        }


    }

    private fun lastRecord() {
        val databaseHandlerVisual: DatabaseHandlerVisual = DatabaseHandlerVisual(this)

        // if says "bmi" because i copied it from my old code and i'm too lazy to change that
        // AND since I'm the only one who will be working with this code I don't necessarily need to
        if(databaseHandlerVisual.getBmiCount() != 0){

            var RecordNum = databaseHandlerVisual.getBmiCount()

            var modelik: RecordModel? = databaseHandlerVisual.getOne(RecordNum)

            var a:Int = modelik?.score ?: 0

            tv_lastScoreView1.text = a.toString()

        }


    }


    private fun highScore(){
        val databaseHandlerVisual: DatabaseHandlerVisual = DatabaseHandlerVisual(this)
        if(databaseHandlerVisual.getBmiCount() != 0){

            var rekord = databaseHandlerVisual.getBiggestInTheColumn()
            tv_HighscoreView1.text = rekord.toString()
        }

    }

    private fun endGameScreen(){

        end_game1.visibility = View.VISIBLE
        highScore()
        lastRecord()
        tv_score1.text = tv_points2.text.toString()


    }



}