
const request = require('../../utils/request')

const PAGE_SIZE = 10

Page({
  
  data: {
    stampList: [],
    query: '',
    offset: 0,
    hasMore: 1,
  },

  onShareAppMessage: function (ops) {
    console.log(ops)
    let from = ops.from
    if (from == 'button') {
      let stid = ops.target.dataset.context.stid
      let cover = ops.target.dataset.context.cover
      return {
        title: '我在《赏邮》发现一套邮票，推荐给你',
        path: 'pages/index/index?lp=detail&stid=' + stid,  
        imageUrl: cover,              
      }
    }

    return {
      title: '赏邮 - 方寸之间, 大千世界',
      path: 'pages/index/index',
    }      
  },

  onLoad: function (opt) {
    this.requestMoreList({ refresh: true })

    // console.log(opt)
    if (opt && opt.lp) {
      let lp = opt.lp
      if (lp == 'detail') {
        let stid = opt.stid
        wx.navigateTo({
          url: '/pages/detail/detail?id=' + stid,
        })
      }
    }
  },

  requestMoreList: function({ refresh }) {
    wx.showNavigationBarLoading()    

    if (refresh) {
      this.data.offset = 0
      this.data.hasMore = 1
    }

    if (!this.data.hasMore) {
      return
    }

    let offset = this.data.offset

    let that = this
    request.favList({ 
      offset, 
      size: PAGE_SIZE, 
      succ: function(res) {
        // console.log(res)

        let newPageData = res
        if (!newPageData) {
          newPageData = []       
        }

        if (refresh) {
          that.setData({
            stampList: newPageData,
            offset: newPageData.length,
            // hasMore: newPageData.length >= PAGE_SIZE, 
            hasMore: newPageData.length > 0,
          }, function () {
            wx.pageScrollTo({
              scrollTop: 0,
              duration: 0,
            })
          })
        } else {
          let stampList = that.data.stampList.concat(newPageData)
          let offset = that.data.offset + newPageData.length
          that.setData({
            stampList,
            offset,
            // hasMore: newPageData.length >= PAGE_SIZE,
            hasMore: newPageData.length > 0,            
          })
        }
      },
      complete: function(res) {
        if (refresh) {
          wx.stopPullDownRefresh()
        }
        wx.hideNavigationBarLoading()
      },
    })
  },

  onReachBottom: function () {
    this.requestMoreList({ refresh: false })
  },  

  onPullDownRefresh: function () {
    this.requestMoreList({ refresh: true })
  },  

})
