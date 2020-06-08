package com.naval.todonotesapp.onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.naval.todonotesapp.R


class OnboardingOneFragment : Fragment() {

    lateinit var nextFragmentOne:TextView
    lateinit var onNextClick: OnNextClick

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onNextClick = context as OnNextClick
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboarding_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(view)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        nextFragmentOne.setOnClickListener(View.OnClickListener {
            onNextClick.onClick()
        })
    }

    private fun bindView(view: View) {
        nextFragmentOne = view.findViewById(R.id.nextFragmentOne)
    }

    interface OnNextClick{
        fun onClick()
    }
}