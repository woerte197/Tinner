package com.wangyang.tinner.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import com.example.router_lib.provider.moudle.home.IHomeProvider
import com.example.router_lib.provider.moudle.news.INewsProvider
import com.example.router_lib.router.RouterManager
import com.wangyang.baselibrary.utils.FragmentManager
import com.wangyang.news_moudle.ui.NewsFragment
import com.wangyang.tinner.R
import com.wangyang.tinner.dagger.compontent.DaggerSplashComment
import com.wangyang.tinner.presenter.MainPresenter
import com.wangyang.tinner.presenter.view.MainView
import com.wangyang.tinner_lib.StartsBarUtil
import com.wangyang.tinner_lib.common.BottomTabSelectListener
import com.wangyang.tinner_lib.ui.TinnerActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject


class MainActivity : TinnerActivity<MainPresenter>(), MainView {
    override fun inject() {
        DaggerSplashComment.builder().build().inject(this)
    }

    private val homeFragment by lazy { RouterManager.INS.getMainProvider().newInstance(IHomeProvider.HOME_FRAGMENT) }
    private val newsFragment by lazy { RouterManager.INS.getMainProvider().newInstance(INewsProvider.NEWS_FRAGMENT) }

    private val stack: Stack<Fragment> = Stack()

    @Inject
    lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initEvent()
    }

    private fun initEvent() {
        mBottomBar.setTabSelectedListener(object : BottomTabSelectListener {
            override fun onTabSelected(position: Int) {
                fragmentManager.changeFragment(supportFragmentManager.beginTransaction(), position)
            }
        })
    }

    private fun initView() {
        stack.push(homeFragment)
        stack.push(newsFragment)
        stack.push(NewsFragment())
        stack.push(NewsFragment())
        fragmentManager.addFragments(R.id.mFrameLayout, supportFragmentManager.beginTransaction(), stack)
        fragmentManager.changeFragment(supportFragmentManager.beginTransaction(), 0)
    }

}