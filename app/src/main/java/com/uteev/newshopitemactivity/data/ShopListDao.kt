package com.uteev.newshopitemactivity.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShopListDao {

    @Query("SELECT * FROM shopitemdbmodel")
    fun getShopList() : LiveData<List<ShopItemDbModel>>

    @Query("SELECT * FROM shopitemdbmodel WHERE id=:shopItemId LIMIT 1")
    fun getShopItem(shopItemId: Int): LiveData<ShopItemDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addShopItem(shopItem: ShopItemDbModel)


    @Query("DELETE FROM shopitemdbmodel WHERE id=:shopItemId")
    fun deleteShopItem(shopItemId: Int)


    fun editShopItem(shopItem: ShopItemDbModel)

    fun changeEnabledState(shopItem: ShopItemDbModel)

}