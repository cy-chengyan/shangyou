

const request = require('../../utils/request')

const PAGE_SIZE = 10

Page({
  
  data: {
    yearOptions: [
      { text: '所有年份', value: 0 },
      { text: '1980', value: 1980 },      
      { text: '1981', value: 1981 },
      { text: '1982', value: 1982 },
      { text: '1983', value: 1983 },
      { text: '1984', value: 1984 },      
      { text: '1985', value: 1985 },
      { text: '1986', value: 1986 },      
      { text: '1987', value: 1987 },                      
      { text: '2018', value: 2018 },                         
      { text: '2019', value: 2019 }      
    ],
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
    type: 0,    
    year: 0,
    offset: 0,
    hasMore: 1,
  },

  onLoad: function () {
    this.requestMoreList({ refresh: true })
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

    let type = this.data.type    
    let year = this.data.year
    let offset = this.data.offset

    let that = this
    request.stampList({ 
      offset, 
      size: PAGE_SIZE, 
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
            hasMore: newPageData.length >= PAGE_SIZE, 
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
            hasMore: newPageData.length >= PAGE_SIZE,
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

})
