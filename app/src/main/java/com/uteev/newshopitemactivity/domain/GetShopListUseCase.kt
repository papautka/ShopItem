package com.uteev.newshopitemactivity.domain

import androidx.lifecycle.LiveData

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopList() : LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }
}