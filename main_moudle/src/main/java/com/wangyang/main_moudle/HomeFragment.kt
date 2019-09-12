package com.wangyang.main_moudle

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.router_lib.provider.moudle.home.IHomeProvider
import com.tencent.mm.opensdk.utils.Log
import com.wangyang.baselibrary.ui.fragment.BaseMvpFragment
import com.wangyang.main_moudle.presenter.HomePresenter
import com.wangyang.main_moudle.presenter.view.HomeView
import com.wangyang.tinner_lib.common.TransAnimationListener
import kotlinx.android.synthetic.main.fragment_home.*

@Route(path = IHomeProvider.HOME_FRAGMENT)
class HomeFragment : BaseMvpFragment<HomePresenter>(), HomeView {
    private var mSmallImageViewHeight = 0
    private var mTransY = 0f
    override fun setLayout(): Int {
        return R.layout.fragment_home
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        goods_detail_toolbar.background.alpha = 0



        mScrollView.listener = { alpha: Float, scrollY: Int ->
            goods_detail_toolbar.background.alpha = alpha.toInt()
            Log.e("listener", scrollY.toString())
        }

        mSmallImageView.post {
            mSmallImageViewHeight = mSmallImageView.height
        }

        mScrollView.loadImageViewAnim = {
            mTransY = it
            mSmallImageView.layoutParams.height = ((mSmallImageViewHeight + mTransY).toInt())
            Log.e("loadImageViewAnim", (mSmallImageViewHeight + mTransY).toString())
            mSmallImageView.requestLayout()
        }

        mScrollView.imageViewCancel = {
            mPullText.text = ""
            val objectAnimator: ValueAnimator = ObjectAnimator.ofFloat(mTransY, 0f)
            objectAnimator.duration = 200
            objectAnimator.addUpdateListener {
                Log.e("loadImageViewAnim", (it.animatedValue as Float?).toString())
                mSmallImageView.layoutParams.height = ((mSmallImageViewHeight + it.animatedValue as Float).toInt())
                mSmallImageView.requestLayout()
            }
            objectAnimator.start()
        }

        mScrollView.loadImageView = {
            mImageView.clearAnimation()
            mImageView.animation = createAnimation(R.anim.top_in, mImageView, 500)
            mImageView.visibility = View.VISIBLE
        }

        mScrollView.loadTextView = {
            mPullText.text = it
        }

        mImageView.setOnClickListener {
            mImageView.clearAnimation()
            mImageView.animation = createAnimation(R.anim.top_out, mImageView, 300)
            mImageView.visibility = View.GONE
        }
    }


    private fun createAnimation(anim: Int, view: View, duration: Long): Animation {
        val animation = AnimationUtils.loadAnimation(activity, anim)
        animation.duration = duration
        animation.setAnimationListener(object : TransAnimationListener {
            override fun onAnimationEnd(animation: Animation?) {
                view.isEnabled = true
            }

            override fun onAnimationStart(animation: Animation?) {
                view.isEnabled = false
            }

        })
        return animation
    }
}