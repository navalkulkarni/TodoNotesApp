
package com.naval.todonotesapp.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.naval.todonotesapp.R

class OnboardingActivity : AppCompatActivity() {

    lateinit var viewPager:ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        bindView()
    }

    private fun bindView() {
        viewPager = findViewById(R.id.viewPagerOnBoarding)
        val adapter = FragmentAdapter(supportFragmentManager)
        viewPager.adapter = adapter
    }
}