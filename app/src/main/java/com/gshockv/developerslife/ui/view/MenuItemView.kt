package com.gshockv.developerslife.ui.view

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.gshockv.developerslife.R
import kotlinx.android.synthetic.main.view_menu_item_layout.view.*

class MenuItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private var checkedTint = ContextCompat.getColor(context, R.color.menuCheckedColor)
    private val defaultIconTint = ContextCompat.getColor(context, R.color.menuIconDefaultTint)
    private val defaultTitleColor = ContextCompat.getColor(context, R.color.menuItemTextColor)
    private val defaultMenuIcon = R.drawable.ic_menu_latest

    init {
        LayoutInflater.from(context).inflate(R.layout.view_menu_item_layout, this, true)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.MenuItemView, 0, 0)
            val title = typedArray.getString(R.styleable.MenuItemView_menuTitle)
            val icon = typedArray.getResourceId(R.styleable.MenuItemView_menuIcon, defaultMenuIcon)
            val checkedMenuTint = typedArray.getColor(R.styleable.MenuItemView_menuCheckedTint, 0)
            if (checkedMenuTint > 0) {
                checkedTint = checkedMenuTint
            }
            val isCheckedNow = typedArray.getBoolean(R.styleable.MenuItemView_menuItemChecked, false)
            typedArray.recycle()

            textViewItem.text = title
            menuIcon.setImageResource(icon)
            ImageViewCompat.setImageTintList(menuIcon, ColorStateList.valueOf(defaultIconTint))

            if (isCheckedNow) {
                setChecked(true)
            }
        }
    }

    fun setChecked(checked: Boolean) {
        val bg = if (checked) R.drawable.menu_item_checked else android.R.drawable.list_selector_background
        setBackgroundResource(bg)
        val iconTint = if (checked) checkedTint else defaultIconTint
        ImageViewCompat.setImageTintList(menuIcon, ColorStateList.valueOf(iconTint))
        val titleColor = if (checked) checkedTint else defaultTitleColor
        textViewItem.setTextColor(titleColor)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        textViewItem.setOnClickListener(l)
        menuIcon.setOnClickListener(l)
    }
}
