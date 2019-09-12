package com.wangyang.baselibrary.recycle.refresh

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.wangyang.baselibrary.recycle.adapter.FooterRecycleAdapter


class RefreshHandler(adapter: FooterRecycleAdapter, val recycleView: RecyclerView) {
    private var refreshListener: RefreshListener? = null
    private var page: Page? = null
    private var loaded: Boolean = true

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            recycleScrolled(recycleView.layoutManager as GridLayoutManager, dy)
        }
    }

    init {
        page = adapter.getPage()
        recycleView.addOnScrollListener(scrollListener)
        recycleView.adapter = adapter
    }


    fun recycleScrolled(layoutManager: GridLayoutManager, dy: Int) {
        val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
        val totalItemCount = layoutManager.itemCount
        if (lastVisibleItem >= totalItemCount - 1 && dy > 0) {
            loadBottom()
        }

    }

    private fun loadBottom() {
        if (page!!.isNeedNext && loaded) {
            page!!.size += 1
            refreshListener!!.loadBottom(page!!.size)
            loaded = false
        }
    }

    interface RefreshListener {
        fun loadBottom(pageSize: Int)
    }

    fun setRefreshListener(listener: RefreshListener) {
        refreshListener = listener
    }

    fun setLoaded(isLoaded: Boolean) {
        this.loaded = isLoaded
    }

}