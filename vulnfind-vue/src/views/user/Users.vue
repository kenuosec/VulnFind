<template>
  <div>
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card class="box-card" >
      <el-form :inline="true" :model="userVO" class="demo-form-inline">
        <el-form-item label="角色" lable-with="70px">
        <el-select v-model="userVO.departmentId" placeholder="请选择">
          <el-option
              v-for="item in departments"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            <span style="float: left">{{ item.name }}</span>
            <span style="float: right; color: #8492a6; font-size: 13px">
              <span class="el-tag el-tag--success el-tag--mini el-tag--plain">{{ item.deptCount }}</span>
            </span>
          </el-option>
        </el-select>
        </el-form-item>
        <el-form-item label="用户名" lable-with="200px">
          <el-input v-model="userVO.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" lable-with="200px">
          <el-input clearable v-model="userVO.email" placeholder="请输入邮箱"></el-input>
        </el-form-item>
        <el-form-item style="margin-left: 500px">
          <el-button type="primary" icon="el-icon-search" @click="getUserList">查询</el-button>

          <el-button type="success" icon="el-icon-plus" @click="show">添加</el-button>

          <el-button icon="el-icon-refresh" @click="resetUserVO">重置</el-button>

        </el-form-item>
      </el-form>
      <el-table
          :data="userlist"
          border
          max-height="800"
          style="width: 100%;">
        <el-table-column
            sortable
            prop="id"
            label="id"
            width="100">
        </el-table-column>
        <el-table-column
            prop="name"
            label="角色"
            width="100">
          <template slot-scope="scope">
            <span class="el-tag el-tag--success el-tag--mini el-tag--dark">
              {{scope.row.departmentId==1?'管理员':scope.row.departmentId==2?'普通用户':'访客'}}</span>
          </template>
        </el-table-column>
        <el-table-column
            prop="username"
            label="用户名"
            width="200">
        </el-table-column>
        <el-table-column
            prop="email"
            label="邮箱"
            width="350">
        </el-table-column>
        <el-table-column
            sortable
            prop="created"
            label="创建日期"
            width="250">
        </el-table-column>
        <el-table-column
            prop="status"
            label="是否启用"
            width="100">
          <template slot-scope="scope">
            <el-switch
                v-model="scope.row.status==1"
                active-color="#13ce66"
                inactive-color="#ff4949">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column
            label="操作">
            <el-button type="primary" size="mini" icon="el-icon-edit"></el-button>
            <el-button type="danger" size="mini" icon="el-icon-delete"></el-button>
            <el-button type="warning" size="mini" icon="el-icon-s-tools"></el-button>
        </el-table-column>
      </el-table>
      <el-pagination
          style="padding-top: 15px;height: 60px"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page=current
          :page-sizes="[3, 5, 10]"
          :page-size=size
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </el-card>
    <UserAdd :addOrUpdateVisible="addOrUpdateVisible" @changeShow="showAddOrUpdate" ref="addOrUpdateRef"></UserAdd>
</div>
</template>

<script>
import { findUserList } from '@/api/users'
import { findDeptAndCount } from '@/api/dept'

import UserAdd from '@/views/user/UserAdd'

export default {
  name: 'Users',
  data () {
    return {
      addOrUpdateVisible: false,
      userVO: {
        departmentId: '',
        username: '',
        email: ''
      },
      formInline: {
        user: '',
        email: '',
        city: '',
        radio: '1'
      },
      userlist: [],
      size: 3,
      current: 1,
      total: 0,
      departments: []
    }
  },
  components: {
    UserAdd

  },
  created () {
    this.getUserList()
    this.getDeptAndCount()
  },
  methods: {
    handleSizeChange (val) {
      this.size = val
      this.getUserList()
    },
    handleCurrentChange (val) {
      this.current = val
      this.getUserList()
    },
    async getUserList () {
      const { data } = await findUserList(this.current, this.size, this.userVO)
      this.userlist = data.data.records
      this.total = data.data.total
    },
    async getDeptAndCount () {
      const { data } = await findDeptAndCount()
      this.departments = data.data
    },
    resetUserVO () {
      this.userVO.departmentId = ''
      this.userVO.email = ''
      this.userVO.username = ''
      this.getUserList()
    },
    show () {
      this.addOrUpdateVisible = true
    },
    showAddOrUpdate (data) {
      if (data === 'false') {
        this.addOrUpdateVisible = false
      } else {
        this.addOrUpdateVisible = true
      }
    }
  }
}

</script>

<style scoped>

</style>
