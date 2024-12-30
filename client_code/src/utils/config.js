const config = {
    get() {
        return {
            url : process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
            name: process.env.VUE_APP_BASE_API,
			menuList:[
				{
					name: '客房信息管理',
					icon: '${frontMenu.fontClass}',
					child:[
						{
							name:'客房信息',
							url:'/index/kefangxinxiList'
						},
					]
				},
				{
					name: '菜品信息管理',
					icon: '${frontMenu.fontClass}',
					child:[
						{
							name:'菜品信息',
							url:'/index/caipinxinxiList'
						},
					]
				},
				{
					name: '新闻资讯管理',
					icon: '${frontMenu.fontClass}',
					child:[
						{
							name:'新闻资讯',
							url:'/index/newsList'
						},
					]
				},
			]
        }
    },
    getProjectName(){
        return {
            projectName: "民宿酒店管理和设计系统的实现"
        } 
    }
}
export default config
