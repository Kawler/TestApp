package com.kaw.feature_main_impl.ui.fragments.relevance

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.kaw.core_utils.StringUtil
import com.kaw.feature_main_impl.databinding.VacanciesByRelevanceFragmentBinding
import com.kaw.feature_main_impl.ui.delegates.vacancyDelegate
import com.kaw.feature_main_impl.ui.viemodels.MainScreenSharedViewModel
import com.kaw.feature_main_impl.ui.viemodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VacanciesByRelevanceFragment : Fragment() {

    private var _binding: VacanciesByRelevanceFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SharedViewModel by activityViewModels<MainScreenSharedViewModel>()
    private lateinit var adapter: ListDelegationAdapter<List<Any>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = VacanciesByRelevanceFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerview()
        binding.vacanciesCountTextView.visibility = View.GONE
        binding.filterTextBtn.visibility = View.GONE
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        setupObservers()
    }

    private fun setupRecyclerview() {
        adapter = ListDelegationAdapter(
            vacancyDelegate(
                onFavoriteClicked = { vacancyId, isFavorite ->
                    viewModel.updateFavorite(vacancyId, isFavorite)
                },
                onItemClicked = {
                    navigateToVacancyDetails()
                }
            )
        )

        binding.vacanciesByRelevanceRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@VacanciesByRelevanceFragment.adapter
        }

    }

    private fun navigateToVacancyDetails() {
        val deepLinkUri = Uri.parse("app://details")
        findNavController().navigate(deepLinkUri)
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.screenData.collect { screenData ->
                        if (screenData != null) {
                            binding.vacanciesByRelevanceRecyclerView.visibility =
                                if (screenData.vacancies.isNullOrEmpty()) View.GONE else View.VISIBLE
                            val vacanciesCount = screenData.vacancies?.size ?: 0
                            val pluralized = StringUtil.getCorrectPlural(
                                vacanciesCount,
                                "вакансия",
                                "вакансии",
                                "вакансий"
                            )
                            binding.vacanciesCountTextView.text = "$vacanciesCount $pluralized"
                            binding.vacanciesCountTextView.visibility =
                                if (screenData.vacancies.isNullOrEmpty()) View.GONE else View.VISIBLE
                            binding.filterTextBtn.visibility =
                                if (screenData.vacancies.isNullOrEmpty()) View.GONE else View.VISIBLE
                            adapter.items = screenData.vacancies
                            adapter.notifyDataSetChanged()
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