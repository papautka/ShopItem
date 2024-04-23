package com.uteev.newshopitemactivity.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun editShopItem(shopItem : ShopItem)

    fun removeShopItem(shopItem: ShopItem)

    fun addShopItem(shopItem: ShopItem)

    fun getShopItem(id: Int) : ShopItem

    fun getShopList() : LiveData<List<ShopItem>>

}