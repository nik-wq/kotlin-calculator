package com.example.calculatore2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.trimmedLength
import com.example.calculatore2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private var btnadd: Button? = null
    private var btnsub: Button? = null
    private var btnmul: Button? = null
    private var btndiv: Button? = null
    private var btneq: Button? = null
    private var txtresult: TextView? = null
    private var btnclr: Button? = null
    private var btndot: Button? = null

    private var firstNumber = ""
    private var secondNumber = ""
    private var ac = ""
    private var didSelectOperation = false
    private var dotAdded = false

    // тут мы просто забираем заголовок и добавляем его к TextView
    fun appendStringFromButton(btn: Button) {
        if (didSelectOperation) {
            binding.tvResult.text = ""
            didSelectOperation = false
        }

        var buttonTitle = btn.getText().toString()

        // supress multiple 000.. and 01, etc
        var resultString = binding.tvResult.text.toString().plus(buttonTitle).toDouble().toString()
        binding.tvResult.text = resultString
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        btnadd = findViewById<View>(R.id.btPlus) as Button
        btnsub = findViewById<View>(R.id.btMinus) as Button
        btnmul = findViewById<View>(R.id.btMultiply) as Button
        btndiv = findViewById<View>(R.id.btDivide) as Button
        txtresult = findViewById<View>(R.id.tvResult) as TextView
        btneq = findViewById<View>(R.id.btEquals) as Button
        btnclr = findViewById<View>(R.id.btClear) as Button
        btndot = binding.btDot


        // setup digits listener
        for (btnId in arrayOf(R.id.btOne, R.id.btTwo, R.id.btThree, R.id.btFour, R.id.btFive, R.id.btSix, R.id.btSeven, R.id.btEight, R.id.btNine, R.id.btZero)){
            val btn = findViewById<View>(btnId) as Button
            btn.setOnClickListener { v -> appendStringFromButton(v as Button) }
        }

        btnclr!!.setOnClickListener {
            binding.tvResult.text = ""
            firstNumber = ""
            secondNumber = ""
            ac = ""
        }


        fun performOperation(first: Double, second: Double, operation: String): Double {
            if (operation == "+") {
                return first + second
            } else if (operation == "-") {
                return first - second
            } else if (operation == "*") {
                return first * second
            } else if (operation == "/") {
                if(second == 0.0) {
                    return 0.0
                } else {
                    return first / second
                }
            } else {
                return second
            }
        }

        fun addDot() {
                binding.tvResult.text = binding.tvResult.text.toString().plus(".")
        }

        btndot!!.setOnClickListener{
            if(!binding.tvResult.text.contains(".")){
                addDot()
                dotAdded = true
            }
        }
        
        btneq!!.setOnClickListener {
            if (ac.isEmpty()) {
                // do nothing in case there is no operation selected
                return@setOnClickListener
            }
            if (dotAdded){
            }

            secondNumber = binding.tvResult.text.toString()
            if (secondNumber.isEmpty()) {
                secondNumber = firstNumber
            }

            var result = performOperation( firstNumber.toDouble(), secondNumber.toDouble(), ac )
            binding.tvResult.text = result.toString()
        }

        fun operationSelected(btn: Button) {
            firstNumber = binding.tvResult.text.toString()
            ac = btn.getText().toString()

            // mark as true, so when user taps on digit next time we'll reset the text view
            didSelectOperation = true
        }

        btnadd!!.setOnClickListener{ v -> operationSelected(v as Button)}
        btnsub!!.setOnClickListener{ v -> operationSelected(v as Button)}
        btnmul!!.setOnClickListener{ v -> operationSelected(v as Button)}
        btndiv!!.setOnClickListener{ v -> operationSelected(v as Button)}
    }
}
