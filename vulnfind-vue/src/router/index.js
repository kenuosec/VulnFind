import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/Login'
// import Users from '@/views/user/Users'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    redirect: { name: 'Login' }
  },
  {
    path: '/home',
    name: 'Home',
    component: () => import('../views/Home'),
    meta: {
      requireAuth: true
    },
    children: [
      {
        path: '/welcome',
        name: '欢迎界面',
        component: () => import('../views/Welcome')
      },
      {
        path: '/users',
        name: '用户管理',
        component: () => import('../views/user/Users')
      },
      {
        path: '/masscan',
        name: 'masscan扫描配置',
        component: () => import('../views/scan/port/MasscanConfig')
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
