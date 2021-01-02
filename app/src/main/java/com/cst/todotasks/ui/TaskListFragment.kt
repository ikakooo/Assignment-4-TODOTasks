package com.cst.todotasks.ui

import android.os.Bundle
import android.util.Log.d
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cst.todotasks.R
import com.cst.todotasks.data_base_local.DatabaseBuilder
import com.cst.todotasks.data_base_local.DatabaseBuilder.roomDB
import com.cst.todotasks.data_base_local.RoomTodoListModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class TaskListFragment : Fragment(R.layout.fragment_task_list) {
    private var todoList = mutableListOf<RoomTodoListModel>()
    private lateinit var todoListAdapter: TaskListFragmentRecyclerviewAdapter

    private lateinit var headerTextViewID: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         headerTextViewID = view.findViewById(R.id.headerTextView_ID)

        setHasOptionsMenu(true)
        val db = roomDB.todoListDaoConnection().getTodoList().toMutableList()
        d("fdbfdbf",db.toString())


        view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            todoListAdapter = TaskListFragmentRecyclerviewAdapter(todoList)
            layoutManager = LinearLayoutManager(context)
            adapter = todoListAdapter

        }

//        db.forEach { todo ->
//
//        }
        todoList.addAll(db)

        (activity as BasicActivity).apply {

            findViewById<FloatingActionButton>(R.id.fab).apply {

                setOnClickListener {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//               .setAction("Action", null).show()
                    findNavController().navigate(R.id.action_TaskListFragment_to_NewTaskFragment)
                    setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_done))
                    title = "New Task"

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
                        headerTextViewID.text="Active Tasks"

                    }
                    R.id.completed -> {
                        // TODO თქვენი კოდი
                        headerTextViewID.text="Completed Tasks"

                    }
                    else -> {
                        // TODO თქვენი კოდი
                        headerTextViewID.text="All Tasks"
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