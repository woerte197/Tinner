package com.wangyang.tinner_lib.widget

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.wangyang.tinner_lib.widget.BaseGridHolder as A

/**
 * Created by Anyway on 2016/2/19.
 */
abstract class DragGridAdapter<T>(private var list: MutableList<T>) :
    BaseAdapter() {

    private var isMove = false

    private var movePosition = -1


    fun getList(): MutableList<T> {
        return list
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): T {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        Log.i(TAG, "-------------------------------")
        for (t in list) {
            Log.i(TAG, t.toString())
        }
        val view = getItemView(position, convertView, parent)
        if (position == movePosition && isMove) {
            view?.visibility = View.INVISIBLE
        }
        return view
    }


    protected abstract fun getItemView(position: Int, convertView: View?, parent: ViewGroup?): View

    /**
     * 给item交换位置
     *
     * @param originalPosition item原先位置
     * @param nowPosition      item现在位置
     */
    fun exchangePosition(originalPosition: Int, nowPosition: Int, isMove: Boolean) {
        val t = list[originalPosition]
        list.removeAt(originalPosition)
        list.add(nowPosition, t)
        movePosition = nowPosition
        this.isMove = isMove
        notifyDataSetChanged()
    }

    companion object {

        private val TAG = "DragGridAdapter"
    }


}