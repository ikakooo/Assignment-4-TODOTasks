package com.cst.todotasks.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cst.todotasks.R



class TaskListFragmentRecyclerviewAdapter(
    val MoviesCastList: MutableList<TodoModel>
) :
    RecyclerView.Adapter<TaskListFragmentRecyclerviewAdapter.ViewHolder>() {

    override fun getItemCount(): Int = MoviesCastList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.tasks_todo_list_layout, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var model: TodoModel

        fun onBind() {
            model = MoviesCastList[adapterPosition]
//            itemView.findViewById<TextView>(R.id.actorName_TextView_ID).text = model.fullName
//            itemView.findViewById<TextView>(R.id.actorRole_TextView_ID).text = model.role
//            Glide.with(itemView.context).load(  model.imageUrl)
//                .into(itemView.findViewById(R.id.actorProfilePhoto_ImageView_ID))
        }

    }

}