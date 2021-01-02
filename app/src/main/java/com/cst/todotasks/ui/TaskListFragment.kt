package com.cst.todotasks.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import com.cst.todotasks.R
import com.cst.todotasks.databinding.ActivityBasicBinding
import com.cst.todotasks.databinding.FragmentTaskListBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class TaskListFragment : Fragment(R.layout.fragment_task_list) {

    override fun onResume() {
        super.onResume()
        (activity as BasicActivity)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        (activity as BasicActivity).apply {

            findViewById<FloatingActionButton>(R.id.fab).apply {

                setOnClickListener {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//               .setAction("Action", null).show()
                    findNavController().navigate(R.id.action_TaskListFragment_to_NewTaskFragment)
                    setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_done))
                    title = "Todo Tasks"
                }
            }
        }

//        view.findViewById<Button>(R.id.button_first).setOnClickListener {
//            findNavController().navigate(R.id.action_TaskListFragment_to_NewTaskFragment)
//        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.menu_clear -> {
                // TODO თქვენი კოდი
                true
            }
            R.id.menu_filter -> {
                showFilteringPopUpMenu()
                true
            }
            else -> false
        }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.tasks_fragment_menu, menu)
    }

    private fun showFilteringPopUpMenu() {
        val view = (activity as BasicActivity).findViewById<View>(R.id.menu_filter) ?: return
        PopupMenu(requireContext(), view).run {
            menuInflater.inflate(R.menu.filter_tasks, menu)

            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.active -> {
                        // TODO თქვენი კოდი
                    }
                    R.id.completed -> {
                        // TODO თქვენი კოდი
                    }
                    else -> {
                        // TODO თქვენი კოდი
                    }
                }
                true
            }
            show()
        }
    }

    companion object {

        fun createInstance() = TaskListFragment()

    }
}