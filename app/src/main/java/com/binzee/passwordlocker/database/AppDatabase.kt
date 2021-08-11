package com.binzee.passwordlocker.database

import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * 数据库
 *
 * @author tong.xw
 * 2021/08/11 10:29
 */
@Database(entities = [NoteItem::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        val taskThread: ExecutorService by lazy {
            Executors.newSingleThreadExecutor()
        }
    }

    abstract fun itemDao(): ItemDao

    /**
     * 重置数据库
     */
    fun resetDatabase() {
        taskThread.execute {
            runInTransaction {
                clearAllTables()
                Log.i("PasswordLocker Database", "清除缓存")
            }
        }
    }
}