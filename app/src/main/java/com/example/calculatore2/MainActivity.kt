package com.example.calculatore2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatore2.databinding.ActivityMainBinding
import java.text.DecimalFormat

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
    private var operationComplete = false

    // тут мы просто забираем заголовок и добавляем его к TextView
    fun appendStringFromButton(btn: Button) {
        if (didSelectOperation) {
            binding.tvResult.text = ""
            didSelectOperation = false
        }

        var buttonTitle = btn.getText().toString()

        // supress multiple 000.. and 01, etc
        var resultString = binding.tvResult.text.toString().plus(buttonTitle)
        binding.tvResult.text = resultString
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        btnadd = binding.btPlus
        btnsub = binding.btMinus
        btnmul = binding.btMultiply
        btndiv = binding.btDivide
        txtresult = binding.tvResult
        btneq = binding.btEquals
        btnclr = binding.btClear
        btndot = binding.btDot


        // setup digits listener
        for (btnId in arrayOf(R.id.btOne, R.id.btTwo, R.id.btThree, R.id.btFour, R.id.btFive, R.id.btSix, R.id.btSeven, R.id.btEight, R.id.btNine, R.id.btZero)){
            val btn = findViewById<View>(btnId) as Button
            btn.setOnClickListener {v -> appendStringFromButton(v as Button) }
        }

        btnclr!!.setOnClickListener {
            binding.tvResult.text = ""
            firstNumber = ""
            secondNumber = ""
            ac = ""
        }

        fun decimalsCount(str: String): Int {
            val arr = str.split(".")
            if (arr.size > 1) {
                return arr[1].length
            } else {
                return 0
            }
        }

        fun displayString(number: Double, decimalsCount: Int): String {
            var df = DecimalFormat("#")
            if (decimalsCount > 0) {
                var pattern = "#."
                for (i in 0..decimalsCount) {
                    pattern += "#"
                }
                df = DecimalFormat(pattern)
            }

            return df.format(number)
        }

        fun performOperation(firstStr: String, secondStr: String, operation: String): String {
            val first = firstStr.toDouble()
            val second = secondStr.toDouble()

            val decimalsCount = maxOf( decimalsCount(firstStr), decimalsCount(secondStr) )

            var result = second
            if (operation == "+") {
                result =  first + second
            } else if (operation == "-") {
                result =  first - second
            } else if (operation == "*") {
                result =  first * second
            } else if (operation == "/") {
                if(second == 0.0) {
                    result =  0.0
                } else {
                    result =  first / second
                }
            }
            if(operationComplete) {
                result = first
            }
            operationComplete

            return displayString( result, decimalsCount )
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

            secondNumber = binding.tvResult.text.toString()
            if (secondNumber.isEmpty()) {
                secondNumber = firstNumber
            }

            if(!operationComplete) {
                binding.tvResult.text = performOperation(firstNumber, secondNumber, ac)
            }
            else{
                binding.tvResult.text = performOperation(firstNumber, secondNumber, ac)
            }
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
