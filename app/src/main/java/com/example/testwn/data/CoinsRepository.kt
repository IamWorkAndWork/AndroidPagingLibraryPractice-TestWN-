package com.example.testwn.data

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.testwn.api.CoinsService
import com.example.testwn.datasource.factory.CoinsDatasourceFactory
import com.example.testwn.model.RepoSearchResult

class CoinsRepository(private val coinsService: CoinsService) {

    private lateinit var coinDataSource: CoinsDatasourceFactory

    private val TAG by lazy {
        javaClass.simpleName
    }

    companion object {
        private const val PAGE_SIZE = 10
    }

    fun search(timePeriod: String, query: String?): RepoSearchResult {

        coinDataSource = CoinsDatasourceFactory(coinsService, timePeriod, query)

        val networkStatus = Transformations.switchMap(coinDataSource.getCointLiveData()) {
            it.networkStatus
        }

        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE * 2)
            .setEnablePlaceholders(true)
            .build()

        val data = LivePagedListBuilder(coinDataSource, config)
            .build()

        return RepoSearchResult(data, networkStatus)

    }

}