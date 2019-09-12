package com.wangyang.tinner.widget

import android.content.Context
import android.util.AttributeSet
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.wangyang.tinner.R
import org.jetbrains.anko.backgroundResource

class BottomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {
    private var mTextBadgeItem: TextBadgeItem = TextBadgeItem()

    init {

//        val homeItem = BottomNavigationItem(
//            R.drawable.select_home,
//            "首页"
//        ).setInactiveIconResource(R.drawable.home)
//            .setActiveColorResource(R.color.app_main)
//            .setInActiveColorResource(R.color.text_normal)
//
//        val newsItem = BottomNavigationItem(
//            R.drawable.select_news,
//            "资讯"
//        ).setInactiveIconResource(R.drawable.news)
//            .setActiveColorResource(R.color.app_main)
//            .setInActiveColorResource(R.color.text_normal)
//
//        val sportItem = BottomNavigationItem(
//            R.drawable.selece_sport,
//            "运动"
//        ).setInactiveIconResource(R.drawable.sport)
//            .setActiveColorResource(R.color.app_main)
//            .setInActiveColorResource(R.color.text_normal)
//
//        val mineItem = BottomNavigationItem(
//            R.drawable.select_mine,
//            "我的"
//        ).setInactiveIconResource(R.drawable.mine)
//            .setActiveColorResource(R.color.app_main)
//            .setInActiveColorResource(R.color.text_normal)
//        setMode(MODE_FIXED)
//        setBarBackgroundColor(R.color.common_white)
//        addItem(homeItem).addItem(newsItem).addItem(sportItem).addItem(mineItem).initialise()
//        Integer.MAX_VALUE



        val homeItem = BottomNavigationItem(
            R.drawable.home,
            "首页"
        ).setInactiveIconResource(R.drawable.home)
            .setActiveColorResource(R.color.app_main)
            .setInActiveColorResource(R.color.text_normal)

        val newsItem = BottomNavigationItem(
            R.drawable.news,
            "资讯"
        ).setInactiveIconResource(R.drawable.news)
            .setActiveColorResource(R.color.text_normal)
            .setInActiveColorResource(R.color.text_normal)

        val sportItem = BottomNavigationItem(
            R.drawable.sport,
            "运动"
        ).setInactiveIconResource(R.drawable.sport)
            .setActiveColorResource(R.color.text_normal)
            .setInActiveColorResource(R.color.text_normal)

        val mineItem = BottomNavigationItem(
            R.drawable.mine,
            "我的"
        ).setInactiveIconResource(R.drawable.mine)
            .setActiveColorResource(R.color.text_normal)
            .setInActiveColorResource(R.color.text_normal)
        setMode(MODE_SHIFTING)
        backgroundResource = R.color.app_main
        addItem(homeItem).addItem(newsItem).addItem(sportItem).addItem(mineItem).initialise()
        Integer.MAX_VALUE

    }

    fun setMineText(count: Int) {
        if (count == 0) {
            mTextBadgeItem.hide()
        } else {
            mTextBadgeItem.setText("$count")
            mTextBadgeItem.show()
        }
    }


}