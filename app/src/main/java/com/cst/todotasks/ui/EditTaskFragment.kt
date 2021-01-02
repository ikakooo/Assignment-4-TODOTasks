package com.cst.todotasks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.cst.todotasks.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class EditTaskFragment : Fragment(R.layout.fragment_edit_task) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        activity?.apply {
//       findViewById<FloatingActionButton>(R.id.fab)?.apply {
//
//            setOnClickListener {
//                Snackbar.make(view, "Task Saved", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//
//            }} }

//        view.findViewById<Button>(R.id.button_second).setOnClickListener {
//            findNavController().navigate(R.id.action_NewTaskFragment_to_TaskListFragment)
//        }
    }
}