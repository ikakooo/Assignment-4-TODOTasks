package com.cst.todotasks.ui.tasks_list

import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cst.todotasks.R
import com.cst.todotasks.data_base_local.DatabaseBuilder.roomDB
import com.cst.todotasks.data_base_local.RoomTodoListModel
import com.cst.todotasks.extensions.myCustomSnackbar
import com.cst.todotasks.extensions.showStrikeThrough


class TaskListFragmentRecyclerviewAdapter(
    val view: View,
    val todoList: MutableList<RoomTodoListModel>, val clickingListener: ItemClickListener
) :
    RecyclerView.Adapter<TaskListFragmentRecyclerviewAdapter.ViewHolder>() {

    override fun getItemCount(): Int = todoList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.tasks_todo_list_layout, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var model: RoomTodoListModel

        fun onBind() {
            model = todoList[adapterPosition]
            val titleTextView = itemView.findViewById<TextView>(R.id.title_TextView_ID)
            val isActiveCheckBoxID = itemView.findViewById<CheckBox>(R.id.isActive_CheckBox_ID)

            isActiveCheckBoxID.isChecked = if (model.isCompleted) {
                titleTextView.showStrikeThrough(true)
                true
            } else false
            titleTextView.text = model.title
            isActiveCheckBoxID.setOnCheckedChangeListener { _, isChecked ->
                d("isChecked", isChecked.toString())
                titleTextView.showStrikeThrough(isChecked)
                if (isChecked) {
                    view.myCustomSnackbar("Task Marked Completed")
                    roomDB.todoListDaoConnection().updateActiveOrCompleted(isChecked, model.id)
                } else {

                    view.myCustomSnackbar("Task Marked Active")
                    roomDB.todoListDaoConnection().updateActiveOrCompleted(isChecked, model.id)
                }
            }

            itemView.findViewById<TextView>(R.id.title_TextView_ID).setOnClickListener {
                clickingListener.viewClicked(model.id)
            }

        }

    }

}