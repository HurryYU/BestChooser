package com.hurryyu.bestchooser

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

/**
 * ===================================================================
 * Author: HurryYu http://www.hurryyu.com & https://github.com/HurryYU
 * Email: 1037914505@qq.com
 * Time: 2020/3/6
 * Version: 1.0
 * Description:
 * ===================================================================
 */
abstract class ChooserView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), View.OnClickListener {

    lateinit var groupTag: String
        private set

    var viewTag: String = ""

    private var groupManager: ChooserViewGroupManager? = null

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        attrs?.apply {
            val typed = context.obtainStyledAttributes(attrs, R.styleable.ChooserView)
            viewTag = typed.getString(R.styleable.ChooserView_viewTag) ?: ""
            typed.recycle()
        }
        createView(attrs)
        setOnClickListener(this)
    }

    internal fun addGroupManager(groupManager: ChooserViewGroupManager, groupTag: String) {
        this.groupManager = groupManager
        this.groupTag = groupTag
        isSelected = false
    }

    override fun onClick(v: View?) {
        groupManager?.selected(this)
    }

    override fun setSelected(selected: Boolean) {
        val isCurrentSelected = isSelected
        super.setSelected(selected)
        if (selected != isCurrentSelected) {
            onSelectChange(selected)
        }
    }

    /**
     * 子类将xml转为View
     * 例如:LayoutInflater.from(mContext).inflate(R.layout.xxx, this)
     */
    abstract fun createView(attrs: AttributeSet?)

    abstract fun onSelectChange(isSelect: Boolean)
}