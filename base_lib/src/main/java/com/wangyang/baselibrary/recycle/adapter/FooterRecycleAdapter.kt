package com.wangyang.baselibrary.recycle.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.wangyang.baselibrary.R
import com.wangyang.baselibrary.recycle.*
import com.wangyang.baselibrary.recycle.holder.BaseRecycleHolder
import com.wangyang.baselibrary.recycle.holder.FooterRecycleHolder
import com.wangyang.baselibrary.recycle.refresh.Footer
import com.wangyang.baselibrary.recycle.refresh.FooterConstant
import com.wangyang.baselibrary.recycle.refresh.Page

class FooterRecycleAdapter(override val context: Context, private val entities: ArrayList<IEntity>, private val typeHandle: ITypeHandle) : BaseRecycleAdapter(context, entities, typeHandle) {

    private val footer = Footer()
    private val page = Page()

    init {
        isNeedNext(entities)
        notifyDataSetChanged()
        page.size = 0
    }

    fun addLoadData(list: ArrayList<IEntity>) {
        entities.remove(footer)
        val startIndex = entities.size
        entities.addAll(list)
        isNeedNext(list)
        notifyItemChanged(startIndex, this.entities.size - startIndex)
    }


    fun setFooterText(text:String) {
        footer.text = text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecycleHolder<IEntity> {
        val itemView = LayoutInflater.from(context).inflate(viewType, parent, false)
        return if (viewType == R.layout.three) {
            FooterRecycleHolder(itemView) as BaseRecycleHolder<IEntity>
        } else {
            typeHandle.createItemViewHolder(viewType, itemView) as BaseRecycleHolder<IEntity>
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (entities[position].itemType == FooterConstant.FOOTER_ITEM_TYPE) {
            return R.layout.three
        }
        return super.getItemViewType(position)
    }

    private fun isNeedNext(list: ArrayList<IEntity>) {
        page.isNeedNext = list.size >= 20
        if (page.isNeedNext) {
            footer.text = "正在加载中..."
        }else {
            footer.text = "我是有尽头的..."
        }
        entities.add(footer)
    }

    fun getPage(): Page {
        return page
    }

}