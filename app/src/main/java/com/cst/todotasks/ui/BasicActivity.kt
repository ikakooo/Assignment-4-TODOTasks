package com.cst.todotasks.ui

import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import com.cst.todotasks.Constants.firstFragmentPath
import com.cst.todotasks.R
import com.cst.todotasks.databinding.ActivityMainBinding

class BasicActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)


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
                if (firstFragmentPath == navController.currentDestination.toString()) {
                    binding.toolbar.title = "ikakk"
                    setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_edit))
                    navController.navigate(R.id.action_FirstFragment_to_SecondFragment)

                } else {
                    setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_add))
//                    setBackgroundResource(R.drawable.ic_add)
                    navController.navigate(R.id.action_SecondFragment_to_FirstFragment)


                }

            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {


        val navController = findNavController(R.id.nav_host_fragment)


        return navController.navigateUp() || super.onSupportNavigateUp()
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