package com.hurryyu.bestchooser.action

import com.hurryyu.bestchooser.ChooserView
import com.hurryyu.bestchooser.IGroupManagerContext
import com.hurryyu.bestchooser.OnChooseChangeListener

/**
 * ===================================================================
 * Author: HurryYu http://www.hurryyu.com & https://github.com/HurryYU
 * Email: cqbbyzh@gmial.com or 1037914505@qq.com
 * Time: 2020/3/7
 * Version: 1.0
 * Description:
 * ===================================================================
 */
internal class SingleChooseAction : IChooseAction {

    private val preSelectedByGroupTag = mutableMapOf<String, ChooserView>()

    override fun action(
        chooserView: ChooserView,
        chooserViewGroupManager: IGroupManagerContext,
        chooseChangeListener: OnChooseChangeListener?
    ) {
        val groupTag = chooserView.groupTag
        val isSelected = chooserView.isSelected
        cancelPreSelectedView(chooserView)
        preSelectedByGroupTag[groupTag] = chooserView
        if (!isSelected) {
            chooserView.isSelected = true
            chooseChangeListener?.onChanged(chooserView, chooserView.viewTag, groupTag, true)
        }
    }

    private fun cancelPreSelectedView(chooserView: ChooserView) {
        val preChooserView = preSelectedByGroupTag[chooserView.groupTag]
        if (preChooserView != chooserView) {
            preChooserView?.isSelected = false
        }
    }
}