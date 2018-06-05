package jp.example.hoge.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ten_key.view.*

class MainActivity : AppCompatActivity() {
    var answerVal = 0               // 表示している値
    var inputlen = 0               // 桁数
    var memoVal = 0               // 一時記憶

    // 演算モード
    enum class calMode {
        NOTMAL,
        PLUS,
        MINUS,
        MULT,
        DIV,
        EQUAL
    }

    var calModeNew: calMode = calMode.NOTMAL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 各ボタン押された時の処理登録
        buttonClear.setOnClickListener {
            answerVal = 0
            inputlen = 0
            memoVal = 0
            calModeNew = calMode.NOTMAL
            answerView.text = answerVal.toString()
        }
        calkey.button_0.setOnClickListener { valAdd(0) }
        calkey.button_1.setOnClickListener { valAdd(1) }
        calkey.button_2.setOnClickListener { valAdd(2) }
        calkey.button_3.setOnClickListener { valAdd(3) }
        calkey.button_4.setOnClickListener { valAdd(4) }
        calkey.button_5.setOnClickListener { valAdd(5) }
        calkey.button_6.setOnClickListener { valAdd(6) }
        calkey.button_7.setOnClickListener { valAdd(7) }
        calkey.button_8.setOnClickListener { valAdd(8) }
        calkey.button_9.setOnClickListener { valAdd(9) }
        calkey.button_add.setOnClickListener { calModeCheng(calMode.PLUS) }
        calkey.button_minus.setOnClickListener { calModeCheng(calMode.MINUS) }
        calkey.button_multiply.setOnClickListener { calModeCheng(calMode.MULT) }
        calkey.button_divide.setOnClickListener { calModeCheng(calMode.DIV) }
        calkey.button_equal.setOnClickListener { calModeCheng(calMode.EQUAL) }
    }

    // 数値ボタン押された 表示値×１０して１桁目に加算
    private fun valAdd(item: Int) {
        if (inputlen == 0) {
            answerVal = 0
        }
        if (10 > inputlen) {
            inputlen++
            answerVal = answerVal * 10 + item
        }
        answerView.text = answerVal.toString()
    }

    // 演算ボタンおされた
    private fun calModeCheng(setMode: calMode) {
        // 入力があったら
        if (0 < inputlen) {
            // 前の演算モードに応じて計算
            when (calModeNew) {
                calMode.PLUS -> answerVal += memoVal
                calMode.MINUS -> answerVal = memoVal - answerVal
                calMode.MULT -> answerVal = memoVal * answerVal
                calMode.DIV -> answerVal = if (0 == answerVal) 0 else memoVal / answerVal
                else -> {
                }
            }
        }
        // 次の計算の準備
        inputlen = 0
        calModeNew = setMode
        memoVal = answerVal
        // 結果表示
        answerView.text = answerVal.toString()
    }
}