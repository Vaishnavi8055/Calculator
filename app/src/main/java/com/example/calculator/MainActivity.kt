package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Numbers
        button_zero.setOnClickListener { appendVal("0", false) }
        button_1.setOnClickListener { appendVal("1", false) }
        button_2.setOnClickListener { appendVal("2", false) }
        button_3.setOnClickListener { appendVal("3", false) }
        button_4.setOnClickListener { appendVal("4", false) }
        button_5.setOnClickListener { appendVal("5", false) }
        button_6.setOnClickListener { appendVal("6", false) }
        button_7.setOnClickListener { appendVal("7", false) }
        button_8.setOnClickListener { appendVal("8", false) }
        button_9.setOnClickListener { appendVal("9", false) }
        button_zero_zero.setOnClickListener { appendVal("00", false) }

        // Operators
        button_plus.setOnClickListener { appendVal("+", false) }
        button_minus.setOnClickListener { appendVal("-", false) }
        button_multiply.setOnClickListener { appendVal("*", false) }
        button_divide.setOnClickListener { appendVal("/", false) }
        button_zero_zero.setOnClickListener { appendVal("00", false) }
        button_mod.setOnClickListener { appendVal("%", false) }
        button_dot.setOnClickListener { appendVal(".", false) }

        button_delete.setOnClickListener {
            val expression = cal_expression.text.toString()
            if (expression.isNotEmpty()) {
                cal_expression.text = expression.substring(0, expression.length - 1)
            }
        }

        button_C.setOnClickListener {
            cal_expression.text = ""
            cal_result.text = ""
        }

        button_equal.setOnClickListener {
            try {
                val expression = ExpressionBuilder(cal_expression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    Toast.makeText(this, "Double", Toast.LENGTH_SHORT).show()
                    cal_result.text = longResult.toString()
                } else
                    cal_result.text = result.toString()

            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show();

                Log.d("EXCEPTION", "Message: ${e.message}")
            }
        }
    }

    private fun appendVal(string: String, isClear: Boolean) {
        if (isClear) {
            cal_expression.text = ""
            //     cal_result.text = ""
            cal_expression.append(string)
        } else {
            cal_expression.append(string)
        }
    }
}