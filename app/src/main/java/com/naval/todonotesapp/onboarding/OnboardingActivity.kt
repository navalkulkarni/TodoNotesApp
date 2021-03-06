
package com.naval.todonotesapp.onboarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.naval.todonotesapp.R
import com.naval.todonotesapp.utils.PrefConstant
import com.naval.todonotesapp.view.LoginActivity

class OnboardingActivity : AppCompatActivity(),OnboardingOneFragment.OnNextClick,OnboardingTwoFragment.OnOptionClick {

    lateinit var viewPager:ViewPager
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        bindView()
        setupSharedPreferences()
    }

    private fun setupSharedPreferences() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREF_NAME,Context.MODE_PRIVATE)
    }

    private fun bindView() {
        viewPager = findViewById(R.id.viewPagerOnBoarding)
        val adapter = FragmentAdapter(supportFragmentManager)
        viewPager.adapter = adapter
    }

    override fun onClick() {
        viewPager.currentItem = 1
    }

    override fun onOptionBack() {
        viewPager.currentItem = 0
    }

    override fun onOptionDone() {
        editor = sharedPreferences.edit()
        editor.putBoolean(PrefConstant.ONBOARDING,true)
        editor.apply()
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
}