
const util = require('../../utils/util')
const MAX_TIMES = 60

Component({
  /**
   * 组件的属性列表
   */
  properties: {
    caption: {
      type: String,
      value: '',
    },
    maxtimes: {
      type: Number,
      value: MAX_TIMES,
    },
    disabled: {
      type: Boolean,
      value: true,
    },
  },

  /**
   * 组件的初始数据
   */
  data: {
    counting: false,
    cur_time: MAX_TIMES,
    staticCaption: null,
  },

  attached() {
    this.setData({
      staticCaption: this.properties.caption,
    })
  },  

  /**
   * 组件的方法列表
   */
  methods: {

    onClick: function() {
      this.triggerEvent('click', {'button': this})
    },

    onStartCount: function() {
      this.setData({
        counting: true,
        cur_time: MAX_TIMES,
        caption: this.data.staticCaption + '(' + util.formatNumber(MAX_TIMES) + ')'
      }, function() {
        let that = this        
        let f = setInterval(function() {
          let cur_time = that.data.cur_time - 1
          that.setData({
            cur_time,
            caption: that.data.staticCaption + '(' + util.formatNumber(cur_time) + ')'                        
          }, function() {
            if (that.data.cur_time <= 0) {
              clearInterval(f)
              that.setData({
                counting: false,
                caption: that.data.staticCaption                       
              })              
            }
          })
        }, 1000); 
      })
    },

    setDisabled: function(disabled) {
      this.setData({
        disabled,
      })
    },

  }
})
