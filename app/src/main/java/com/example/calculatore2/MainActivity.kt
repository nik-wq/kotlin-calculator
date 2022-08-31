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
            firstNumber = ""
            secondNumber = ""
            addCounter = 0
            mulCounter = 0
            subCounter = 0
            divCounter = 0
            negCounter = 0
        }

        btnadd!!.setOnClickListener {
            firstNumber = binding.tvResult.text.toString()
            binding.tvResult.text = "+"
            addCounter++

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
            firstNumber = binding.tvResult.text.toString()
            binding.tvResult.text = "-"
            subCounter++

        }
        btnmul!!.setOnClickListener{
                firstNumber = binding.tvResult.text.toString()
                binding.tvResult.text = "*"
                mulCounter++
        }
        btndiv!!.setOnClickListener{

                firstNumber = binding.tvResult.text.toString()
                binding.tvResult.text = "/"
                divCounter++
        }
        btneq!!.setOnClickListener {
            secondNumber = binding.tvResult.text.toString()
            secondNumber = secondNumber.replace("+","")
            secondNumber = secondNumber.replace("-","")
            secondNumber = secondNumber.replace("*","")
            secondNumber = secondNumber.replace("/","")
            if(addCounter>=1){
                var firstInt = firstNumber.toInt()
                var secondInt = secondNumber.toInt()
                if(negCounter>=1){
                    var result = firstInt+-secondInt
                    binding.tvResult.text = result.toString()
                }
                else{
                    var result = firstInt + secondInt
                    binding.tvResult.text = result.toString()
                }
            }
            if(subCounter>=1){
                var firstInt = firstNumber.toInt()
                var secondInt = secondNumber.toInt()
                if(negCounter>=1){
                    var result = firstInt+secondInt
                    binding.tvResult.text = result.toString()
                }
                else{
                    var result = firstInt-secondInt
                    binding.tvResult.text = result.toString()
                }
            }
            if(mulCounter>=1){
                var firstInt = firstNumber.toInt()
                var secondInt = secondNumber.toInt()
                if(negCounter>=1){
                    var result = firstInt*-secondInt
                    binding.tvResult.text = result.toString()
                }
                else{
                    var result = firstInt * secondInt
                    binding.tvResult.text = result.toString()
                }
            }
            if(divCounter>=1){
                var firstInt = firstNumber.toInt()
                var secondInt = secondNumber.toInt()
                if(negCounter>=1){
                    var result = firstInt/-secondInt
                    binding.tvResult.text = result.toString()
                }
                else{
                    var result = firstInt / secondInt
                    binding.tvResult.text = result.toString()
                }
            }
        }


        // тут мы просто забираем заголовок и добавляем его к TextView
        fun appendStringFromButton(btn: Button) {
            var buttonTitle = btn.getText().toString()
            binding.tvResult.text = binding.tvResult.text.toString().plus(buttonTitle)
        }

        // Никит, смотри
        // - тут я отдаю в функцию параметром кнопку, на которую кликнули
        // - и уже там из этой кнопки достаю ее title, его добавляю в TextView
        // - таким образом наш код универсальнее и унифицированнее получился
        btnone!!.setOnClickListener { v -> appendStringFromButton(v as Button) }
        btntwo!!.setOnClickListener { v -> appendStringFromButton(v as Button) }
        btnthree!!.setOnClickListener { v -> appendStringFromButton(v as Button) }
        btnfour!!.setOnClickListener { v -> appendStringFromButton(v as Button) }
        btnfive!!.setOnClickListener { v -> appendStringFromButton(v as Button) }
        btnsix!!.setOnClickListener { v -> appendStringFromButton(v as Button) }
        btnseven!!.setOnClickListener { v -> appendStringFromButton(v as Button) }
        btneight!!.setOnClickListener { v -> appendStringFromButton(v as Button) }
        btnnine!!.setOnClickListener { v -> appendStringFromButton(v as Button) }
        btnzero!!.setOnClickListener { v -> appendStringFromButton(v as Button) }
    }
}