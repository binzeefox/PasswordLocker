package com.binzee.passwordlocker.base

import android.app.Application
import androidx.room.Room
import com.binzee.passwordlocker.database.AppDatabase
import com.binzeefox.foxdevframe_kotlin.FoxCore

/**
 * 应用基类
 *
 * @author tong.xw
 * 2021/08/11 10:13
 */
class MyApplication: Application() {
    companion object {
        val database: AppDatabase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Room.databaseBuilder(
                FoxCore.appContext, AppDatabase::class.java, "PasswordLockerDB"
            ).build()
        }
    }
}