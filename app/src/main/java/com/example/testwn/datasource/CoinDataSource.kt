package com.example.testwn.datasource

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.testwn.api.CoinsService
import com.example.testwn.model.Coin
import com.example.testwn.model.NetworkState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CoinDataSource(
    private val api: CoinsService,
    private val timePeriod: String,
    private val query: String?
) : PageKeyedDataSource<Long, Coin>() {

    private var offset: Long = 0
    private val TAG by lazy {
        javaClass.simpleName
    }

    companion object {
        const val REQUEST_LIMIT = 20
    }

    private val _networkStatus = MutableLiveData<NetworkState>()
    val networkStatus: LiveData<NetworkState>
        get() = _networkStatus

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Coin>
    ) {
        GlobalScope.launch(Dispatchers.IO) {

            _networkStatus.postValue(NetworkState.LOADING)

            try {
                if (TextUtils.isEmpty(query)) {

                    Log.d(
                        TAG,
                        "loadInitial with default offset = " + offset + "  | timePeriod = " + timePeriod + " | query = " + query
                    )

                    val coins = api.fetchCoins(timePeriod, REQUEST_LIMIT, offset)
                    val listCoins = coins.data.coins

                    callback.onResult(listCoins, null, 1)

                    if (listCoins.size == 0) {
                        _networkStatus.postValue(NetworkState.LOADED_EMPTY)
                    } else {
                        _networkStatus.postValue(NetworkState.LOADED)
                    }


                } else {

                    Log.d(
                        TAG,
                        "loadInitial with search offset = " + offset + "  | timePeriod = " + timePeriod + " | query = " + query
                    )

                    val listCoins = loadDataFromQuery()
                    callback.onResult(listCoins, null, 1)

                    if (listCoins.size == 0) {
                        _networkStatus.postValue(NetworkState.LOADED_EMPTY)
                    } else {
                        _networkStatus.postValue(NetworkState.LOADED)
                    }
                }

            } catch (e: Exception) {
                _networkStatus.postValue(NetworkState.error(e.message))

                Log.e(TAG, "error = " + e)
            }
        }

    }


    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Coin>) {
        GlobalScope.launch(Dispatchers.IO) {
            try {

                offset = params.key

                if (TextUtils.isEmpty(query)) {
                    Log.d(
                        TAG,
                        "loadAfter with default offset = " + offset + "  | timePeriod = " + timePeriod + " | query = " + query
                    )

                    _networkStatus.postValue(NetworkState.LOADING)

                    val coins = api.fetchCoins(timePeriod, REQUEST_LIMIT, offset)
                    callback.onResult(coins.data.coins, offset + 1)
                    _networkStatus.postValue(NetworkState.LOADED)

                }
//                else {
//                    Log.d(
//                        TAG,
//                        "loadAfter with search offset = " + offset + "  | timePeriod = " + timePeriod + " | query = " + query
//                    )
//
//                    val listCoins = loadDataFromQuery()
//                    callback.onResult(listCoins, offset + 1)
//                    _networkStatus.postValue(NetworkState.LOADED)
//                }


            } catch (e: Exception) {
                _networkStatus.postValue(NetworkState.error(e.message))
            }
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Coin>) {

    }


    private suspend fun loadDataFromQuery(): MutableList<Coin> {
        val coinsByIDs = api.searchCoinsByIds(timePeriod, query)
        val coinsBySlugs = api.searchCoinsBySlugs(timePeriod, query)
        val coinsByPrefix = api.searchCoinsByPrefix(timePeriod, query)
        val coinsBySymbols = api.searchCoinsBySymbols(timePeriod, query)
        val listCoins = mutableListOf<Coin>()
        listCoins.addAll(isHaveCoin(coinsByIDs.data.coins))
        listCoins.addAll(isHaveCoin(coinsBySlugs.data.coins))
        listCoins.addAll(isHaveCoin(coinsByPrefix.data.coins))
        listCoins.addAll(isHaveCoin(coinsBySymbols.data.coins))
        return listCoins
    }

    private fun isHaveCoin(coins: List<Coin>): Collection<Coin> {
        if (coins.size == 1) {
            if (coins.get(0) == null) {
                return emptyList()
            }
        }
        return coins
    }

}