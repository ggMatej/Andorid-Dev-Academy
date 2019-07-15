package hr.ferit.matejmijic.ada_zadaca3_yahtzee.model

import android.media.Image
import hr.ferit.matejmijic.ada_zadaca3_yahtzee.R


data class Dice (
    var image: Int
) {
    init {
        when(image){
            1 -> image = R.drawable.dice_one
            2 -> image = R.drawable.dice_two
            3 -> image = R.drawable.dice_three
            4 -> image = R.drawable.dice_four
            5 -> image = R.drawable.dice_five
            6 -> image = R.drawable.dice_six
        }
    }
}

