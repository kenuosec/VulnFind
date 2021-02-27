<template>
  <div>
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card class="box-card" >
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
<!--        <el-form-item label="部门" lable-with="70px">-->
<!--        <el-select v-model="formInline.city" placeholder="请选择">-->
<!--          <el-option-->
<!--              v-for="item in cities"-->
<!--              :key="item.value"-->
<!--              :label="item.label"-->
<!--              :value="item.value">-->
<!--            <span style="float: left">{{ item.label }}</span>-->
<!--            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>-->
<!--          </el-option>-->
<!--        </el-select>-->
<!--        </el-form-item>-->
        <el-form-item label="用户名" lable-with="200px">
          <el-input v-model="formInline.user" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" lable-with="200px">
          <el-input clearable v-model="formInline.email" placeholder="请输入邮箱"></el-input>
        </el-form-item>
<!--        <el-form-item lable-with="1000px">-->
<!--          <el-radio v-model="formInline.radio" label="1">1</el-radio>-->
<!--          <el-radio v-model="formInline.radio" label="2">2</el-radio>-->
<!--          <el-radio v-model="formInline.radio" label="3">3</el-radio>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="用户名" lable-with="70px">-->
<!--          <el-input clearable v-model="formInline.nikename" placeholder="请输入"></el-input>-->
<!--        </el-form-item>-->
        <el-form-item style="margin-left: 1100px">
          <el-button type="primary" icon="el-icon-search">查询</el-button>
          <el-button icon="el-icon-refresh">重置</el-button>

        </el-form-item>
      </el-form>
      <el-table
          :data="userlist"
          border
          max-height="400"
          style="width: 100%;">
        <el-table-column
            sortable
            prop="id"
            label="id"
            width="140">
        </el-table-column>
        <el-table-column
            prop="username"
            label="用户名"
            width="350">
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
            label="是否禁用"
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
          :current-page=pagecurrent
          :page-sizes="[3, 5, 10]"
          :page-size=pagesize
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
import {findUserList} from "@/api/users";

export default {
  name: 'Users',
  data() {
    return {
      formInline: {
        user: '',
        email: '',
        city: '',
        radio: '1',
      },
      userlist: [],
      pagesize: 5,
      pagecurrent: 1,
      total: 0,
      cities: [{
        value: 'Beijing',
        label: '北京'
      }, {
        value: 'Shanghai',
        label: '上海'
      }, {
        value: 'Nanjing',
        label: '南京'
      }, {
        value: 'Chengdu',
        label: '成都'
      }, {
        value: 'Shenzhen',
        label: '深圳'
      }, {
        value: 'Guangzhou',
        label: '广州'
      }]
    }
  },
  created() {
    this.getUserList(1)
  },
  methods: {
    onSubmit() {
      alert("操");
    },
    handleSizeChange(val) {
      this.size = val;
      this.getUserList();
    },
    handleCurrentChange(val) {
      this.current = val;
      this.getUserList();
    },
    async getUserList(){
      const {data} = await findUserList(this.current,this.size)
      console.log(data)
      this.userlist = data.data.records;
      this.total = data.data.total;
    }
  }
}
</script>

<style scoped>

</style>
