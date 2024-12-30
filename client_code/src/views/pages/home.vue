<template>
  <div>
    <div class="home_box">
      <!-- 客房信息推荐 -->
      <div class="recomList_view">
        <div class="recomList_title">客房信息推荐</div>
        <div class="recommend_list_one">
          <div
            class="recommend_item animation_box"
            v-for="(item, index) in kefangxinxiRecomList"
            :key="index"
            @click="detailClick('kefangxinxi', item.id)"
          >
            <div class="recommend_img_box">
              <img
                class="recommend_img"
                v-if="isHttp(item.fangjiantupian)"
                :src="item.fangjiantupian.split(',')[0]"
                alt=""
              />
              <img
                class="recommend_img"
                v-else
                :src="
                  item.fangjiantupian
                    ? $config.url + item.fangjiantupian.split(',')[0]
                    : ''
                "
                alt=""
              />
            </div>
            <div class="recommend_content">
              <div class="recommend_title">房间号：{{ item.fangjianhao }}</div>
              <div class="recommend_title">每晚/元：{{ item.jiage }}</div>
              <div class="recommend_title">
                房间状态：{{ item.fangjianzhuangtai }}
              </div>
              <div class="recommend_one_bottom">
                <div class="recommend_like" v-if="item.thumbsupnum">
                  <span class="iconfont icon-thumb-up-line1 like_icon"></span>
                  <div class="like_num">{{ item.thumbsupnum }}</div>
                </div>
                <div class="recommend_collect" v-if="item.storeupnum">
                  <el-icon><StarFilled /></el-icon>
                  <div class="collect_num">{{ item.storeupnum }}</div>
                </div>
                <div class="recommend_clickNum" v-if="item.clicknum">
                  <el-icon><View /></el-icon>
                  <div class="clickNum_num">{{ item.clicknum }}</div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="recommend_more_view" @click="moreClick('kefangxinxi')">
          <span class="recommend_more_text">全部</span>
        </div>
      </div>
      <!-- 菜品信息推荐 -->
      <div class="recomList_view">
        <div class="recomList_title">菜品信息推荐</div>
        <div class="recommend_list_one">
          <div
            class="recommend_item animation_box"
            v-for="(item, index) in caipinxinxiRecomList"
            :key="index"
            @click="detailClick('caipinxinxi', item.id)"
          >
            <div class="recommend_img_box">
              <img
                class="recommend_img"
                v-if="isHttp(item.caipintupian)"
                :src="item.caipintupian.split(',')[0]"
                alt=""
              />
              <img
                class="recommend_img"
                v-else
                :src="
                  item.caipintupian
                    ? $config.url + item.caipintupian.split(',')[0]
                    : ''
                "
                alt=""
              />
            </div>
            <div class="recommend_content">
              <div class="recommend_title">
                {{ item.caipinmingcheng }}
              </div>
              <div class="recommend_one_bottom">
                <div class="recommend_like" v-if="item.thumbsupnum">
                  <span class="iconfont icon-thumb-up-line1 like_icon"></span>
                  <div class="like_num">{{ item.thumbsupnum }}</div>
                </div>
                <div class="recommend_collect" v-if="item.storeupnum">
                  <el-icon><StarFilled /></el-icon>
                  <div class="collect_num">{{ item.storeupnum }}</div>
                </div>
                <div class="recommend_clickNum" v-if="item.clicknum">
                  <el-icon><View /></el-icon>
                  <div class="clickNum_num">{{ item.clicknum }}</div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="recommend_more_view" @click="moreClick('caipinxinxi')">
          <span class="recommend_more_text">全部</span>
        </div>
      </div>
      <!-- 新闻资讯 -->
      <div class="newsList_view">
        <div class="newsList_title">新闻资讯</div>
        <div class="news_list_one">
          <div
            class="news_item animation_box"
            v-for="(item, index) in newsList"
            :key="index"
            @click="newsDetailClick(item.id)"
          >
            <div class="news_img_box">
              <img
                class="news_img"
                v-if="isHttp(item.picture)"
                :src="item.picture.split(',')[0]"
                alt=""
              />
              <img
                class="news_img"
                v-else
                :src="
                  item.picture ? $config.url + item.picture.split(',')[0] : ''
                "
                alt=""
              />
            </div>
            <div class="news_content">
              <div class="news_title">{{ item.title }}</div>
              <div class="news_text">{{ item.introduction }}</div>
              <div class="news_time">{{ item.addtime.split(' ')[0] }}</div>
            </div>
          </div>
        </div>
        <div class="news_more_view" @click="moreClick('news')">
          <span class="news_more_text">全部</span>
        </div>
      </div>
    </div>
    <formModel ref="newsFormModelRef"></formModel>
  </div>
</template>

<script setup>
import { ref, getCurrentInstance } from 'vue'
import { useRouter } from 'vue-router'
const context = getCurrentInstance()?.appContext.config.globalProperties
const router = useRouter()
//客房信息推荐
const kefangxinxiRecomList = ref([])
const getkefangxinxiRecomList = () => {
  let autoSortUrl = 'kefangxinxi/autoSort'
  if (context?.$toolUtil.storageGet('frontToken')) {
    autoSortUrl = 'kefangxinxi/autoSort2'
  }
  context
    ?.$http({
      url: autoSortUrl,
      method: 'get',
      params: {
        page: 1,
        limit: 6,
      },
    })
    .then((res) => {
      kefangxinxiRecomList.value = res.data.data.list
    })
}
//菜品信息推荐
const caipinxinxiRecomList = ref([])
const getcaipinxinxiRecomList = () => {
  let autoSortUrl = 'caipinxinxi/autoSort'
  context
    ?.$http({
      url: autoSortUrl,
      method: 'get',
      params: {
        page: 1,
        limit: 6,
      },
    })
    .then((res) => {
      caipinxinxiRecomList.value = res.data.data.list
    })
}
//新闻资讯弹窗
import formModel from './news/formModel'
const newsFormModelRef = ref(null)
//新闻资讯
const newsList = ref([])
const getNewsList = () => {
  context
    ?.$http({
      url: 'news/list',
      method: 'get',
      params: {
        page: 1,
        limit: 6,
      },
    })
    .then((res) => {
      newsList.value = res.data.data.list
    })
}
const newsDetailClick = (id = null) => {
  if (id) {
    newsFormModelRef.value.init(id)
  }
}
//判断图片链接是否带http
const isHttp = (str) => {
  return str && str.substr(0, 4) == 'http'
}
//跳转详情
const detailClick = (table, id) => {
  router.push(`/index/${table}Detail?id=${id}`)
}
const moreClick = (table) => {
  router.push(`/index/${table}List`)
}
const init = () => {
  //客房信息推荐
  getkefangxinxiRecomList()
  //菜品信息推荐
  getcaipinxinxiRecomList()
  //新闻资讯
  getNewsList()
}
init()
</script>

<style lang="scss">
.home_box {
  padding: 0;
  margin: 0 auto;
  background: #fff;
  display: flex;
  width: 100%;
  justify-content: space-between;
  align-items: flex-start;
  flex-wrap: wrap;
}

// 推荐
.recomList_view {
  padding: 20px 12% 0;
  margin: 0 auto;
  background: #fff;
  width: 100%;
  position: relative;
  order: 1;
  .recomList_title {
    padding: 0 0 10px;
    margin: 0px 0 0;
    color: #ef4238;
    font-weight: 500;
    width: auto;
    font-size: 26px;
    border-color: #2da065;
    border-width: 0 0 0px;
    border-style: solid;
    text-align: left;
  }
  // list
  .recommend_list_one {
    padding: 0;
    margin: 10px auto 0;
    display: flex;
    width: 100%;
    justify-content: space-between;
    flex-wrap: wrap;
    .recommend_item {
      cursor: pointer;
      border: 1px solid #eee;
      box-shadow: 0 0px 0px rgba(0, 0, 0, 0.1);
      padding: 0px;
      margin: 0 0px 30px;
      background: #fff;
      width: 15%;
    }
    .recommend_img_box {
      margin: 0 0 0px;
      width: 100%;
      font-size: 0;
      .recommend_img {
        object-fit: cover;
        width: 100%;
        height: 220px;
      }
    }
    .recommend_content {
      padding: 0 10px;
      display: block;
      justify-content: space-between;
      flex-wrap: wrap;
      .recommend_title {
        padding: 0 16px;
        margin: 0 0 0px;
        color: #ef4238;
        white-space: nowrap;
        font-weight: 500;
        font-size: 14px;
        line-height: 1.8;
        text-overflow: ellipsis;
        overflow: hidden;
        background: none;
        width: 100%;
        text-align: center;
        height: auto;
      }
      .recommend_price {
        color: #d46f6f;
        width: auto;
        font-size: 14px;
        line-height: 1.8;
        float: right;
        text-align: right;
        order: 2;
      }
      .recommend_one_bottom {
        padding: 0 0px;
        flex: 1;
        display: flex;
        width: auto;
        line-height: 1.5;
        justify-content: center;
        align-items: center;
        float: left;
        .recommend_like {
          margin: 0 10px 0 0;
          color: inherit;
          display: flex;
          font-size: 14px;
          align-items: center;
          .like_icon {
            margin: 0 4px 0 0;
            color: #0266b5;
          }
          .like_num {
            color: #999;
          }
        }
        .recommend_collect {
          margin: 0 10px 0 0;
          color: #999;
          display: flex;
          font-size: 14px;
          align-items: center;
          .el-icon {
            margin: 0 4px 0 0;
            color: #ee7810;
          }
          .collect_num {
            color: inherit;
          }
        }
        .recommend_clickNum {
          margin: 0 10px 0 0;
          color: #999;
          display: flex;
          font-size: 14px;
          align-items: center;
          .el-icon {
            margin: 0 4px 0 0;
            color: #4aac26;
          }
          .clickNum_num {
            color: inherit;
          }
        }
      }
    }
  }
  // list
  // animation
  .animation_box {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box:hover {
    box-shadow: 0 0 8px #fff, 0 5px 13px rgba(0, 0, 0, 0.07);
    transform: rotate(0deg) scale(1) skew(0deg, 0deg)
      translate3d(0px, -3px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  .animation_box img {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box img:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  // animation
  // 更多
  .recommend_more_view {
    cursor: pointer;
    border: 0px solid #ddd;
    padding: 0;
    margin: 20px auto;
    color: #fff;
    line-height: 34px;
    right: 12%;
    border-radius: 0px;
    top: 0;
    background: none;
    width: 120px;
    position: absolute;
    text-align: right;
    .recommend_more_text {
      padding: 0 16px 0 0;
      color: #ef4238;
      background: url(http://localhost:8080/homestayHotel/file/87790c3e4a1d4685a636389bf810fe43.png)
        no-repeat right center;
      font-size: 16px;
    }
  }
}
// 推荐
// 新闻资讯
.newsList_view {
  padding: 40px 12% 0px;
  margin: 0 auto;
  background: #fff;
  width: 100%;
  position: relative;
  order: 3;
  height: auto;

  .newsList_title {
    padding: 0px;
    margin: 0 auto 20px;
    color: #ffb400;
    background: none;
    font-weight: 500;
    width: auto;
    font-size: 26px;
    border-color: #999;
    border-width: 0 0 0px;
    border-style: dashed;
    text-align: left;
  }
  // list
  .news_list_one {
    padding: 0px;
    margin: 20px 0 0;
    background: none;
    display: flex;
    width: 100%;
    justify-content: space-between;
    flex-wrap: wrap;
    .news_item {
      cursor: pointer;
      padding: 0 0 20px;
      margin: 0 20px 40px 0;
      background: #fff;
      display: flex;
      width: calc(32% - 20px);
      align-items: center;
      border-bottom: 1px solid #eee;
      .news_img_box {
        overflow: hidden;
        width: 210px;
        height: 130px;
        .news_img {
          border: 0px solid #eee;
          object-fit: cover;
          width: 100%;
          height: 100%;
        }
      }
      .news_content {
        margin: 0 0 0 30px;
        display: flex;
        width: calc(100% - 240px);
        flex-wrap: wrap;
        .news_title {
          border: 0px solid #888;
          padding: 0px;
          margin: 0 0 10px;
          overflow: hidden;
          color: #000;
          white-space: nowrap;
          font-weight: 500;
          width: 100%;
          font-size: 16px;
          text-overflow: ellipsis;
          text-align: left;
          order: 2;
        }
        .news_text {
          padding: 0 10px 0 0;
          margin: 0 0 10px;
          overflow: hidden;
          color: #888;
          display: block;
          font-size: 14px;
          line-height: 24px;
          height: 48px;
          order: 3;
        }
        .news_time {
          color: #ff5200;
          font-weight: 500;
          display: block;
          width: 100%;
          text-align: left;
          order: 4;
        }
      }
    }
  }
  // list
  // animation
  .animation_box {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg)
      translate3d(0px, -3px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  .animation_box img {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box img:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: 0.3s;
  }
  // animation
  // 更多
  .news_more_view {
    cursor: pointer;
    border: 0px solid #ddd;
    border-radius: 0px;
    padding: 0px 0;
    margin: 16px auto 0;
    top: 26px;
    background: none;
    width: auto;
    line-height: 34px;
    position: absolute;
    right: 12%;
    text-align: center;
    .news_more_text {
      padding: 0 16px 0 0;
      color: #ffb400;
      background: url(http://localhost:8080/homestayHotel/file/f61ce3a2d75848c48d42317e08098c1d.png)
        no-repeat right center;
      display: inline-block;
      font-size: 16px;
    }
  }
}
// 新闻资讯
// 首页展示
.homeList_view {
  padding: 0px 12%;
  margin: 0px 0 0;
  background: #fff;
  width: 100%;
  position: relative;
  text-align: center;
  order: 2;

  .homeList_title {
    padding: 0px;
    margin: 0;
    color: #2d98f3;
    background: none;
    font-weight: 500;
    width: 100%;
    font-size: 26px;
    border-color: #eee;
    border-width: 0px;
    border-style: solid;
    text-align: left;
  }
  // list
  .home_list_five {
    margin: 20px 0 0;
    color: #ffb400;
    display: flex;
    width: 100%;
    line-height: 1.5;
    justify-content: space-between;
    align-items: flex-start;
    flex-wrap: wrap;
    .home_list_five_item1 {
      margin: 0 0 30px;
      width: 15%;
      transition: all 0.3s;
      .img_box {
        object-fit: cover;
        width: 100%;
        height: 200px;
        .home_img {
          width: 100%;
          height: 100%;
        }
      }
      .homeList_content {
        border: 1px solid #eee;
        padding: 10px;
        background: #fff;
        display: flex;
        flex-wrap: wrap;
        .homeList_title1 {
          width: 100%;
          text-align: center;
        }
        .homeList_title2 {
          width: 100%;
          text-align: center;
        }
        .homeList_title3 {
          width: 100%;
          text-align: center;
        }
        .homeList_title4 {
          width: 100%;
          text-align: center;
        }
        .homeList_title5 {
          width: 100%;
          text-align: center;
        }
      }
    }
    .home_list_five_item1:hover {
      .img_box {
        .home_img {
        }
      }
      .homeList_content {
        .homeList_title1 {
        }
        .homeList_title2 {
        }
        .homeList_title3 {
        }
        .homeList_title4 {
        }
        .homeList_title5 {
        }
      }
    }
    .home_list_five_item2 {
      margin: 0 0 30px;
      width: 15%;
      transition: all 0.3s;
      .img_box {
        object-fit: cover;
        width: 100%;
        height: 200px;
        .home_img {
          width: 100%;
          height: 100%;
        }
      }
      .homeList_content {
        border: 1px solid #eee;
        padding: 10px;
        background: #fff;
        display: flex;
        flex-wrap: wrap;
        .homeList_title1 {
          width: 100%;
          text-align: center;
        }
        .homeList_title2 {
          width: 100%;
          text-align: center;
        }
        .homeList_title3 {
          width: 100%;
          text-align: center;
        }
        .homeList_title4 {
          width: 100%;
          text-align: center;
        }
        .homeList_title5 {
          width: 100%;
          text-align: center;
        }
      }
    }
    .home_list_five_item2:hover {
      .img_box {
        .home_img {
        }
      }
      .homeList_content {
        .homeList_title1 {
        }
        .homeList_title2 {
        }
        .homeList_title3 {
        }
        .homeList_title4 {
        }
        .homeList_title5 {
        }
      }
    }
    .home_list_five_item3 {
      margin: 0 0 30px;
      width: 15%;
      transition: all 0.3s;
      .img_box {
        object-fit: cover;
        width: 100%;
        height: 200px;
        .home_img {
          width: 100%;
          height: 100%;
        }
      }
      .homeList_content {
        border: 1px solid #eee;
        padding: 10px;
        background: #fff;
        display: flex;
        flex-wrap: wrap;
        .homeList_title1 {
          width: 100%;
          text-align: center;
        }
        .homeList_title2 {
          width: 100%;
          text-align: center;
        }
        .homeList_title3 {
          width: 100%;
          text-align: center;
        }
        .homeList_title4 {
          width: 100%;
          text-align: center;
        }
        .homeList_title5 {
          width: 100%;
          text-align: center;
        }
      }
    }
    .home_list_five_item3:hover {
      .img_box {
        .home_img {
        }
      }
      .homeList_content {
        .homeList_title1 {
        }
        .homeList_title2 {
        }
        .homeList_title3 {
        }
        .homeList_title4 {
        }
        .homeList_title5 {
        }
      }
    }
    .home_list_five_item4 {
      margin: 0 0 30px;
      width: 15%;
      transition: all 0.3s;
      .img_box {
        object-fit: cover;
        width: 100%;
        height: 200px;
        .home_img {
          width: 100%;
          height: 100%;
        }
      }
      .homeList_content {
        border: 1px solid #eee;
        padding: 10px;
        background: #fff;
        display: flex;
        flex-wrap: wrap;
        .homeList_title1 {
          width: 100%;
          text-align: center;
        }
        .homeList_title2 {
          width: 100%;
          text-align: center;
        }
        .homeList_title3 {
          width: 100%;
          text-align: center;
        }
        .homeList_title4 {
          width: 100%;
          text-align: center;
        }
        .homeList_title5 {
          width: 100%;
          text-align: center;
        }
      }
    }
    .home_list_five_item4:hover {
      .img_box {
        .home_img {
        }
      }
      .homeList_content {
        .homeList_title1 {
        }
        .homeList_title2 {
        }
        .homeList_title3 {
        }
        .homeList_title4 {
        }
        .homeList_title5 {
        }
      }
    }
    .home_list_five_item5 {
      margin: 0 0 30px;
      width: 15%;
      .img_box {
        object-fit: cover;
        width: 100%;
        height: 200px;
        .home_img {
          width: 100%;
          height: 100%;
        }
      }
      .homeList_content {
        border: 1px solid #eee;
        padding: 10px;
        background: #fff;
        display: flex;
        flex-wrap: wrap;
        .homeList_title1 {
          width: 100%;
          text-align: center;
        }
        .homeList_title2 {
          width: 100%;
          text-align: center;
        }
        .homeList_title3 {
          width: 100%;
          text-align: center;
        }
        .homeList_title4 {
          width: 100%;
          text-align: center;
        }
        .homeList_title5 {
          width: 100%;
          text-align: center;
        }
      }
    }
    .home_list_five_item5:hover {
      .img_box {
        .home_img {
        }
      }
      .homeList_content {
        .homeList_title1 {
        }
        .homeList_title2 {
        }
        .homeList_title3 {
        }
        .homeList_title4 {
        }
        .homeList_title5 {
        }
      }
    }
    .home_list_five_item6 {
      margin: 0;
      width: 15%;
      .img_box {
        object-fit: cover;
        width: 100%;
        height: 200px;
        .home_img {
          width: 100%;
          height: 100%;
        }
      }
      .homeList_content {
        border: 1px solid #eee;
        padding: 10px;
        background: #fff;
        display: flex;
        flex-wrap: wrap;
        .homeList_title1 {
          width: 100%;
          text-align: center;
        }
        .homeList_title2 {
          width: 100%;
          text-align: center;
        }
        .homeList_title3 {
          width: 100%;
          text-align: center;
        }
        .homeList_title4 {
          width: 100%;
          text-align: center;
        }
        .homeList_title5 {
          width: 100%;
          text-align: center;
        }
      }
    }
    .home_list_five_item6:hover {
      .img_box {
        .home_img {
        }
      }
      .homeList_content {
        .homeList_title1 {
        }
        .homeList_title2 {
        }
        .homeList_title3 {
        }
        .homeList_title4 {
        }
        .homeList_title5 {
        }
      }
    }
    .home_list_five_item7 {
      margin: 0 0 30px;
      width: 15%;
      .img_box {
        object-fit: cover;
        width: 100%;
        height: 200px;
        .home_img {
          width: 100%;
          height: 100%;
        }
      }
      .homeList_content {
        border: 1px solid #eee;
        padding: 10px;
        background: #fff;
        display: flex;
        flex-wrap: wrap;
        .homeList_title1 {
          width: 100%;
          text-align: center;
        }
        .homeList_title2 {
          width: 100%;
          text-align: center;
        }
        .homeList_title3 {
          width: 100%;
          text-align: center;
        }
        .homeList_title4 {
          width: 100%;
          text-align: center;
        }
        .homeList_title5 {
          width: 100%;
          text-align: center;
        }
      }
    }
    .home_list_five_item7:hover {
      .img_box {
        .home_img {
        }
      }
      .homeList_content {
        .homeList_title1 {
        }
        .homeList_title2 {
        }
        .homeList_title3 {
        }
        .homeList_title4 {
        }
        .homeList_title5 {
        }
      }
    }
    .home_list_five_item8 {
      margin: 0 0 30px;
      width: 15%;
      .img_box {
        object-fit: cover;
        width: 100%;
        height: 200px;
        .home_img {
          width: 100%;
          height: 100%;
        }
      }
      .homeList_content {
        border: 1px solid #eee;
        padding: 10px;
        background: #fff;
        display: flex;
        flex-wrap: wrap;
        .homeList_title1 {
          width: 100%;
          text-align: center;
        }
        .homeList_title2 {
          width: 100%;
          text-align: center;
        }
        .homeList_title3 {
          width: 100%;
          text-align: center;
        }
        .homeList_title4 {
          width: 100%;
          text-align: center;
        }
        .homeList_title5 {
          width: 100%;
          text-align: center;
        }
      }
    }
    .home_list_five_item8:hover {
      .img_box {
        .home_img {
        }
      }
      .homeList_content {
        .homeList_title1 {
        }
        .homeList_title2 {
        }
        .homeList_title3 {
        }
        .homeList_title4 {
        }
        .homeList_title5 {
        }
      }
    }
  }
  // list
  // animation
  .animation_box {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box:hover {
    box-shadow: 0 0 8px #fff, 0 5px 13px rgba(0, 0, 0, 0.07);
    transform: rotate(3deg) scale(0.96) skew(0deg, 0deg)
      translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: all 0.3s;
  }
  .animation_box img {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    z-index: initial;
  }
  .animation_box img:hover {
    transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
    -webkit-perspective: 1000px;
    perspective: 1000px;
    transition: all 0.3s;
  }
  // animation
  // 更多
  .homeList_more_view {
    cursor: pointer;
    padding: 0;
    margin: 8px auto 0;
    top: 0px;
    background: none;
    display: inline-block;
    width: auto;
    position: absolute;
    right: 12%;
    text-align: center;
    .homeList_more_text {
      padding: 0 16px 0 0;
      color: #2d98f3;
      background: url(http://localhost:8080/homestayHotel/file/7e9d18d133854d009b2ed0fe4be90205.png)
        no-repeat right center;
      display: inline-block;
      font-size: 16px;
    }
  }
}
// 首页展示
</style>
