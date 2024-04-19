package com.uteev.newshopitemactivity.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.uteev.newshopitemactivity.domain.ShopItem
import com.uteev.newshopitemactivity.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()
    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private var autoIncrement = 0



    init {
        for (i in 0 until 15) {
            val item = ShopItem("Name $i", i, true)
            addShopItem(item)
        }
    }


    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun removeShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun addShopItem(shopItem: ShopItem) {
        if(shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrement++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
       return shopList.find { it.id == shopItemId }
            ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateList() {
        shopListLD.value = shopList.toList()
    }

}
