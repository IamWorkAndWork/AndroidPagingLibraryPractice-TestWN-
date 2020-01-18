package com.example.testwn.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coin")
data class Coin(
    @PrimaryKey
    @field:SerializedName("id")
    val id: Long,
    @SerializedName("approvedSupply")
    val approvedSupply: Boolean?,
    @SerializedName("change")
    val change: Double?,
    @SerializedName("circulatingSupply")
    val circulatingSupply: Double?,
    @SerializedName("color")
    val color: String?,
    @SerializedName("confi?rmedSupply")
    val confirmedSupply: Boolean,
    @SerializedName("description")
    val description: String?,
    @SerializedName("firstSeen")
    val firstSeen: Long?,
    @SerializedName("iconType")
    val iconType: String?,
    @SerializedName("iconUrl")
    val iconUrl: String?,
    @SerializedName("marketCap")
    val marketCap: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("numberOfExchanges")
    val numberOfExchanges: Long?,
    @SerializedName("numberOfMarkets")
    val numberOfMarkets: Long?,
    @SerializedName("penalty")
    val penalty: Boolean?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("rank")
    val rank: Long?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("totalSupply")
    val totalSupply: Double?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("uuid")
    val uuid: String?,
    @SerializedName("volume")
    val volume: Double?,
    @SerializedName("websiteUrl")
    val websiteUrl: String?
)