
const util = require('../../utils/util')
const biz = require('../../utils/biz')
const request = require('../../utils/request')

const app = getApp()

Page({
  
  data: {
    isLogin: false,
    mobileNumber: '',
    checkCode: '',
    canSubmit: false,    
    isSubmitting: false,

    avatar: biz.avatar(),
    nickname: biz.nickname(),
  },

  view: {
    countButton: null,
  },

  onLoad: function () {
    let isLogin = biz.isLogined()
    this.setData({
      isLogin,
    })
    if (!isLogin) {
      wx.setNavigationBarTitle({
        title: '还未登录/注册',
      })
      this.view.countButton = this.selectComponent('#count-button')
    }
  },

  onInputMobileNumber: function(e) {
    // console.log(e)
    this.data.mobileNumber = e.detail
    let countButton = this.view.countButton
    if (countButton) {
      countButton.setDisabled(!util.checkMobileNumber(this.data.mobileNumber))
    }
    this.calcCanSubbmit()    
  },

  onInputCheckCode: function(e) {
    // console.log(e)
    this.data.checkCode = e.detail
    this.calcCanSubbmit()    
  },
  
  onSendCheckCode: function(e) {
    // console.log(e)
    let countButton = e.detail.button
    let mobileNumber = this.data.mobileNumber
    if (!util.checkMobileNumber(mobileNumber)) {
      util.notify({msg: '手机号不正确', type: 'ERROR'})
      return
    }

    countButton.setDisabled(true)
    request.sendCheckCode({
      mobileNumber,
      succ: function() {
        util.notify({msg: '验证码发送成功，请注意查收'})
        countButton.onStartCount()
      },
      fail: function() {
        util.notify({msg: '验证码发送失败，请秒候重试', type: 'ERROR'})
        countButton.setDisabled(false)        
      }
    })
  },

  calcCanSubbmit: function() {
    let mobileNumber = this.data.mobileNumber
    let checkCode = this.data.checkCode
    this.setData({
      canSubmit: util.checkMobileNumber(mobileNumber) && checkCode.length == 4,
    })
  },

  onSubmit: function() {
    this.setData({
      isSubmitting: true,
    }, function() {
      let that = this
      let mobileNumber = this.data.mobileNumber
      let checkCode = this.data.checkCode
      request.regAndLogin({
        mobileNumber,
        checkCode,
        succ: function(res) {
          // console.log(res)
          util.notify({msg: '登录成功'})
          app.globalData.loginInfo = res.login_info
          app.globalData.userInfo = res.user
          wx.setStorageSync('loginInfo', app.globalData.loginInfo)
          wx.setStorageSync('userInfo', app.globalData.userInfo)

          that.setData({
            isLogin: true,
            avatar: biz.avatar(),
            nickname: biz.nickname(),
          })          
        },
        fail: function(res) {
          // console.log(res)
          let msg = res.data.msg
          if (!msg) {
            msg = '登录失败'
          }
          util.notify({msg, type: 'ERROR'})
        },
        complete: function(res) {
          that.setData({
            isSubmitting: false,
          })          
        }        
      })
    })
  }, 

  onSyncWxInfo: function(e) {
    // console.log(e)
    const detail = e['detail']
    const err = detail['errMsg']
    if (err.indexOf('ok') == -1) {
      return
    }

    console.log(detail['userInfo'])
    const u = detail['userInfo']
    let nickname = u.nickName
    let avatar = u.avatarUrl
    let gender = u.gender

    let that = this
    request.updateUserInfo({
      nickname,
      avatar,
      gender,
      succ: function(e) {
        that.setData({
          nickname,          
          avatar,
        })

        app.globalData.userInfo.nickname = nickname
        app.globalData.userInfo.avatar = avatar
        wx.setStorageSync('userInfo', app.globalData.userInfo)

        util.notify({msg: '更新成功'})
      },
      fail: function(e) {
        util.notify({msg: '更新失败', type: 'ERROR'})
      },
    })

  },

})
