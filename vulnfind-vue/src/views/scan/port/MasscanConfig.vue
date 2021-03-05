<template>
  <div>
    <el-breadcrumb separator="/" style="padding-left: 10px;padding-bottom: 10px;font-size: 12px">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>扫描配置</el-breadcrumb-item>
      <el-breadcrumb-item>端口配置</el-breadcrumb-item>
      <el-breadcrumb-item>masscan</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card class="box-card">
      选择端口:

      <el-radio v-model="radio" label="1">精简端口</el-radio>
      <el-radio v-model="radio" label="2">常见端口</el-radio>
      <el-radio v-model="radio" label="3">所有端口</el-radio>
      </el-card>

    <el-input
        v-for="item in port"
        type="textarea"
        :rows="4"
        placeholder="选输入扫描端口:"
        v-model="textarea">
    </el-input>

  </div>
</template>

<script>
export default {
  name: "MasscanConfig",
  data () {
    return {
      radio: '3',
      textarea: '',
      // port: [
      //   {label:},
      //   {}
      // ]
    };
  }
}
</script>

<style scoped>

</style>
