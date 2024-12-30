<template>
  <div>
    <div class="register_view">
      <el-form :model="registerForm" class="register_form">
        <div class="title_view">{{ projectName }}注册</div>
        <div class="list_item">
          <div class="list_label">用户账号：</div>
          <el-input
            class="list_inp"
            v-model="registerForm.yonghuzhanghao"
            placeholder="请输入用户账号"
            type="text"
          />
        </div>
        <div class="list_item">
          <div class="list_label">用户密码：</div>
          <el-input
            class="list_inp"
            v-model="registerForm.yonghumima"
            placeholder="请输入用户密码"
            type="password"
          />
        </div>
        <div class="list_item">
          <div class="list_label">确认用户密码：</div>
          <el-input
            class="list_inp"
            v-model="registerForm.yonghumima2"
            type="password"
            placeholder="请输入确认用户密码"
          />
        </div>
        <div class="list_item">
          <div class="list_label">用户姓名：</div>
          <el-input
            class="list_inp"
            v-model="registerForm.yonghuxingming"
            placeholder="请输入用户姓名"
            type="text"
          />
        </div>
        <div class="list_item">
          <div class="list_label">头像：</div>
          <div :style="{ width: '80%' }" class="list_file_list">
            <uploads
              action="file/upload"
              tip="请上传头像"
              :limit="3"
              :fileUrls="registerForm.touxiang ? registerForm.touxiang : ''"
              @change="touxiangUploadSuccess"
            >
            </uploads>
          </div>
        </div>
        <div class="list_item">
          <div class="list_label">性别：</div>
          <el-select
            class="list_sel"
            v-model="registerForm.xingbie"
            placeholder="请选择性别"
          >
            <el-option
              v-for="item in yonghuxingbieLists"
              :label="item"
              :value="item"
            ></el-option>
          </el-select>
        </div>

        <div class="list_item">
          <div class="list_label">手机号码：</div>
          <el-input
            class="list_inp"
            v-model="registerForm.shoujihaoma"
            placeholder="请输入手机号码"
            type="text"
          />
        </div>
        <div class="list_btn">
          <el-button class="register" type="success" @click="handleRegister"
            >注册</el-button
          >
          <div class="r-login" @click="close">已有账号，直接登录</div>
        </div>
      </el-form>
    </div>
  </div>
</template>
<script setup>
import { ref, getCurrentInstance, nextTick } from 'vue'
const context = getCurrentInstance()?.appContext.config.globalProperties
const projectName = context?.$project.projectName
//获取注册类型
import { useRoute } from 'vue-router'
const route = useRoute()
const tableName = ref('yonghu')

const registerForm = ref({
  xingbie: '',
})
const yonghuxingbieLists = ref([])
const init = () => {
  yonghuxingbieLists.value = '男,女'.split(',')
}
const touxiangUploadSuccess = (fileUrls) => {
  registerForm.value.touxiang = fileUrls
}
// 多级联动参数
//注册按钮
const handleRegister = () => {
  let url = tableName.value + '/register'
  if (!registerForm.value.yonghuzhanghao) {
    context?.$toolUtil.message(`用户账号不能为空`, 'error')
    return false
  }
  if (!registerForm.value.yonghumima) {
    context?.$toolUtil.message(`用户密码不能为空`, 'error')
    return false
  }
  if (!registerForm.value.yonghuxingming) {
    context?.$toolUtil.message(`用户姓名不能为空`, 'error')
    return false
  }
  if (registerForm.value.touxiang != null) {
    registerForm.value.touxiang = registerForm.value.touxiang.replace(
      new RegExp(context?.$config.url, 'g'),
      ''
    )
  }
  if (
    registerForm.value.shoujihaoma &&
    !context?.$toolUtil.isMobile(registerForm.value.shoujihaoma)
  ) {
    context?.$toolUtil.message(`手机号码应输入手机格式`, 'error')
    return false
  }

  context
    ?.$http({
      url: url,
      method: 'post',
      data: registerForm.value,
    })
    .then((res) => {
      context?.$toolUtil.message('注册成功', 'success', (obj) => {
        context?.$router.push({
          path: '/login',
        })
      })
    })
}
//公共方法
const getUUID = () => {
  return new Date().getTime()
}
//返回登录
const close = () => {
  context?.$router.push({
    path: '/login',
  })
}
init()
</script>
<style lang="scss" scoped>
.register_view {
  background-repeat: no-repeat;
  flex-direction: column;
  background-size: 100%100%;
  display: flex;
  min-height: 100vh;
  justify-content: center;
  position: relative;
  background-position: center center;
  background-image: url(http://localhost:8080/homestayHotel/file/88e54ac25f9d4aa883e39f7d74b4c859.png);
  // 表单盒子
  .register_form {
    border-radius: 0px;
    padding: 30px 20px;
    box-shadow: rgb(255, 181, 137) 0px 4px 20px 0px;
    margin: 30px 0px 30px 58vw;
    flex-direction: column;
    background: #fff;
    display: flex;
    width: 34.8vw;
    box-sizing: border-box;
  }
  // 标题样式
  .title_view {
    padding: 0px;
    margin: 0 auto 20px;
    color: #5d5b5c;
    font-weight: 600;
    width: 80%;
    font-size: 26px;
    text-align: center;
  }
  // item盒子
  .list_item {
    margin: 10px auto;
    display: flex;
    width: 80%;
    justify-content: flex-start;
    align-items: center;
    // label
    .list_label {
      color: #666;
      width: 70px;
      font-size: 14px;
      box-sizing: border-box;
      text-align: left;
    }
    // 输入框
    :deep(.list_inp) {
      border: 1px solid #ddd;
      border-radius: 4px;
      padding: 0 10px;
      color: #666;
      width: 80%;
      line-height: 32px;
      box-sizing: border-box;
      height: 32px;
      //去掉默认样式
      .el-input__wrapper {
        border: none;
        box-shadow: none;
        background: none;
        border-radius: 0;
        height: 100%;
        padding: 0;
      }
      .is-focus {
        box-shadow: none !important;
      }
    }
  }
  //下拉框样式
  :deep(.list_sel) {
    border: 1px solid #ddd;
    border-radius: 4px;
    padding: 0 10px;
    color: #666;
    width: 80%;
    line-height: 32px;
    box-sizing: border-box;
    //去掉默认样式
    .select-trigger {
      height: 100%;
      .el-input {
        height: 100%;
        .el-input__wrapper {
          border: none;
          box-shadow: none;
          background: none;
          border-radius: 0;
          height: 100%;
          padding: 0;
        }
        .is-focus {
          box-shadow: none !important;
        }
      }
    }
  }
  //按钮盒子
  .list_btn {
    margin: 10px auto;
    display: flex;
    width: 80%;
    align-items: center;
    flex-wrap: wrap;
    //注册按钮
    .register {
      border: none;
      border-radius: 4px;
      margin: 20px auto 0;
      color: #fff;
      background: #d09d7d;
      font-weight: 700;
      width: 80%;
      font-size: 16px;
      height: 40px;
    }
    //注册按钮悬浮样式
    .register:hover {
      border: none;
      margin: 20px auto 0;
      background: #d09d7d;
      font-weight: 700;
      width: 80%;
      font-size: 16px;
      height: 40px;
    }
    //已有账号
    .r-login {
      cursor: pointer;
      margin: 10px auto;
      color: #999;
      width: 80%;
      font-size: 14px;
      text-align: center;
    }
  }
  //图片上传样式
  .list_file_list {
    //提示语
    :deep(.el-upload__tip) {
      margin: 7px 0 0;
      color: #999;
      display: flex;
      font-size: 14px;
      justify-content: flex-start;
      align-items: center;
    }
    //外部盒子
    :deep(.el-upload--picture-card) {
      border: 1px solid #ddd;
      cursor: pointer;
      background-color: #fff;
      border-radius: 4px;
      width: 80px;
      line-height: 70px;
      text-align: center;
      height: 80px;
      //图标
      .el-icon {
        color: #999;
        font-size: 32px;
        line-height: 80px;
      }
    }
    :deep(.el-upload-list__item) {
      border: 1px solid #ddd;
      cursor: pointer;
      background-color: #fff;
      border-radius: 4px;
      width: 80px;
      line-height: 70px;
      text-align: center;
      height: 80px;
    }
  }
}
</style>
