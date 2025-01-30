package com.kaw.feature_main_impl.ui.delegates

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.kaw.feature_main_api.domain.models.Offer
import com.kaw.feature_main_impl.R
import com.kaw.feature_main_impl.databinding.ItemOfferBinding

fun OfferDelegate() = adapterDelegateViewBinding<Offer, Any, ItemOfferBinding>(
    { layoutInflater: LayoutInflater, parent: ViewGroup ->
        ItemOfferBinding.inflate(layoutInflater, parent, false)
    },
    on = { item, _, _ -> item is Offer}
) {
    bind {
        binding.offerTitleTextView.text = item.title
        binding.offerButtonTextView.apply {
            text = item.button?.text?.trim()
            visibility = if(item.button?.text.isNullOrEmpty()) View.GONE else View.VISIBLE
        }

        binding.offerPromoImg.apply {
            visibility = if (item.id == null) View.GONE else View.VISIBLE
            when(item.id) {
                "near_vacancies" -> {
                    backgroundTintList = ColorStateList.valueOf(getColor(R.color.dark_blue))
                    setImageResource(R.drawable.ic_location)
                    imageTintList = ColorStateList.valueOf(getColor(R.color.blue))
                }
                "level_up_resume" -> {
                    backgroundTintList = ColorStateList.valueOf(getColor(R.color.dark_green))
                    setImageResource(R.drawable.ic_level_up)
                    imageTintList = ColorStateList.valueOf(getColor(R.color.green))
                }
                "temporary_job" -> {
                    backgroundTintList = ColorStateList.valueOf(getColor(R.color.dark_green))
                    setImageResource(R.drawable.ic_temp_job)
                    imageTintList = ColorStateList.valueOf(getColor(R.color.green))
                }
                else -> visibility = View.GONE
            }
        }
        itemView.setOnClickListener {
        }
    }
}