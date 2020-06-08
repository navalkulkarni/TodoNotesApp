package com.naval.todonotesapp.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.naval.todonotesapp.R


class OnboardingTwoFragment : Fragment() {

    lateinit var nextFragmentTwo:TextView
    lateinit var backFragmentTwo:TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboarding_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(view)
    }

    private fun bindView(view: View) {
        backFragmentTwo = view.findViewById(R.id.backFragmentTwo)
        nextFragmentTwo = view.findViewById(R.id.nextFragmentTwo)
    }

}