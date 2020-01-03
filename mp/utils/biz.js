
const CONST = require('./const')

const app = getApp()

function isLogined() {
  let loginInfo = app.globalData.loginInfo
  if (!loginInfo || loginInfo.uid == CONST.UNKNOWN_UID) {
    return false
  }
  return true
}

function avatar() {
  if (app.globalData.userInfo && app.globalData.userInfo.avatar) {
    return app.globalData.userInfo.avatar    
  }
  return '/assets/guest.png'
}

function nickname() {
  if (app.globalData.userInfo && app.globalData.userInfo.nickname) {
    return app.globalData.userInfo.nickname    
  }
  return '未设置用户名'
}

function isFav(stid) {
  return app.globalData.favStamps.has(stid) ? 1 : 2
}

function setFav({stid, status}) {
  if (status == 1) {
    app.globalData.favStamps.add(stid)    
  } else {
    app.globalData.favStamps.delete(stid)
  }
}


module.exports = {
  isLogined,
  avatar,
  nickname,
  isFav,
  setFav,  
}
