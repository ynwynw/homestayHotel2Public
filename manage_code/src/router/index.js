	import {
		createRouter,
		createWebHashHistory
	} from 'vue-router'
	import news from '@/views/news/list'
	import kefangyuding from '@/views/kefangyuding/list'
	import canyinbu from '@/views/canyinbu/list'
	import kefangxinxi from '@/views/kefangxinxi/list'
	import kefangleixing from '@/views/kefangleixing/list'
	import tuifangxinxi from '@/views/tuifangxinxi/list'
	import kefangbu from '@/views/kefangbu/list'
	import caipinxinxi from '@/views/caipinxinxi/list'
	import diancanxinxi from '@/views/diancanxinxi/list'
	import caipinfenlei from '@/views/caipinfenlei/list'
	import caiwubu from '@/views/caiwubu/list'
	import discusskefangxinxi from '@/views/discusskefangxinxi/list'
	import yonghu from '@/views/yonghu/list'
	import config from '@/views/config/list'
	import discusscaipinxinxi from '@/views/discusscaipinxinxi/list'
	import canyinbuRegister from '@/views/canyinbu/register'
	import canyinbuCenter from '@/views/canyinbu/center'
	import kefangbuRegister from '@/views/kefangbu/register'
	import kefangbuCenter from '@/views/kefangbu/center'
	import caiwubuRegister from '@/views/caiwubu/register'
	import caiwubuCenter from '@/views/caiwubu/center'

export const routes = [{
		path: '/login',
		name: 'login',
		component: () => import('../views/login.vue')
	},{
		path: '/',
		name: '首页',
		component: () => import('../views/index'),
		children: [{
			path: '/',
			name: '首页',
			component: () => import('../views/HomeView.vue'),
			meta: {
				affix: true
			}
		}, {
			path: '/updatepassword',
			name: '修改密码',
			component: () => import('../views/updatepassword.vue')
		}
		
		,{
			path: '/canyinbuCenter',
			name: '餐饮部个人中心',
			component: canyinbuCenter
		}
		,{
			path: '/kefangbuCenter',
			name: '客房部个人中心',
			component: kefangbuCenter
		}
		,{
			path: '/caiwubuCenter',
			name: '财务部个人中心',
			component: caiwubuCenter
		}
		,{
			path: '/news',
			name: '新闻资讯',
			component: news
		}
		,{
			path: '/kefangyuding',
			name: '客房预订',
			component: kefangyuding
		}
		,{
			path: '/canyinbu',
			name: '餐饮部',
			component: canyinbu
		}
		,{
			path: '/kefangxinxi',
			name: '客房信息',
			component: kefangxinxi
		}
		,{
			path: '/kefangleixing',
			name: '客房类型',
			component: kefangleixing
		}
		,{
			path: '/tuifangxinxi',
			name: '退房信息',
			component: tuifangxinxi
		}
		,{
			path: '/kefangbu',
			name: '客房部',
			component: kefangbu
		}
		,{
			path: '/caipinxinxi',
			name: '菜品信息',
			component: caipinxinxi
		}
		,{
			path: '/diancanxinxi',
			name: '点餐信息',
			component: diancanxinxi
		}
		,{
			path: '/caipinfenlei',
			name: '菜品分类',
			component: caipinfenlei
		}
		,{
			path: '/caiwubu',
			name: '财务部',
			component: caiwubu
		}
		,{
			path: '/discusskefangxinxi',
			name: '客房信息评论',
			component: discusskefangxinxi
		}
		,{
			path: '/yonghu',
			name: '用户',
			component: yonghu
		}
		,{
			path: '/config',
			name: '轮播图',
			component: config
		}
		,{
			path: '/discusscaipinxinxi',
			name: '菜品信息评论',
			component: discusscaipinxinxi
		}
		]
	},
	{
		path: '/canyinbuRegister',
		name: '餐饮部注册',
		component: canyinbuRegister
	},
	{
		path: '/kefangbuRegister',
		name: '客房部注册',
		component: kefangbuRegister
	},
	{
		path: '/caiwubuRegister',
		name: '财务部注册',
		component: caiwubuRegister
	},
]

const router = createRouter({
	history: createWebHashHistory(process.env.BASE_URL),
	routes
})

export default router
