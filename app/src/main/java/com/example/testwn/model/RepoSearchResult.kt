package com.example.testwn.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class RepoSearchResult(
    val data: LiveData<PagedList<Coin>>,
    val networkStatus: LiveData<NetworkState>
)


