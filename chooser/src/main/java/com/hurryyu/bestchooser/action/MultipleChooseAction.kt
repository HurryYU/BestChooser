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
internal class MultipleChooseAction : IChooseAction {

    private val selectedCountByGroupTag = mutableMapOf<String, Int>()

    override fun action(
        chooserView: ChooserView,
        chooserViewGroupManager: IGroupManagerContext,
        chooseChangeListener: OnChooseChangeListener?
    ) {
        val groupTag = chooserView.groupTag
        val maxSelectedCount = chooserViewGroupManager.getMaxSelectedCountByGroupTag(groupTag)
        var preCount = selectedCountByGroupTag[groupTag] ?: 0
        if (preCount == 0 && chooserView.isSelected) {
            return
        }
        if (preCount >= maxSelectedCount && !chooserView.isSelected) {
            chooseChangeListener?.reachTheUpperLimit(groupTag, maxSelectedCount)
            return
        }
        chooserView.isSelected = !chooserView.isSelected
        if (chooserView.isSelected) {
            selectedCountByGroupTag[groupTag] = ++preCount
        } else {
            selectedCountByGroupTag[groupTag] = --preCount
        }
        chooseChangeListener?.onChanged(
            chooserView,
            chooserView.viewTag,
            groupTag,
            chooserView.isSelected
        )
    }
}