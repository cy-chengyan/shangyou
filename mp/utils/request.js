
const CONST = require('./const')
const util = require('./util')

///////////////
// private
///////////////
function httpRequestCallBack(res, cbFunc) {
  if (typeof cbFunc === 'function') {
    let data = res.data
    if (typeof data === 'string') {
      try {
        res.data = JSON.parse(data)
      } catch (e) {
        res.data = { code: CONST.UNKNOWN_ERR, msg: CONST.DEFAULT_REQ_FAILED_MSG }
      }
    }
    cbFunc(res)
  }
}

/**
 * 检查服务器返回的业务结果是否正确
 */
function isBizSucc(res) {
  let data = res.data
  if (!data || data.code !== CONST.BIZ_OK) {
    return false
  }
  return true
}

/**
 * 默认错误处理函数, 如果msg传空，则什么都不显示
 */
function defaultProcFail({ msg }) {
  wx.hideLoading()  
  wx.hideNavigationBarLoading()
  if (!msg || msg === '')
    return

  wx.showToast({
    icon: 'none',
    title: msg,
    mask: true,
  })
}

/**
 * 业务请求(POST)，如果请求业务成功，则succ会被调用，直接给succ传的是data域; 否则fail会被调用
 * @param customErrMsg 自定义错误提示，出错时提示串
 */
function bizPostRequest({ path, data, succ, fail, complete, customErrMsg }) {
  const app = getApp()
  const fdata = {
    data,
    login: app.globalData.loginInfo,
  }
  // console.debug(path)
  // console.debug(fdata)

  wx.request({
    url: path,
    data: fdata,
    method: 'POST',
    header: { 'Content-Type': 'application/json' },
    success: (res) => {
      httpRequestCallBack(res, function(res) {
        if (!isBizSucc(res)) { // 虽然http成功，但业务结果不成功，不算成功
          if (fail) {
            fail(res)
          } else {
            defaultProcFail({ msg: customErrMsg })
          }
        } else {
          if (succ) {
            succ(res.data.data)
          }
        }
      })
    },
    fail: function (res) {
      httpRequestCallBack(res, function(res) {
        if (fail) {
          fail(res.data)
        } else {
          defaultProcFail({ msg: customErrMsg })
        }
      })
    },
    complete: function (res) {
      // console.debug(res)
      if (complete) {
        complete(res.data)
      }
    }
  })
}

/**
 * 业务请求(GET)，如果请求业务成功，则succ会被调用，直接给succ传的是data域; 否则fail会被调用
 * @param customErrMsg 自定义错误提示，出错时提示串
 */
function bizGetRequest({ path, succ, fail, complete, customErrMsg }) {
  // console.debug(path)

  wx.request({
    url: path,
    method: 'GET',
    header: { 'Content-Type': 'application/json' },
    success: (res) => {
      httpRequestCallBack(res, function(res) {
        if (!isBizSucc(res)) { // 虽然http成功，但业务结果不成功，不算成功
          if (fail) {
            fail(res)
          } else {
            defaultProcFail({ msg: customErrMsg })
          }
        } else {
          if (succ) {
            succ(res.data.data)
          }
        }
      })
    },
    fail: function (res) {
      httpRequestCallBack(res, function(res) {
        if (fail) {
          fail(res.data)
        } else {
          defaultProcFail({ msg: customErrMsg })
        }
      })
    },
    complete: function (res) {
      // console.debug(res)
      if (complete) {
        complete(res.data)
      }
    }
  })
}


///////////////
// public
///////////////
function stampList({ offset, size, type, year, succ, complete }) {
  bizPostRequest({
    path: CONST.API_ROOT_URL_V1 + '/stamp/list',
    data: {
      offset,
      size,
      type,
      year,
    },
    succ: function(res) {
      // console.log(res)
      if (succ) {
        if (res) {
          for (let i = 0; i < res.length; i++) {
            let stamp = res[i]
            stamp.background = util.removeHtml(stamp.background)
          }
        }
        succ(res)
      }
    },
    complete,
  })
}

function stampDetail({ stid, succ, complete }) {
  bizGetRequest({
    path: CONST.API_ROOT_URL_V1 + '/stamp/detail/' + stid,
    succ,
    complete,
  })
}


module.exports = {
  stampList,
  stampDetail,  
}
