package com.binzee.passwordlocker

import android.os.Bundle
import android.widget.Button
import com.binzee.commonlib.utils.FingerPrintUtil
import com.binzee.commonlib.utils.FingerPrintUtil.Callback.Companion.RESULT_ERROR
import com.binzee.commonlib.utils.FingerPrintUtil.Callback.Companion.RESULT_SUCCESS
import com.binzeefox.foxdevframe_kotlin.ui.FoxActivity
import com.binzeefox.foxdevframe_kotlin.ui.utils.NoticeUtil

class MainActivity : FoxActivity() {

    private val btnAction: Button get() = findViewById(R.id.btn_action)

    override fun getContentViewResource(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreen()

        btnAction.setOnClickListener {
            test()
        }
    }

    private fun test() {
        FingerPrintUtil.from(this) { result, message ->
            when (result) {
                RESULT_SUCCESS -> NoticeUtil.toast("验证成功").showNow()
                RESULT_ERROR -> if (message != "取消") NoticeUtil.toast(message!!).showNow()
            }
        }.showDialog("测试指纹验证", "请验证指纹")
    }
}