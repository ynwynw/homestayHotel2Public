import { createRouter, createWebHashHistory } from 'vue-router'
import index from '../views'
import home from '../views/pages/home.vue'
import login from '../views/pages/login.vue'
import canyinbuList from '@/views/pages/canyinbu/list'
import canyinbuDetail from '@/views/pages/canyinbu/formModel'
import canyinbuAdd from '@/views/pages/canyinbu/formAdd'
import kefangbuList from '@/views/pages/kefangbu/list'
import kefangbuDetail from '@/views/pages/kefangbu/formModel'
import kefangbuAdd from '@/views/pages/kefangbu/formAdd'
import caiwubuList from '@/views/pages/caiwubu/list'
import caiwubuDetail from '@/views/pages/caiwubu/formModel'
import caiwubuAdd from '@/views/pages/caiwubu/formAdd'
import yonghuList from '@/views/pages/yonghu/list'
import yonghuDetail from '@/views/pages/yonghu/formModel'
import yonghuAdd from '@/views/pages/yonghu/formAdd'
import yonghuRegister from '@/views/pages/yonghu/register'
import yonghuCenter from '@/views/pages/yonghu/center'
import newsList from '@/views/pages/news/list'
import kefangleixingList from '@/views/pages/kefangleixing/list'
import kefangleixingDetail from '@/views/pages/kefangleixing/formModel'
import kefangleixingAdd from '@/views/pages/kefangleixing/formAdd'
import kefangxinxiList from '@/views/pages/kefangxinxi/list'
import kefangxinxiDetail from '@/views/pages/kefangxinxi/formModel'
import kefangxinxiAdd from '@/views/pages/kefangxinxi/formAdd'
import storeupList from '@/views/pages/storeup/list'
import kefangyudingList from '@/views/pages/kefangyuding/list'
import kefangyudingDetail from '@/views/pages/kefangyuding/formModel'
import kefangyudingAdd from '@/views/pages/kefangyuding/formAdd'
import tuifangxinxiList from '@/views/pages/tuifangxinxi/list'
import tuifangxinxiDetail from '@/views/pages/tuifangxinxi/formModel'
import tuifangxinxiAdd from '@/views/pages/tuifangxinxi/formAdd'
import caipinfenleiList from '@/views/pages/caipinfenlei/list'
import caipinfenleiDetail from '@/views/pages/caipinfenlei/formModel'
import caipinfenleiAdd from '@/views/pages/caipinfenlei/formAdd'
import caipinxinxiList from '@/views/pages/caipinxinxi/list'
import caipinxinxiDetail from '@/views/pages/caipinxinxi/formModel'
import caipinxinxiAdd from '@/views/pages/caipinxinxi/formAdd'
import diancanxinxiList from '@/views/pages/diancanxinxi/list'
import diancanxinxiDetail from '@/views/pages/diancanxinxi/formModel'
import diancanxinxiAdd from '@/views/pages/diancanxinxi/formAdd'
import menuList from '@/views/pages/menu/list'
import menuDetail from '@/views/pages/menu/formModel'
import menuAdd from '@/views/pages/menu/formAdd'
import discusskefangxinxiList from '@/views/pages/discusskefangxinxi/list'
import discusskefangxinxiDetail from '@/views/pages/discusskefangxinxi/formModel'
import discusskefangxinxiAdd from '@/views/pages/discusskefangxinxi/formAdd'
import discusscaipinxinxiList from '@/views/pages/discusscaipinxinxi/list'
import discusscaipinxinxiDetail from '@/views/pages/discusscaipinxinxi/formModel'
import discusscaipinxinxiAdd from '@/views/pages/discusscaipinxinxi/formAdd'

const routes = [{
		path: '/',
		redirect: '/index/home'
	},
	{
		path: '/index',
		component: index,
		children: [{
			path: 'home',
			component: home
		}
		, {
			path: 'canyinbuList',
			component: canyinbuList
		}, {
			path: 'canyinbuDetail',
			component: canyinbuDetail
		}, {
			path: 'canyinbuAdd',
			component: canyinbuAdd
		}
		, {
			path: 'kefangbuList',
			component: kefangbuList
		}, {
			path: 'kefangbuDetail',
			component: kefangbuDetail
		}, {
			path: 'kefangbuAdd',
			component: kefangbuAdd
		}
		, {
			path: 'caiwubuList',
			component: caiwubuList
		}, {
			path: 'caiwubuDetail',
			component: caiwubuDetail
		}, {
			path: 'caiwubuAdd',
			component: caiwubuAdd
		}
		, {
			path: 'yonghuList',
			component: yonghuList
		}, {
			path: 'yonghuDetail',
			component: yonghuDetail
		}, {
			path: 'yonghuAdd',
			component: yonghuAdd
		}
		, {
			path: 'yonghuCenter',
			component: yonghuCenter
		}
		, {
			path: 'newsList',
			component: newsList
		}
		, {
			path: 'kefangleixingList',
			component: kefangleixingList
		}, {
			path: 'kefangleixingDetail',
			component: kefangleixingDetail
		}, {
			path: 'kefangleixingAdd',
			component: kefangleixingAdd
		}
		, {
			path: 'kefangxinxiList',
			component: kefangxinxiList
		}, {
			path: 'kefangxinxiDetail',
			component: kefangxinxiDetail
		}, {
			path: 'kefangxinxiAdd',
			component: kefangxinxiAdd
		}
		, {
			path: 'storeupList',
			component: storeupList
		}
		, {
			path: 'kefangyudingList',
			component: kefangyudingList
		}, {
			path: 'kefangyudingDetail',
			component: kefangyudingDetail
		}, {
			path: 'kefangyudingAdd',
			component: kefangyudingAdd
		}
		, {
			path: 'tuifangxinxiList',
			component: tuifangxinxiList
		}, {
			path: 'tuifangxinxiDetail',
			component: tuifangxinxiDetail
		}, {
			path: 'tuifangxinxiAdd',
			component: tuifangxinxiAdd
		}
		, {
			path: 'caipinfenleiList',
			component: caipinfenleiList
		}, {
			path: 'caipinfenleiDetail',
			component: caipinfenleiDetail
		}, {
			path: 'caipinfenleiAdd',
			component: caipinfenleiAdd
		}
		, {
			path: 'caipinxinxiList',
			component: caipinxinxiList
		}, {
			path: 'caipinxinxiDetail',
			component: caipinxinxiDetail
		}, {
			path: 'caipinxinxiAdd',
			component: caipinxinxiAdd
		}
		, {
			path: 'diancanxinxiList',
			component: diancanxinxiList
		}, {
			path: 'diancanxinxiDetail',
			component: diancanxinxiDetail
		}, {
			path: 'diancanxinxiAdd',
			component: diancanxinxiAdd
		}
		, {
			path: 'menuList',
			component: menuList
		}, {
			path: 'menuDetail',
			component: menuDetail
		}, {
			path: 'menuAdd',
			component: menuAdd
		}
		, {
			path: 'discusskefangxinxiList',
			component: discusskefangxinxiList
		}, {
			path: 'discusskefangxinxiDetail',
			component: discusskefangxinxiDetail
		}, {
			path: 'discusskefangxinxiAdd',
			component: discusskefangxinxiAdd
		}
		, {
			path: 'discusscaipinxinxiList',
			component: discusscaipinxinxiList
		}, {
			path: 'discusscaipinxinxiDetail',
			component: discusscaipinxinxiDetail
		}, {
			path: 'discusscaipinxinxiAdd',
			component: discusscaipinxinxiAdd
		}
		]
	},
	{
		path: '/login',
		component: login
	}
	,{
		path: '/yonghuRegister',
		component: yonghuRegister
	}
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router
