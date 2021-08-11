package com.binzee.passwordlocker.database

import androidx.room.*

// 条目数据类
// by tong.xw 2021/08/11 10:15

@Entity
data class NoteItem(
    @PrimaryKey(autoGenerate = true) var id: Long,  // 主键
    var usernameL: String,  // 用户名
    var password: String,   // 密码
    var loginFor: String,   // 用于登录
    var lastCheck: Long = System.currentTimeMillis(),   // 上次登录时间
    val createTime: Long = System.currentTimeMillis(),  // 创建时间
    var describe: String = ""
)

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = NoteItem::class)
    fun insert(condition: ItemCondition): Long

    @Update
    fun update(item: NoteItem)

    @Query("SELECT * FROM NoteItem ")
    fun queryAll(): List<NoteItem>

    @Query("SELECT * FROM NoteItem WHERE loginFor = :loginFor")
    fun queryByLoginFor(loginFor: String): List<NoteItem>

    @Query("DELETE FROM NoteItem WHERE id = :id")
    fun delete(id: Long)
}

data class ItemCondition(
    var usernameL: String,  // 用户名
    var password: String,   // 密码
    var loginFor: String,   // 用于登录
    var lastCheck: Long = System.currentTimeMillis(),   // 上次登录时间
    val createTime: Long = System.currentTimeMillis(),  // 创建时间
    var describe: String = ""
)