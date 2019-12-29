
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
    showActions: false,    
    actions: [
      {
        name: '相关邮票',
        value: 1,
      },      
      {
        name: '收藏(暂未实现)',
        value: 2,
      },
      {
        name: '分享',
        value: 3,
        openType: 'share'  
      }
    ],

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
      }
    },

    onHideSimilar: function(e) {
      this.setData({
        showSimilar: false,          
      })
    },   
  }
})
