package com.uteev.newshopitemactivity.presentation

import androidx.recyclerview.widget.DiffUtil
import com.uteev.newshopitemactivity.domain.ShopItem

class ShopItemDiffCallback : DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
       return oldItem == newItem
    }

    override fun getChangePayload(oldItem: ShopItem, newItem: ShopItem): Any? {
        return super.getChangePayload(oldItem, newItem)
    }
}