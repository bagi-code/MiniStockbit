package com.robby.ministockbit.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.robby.ministockbit.R
import com.robby.ministockbit.moduls.MiniStockbitActivity

fun Activity.goNavController(navigation : Int, supportFragmentManager : FragmentManager, it : Bundle, navOptions : NavOptions) {
    val navHostFragment = supportFragmentManager
        .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    val navController = navHostFragment.navController
    navController.navigate(navigation, it, navOptions)
}

fun Fragment.goToHome(isFinish:Boolean = false) {
    val intent = Intent(activity, MiniStockbitActivity::class.java)
    intent.putExtras(Bundle().apply {
        putString("page_request", Const.PARAM_HOME)
    })
    startActivity(intent)

    if (isFinish) requireActivity().finish()
}
