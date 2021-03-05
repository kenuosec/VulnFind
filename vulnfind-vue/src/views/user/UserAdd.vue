<template>
  <div>
    <el-dialog v-bind="$attrs" :visible.sync="showDialog" v-on="$listeners" @open="onOpen" @close="onClose" title="用户添加">
      <el-row :gutter="15">
        <el-form ref="elForm" :model="user" :rules="rules" size="medium" label-width="100px">
          <el-col :span="24">
            <el-form-item label="用户头像">
              <pan-thumb :image="image" />
              <el-button type="primary" icon="el-icon-upload" @click="imagecropperShow=true">
                选择头像
              </el-button>
              <image-cropper
                v-show="imagecropperShow"
                :width="300"
                :height="300"
                url="/uploadImgFile"
                :key="imagecrooperKey"
                field="file"
                @close="closeImage"
                @crop-upload-success="cropSuccess"/>
            </el-form-item>>
          </el-col>

          <el-col :span="12">
            <el-form-item label="用户名" prop="username">
              <el-input v-model="user.username" placeholder="请输入用户名" clearable :style="{width: '100%'}"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="密码" prop="password">
              <el-input v-model="user.password" placeholder="请输入密码" clearable show-password :style="{width: '100%'}"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="角色" prop="departmentId">
              <el-select v-model="user.departmentId" placeholder="请选择角色" clearable :style="{width: '100%'}">
                <el-option v-for="(item, index) in departmentIdOptions" :key="index" :label="item.label"
                  :value="item.value" :disabled="item.disabled"></el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="user.email" placeholder="请输入邮箱" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
          </el-col>

        </el-form>
      </el-row>
      <div slot="footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="handelConfirm">确定</el-button>
      </div>

    </el-dialog>
  </div>
</template>
<script>

import ImageCropper from '../../components/ImageCropper'
import PanThumb from '../../components/PanThumb'

import {deleteImgFile,saveUser} from "@/api/users";

export default {
  inheritAttrs: false,
  components: {
    ImageCropper,
    PanThumb
  },
  props: {
    addOrUpdateVisible: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      user: {
        avatar: null,
        username: undefined,
        password: undefined,
        departmentId: undefined,
        email: undefined,
        id: undefined
      },
      showDialog: false,
      imagecropperShow: false,
      imagecrooperKey: 0,
      image: 'https://avatars.githubusercontent.com/u/45926593',
      rules: {
        username: [{
          required: true,
          message: '请输入用户名',
          trigger: 'blur'
        }],
        password: [{
          required: true,
          message: '请输入密码',
          trigger: 'blur'
        }],
        departmentId: [{
          required: true,
          message: '请选择角色',
          trigger: 'change'
        }],
        email: [{
          required: true,
          message: '请输入邮箱',
          trigger: 'blur'
        }]
      },
      field101Action: 'https://jsonplaceholder.typicode.com/posts/',
      field101fileList: [],
      departmentIdOptions: [{
        label: '管理员',
        value: 1
      }, {
        label: '普通用户',
        value: 2
      },{
        label: '访客',
        value: 3
      },]
    }
  },
  computed: {},
  watch: {
    addOrUpdateVisible (oldVal, newVal) {
      this.showDialog = this.addOrUpdateVisible
    }
  },
  created () {},
  mounted () {},
  methods: {
    onOpen () {},
    onClose () {
      this.$refs.elForm.resetFields()
      this.$emit('changeShow', 'false')
    },
    close () {
      this.$emit('update:visible', false)
      this.$emit('changeShow', 'false')
    },
    handelConfirm () {
      this.$refs.['elForm'].validate(valid => {
        if (!valid) {
          return
        }
        console.log(this.user)
        this.addUser()
      })
    },
    //添加用户
    async addUser () {
      const { data: res } = await saveUser(this.user)
      if (res.code == 200){
        this.$notify.success({
          title: '操作成功',
          message: '用户添加成功',
        })
        //关闭显示框
        this.$emit('changeShow', 'false')
        //清空当前用户
        this.user = {}
        this.$emit('getUserList');
        this.$emit('getDeptAndCount');
      }else {
        return this.$message.error('用户添加失败:'+ res.message)
      }
    },
    field101BeforeUpload (file) {
      const isRightSize = file.size / 1024 / 1024 < 2
      if (!isRightSize) {
        this.$message.error('文件大小超过 2MB')
      }
      return isRightSize
    },
    handleClose () {
      this.$emit('changeShow', 'false')
    },
    //上传成功后的回调函数
    cropSuccess(data) {
      console.log(data)
      var oldImage = this.image;
      this.imagecropperShow = false
      this.user.avatar = this.image = data.data;
      this.imagecropperKey = this.imagecropperKey + 1
      this.deleteAvatar(oldImage);
    },
    //删除之前的头像
    async deleteAvatar(oldImage){
      const {data} = await deleteImgFile(oldImage);
      console.log(data)
    },
    closeImage() {
      this.imagecropperShow = false
      this.imagecropperKey = this.imagecropperKey + 1
    }
  }
}

</script>
<style>
.el-upload__tip {
  line-height: 1.2;
}
</style>
