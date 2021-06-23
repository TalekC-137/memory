package com.scollon.markk

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_records.*
import kotlinx.android.synthetic.main.activity_sequence.*

class recordsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_records)

        getVisRec()
        getSeqRec()
    }


    private fun getVisRec(){
        val databaseHandlerVisual:DatabaseHandlerVisual = DatabaseHandlerVisual(this)
        if(databaseHandlerVisual.getBmiCount() != 0){

            var rekord = databaseHandlerVisual.getBiggestInTheColumn()
            tv_visualRec.text = rekord.toString()
        }else{
            tv_visualRec.text = "0"
        }


    }
    private fun getSeqRec(){

        val databaseHandler3: DatabaseHandler3 = DatabaseHandler3(this)
        if(databaseHandler3.getBmiCount() != 0) {

            var rekord = databaseHandler3.getBiggestInTheColumn()
            tv_sequenceRec.text = rekord.toString()
        }else{
            tv_sequenceRec.text = "0"
        }
    }

}