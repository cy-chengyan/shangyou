
const util = require("../../utils/util")
const request = require("../../utils/request")
const biz = require("../../utils/biz")

Component({
  /**
   * 组件的属性列表
   */
  properties: {
    stampSummary: {
      type: Object,
      value: null,
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    // pictures: [],
    unfaved_actions: [
      {
        name: '相关邮票',
        value: 1,
      },      
      {
        name: '收藏',
        value: 2,
      },
      {
        name: '分享',
        value: 3,
        openType: 'share'  
      }
    ],
    faved_actions: [
      {
        name: '相关邮票',
        value: 1,
      },      
      {
        name: '取消收藏',
        value: 2,
      },
      {
        name: '分享',
        value: 3,
        openType: 'share'  
      }
    ],    
    showActions: false,
    showSimilar: false, 
    viewSimilarStamp: null,
    context: null,
  },

  view: {
    similarStamp: null,
  },

  attached: function () {
    // console.log(this.properties.stampSummary)
    /*
    if (this.properties.stampSummary.pictures) {
      let pictures = []
      let p = this.properties.stampSummary.pictures
      for (let i = 0; i < 3 && i < p.length; i++) {
        pictures.push(p[i])
      }
      this.setData({
        pictures,
      })
      // this.data.pictures = pictures
    }
    */
    
    this.data.viewSimilarStamp = this.selectComponent('#similar-stamp')
    let stampSummary = this.properties.stampSummary    
    this.setData({
      context: { stid: stampSummary.stid, cover: stampSummary.pictures[0] }
    })
  },  

  /**
   * 组件的方法列表
   */
  methods: {
    onStampDetail: function() {
      wx.navigateTo({
        url: '/pages/detail/detail?id=' + this.properties.stampSummary.stid,
      })
    },

    onMore: function() {
      this.setData({
        showActions: true,
      })
    },

    onActionClose: function(e) {
      // console.log(e)
      this.setData({
        showActions: false,
      })
    }, 

    onActionSelect: function(e) {
      // console.log(e)
      this.setData({
        showActions: false,
      })

      let v = e.detail.value
      if (v == 1) {
        this.setData({
          showSimilar: true,          
        })
        this.data.viewSimilarStamp.showSimilar()
      } else if (v == 2) {
        let param = "stampSummary.fav"
        let v = this.properties.stampSummary.fav == 1 ? 2 : 1
        let msg = v == 1 ? '收藏' : '取消收藏'
        let that = this
        request.favAddOrDel({
          stid: this.properties.stampSummary.stid,
          status: v,
          succ: function() {
            util.notify({ msg: msg + '成功' })
            biz.setFav({ stid: that.properties.stampSummary.stid, status: v })
            that.setData({
              [param]: v
            })
          },
          fail: function(res) {
            console.log(res)
            let data = res.data
            let code = data.code
            if (code === -12) {
              msg = msg + '失败，请先登录/注册'
            } else {
              msg = msg + '失败'
            }
            util.notify({ msg, type: 'ERROR' })
          },
        })
      }
    },

    onHideSimilar: function(e) {
      this.setData({
        showSimilar: false,          
      })
    },   
  }
})
