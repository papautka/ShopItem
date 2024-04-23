package com.uteev.newshopitemactivity.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uteev.newshopitemactivity.data.ShopListRepositoryImpl
import com.uteev.newshopitemactivity.domain.AddShopItemUseCase
import com.uteev.newshopitemactivity.domain.EditShopItemUseCase
import com.uteev.newshopitemactivity.domain.GetShopItem
import com.uteev.newshopitemactivity.domain.GetShopListUseCase
import com.uteev.newshopitemactivity.domain.ShopItem

class ShopItemViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getShopItem = GetShopItem(repository)

    private val _inputErrorName = MutableLiveData<Boolean>()
    val inputErrorName: LiveData<Boolean>
        get() =_inputErrorName

    private val _inputErrorCount = MutableLiveData<Boolean>()
    val inputErrorCount : LiveData<Boolean>
        get() = _inputErrorCount

    val shopList = GetShopListUseCase(repository).getShopList()

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen
    fun addShopItem(inputName : String?, inputCount : String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        if(validateInput(name, count)) {
            val shopItem = ShopItem(
                name,
                count,
                true
            )
            addShopItemUseCase.addShopItem(shopItem)
            finishScreen()
        } else {
            Log.d("addShopItem", "validation error")
        }
    }

    fun editShopItem(inputNewName : String?, inputNewCount : String?) {
        val name = parseName(inputNewName)
        val count = parseCount(inputNewCount)
        if(validateInput(name, count)) {
            _shopItem.value?.let {
                val shopItem = it.copy(name = name, count = count)
                editShopItemUseCase.editShopItem(shopItem)
                finishScreen()
            }
        } else {
            Log.d("editShopItem", "validation error")
        }
    }

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem : LiveData<ShopItem>
        get() = _shopItem

    fun getItem(shopItemId: Int) {
        val item = getShopItem.getShopItem(shopItemId)
        _shopItem.value = item
    }

    private fun parseName(inputName : String?) : String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount : String?) : Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }

    }

    private fun validateInput(inputName : String, inputCount : Int) : Boolean {
        var result = true
        if (inputName.isBlank()) {
            _inputErrorName.value = true
            result = false
        }
        if (inputCount <= 0) {
            _inputErrorCount.value = true
            result = false
        }
        return result
    }

    public fun resetErrorInputName() {
        _inputErrorName.value = false
    }

    public fun resetErrorInputCount() {
        _inputErrorCount.value = false
    }

    public fun finishScreen() {
        _shouldCloseScreen.value = Unit
    }


}