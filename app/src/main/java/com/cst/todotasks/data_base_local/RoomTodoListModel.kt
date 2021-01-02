package com.cst.todotasks.data_base_local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Todo_List")
data class RoomTodoListModel (
    @PrimaryKey(autoGenerate = true) val id : Long?=null,
    @ColumnInfo(name = "title") val title:String,
    @ColumnInfo(name = "description") val description:String,
    @ColumnInfo(name = "isCompleted") val isCompleted:Boolean
)