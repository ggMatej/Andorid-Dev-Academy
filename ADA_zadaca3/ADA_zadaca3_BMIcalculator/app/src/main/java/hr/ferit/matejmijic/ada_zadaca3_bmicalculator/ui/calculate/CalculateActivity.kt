package hr.ferit.matejmijic.ada_zadaca3_bmicalculator.ui.calculate

import android.content.Intent
import android.widget.Toast
import hr.ferit.matejmijic.ada_zadaca3_bmicalculator.calculations.Calculate
import hr.ferit.matejmijic.ada_zadaca3_bmicalculator.R
import hr.ferit.matejmijic.ada_zadaca3_bmicalculator.ui.common.BaseActivity
import hr.ferit.matejmijic.ada_zadaca3_bmicalculator.ui.result.ResultActivity
import kotlinx.android.synthetic.main.activity_calculate.*
import java.lang.NumberFormatException

class CalculateActivity : BaseActivity(),
    Calculate {

    override fun getResourceViewId(): Int =
        R.layout.activity_calculate

    override fun setUpUi(){
        calculateButton.setOnClickListener { calculate() }
    }

    private fun calculate() {
        try {
            val height= enterHeightView.text.toString().toDouble()
            val weight = enterWeightView.text.toString().toDouble()

            if(height in 1.0..2.5 && weight in 40.0..350.0){
                val result = calculateBMI(height = height,weight = weight)
                navigateToResult(result)
            }else Toast.makeText(this,"Invalid data!",Toast.LENGTH_LONG).show()

        } catch (e: NumberFormatException){
            Toast.makeText(this,"Invalid data!",Toast.LENGTH_LONG).show()
        }




    }

    private fun navigateToResult(result: Double) {
        val resultIntent = Intent(this,ResultActivity::class.java)
        resultIntent.putExtra(ResultActivity.EXTRA_RESULT,result)
        startActivity(resultIntent)
    }

}
