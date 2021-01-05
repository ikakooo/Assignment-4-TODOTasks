package com.cst.todotasks.ui

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.fragment.findNavController
import com.cst.todotasks.R
import com.cst.todotasks.data_base_local.DatabaseBuilder
import com.cst.todotasks.data_base_local.DatabaseBuilder.roomDB
import com.cst.todotasks.data_base_local.RoomTodoListModel
import com.cst.todotasks.extensions.myCustomSnackbar
import com.cst.todotasks.extensions.toEditable
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class EditTaskFragment : Fragment(R.layout.fragment_edit_task) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        val todoTaskLongID: Long = bundle?.getLong("TodoTaskLongID") ?: 1
        d("idTag", todoTaskLongID.toString())

        val isActiveEditCheckBoxID = view.findViewById<CheckBox>(R.id.isActiveEdit_CheckBox_ID)
        val todoTitleEditEditTextID = view.findViewById<EditText>(R.id.todoTitleEdit_EditText_ID)
        val todoDescriptionEditEditTextID =
            view.findViewById<EditText>(R.id.todoDescriptionEdit_EditText_ID)

        val todoTask = roomDB.todoListDaoConnection().getTodoTask(todoTaskLongID)


        isActiveEditCheckBoxID.isChecked = todoTask.isCompleted
        todoTitleEditEditTextID.text = todoTask.title.toEditable()
        todoDescriptionEditEditTextID.text = todoTask.description.toEditable()

        (activity as BasicActivity).apply {
            findViewById<FloatingActionButton>(R.id.fab_ID).apply {
                setOnClickListener {
                    d("ddsdvds", todoTask.toString() + "dfdv" + todoTaskLongID)

                    roomDB.todoListDaoConnection().updateTodoTask(RoomTodoListModel(
                        title = todoTitleEditEditTextID.text.toString(),
                        description = todoDescriptionEditEditTextID.text.toString(),
                        isCompleted = isActiveEditCheckBoxID.isChecked,
                        id = todoTaskLongID
                    ))

                    setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_add))
                    title = "Todo Tasks"
                    myCustomSnackbar("Task Edited")
                    findNavController().navigate(R.id.action_EditTaskFragment_to_TaskListFragment)




                    supportActionBar?.apply {

                        setDisplayHomeAsUpEnabled(false)
                        setDisplayShowHomeEnabled(false)
                        setHomeButtonEnabled(false)
                    }
                }
            }
        }
    }
}