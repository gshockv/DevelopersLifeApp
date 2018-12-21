package com.gshockv.developerslife.ui.stream

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.gshockv.developerslife.R
import com.gshockv.developerslife.data.StreamType
import com.gshockv.developerslife.ui.stream.view.MenuItemView


class BottomNavigationDialog : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "BottomNavigationDialog"

        fun newInstance() = BottomNavigationDialog()
    }

    var onStreamSelected: ((type: StreamType) -> Unit)? = null
    var selectedStream: StreamType = StreamType.LATEST

    private lateinit var items : HashMap<StreamType, MenuItemView>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buildItemsList(view)

        items.forEach { item ->
            item.value.setOnClickListener {
                hightlightStreamItem(item.value)
                dismiss()
                onStreamSelected?.invoke(item.key)
            }
        }

        hightlightStreamItem(items[selectedStream]!!)
    }

    private fun hightlightStreamItem(item: MenuItemView) {
        uncheckAllItems()
        item.setChecked(true)
    }

    private fun buildItemsList(view: View) {
        with(view) {
            items = hashMapOf(
                StreamType.LATEST to findViewById(R.id.menuItemLatest),
                StreamType.HOT to findViewById(R.id.menuItemHot),
                StreamType.TOP to findViewById(R.id.menuItemTop),
                StreamType.RANDOM to findViewById(R.id.menuItemRandom)
            )
        }
    }

    private fun uncheckAllItems() {
        if (::items.isInitialized.not()) {
            buildItemsList(view!!)
        }
        items.forEach {
            it.value.setChecked(false)
        }
    }
}
