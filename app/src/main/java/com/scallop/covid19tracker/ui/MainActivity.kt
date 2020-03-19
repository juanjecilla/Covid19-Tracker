package com.scallop.covid19tracker.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scallop.covid19tracker.databinding.ActivityMainBinding
import com.scallop.covid19tracker.model.Status
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mMainViewModel: MainViewModel by viewModel()

    private lateinit var mAdapter: CountryAdapter
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mAdapter = CountryAdapter()

        mBinding.includes.countryList.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mBinding.includes.countryList.adapter = mAdapter

        mMainViewModel.fetchWorldwideInfo()
        mMainViewModel.fetchInfoByCountry()
    }

    override fun onStart() {
        super.onStart()

        mMainViewModel.getWorldwideInfoLiveData().observe(this, Observer {
            when (it?.responseType) {
                Status.ERROR -> {
                    mBinding.worldwideLayout.visibility = View.GONE
                }
                Status.LOADING -> {
                }
                Status.SUCCESSFUL -> {
                    mBinding.worldwideLayout.visibility = View.VISIBLE
                }
            }
            it?.data?.let { response ->
                mBinding.totalCasesValue.text = response.cases.toString()
                mBinding.totalDeathsValue.text = response.deaths.toString()
                mBinding.totalRecoveredValue.text = response.recovered.toString()
            }
        })

        mMainViewModel.getCountriesInfoLiveData().observe(this, Observer {
            when (it?.responseType) {
                Status.ERROR -> {
                    mBinding.includes.progressBar.visibility = View.GONE
                }
                Status.LOADING -> {
                    mBinding.includes.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESSFUL -> {
                    mBinding.includes.progressBar.visibility = View.GONE
                }
            }
            it?.data?.let { response ->
                mAdapter.updateList(response)
            }
        })

    }
}