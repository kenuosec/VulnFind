<template>
  <div class="login_container">
    <div class="login_box">
      <!-- 登陆头像 -->
      <div class="avatar_box">
        <img src="../assets/logo.png" />
      </div>
      <!-- 登陆表单 -->
      <el-form
        ref="ruleForm"
        :model="ruleForm"
        :rules="rules"
        label-width="0px"
        class="login_form"
      >
        <el-form-item prop="username">
          <el-input
            v-model="ruleForm.username"
            prefix-icon="el-icon-user-solid"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            v-model="ruleForm.password"
            prefix-icon="el-icon-lollipop"
            type="password"
          ></el-input>
        </el-form-item>

        <el-form-item class="btns">
          <el-button type="primary" plain @click="submitForm('ruleForm')">登陆</el-button>
          <el-button type="danger" plain @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
export default {
  name: 'Login',
  data () {
    return {
      ruleForm: {
        username: 'txf',
        password: '111111'
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请选择密码', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log('post submit!!')
          const _this = this
          this.$axios.post('/login', this.ruleForm).then(res => {
            console.log(res.data)
            const jwt = res.headers.authorization
            const userInfo = res.data.data
            // 把数据共享出去
            _this.$store.commit('SET_TOKEN', jwt)
            _this.$store.commit('SET_USERINFO', userInfo)
            // 获取
            console.log(_this.$store.getters.getUser)
            _this.$router.push('/home')
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style lang="less" scoped>
.login_container {
  background-color: #33313e;
  height: 100%;
}
.login_box {
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
  .avatar_box {
    height: 130px;
    width: 130px;
    border-radius: 50%;
    padding: 10px;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;
    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: #eee;
    }
  }
}
.login_form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
  //border: 1px solid #eaeaea;
  //box-shadow: 0 0 25px #cac6c6;
}
.btns {
  display: flex;
  justify-content: flex-end;
}
</style>
