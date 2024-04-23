package com.uteev.newshopitemactivity.presentation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.uteev.newshopitemactivity.R
import com.uteev.newshopitemactivity.domain.ShopItem

class ShopItemActivity : AppCompatActivity() {

    private var shopItemViewModel = ShopItemViewModel()
    private lateinit var shopListAdapter: ShopListAdapter
    private val errorName = shopItemViewModel.inputErrorName
    private val errorCount = shopItemViewModel.inputErrorCount
    private val shouldCloseScreen = shopItemViewModel.shouldCloseScreen
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
        shopItemViewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
    }

}