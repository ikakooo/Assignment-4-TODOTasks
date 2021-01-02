package com.cst.todotasks.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cst.todotasks.R
import com.cst.todotasks.data_base_local.RoomTodoListModel


class TaskListFragmentRecyclerviewAdapter(
    val todoList: MutableList<RoomTodoListModel>
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
            itemView.findViewById<TextView>(R.id.title_TextView_ID).text= model.title
          //  itemView.findViewById<TextView>(R.id.description_TextView_ID).text = model.description

//            itemView.findViewById<TextView>(R.id.actorName_TextView_ID).text = model.fullName
//            itemView.findViewById<TextView>(R.id.actorRole_TextView_ID).text = model.role
//            Glide.with(itemView.context).load(  model.imageUrl)
//                .into(itemView.findViewById(R.id.actorProfilePhoto_ImageView_ID))
        }

    }

}