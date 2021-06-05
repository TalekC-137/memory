package com.scollon.markk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sequence.*
import kotlinx.android.synthetic.main.activity_sequence.lista
import kotlinx.coroutines.*
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule

class sequence : AppCompatActivity() {


    private val bloczki by lazy { arrayOf(
        findViewById<Button>(R.id.btn1), findViewById(R.id.btn2),
        findViewById(R.id.btn3),findViewById(R.id.btn4),
        findViewById(R.id.btn5),findViewById(R.id.btn6),
        findViewById(R.id.btn7),findViewById(R.id.btn8),
        findViewById(R.id.btn9),
    ) }
    var activationSpeed: Long = 500
    var seq_pos = 0
    var rounds = 1
    var seq = listOf<Int>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sequence)

        basicColor()
        clickableFalse()

        for(i in 0..100){
            seq += (1..9).random()
        }

        btn_run.setOnClickListener(){
            seqShow()
            btn_run.visibility  = View.GONE
        }

        btn_again.setOnClickListener(){
            finish()
            startActivity(getIntent())
            overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);
        }

        btn_save.setOnClickListener(){
            addRecord()
        }


        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, seq)
        lista.adapter = adapter

    }

    fun klik(view: View){
        activationSpeed = 250
        when(view.id){

            R.id.btn1 -> getButton(1)
            R.id.btn2 -> getButton(2)
            R.id.btn3 -> getButton(3)
            R.id.btn4 -> getButton(4)
            R.id.btn5 -> getButton(5)
            R.id.btn6 -> getButton(6)
            R.id.btn7 -> getButton(7)
            R.id.btn8 -> getButton(8)
            R.id.btn9 -> getButton(9)

        }

    }

    fun getButton(btn_pushed: Int){

//player got it right
        if (seq[seq_pos] == btn_pushed) {
            seq_pos++
            btnShow(btn_pushed)

            if(seq_pos == rounds){
                //player got the whole sequence right
                //    Toast.makeText(this, "brawo", Toast.LENGTH_LONG).show()
                seq_pos =0
                rounds++ // one more block will be shown in the next round
                seqShow()
            }

        } else {
            // player made a mistake
              Toast.makeText(this, "better luck next time", Toast.LENGTH_LONG).show()
            var beatenScore = rounds-1
            tv_score.text = beatenScore.toString()
            cimno.visibility = View.VISIBLE
            end_game.visibility = View.VISIBLE
            highScore()
            lastRecord()

        }



    }

    fun seqShow(){
        activationSpeed = 500
        clickableFalse()

        tv_rounds.text = (rounds-1).toString()
        CoroutineScope(Dispatchers.IO).launch    {
            for (i in 0..rounds-1){   // it only shows the sequence to the set number of round that is currently being played
                delay(TimeUnit.SECONDS.toMillis(1))
                withContext(Dispatchers.Main) {
                    // this is called after 1 sec
                    btnShow(seq[i])
                    if(i == rounds-1){
                        CoroutineScope(Dispatchers.IO).launch    {
                            delay(TimeUnit.MILLISECONDS.toMillis(500))
                            withContext(Dispatchers.Main) {
                                // this is called after 3 secs
                                clickableTrue()
                            }
                        }
                    }
                }
            }
        }
    }

    fun btnShow(btn: Int){

        // iterates through all blocks to see which should be shown
        for (i in bloczki.indices){
            if(i==btn-1) {
                CoroutineScope(Dispatchers.IO).launch {
                    delay(TimeUnit.MILLISECONDS.toMillis(activationSpeed))
                    withContext(Dispatchers.Main) {
                        // this is called after 3 secs
                        bloczki[i].setBackgroundResource(R.drawable.blue);

                    }
                }
                bloczki[i].setBackgroundResource(R.drawable.white);

            }
        }

    }
    private fun addRecord() {
        val score = tv_score.text.toString()
        val scoreInt = Integer.parseInt(score)
        val databaseHandler3: DatabaseHandler3 = DatabaseHandler3(this)
        if (!score.isEmpty()) {
            val status =
                databaseHandler3.addEmployee(RecordModel(0, scoreInt))
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
        val databaseHandler3: DatabaseHandler3 = DatabaseHandler3(this)

            // if says "bmi" because i copied it from my old code and i'm too lazy to change that
           // AND since I'm the only one who will be working with this code I don't necessarily need to
        if(databaseHandler3.getBmiCount() != 0){

            var RecordNum = databaseHandler3.getBmiCount()

            var modelik: RecordModel? = databaseHandler3.getOne(RecordNum)

            var a:Int = modelik?.score ?: 0

            tv_lastScoreView.text = a.toString()

        }


    }


    private fun highScore(){
        val databaseHandler3: DatabaseHandler3 = DatabaseHandler3(this)
        if(databaseHandler3.getBmiCount() != 0){

            var rekord = databaseHandler3.getBiggestInTheColumn()
            tv_HighscoreView.text = rekord.toString()
        }

    }

    fun basicColor(){

        for(i in bloczki.indices) {
            bloczki[i].setBackgroundResource(R.drawable.blue)
        }
    }
    fun clickableFalse(){

        for(i in bloczki.indices) {
            bloczki[i].isClickable = false
        }
    }
    fun clickableTrue(){
        for(i in bloczki.indices) {
            bloczki[i].isClickable = true
        }
    }



}