package com.cst.todotasks.ui

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import com.cst.todotasks.Constants.newTaskFragment
import com.cst.todotasks.Constants.taskListFragment
import com.cst.todotasks.R
import com.cst.todotasks.databinding.ActivityBasicBinding

class BasicActivity : AppCompatActivity() {
    private val bindingActivity by viewBinding(ActivityBasicBinding::inflate)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindingActivity.root)
        setSupportActionBar(findViewById(R.id.toolbar))

        val navController = findNavController(R.id.nav_host_fragment)
//        supportActionBar?.setStackedBackgroundDrawable(AppCompatResources.getDrawable(baseContext, R.drawable.ic_list))


        // replaceFragment(R.id.fragment_container, TaskListFragment.createInstance())
//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
////        }
//        bindingActivity.fab.apply {
//            setOnClickListener {
//                d("efewfew",navController.currentDestination.toString())
////                setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_edit))
//                when(navController.currentDestination.toString()) {
//                    taskListFragment -> {
//                        bindingActivity.toolbar.title = "New Task"
//                        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//                        supportActionBar?.setDisplayShowHomeEnabled(true)
//
//                        setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_done))
//
//                        navController.navigate(R.id.action_TaskListFragment_to_NewTaskFragment)
//
//
//                    }
//                    newTaskFragment -> {
//                        bindingActivity.toolbar.title = "Todo List"
//
//
//                        supportActionBar?.setDisplayHomeAsUpEnabled(false)
//                        supportActionBar?.setDisplayShowHomeEnabled(false)
//                        setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_add))
//            //                    setBackgroundResource(R.drawable.ic_add)
//                        navController.navigate(R.id.action_NewTaskFragment_to_TaskListFragment)
//
//
//                    }
//                    else -> {
//
//
//                    }
//                }
//
//            }
//        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//
//
//        val navController = findNavController(R.id.nav_host_fragment)
//
//
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }



    private inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
        crossinline bindingInflater: (LayoutInflater) -> T
    ) = lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }
}