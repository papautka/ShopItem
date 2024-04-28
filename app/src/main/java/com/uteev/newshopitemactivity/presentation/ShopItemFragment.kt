package com.uteev.newshopitemactivity.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import com.uteev.newshopitemactivity.R
import com.uteev.newshopitemactivity.domain.ShopItem

class ShopItemFragment() : Fragment() {

    private var shopItemViewModel = ShopItemViewModel()
    private lateinit var shopListAdapter: ShopListAdapter
    private val errorName = shopItemViewModel.inputErrorName
    private val errorCount = shopItemViewModel.inputErrorCount
    private val shopItem = shopItemViewModel.shopItem
    private val shouldCloseScreen = shopItemViewModel.shouldCloseScreen

    private lateinit var textInputName: TextInputLayout
    private lateinit var textInputCount: TextInputLayout
    private lateinit var etName: EditText
    private lateinit var etCount: EditText
    private lateinit var saveButton: Button

    private lateinit var onEditingFinishedListener : OnEditingFinishedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnEditingFinishedListener) {
            onEditingFinishedListener = context
        } else {
            throw RuntimeException("Activity must implement OnEditingFinishedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shop_item, container, false)
        return view
    }

    private var shopItemId = ShopItem.UNDEFINED_ID
    private var screenMode = UNKOWN_MODE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shopItemViewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        initViews(view)
        changeCountResetError()
        changeNameResetError()
        when (screenMode) {
            MODE_ADD -> startAddMode()
            MODE_EDIT -> startEditMode()
        }
        checkErrorCount()
        checkErrorName()
        checkFinishActivity()
    }

    private fun initViews(view: View) {
        textInputName = view.findViewById(R.id.til_name)
        textInputCount = view.findViewById(R.id.til_count)
        etName = view.findViewById(R.id.et_name)
        etCount = view.findViewById(R.id.et_count)
        saveButton = view.findViewById(R.id.save_button)

    }


    private fun changeCountResetError() {
        etCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                shopItemViewModel.resetErrorInputCount()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun changeNameResetError() {
        etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                shopItemViewModel.resetErrorInputName()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun checkErrorName() {
        shopItemViewModel.inputErrorName.observe(viewLifecycleOwner) {
            val messageError = if (it) {
                getString(R.string.errorName)
            } else {
                null
            }
            Log.d("checkErrorName", "$messageError")
            if (messageError != null) {
                textInputName.error = messageError
            }
        }
    }

    private fun checkErrorCount() {
        shopItemViewModel.inputErrorCount.observe(viewLifecycleOwner) {
            val messageError = if (it) {
                getString(R.string.errorCount)
            } else {
                null
            }
            if (messageError != null)
                textInputCount.error = messageError.toString()
        }
    }

    private fun checkFinishActivity() {
        shopItemViewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
//            activity?.onBackPressed()
//            requireActivity().finish()

//            (activity as MainActivity).onEditFinished() // так делать не надо
                // так как фрагмент уже уничтожен
            onEditingFinishedListener?.onEditingFinished()
        }
    }


    private fun startAddMode() {
        saveButton.setOnClickListener {
            val name = etName.text.toString()
            val count = etCount.text.toString()
            shopItemViewModel.addShopItem(name, count)
        }
    }

    private fun startEditMode() {
        shopItemViewModel.getItem(shopItemId)
        shopItemViewModel.shopItem.observe(viewLifecycleOwner) {
            etName.setText(it.name)
            etCount.setText(it.count.toString())
        }
        saveButton.setOnClickListener {
            val name = etName.text?.toString()
            val count = etCount.text?.toString()
            shopItemViewModel.editShopItem(name, count)
        }
    }

    companion object {
        private const val SCREEN_MODE = "extra_mode"
        private const val SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val UNKOWN_MODE = ""

        fun newInstanceAddItem(): ShopItemFragment {
            val args = Bundle().apply {
                putString(SCREEN_MODE, MODE_ADD)
            }
            val fragment = ShopItemFragment()
            fragment.arguments = args
            return fragment
        }

        fun newInstanceEditItem(shopItemId: Int): ShopItemFragment {
            val args = Bundle().apply {
                putString(SCREEN_MODE, MODE_EDIT)
                putInt(SHOP_ITEM_ID, shopItemId)
            }
            return ShopItemFragment().apply {
                arguments = args
            }
        }

    }

    interface OnEditingFinishedListener {
        fun onEditingFinished()
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
                throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(SHOP_ITEM_ID)) {
                throw RuntimeException("Param shop item id is absent")
            }
            shopItemId = args.getInt(SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
        }
    }
}
