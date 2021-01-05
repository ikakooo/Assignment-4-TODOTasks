package com.cst.todotasks.ui

import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cst.todotasks.R
import com.cst.todotasks.data_base_local.DatabaseBuilder.roomDB
import com.cst.todotasks.data_base_local.RoomTodoListModel
import com.cst.todotasks.extensions.myCustomSnackbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NewTaskFragment : Fragment(R.layout.fragment_new_task) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val todoTitle = view.findViewById<EditText>(R.id.todoTitle_EditText_ID)
        val todoDescription = view.findViewById<EditText>(R.id.todoDescription_EditText_ID)

        (activity as BasicActivity).apply {


            findViewById<FloatingActionButton>(R.id.fab_ID).apply {

                setOnClickListener {

                    if (todoTitle.text.isNotEmpty() && todoDescription.text.isNotEmpty()) {
                        myCustomSnackbar("Task Saved")
                        d("fsdfsdf", todoTitle.text.toString())
                        roomDB.todoListDaoConnection().insertRoomTodoListModel(
                            RoomTodoListModel(
                                title = todoTitle.text.toString(),
                                description = todoDescription.text.toString(),
                                isCompleted = false
                            )
                        )
                        findNavController().navigate(R.id.action_NewTaskFragment_to_TaskListFragment)
                        setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_add))
                        title = "Todo Tasks"



                        supportActionBar?.apply {

                            setDisplayHomeAsUpEnabled(false)
                            setDisplayShowHomeEnabled(false)
                            setHomeButtonEnabled(false)
                        }
                    } else {
                        myCustomSnackbar("Task isn't Saved, please fill up fields")
                    }
                }
            }
        }

    }
}