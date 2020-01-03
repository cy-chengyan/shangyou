

const request = require('../../utils/request')
const biz = require('../../utils/biz')

const PAGE_SIZE = 10
const app = getApp()

Page({
  
  data: {
    yearOptions: [],
    typeOptions: [ 
      { text: '所有类型', value: 0 },
      { text: '编年邮票', value: 1 },
      { text: '纪字头邮票', value: 2 },
      { text: '特字头邮票', value: 3 },
      { text: 'J字头邮票', value: 4 },
      { text: 'T字头邮票', value: 5 },
      { text: '普通邮票', value: 6 },      
      { text: '编号邮票', value: 7 },                                          
      { text: '文革邮票', value: 8 },                                          
      { text: '军用邮票', value: 9 },                                          
      { text: '个性化服务专用邮票', value: 10 },                                          
      { text: '贺年专用邮票', value: 11 },                                          
      { text: '贺卡专用邮票', value: 12 },                                          
      { text: '航空邮票', value: 13 },                                          
      { text: '欠资邮票', value: 14 },
      { text: '加字改值邮票', value: 15 },
      { text: '包裹邮票', value: 16 }            
    ],

    stampList: [],
    query: '',
    type: 0,    
    year: 0,
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
    let options = [
      { text: '所有年份', value: 0 }
    ]
    var date = new Date()
    var year = date.getFullYear()  
    for (let i = year; i >= 1949; i--) {
      let opt = { text: i.toString(), value: i }
      options.push(opt)
    }
    this.setData({
      yearOptions: options,      
    })

    let that = this
    if (biz.isLogined()) {
      request.favStampIds({
        succ: function(res) {
          // console.log(res)
          app.globalData.favStamps = new Set(res)
        },
        fail: function() {
          console.error('获取收藏邮票列表失败')
        },
        complete: function() {
          that.requestMoreList({ refresh: true })
        },
      })
    }

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

    let query = this.data.query.trim()
    let type = this.data.type    
    let year = this.data.year
    let offset = this.data.offset

    let that = this
    let func = query.length > 0 ? request.searchStamp : request.stampList
    func({ 
      offset, 
      size: PAGE_SIZE, 
      query,
      type, 
      year, 
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

  onTypeChange: function(e) {
    // console.log(e)
    let that = this
    this.setData({
      type: e.detail
    }, function() {
      that.requestMoreList({ refresh: true })
    })
  }, 
  
  onYearChange: function(e) {
    // console.log(e)
    let that = this
    this.setData({
      year: e.detail
    }, function() {
      that.requestMoreList({ refresh: true })
    })    
  },

  onSearch: function(e) {
    this.data.query = e.detail
    this.requestMoreList({ refresh: true })    
  }, 

})
