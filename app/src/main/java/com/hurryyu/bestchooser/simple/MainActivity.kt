package com.hurryyu.bestchooser.simple

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hurryyu.bestchooser.ChooserMode
import com.hurryyu.bestchooser.ChooserView
import com.hurryyu.bestchooser.ChooserViewGroupManager
import com.hurryyu.bestchooser.OnChooseChangeListener
import com.hurryyu.bestchooser.simple.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
            .addChooserView(
                "meatGroup",
                binding.yuxiangrousiView,
                binding.xiaochaorouView,
                binding.huiguorouView
            )
            .addChooserView(
                "vegetable",
                binding.douyaView,
                binding.mapodoufuView,
                binding.yuxiangqieziView,
                binding.tudousiView
            )
            .addChooserView("soup", binding.fanqiejidantangView, binding.zicaidantangView)
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
                chooserView = arrayOf(
                    binding.qinghuaSchoolView1,
                    binding.cqcetSchoolView1,
                    binding.zhijiaoSchoolView1,
                    binding.ligongSchoolView1
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
                chooserView = arrayOf(
                    binding.qinghuaSchoolView,
                    binding.cqcetSchoolView,
                    binding.zhijiaoSchoolView,
                    binding.ligongSchoolView
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
            .addChooserView(
                chooserView = arrayOf(
                    binding.aliPayView,
                    binding.wechatPayView,
                    binding.unionPayView
                )
            )
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
