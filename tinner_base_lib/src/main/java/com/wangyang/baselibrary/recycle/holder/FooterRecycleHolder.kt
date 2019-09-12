package com.wangyang.baselibrary.recycle.holder

import android.view.View
import android.widget.TextView
import com.wangyang.baselibrary.R
import com.wangyang.baselibrary.recycle.refresh.Footer
import com.wangyang.baselibrary.recycle.adapter.BaseRecycleAdapter

class FooterRecycleHolder(view: View) : BaseRecycleHolder<Footer>(view) {
    override fun setView(entity: Footer, adapter: BaseRecycleAdapter, position: Int) {
        mView.findViewById<TextView>(R.id.mFooterText).text = entity.text
    }

}