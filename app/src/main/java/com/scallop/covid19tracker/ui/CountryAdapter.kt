package com.scallop.covid19tracker.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scallop.covid19tracker.R
import com.scallop.covid19tracker.model.Country
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private var mData = mutableListOf<Country>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var mCountry: Country

        fun bind(item: Country) {
            mCountry = item

            with(itemView) {
                country_name.text = item.country
                total_cases_value.text = item.cases.toString()
                total_deaths_value.text = item.deaths.toString()
                total_recovered_value.text = item.recovered.toString()
                today_cases_value.text = item.todayCases.toString()
                today_deaths_value.text = item.todayDeaths.toString()
                active_cases_value.text = item.active.toString()
                critical_cases_value.text = item.critical.toString()
            }
        }
    }

    fun updateList(list: List<Country>) {
        if (list.isNotEmpty()) {
            mData.clear()
            mData.addAll(list)
            notifyDataSetChanged()
        }
    }
}