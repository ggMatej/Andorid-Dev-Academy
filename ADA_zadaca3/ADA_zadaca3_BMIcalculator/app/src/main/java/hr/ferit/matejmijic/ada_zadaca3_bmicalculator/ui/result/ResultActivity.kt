package hr.ferit.matejmijic.ada_zadaca3_bmicalculator.ui.result

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import hr.ferit.matejmijic.ada_zadaca3_bmicalculator.R
import hr.ferit.matejmijic.ada_zadaca3_bmicalculator.ui.common.BaseActivity
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : BaseActivity() {
    companion object {
        const val EXTRA_RESULT = "EXTRA_RESULT"
        const val NO_RESULT = -1.0
    }

    override fun getResourceViewId(): Int = R.layout.activity_result

    override fun setUpUi() {
        val result = intent.getDoubleExtra(EXTRA_RESULT, NO_RESULT)
        val weightCategories = resources.getStringArray(R.array.weightCategories)
        val advices = resources.getStringArray(R.array.advice)
        resultDoubleView.text = "%.2f".format(result)
        when{
            result < 18.5 -> {
                resultStringView.text = weightCategories[0]
                adviceText.text = advices[0]
                resultImage.setColorFilter(Color.RED)
            }
            result in 18.5..24.9 -> {
                resultStringView.text = weightCategories[1]
                adviceText.text = advices[1]
                resultImage.setColorFilter(Color.GREEN)
            }
            result in 25.0..29.9-> {
                resultStringView.text = weightCategories[2]
                adviceText.text = advices[2]
                resultImage.setColorFilter(Color.YELLOW)
            }
            result >= 30 -> {
                resultStringView.text = weightCategories[3]
                adviceText.text = advices[3]
                resultImage.setColorFilter(Color.RED)
            }
        }
    }


}
