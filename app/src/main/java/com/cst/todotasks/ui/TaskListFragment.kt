package com.cst.todotasks.ui

import android.os.Bundle
import android.util.Log.d
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
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
    private val dbAll = roomDB.todoListDaoConnection().getTodoList().toMutableList()
    private val dbActive = roomDB.todoListDaoConnection().completedTasks(false).toMutableList()
    private val dbCompleted = roomDB.todoListDaoConnection().completedTasks(true).toMutableList()

    private var todoList = mutableListOf<RoomTodoListModel>()
    private lateinit var todoListAdapter: TaskListFragmentRecyclerviewAdapter

    private lateinit var headerTextViewID: TextView
    private lateinit var logoNoFillID: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        headerTextViewID = view.findViewById(R.id.headerTextView_ID)
        logoNoFillID = view.findViewById(R.id.logo_no_fill_ID)

        setHasOptionsMenu(true)
        logoNoFillID.isVisible = dbAll.isEmpty()

        d("fdbfdbf", dbAll.toString())
        d("fdbfdbf", dbActive.toString())
        d("fdbfdbf", dbCompleted.toString())

        view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            todoListAdapter = TaskListFragmentRecyclerviewAdapter(todoList)
            layoutManager = LinearLayoutManager(context)
            adapter = todoListAdapter

        }
        todoList.addAll(dbAll)

        (activity as BasicActivity).apply {

            findViewById<FloatingActionButton>(R.id.fab).apply {

                setOnClickListener {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//               .setAction("Action", null).show()
                    findNavController().navigate(R.id.action_TaskListFragment_to_NewTaskFragment)
                    setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_done))
                    title = "New Task"


                    supportActionBar?.apply {

                        setDisplayHomeAsUpEnabled(true)
                        setDisplayShowHomeEnabled(true)
                        setHomeButtonEnabled(true)
                    }

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
                logoNoFillID.isVisible = todoList.isEmpty()
                    when (it.itemId) {
                        R.id.active -> {
                            todoList.clear()
                            headerTextViewID.text = "Active Tasks"
                            todoList.addAll(dbActive)
                        }
                        R.id.completed -> {
                            todoList.clear()
                            headerTextViewID.text = "Completed Tasks"
                            todoList.addAll(dbCompleted)
                        }
                        else -> {
                            todoList.clear()
                            headerTextViewID.text = "All Tasks"
                            todoList.addAll(dbAll)
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