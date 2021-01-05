package com.cst.todotasks.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.cst.todotasks.R
import com.cst.todotasks.databinding.ActivityBasicBinding

class BasicActivity : AppCompatActivity() {
    private val bindingActivity by viewBinding(ActivityBasicBinding::inflate)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindingActivity.root)
        setSupportActionBar(findViewById(R.id.toolbar))

    }

    private inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
        crossinline bindingInflater: (LayoutInflater) -> T
    ) = lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }
}