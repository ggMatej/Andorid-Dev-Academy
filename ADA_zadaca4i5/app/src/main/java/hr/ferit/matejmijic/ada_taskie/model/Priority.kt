package hr.ferit.matejmijic.ada_taskie.model

import androidx.annotation.ColorRes
import hr.ferit.matejmijic.ada_taskie.R


enum class Priority(@ColorRes private val colorRes: Int) {
    LOW(R.color.colorLow),
    MEDIUM(R.color.colorMedium),
    HIGH(R.color.colorHigh);

    fun getColor(): Int = colorRes
}