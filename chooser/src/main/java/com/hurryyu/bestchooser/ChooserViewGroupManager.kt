package com.hurryyu.bestchooser

import com.hurryyu.bestchooser.action.IChooseAction
import com.hurryyu.bestchooser.action.MultipleChooseAction
import com.hurryyu.bestchooser.action.SingleChooseAction

/**
 * ===================================================================
 * Author: HurryYu http://www.hurryyu.com & https://github.com/HurryYU
 * Email: cqbbyzh@gmial.com or 1037914505@qq.com
 * Time: 2020/3/6
 * Version: 1.0
 * Description:
 * ===================================================================
 */
class ChooserViewGroupManager private constructor(
    val chooserViewByGroupTag: MutableMap<String, MutableList<ChooserView>>,
    private val everyChooserModeInfoByGroupTag: MutableMap<String, ChooserModeInfo>
) : IGroupManagerContext {

    private var chooseChangeListener: OnChooseChangeListener? = null

    companion object {
        private const val DEFAULT_GROUP_TAG_NAME = "default_group_tag"
    }

    init {
        chooserViewByGroupTag.forEach { keyValue ->
            keyValue.value.forEach {
                it.addGroupManager(this, keyValue.key)
            }
        }
    }

    fun selected(chooserView: ChooserView) {
        val groupTag = chooserView.groupTag
        getGroupTagChooserMode(groupTag)?.action(chooserView, this, chooseChangeListener)
    }

    private fun getGroupTagChooserMode(groupTag: String) =
        everyChooserModeInfoByGroupTag[groupTag]?.chooserMode

    fun setOnChooseChangeListener(onChooseChangeListener: OnChooseChangeListener) {
        this.chooseChangeListener = onChooseChangeListener
    }

    override fun getMaxSelectedCountByGroupTag(groupTag: String): Int =
        everyChooserModeInfoByGroupTag[groupTag]?.maxCount ?: Int.MAX_VALUE

    class Builder @JvmOverloads constructor(private val chooserMode: ChooserMode = ChooserMode.MODE_SINGLE) {
        private var allGroupTagMaxSelectCount: Int = Int.MAX_VALUE
        private val chooserViewByGroupTag = mutableMapOf<String, MutableList<ChooserView>>()
        private val everyChooserModeInfoByGroupTag = mutableMapOf<String, ChooserModeInfo>()
        private val overrideChooserModeInfoByGroupTag = mutableMapOf<String, ChooserModeInfo>()

        private var singleMode: SingleChooseAction? = null
        private var multipleMode: MultipleChooseAction? = null

        fun allGroupMaxCount(maxCount: Int) = apply {
            if (maxCount > 1) {
                allGroupTagMaxSelectCount = maxCount
            }
        }

        @JvmOverloads
        fun addChooserView(
            groupTag: String = DEFAULT_GROUP_TAG_NAME,
            vararg chooserView: ChooserView
        ) = apply {
            if (chooserView.isNotEmpty()) {
                val chooserViewList = chooserViewByGroupTag.getOrPut(groupTag) {
                    mutableListOf()
                }
                chooserViewList.addAll(chooserView)
            }
        }

        @JvmOverloads
        fun chooserModeByGroupTag(
            groupTag: String, chooserMode: ChooserMode,
            maxCount: Int = Int.MAX_VALUE
        ) = apply {
            if (chooserMode == ChooserMode.MODE_MULTIPLE && maxCount < 2) {
                return@apply
            }
            overrideChooserModeInfoByGroupTag[groupTag] =
                ChooserModeInfo(getMode(chooserMode), maxCount)
        }

        fun build(): ChooserViewGroupManager {
            val overrideGroupTagSet = overrideChooserModeInfoByGroupTag.keys
            chooserViewByGroupTag.keys.filter { !overrideGroupTagSet.contains(it) }.forEach {
                everyChooserModeInfoByGroupTag[it] =
                    ChooserModeInfo(getMode(chooserMode), allGroupTagMaxSelectCount)
            }
            everyChooserModeInfoByGroupTag.putAll(overrideChooserModeInfoByGroupTag)
            return ChooserViewGroupManager(chooserViewByGroupTag, everyChooserModeInfoByGroupTag)
        }

        private fun getMode(chooserMode: ChooserMode) = when (chooserMode) {
            ChooserMode.MODE_SINGLE -> {
                singleMode ?: SingleChooseAction()
            }
            ChooserMode.MODE_MULTIPLE -> {
                multipleMode ?: MultipleChooseAction()
            }
        }
    }

    data class ChooserModeInfo(
        val chooserMode: IChooseAction,
        val maxCount: Int
    )
}