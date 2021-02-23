import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Element from 'element-ui'
import "./permission"

// 全局样式
import './assets/css/global.css'

Vue.use(Element)

Vue.config.productionTip = false
import axios from 'axios'

axios.defaults.baseURL = "http://localhost:8888"

// 前置拦截
axios.interceptors.request.use(config => {
  return config
})
// 后置拦截
axios.interceptors.response.use(response => {
  let res = response.data;

  console.log("======后置拦截====")
  console.log(res)
  console.log("=================")

  if (res.code === 200) {
    return response
    Element.Message.success('登陆成功', { duration: 3 * 1000 })
  } else {
    Element.Message.error('登陆失败', { duration: 3 * 1000 })
    return Promise.reject(response.data.msg)
  }
},
error => {
  console.log(error)
  if(error.response.data) {
    error.message = error.response.data.msg
  }

  if(error.response.status === 401) {
    store.commit("REMOVE_INFO")
    router.push("/login")
  }

  Element.Message.error(error.message, {duration: 3 * 1000})
  return Promise.reject(error)
}
)

Vue.prototype.$axios = axios

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
