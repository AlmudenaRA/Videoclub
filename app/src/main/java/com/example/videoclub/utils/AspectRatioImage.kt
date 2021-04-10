package com.example.videoclub.utils

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.example.videoclub.R

class AspectRatioImage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var ratio: Float = DEFAULT_RATIO

    init {
        attrs?.let {
            val a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImage)
            with(a) {
                ratio = getFloat(R.styleable.AspectRatioImage_ratio, DEFAULT_RATIO)
                recycle()
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var width = measuredWidth //Coge el ancho predeterminado
        var height = measuredHeight //Coge el alto predeterminado

        if (width == 0 && height == 0) {
            return
        }

        //Iguala las dimensiones para que sean las mismas
        if (width > 0) {
            height = (width * ratio).toInt()
        } else {
            width = (height / ratio).toInt()
        }

        setMeasuredDimension(width, height)
    }

    companion object {
        const val DEFAULT_RATIO = 1F
    }
}