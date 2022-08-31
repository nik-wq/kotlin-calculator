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
        fun CalculatorType(){
            when(numberCounter){
                9->{
                    binding.tvResult.text=a+b+c+d+e+f+g+v+t+y
                    numberCounter++
                }
                8->{
                    binding.tvResult.text=a+b+c+d+e+f+g+v+t
                    numberCounter++
                }
                7->{
                    binding.tvResult.text=a+b+c+d+e+f+g+v
                    numberCounter++
                }
                6->{
                    binding.tvResult.text=a+b+c+d+e+f+g
                    numberCounter++
                }
                5->{
                    binding.tvResult.text=a+b+c+d+e+f
                    numberCounter++
                }
                4->{
                    binding.tvResult.text=a+b+c+d+e
                    numberCounter++
                }
                3->{
                    binding.tvResult.text=a+b+c+d
                    numberCounter++
                }
                2->{
                    binding.tvResult.text=a+b+c
                    numberCounter++
                }
                1->{
                    binding.tvResult.text=a+b
                    numberCounter++
                }
                0->{
                    binding.tvResult.text=a
                    numberCounter++
                }

            }
        }

//a b c d e f g v t y

        btnone!!.setOnClickListener {
            a="1"; b="1"; c="1"; d="1"; e="1"; f="1"; g="1"; v="1";t="1";y="1"
            CalculatorType()
        }
        btntwo!!.setOnClickListener {
            a="2"; b="2"; c="2"; d="2"; e="2"; f="2"; g="2"; v="2";t="2";y="2"
            CalculatorType()
        }
        btnthree!!.setOnClickListener {
            a="3"; b="3"; c="3"; d="3"; e="3"; f="3"; g="3"; v="3";t="3";y="3"
            CalculatorType()
        }
        btnfour!!.setOnClickListener {
            a="4"; b="4"; c="4"; d="4"; e="4"; f="4"; g="4"; v="4";t="4";y="4"
            CalculatorType()
        }
        btnfive!!.setOnClickListener {
            a="5"; b="5"; c="5"; d="5"; e="5"; f="5"; g="5"; v="5";t="5";y="5"
            CalculatorType()
        }
        btnsix!!.setOnClickListener {
            a="6"; b="6"; c="6"; d="6"; e="6"; f="6"; g="6"; v="6";t="6";y="6"
            CalculatorType()
        }
        btnseven!!.setOnClickListener {
            a="7"; b="7"; c="7"; d="7"; e="7"; f="7"; g="7"; v="7";t="7";y="7"
            CalculatorType()
        }
        btneight!!.setOnClickListener {
            a="8"; b="8"; c="8"; d="8"; e="8"; f="8"; g="8"; v="8";t="8";y="8"
            CalculatorType()
        }
        btnnine!!.setOnClickListener {
            a="9"; b="9"; c="9"; d="9"; e="9"; f="9"; g="9"; v="9";t="9";y="9"
            CalculatorType()
        }
        btnzero!!.setOnClickListener {
            if(numberCounter>=1)
                b="0"; c="0"; d="0"; e="0"; f="0"; g="0"; v="0";t="0";y="0"
                CalculatorType()
        }
    }
}