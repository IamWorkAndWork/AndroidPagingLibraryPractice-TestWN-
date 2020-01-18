package com.example.testwn.ui.coins_list

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.testwn.data.CoinsRepository
import com.example.testwn.model.Coin
import com.example.testwn.model.NetworkState
import com.example.testwn.model.RepoSearchResult


data class Params(var timePeriod: String, var query: String)

class CoinsListViewModel(
    private val repository: CoinsRepository
) : ViewModel() {

    private val _parameterQuery = MutableLiveData<Params>().apply {
        this.value = Params(FILTER_24H, "")
    }
    var parameterQuery: LiveData<Params> = _parameterQuery

    private val _timePeriod = MutableLiveData<String>()
    var timePeriod: LiveData<String> = _timePeriod

    private val TAG by lazy {
        javaClass.simpleName
    }

    companion object {
        const val FILTER_24H = "24h"
        const val FILTER_7D = "7d"
        const val FILTER_30D = "30d"
    }

    val onTextQuery: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(query: Editable?) {
            Log.d(TAG, "search query = " + query)
            searchQuery(query.toString())
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
    }

    private val repoResult: LiveData<RepoSearchResult> =
        Transformations.map(_parameterQuery) { query ->
            repository.search(query.timePeriod, query.query)

        }

    var listCoins: LiveData<PagedList<Coin>> =
        Transformations.switchMap(repoResult) {
            it.data
        }

    var networkStatus: LiveData<NetworkState> = Transformations.switchMap(repoResult) {
        it.networkStatus
    }

    fun searchQuery(query: String?) {
        _parameterQuery.value =
            Params(_parameterQuery.value?.timePeriod.toString(), query.toString())
    }

    fun filterByPeriod(filter: String) {
        _parameterQuery.value = Params(filter, _parameterQuery.value?.query.toString())
        _timePeriod.value = filter
    }


}
