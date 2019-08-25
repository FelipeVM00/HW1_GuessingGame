package com.example.hw1_guessinggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_number_guess.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    /*
    Variable que almacena los intentos del usuario
     */
    var attempts: Int = 0

    /*
    Variable para manejar los intentos del usuario en pantalla
     */
    var attemptsText: TextView? = null;
    /*
    Variable que almacena el numero a adivinar
     */
    var numberToGuess: Int = Random.nextInt(1, 1000)

    /*
    Variable para manejar el numero del usuario en pantalla
     */
    var userNumberText: TextView? = null

    /*
    Variable para manejar los digitos presionados en el teclado en pantalla
     */
    var digit: Int = 0;

    /*
    Variable para manejar el numero que digita el usuario
     */
    var userNumber: Long = 0;

    /*
    Variable para mostrar pistas
     */
    var hint: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_guess)
        userNumberText = findViewById(R.id.numberText)
        hint = findViewById(R.id.textHint)
        attemptsText = findViewById(R.id.attemptsText)
    }

    /*
    Funcion para manejar los digitos presionados
     */
    fun numberPressed(view: View){
        var b: Button = view as Button;
        digit = b.text.toString().toInt()
        if(userNumberText?.text?.equals("?")!!){
            userNumberText?.textSize = 100.toFloat()
            userNumberText?.text = "0";

        }
        if(userNumber?.times(10)?.plus(digit.toLong()) <= 1000){
            userNumber = userNumberText?.text.toString().toLong()
            userNumber = userNumber?.times(10)?.plus(digit.toLong())
            userNumberText?.text = userNumber.toString()
        }else{
            Toast.makeText(this, "No bigger than 1000", Toast.LENGTH_SHORT).show()
        }
    }

    /*
    Funcion para resetear el juego
     */
    fun clickBtnPlayAgain(view: View){
        numberToGuess = Random.nextInt(1, 1000)
        userNumberText?.textSize = 200.toFloat()
        userNumberText?.text = "?"
        userNumber = 0
        hint?.text = "Hint:"
        attempts = 0
        attemptsText?.text = "Attempts: "
    }

    /*
    Funcion para borrar el ultimo digito
     */
    fun clickErase(view: View){
        if(userNumberText?.length() == 1){
            userNumberText?.textSize = 200.toFloat()
            userNumberText?.text = "?"
        }else{
            userNumberText?.text = userNumberText?.text?.replaceRange(userNumberText?.length()!!.minus(1), userNumberText?.length()!!.toInt(),"")
            userNumber = userNumberText?.text.toString().toLong()
        }

    }

    /*
    Funcion para hacer un intento de adivinar el numero
     */
    fun clickTry(view: View){
        attempts++
        var num: Long? = null
        if(userNumberText?.text.toString() == "?"){
            Toast.makeText(this, "Try first !", Toast.LENGTH_SHORT).show()
        }else{
            attemptsText?.text = "Attempts: " + attempts
            num = userNumberText?.text.toString().toLong()
            when {
                num!! > numberToGuess -> hint?.text = "Hint: It's lower!!"
                num!! < numberToGuess -> hint?.text = "Hint: It's higher!!"
                else -> {
                    hint?.text = "CONGRATS !!"
                    Toast.makeText(this, "YOU WON !!!!!!! WITH " + attempts + " ATTEMPTS", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
