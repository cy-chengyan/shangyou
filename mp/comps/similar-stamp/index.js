
const request = require("../../utils/request")
const util = require("../../utils/util")

Component({

  properties: {
    stid: {
      type: String,
      value: null,
    }
  },

  data: {
    stamps: [],
    showLoading: false,
  },

  methods: {
    showSimilar: function() {
      if (this.data.stamps.length > 0) {
        return
      }

      this.setData({
        showLoading: true,
      })

      let that = this
      request.querySimilarStamp({
        stid: this.properties.stid,
        size: 2,
        succ: function(res) {
          // console.log(res)
          that.setData({
            stamps: res,
            showLoading: false,
          })
        },
        fail: function(res) {
          util.notify({ msg: "加载失败", type: "ERROR" })
          that.setData({
            showLoading: false,
          })
        },
      })
    }
  }

})
