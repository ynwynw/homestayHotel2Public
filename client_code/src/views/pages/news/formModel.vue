<template>
	<div>
		<el-dialog v-model="formVisible" :title="formTitle" width="60%" destroy-on-close>
			<div class="news_detail">
				<div class="news_detail_title">{{detail.title}}</div>
				<div class="news_detail_time">发布时间：{{detail.addtime}}</div>
				<div class="news_detail_content" v-html="detail.content"></div>
			</div>
			<template #footer>
				<span class="dialog-footer">
					<el-button @click="formVisible=false">关闭</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>
<script setup>
	import {
		ref,
		nextTick,
		getCurrentInstance,
		defineEmits
	} from 'vue';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const id = ref(0)
	const detail = ref({})
	const formRef = ref(null)
	const formVisible = ref(false)
	const formTitle = ref('')
	const resetForm = () => {
		detail.value = {}
	}
	//初始化
	const init = (refid=null) => {
		resetForm()
		if(refid){
			id.value = refid
			getInfo()
		}
		formTitle.value = '查看新闻资讯'
		formVisible.value = true
	}
	//声明父级调用
	defineExpose({
		init
	})
	//回调父级方法
	const getInfo = () => {
		context?.$http({
			url: `news/detail/${id.value}`,
			method: 'get'
		}).then(res => {
			detail.value = res.data.data
			formVisible.value = true
		})
	}
</script>
<style>
	.news_detail {
		padding: 0px;
		background: #fff;
		width: 100%;
		.news_detail_title {
			padding: 10px 20px;
			color: #3e3e3e;
			background: none;
			font-weight: 500;
			font-size: 18px;
			border-color: #eee;
			border-width: 0 0 1px;
			border-style: solid;
			text-align: center;
		}
	
		.news_detail_time {
			padding: 10px 20px 0;
			color: #999;
			font-size: 13px;
			text-align: left;
		}
	
		.news_detail_content {
			border: 0px solid #ddd;
			padding: 20px;
			margin: 0px 0 0;
			text-indent: 2em;
			background: #fff;
			line-height: 24px;
			text-align: left;
		}
	}
</style>