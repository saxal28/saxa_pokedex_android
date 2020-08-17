package saxal.me.saxapokedex.util

import android.util.Log
import java.util.*


/**
 * Timestamp
 * -- used for logging
 * */


class Timestamp {
    companion object {
        fun time() = Date().time

        fun getTimestampDifference(
            startTime: Long,
            endTime: Long,
            tag: String? = "DURATION"
        ): String {
            val diff: Long = endTime - startTime
            val seconds = diff / 1000.0000

            Log.i("$tag", "seconds: $seconds")
            Log.i("$tag", "milliseconds: $diff")

            return "Duration: "
        }
    }
}
