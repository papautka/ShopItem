package com.uteev.newshopitemactivity.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.uteev.newshopitemactivity.R
import com.uteev.newshopitemactivity.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemOnLongClick : ((ShopItem) -> Unit)? = null
    var onShopItemClick : ((ShopItem) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            0 -> R.layout.item_shop_disbaled
            else -> R.layout.item_shop_enabled
        }
        val view = LayoutInflater.from(parent.context).inflate(
            layout,
            parent,
            false
        )
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)

        viewHolder.view.setOnLongClickListener {
            onShopItemOnLongClick?.invoke(shopItem)
            true
        }
        viewHolder.view.setOnClickListener {
            onShopItemClick?.invoke(shopItem)
        }

        viewHolder.tvName.text = shopItem.name
        viewHolder.tvCount.text = shopItem.count.toString()
    }

    override fun getItemViewType(position: Int): Int {
        if (getItem(position).enabled) {
            return ENABLED
        } else {
            return DISABLED
        }
    }

    companion object {
        const val ENABLED = 1
        const val DISABLED = 0
        const val MAX_SIZE_POOL = 15
    }

}