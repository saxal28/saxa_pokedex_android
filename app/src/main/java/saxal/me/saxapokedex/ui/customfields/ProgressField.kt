package saxal.me.saxapokedex.ui.customfields

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import saxal.me.saxapokedex.R
import saxal.me.saxapokedex.databinding.FieldProgressBinding

class ProgressField @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    val binding: FieldProgressBinding =
        DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.field_progress, this, true)
}


@BindingAdapter("progress")
fun ProgressField.bindCheckboxFieldData(data: Double) {

    fun setWidth(percent: Double) {
        val width = binding.statValueTrack.measuredWidth * percent
        val density = resources.displayMetrics.density.toDouble()
        val height = (5 * density).toInt()

        if(width > 0.0) {
            binding.statValue.layoutParams = ConstraintLayout.LayoutParams(width.toInt(), height)

            val colorResource = when((percent * 180).toInt()) {
                in 0..25 -> R.drawable.progress_bar_error
                in 26..59 -> R.drawable.progress_bar_warning
                in 60..89 -> R.drawable.progress_bar_caution
                else -> R.drawable.progress_bar_success
            }

            binding.statValue.setBackgroundResource(colorResource)
        }
    }

    setWidth(data)
}