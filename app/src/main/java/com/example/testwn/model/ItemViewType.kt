package com.example.testwn.model


sealed class ItemViewType() {
    class ItemNormal(var coin: Coin?) : ItemViewType()
    class ItemRight(var coin: Coin?) : ItemViewType()
}
