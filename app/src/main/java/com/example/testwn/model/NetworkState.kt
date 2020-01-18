package com.example.testwn.model

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED,
    EMPTY
}

data class NetworkState  constructor(
    val status: Status,
    val message: String? = null
) {

    companion object {
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)
        val LOADED_EMPTY = NetworkState(Status.EMPTY)
        fun error(msg: String?) = NetworkState(Status.FAILED, msg)
    }
}