package saxal.me.saxapokedex.ui.bindingadapter

import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.request.transition.TransitionFactory
import kotlinx.android.synthetic.main.page_loader.view.*
import saxal.me.saxapokedex.MainActivity
import saxal.me.saxapokedex.R


class DrawableAlwaysCrossFadeFactory : TransitionFactory<Drawable> {
    private val resourceTransition: DrawableCrossFadeTransition = DrawableCrossFadeTransition(200, true) //customize to your own needs or apply a builder pattern
    override fun build(dataSource: DataSource?, isFirstResource: Boolean): Transition<Drawable> {
        return resourceTransition
    }
}

fun getProgressBarIndeterminate(): Drawable? {
    val attrs = intArrayOf(android.R.attr.indeterminateDrawable)
    val attrsIndeterminateDrawableIndex = 0
    val a: TypedArray =
        MainActivity.contextInstance!!.obtainStyledAttributes(android.R.style.Widget_ProgressBar, attrs)
    return try {
        a.getDrawable(attrsIndeterminateDrawableIndex)
    } finally {
        a.recycle()
    }
}

//.transition(DrawableTransitionOptions.with(DrawableAlwaysCrossFadeFactory()))
//.placeholder(getProgressBarIndeterminate())

@BindingAdapter("profileImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl)
        .placeholder(R.drawable.ic_poke_shadow)
        .into(view)
}