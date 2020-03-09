package com.hurryyu.bestchooser.simple

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.hurryyu.bestchooser.ChooserView
import kotlinx.android.synthetic.main.view_school_choose.view.*

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

    private lateinit var view: View
    var schoolName: String = ""
        set(value) {
            field = value
            view.tv_school_name.text = value
        }
    var schoolLevel: String = ""
        set(value) {
            field = value
            view.tv_school_level.text = value
        }

    override fun createView(attrs: AttributeSet?) {
        view = LayoutInflater.from(context).inflate(R.layout.view_school_choose, this)
        attrs.apply {
            val typed = context.obtainStyledAttributes(this, R.styleable.SchoolChooseView)
            schoolName = typed.getString(R.styleable.SchoolChooseView_school_name) ?: ""
            schoolLevel = typed.getString(R.styleable.SchoolChooseView_school_level) ?: ""
            typed.recycle()
        }
    }

    override fun onSelectChange(isSelect: Boolean) {
        if (isSelect) {
            view.tv_school_name.setTextColor(Color.parseColor("#4dd171"))
            view.iv_school_choose.visibility = View.VISIBLE
        } else {
            view.tv_school_name.setTextColor(Color.parseColor("#686868"))
            view.iv_school_choose.visibility = View.GONE
        }
    }
}