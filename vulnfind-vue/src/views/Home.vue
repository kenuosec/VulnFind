<template>
  <el-container class="main_container">
    <!--头部布局-->
    <el-header>

      <!--logo和项目名称-->
      <div class="left_box">
        <img src="../assets/img/timg.gif">
        <span>漏洞发现平台</span>
      </div>

      <!--用户登录展示的头像-->
      <div class="right_box">
        <el-dropdown>
          <div class="block">
            <el-avatar :size="50" src="">{{ user.username }}</el-avatar>
          </div>

          <!--下拉菜单-->
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item icon="el-icon-house">
              <span >首页</span>
            </el-dropdown-item>
            <el-dropdown-item icon="el-icon-switch-button" >
              <span @click="logout">退出登录</span>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>
    <el-container>

      <!--侧边栏-->
      <el-aside :width="isCollapse?'64px':'200px'">
        <!--展开/收起-->
        <div class="toggle_box" @click="toggleCollapse">|||</div>
        <el-menu
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            background-color="#001529"
            text-color="#fff"
            active-text-color="#ffd04b"
            :collapse="isCollapse"
            :default-active="activePath"
            :collapse-transition="false"
            :unique-opened="true"
            :router="true">
          <MenuTree :menuList="this.MenuList"></MenuTree>
        </el-menu>
      </el-aside>
      <el-main>
        <!--路由视图-->
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import MenuTree from '../components/MenuTree'

export default {
  name: 'Main',
  props: {
    href: String
  },
  data () {
    return {
      user: {
      },
      // 默认不收起菜单栏
      isCollapse: false,
      activePath: '',
      MenuList: [
        // 系统管理(用户管理)
        {
          id: 1,
          parentId: 0,
          menuName: '系统管理',
          url: '',
          icon: 'el-icon-setting',
          orderNum: 1,
          open: 1,
          disabled: false,
          perms: null,
          type: 0,
          children: [
            {
              id: 11,
              parentId: 1,
              menuName: '用户管理',
              url: '/Users',
              icon: 'el-icon-user',
              orderNum: 2,
              open: 0,
              disabled: false,
              perms: 'users',
              type: 0,
              children: []
            }
          ]
        },
        // 扫描配置(端口配置)
        {
          id: 2,
          parentId: 0,
          menuName: '扫描配置',
          url: null,
          icon: 'el-icon-s-goods',
          orderNum: 2,
          open: 0,
          disabled: false,
          perms: null,
          type: 0,
          children: [
            {
              id: 21,
              parentId: 312,
              menuName: '端口配置',
              url: null,
              icon: 'el-icon-edit',
              orderNum: 3,
              open: 0,
              disabled: false,
              perms: null,
              type: 0,
              children: [
                {
                  id: 211,
                  parentId: 311,
                  menuName: 'masscan',
                  url: '/masscan',
                  icon: 'el-icon-edit',
                  orderNum: 1,
                  open: 0,
                  disabled: false,
                  perms: '',
                  type: 0,
                  children: []
                },
                {
                  id: 212,
                  parentId: 311,
                  menuName: 'nmap',
                  url: '/namp',
                  icon: 'el-icon-coordinate',
                  orderNum: 5,
                  open: 0,
                  disabled: false,
                  perms: '',
                  type: 0,
                  children: []
                }
              ]
            },
            {
              id: 22,
              parentId: 312,
              menuName: '子域名扫描配置',
              url: '',
              icon: 'el-icon-date',
              orderNum: 1,
              open: 1,
              disabled: false,
              perms: 'el-icon-date',
              type: 0,
              children: [
                {
                  id: 221,
                  parentId: 229,
                  menuName: 'oneforall',
                  url: '/oneforall',
                  icon: 'el-icon-date',
                  orderNum: 1,
                  open: 1,
                  disabled: false,
                  perms: 'el-icon-date',
                  type: 0,
                  children: []
                },
                {
                  id: 222,
                  parentId: 229,
                  menuName: 'subDomains',
                  url: '/subDomainsBrute',
                  icon: 'el-icon-goods',
                  orderNum: 5,
                  open: 1,
                  disabled: false,
                  perms: '',
                  type: 0,
                  children: []
                }
              ]
            },
            {
              id: 9,
              parentId: 312,
              menuName: '目录扫描配置',
              url: '',
              icon: 'el-icon-date',
              orderNum: 1,
              open: 1,
              disabled: false,
              perms: 'el-icon-date',
              type: 0,
              children: [
                {
                  id: 230,
                  parentId: 229,
                  menuName: 'oneforall',
                  url: '/oneforall',
                  icon: 'el-icon-date',
                  orderNum: 1,
                  open: 1,
                  disabled: false,
                  perms: 'el-icon-date',
                  type: 0,
                  children: []
                },
                {
                  id: 270,
                  parentId: 229,
                  menuName: 'subDomains',
                  url: '/subDomainsBrute',
                  icon: 'el-icon-goods',
                  orderNum: 5,
                  open: 1,
                  disabled: false,
                  perms: '',
                  type: 0,
                  children: []
                }
              ]
            }
          ]
        },
        // 扫描管理(端口管理/子域名管理/URL管理/漏洞管理)
        {
          id: 3,
          parentId: 0,
          menuName: '扫描管理',
          url: '',
          icon: 'el-icon-platform-eleme',
          orderNum: 3,
          open: 0,
          disabled: false,
          perms: '',
          type: 0,
          children: [
            {
              id: 31,
              parentId: 303,
              menuName: '端口管理',
              url: '/map',
              icon: 'el-icon-s-opportunity',
              orderNum: 1,
              open: 1,
              disabled: false,
              perms: 'map:view',
              type: 0,
              children: []
            },
            {
              id: 32,
              parentId: 303,
              menuName: '子域名管理',
              url: '/health',
              icon: 'el-icon-s-cooperation',
              orderNum: 1,
              open: 0,
              disabled: false,
              perms: '',
              type: 0,
              children: []
            },
            {
              id: 33,
              parentId: 303,
              menuName: 'URL管理',
              url: null,
              icon: 'el-icon-c-scale-to-original',
              orderNum: 2,
              open: 1,
              disabled: false,
              perms: null,
              type: 0,
              children: []
            },
            {
              id: 34,
              parentId: 303,
              menuName: '漏洞管理',
              url: '/rumors',
              icon: 'el-icon-help',
              orderNum: 5,
              open: 0,
              disabled: false,
              perms: null,
              type: 0,
              children: []
            }
          ]
        },
        // 日志管理(登入日志/操作日志)
        {
          id: 4,
          parentId: 0,
          menuName: '日志管理',
          url: '/logs',
          icon: 'el-icon-camera',
          orderNum: 6,
          open: 0,
          disabled: false,
          perms: null,
          type: 0,
          children: [
            {
              id: 41,
              parentId: 5,
              menuName: '登入日志',
              url: '/loginLog',
              icon: 'el-icon-date',
              orderNum: 1,
              open: 0,
              disabled: false,
              perms: 'login:log',
              type: 0,
              children: []
            },
            {
              id: 42,
              parentId: 5,
              menuName: '操作日志',
              url: '/logs',
              icon: 'el-icon-edit',
              orderNum: 1,
              open: 1,
              disabled: false,
              perms: '',
              type: 0,
              children: []
            }
          ]
        }
      ]
    }
  },
  components: {
    MenuTree
  },
  methods: {
    toggleCollapse () {
      this.isCollapse = !this.isCollapse
    },
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPath) {
      console.log(key, keyPath)
    },
    logout () {
      const _this = this
      _this.$axios.get('/logout', {
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res => {
        _this.$store.commit('REMOVE_INFO')
        _this.$router.push('/login')
      })
    }
  },
  created () {
    if (this.$store.getters.getUser.username) {
      this.user.username = this.$store.getters.getUser.username
      this.user.avatar = this.$store.getters.getUser.avatar
      // this.hasLogin = true
    }
  }
}
</script>

<style lang="less" scoped>
/*设置整个容器的高度*/
.main_container {
  height: 100%;
}
/*头部布局*/
.el-header {
  background-color: #001529;

  display: flex;
  justify-content: space-between;
  padding-left: 0;
  color: #FFFFFF;
  align-items: center;
  font-size: 20px;
  /*左边logo和标题*/
  .left_box {
    //color: #001529;
    font-size: 30px;
    display: flex;
    align-items: center;
    img {
      width: 60px;
      height: 60px;
      margin: 0px 0px 10px 15px;
    }
    /*标题离logo间隔*/
    span {
      margin-left: 30px;
    }
  }
  /*右边的登录头像*/
  .right_box {
    .el-dropdown > img {
      width: 60px;
      height: 60px;
      border-radius: 50%;
      background-color: #FFFFFF;
      margin: 0px 15px 0px 0px;
      background-size: contain;
    }
  }
}
/*侧边栏*/
.el-aside {
  background-color: #001529;
  .el-menu {
    border-right: none;
  }
  /*展开/收起*/
  .toggle_box {
    background-color: #252822;
    font-size: 15px;
    font-weight: bold;
    line-height: 24px;
    color: #FFFFFF;
    letter-spacing: 0.2em;
    text-align: center;
    cursor: pointer;
  }
}
/*内容主体*/
.el-main {
  background-color: #E9EEF3;
}
/*下拉菜单的样式*/
.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}
</style>
