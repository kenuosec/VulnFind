<template>
  <div>
    <el-dialog v-bind="$attrs" :visible.sync="showDialog" v-on="$listeners" @open="onOpen" @close="onClose" title="Dialog Titile">
      <el-row :gutter="15">
        <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
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
              <el-input v-model="formData.username" placeholder="请输入用户名" clearable :style="{width: '100%'}"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="密码" prop="password">
              <el-input v-model="formData.password" placeholder="请输入密码" clearable show-password :style="{width: '100%'}"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="角色" prop="departmentId">
              <el-select v-model="formData.departmentId" placeholder="请选择角色" clearable :style="{width: '100%'}">
                <el-option v-for="(item, index) in departmentIdOptions" :key="index" :label="item.label"
                  :value="item.value" :disabled="item.disabled"></el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="formData.email" placeholder="请输入邮箱" clearable :style="{width: '100%'}">
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

import {deleteImgFile} from "@/api/users";

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
      formData: {
        field101: null,
        username: undefined,
        password: undefined,
        departmentId: undefined,
        email: undefined
      },
      showDialog: false,
      imagecropperShow: false,
      imagecrooperKey: 0,
      image: 'https://wpimg.wallstcn.com/577965b9-bb9e-4e02-9f0c-095b41417191',
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
        label: '选项一',
        value: 1
      }, {
        label: '选项二',
        value: 2
      }]
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
      this.$refs.elForm.validate(valid => {
        if (!valid) return
        this.close()
      })
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
    cropSuccess(data) {
      console.log(data)
      var oldImage = this.image;
      this.imagecropperShow = false
      this.image = data.data;
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
