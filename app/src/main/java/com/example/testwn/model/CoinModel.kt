package com.example.testwn.model


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coin")
data class CoinModel(
    @Ignore
    @SerializedName("allTimeHigh")
    val allTimeHigh: AllTimeHigh,
    @SerializedName("approvedSupply")
    val approvedSupply: Boolean,
    @SerializedName("change")
    val change: Double,
    @SerializedName("circulatingSupply")
    val circulatingSupply: Double,
    @SerializedName("color")
    val color: String,
    @SerializedName("confirmedSupply")
    val confirmedSupply: Boolean,
    @SerializedName("description")
    val description: String,
    @SerializedName("firstSeen")
    val firstSeen: Long,
    @Ignore
    @SerializedName("history")
    val history: List<String>,
    @SerializedName("iconType")
    val iconType: String,
    @SerializedName("iconUrl")
    val iconUrl: String,
    @PrimaryKey
    @SerializedName("id")
    val id: Long,
    @Ignore
    @SerializedName("links")
    val links: List<Link>,
    @SerializedName("marketCap")
    val marketCap: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("numberOfExchanges")
    val numberOfExchanges: Long,
    @SerializedName("numberOfMarkets")
    val numberOfMarkets: Long,
    @SerializedName("penalty")
    val penalty: Boolean,
    @SerializedName("price")
    val price: String,
    @SerializedName("rank")
    val rank: Long,
    @SerializedName("slug")
    val slug: String,
    @Ignore
    @SerializedName("socials")
    val socials: List<Social>,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("totalSupply")
    val totalSupply: Double,
    @SerializedName("type")
    val type: String,
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("volume")
    val volume: Double,
    @SerializedName("websiteUrl")
    val websiteUrl: String
)