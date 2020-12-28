package com.cst.todotasks.data_base_local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodoListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRoomTodoListModel(todoList: RoomTodoListModel)

    @Query("select * from Todo_List")
    fun getTodoList(): List<RoomTodoListModel>

    @Query("delete from Todo_List where header = :id")
    fun deleteTodoListItem(id: String)

    @Query("Select * from Todo_List where isActive =  :isActive")
    fun isActiveItem(isActive: Boolean): RoomTodoListModel
}

