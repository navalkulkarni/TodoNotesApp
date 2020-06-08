package com.naval.todonotesapp.onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.naval.todonotesapp.R


class OnboardingTwoFragment : Fragment() {

    lateinit var doneFragmentTwo:TextView
    lateinit var backFragmentTwo:TextView
    lateinit var onOptionClick:OnOptionClick


    override fun onAttach(context: Context) {
        super.onAttach(context)

        onOptionClick = context as OnOptionClick
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboarding_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(view)
        setupClicklisteners()
    }

    private fun setupClicklisteners() {
        doneFragmentTwo.setOnClickListener(View.OnClickListener {
            onOptionClick.onOptionDone()
        })

        backFragmentTwo.setOnClickListener(View.OnClickListener {
            onOptionClick.onOptionBack()
        })
    }

    private fun bindView(view: View) {
        backFragmentTwo = view.findViewById(R.id.backFragmentTwo)
        doneFragmentTwo = view.findViewById(R.id.doneFragmentTwo)
    }

    interface OnOptionClick{
        fun onOptionBack()
        fun onOptionDone()
    }
}