package app.kopanev.calculatore2

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatore2.R
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
    private var btnprev: Button? = null

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

    fun changeBtColor(btn: Button) {
        if(binding.tvResult.text!=""&&binding.tvResult.text!="-")
            btn.getBackground().setColorFilter(getResources().getColor(R.color.btcolor2), PorterDuff.Mode.SRC_ATOP)
    }
    fun changeBtColorBack(btn: Button, btn1: Button, btn2: Button, btn3: Button) {
        btn.getBackground().setColorFilter(getResources().getColor(R.color.btcolor), PorterDuff.Mode.SRC_ATOP)
        btn1.getBackground().setColorFilter(getResources().getColor(R.color.btcolor), PorterDuff.Mode.SRC_ATOP)
        btn2.getBackground().setColorFilter(getResources().getColor(R.color.btcolor), PorterDuff.Mode.SRC_ATOP)
        btn3.getBackground().setColorFilter(getResources().getColor(R.color.btcolor), PorterDuff.Mode.SRC_ATOP)
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
            btn.setOnClickListener { v -> appendStringFromButton(v as Button)
                btnprev = v as Button
            }
        }

        var result = 0.0

        btnclr!!.setOnClickListener {
            binding.tvResult.text = ""
            firstNumber = ""
            secondNumber = ""
            ac = ""
            previousAC = ""
            cdecimalsCount = false
            changeBtColorBack(btnadd!!,btnsub!!,btndiv!!,btnmul!!)
            btnprev = it as Button
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
            firstStr.replace(",",".")
            secondStr.replace(",",".")
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
            firstNumber.replace(",",".")

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
            if(binding.tvResult.text==""){
                changeBtColorBack(btnadd!!,btnsub!!,btndiv!!,btnmul!!)
            }
            btnprev = it as Button
        }

        btndot!!.setOnClickListener {
            if(!binding.tvResult.text.contains(".")){
                addDot()
                dotAdded = true
            }

            // reset previous AC
            previousAC = ""

            btnprev = it as Button
        }

        btneq!!.setOnClickListener {
            firstNumber.replace(",",".")
            changeBtColorBack(btnadd!!,btnsub!!,btndiv!!,btnmul!!)
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
            binding.tvResult.text = binding.tvResult.text.toString().replace(",",".")
            if(cdecimalsCount) {
                binding.tvResult.text = displayString(result, 3)
            }
            previousAC = "="
            Log.d("CALCULATOR1", "pac == " + previousAC)
            btnprev = it as Button
        }

        fun operationSelected(btn: Button) {
                if (previousAC != "=") {
                    previousAC = ac
                }
                val btnTitle = btn.getText().toString()

                // if we have first & (textBinding has number) & previousAC & previousAC != '='
                // => perform first previousAC (textBinding has number)
                // => save result to first

                val potentialSecondNumber = binding.tvResult.text.toString()
                if (firstNumber.isNotEmpty() &&
                    firstNumber != "." &&
                    previousAC.isNotEmpty() &&
                    previousAC != "=" &&
                    potentialSecondNumber.isNotEmpty() &&
                    !isStringOperation(potentialSecondNumber)
                ) {
                    Log.d("CALCULATOR1", "from line 276 ac == " + ac)
                    Log.d("CALCULATOR1", "from line 277 pac == " + previousAC)
                    firstNumber = performOperation(firstNumber, potentialSecondNumber, previousAC)
                    binding.tvResult.text = firstNumber
                    previousAC = ac
                    Log.d("CALCULATOR1", "from line 282 pac == " + previousAC)
                    // binding.tvResult.text = btnTitle = firstNumber
                } else if (!isStringOperation(binding.tvResult.text.toString())) {
                    firstNumber = binding.tvResult.text.toString()
                }
                ac = btnTitle
                Log.d(
                    "CALCULATOR1",
                    "from operationSelected line 284:" + btnprev!!.getText().toString()
                )

                // mark as true, so when user taps on digit next time we'll reset the text view
                didSelectOperation = true

                // we show an operation if the first number is filled only
                // edge case is when first number is negative
                val isPreviousButtonAnOperation = isStringOperation(btnprev!!.getText().toString())

                val isFirstNumberAndNegative = ((firstNumber.isEmpty() || isPreviousButtonAnOperation) && btnTitle == "-")

                if (isFirstNumberAndNegative) {
                    binding.tvResult.text = "-"
                } else {
                    // in all other cases let's just clear the text view -)
                    // binding.tvResult.text = ""
                }
        }

        fun makeAnim(btn: Button) {
            val viewsToFade = ArrayList<View>()

            viewsToFade.add(btn)
            if(binding.tvResult.text != ""&&binding.tvResult.text != "-") {
                for (v in viewsToFade) {
                    v.alpha = 1f
                }

                for (v in viewsToFade) {
                    v.animate().alpha(0.5f).setDuration(300).start()
                }

                for (v in viewsToFade) {
                    v.alpha = 0.5f
                }

                for (v in viewsToFade) {
                    changeBtColor(btn)
                    v.animate().alpha(1f).setDuration(450).start()
                }
                changeBtColor(btn)
            }
        }

        btnadd!!.setOnClickListener { v -> operationSelected(v as Button)
            changeBtColorBack(btnadd!!,btnsub!!,btndiv!!,btnmul!!)
            makeAnim(btnadd!!)
            changeBtColor(btnadd!!)
            btnprev = v as Button
        }
        btnsub!!.setOnClickListener { v -> operationSelected(v as Button)
            changeBtColorBack(btnadd!!,btnsub!!,btndiv!!,btnmul!!)
                makeAnim(btnsub!!)
                changeBtColor(btnsub!!)
                btnprev = v as Button

        }
        btnmul!!.setOnClickListener { v -> operationSelected(v as Button)
            changeBtColorBack(btnadd!!,btnsub!!,btndiv!!,btnmul!!)
            makeAnim(btnmul!!)
            changeBtColor(btnmul!!)
            btnprev = v as Button
        }
        btndiv!!.setOnClickListener { v -> operationSelected(v as Button)
            changeBtColorBack(btnadd!!,btnsub!!,btndiv!!,btnmul!!)
            makeAnim(btndiv!!)
            changeBtColor(btndiv!!)
            btnprev = v as Button
        }
    }
}