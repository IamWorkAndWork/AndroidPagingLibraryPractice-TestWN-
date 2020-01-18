package com.example.testwn.ui.coins_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testwn.R
import com.example.testwn.data.CoinsRepository
import com.example.testwn.model.Coin
import com.example.testwn.model.Status
import kotlinx.android.synthetic.main.coins_list_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoinsListFragment : Fragment(), OnCoinsListListener {

    private val coinsRepository: CoinsRepository by inject()

    private var isPullToRefresh: Boolean = false
    private val TAG by lazy { javaClass.simpleName }

    companion object {
        fun newInstance() = CoinsListFragment()
    }

    val viewModel by viewModel<CoinsListViewModel>()
    private lateinit var adapter: CoinsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.coins_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initWidget()
        initListener()

        observeViewModel()

    }

    private fun initListener() {

        coinsListTxtQuery.addTextChangedListener(viewModel.onTextQuery)

        coinsListSwipeRefresh.setOnRefreshListener {
            isPullToRefresh = true
            coinsListTxtQuery.setText("")
            viewModel.searchQuery(coinsListTxtQuery.text.toString())
        }

        coinsListBt24h.setOnClickListener {
            viewModel.filterByPeriod(CoinsListViewModel.FILTER_24H)
        }

        coinsListBt7d.setOnClickListener {
            viewModel.filterByPeriod(CoinsListViewModel.FILTER_7D)

        }

        coinsListBt30d.setOnClickListener {
            viewModel.filterByPeriod(CoinsListViewModel.FILTER_30D)

        }
    }

    private fun initWidget() {

        adapter = CoinsListAdapter(this)

        val linearLayoutManager = LinearLayoutManager(activity)
        val divider = DividerItemDecoration(activity, linearLayoutManager.orientation)

        coinsListRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = this@CoinsListFragment.adapter
            addItemDecoration(divider)
        }

    }

    private fun observeViewModel() {

        viewModel.timePeriod.observe(this, Observer {

            defaultButtonStyle()

            it?.let {
                when (it) {
                    CoinsListViewModel.FILTER_24H -> {
                        coinsListBt24h.setBackgroundResource(R.drawable.button_shape_period_active)
                        coinsListBt24h.setTextColor(
                            ContextCompat.getColor(
                                activity!!,
                                R.color.colorWhite
                            )
                        )
                    }
                    CoinsListViewModel.FILTER_7D -> {
                        coinsListBt7d.setBackgroundResource(R.drawable.button_shape_period_active)
                        coinsListBt7d.setTextColor(
                            ContextCompat.getColor(
                                activity!!,
                                R.color.colorWhite
                            )
                        )
                    }
                    CoinsListViewModel.FILTER_30D -> {
                        coinsListBt30d.setBackgroundResource(R.drawable.button_shape_period_active)
                        coinsListBt30d.setTextColor(
                            ContextCompat.getColor(
                                activity!!,
                                R.color.colorWhite
                            )
                        )
                    }
                }
            }
        })

        viewModel.listCoins.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.parameterQuery.observe(this, Observer {
            //            Log.d(TAG, "observe param = " + it.toString())
        })

        viewModel.networkStatus.observe(this, Observer {
            it?.let {
                Log.d(TAG, "netwotkStatus = " + it.status)
                when (it.status) {
                    Status.RUNNING -> {
                        coinsListProgressBar.visibility = View.VISIBLE
                        if (isPullToRefresh)
                            coinsListSwipeRefresh.isRefreshing = true
                    }
                    Status.SUCCESS -> {
                        coinsListProgressBar.visibility = View.GONE
                        coinsListSwipeRefresh.isRefreshing = false
                        isPullToRefresh = false
                    }
                    Status.FAILED -> {
                        coinsListProgressBar.visibility = View.GONE
                        coinsListSwipeRefresh.isRefreshing = false
                        isPullToRefresh = false
                    }
                    Status.EMPTY -> {
                        coinsListProgressBar.visibility = View.GONE
                        coinsListSwipeRefresh.isRefreshing = false
                        isPullToRefresh = false
                        Toast.makeText(
                            activity,
                            getString(R.string.not_found_query),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }

    private fun defaultButtonStyle() {

        coinsListBt24h.setBackgroundResource(R.drawable.button_shape_period_inactive)
        coinsListBt7d.setBackgroundResource(R.drawable.button_shape_period_inactive)
        coinsListBt30d.setBackgroundResource(R.drawable.button_shape_period_inactive)
        coinsListBt24h.setTextColor(
            ContextCompat.getColor(
                activity!!,
                R.color.colorGrayMedium
            )
        )
        coinsListBt7d.setTextColor(
            ContextCompat.getColor(
                activity!!,
                R.color.colorGrayMedium
            )
        )
        coinsListBt30d.setTextColor(
            ContextCompat.getColor(
                activity!!,
                R.color.colorGrayMedium
            )
        )

    }



    override fun onClicked(coin: Coin?) {
        Toast.makeText(activity, "click at : " + coin?.name, Toast.LENGTH_SHORT).show()
    }


}
