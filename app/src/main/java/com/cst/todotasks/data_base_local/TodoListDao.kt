package com.cst.todotasks.data_base_local

import androidx.room.*

@Dao
interface TodoListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRoomTodoListModel(todoList: RoomTodoListModel)

    @Query("select * from Todo_List")
    fun getTodoList(): List<RoomTodoListModel>

    @Query("delete from Todo_List where isCompleted = :isCompleted")
    infix fun deleteTodoListItemsIsCompleted(isCompleted: Boolean)

    @Query("Select * from Todo_List where isCompleted =  :isCompleted")
    fun completedTasks(isCompleted: Boolean): List<RoomTodoListModel>

    @Query("UPDATE Todo_List SET isCompleted=:isCompleted WHERE id = :id")
    fun updateActiveOrCompleted(isCompleted: Boolean, id: Long?)

    @Query("UPDATE Todo_List SET title = :title and description= :description and isCompleted=:isCompleted WHERE id = :id")
    fun saveEditedTodoTask(title:String,description:String,isCompleted: Boolean, id: Long?)

    @Query("select * from Todo_List WHERE id = :id")
    fun getTodoTask(id: Long?):RoomTodoListModel

    @Update
    fun updateTodoTask(todoList: RoomTodoListModel)

    @Query("delete from Todo_List where id = :id ")
    fun deleteTaskById(id: Long?)
}