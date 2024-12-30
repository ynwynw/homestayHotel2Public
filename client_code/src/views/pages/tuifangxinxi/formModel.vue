<template>
  <div
    class="app-contain"
    :style="{
      padding: '0 12%',
      margin: '10px auto 20px',
      alignItems: 'flex-start',
      borderRadius: '0px',
      flexWrap: 'wrap',
      background: 'none',
      display: 'flex',
      width: '100%',
      position: 'relative',
      justifyContent: 'space-between',
    }"
  >
    <div class="bread_view">
      <el-breadcrumb separator=">" class="breadcrumb">
        <el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }"
          >首页</el-breadcrumb-item
        >
        <el-breadcrumb-item
          class="second_breadcrumb"
          v-for="(item, index) in breadList"
          :key="index"
          >{{ item.name }}</el-breadcrumb-item
        >
      </el-breadcrumb>
    </div>
    <div class="back_view">
      <el-button class="back_btn" @click="backClick" type="primary"
        >返回</el-button
      >
    </div>
    <div class="detail_view">
      <div class="swiper_view">
        <mySwiper
          :data="bannerList"
          :type="3"
          :loop="false"
          :navigation="false"
          :pagination="true"
          :paginationType="1"
          :scrollbar="false"
          :slidesPerView="1"
          :spaceBetween="20"
          :autoHeight="false"
          :centeredSlides="false"
          :freeMode="false"
          :effectType="5"
          :direction="horizontal"
          :autoplay="true"
          :slidesPerColumn="1"
        >
          <template #default="scope">
            <img
              :style="{
                border: '0px solid #fff',
                width: '100%',
                objectFit: 'cover',
                height: '400px',
              }"
              :src="scope.row ? $config.url + scope.row : ''"
            />
          </template>
        </mySwiper>
      </div>

      <div class="info_view">
        <div class="title_view">
          <div class="detail_title"></div>
        </div>
        <div class="info_item">
          <div class="info_label">预订编号</div>
          <div class="info_text">{{ detail.yudingbianhao }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">房间号</div>
          <div class="info_text">{{ detail.fangjianhao }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">客房类型</div>
          <div class="info_text">{{ detail.kefangleixing }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">入住日期</div>
          <div class="info_text">{{ detail.ruzhuriqi }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">入住天数</div>
          <div class="info_text">{{ detail.ruzhutianshu }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">用户账号</div>
          <div class="info_text">{{ detail.yonghuzhanghao }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">用户姓名</div>
          <div class="info_text">{{ detail.yonghuxingming }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">手机号码</div>
          <div class="info_text">{{ detail.shoujihaoma }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">退房时间</div>
          <div class="info_text">{{ detail.tuifangshijian }}</div>
        </div>
        <div class="info_item">
          <div class="info_label">客房部账号</div>
          <div class="info_text">{{ detail.kefangbuzhanghao }}</div>
        </div>
        <div class="btn_view">
          <el-button
            class="edit_btn"
            v-if="centerType && btnAuth('tuifangxinxi', '修改')"
            type="primary"
            @click="editClick"
            >修改</el-button
          >
          <el-button
            class="del_btn"
            v-if="centerType && btnAuth('tuifangxinxi', '删除')"
            type="danger"
            @click="delClick"
            >删除</el-button
          >
        </div>
      </div>
    </div>
    <el-tabs type="border-card" v-model="activeName" class="tabs_view">
    </el-tabs>
  </div>
</template>
<script setup>
import axios from 'axios'
import {
  ref,
  getCurrentInstance,
  watch,
  onUnmounted,
  onMounted,
  nextTick,
  computed,
} from 'vue'
import { ElMessageBox } from 'element-plus'
import { useRoute, useRouter } from 'vue-router'
const context = getCurrentInstance()?.appContext.config.globalProperties
const route = useRoute()
const router = useRouter()
//基础信息
const tableName = 'tuifangxinxi'
const formName = '退房信息'
//基础信息
const breadList = ref([
  {
    name: formName,
  },
])
//权限验证
const btnAuth = (e, a) => {
  if (centerType.value) {
    return context?.$toolUtil.isBackAuth(e, a)
  } else {
    return context?.$toolUtil.isAuth(e, a)
  }
}
//查看权限验证
const btnFrontAuth = (e, a) => {
  if (centerType.value) {
    return context?.$toolUtil.isBackAuth(e, a)
  } else {
    return context?.$toolUtil.isFrontAuth(e, a)
  }
}
// 返回
const backClick = () => {
  history.back()
}
// 轮播图
const bannerList = ref([])
// 详情
const title = ref('')
const detail = ref({})
const activeName = ref('first')
const getDetail = () => {
  context
    ?.$http({
      url: `${tableName}/detail/${route.query.id}`,
      method: 'get',
    })
    .then((res) => {
      detail.value = res.data.data
    })
}
// 下载文件
const downClick = (file) => {
  if (!file) {
    context?.$toolUtil.message('文件不存在', 'error')
  }
  let arr = file.replace(new RegExp('file/', 'g'), '')
  axios
    .get(
      (location.href.split(context?.$config.name).length > 1
        ? location.href.split(context?.$config.name)[0]
        : '') +
        context?.$config.name +
        '/file/download?fileName=' +
        arr,
      {
        headers: {
          token: context?.$toolUtil.storageGet('frontToken'),
        },
        responseType: 'blob',
      }
    )
    .then(({ data }) => {
      const binaryData = []
      binaryData.push(data)
      const objectUrl = window.URL.createObjectURL(
        new Blob(binaryData, {
          type: 'application/pdf;chartset=UTF-8',
        })
      )
      const a = document.createElement('a')
      a.href = objectUrl
      a.download = arr
      // a.click()
      // 下面这个写法兼容火狐
      a.dispatchEvent(
        new MouseEvent('click', {
          bubbles: true,
          cancelable: true,
          view: window,
        })
      )
      window.URL.revokeObjectURL(data)
    })
}
// 判断是否从个人中心跳转
const centerType = ref(false)
const init = () => {
  if (route.query.centerType) {
    centerType.value = true
  }
  getDetail()
}
//修改
const editClick = () => {
  router.push(`/index/${tableName}Add?id=${detail.value.id}&&type=edit`)
}
//删除
const delClick = () => {
  ElMessageBox.confirm(`是否删除此${formName}？`, '提示', {
    confirmButtonText: '是',
    cancelButtonText: '否',
    type: 'warning',
  }).then(() => {
    context
      ?.$http({
        url: `${tableName}/delete`,
        method: 'post',
        data: [detail.value.id],
      })
      .then((res) => {
        context?.$toolUtil.message('删除成功', 'success', () => {
          history.back()
        })
      })
  })
}
onMounted(() => {
  init()
})
</script>
<style lang="scss" scoped>
// 返回盒子
.back_view {
  border-radius: 0px;
  padding: 0 20px;
  margin: 0 auto 20px;
  background: none;
  display: block;
  width: 100%;
  text-align: right;
  // 返回按钮
  .back_btn {
    border: 1px solid #ddd;
    cursor: pointer;
    border-radius: 0px;
    padding: 0 30px;
    outline: none;
    color: #666;
    background: #f9f9f9;
    width: auto;
    font-size: 14px;
    height: 32px;
  }
  // 返回按钮-悬浮
  .back_btn:hover {
  }
}
// 面包屑盒子
.bread_view {
  border-radius: 0px;
  padding: 12px 0px;
  margin: 0px auto;
  background: none;
  width: 100%;
  border-color: #eee;
  border-width: 0 0 0px;
  position: relative;
  border-style: solid;
  :deep(.breadcrumb) {
    font-size: 14px;
    line-height: 1;
    .el-breadcrumb__separator {
      margin: 0 9px;
      color: #999;
      font-weight: 500;
    }
    .first_breadcrumb {
      .el-breadcrumb__inner {
        color: #333;
        display: inline-block;
      }
    }
    .second_breadcrumb {
      .el-breadcrumb__inner {
        color: #999;
        display: inline-block;
      }
    }
  }
}

.detail_view {
  border-radius: 0;
  padding: 20px 20px 80px;
  background: linear-gradient(to right, #3a475f, #422240);
  display: flex;
  width: 100%;
  min-height: 520px;
  border-color: #ddd;
  border-width: 0px;
  justify-content: space-between;
  position: relative;
  border-style: solid;
  flex-wrap: wrap;
  // 轮播图
  .swiper_view {
    border: 0px solid #ddd;
    padding: 10px;
    margin: 0;
    background: #fff;
    width: 320px;
    float: left;
    height: 420px;
    order: 0;
  }

  // 文字区
  .info_view {
    border: 0px solid #ddd;
    padding: 0 4%;
    margin: 0 0 0px;
    background: none;
    width: calc(100% - 350px);
    box-sizing: border-box;
    float: right;
    order: 3;

    .title_view {
      border: 0px solid #ddd;
      padding: 0px;
      margin: 0 0 10px;
      background: none;
      display: flex;
      width: 100%;
      line-height: 40px;
      justify-content: space-between;
      align-items: center;

      .detail_title {
        color: #fff;
        font-weight: 600;
        font-size: 26px;
      }
      .follow {
        border: 0px solid #ffffff50;
        cursor: pointer;
        border-radius: 4px;
        padding: 4px 10px;
        color: #fff;
        background: none;
        display: flex;
        width: auto;
        line-height: 1;
        justify-content: center;
        align-items: center;
        .iconfont {
          margin: 0 4px 0 0;
          color: #f60;
          font-size: 18px;
        }
        .iconfontActive {
          margin: 0 4px 0 0;
          color: #11396160;
          font-size: 18px;
        }
        span {
          color: #f60;
          font-size: 14px;
        }
        .textActive {
          color: #11396190;
          font-size: 14px;
        }
      }
      .follow:hover {
      }
      .follow:active {
        transform: scale(0.9);
      }
    }

    .info_item {
      border-radius: 0px;
      padding: 0 0 6px;
      margin: 0 0 0px;
      background: none;
      display: flex;
      border-color: #ddd;
      border-width: 0 0 0px;
      align-items: center;
      border-style: dashed;

      .info_label {
        margin: 0 12px 0 0;
        color: #fff;
        font-weight: 500;
        width: auto;
      }
      .info_text {
        color: #fff;
      }
    }
    .btn_view {
      padding: 0;
      margin: 6px 0 10px;
      display: block;
      width: 100%;
      flex-wrap: wrap;
      // 修改-按钮
      .edit_btn {
        border: 0px solid #2da065;
        padding: 0 10px;
        color: #fff;
        background: #2da065;
        line-height: 32px;
        transition: all 0.3s;
        height: 32px;
      }
      // 悬浮
      .edit_btn:hover {
      }
      // 删除-按钮
      .del_btn {
        border: 0px solid #f68b8b;
        padding: 0 10px;
        color: #fff;
        background: #f68b8b;
        line-height: 32px;
        transition: all 0.3s;
        height: 32px;
      }
      // 悬浮
      .del_btn:hover {
      }
    }
  }
}

//底部盒子
.tabs_view {
  border: 0px solid #ddd;
  border-radius: 0px;
  padding: 20px 0;
  box-shadow: none;
  margin: 0px auto;
  background: #fff;
  width: 100%;
  :deep(.el-tabs__header) {
    background: transparent;
    border: none;
  }
  // 头部
  :deep(.el-tabs__nav-scroll) {
    border-radius: 0;
    padding: 0px 0px 0;
    margin: 0;
    background: url(http://localhost:8080/homestayHotel/file/9641f34153974d92b82100003e815b85.png)
      repeat-x center bottom;
    display: flex;
    border-color: #eee;
    border-width: 0;
    position: relative;
    border-style: solid;
    flex-wrap: wrap;
    height: 48px;
    .el-tabs__nav {
      background: none;
      .el-tabs__item {
        border: 0px solid #eee;
        padding: 0 0px;
        margin: 0 30px 0 0;
        color: #333;
        font-weight: 500;
        display: inline-block;
        font-size: 18px;
        line-height: 48px;
        transition: all 0.3s;
        border-radius: 0px;
        background: none;
        border-width: 0px 0px 2px;
        position: relative;
        list-style: none;
        text-align: center;
        height: 48px;
      }
      .el-tabs__item:hover {
        border: 0px solid #ef4238;
        color: #ef4238;
        background: none;
        border-width: 0px 0px 2px;
      }
      .is-active {
        border: 0px solid #ef4238;
        border-radius: 0px;
        padding: 0 0px;
        margin: 0 30px 0 0;
        color: #ef4238;
        background: none;
        font-size: 18px;
        border-width: 0px 0px 2px;
        line-height: 48px;
        text-align: center;
        height: 48px;
      }
    }
  }
  // 内容区
  :deep(.el-tabs__content) {
    border-radius: 4px;
    padding: 20px 0 0;
    margin: 0px;
    color: #666;
    background: none;
    font-size: 14px;
    border-color: #eee;
    border-width: 0px;
    border-style: solid;
  }
}
</style>
