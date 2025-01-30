package com.kaw.feature_favorite_impl.ui.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.kaw.core_utils.StringUtil
import com.kaw.feature_favorite_impl.R
import com.kaw.feature_favorite_impl.databinding.FragmentFavoriteBinding
import com.kaw.feature_favorite_impl.ui.delegates.VacancyDelegate
import com.kaw.feature_favorite_impl.ui.viewmodels.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var adapterVacancies: ListDelegationAdapter<List<Any>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerview()
        setupObservers()
        setupClickListeners()
    }

    private fun setupClickListeners() {

    }

    private fun setupRecyclerview() {
        adapterVacancies = ListDelegationAdapter(
            VacancyDelegate { _, _ ->
            }
        )

        binding.vacancyList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@FavoriteFragment.adapterVacancies
        }
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.screenData.collect { screenData ->
                        if (screenData != null) {
                            binding.vacancyCount.visibility =
                                if (screenData.vacancies.isNullOrEmpty()) View.GONE else View.VISIBLE
                            binding.vacancyList.visibility =
                                if (screenData.vacancies.isNullOrEmpty()) View.GONE else View.VISIBLE

                            adapterVacancies.items = screenData.vacancies
                            adapterVacancies.notifyDataSetChanged()

                            val vacanciesCount = screenData.vacancies?.size ?: 0
                            val pluralized = StringUtil.getCorrectPlural(
                                vacanciesCount,
                                "вакансия",
                                "вакансии",
                                "вакансий"
                            )
                            binding.vacancyCount.text = "$vacanciesCount $pluralized"
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}