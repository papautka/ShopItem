package com.uteev.newshopitemactivity.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.uteev.newshopitemactivity.R
import com.uteev.newshopitemactivity.domain.ShopItem

class ShopItemActivity : AppCompatActivity() {

//    private var shopItemViewModel = ShopItemViewModel()
//    private lateinit var shopListAdapter: ShopListAdapter
//    private val errorName = shopItemViewModel.inputErrorName
//    private val errorCount = shopItemViewModel.inputErrorCount
//    private val shopItem = shopItemViewModel.shopItem
//    private val shouldCloseScreen = shopItemViewModel.shouldCloseScreen
//
//    private lateinit var textInputName : TextInputLayout
//    private lateinit var textInputCount : TextInputLayout
//    private lateinit var etName: EditText
//    private lateinit var etCount: EditText
//    private lateinit var saveButton : Button

//    private fun initViews() {
//        textInputName = findViewById(R.id.til_name)
//        textInputCount = findViewById(R.id.til_count)
//        etName = findViewById(R.id.et_name)
//        etCount = findViewById(R.id.et_count)
//        saveButton = findViewById(R.id.save_button)
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
//        parseIntent()
//        shopItemViewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
//        initViews()
//        changeCountResetError()
//        changeNameResetError()
//        when (screenMode) {
//            MODE_ADD -> startAddMode()
//            MODE_EDIT -> startEditMode()
//        }
//        checkErrorCount()
//        checkErrorName()
//        checkFinishActivity()
    }

//    private fun changeCountResetError() {
//        etCount.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                shopItemViewModel.resetErrorInputCount()
//            }
//            override fun afterTextChanged(s: Editable?) {
//            }
//        })
//    }
//    private fun changeNameResetError() {
//        etName.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                shopItemViewModel.resetErrorInputName()
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//            }
//        })
//    }
//
//    private fun checkErrorName() {
//        shopItemViewModel.inputErrorName.observe(this) {
//            val messageError = if (it) {
//                getString(R.string.errorName)
//            } else {
//                null
//            }
//            Log.d("checkErrorName", "$messageError")
//            if (messageError != null) {
//                textInputName.error = messageError
//            }
//        }
//    }
//    private fun checkErrorCount() {
//        shopItemViewModel.inputErrorCount.observe(this) {
//            val messageError = if (it) {
//                getString(R.string.errorCount)
//            } else {
//                null
//            }
//            if (messageError != null)
//            textInputCount.error = messageError.toString()
//        }
//    }
//
//    private fun checkFinishActivity() {
//        shopItemViewModel.shouldCloseScreen.observe(this) {
//                finish()
//        }
//    }
//
//
//    private fun startAddMode() {
//        saveButton.setOnClickListener {
//            val name = etName.text.toString()
//            val count = etCount.text.toString()
//            shopItemViewModel.addShopItem(name, count)
//        }
//    }
//
//    private fun startEditMode() {
//        shopItemViewModel.getItem(shopItemId)
//        shopItemViewModel.shopItem.observe(this) {
//            etName.setText(it.name)
//            etCount.setText(it.count.toString())
//        }
//        saveButton.setOnClickListener {
//            val name = etName.text?.toString()
//            val count = etCount.text?.toString()
//            shopItemViewModel.editShopItem(name, count)
//        }
//    }

    companion object {
        private const val EXTRA_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val UNKOWN_MODE = ""
        private var shopItemId = ShopItem.UNDEFINED_ID
        private var screenMode = UNKOWN_MODE

        fun newIntentAddMode(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditMode(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            return intent
        }
    }

//    private fun parseIntent() {
//        if(!intent.hasExtra(EXTRA_MODE)) {
//            throw RuntimeException("Param extra mode is absent!")
//        }
//        screenMode = intent.getStringExtra(EXTRA_MODE) ?: UNKOWN_MODE
//        if (screenMode != MODE_ADD && screenMode != MODE_EDIT) {
//            throw RuntimeException("Unknown mode $screenMode")
//        }
//        if (screenMode == MODE_EDIT) {
//            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
//                throw RuntimeException("Param extra shop item id is absent!")
//            }
//            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, -1)
//        }
//    }

}