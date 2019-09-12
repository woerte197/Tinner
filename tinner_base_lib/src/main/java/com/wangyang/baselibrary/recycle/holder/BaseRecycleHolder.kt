package com.wangyang.baselibrary.recycle.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.wangyang.baselibrary.recycle.IEntity
import com.wangyang.baselibrary.recycle.adapter.BaseRecycleAdapter

abstract class BaseRecycleHolder<T: IEntity>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var mContext: Context = itemView.context
    var mView = itemView
    abstract fun setView(entity: T, adapter: BaseRecycleAdapter, position: Int)

}
