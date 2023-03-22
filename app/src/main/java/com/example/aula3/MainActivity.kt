package com.example.aula3

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Serializable
import java.text.Normalizer
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var aux = 1
        var btnProximo = findViewById<Button>(R.id.btnProximo)
        var palavra = findViewById<TextView>(R.id.txtPalavra)
        var dica = findViewById<TextView>(R.id.txtDica)

        data class DicionarioClass(
            val index: Int,
            val palavras: String,
            val dica: String,
        )

        fun readCsv(inputStream: InputStream): List<DicionarioClass> {
            val reader = inputStream.bufferedReader()
            val header = reader.readLine()
            return reader.lineSequence()
                .filter { it.isNotBlank() }
                .map {
                    val (index, palavras, dica) = it.split(',', ignoreCase = false, limit = 20)
                    DicionarioClass(
                        index.trim().toInt(),
                        palavras.trim(),
                        dica.trim().removeSurrounding("\""),
                    )
                }.toList()
        }

        val dicionario = readCsv(assets.open("words.csv"))
        Log.d("1 palavra do dicionario", dicionario[0].palavras)
        Log.d("Ultima palavra do dicionario", dicionario[(dicionario.size - 1)].palavras)

        // https://br.pinterest.com/pin/78883430965332973/   backgorund


//        var dicionario = ArrayList<String>()
//        var dicionario2 = "qweertyuiopasdfghjklzxcvbnm "
        var dicionario2 = "abcdefghijklmnopqrstuvwxyz "
        var generating = 1
        var generatingIndex = 1
        var frase1 = "none"
        var frase2 = "none"
        var frase3 = "none"
        var frase4 = "none"
        var frase5 = "none"
        var dica1 = "none"
        var dica2 = "none"
        var dica3 = "none"
        var dica4 = "none"
        var dica5 = "none"

        var ponto = 0
        var erros = 0

        var randomValue = (0..dicionario.size).random()

        while (generating == 1) {
            randomValue = (0..(dicionario.size - 1)).random()

            when (generatingIndex) {
                1 -> {
                    if (!(dicionario[randomValue].palavras.toLowerCase() == frase1 || dicionario[randomValue].palavras.toLowerCase() == frase2 || dicionario[randomValue].palavras.toLowerCase() == frase3 || dicionario[randomValue].palavras.toLowerCase() == frase4 || dicionario[randomValue].palavras.toLowerCase() == frase5)) {
                        frase1 = dicionario[randomValue].palavras.toLowerCase()
                        dica1 = dicionario[randomValue].dica
                        generatingIndex = 2
                    }
                }
                2 -> {
                    if (!(dicionario[randomValue].palavras.toLowerCase() == frase1 || dicionario[randomValue].palavras.toLowerCase() == frase2 || dicionario[randomValue].palavras.toLowerCase() == frase3 || dicionario[randomValue].palavras.toLowerCase() == frase4 || dicionario[randomValue].palavras.toLowerCase() == frase5)) {
                        frase2 = dicionario[randomValue].palavras.toLowerCase()
                        dica2 = dicionario[randomValue].dica
                        generatingIndex = 3
                    }
                }
                3 -> {
                    if (!(dicionario[randomValue].palavras.toLowerCase() == frase1 || dicionario[randomValue].palavras.toLowerCase() == frase2 || dicionario[randomValue].palavras.toLowerCase() == frase3 || dicionario[randomValue].palavras.toLowerCase() == frase4 || dicionario[randomValue].palavras.toLowerCase() == frase5)) {
                        frase3 = dicionario[randomValue].palavras.toLowerCase()
                        dica3 = dicionario[randomValue].dica
                        generatingIndex = 4
                    }
                }
                4 -> {
                    if (!(dicionario[randomValue].palavras.toLowerCase() == frase1 || dicionario[randomValue].palavras.toLowerCase() == frase2 || dicionario[randomValue].palavras.toLowerCase() == frase3 || dicionario[randomValue].palavras.toLowerCase() == frase4 || dicionario[randomValue].palavras.toLowerCase() == frase5)) {
                        frase4 = dicionario[randomValue].palavras.toLowerCase()
                        dica4 = dicionario[randomValue].dica
                        generatingIndex = 5
                    }
                }
                5 -> {
                    if (!(dicionario[randomValue].palavras.toLowerCase() == frase1 || dicionario[randomValue].palavras.toLowerCase() == frase2 || dicionario[randomValue].palavras.toLowerCase() == frase3 || dicionario[randomValue].palavras.toLowerCase() == frase4 || dicionario[randomValue].palavras.toLowerCase() == frase5)) {
                        frase5 = dicionario[randomValue].palavras.toLowerCase()
                        dica5 = dicionario[randomValue].dica
                        generating = 0
                    }
                }

            }
        }

        Log.d("a", frase1)
        Log.d("a", frase2)
        Log.d("a", frase3)
        Log.d("a", frase4)
        Log.d("a", frase5)


        fun geraFase(palavraMestre: String): List<Serializable> {
            var gabarito = ArrayList<Char>()
            var desafio = palavraMestre
            var count = 0
            var loopsize = palavraMestre.length - palavraMestre.length / 2

            while (count < loopsize) {
                randomValue = (0..(palavraMestre.length - 1)).random()

                if (desafio[randomValue] != '_') {
                    if (desafio[randomValue] !in gabarito) {
                        gabarito.add(desafio[randomValue])
                        desafio = desafio.replaceRange(randomValue..randomValue, "_")
                        count++
                    }
                }

            }

            for (item in gabarito) {
                if (desafio.contains(item)) {
                    desafio = desafio.replace(item, '_')
                }
            }

            return listOf(desafio, gabarito)
        }
/* Gera Teclado aleatorio (muito dificil)
        fun geraDesafio(gabaritoMestre: ArrayList<Char>): ArrayList<Char> {
            var gabarito = ArrayList<Char>()
            var gabarito2 = ArrayList<Char>()
            var count = 0
            var count2 = 0
            var loopsize = (dicionario2.length - 1)

            for (item in gabaritoMestre) {
                gabarito2.add(item)
            }


            while (count < loopsize) {
                if ((0..4).random() == 0) {
                    if (count2 <= (gabaritoMestre.size - 1)) {
                        gabarito.add(gabaritoMestre[count2])
                        count2++
                    }
                }

                randomValue = (0..(dicionario2.length - 1)).random()

                if (dicionario2[randomValue] !in gabarito && dicionario2[randomValue] !in gabarito2) {
                    gabarito.add(dicionario2[randomValue])
                }
                count++

            }

            for (item in gabarito2) {
                if (item !in gabarito) {
                    gabarito.add(item)
                }
            }


            return gabarito
        }*/


        var material = geraFase(frase1)
        var desafio = material[0]
        var gabarito = material[1]
        Log.d("desafio", desafio.toString())
        Log.d("gabarito", gabarito.toString())


        fun removeAcento(char: Char): Char {
            val semAcento = Normalizer.normalize(char.toString(), Normalizer.Form.NFD)
                .replace("[^\\p{ASCII}]".toRegex(), "")
            return semAcento[0].lowercaseChar()
        }

        var historico = ArrayList<Char>()
        fun valida(caractere: Char): Int {
            if (caractere !in historico) {
                historico.add(caractere)
                var validacao = 0 // 0-letra errada  1-letra certa  2-finalizo  3-nulo
                var fraseDesafio = ""
                Log.d("caractere", caractere.toString())

                when (aux) {
                    1 -> {
                        fraseDesafio = frase1
                    }
                    2 -> {
                        fraseDesafio = frase2
                    }
                    3 -> {
                        fraseDesafio = frase3
                    }
                    4 -> {
                        fraseDesafio = frase4
                    }
                    5 -> {
                        fraseDesafio = frase5
                    }

                }
                Log.d("fraseDesafio", fraseDesafio.toString())
                var index = 0
                for (item in fraseDesafio) {
                    val normalizado = removeAcento(item)
                    if (normalizado == caractere) {
                        desafio = desafio.toString()
                            .replaceRange(index..index, fraseDesafio[index].toString())
                        palavra.setText(desafio.toString())
                        validacao = 1
                    }

                    index++
                }
                if ((fraseDesafio.length - 1) == (desafio.toString().replace("_", "").length - 1)) {
                    validacao = 2
                }
                return validacao
            }
            return 3
        }




        palavra.setText(desafio.toString())
        dica.setText(dica1)


//       var desafioBotao = geraDesafio(gabarito as ArrayList<Char>)
//        Log.d("desafioBotao", desafioBotao.toString())

        val constraintLayout = findViewById(R.id.buttonHolder) as ConstraintLayout
        val cronometro = findViewById<Chronometer>(R.id.cronometro)
        var contando = 0
        var tempoDePausa = 0L

        fun geraTeclado() {
            var nBotoesPorLinha = dicionario2.length / 4

            val table = TableLayout(this)
            table.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT
            );
            var count = nBotoesPorLinha

            var row = TableRow(this)

            for (item in dicionario2) {
                if (count >= nBotoesPorLinha) {
                    table.addView(row);
                    row = TableRow(this)
                    row.layoutParams = TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT
                    );
                    count = 0
                }


                val button = Button(this)

                button.layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT
                );
                button.text = item.toString()
                (button.layoutParams as TableRow.LayoutParams).setMargins(10, 10, 10, 10)
                button.setBackgroundResource(R.drawable.buttonpadrao)
                for (letra in desafio.toString()) {
                    if (letra == item) {
                        button.setBackgroundResource(R.drawable.buttoncerto)

                    }
                }
                (button.layoutParams as TableRow.LayoutParams).weight = 1F
                button.setOnClickListener(View.OnClickListener {
                    if (contando == 0) {
                        cronometro.base = SystemClock.elapsedRealtime() - tempoDePausa
                        cronometro.start()
                        contando = 1
                    }
                    val status = valida(button.text[0])
                    when (status) {
                        0 -> {
                            erros++
                            button.setBackgroundResource(R.drawable.buttonerrado)
                        }
                        1 -> {
                            ponto++
                            button.setBackgroundResource(R.drawable.buttoncerto)

                        }
                        2 -> {
                            contando = 0
                            cronometro.stop()
                            tempoDePausa = SystemClock.elapsedRealtime() - cronometro.base
                            button.setBackgroundResource(R.drawable.buttoncerto)
                            ponto++
                            aux++
                            btnProximo.visibility = View.VISIBLE
                            constraintLayout.removeAllViews()
                        }
                        3 -> {

                        }
                    }
                })

//                button.setBackgroundColor(Color.DKGRAY)
                button.setTextColor(Color.WHITE)
                row.addView(button)
                count++
            }
            table.addView(row);
            constraintLayout.addView(table);
        }
        geraTeclado()




        btnProximo.setOnClickListener {
            historico = ArrayList<Char>()
            when (aux) {
                2 -> {
                    btnProximo.visibility = View.INVISIBLE
                    material = geraFase(frase2)
                    dica.setText(dica2)
                    desafio = material[0]
                    gabarito = material[1]
                    palavra.setText(desafio.toString())
//                    desafioBotao = geraDesafio(gabarito as ArrayList<Char>)
                    Log.d("desafio", desafio.toString())
                    Log.d("gabarito", gabarito.toString())
                    Log.d("ponto", ponto.toString())
                    Log.d("erros", erros.toString())
                    geraTeclado()
                }
                3 -> {
                    btnProximo.visibility = View.INVISIBLE
                    material = geraFase(frase3)
                    dica.setText(dica3)
                    desafio = material[0]
                    gabarito = material[1]
                    palavra.setText(desafio.toString())
//                    desafioBotao = geraDesafio(gabarito as ArrayList<Char>)
                    Log.d("desafio", desafio.toString())
                    Log.d("gabarito", gabarito.toString())
                    Log.d("ponto", ponto.toString())
                    Log.d("erros", erros.toString())
                    geraTeclado()
                }
                4 -> {
                    btnProximo.visibility = View.INVISIBLE
                    material = geraFase(frase4)
                    dica.setText(dica4)
                    desafio = material[0]
                    gabarito = material[1]
                    palavra.setText(desafio.toString())
//                    desafioBotao = geraDesafio(gabarito as ArrayList<Char>)
                    Log.d("desafio", desafio.toString())
                    Log.d("gabarito", gabarito.toString())
                    Log.d("ponto", ponto.toString())
                    Log.d("erros", erros.toString())
                    geraTeclado()
                }
                5 -> {
                    btnProximo.visibility = View.INVISIBLE
                    material = geraFase(frase5)
                    dica.setText(dica5)
                    desafio = material[0]
                    gabarito = material[1]
                    palavra.setText(desafio.toString())
//                    desafioBotao = geraDesafio(gabarito as ArrayList<Char>)
                    Log.d("desafio", desafio.toString())
                    Log.d("gabarito", gabarito.toString())
                    Log.d("ponto", ponto.toString())
                    Log.d("erros", erros.toString())
                    geraTeclado()
                }
                6 -> {
                    cronometro.text
                    val intent = Intent(this, score::class.java)
                    val tempoBundle = Bundle()
                    val pontosBundle = Bundle()
                    val errosBundle = Bundle()
                    Log.d("Tempo", cronometro.text.toString())
                    tempoBundle.putString("tempo", cronometro.text.toString())
                    pontosBundle.putInt("pontos", ponto)
                    errosBundle.putInt("erros", erros)

                    intent.putExtras(tempoBundle)
                    intent.putExtras(pontosBundle)
                    intent.putExtras(errosBundle)

                    startActivity(intent)
                    finish()
                }

            }
        }

    }
}