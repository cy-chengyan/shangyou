
# 小程序注意事项

## 分享功能

首页列表中的分享，是放在van-action-sheet组件中的，但是该组件的分享按钮由于不支持传参数，所以改了vant的源代码。

/miniprogram_npm/action-sheet

```html
<button
      wx:for="{{ actions }}"
      wx:key="index"
      open-type="{{ item.openType }}"
      style="{{ item.color ? 'color: ' + item.color : '' }}"
      class="{{ utils.bem('action-sheet__item', { disabled: item.disabled || item.loading }) }} van-hairline--top {{ item.className || '' }}"
      hover-class="van-action-sheet__item--hover"
      data-index="{{ index }}"
      data-context="{{ context }}" <!-- 这个是添加的新属性 -->
      bind:tap="onSelect"
    >
```

相关的js文件:

```js
       safeAreaInsetBottom: {
            type: Boolean,
            value: true
        },
        context: { // 这是新加的
            type: Object,
            value: null
        }
```