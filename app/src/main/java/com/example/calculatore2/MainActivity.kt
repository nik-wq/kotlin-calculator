package com.example.calculatore2

import android.os.Bundle
import android.util.Log
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
    private var btnrem: Button? = null

    private var firstNumber = ""
    private var secondNumber = ""
    private var ac = ""
    private var previousAC = ""

    private var didSelectOperation = false
    private var dotAdded = false
    private var cdecimalsCount = false

    // helpers

    fun isStringOperation(string: String): Boolean {
        return (string == "+" || string == "/" || string == "*" || string == "-")
    }

    fun removeRedundantZero(sourceString: String): String {
        if(sourceString.length == 2 && sourceString.startsWith("0")){
            return sourceString.substring(1)
        } else {
            return sourceString
        }
    }

    // тут мы просто забираем заголовок и добавляем его к TextView
    fun appendStringFromButton(btn: Button) {
        if (didSelectOperation) {
            binding.tvResult.text = ""
            didSelectOperation = false
        }

        var appendingString = ""

        // if the string is empty and user selected '-' before and this is entering first number
        // a little bit complicated, could be easier I guess... let's think about it, Nikita =)
        if (firstNumber.isEmpty() && binding.tvResult.text.toString().isEmpty() && ac == "-") {
            appendingString = "-"
        }

        appendingString += btn.getText().toString()

        var resultString = binding.tvResult.text.toString().plus(appendingString)
        binding.tvResult.text = removeRedundantZero(resultString)
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
        btnrem = binding.btRemove


        // setup digits listener
        for (btnId in arrayOf(R.id.btOne, R.id.btTwo, R.id.btThree, R.id.btFour, R.id.btFive, R.id.btSix, R.id.btSeven, R.id.btEight, R.id.btNine, R.id.btZero)){
            val btn = findViewById<View>(btnId) as Button
            btn.setOnClickListener { v -> appendStringFromButton(v as Button) }
        }

        var result = 0.0

        btnclr!!.setOnClickListener {
            binding.tvResult.text = ""
            firstNumber = ""
            secondNumber = ""
            ac = ""
            previousAC = ""
            cdecimalsCount = false
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

            result = second
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
                    if(decimalsCount(result.toString())!=0){
                        cdecimalsCount = true
                    }
                }
            }

            // update firstNumber so we can reuse it if user press '=' again
            firstNumber = result.toString()

            return displayString( result, decimalsCount )
        }

        fun dropLastString() {
            if(ac=="") {
                firstNumber = binding.tvResult.text.toString()
                binding.tvResult.text = firstNumber.dropLast(1)
            }
            else {
                secondNumber = binding.tvResult.text.toString()
                binding.tvResult.text = secondNumber.dropLast(1)
            }
        }



        fun addDot() {
            binding.tvResult.text = binding.tvResult.text.toString().plus(".")
        }
        btnrem!!.setOnClickListener {
            dropLastString()
        }

        btndot!!.setOnClickListener {
            if(!binding.tvResult.text.contains(".")){
                addDot()
                dotAdded = true
            }

            // reset previous AC
            previousAC = ""
        }

        btneq!!.setOnClickListener {
            if (isStringOperation( binding.tvResult.text.toString())) {
                return@setOnClickListener
            }

            if (ac.isEmpty()) {
                // do nothing in case there is no operation selected
                return@setOnClickListener
            }

            if (firstNumber == "." || firstNumber.isEmpty()) {
                firstNumber = "0"
            }

            Log.d("CALCULATOR", "ac == " + ac + ", first == " + firstNumber)

            if (previousAC != "=") {
                secondNumber = binding.tvResult.text.toString()
                if (secondNumber.isEmpty()) {
                    secondNumber = "0"
                }
            } else {
                // repeat the action
                // do nothing, all the numbers are set up already
            }

            Log.d("CALCULATOR", "first == " + firstNumber + ", second == " + secondNumber)

            binding.tvResult.text = performOperation(firstNumber, secondNumber, ac)
            if(cdecimalsCount) {
                binding.tvResult.text = displayString(result, 3)
            }

            previousAC = "="
        }

        fun operationSelected(btn: Button) {
            previousAC = ac
            val btnTitle = btn.getText().toString()

            // just to be sure...
            if (!isStringOperation(binding.tvResult.text.toString())) {
                firstNumber = binding.tvResult.text.toString()
            }
            ac = btnTitle

            // mark as true, so when user taps on digit next time we'll reset the text view
            didSelectOperation = true

            // we show an operation if the first number is filled only
            // edge case is when first number is negative
            val isFirstNumberAndNegative = ( firstNumber.isEmpty() && btnTitle == "-" )
            val isFirstNumberPresentAlready = !firstNumber.isEmpty()

            if (isFirstNumberAndNegative || isFirstNumberPresentAlready) {
                binding.tvResult.text = btnTitle
            } else {
                // in all other cases let's just clear the text view -)
                binding.tvResult.text = ""
            }
        }

        btnadd!!.setOnClickListener { v -> operationSelected(v as Button) }
        btnsub!!.setOnClickListener { v -> operationSelected(v as Button) }
        btnmul!!.setOnClickListener { v -> operationSelected(v as Button) }
        btndiv!!.setOnClickListener { v -> operationSelected(v as Button) }

    }
}