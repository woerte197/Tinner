package com.wangyang.tinner_lib.widget

import android.view.View
import android.view.ViewGroup
import android.content.Context
import android.widget.TextView
import android.view.LayoutInflater
import com.wangyang.tinner_lib.R


class TestAdapter(var context: Context, list: MutableList<String>) : DragGridAdapter<String>(list) {
    override fun getItemView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val text = getList()[position]
        var convertView = LayoutInflater.from(context).inflate(R.layout.item_drag, null)
        val tv_text = convertView.findViewById(R.id.item) as TextView
        tv_text.text = text
        return convertView
    }



}
