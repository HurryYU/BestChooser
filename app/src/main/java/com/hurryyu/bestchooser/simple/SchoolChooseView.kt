package com.hurryyu.bestchooser.simple

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.hurryyu.bestchooser.ChooserView
import com.hurryyu.bestchooser.simple.databinding.ViewSchoolChooseBinding

/**
 * ===================================================================
 * Author: HurryYu http://www.hurryyu.com & https://github.com/HurryYU
 * Email: cqbbyzh@gmial.com or 1037914505@qq.com
 * Time: 2020/3/9
 * Version: 1.0
 * Description:
 * ===================================================================
 */
class SchoolChooseView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ChooserView(context, attrs, defStyleAttr) {

    private lateinit var binding: ViewSchoolChooseBinding
    var schoolName: String = ""
        set(value) {
            field = value
            binding.tvSchoolName.text = value
        }
    var schoolLevel: String = ""
        set(value) {
            field = value
            binding.tvSchoolLevel.text = value
        }

    override fun createView(attrs: AttributeSet?) {
        binding = ViewSchoolChooseBinding.inflate(LayoutInflater.from(context), this, true)
        attrs.apply {
            val typed = context.obtainStyledAttributes(this, R.styleable.SchoolChooseView)
            schoolName = typed.getString(R.styleable.SchoolChooseView_school_name) ?: ""
            schoolLevel = typed.getString(R.styleable.SchoolChooseView_school_level) ?: ""
            typed.recycle()
        }
    }

    override fun onSelectChange(isSelect: Boolean) {
        if (isSelect) {
            binding.tvSchoolName.setTextColor(Color.parseColor("#4dd171"))
            binding.ivSchoolChoose.visibility = View.VISIBLE
        } else {
            binding.tvSchoolName.setTextColor(Color.parseColor("#686868"))
            binding.ivSchoolChoose.visibility = View.GONE
        }
    }
}