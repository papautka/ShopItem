package com.uteev.newshopitemactivity.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uteev.newshopitemactivity.data.ShopListRepositoryImpl
import com.uteev.newshopitemactivity.domain.AddShopItemUseCase
import com.uteev.newshopitemactivity.domain.EditShopItemUseCase
import com.uteev.newshopitemactivity.domain.GetShopListUseCase
import com.uteev.newshopitemactivity.domain.RemoveShopItemUseCase
import com.uteev.newshopitemactivity.domain.ShopItem
import com.uteev.newshopitemactivity.domain.ShopListRepository

class MainViewModel : ViewModel() {
    // так делать не надо (data не должна знать о presentation)
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val removeShopItemUseCase = RemoveShopItemUseCase(repository)

    val shopList = GetShopListUseCase(repository).getShopList()

    fun editShopItem(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }

    fun removeShopItem(shopItem: ShopItem) {
        removeShopItemUseCase.removeShopItem(shopItem)
    }

}
