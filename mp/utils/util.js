
import Notify from '@vant/weapp/notify/notify'

const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

function removeHtml(content) {
  if (!content)
    return content
  content = content.replace(/\s/ig, '')
  content = content.replace(/<[^>]+>/ig, '')
  return content
}

function checkMobileNumber(mobileNumber){ 
  if(!(/^1[3-9]\d{9}$/.test(mobileNumber))){ 
      return false; 
  }
  return true;
}

function notify({msg, type}) {
  let bg = type == 'ERROR' ? '#D84E43' : '#2BA245'
  Notify({
    message: msg,
    background: bg
  });  
}

module.exports = {
  formatTime,
  formatNumber,
  removeHtml,
  checkMobileNumber,  
  notify,
}
