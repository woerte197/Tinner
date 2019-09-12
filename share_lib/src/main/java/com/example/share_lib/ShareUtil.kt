package com.example.share_lib

import android.content.Context
import cn.sharesdk.facebook.Facebook
import cn.sharesdk.framework.Platform
import cn.sharesdk.framework.PlatformActionListener
import cn.sharesdk.framework.ShareSDK
import cn.sharesdk.onekeyshare.OnekeyShare
import cn.sharesdk.sina.weibo.SinaWeibo
import cn.sharesdk.tencent.qq.QQ
import cn.sharesdk.wechat.friends.Wechat
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONException
import org.json.JSONObject
import java.util.*


object ShareUtil {
    private lateinit var shareListener: ShareListener

    fun setListener(listener: ShareListener) {
        shareListener = listener
    }

    fun showShare(context: Context) {
        val oks = OnekeyShare()
        //关闭sso授权
        oks.disableSSOWhenAuthorize()
        //title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("分享")
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn")
        // text是分享文本，所有平台都需要这个字段
        oks.text = "我是分享文本"
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg")//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn")
        // comment是我对这条分享的评论，仅在人人网使用
        oks.setComment("我是测试评论文本")
        // 启动分享GUI
        oks.show(context)
    }

    fun showShare(platform: String?, context: Context) {
        val oks = OnekeyShare()
        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
        if (platform != null) {
            oks.setPlatform(platform)
            val platform = ShareSDK.getPlatform(platform)
            platform.platformActionListener = platformActionListener
        }
        //关闭sso授权
        oks.disableSSOWhenAuthorize()
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("标题")
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn")
        // text是分享文本，所有平台都需要这个字段
        oks.text = "我是分享文本"
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg")
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn")
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本")
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK")
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn")

        //启动分享
        oks.show(context)
    }

    private val platformActionListener = object : PlatformActionListener {
        override fun onComplete(p0: Platform?, p1: Int, p2: HashMap<String, Any>?) {
            doAsync {
                val platformName = platformName(p0)
                uiThread {
                    shareListener.onComplete("$platformName 分享成功")
                }
            }
        }

        override fun onCancel(p0: Platform?, p1: Int) {
            doAsync {
                val platformName = platformName(p0)
                uiThread {
                    shareListener.onCancel("$platformName 分享取消")
                }
            }
        }

        override fun onError(p0: Platform?, p1: Int, p2: Throwable?) {
            doAsync {
                val platformName = platformName(p0)
                uiThread {
                    shareListener.onError("$platformName 分享失败")
                }
            }
        }
    }

    fun shareWeibo(text: String, lcCreateAt: String, lcDisplayName: String?, imageUrl: String, imageWidth: Int, imageHeight: Int, lcSummary: String, lcUrl: String, lcObjectType: String) {
        val weibo = ShareSDK.getPlatform(SinaWeibo.NAME)
        weibo.platformActionListener = platformActionListener
        val jsonObject = JSONObject()
        try {
            jsonObject.put("url", imageUrl)
            jsonObject.put("width", imageWidth)
            jsonObject.put("height", imageHeight)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val sp = Platform.ShareParams()
        sp.text = text
        sp.lcCreateAt = lcCreateAt
        sp.lcDisplayName = lcDisplayName
        sp.lcImage = jsonObject
        sp.lcSummary = lcSummary
        sp.lcUrl = lcUrl
        sp.lcObjectType = lcObjectType
        weibo.share(sp)
    }
     fun shareSina() {
        val jsonObject = JSONObject()
        try {
            jsonObject.put("url", "http://wx4.sinaimg.cn/large/006WfoFPly1fq0jo9svnaj30dw0dwdhv.jpg")
            jsonObject.put("width", 120)
            jsonObject.put("height", 120)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val platform = ShareSDK.getPlatform(SinaWeibo.NAME)
        val sp = Platform.ShareParams()
        sp.text = "第一次测试"
        sp.lcCreateAt = "2019-01-24"
        sp.lcDisplayName = "displayName测试"
        sp.lcImage = jsonObject
        sp.lcSummary = "Summary测试"
        sp.lcUrl = "http://www.mob.com/"
        sp.lcObjectType = "webpage"

        platform.platformActionListener = platformActionListener
        platform.share(sp)
    }



    private fun platformName(p0: Platform?): String? {
        return when (p0!!.name) {
            SinaWeibo.NAME -> {
                "新浪微博"
            }
            QQ.NAME -> {
                "腾讯QQ"
            }
            Wechat.NAME -> {
                "微信"
            }
            Facebook.NAME -> {
                "Facebook"
            }
            else -> {
                "Other Platform"
            }
        }
    }



}

