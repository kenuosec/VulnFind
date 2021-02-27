import axios from 'axios'
import Element from "element-ui";
import store from "@/store";
import router from "@/router";

axios.defaults.baseURL = 'http://localhost:8888'
// 前置拦截
axios.interceptors.request.use(config => {
    return config
})
// 后置拦截
axios.interceptors.response.use(response => {
        const res = response.data
        if (res.code === 200) {
            return response
        } else {
            Element.Message.error('登陆失败', { duration: 3 * 1000 })
            return Promise.reject(response.data.msg)
        }
    },
    error => {
        if (error.response.data) {
            error.message = error.response.data.msg
        }
        if (error.response.status === 401) {
            store.commit('REMOVE_INFO')
            router.push('/login')
        }
        Element.Message.error(error.message, { duration: 3 * 1000 })
        return Promise.reject(error)
    }
)
