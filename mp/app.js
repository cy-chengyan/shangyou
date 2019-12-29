
const CONST = require('./utils/const')

App({

  onLaunch: function () {
    // 从本地缓存中获取登录和用户信息
    let v = wx.getStorageSync('loginInfo')
    if (v) {
      this.globalData.loginInfo = v
    }
    v = wx.getStorageSync('userInfo')
    if (v) {
      this.globalData.userInfo = v
    }
  },

  globalData: {
    userInfo: null,
    loginInfo: {
      uid: CONST.UNKNOWN_UID,
      login_at: null,
      token: null,
    },
  },

})
