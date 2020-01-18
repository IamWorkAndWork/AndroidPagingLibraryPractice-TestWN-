package com.example.testwn.ui.coins_list

import com.example.testwn.model.Coin

interface OnCoinsListListener {
    fun onClicked(coin: Coin?)
}