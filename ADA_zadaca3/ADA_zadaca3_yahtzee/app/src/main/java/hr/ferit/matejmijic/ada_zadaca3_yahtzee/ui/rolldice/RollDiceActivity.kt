package hr.ferit.matejmijic.ada_zadaca3_yahtzee.ui.rolldice

import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.drawable.Drawable
import android.location.GpsStatus
import android.media.Image
import android.net.sip.SipSession
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import hr.ferit.matejmijic.ada_zadaca3_yahtzee.R
import hr.ferit.matejmijic.ada_zadaca3_yahtzee.model.Dice
import hr.ferit.matejmijic.ada_zadaca3_yahtzee.model.DiceRoller
import hr.ferit.matejmijic.ada_zadaca3_yahtzee.ui.common.BaseActivity
import kotlinx.android.synthetic.main.activity_roll_dice.*
import kotlin.random.Random


class RollDiceActivity : BaseActivity(){


    override fun getViewResourceId(): Int = R.layout.activity_roll_dice

    override fun setUpUi() {
        val imageViews = listOf(firstDiceView,secondDiceView,thirdDiceView,
            fourthDiceView,fifthDiceView,sixthDiceView)

        rollDiceButton.setOnClickListener { DiceRoller.rollDice(imageViews, numberOfLeftRollsView) }

        for (i in 0..5) imageViews[i].setOnClickListener { DiceRoller.selectDice(imageViews,i,numberOfLeftRollsView) }
    }


}

