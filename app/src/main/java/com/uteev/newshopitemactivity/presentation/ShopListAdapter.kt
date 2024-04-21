package com.uteev.newshopitemactivity.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.uteev.newshopitemactivity.R
import com.uteev.newshopitemactivity.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {
    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }

    var count = 0

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        Log.d("TAG", "onCreateViewHolder + ${++count}")
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
        val shopItem = shopList[position]
        val status = if (shopItem.enabled) "Enabled" else "Disabled"

        viewHolder.view.setOnLongClickListener {
            true
        }
        if (shopItem.enabled) {
            viewHolder.tvName.text = status
            viewHolder.tvCount.text = shopItem.count.toString()
            viewHolder.tvName.setTextColor(ContextCompat.getColor(viewHolder.view.context, R.color.black))
        } else {
            viewHolder.tvName.text = status
            viewHolder.tvCount.text = shopItem.count.toString()
            viewHolder.tvName.setTextColor(ContextCompat.getColor(viewHolder.view.context, R.color.white))
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (shopList[position].enabled) {
            return ENABLED
        } else {
            return DISABLED
        }
    }

    companion object {
        const val ENABLED = 1
        const val DISABLED = 0
    }
}