package com.scollon.markk

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
var kliknięcia = 1


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
        button_akt.setOnClickListener(){
           // losują się bloczki powstaje ich pseudo animacja
            if(kliknięcia ==1){
                losowanie()



                kliknięcia++
            }else {
                Toast.makeText(this, "można losować tylko raz", Toast.LENGTH_LONG).show()
            }
        }
        btn_submit.setOnClickListener(){
                wynik()

        }



    }
    var ClickedBtns = listOf<Int>()
     fun ClickMuch(view: View){

        when(view.id){
            R.id.button -> {button.setBackgroundResource(R.drawable.kafelek2)
                ClickedBtns +=1  }
            R.id.button2 ->  {button2.setBackgroundResource(R.drawable.kafelek2)
                ClickedBtns +=2}
            R.id.button3 -> {button3.setBackgroundResource(R.drawable.kafelek2)
                ClickedBtns +=3}
                    R.id.button4 ->  {button4.setBackgroundResource(R.drawable.kafelek2)
                        ClickedBtns +=4}
                    R.id.button5->  {button5.setBackgroundResource(R.drawable.kafelek2)
                        ClickedBtns +=5}
                    R.id.button6->   {button6.setBackgroundResource(R.drawable.kafelek2)
                        ClickedBtns +=6}
                    R.id.button7->   {button7.setBackgroundResource(R.drawable.kafelek2)
                        ClickedBtns +=7}
                    R.id.button8->   {button8.setBackgroundResource(R.drawable.kafelek2)
                        ClickedBtns +=8  }
                    R.id.button9->   {button9.setBackgroundResource(R.drawable.kafelek2)
                        ClickedBtns +=9}
                    R.id.button10->  {button10.setBackgroundResource(R.drawable.kafelek2)
                        ClickedBtns +=10}
                    R.id.button11-> { button11.setBackgroundResource(R.drawable.kafelek2)
                        ClickedBtns +=11}
                    R.id.button12->  {button12.setBackgroundResource(R.drawable.kafelek2)
                        ClickedBtns +=12}
                    R.id.button13->  {button13.setBackgroundResource(R.drawable.kafelek2)
                        ClickedBtns +=13}
                    R.id.button14->  {button14.setBackgroundResource(R.drawable.kafelek2)
                            ClickedBtns +=14}
                    R.id.button15->  {button15.setBackgroundResource(R.drawable.kafelek2)
                        ClickedBtns +=15}
                    R.id.button16->  {button16.setBackgroundResource(R.drawable.kafelek2)
                        ClickedBtns +=16}

        }


       }

//losowanie 4 kafelków
    // jebać optymalizację, w wakację zamknę to w jakiegoś fajnego loopa żeby nie wyglądało jak cegła


   var choosen1 = 0; var choosen2 = 0; var choosen3 = 0; var choosen4 =0

    fun losowanie() {

       val rand1 = random()
     val rand2 = random()
        if (rand2 == rand1){
            losowanie()
        }else{
            val rand3 = random()
            if(rand3 == rand1 || rand3 == rand2){
                losowanie()
            }else{
                val rand4 = random()
                if(rand4 == rand1 || rand4 == rand2|| rand4 == rand3){
                    losowanie()
                }else{

                    if(rand1 == 1 || rand2 == 1 || rand3 == 1||  rand4 == 1) { button.setBackgroundResource(R.drawable.kafelek2) }
                    if(rand1 == 2 || rand2 == 2 || rand3 == 2||  rand4 == 2) { button2.setBackgroundResource(R.drawable.kafelek2) }
                    if(rand1 == 3 || rand2 == 3 || rand3 == 3||  rand4 == 3) { button3.setBackgroundResource(R.drawable.kafelek2) }
                    if(rand1 == 4 || rand2 == 4 || rand3 == 4||  rand4 == 4) { button4.setBackgroundResource(R.drawable.kafelek2) }
                    if(rand1 == 5 || rand2 == 5 || rand3 == 5||  rand4 == 5) { button5.setBackgroundResource(R.drawable.kafelek2) }
                    if(rand1 == 6 || rand2 == 6 || rand3 == 6||  rand4 == 6) { button6.setBackgroundResource(R.drawable.kafelek2) }
                    if(rand1 == 7 || rand2 == 7 || rand3 == 7||  rand4 == 7) { button7.setBackgroundResource(R.drawable.kafelek2) }
                    if(rand1 == 8 || rand2 == 8 || rand3 == 8||  rand4 == 8) { button8.setBackgroundResource(R.drawable.kafelek2) }
                    if(rand1 == 9 || rand2 == 9 || rand3 == 9||  rand4 == 9) { button9.setBackgroundResource(R.drawable.kafelek2) }
                    if(rand1 == 10 || rand2 == 10 || rand3 == 10||  rand4 == 10) { button10.setBackgroundResource(R.drawable.kafelek2) }
                    if(rand1 == 11 || rand2 == 11 || rand3 == 11||  rand4 == 11) { button11.setBackgroundResource(R.drawable.kafelek2) }
                    if(rand1 == 12 || rand2 == 12 || rand3 == 12||  rand4 == 12) { button12.setBackgroundResource(R.drawable.kafelek2) }
                    if(rand1 == 13 || rand2 == 13 || rand3 == 13||  rand4 == 13) { button13.setBackgroundResource(R.drawable.kafelek2) }
                    if(rand1 == 14 || rand2 == 14 || rand3 == 14||  rand4 == 14) { button14.setBackgroundResource(R.drawable.kafelek2) }
                    if(rand1 == 15 || rand2 == 15 || rand3 == 15||  rand4 == 15) { button15.setBackgroundResource(R.drawable.kafelek2) }
                    if(rand1 == 16 || rand2 == 16 || rand3 == 16||  rand4 == 16) { button16.setBackgroundResource(R.drawable.kafelek2) }
                        //czeka na zniknięcie
                    Timer("waiting", false).schedule(3000) {

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
                    choosen1 = rand1
                    choosen2 = rand2
                    choosen3 = rand3
                    choosen4 = rand4




                }
            }



        }


    }
fun random():Int{
    val rand1 = (0..16).random()
    return rand1
}


fun wynik(){
    if(ClickedBtns.contains(choosen1) && ClickedBtns.contains(choosen2) && ClickedBtns.contains(choosen3) && ClickedBtns.contains(choosen4)){
        Toast.makeText(this, "brawo", Toast.LENGTH_LONG).show()

    }else{
        Toast.makeText(this, "źle", Toast.LENGTH_LONG).show()
    }

}


}


