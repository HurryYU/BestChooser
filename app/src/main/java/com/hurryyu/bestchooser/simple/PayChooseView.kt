package com.hurryyu.bestchooser.simple

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import com.hurryyu.bestchooser.ChooserView
import com.hurryyu.bestchooser.simple.databinding.ViewPayChooseBinding

/**
 * ===================================================================
 * Author: HurryYu http://www.hurryyu.com & https://github.com/HurryYU
 * Email: cqbbyzh@gmial.com or 1037914505@qq.com
 * Time: 2020/3/8
 * Version: 1.0
 * Description:
 * ===================================================================
 */
class PayChooseView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ChooserView(context, attrs, defStyleAttr) {

    var paymentName: String = ""
        set(value) {
            field = value
            binding.tvPaymentName.text = value
        }
    var paymentImageResId: Int = 0
        set(value) {
            field = value
            binding.ivPaymentIcon.setImageResource(value)
        }

    private lateinit var binding: ViewPayChooseBinding
    private val gradientDrawable: GradientDrawable by lazy { GradientDrawable() }
    private val valueAnimator: ValueAnimator by lazy {
        val animator = ValueAnimator.ofInt(0, 255)
        animator.duration = 250
        animator
    }

    override fun createView(attrs: AttributeSet?) {
        binding = ViewPayChooseBinding.inflate(LayoutInflater.from(context), this, true)
        attrs?.apply {
            val obtainStyledAttributes =
                context.obtainStyledAttributes(this, R.styleable.PayChooseView)
            paymentName =
                obtainStyledAttributes.getString(R.styleable.PayChooseView_payment_name) ?: ""
            val imgId =
                obtainStyledAttributes.getResourceId(R.styleable.PayChooseView_payment_image_src, 0)
            if (imgId != 0) {
                paymentImageResId = imgId
            }
            obtainStyledAttributes.recycle()
        }
    }

    init {
        gradientDrawable.apply {
            setStroke(5, Color.RED)
            alpha = 0
            binding.root.background = this
        }
    }

    override fun onSelectChange(isSelect: Boolean) {
        if (isSelect) {
            selectedAnim()
        } else {
            unSelectedAnim()
        }
    }

    private fun selectedAnim() {
        valueAnimator.addUpdateListener {
            val value: Int = it.animatedValue as Int
            gradientDrawable.alpha = value
        }
        valueAnimator.start()
    }

    private fun unSelectedAnim() {
        valueAnimator.reverse()
    }
}