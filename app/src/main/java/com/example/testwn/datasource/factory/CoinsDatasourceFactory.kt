package com.example.testwn.datasource.factory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.testwn.api.CoinsService
import com.example.testwn.datasource.CoinDataSource
import com.example.testwn.model.Coin

class CoinsDatasourceFactory(
    private val coinsService: CoinsService,
    private val timePeriod: String,
    private val query: String?
) : DataSource.Factory<Long, Coin>() {

    private val _coinDataSource: MutableLiveData<CoinDataSource> = MutableLiveData()

    override fun create(): DataSource<Long, Coin> {
        val datasource = CoinDataSource(coinsService, timePeriod, query)
        _coinDataSource.postValue(datasource)
        return datasource
    }

    fun getCointLiveData(): LiveData<CoinDataSource> {
        return _coinDataSource
    }


}