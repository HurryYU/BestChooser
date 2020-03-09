package com.hurryyu.bestchooser

/**
 * ===================================================================
 * Author: HurryYu http://www.hurryyu.com & https://github.com/HurryYU
 * Email: cqbbyzh@gmial.com or 1037914505@qq.com
 * Time: 2020/3/7
 * Version: 1.0
 * Description:
 * ===================================================================
 */
abstract class OnChooseChangeListener {
    abstract fun onChanged(
        chooserView: ChooserView,
        viewTag: String,
        groupTag: String,
        isSelected: Boolean
    )

    open fun reachTheUpperLimit(groupTag: String, limit: Int) {}
}