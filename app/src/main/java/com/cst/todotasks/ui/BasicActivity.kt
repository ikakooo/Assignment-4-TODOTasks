package com.cst.todotasks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import com.cst.todotasks.Constants.taskListFragment
import com.cst.todotasks.R
import com.cst.todotasks.databinding.ActivityBasicBinding

class BasicActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityBasicBinding::inflate)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))

        val navController = findNavController(R.id.nav_host_fragment)


        // replaceFragment(R.id.fragment_container, TaskListFragment.createInstance())
//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
        binding.fab.apply {
            setOnClickListener {
//                setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_edit))
                if (taskListFragment == navController.currentDestination.toString()) {
                    binding.toolbar.title = "Todo"
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    supportActionBar?.setDisplayShowHomeEnabled(true)

                    setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_edit))

                    navController.navigate(R.id.action_TaskListFragment_to_NewTaskFragment)


                } else {
                    binding.toolbar.title = "New Task"

                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setDisplayShowHomeEnabled(false)
                    setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_add))
//                    setBackgroundResource(R.drawable.ic_add)
                    navController.navigate(R.id.action_NewTaskFragment_to_TaskListFragment)



                }

            }
        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//
//
//        val navController = findNavController(R.id.nav_host_fragment)
//
//
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }

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


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.tasks_fragment_menu, menu)
        return true
    }

    private fun showFilteringPopUpMenu() {
        val view = findViewById<View>(R.id.menu_filter) ?: return
        PopupMenu(this, view).run {
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

    private inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
        crossinline bindingInflater: (LayoutInflater) -> T
    ) = lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }
}