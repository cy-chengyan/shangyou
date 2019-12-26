
const CONST = require('./utils/const')

App({

  onLaunch: function () {
  
  },

  globalData: {
    userInfo: null,
    loginInfo: {
      uid: CONST.UNKNOWN_UID,
      login_at: null,
      token: null,
    }    
  },

})
