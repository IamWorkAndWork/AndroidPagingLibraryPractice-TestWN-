package com.example.testwn.ui.coins_list

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testwn.model.Coin
import com.example.testwn.ui.coins_list.view_holder.CoinsListRightViewHolder
import com.example.testwn.ui.coins_list.view_holder.CoinsListViewHolder

class CoinsListAdapter(private val listener: OnCoinsListListener) :
    PagedListAdapter<Coin, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_NORMAL -> {
                return CoinsListViewHolder.create(parent, listener)
            }
            else -> {
                return CoinsListRightViewHolder.create(parent, listener)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val coin = getItem(position)

        when (holder.itemViewType) {
            TYPE_NORMAL -> {
                (holder as CoinsListViewHolder).bindItem(coin)
            }
            else -> {
                (holder as CoinsListRightViewHolder).bindItem(coin)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val pos = (position + 1)
        if (pos % 5 == 0) {
            return TYPE_RIGHT
        }
        return TYPE_NORMAL
    }

    companion object {
        const val TYPE_NORMAL = 1
        const val TYPE_RIGHT = 2

        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Coin>() {
            override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean =
                oldItem == newItem
        }

    }


}