package com.uteev.newshopitemactivity.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.uteev.newshopitemactivity.R
import com.uteev.newshopitemactivity.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter
    private var shopItemContainer : FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
        shopItemContainer = findViewById(R.id.shop_item_container)
            // книжная ориентация
            setupRecyclerView()
            viewModel = ViewModelProvider(this)[MainViewModel::class.java]
            viewModel.shopList.observe(this) {
                // Perform any necessary actions with the shopList
//            shopListAdapter.shopList = it
                shopListAdapter.submitList(it)
            }
            val buttonAddShopItem = findViewById<FloatingActionButton>(R.id.button_add_shop_item)
            buttonAddShopItem.setOnClickListener {
                if(checkScreenOrientation()) {
                    val intentAdd = ShopItemActivity.newIntentAddMode(this)
                    intentAdd.putExtra("extra_mode", "mode_add")
                    startActivity(intentAdd)
                } else {
                    launchFragment(ShopItemFragment.newInstanceAddItem())
                }
            }
    }

    private fun checkScreenOrientation() : Boolean {
        return shopItemContainer == null
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.shop_item_container, fragment)
            .commit()
    }

    private fun setupRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        installRv(rvShopList)
        setupOnLongClickListenerChangeStatus()
        setupOnClickListener()
        touchSwipeRemove(rvShopList)

    }

    private fun installRv(rvShopList: RecyclerView) {
        with(rvShopList) {
            shopListAdapter = ShopListAdapter()
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.ENABLED,
                ShopListAdapter.MAX_SIZE_POOL
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.DISABLED,
                ShopListAdapter.MAX_SIZE_POOL
            )
        }
    }

    private fun setupOnLongClickListenerChangeStatus() {
        shopListAdapter.onShopItemOnLongClick = {
            viewModel.editShopItem(it)
        }
    }

    private fun setupOnClickListener() {
        shopListAdapter.onShopItemClick = {
            if (checkScreenOrientation()) {
                val intentEdit = ShopItemActivity.newIntentEditMode(this, it.id)
                intentEdit.putExtra("extra_mode", "mode_edit")
                intentEdit.putExtra("extra_shop_item_id", it.id)
                startActivity(intentEdit)
            } else {
                launchFragment(ShopItemFragment.newInstanceEditItem(shopItemId = it.id))

            }
        }
    }

    private fun touchSwipeRemove(rvShopList: RecyclerView?) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val shopItem = shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.removeShopItem(shopItem)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }
}