const config = {
  get() {
    return {
      url:
        process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
      name: process.env.VUE_APP_BASE_API,
      // 退出到首页链接
      indexUrl: 'http://localhost:8080/homestayHotel/front/index.html',
    }
  },
  getProjectName() {
    return {
      projectName: '民宿酒店管理和设计系统的实现',
    }
  },
}
export default config
