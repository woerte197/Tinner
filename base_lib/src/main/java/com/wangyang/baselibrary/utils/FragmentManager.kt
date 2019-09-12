package com.wangyang.baselibrary.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import java.util.*
import javax.inject.Inject

class FragmentManager @Inject constructor() {

    private val stack = Stack<Fragment>()

    fun addFragment(id: Int, transaction: FragmentTransaction, fragment: Fragment) {
        stack.add(fragment)
        transaction.add(id, fragment)
        transaction.commit()
    }

    fun addFragments(id: Int, transaction: FragmentTransaction, fragments: Stack<Fragment>) {
        stack.addAll(fragments)
        for (fragment in fragments) {
            transaction.add(id, fragment)
        }
        transaction.commit()
    }

    fun changeFragment(transaction: FragmentTransaction, position: Int) {
        for (fragment in stack) {
            transaction.hide(fragment)
        }
        transaction.show(stack[position])
        transaction.commit()
    }


}