package com.wangyang.baselibrary.recycle

import android.view.View
import com.wangyang.baselibrary.recycle.holder.BaseRecycleHolder

interface ITypeHandle {
    fun createItemViewHolder(viewType: Int, view: View): BaseRecycleHolder<*>

    fun getViewType(entity: IEntity): Int
}
