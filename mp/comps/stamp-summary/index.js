
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    stampSummary: null,
  },

  /**
   * 组件的初始数据
   */
  data: {
    // pictures: [],
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
  },  

  /**
   * 组件的方法列表
   */
  methods: {
    onStampDetail: function() {
      wx.navigateTo({
        url: '/pages/detail/detail?id=' + this.properties.stampSummary.stid,
      })
    }    
  }
})
