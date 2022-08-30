package com.example.calculatore2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatore2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private var btnadd: Button? = null
    private var btnsub: Button? = null
    private var btnmul: Button? = null
    private var btndiv: Button? = null
    private var btneq: Button? = null
    private var txtresult: TextView? = null
    private var btnseven: Button? = null
    private var btnone: Button? = null
    private var btntwo: Button? = null
    private var btnthree: Button? = null
    private var btnfour: Button? = null
    private var btnfive: Button? = null
    private var btnsix: Button? = null
    private var btneight: Button? = null
    private var btnnine: Button? = null
    private var btnzero: Button? = null
    private var btnclr: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var a: String = ""
        var b: String = ""
        var c: String = ""
        var d: String = ""
        var e: String = ""
        var f: String = ""
        var g: String = ""
        var v: String = ""
        var t: String = ""
        var y: String = ""
        var numberCounter = 0
        var firstNumber: String = ""
        var secondNumber = ""
        var addCounter = 0
        var subCounter = 0
        var divCounter = 0
        var mulCounter = 0
        var negCounter = 0


        btnadd = findViewById<View>(R.id.btPlus) as Button
        btnsub = findViewById<View>(R.id.btMinus) as Button
        btnmul = findViewById<View>(R.id.btMultiply) as Button
        btndiv = findViewById<View>(R.id.btDivide) as Button
        txtresult = findViewById<View>(R.id.tvResult) as TextView
        btneq = findViewById<View>(R.id.btEquals) as Button
        btnclr = findViewById<View>(R.id.btClear) as Button


        btnone = findViewById<View>(R.id.btOne) as Button
        btntwo = findViewById<View>(R.id.btTwo) as Button
        btnthree = findViewById<View>(R.id.btThree) as Button
        btnfour = findViewById<View>(R.id.btFour) as Button
        btnfive = findViewById<View>(R.id.btFive) as Button
        btnsix = findViewById<View>(R.id.btSix) as Button
        btnseven = findViewById<View>(R.id.btSeven) as Button
        btneight = findViewById<View>(R.id.btEight) as Button
        btnnine = findViewById<View>(R.id.btNine) as Button
        btnzero = findViewById<View>(R.id.btZero) as Button


        btnclr!!.setOnClickListener {
            binding.tvResult.text = ""
            numberCounter = 0
            firstNumber = ""
            secondNumber = ""
            addCounter = 0
            mulCounter = 0
            subCounter = 0
            divCounter = 0
            negCounter = 0
        }

        btnadd!!.setOnClickListener {
            if(numberCounter>=1) {
                firstNumber = binding.tvResult.text.toString()
                firstNumber.toInt()
                binding.tvResult.text = "+"
                numberCounter = 0
                addCounter++
            }
        }
        btnsub!!.setOnClickListener{
              if(addCounter>=1) {
                  binding.tvResult.text = "-"
                  negCounter++
              }
            if(subCounter>=1) {
                binding.tvResult.text = "-"
                negCounter++
            }
            if(mulCounter>=1) {
                binding.tvResult.text = "-"
                negCounter++
            }
            if(divCounter>=1) {
                binding.tvResult.text = "-"
                negCounter++
            }
            if(numberCounter>=1){
                firstNumber = binding.tvResult.text.toString()
                firstNumber.toInt()
                binding.tvResult.text = "-"
                numberCounter = 0
                subCounter++
            }
        }
        btnmul!!.setOnClickListener{
            if(numberCounter>=1)
                firstNumber = binding.tvResult.text.toString()
                firstNumber.toInt()
                binding.tvResult.text = "*"
                numberCounter = 0
                mulCounter++
        }
        btndiv!!.setOnClickListener{
            if(numberCounter>=1)
                firstNumber = binding.tvResult.text.toString()
                firstNumber.toInt()
                binding.tvResult.text = "/"
                numberCounter = 0
                divCounter++
        }
        btneq!!.setOnClickListener {
            if(addCounter>=1){
                    secondNumber = binding.tvResult.text.toString()
                    var firstInt = firstNumber.toInt()
                    var secondInt = secondNumber.toInt()
                if(negCounter==1){
                    var result = firstInt+-secondInt
                    binding.tvResult.text = result.toString()
                }
                else{
                    var result = firstInt + secondInt
                    binding.tvResult.text = result.toString()
                }

            }
            if(subCounter>=1){
                secondNumber = binding.tvResult.text.toString()
                var firstInt = firstNumber.toInt()
                var secondInt = secondNumber.toInt()
                if(negCounter==1){
                    var result = firstInt+secondInt
                    binding.tvResult.text = result.toString()
                }
                else{
                    var result = firstInt-secondInt
                    binding.tvResult.text = result.toString()
                }
            }
            if(mulCounter>=1){
                secondNumber = binding.tvResult.text.toString()
                var firstInt = firstNumber.toInt()
                var secondInt = secondNumber.toInt()
                if(negCounter==1){
                    var result = firstInt*-secondInt
                    binding.tvResult.text = result.toString()
                }
                else{
                    var result = firstInt * secondInt
                    binding.tvResult.text = result.toString()
                }
            }
            if(divCounter>=1){
                secondNumber = binding.tvResult.text.toString()
                var firstInt = firstNumber.toInt()
                var secondInt = secondNumber.toInt()
                if(negCounter==1){
                    var result = firstInt/-secondInt
                    binding.tvResult.text = result.toString()
                }
                else{
                    var result = firstInt / secondInt
                    binding.tvResult.text = result.toString()
                }
            }
        }



        btnone!!.setOnClickListener {

            if(numberCounter == 9) {
            y = "1"
            binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"+"$t"+"$y"
            numberCounter++
        }
            if(numberCounter == 8) {
                t = "1"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"+"$t"
                numberCounter++
            }
            if(numberCounter == 7) {
                v = "1"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"
                numberCounter++
            }
            if(numberCounter == 6) {
                g = "1"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"
                numberCounter++
            }
            if(numberCounter == 5) {
                f = "1"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d" + "$e" + "$f"
                numberCounter++
            }
            if(numberCounter == 4) {
                e="1"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d" + "$e"
                numberCounter++
            }
            if(numberCounter == 3){
                d="1"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d"
                numberCounter++
            }

            if (numberCounter == 2) {
                c = "1"
                binding.tvResult.text = "$a" + "$b" + "$c"
                numberCounter++
            }
            if (numberCounter == 1) {
                b = "1"
                binding.tvResult.text = "$a" + "$b"
                numberCounter++
            }
            if (numberCounter == 0) {
                a = "1"
                binding.tvResult.text = "$a"
                numberCounter++
            }


        }
        btntwo!!.setOnClickListener {
            if(numberCounter == 9) {
                y = "2"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"+"$t"+"$y"
                numberCounter++
            }
            if(numberCounter == 8) {
                t = "2"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"+"$t"
                numberCounter++
            }
            if(numberCounter == 7) {
                v = "2"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"
                numberCounter++
            }
            if(numberCounter == 6) {
                g = "2"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"
                numberCounter++
            }
            if(numberCounter == 5) {
                f = "2"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d" + "$e" + "$f"
                numberCounter++
            }
            if(numberCounter == 4) {
                e="2"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d" + "$e"
                numberCounter++
            }
            if(numberCounter == 3){
                d="2"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d"
                numberCounter++
            }
            if (numberCounter == 2) {
                c = "2"
                binding.tvResult.text = "$a" + "$b" + "$c"
                numberCounter++
            }
            if (numberCounter == 1) {
                b = "2"
                binding.tvResult.text = "$a" + "$b"
                numberCounter++
            }
            if (numberCounter == 0) {
                a = "2"
                binding.tvResult.text = "$a"
                numberCounter++
            }

        }
        btnthree!!.setOnClickListener {
            if(numberCounter == 9) {
                y = "3"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"+"$t"+"$y"
                numberCounter++
            }
            if(numberCounter == 8) {
                t = "3"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"+"$t"
                numberCounter++
            }
            if(numberCounter == 7) {
                v = "3"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"
                numberCounter++
            }
            if(numberCounter == 6) {
                g = "3"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"
                numberCounter++
            }
            if(numberCounter == 5) {
                f = "3"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d" + "$e" + "$f"
                numberCounter++
            }
            if(numberCounter == 4) {
                e="3"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d" + "$e"
                numberCounter++
            }
            if(numberCounter == 3){
                d="3"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d"
                numberCounter++
            }
            if (numberCounter == 2) {
                c = "3"
                binding.tvResult.text = "$a" + "$b" + "$c"
                numberCounter++
            }
            if (numberCounter == 1) {
                b = "3"
                binding.tvResult.text = "$a" + "$b"
                numberCounter++
            }
            if (numberCounter == 0) {
                a = "3"
                binding.tvResult.text = "$a"
                numberCounter++
            }

        }
        btnfour!!.setOnClickListener {
            //a,b,c,d,e,f,g,v,t,y
            if(numberCounter == 9) {
                y = "4"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"+"$t"+"$y"
                numberCounter++
            }
            if(numberCounter == 8) {
                t = "4"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"+"$t"
                numberCounter++
            }
            if(numberCounter == 7) {
                v = "4"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"
                numberCounter++
            }
            if(numberCounter == 6) {
                g = "4"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"
                numberCounter++
            }
            if(numberCounter == 5) {
                f = "4"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d" + "$e" + "$f"
                numberCounter++
            }
            if(numberCounter == 4) {
                e="4"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d" + "$e"
                numberCounter++
            }
            if(numberCounter == 3){
                d="4"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d"
                numberCounter++
            }
            if(numberCounter == 2){
                c="4"
                binding.tvResult.text = "$a" + "$b" + "$c"
                numberCounter++
            }
            if(numberCounter == 1){
                b="4"
                binding.tvResult.text = "$a" + "$b"
                numberCounter++
            }
            if(numberCounter == 0){
                a="4"
                binding.tvResult.text = "$a"
                numberCounter++
            }

        }
        btnfive!!.setOnClickListener {
            if(numberCounter == 9) {
                y = "5"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"+"$t"+"$y"
                numberCounter++
            }
            if(numberCounter == 8) {
                t = "5"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"+"$t"
                numberCounter++
            }
            if(numberCounter == 7) {
                v = "5"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"
                numberCounter++
            }
            if(numberCounter == 6) {
                g = "5"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"
                numberCounter++
            }
            if(numberCounter == 5) {
                f = "5"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d" + "$e" + "$f"
                numberCounter++
            }
            if(numberCounter == 4) {
                e="5"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d" + "$e"
                numberCounter++
            }
            if(numberCounter == 3){
                d="5"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d"
                numberCounter++
            }
            if(numberCounter == 2){
                c="5"
                binding.tvResult.text = "$a" + "$b" + "$c"
                numberCounter++
            }
            if(numberCounter == 1){
                b="5"
                binding.tvResult.text = "$a" + "$b"
                numberCounter++
            }
            if(numberCounter == 0){
                a="5"
                binding.tvResult.text = "$a"
                numberCounter++
            }

        }
        btnsix!!.setOnClickListener {
            if(numberCounter == 9) {
                y = "6"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"+"$t"+"$y"
                numberCounter++
            }
            if(numberCounter == 8) {
                t = "6"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"+"$t"
                numberCounter++
            }
            if(numberCounter == 7) {
                v = "6"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"
                numberCounter++
            }
            if(numberCounter == 6) {
                g = "6"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"
                numberCounter++
            }
            if(numberCounter == 5) {
                f = "6"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d" + "$e" + "$f"
                numberCounter++
            }
            if(numberCounter == 4) {
                e="6"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d" + "$e"
                numberCounter++
            }
            if(numberCounter == 3){
                d="6"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d"
                numberCounter++
            }
            if(numberCounter == 2){
                c="6"
                binding.tvResult.text = "$a" + "$b" + "$c"
                numberCounter++
            }
            if(numberCounter == 1){
                b="6"
                binding.tvResult.text = "$a" + "$b"
                numberCounter++
            }
            if(numberCounter == 0){
                a="6"
                binding.tvResult.text = "$a"
                numberCounter++
            }
        }
        btnseven!!.setOnClickListener {
            if(numberCounter == 9) {
                y = "7"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"+"$t"+"$y"
                numberCounter++
            }
            if(numberCounter == 8) {
                t = "7"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"+"$t"
                numberCounter++
            }
            if(numberCounter == 7) {
                v = "7"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"
                numberCounter++
            }
            if(numberCounter == 6) {
                g = "7"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"
                numberCounter++
            }
            if(numberCounter == 5) {
                f = "7"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d" + "$e" + "$f"
                numberCounter++
            }
            if(numberCounter == 4) {
                e="7"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d" + "$e"
                numberCounter++
            }
            if(numberCounter == 3){
                d="7"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d"
                numberCounter++
            }
            if(numberCounter == 2){
                c="7"
                binding.tvResult.text = "$a" + "$b" + "$c"
                numberCounter++
            }
            if(numberCounter == 1){
                b="7"
                binding.tvResult.text = "$a" + "$b"
                numberCounter++
            }
            if(numberCounter == 0){
                a="7"
                binding.tvResult.text = "$a"
                numberCounter++
            }
        }
        btneight!!.setOnClickListener {
            if(numberCounter == 9) {
                y = "8"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"+"$t"+"$y"
                numberCounter++
            }
            if(numberCounter == 8) {
                t = "8"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"+"$t"
                numberCounter++
            }
            if(numberCounter == 7) {
                v = "8"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"
                numberCounter++
            }
            if(numberCounter == 6) {
                g = "8"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"
                numberCounter++
            }
            if(numberCounter == 5) {
                f = "8"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d" + "$e" + "$f"
                numberCounter++
            }
            if(numberCounter == 4) {
                e="8"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d" + "$e"
                numberCounter++
            }
            if(numberCounter == 3){
                d="8"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d"
                numberCounter++
            }
            if(numberCounter == 2){
                c="8"
                binding.tvResult.text = "$a" + "$b" + "$c"
                numberCounter++
            }
            if(numberCounter == 1){
                b="8"
                binding.tvResult.text = "$a" + "$b"
                numberCounter++
            }
            if(numberCounter == 0){
                a="8"
                binding.tvResult.text = "$a"
                numberCounter++
            }
        }
        btnnine!!.setOnClickListener {
            if(numberCounter == 9) {
                y = "9"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"+"$t"+"$y"
                numberCounter++
            }
            if(numberCounter == 8) {
                t = "9"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"+"$t"
                numberCounter++
            }
            if(numberCounter == 7) {
                v = "9"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"
                numberCounter++
            }
            if(numberCounter == 6) {
                g = "9"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"
                numberCounter++
            }
            if(numberCounter == 5) {
                f = "9"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d" + "$e" + "$f"
                numberCounter++
            }
            if(numberCounter == 4) {
                e="9"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d" + "$e"
                numberCounter++
            }
            if(numberCounter == 3){
                d="9"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d"
                numberCounter++
            }
            if(numberCounter == 2){
                c="9"
                binding.tvResult.text = "$a" + "$b" + "$c"
                numberCounter++
            }
            if(numberCounter == 1){
                b="9"
                binding.tvResult.text = "$a" + "$b"
                numberCounter++
            }
            if(numberCounter == 0){
                a="9"
                binding.tvResult.text = "$a"
                numberCounter++
            }
        }
        btnzero!!.setOnClickListener {
            if(numberCounter == 9) {
                y = "0"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"+"$t"+"$y"
                numberCounter++
            }
            if(numberCounter == 8) {
                t = "0"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"+"$t"
                numberCounter++
            }
            if(numberCounter == 7) {
                v = "0"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"+"$v"
                numberCounter++
            }
            if(numberCounter == 6) {
                g = "0"
                binding.tvResult.text = "$a"+"$b"+"$c"+"$d"+"$e"+"$f"+"$g"
                numberCounter++
            }
            if(numberCounter == 5) {
                f = "0"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d" + "$e" + "$f"
                numberCounter++
            }
            if(numberCounter == 4) {
                e="0"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d" + "$e"
                numberCounter++
            }
            if(numberCounter == 3){
                d="0"
                binding.tvResult.text = "$a" + "$b" + "$c" + "$d"
                numberCounter++
            }
            if(numberCounter == 2){
                c="0"
                binding.tvResult.text = "$a" + "$b" + "$c"
                numberCounter++
            }
            if(numberCounter == 1){
                b="0"
                binding.tvResult.text = "$a" + "$b"
                numberCounter++
            }

        }
    }
}