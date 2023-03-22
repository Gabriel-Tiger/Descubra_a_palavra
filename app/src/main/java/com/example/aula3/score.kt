package com.example.aula3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class score : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val txtPonto = findViewById<TextView>(R.id.txtPontos)
        val txtErros = findViewById<TextView>(R.id.txtErro)
        val txtTempo = findViewById<TextView>(R.id.txtTempo)
        val novoJogo = findViewById<Button>(R.id.button)

        val bundle = intent.extras
        var value = 0

        if (bundle != null) {
            txtTempo.text = bundle.getString("tempo").toString()
            txtPonto.text = bundle.getInt("pontos").toString()
            txtErros.text = bundle.getInt("erros").toString()
        }

        novoJogo.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}