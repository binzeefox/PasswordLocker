package com.binzee.commonlib.utils

import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.binzeefox.foxdevframe_kotlin.FoxCore


/**
 * 指纹相关工具类
 *
 * @author tong.xw
 * 2021/08/10 15:48
 */
object FingerPrintUtil {

    fun from(fragment: Fragment, callback: Callback): Prompter {
        val executor = ContextCompat.getMainExecutor(FoxCore.appContext)
        val prompt = BiometricPrompt(fragment, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int,
                                               errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                callback.onResult(Callback.RESULT_ERROR, errString)
            }

            override fun onAuthenticationSucceeded(
                result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                callback.onResult(Callback.RESULT_SUCCESS, null)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                callback.onResult(Callback.RESULT_FAIL, null)
            }
        })
        return Prompter(prompt)
    }

    fun from(activity: FragmentActivity, callback: Callback): Prompter {
        val executor = ContextCompat.getMainExecutor(FoxCore.appContext)
        val prompt = BiometricPrompt(activity, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int,
                                               errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                callback.onResult(Callback.RESULT_ERROR, errString)
            }

            override fun onAuthenticationSucceeded(
                result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                callback.onResult(Callback.RESULT_SUCCESS, null)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                callback.onResult(Callback.RESULT_FAIL, null)
            }
        })
        return Prompter(prompt)
    }

    ///////////////////////////////////////////////////////////////////////////
    // 内部方法
    ///////////////////////////////////////////////////////////////////////////

    class Prompter(private val prompt: BiometricPrompt) {

        fun showDialog(title: CharSequence, subTitle: CharSequence) {
            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle(title)
                .setSubtitle(subTitle)
                .setNegativeButtonText("取消")
                .build()
            prompt.authenticate(promptInfo)
        }
    }

    fun interface Callback {
        companion object {
            const val RESULT_SUCCESS = 1
            const val RESULT_FAIL = 0
            const val RESULT_ERROR = -1 // 一般未用户取消
        }
        fun onResult(result: Int, message: CharSequence?)
    }
}