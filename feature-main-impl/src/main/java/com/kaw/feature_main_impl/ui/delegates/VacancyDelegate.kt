package com.kaw.feature_main_impl.ui.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.kaw.core_utils.DateUtil
import com.kaw.core_utils.StringUtil
import com.kaw.feature_main_api.domain.models.Vacancy
import com.kaw.feature_main_impl.R
import com.kaw.feature_main_impl.databinding.ItemVacancyBinding

fun VacancyDelegate(onFavoriteClicked: (String, Boolean) -> Unit) =
    adapterDelegateViewBinding<Vacancy, Any, ItemVacancyBinding>(
        { layoutInflater: LayoutInflater, parent: ViewGroup ->
            ItemVacancyBinding.inflate(layoutInflater, parent, false)
        },
        on = { item, _, _ -> item is Vacancy }
    ) {
        bind {
            binding.lookingTextView.apply {
                if(item.lookingNumber == null){
                    visibility = View.GONE
                }else{
                    visibility = View.VISIBLE
                    text = "Сейчас просматривает ${item.lookingNumber} ${StringUtil.getCorrectPlural(item.lookingNumber!!, "человек", "человека", "человек")}"
                }
            }
            binding.vacancySalary.apply {
                if (item.salary == null || item.salary?.short == null) {
                    visibility = View.GONE
                } else {
                    visibility = View.VISIBLE
                    text = item.salary?.short
                }
            }
            binding.vacancyTitleTextView.text = item.title
            binding.companyNameTextView.text = item.company
            binding.locationTextView.apply {
                val address = "${item.address?.town ?: ""}, ${item.address?.street ?: ""} ${item.address?.house ?: ""}"
                text = address.trim().removeSuffix(",")
                visibility = if(text.isNullOrEmpty()) View.GONE else View.VISIBLE
            }
            binding.experienceTextView.text = item.experience?.previewText
            binding.dateTextView.text = item.publishedDate
            binding.favoriteImageView.setImageResource(if(item.isFavorite) R.drawable.ic_fav_filled else R.drawable.ic_fav)
            binding.favoriteImageView.setOnClickListener {
                onFavoriteClicked(item.id!!, !item.isFavorite)
            }
            itemView.setOnClickListener {

            }
        }
    }