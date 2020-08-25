package saxal.me.saxapokedex.util

fun Double.roundTo(decimals: Int = 2) = "%.${decimals}f".format(this).toDouble()
