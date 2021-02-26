import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/Login'
import Users from '@/views/system/Users'

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
        path: '/users',
        name: '用户管理',
        component: Users
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
