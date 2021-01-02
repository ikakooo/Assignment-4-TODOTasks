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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NewTaskFragment : Fragment(R.layout.fragment_new_task) {

    override fun onResume() {
        super.onResume()
        (activity as BasicActivity).title = "New Task"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val todoTitle = view.findViewById<EditText>(R.id.todoTitle_EditText_ID)
        val todoDescription = view.findViewById<EditText>(R.id.todoDescription_EditText_ID)

        (activity as BasicActivity).apply {
            supportActionBar?.apply {

                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
                setHomeButtonEnabled(true)
            }



       findViewById<FloatingActionButton>(R.id.fab).apply {

            setOnClickListener {
                Snackbar.make(view, "Task Saved", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                d("fsdfsdf",todoTitle.text.toString())
                findNavController().navigate(R.id.action_NewTaskFragment_to_TaskListFragment)
                setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_add))
                title = "New Task"
            }} }

//        view.findViewById<Button>(R.id.button_second).setOnClickListener {
//            findNavController().navigate(R.id.action_NewTaskFragment_to_TaskListFragment)
//        }
    }
}