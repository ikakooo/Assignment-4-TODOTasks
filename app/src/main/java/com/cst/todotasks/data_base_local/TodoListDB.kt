package com.cst.todotasks.data_base_local

import androidx.room.*

@Database(entities = [RoomTodoListModel::class], version = 2)
abstract class TodoListDB: RoomDatabase(){
    abstract fun todoListDaoConnection() : TodoListDao
}