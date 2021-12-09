package com.hurryyu.bestchooser.simple

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import com.hurryyu.bestchooser.ChooserView
import com.hurryyu.bestchooser.simple.databinding.ViewMenuChooseBinding

/**
 * ===================================================================
 * Author: HurryYu http://www.hurryyu.com & https://github.com/HurryYU
 * Email: cqbbyzh@gmial.com or 1037914505@qq.com
 * Time: 2020/3/9
 * Version: 1.0
 * Description:
 * ===================================================================
 */
class MenuChooseView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ChooserView(context, attrs, defStyleAttr) {

    private lateinit var binding: ViewMenuChooseBinding

    var foodName = ""
        set(value) {
            field = value
            binding.tvFoodName.text = value
        }
    var foodImageResId = 0
        set(value) {
            field = value
            binding.ivFoodImg.setImageResource(value)
        }

    override fun createView(attrs: AttributeSet?) {
        binding = ViewMenuChooseBinding.inflate(LayoutInflater.from(context), this, true)
        attrs.apply {
            val typedArray =
                context.obtainStyledAttributes(this, R.styleable.MenuChooseView)
            foodName = typedArray.getString(R.styleable.MenuChooseView_food_name) ?: ""
            val foodImg = typedArray.getResourceId(R.styleable.MenuChooseView_food_img, 0)
            if (foodImg != 0) {
                foodImageResId = foodImg
            }
            typedArray.recycle()
        }
    }

    override fun onSelectChange(isSelect: Boolean) {
        if (isSelect) {
            binding.tvFoodName.setTextColor(Color.parseColor("#FF8000"))
        } else {
            binding.tvFoodName.setTextColor(Color.parseColor("#6D6D6D"))
        }
    }
}