package com.naval.todonotesapp.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class FragmentAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return OnboardingOneFragment()
            else -> return OnboardingTwoFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }
}