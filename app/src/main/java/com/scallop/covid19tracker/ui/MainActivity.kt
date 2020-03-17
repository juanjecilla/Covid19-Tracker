package com.scallop.covid19tracker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scallop.covid19tracker.R
import com.scallop.covid19tracker.model.Status
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mMainViewModel: MainViewModel by viewModel()

    private lateinit var listAdapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listAdapter = CountryAdapter()

        country_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        country_list.adapter = listAdapter

        mMainViewModel.fetchWorldwideInfo()
        mMainViewModel.fetchInfoByCountry()
    }

    override fun onStart() {
        super.onStart()

        mMainViewModel.getWorldwideInfoLiveData().observe(this, Observer {
            when (it?.responseType) {
                Status.ERROR -> {
                    //Error handling
                }
                Status.LOADING -> {
                    //Progress
                }
                Status.SUCCESSFUL -> {
                    // On Successful response
                }
            }
            it?.data?.let { response ->
                total_cases_value.text = response.cases.toString()
                total_deaths_value.text = response.deaths.toString()
                total_recovered_value.text = response.recovered.toString()
            }
        })

        mMainViewModel.getCountriesInfoLiveData().observe(this, Observer {
            when (it?.responseType) {
                Status.ERROR -> {
                    //Error handling
                }
                Status.LOADING -> {

                }
                Status.SUCCESSFUL -> {
                    // On Successful response
                }
            }
            it?.data?.let { response ->
                listAdapter.updateList(response)
            }
        })

    }
}