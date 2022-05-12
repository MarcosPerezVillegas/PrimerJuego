package com.example.primerjuego

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.random.Random

class ActivityJuego : AppCompatActivity() {
    lateinit var musicaFondo:MediaPlayer
    lateinit var respuestaJugador:EditText
    lateinit var btnRespuesta:Button
    lateinit var respuestaCorrecta:MediaPlayer
    lateinit var respuestaIncorrecta:MediaPlayer
    var intentos = 0
    var numeroMaquina = 0
    var numeroJugador = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)
        initUI()
        musicaFondo = MediaPlayer.create(this,R.raw.fondo)
        respuestaCorrecta = MediaPlayer.create(this,R.raw.correcto)
        respuestaIncorrecta = MediaPlayer.create(this,R.raw.incorrecto)
        playMusica()

        btnRespuesta.setOnClickListener {

            val respuesta = respuestaJugador.text.toString()
            if (respuesta.equals("")){
                Toast.makeText(this, "Eres deficiente???", Toast.LENGTH_LONG).show()
                sIncorrecto()
            }else{
                numeroJugador = respuesta.toInt()
                if(intentos==0){
                    generarNumero()
                }
                if(numeroJugador>9){
                    Toast.makeText(this, "Efectivamente, es usted deficiente",
                        Toast.LENGTH_LONG).show()
                }
                else{
                    if(numeroMaquina==numeroJugador){
                        sCorrecto()
                        Toast.makeText(this, "Le atinates", Toast.LENGTH_SHORT).show()
                        intentos=0
                    }else{
                        if(intentos == 2){
                            sIncorrecto()
                            Toast.makeText(this, "El número era el $numeroMaquina," +
                                    " tas bien salado mijo",Toast.LENGTH_SHORT).show()
                            val salirJuego = Intent (this,MainActivity::class.java)
                            startActivity(salirJuego)
                            intentos = 0
                        }
                        else{
                            sIncorrecto()
                            Toast.makeText(this, "Nel, calale otra vez",
                                Toast.LENGTH_SHORT).show()
                            intentos+=1
                        }
                    }
                }
            }
            respuestaJugador.text.clear()
        }

    }


    override fun onStop() {
        super.onStop()
        musicaFondo.release()
        respuestaCorrecta.release()
        respuestaIncorrecta.release()
    }

    fun playMusica(){
        musicaFondo.isLooping = true
        musicaFondo.setVolume(0.4f,0.4f)
        musicaFondo.start() // no need to call prepare(); create() does that for you
    }

    fun sCorrecto(){
        respuestaCorrecta.start()
    }

    fun sIncorrecto(){
        respuestaIncorrecta.start()
    }

    fun generarNumero(){
        numeroMaquina = Random.nextInt(0,9)
    }

    fun initUI(){
        btnRespuesta = findViewById(R.id.btnComprobar)
        respuestaJugador = findViewById(R.id.respuesta)
    }

}