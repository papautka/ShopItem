package com.uteev.newshopitemactivity.domain

class GetShopItem(private val shopListRepository: ShopListRepository) {
    fun getShopItem(id: Int) : ShopItem {
        return shopListRepository.getShopItem(id)
    }
}