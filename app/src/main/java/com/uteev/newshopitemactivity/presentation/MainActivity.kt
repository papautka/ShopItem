package com.uteev.newshopitemactivity.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.uteev.newshopitemactivity.R
import com.uteev.newshopitemactivity.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            // Perform any necessary actions with the shopList
            shopListAdapter.shopList = it
        }
    }
    private fun setupRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        with(rvShopList) {
            shopListAdapter = ShopListAdapter()
            adapter =  shopListAdapter
            recycledViewPool.setMaxRecycledViews(ShopListAdapter.ENABLED, ShopListAdapter.MAX_SIZE_POOL)
           recycledViewPool.setMaxRecycledViews(ShopListAdapter.DISABLED, ShopListAdapter.MAX_SIZE_POOL)
        }
        shopListAdapter.onShopItemOnLongClick = {
                viewModel.editShopItem(it)
        }
    }

}