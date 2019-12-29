
Component({
  properties: {
    stamp: {
      type: Object,
      value: null,
    }
  },

  data: {

  },

  methods: {
    onStampDetail: function(e) {
      wx.navigateTo({
        url: '/pages/detail/detail?id=' + this.properties.stamp.stid,
      })
    },
  }
})
