package com.cst.todotasks.data_base_local

import androidx.room.Room
import com.cst.todotasks.TodoApp

object DatabaseBuilder {
    val roomDB by lazy {
        Room.databaseBuilder(
                TodoApp.instance.getContext(),
            TodoListDB::class.java,
            "TodoList.roomDB"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

}