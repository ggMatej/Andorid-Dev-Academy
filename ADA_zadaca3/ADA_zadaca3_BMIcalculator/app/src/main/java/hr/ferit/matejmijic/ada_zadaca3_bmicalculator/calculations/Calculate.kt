package hr.ferit.matejmijic.ada_zadaca3_bmicalculator.calculations

interface Calculate {

    fun calculateBMI(weight: Double, height: Double): Double{
        return weight/Math.pow(height,2.0)
    }
}