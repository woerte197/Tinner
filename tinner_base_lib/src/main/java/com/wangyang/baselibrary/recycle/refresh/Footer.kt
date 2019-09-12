package com.wangyang.baselibrary.recycle.refresh

import com.wangyang.baselibrary.recycle.IEntity

data class Footer(override val itemType: Int = FooterConstant.FOOTER_ITEM_TYPE, var isShow:Boolean = true, var text:String = "正在加载中") : IEntity {}