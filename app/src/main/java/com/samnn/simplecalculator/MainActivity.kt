package com.samnn.simplecalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private var saved: String? = null
    private var expression: ((String?, String?) -> Double)? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val equation = findViewById<TextView>(R.id.equation)
        equation.text = "0"
//
        setButtonListener(R.id.clear) {
            saved = null
            expression = null
            equation.text = "0"
        }
//
        setButtonListener(R.id.clear_entry) {
            equation.text = "0"
        }

        setButtonListener(R.id.backspace) {
            if (equation.text.length > 1)
                equation.text = equation.text.subSequence(0, equation.text.length - 1)
            else equation.text = "0"
        }

        setButtonListener(R.id.divide) {
            if (equation.text == "Error") return@setButtonListener
            expression = this::divide
            saved = equation.text.toString()
            equation.text = "0"
        }

        setButtonListener(R.id.multiply) {
            if (equation.text == "Error") return@setButtonListener
            expression = this::multiply
            saved = equation.text.toString()
            equation.text = "0"
        }

        setButtonListener(R.id.subtract) {
            if (equation.text == "Error") return@setButtonListener
            expression = this::subtract
            saved = equation.text.toString()
            equation.text = "0"
        }

        setButtonListener(R.id.add) {
            if (equation.text == "Error") return@setButtonListener
            expression = this::add
            saved = equation.text.toString()
            equation.text = "0"
        }
//
        setButtonListener(R.id.zero) {
            if (equation.text.toString() != "0") run {
                equation.text = "${equation.text}0"
            }
        }
//
        setButtonListener(R.id.one) {
            if (equation.text.toString() != "0") run {
                equation.text = "${equation.text}1"
            }
            else equation.text = "1"
        }
//
        setButtonListener(R.id.two) {
            if (equation.text.toString() != "0") run {
                equation.text = "${equation.text}2"
            }
            else equation.text = "2"
        }
//
        setButtonListener(R.id.three) {
            if (equation.text.toString() != "0") run {
                equation.text = "${equation.text}3"
            }
            else equation.text = "3"
        }

        setButtonListener(R.id.four) {
            if (equation.text.toString() != "0") run {
                equation.text = "${equation.text}4"
            }
            else equation.text = "4"
        }
//
        setButtonListener(R.id.five) {
            if (equation.text.toString() != "0") run {
                equation.text = "${equation.text}5"
            }
            else equation.text = "5"
        }
//
        setButtonListener(R.id.six) {
            if (equation.text.toString() != "0") run {
                equation.text = "${equation.text}6"
            }
            else equation.text = "6"
        }
//
        setButtonListener(R.id.seven) {
            if (equation.text.toString() != "0") run {
                equation.text = "${equation.text}7"
            }
            else equation.text = "7"
        }
//
        setButtonListener(R.id.eight) {
            if (equation.text.toString() != "0") run {
                equation.text = "${equation.text}8"
            }
            else equation.text = "8"
        }
//
        setButtonListener(R.id.nine) {
            if (equation.text.toString() != "0") run {
                equation.text = "${equation.text}9"
            }
            else equation.text = "9"
        }

        setButtonListener(R.id.dot) {
            equation.text = "${equation.text}."
        }

        setButtonListener(R.id.sign) {
            if (equation.text.toString() != "0") run {
                if (equation.text[0] == '-') {
                    equation.text = equation.text.subSequence(1, equation.text.length)
                }
                else {
                    equation.text = "-${equation.text}"
                }
            }
        }

        setButtonListener(R.id.equal) {
            if (expression != null) {
                val df = DecimalFormat("0.######")
                val result =  expression?.invoke(saved, equation.text.toString()) ?: Double.POSITIVE_INFINITY
                equation.text = if (result.isFinite()) df.format(result) else "Error"
                expression = null
            }
        }
    }

    private fun setButtonListener(id: Int, function: () -> Unit = {}) {
        val button = findViewById<Button>(id)
        button.setOnClickListener {function()}
    }

    private fun add(a: String?, b: String?): Double {
        val first = a?.toDouble() ?: .0
        val second = b?.toDouble() ?: .0
        return first + second
    }

    private fun subtract(a: String?, b: String?): Double {
        val first = a?.toDouble() ?: .0
        val second = b?.toDouble() ?: .0
        return first - second
    }

    private fun multiply(a: String?, b: String?): Double {
        val first = a?.toDouble() ?: .0
        val second = b?.toDouble() ?: .0
        return first * second
    }

    private fun divide(a: String?, b: String?): Double {
        val first = a?.toDouble() ?: .0
        val second = b?.toDouble() ?: .0
        return first / second
    }
}