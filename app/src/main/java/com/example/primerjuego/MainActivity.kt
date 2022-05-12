package com.example.primerjuego

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var btnInicio : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnInicio = findViewById(R.id.btnComenzar)
        btnInicio.setOnClickListener {
            val iniciarJuego = Intent (this,ActivityJuego::class.java)
            startActivity(iniciarJuego)
        }
    }
}