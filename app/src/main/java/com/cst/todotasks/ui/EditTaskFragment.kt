package com.cst.todotasks.ui

import android.os.Bundle
import android.util.Log
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

        val bundle = this.arguments
        val todoTaskLongID: Long = bundle?.getLong("TodoTaskLongID") ?: 1
        Log.d("idTag", todoTaskLongID.toString())
        activity?.apply {
            findViewById<FloatingActionButton>(R.id.fab)?.apply {
                setOnClickListener {



                }
            }
        }
    }
}