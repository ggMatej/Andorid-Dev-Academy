package hr.ferit.matejmijic.ada_zadaca3_yahtzee.model

import android.app.Application
import android.graphics.Color
import android.provider.Settings.Global.getString
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import hr.ferit.matejmijic.ada_zadaca3_yahtzee.R
import hr.ferit.matejmijic.ada_zadaca3_yahtzee.ui.rolldice.RollDiceActivity
import kotlinx.android.synthetic.main.activity_roll_dice.*
import kotlin.random.Random

object DiceRoller {

    fun rollDice(imageViews: List<ImageView>, view: TextView) {

        val dices: MutableList<Dice> = mutableListOf()

        for (i in 0..5) {
            dices.add(Dice(Random.nextInt(1,7)))
            if(imageViews[i].colorFilter == null) imageViews[i].setImageResource(dices[i].image)
        }

        when(view.text){
            "3" -> view.text = "2"
            "2" -> view.text = "1"
            "1" -> {
                for (i in 0..5){
                    imageViews[i].clearColorFilter()
                }
                view.text = "Finished!"
            }
            "Finished!" -> {
                for (i in 0..5){
                    imageViews[i].setImageResource(R.drawable.dice_unknown)
                }
                view.text = "3"
            }
        }
    }

    fun selectDice(imageViews: List<ImageView>, i: Int, view: TextView) {

        if(view.text != "3" && view.text != "Finished!"){
            if (imageViews[i].colorFilter == null){
                imageViews[i].setColorFilter(Color.RED)
            } else {
                imageViews[i].clearColorFilter()
            }
        }
    }
}