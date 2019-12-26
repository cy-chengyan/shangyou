
const request = require('../../utils/request')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    stampDetail: null,

    container_base_stamp: null,
    container_sub_stamps: null,    
    container_xiaoben: null,
    container_big_formats: null,
    container_small_formats: null,   
    container_zeng_songs: null, 
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // console.log(options)
    let stampId = options.id
    // console.log(stampId)
    if (!stampId) {
      return
    }

    let that = this
    request.stampDetail({
      stid: stampId,
      succ: function(res) {
        // console.log(res)
        that.setData({
          stampDetail: res,
        }, function() {
          wx.setNavigationBarTitle({
            title: that.data.stampDetail.base_stamp.name,
          })
          /*
          that.setData({
            container_base_stamp: () => wx.createSelectorQuery().select('#base_stamp'),        
            container_sub_stamps: () => wx.createSelectorQuery().select('#sub_stamps'),
            container_xiaoben: () => wx.createSelectorQuery().select('#xiaoben'),
            container_big_formats: () => wx.createSelectorQuery().select('#big_formats'),       
            container_small_formats: () => wx.createSelectorQuery().select('#small_formats'),                   
            container_zeng_songs: () => wx.createSelectorQuery().select('#zeng_songs'),                               
          })
          */
        })
      }
    })
  },

  onPreview: function(e) {
    // console.log(e)
    let current = e.currentTarget.dataset.cur_picture
		wx.previewImage({
		  	current,
		  	urls: this.data.stampDetail.base_stamp.pictures,
		})
  }, 

})