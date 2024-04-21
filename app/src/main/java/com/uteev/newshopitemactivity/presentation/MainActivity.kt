package com.uteev.newshopitemactivity.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.uteev.newshopitemactivity.R
import com.uteev.newshopitemactivity.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : MainViewModel
    private lateinit var llShopList : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        llShopList = findViewById(R.id.ll_shop_list)
        viewModel.shopList.observe(this) {
            showList(it)
            // Perform any necessary actions with the shopList
        }
    }

    private fun showList(list : List<ShopItem>) {
        llShopList.removeAllViews()
        for (shopItem in list) {
           val layoutId = if(shopItem.enabled) {
               R.layout.item_shop_enabled
           } else {
               R.layout.item_shop_disbaled
           }
           val view = LayoutInflater.from(this).inflate(layoutId, llShopList, false)
            val tvName = view.findViewById<TextView>(R.id.tv_name)
            val tvCount = view.findViewById<TextView>(R.id.tv_count)
            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()
            llShopList.addView(view)

            view.setOnLongClickListener {
                viewModel.editShopItem(shopItem)
                true
            }
    }
    }
}