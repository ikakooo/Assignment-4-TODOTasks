package com.cst.todotasks.ui

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.fragment.findNavController
import com.cst.todotasks.R
import com.cst.todotasks.data_base_local.DatabaseBuilder.roomDB
import com.cst.todotasks.data_base_local.RoomTodoListModel
import com.cst.todotasks.extensions.myCustomSnackbar
import com.cst.todotasks.extensions.showStrikeThrough
import com.cst.todotasks.extensions.toEditable
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailedTaskFragment : Fragment(R.layout.fragment_detile_task) {
    private lateinit var todoTask: RoomTodoListModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val bundle = this.arguments
        val todoTaskLongID: Long = bundle?.getLong("TodoTaskLongID") ?: 1
        Log.d("idTag", todoTaskLongID.toString())

        val isActiveEditCheckBoxID = view.findViewById<CheckBox>(R.id.isActiveDetail_CheckBox_ID)
        val todoTitleEditEditTextID = view.findViewById<EditText>(R.id.todoTitleDetail_EditText_ID)
        val todoDescriptionEditEditTextID =
            view.findViewById<EditText>(R.id.todoDescriptionDetail_EditText_ID)

        todoTask = roomDB.todoListDaoConnection().getTodoTask(todoTaskLongID)



        todoTitleEditEditTextID.text = todoTask.title.toEditable()
        todoTitleEditEditTextID.inputType = InputType.TYPE_NULL
        todoDescriptionEditEditTextID.text = todoTask.description.toEditable()
        todoDescriptionEditEditTextID.inputType = InputType.TYPE_NULL
        isActiveEditCheckBoxID.isChecked = if (todoTask.isCompleted) {
            todoTitleEditEditTextID.showStrikeThrough(true)
            todoDescriptionEditEditTextID.showStrikeThrough(true)
            true
        } else false

        isActiveEditCheckBoxID.setOnCheckedChangeListener { _, isChecked ->
            Log.d("isChecked", isChecked.toString())
            todoTitleEditEditTextID.showStrikeThrough(isChecked)
            todoDescriptionEditEditTextID.showStrikeThrough(isChecked)
            if (isChecked) {
                view.myCustomSnackbar("Task Marked Completed")
                roomDB.todoListDaoConnection().updateActiveOrCompleted(isChecked, todoTask.id)
            } else {
                view.myCustomSnackbar("Task Marked Active")
                roomDB.todoListDaoConnection().updateActiveOrCompleted(isChecked, todoTask.id)
            }
        }
        (activity as BasicActivity).apply {
            findViewById<FloatingActionButton>(R.id.fab_ID).apply {
                setOnClickListener {
                    findNavController().navigate(
                        R.id.action_DetailedTaskFragment_to_EditTaskFragment,
                        bundle
                    )
                    setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_done))
                    title = "Edit Tasks"

                    supportActionBar?.apply {

                        setDisplayShowTitleEnabled(true)
                        setDisplayShowHomeEnabled(true)

                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.trash_button, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.delete_Task -> {
                roomDB.todoListDaoConnection().deleteTaskById(todoTask.id)
                findNavController().navigate(R.id.action_DetailedTaskFragment_to_TaskListFragment)
                true
            }

            else -> false
        }
}