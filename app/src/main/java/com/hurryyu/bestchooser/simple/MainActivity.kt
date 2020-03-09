package com.hurryyu.bestchooser.simple

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hurryyu.bestchooser.ChooserMode
import com.hurryyu.bestchooser.ChooserView
import com.hurryyu.bestchooser.ChooserViewGroupManager
import com.hurryyu.bestchooser.OnChooseChangeListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        singleChoose()
        multipleChoose()
        multipleChooseWitLimit()
        menuChoose()
    }

    /**
     * 菜单选择(多选+单选混合)
     */
    private fun menuChoose() {
        val menuChooserManager = ChooserViewGroupManager.Builder()
            .addChooserView("meatGroup", yuxiangrousi_view, xiaochaorou_view, huiguorou_view)
            .addChooserView(
                "vegetable",
                douya_view,
                mapodoufu_view,
                yuxiangqiezi_view,
                tudousi_view
            )
            .addChooserView("soup", fanqiejidantang_view, zicaidantang_view)
            .chooserModeByGroupTag("meatGroup", ChooserMode.MODE_MULTIPLE, 2)
            .build()

        menuChooserManager.setOnChooseChangeListener(object : OnChooseChangeListener() {
            override fun onChanged(
                chooserView: ChooserView,
                viewTag: String,
                groupTag: String,
                isSelected: Boolean
            ) {

            }

            override fun reachTheUpperLimit(groupTag: String, limit: Int) {
                Toast.makeText(this@MainActivity, "您只能选择${limit}个荤菜", Toast.LENGTH_SHORT).show()
            }

        })
    }

    /**
     * 多选且限制数量
     */
    private fun multipleChooseWitLimit() {
        val selectedList = mutableListOf<String>()

        val multipleChooserManager = ChooserViewGroupManager.Builder(ChooserMode.MODE_MULTIPLE)
            .allGroupMaxCount(2)
            .addChooserView(
                chooserView = *arrayOf(
                    qinghua_school_view_1,
                    cqcet_school_view_1,
                    zhijiao_school_view_1,
                    ligong_school_view_1
                )
            )
            .build()

        multipleChooserManager.setOnChooseChangeListener(object : OnChooseChangeListener() {
            override fun onChanged(
                chooserView: ChooserView,
                viewTag: String,
                groupTag: String,
                isSelected: Boolean
            ) {
                if (isSelected) {
                    selectedList.add(viewTag)
                } else {
                    selectedList.remove(viewTag)
                }
                Toast.makeText(this@MainActivity, selectedList.toString(), Toast.LENGTH_SHORT)
                    .show()
            }

            override fun reachTheUpperLimit(groupTag: String, limit: Int) {
                Toast.makeText(this@MainActivity, "达到上限:$limit", Toast.LENGTH_SHORT).show()
            }

        })
    }

    /**
     * 多选
     */
    private fun multipleChoose() {
        val selectedList = mutableListOf<String>()

        val multipleChooserManager = ChooserViewGroupManager.Builder(ChooserMode.MODE_MULTIPLE)
            .addChooserView(
                chooserView = *arrayOf(
                    qinghua_school_view,
                    cqcet_school_view,
                    zhijiao_school_view,
                    ligong_school_view
                )
            )
            .build()

        multipleChooserManager.setOnChooseChangeListener(object : OnChooseChangeListener() {
            override fun onChanged(
                chooserView: ChooserView,
                viewTag: String,
                groupTag: String,
                isSelected: Boolean
            ) {
                if (isSelected) {
                    selectedList.add(viewTag)
                } else {
                    selectedList.remove(viewTag)
                }
                Toast.makeText(this@MainActivity, selectedList.toString(), Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }

    /**
     * 单选
     */
    private fun singleChoose() {
        val singleChooserManager = ChooserViewGroupManager.Builder()
            .addChooserView(chooserView = *arrayOf(ali_pay_view, wechat_pay_view, union_pay_view))
            .build()

        singleChooserManager.setOnChooseChangeListener(object : OnChooseChangeListener() {
            override fun onChanged(
                chooserView: ChooserView,
                viewTag: String,
                groupTag: String,
                isSelected: Boolean
            ) {
                Toast.makeText(this@MainActivity, viewTag, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
