package com.cst.todotasks.ui.tasks_list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cst.todotasks.R
import com.cst.todotasks.data_base_local.DatabaseBuilder.roomDB
import com.cst.todotasks.data_base_local.RoomTodoListModel
import com.cst.todotasks.ui.BasicActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class TaskListFragment : Fragment(R.layout.fragment_task_list) {
    private var dbAll = roomDB.todoListDaoConnection().getTodoList().toMutableList()
    private var dbActive = roomDB.todoListDaoConnection().completedTasks(false).toMutableList()
    private var dbCompleted = roomDB.todoListDaoConnection().completedTasks(true).toMutableList()

    private var todoList = mutableListOf<RoomTodoListModel>()
    private lateinit var todoListAdapter: TaskListFragmentRecyclerviewAdapter

    private lateinit var headerTextViewID: TextView
    private lateinit var logoNoFillID: ImageView
    private lateinit var actionButtonFab: FloatingActionButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        headerTextViewID = view.findViewById(R.id.headerTextView_ID)
        logoNoFillID = view.findViewById(R.id.logo_no_fill_ID)
        actionButtonFab=(activity as BasicActivity).findViewById(R.id.fab_ID)

        setHasOptionsMenu(true)
        logoNoFillID.isVisible = dbAll.isEmpty()


        view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            todoListAdapter =
                TaskListFragmentRecyclerviewAdapter(view,todoList, object : ItemClickListener {
                    override fun viewClicked(position: Long?) {
                        val bundle = Bundle()
                        if (position != null) {
                            bundle.putLong("TodoTaskLongID", position)
                            findNavController().navigate(R.id.action_TaskListFragment_to_DetailedTaskFragment,bundle)
                            actionButtonFab.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_edit))
                            (activity as BasicActivity).title = "Detailed Task"
                            (activity as BasicActivity).supportActionBar?.apply {

                                setDisplayHomeAsUpEnabled(true)
                                setDisplayShowHomeEnabled(true)
                                setHomeButtonEnabled(true)
                            }


                        }

                    }
                })
            layoutManager = LinearLayoutManager(context)
            adapter = todoListAdapter

        }


        todoList.clear()
        todoList.addAll(dbAll)



        (activity as BasicActivity).apply {

            actionButtonFab.apply {

                setOnClickListener {
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
                roomDB.todoListDaoConnection().deleteTodoListItemsIsCompleted(true)
                dbAll = roomDB.todoListDaoConnection().getTodoList().toMutableList()

                todoList.clear()
                todoList.addAll(dbAll)
                todoListAdapter.notifyDataSetChanged()

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
                        dbActive =
                            roomDB.todoListDaoConnection().completedTasks(false).toMutableList()
                        todoList.clear()
                        headerTextViewID.text = "Active Tasks"
                        todoList.addAll(dbActive)
                        todoListAdapter.notifyDataSetChanged()

                    }
                    R.id.completed -> {
                        dbCompleted =
                            roomDB.todoListDaoConnection().completedTasks(true).toMutableList()
                        todoList.clear()
                        headerTextViewID.text = "Completed Tasks"
                        todoList.addAll(dbCompleted)
                        todoListAdapter.notifyDataSetChanged()

                    }
                    else -> {
                        dbAll = roomDB.todoListDaoConnection().getTodoList().toMutableList()
                        todoList.clear()
                        headerTextViewID.text = "All Tasks"
                        todoList.addAll(dbAll)
                        todoListAdapter.notifyDataSetChanged()
                    }
                }

                logoNoFillID.isVisible = todoList.isEmpty()
                true
            }
            show()
        }
    }

    companion object {

        fun createInstance() = TaskListFragment()

    }
}