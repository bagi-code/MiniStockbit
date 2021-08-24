package com.robby.ministockbit.moduls

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import com.robby.ministockbit.R
import com.robby.ministockbit.databinding.ActivityMiniStockbitBinding
import com.robby.ministockbit.utils.Const
import com.robby.ministockbit.utils.goNavController

class MiniStockbitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMiniStockbitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMiniStockbitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        RuteApps(intent)
    }

    private fun RuteApps(intent: Intent) {
        intent.extras?.let {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.LoginFragment,true)
                .build()

            when {
                it.get("page_request") == Const.PARAM_HOME ->
                    goNavController(
                        R.id.action_LoginFragment_to_HomeFragment,
                        supportFragmentManager, it, navOptions)
            }
        }
    }
}