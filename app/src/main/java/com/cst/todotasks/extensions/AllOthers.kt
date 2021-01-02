package com.cst.todotasks.extensions

import android.graphics.Paint
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun AppCompatActivity.replaceFragment(mainFragmentId: Int, baseFragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(mainFragmentId, baseFragment, baseFragment::class.java.simpleName)
            .commitAllowingStateLoss()
    }

fun TextView.showStrikeThrough(show: Boolean) {
    paintFlags =
        if (show) paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        else paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
}

fun View.myCustomSnackbar(text: String){
    Snackbar.make(this, text, Snackbar.LENGTH_LONG)
        .setAction("Action", null).show()
}