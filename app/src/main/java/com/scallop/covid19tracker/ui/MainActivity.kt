package com.scallop.covid19tracker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scallop.covid19tracker.databinding.ActivityMainBinding
import com.scallop.covid19tracker.model.Status
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
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

        mBinding.includes.countryList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mBinding.includes.countryList.adapter = mAdapter

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
                mBinding.totalCasesValue.text = response.cases.toString()
                mBinding.totalDeathsValue.text = response.deaths.toString()
                mBinding.totalRecoveredValue.text = response.recovered.toString()
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
                mAdapter.updateList(response)
            }
        })

    }
}