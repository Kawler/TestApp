package com.kaw.effectivemobile.ui.fragments.callbacks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kaw.effectivemobile.R

class CallbacksFragment : Fragment() {

    companion object {
        fun newInstance() = CallbacksFragment()
    }

    private val viewModel: CallbacksViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_callbacks, container, false)
    }
}