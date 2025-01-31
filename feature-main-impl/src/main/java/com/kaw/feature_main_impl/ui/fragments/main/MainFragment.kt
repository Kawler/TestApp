package com.kaw.feature_main_impl.ui.fragments.main

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
import com.kaw.feature_main_impl.R
import com.kaw.feature_main_impl.databinding.MainFragmentBinding
import com.kaw.feature_main_impl.ui.delegates.offerDelegate
import com.kaw.feature_main_impl.ui.delegates.vacancyDelegate
import com.kaw.feature_main_impl.ui.viemodels.MainScreenSharedViewModel
import com.kaw.feature_main_impl.ui.viemodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SharedViewModel by activityViewModels<MainScreenSharedViewModel>()
    private lateinit var adapterOffers: ListDelegationAdapter<List<Any>>
    private lateinit var adapterVacancies: ListDelegationAdapter<List<Any>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.moreVacanciesButton.visibility = View.GONE
        binding.vacanciesTitle.visibility = View.GONE
        setupRecyclerview()
        setupObservers()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.moreVacanciesButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_vacanciesByRelevanceFragment)
        }
    }

    private fun setupRecyclerview() {
        adapterOffers = ListDelegationAdapter(
            offerDelegate(),
        )
        adapterVacancies = ListDelegationAdapter(
            vacancyDelegate(
                onFavoriteClicked = { vacancyId, isFavorite ->
                    viewModel.updateFavorite(vacancyId, isFavorite)
                },
                onItemClicked = {
                    navigateToVacancyDetails()
                }
            )
        )

        binding.promoRv.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = this@MainFragment.adapterOffers
        }

        binding.mainRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@MainFragment.adapterVacancies
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
                            binding.promoRv.visibility =
                                if (screenData.offers.isNullOrEmpty()) View.GONE else View.VISIBLE
                            binding.vacanciesTitle.visibility =
                                if (screenData.vacancies.isNullOrEmpty()) View.GONE else View.VISIBLE
                            binding.mainRecyclerView.visibility =
                                if (screenData.vacancies.isNullOrEmpty()) View.GONE else View.VISIBLE
                            binding.moreVacanciesButton.visibility =
                                if (screenData.vacancies.isNullOrEmpty()) View.GONE else View.VISIBLE

                            adapterOffers.items = screenData.offers
                            adapterOffers.notifyDataSetChanged()

                            adapterVacancies.items = screenData.vacancies?.take(3)
                            adapterVacancies.notifyDataSetChanged()

                            val vacanciesCount = screenData.vacancies?.size ?: 0
                            val pluralized = StringUtil.getCorrectPlural(
                                vacanciesCount,
                                "вакансия",
                                "вакансии",
                                "вакансий"
                            )
                            binding.moreVacanciesButton.text = "Еще $vacanciesCount $pluralized"
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