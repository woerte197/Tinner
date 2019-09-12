package com.wangyang.baselibrary.view

import android.content.Context
import android.util.AttributeSet
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.wangyang.baselibrary.R

class BottomNavBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {
    private val textBadgeItem: TextBadgeItem = TextBadgeItem()
    private var shapeBadgeItem: ShapeBadgeItem = ShapeBadgeItem()

    init {
        val homeItem = BottomNavigationItem(R.drawable.btn_nav_home_press, "主页")
                .setInactiveIconResource(R.drawable.btn_nav_home_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
                .setBadgeItem(textBadgeItem)
        val homeItem1 = BottomNavigationItem(R.drawable.btn_nav_home_press, "我的")
                .setInactiveIconResource(R.drawable.btn_nav_home_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
                .setBadgeItem(TextBadgeItem())
                .setBadgeItem(shapeBadgeItem)
        addItem(homeItem).addItem(homeItem1).initialise()
        setMode(MODE_FIXED)
        setBackgroundStyle(BACKGROUND_STYLE_STATIC)
        setBarBackgroundColor(R.color.common_white)
        Integer.MAX_VALUE

    }

    fun setTextItemCount(count: Int) {
        if (count == 0) {
            textBadgeItem.hide()
        } else {
            textBadgeItem.show()
            textBadgeItem.setText("$count")
        }
    }

    fun isVisibilityShapeItem(isVisibility: Boolean) {
        if (isVisibility) {
            shapeBadgeItem.show()
        } else {
            shapeBadgeItem.hide()
        }

    }


}