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
    private var cifpd = false

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
            cifpd = false
        }

        fun decimalsCount(str: String): Int {
            firstNumber.replace(",",".")
            secondNumber.replace(",",".")
            val arr = str.split(".")
            if (arr.size > 1) {
                return arr[1].length
            } else {
                return 0
            }
        }

        fun displayString(number: Double, decimalsCount: Int): String {
            firstNumber.replace(",",".")
            secondNumber.replace(",",".")
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
            firstStr.replace(",",".")
            secondStr.replace(",",".")
            val first = firstStr.toDouble()
            val second = secondStr.toDouble()

            var decimalsCount = maxOf( decimalsCount(firstStr), decimalsCount(secondStr) )

            result = second
            if (operation == "+") {
                first.toString().replace(",",".")
                result =  first + second
            } else if (operation == "-") {
                first.toString().replace(",",".")
                result =  first - second
            } else if (operation == "*") {
                first.toString().replace(",",".")
                result =  first * second
            } else if (operation == "/") {
                first.toString().replace(",",".")
                if(second == 0.0) {
                    result =  0.0
                } else {
                    result =  first / second
                    decimalsCount = decimalsCount(result.toString())
                }
            }

            // update firstNumber so we can reuse it if user press '=' again
            firstNumber = result.toString()

            return displayString( result, decimalsCount ).replace(",", ".")
        }

        fun dropLastString() {
            if(ac=="") {
                firstNumber = binding.tvResult.text.toString()
                binding.tvResult.text = firstNumber.dropLast(1)
                binding.tvResult.text = binding.tvResult.text.toString().replace(",",".")
            }
            else {
                secondNumber = binding.tvResult.text.toString()
                binding.tvResult.text = secondNumber.dropLast(1)
                binding.tvResult.text = binding.tvResult.text.toString().replace(",",".")
            }
        }

        fun addDot() {
            binding.tvResult.text = binding.tvResult.text.toString().plus(".")
        }
        btnrem!!.setOnClickListener {
            firstNumber.replace(",",".")
            secondNumber.replace(",",".")
            dropLastString()
        }

        btndot!!.setOnClickListener {
            if(!binding.tvResult.text.contains(".")){
                addDot()
                firstNumber.replace(",",".")
                secondNumber.replace(",",".")
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

            firstNumber.replace(",",".")
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


            previousAC = "="
        }

        fun operationSelected(btn: Button) {
            firstNumber=binding.tvResult.text.toString()

            if(firstNumber!=""&&!isStringOperation(binding.tvResult.text.toString())){
                secondNumber = binding.tvResult.text.toString()
                firstNumber = performOperation(firstNumber,secondNumber,previousAC)
            }
            else if (!isStringOperation(binding.tvResult.text.toString())) {
                firstNumber.replace(",",".")
                firstNumber = binding.tvResult.text.toString()
            }

            previousAC = ac
            val btnTitle = btn.getText().toString()
            ac = btnTitle

            // mark as true, so when user taps on digit next time we'll reset the text view
            didSelectOperation = true

            // we show an operation if the first number is filled only
            // edge case is when first number is negative
            val isFirstNumberAndNegative = ( firstNumber.isEmpty() && btnTitle == "-" )
            val isFirstNumberPresentAlready = firstNumber.isNotEmpty()

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