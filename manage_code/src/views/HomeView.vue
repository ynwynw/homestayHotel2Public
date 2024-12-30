<template>
	<div class="home_view">
		<div class="projectTitle">欢迎使用 {{projectName}}</div>
		<div class="count_list">
			<el-collapse-transition v-if="btnAuth('kefangyuding','首页总数')">
				<el-card v-show="countTypeList.closekefangyudingCountType" class="card_view">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								客房预订
							</div>
							<div class="card_head_right">
								<el-icon @click="countTypeClick('hiddenkefangyudingCountType')" class="showIcons"
									:class="countTypeList.hiddenkefangyudingCountType?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="countTypeClick('closekefangyudingCountType')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
							
						</div>
					</template>
					<el-collapse-transition>
						<div class="count_item" v-show="countTypeList.hiddenkefangyudingCountType">
							<div class="count_title">客房预订总数</div>
							<div class="count_num">{{kefangyudingCount}}</div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
			<el-collapse-transition v-if="btnAuth('diancanxinxi','首页总数')">
				<el-card v-show="countTypeList.closediancanxinxiCountType" class="card_view">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								点餐信息
							</div>
							<div class="card_head_right">
								<el-icon @click="countTypeClick('hiddendiancanxinxiCountType')" class="showIcons"
									:class="countTypeList.hiddendiancanxinxiCountType?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="countTypeClick('closediancanxinxiCountType')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
							
						</div>
					</template>
					<el-collapse-transition>
						<div class="count_item" v-show="countTypeList.hiddendiancanxinxiCountType">
							<div class="count_title">点餐信息总数</div>
							<div class="count_num">{{diancanxinxiCount}}</div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
		</div>
		<div class="card_list">
			<el-collapse-transition v-if="btnAuth('kefangyuding','首页统计')">
				<el-card v-show="cardTypeList.closekefangyudingChartType1" class="card_view">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								客房预订
							</div>
							<div class="card_head_right">
								<el-icon @click="cardTypeClick('hiddenkefangyudingChartType1')" class="showIcons"
									:class="cardTypeList.hiddenkefangyudingChartType1?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="cardTypeClick('closekefangyudingChartType1')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
						</div>
					</template>
					<el-collapse-transition>
						<div class="card_item" v-show="cardTypeList.hiddenkefangyudingChartType1">
							<div id="kefangyudingzongjiageEchart1" style="width: 100%;height: 400px;"></div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
			<el-collapse-transition v-if="btnAuth('diancanxinxi','首页统计')">
				<el-card v-show="cardTypeList.closediancanxinxiChartType1" class="card_view">
					<template #header>
						<div class="index_card_head">
							<div class="card_head_title">
								点餐信息
							</div>
							<div class="card_head_right">
								<el-icon @click="cardTypeClick('hiddendiancanxinxiChartType1')" class="showIcons"
									:class="cardTypeList.hiddendiancanxinxiChartType1?'showIcons1':''">
									<ArrowUpBold />
								</el-icon>
								<el-icon @click="cardTypeClick('closediancanxinxiChartType1')" class="closeIcons">
									<CloseBold />
								</el-icon>
							</div>
						</div>
					</template>
					<el-collapse-transition>
						<div class="card_item" v-show="cardTypeList.hiddendiancanxinxiChartType1">
							<div id="diancanxinxizongjiaEchart1" style="width: 100%;height: 400px;"></div>
						</div>
					</el-collapse-transition>
				</el-card>
			</el-collapse-transition>
		</div>
	</div>
</template>

<script setup>
	import {
		inject,
		nextTick,
		ref,
		getCurrentInstance
	} from 'vue';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const projectName = context.$project.projectName
	const countTypeList = ref({
	})
	const getCountList=()=>{
		countTypeList.value.closekefangyudingCountType = true
		countTypeList.value.hiddenkefangyudingCountType = true
		if(btnAuth('kefangyuding','首页总数')){
			getkefangyudingCount()
		}
		countTypeList.value.closediancanxinxiCountType = true
		countTypeList.value.hiddendiancanxinxiCountType = true
		if(btnAuth('diancanxinxi','首页总数')){
			getdiancanxinxiCount()
		}
	}
	const kefangyudingCount = ref(0)
	const getkefangyudingCount = () => {
		context?.$http({
			url:'kefangyuding/count',
			method: 'get'
		}).then(res=>{
			kefangyudingCount.value = res.data.data
		})
	}
	const diancanxinxiCount = ref(0)
	const getdiancanxinxiCount = () => {
		context?.$http({
			url:'diancanxinxi/count',
			method: 'get'
		}).then(res=>{
			diancanxinxiCount.value = res.data.data
		})
	}
	const countTypeClick = (e) => {
		countTypeList.value[e] = !countTypeList.value[e]
	}
	const init=()=>{
		getCountList()
		getCardList()
	}
	//权限验证
	const btnAuth = (e,a)=>{
		return context?.$toolUtil.isAuth(e,a)
	}
	let echarts = inject("echarts")
	const cardTypeClick = (e) =>{
		cardTypeList.value[e] = !cardTypeList.value[e]
		setTimeout(()=>{
			getCardList()
		},1000)
	}
	const cardTypeList = ref({
		closekefangyudingChartType1: true,
		hiddenkefangyudingChartType1: true,
		closediancanxinxiChartType1: true,
		hiddendiancanxinxiChartType1: true,
	})
	const getCardList = () => {
		if(btnAuth('kefangyuding','首页统计')){
			getkefangyudingChart1()
		}
		if(btnAuth('diancanxinxi','首页统计')){
			getdiancanxinxiChart1()
		}
	}
	const getkefangyudingChart1 = () => {
		nextTick(()=>{
			var zongjiageEchart1 = echarts.init(document.getElementById("kefangyudingzongjiageEchart1"),'macarons');
			context?.$http({
				url: `kefangyuding/value/ruzhuriqi/zongjiage`,
				method: "get",
			}).then(obj=>{
				let res = obj.data.data
				let xAxis = [];
				let yAxis = [];
				let pArray = []
				for(let i=0;i<res.length;i++){
				    xAxis.push(res[i].ruzhuriqi);
				    yAxis.push(parseFloat((res[i].total)));
				    pArray.push({
				        value: parseFloat((res[i].total)),
				        name: res[i].ruzhuriqi
				    })
				}
				var option = {};
                option = {
                    title: {
                        text: '客房收入',
                        left: 'center'
                    },
                    tooltip: {
                      trigger: 'item',
                      formatter: '{b} : {c}'
                    },
                    xAxis: {
                        type: 'category',
                        data: xAxis,
                        axisLabel: {
                            rotate: 40
                        }
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: yAxis,
                        type: 'bar'
                    }]
                };
				zongjiageEchart1.clear()
				// 使用刚指定的配置项和数据显示图表。
				zongjiageEchart1.setOption(option);
				//根据窗口的大小变动图表
				zongjiageEchart1.resize();
			})
		})
	}
	const getdiancanxinxiChart1 = () => {
		nextTick(()=>{
			var zongjiaEchart1 = echarts.init(document.getElementById("diancanxinxizongjiaEchart1"),'macarons');
			context?.$http({
				url: `diancanxinxi/value/diancanshijian/zongjia`,
				method: "get",
			}).then(obj=>{
				let res = obj.data.data
				let xAxis = [];
				let yAxis = [];
				let pArray = []
				for(let i=0;i<res.length;i++){
				    xAxis.push(res[i].diancanshijian);
				    yAxis.push(parseFloat((res[i].total)));
				    pArray.push({
				        value: parseFloat((res[i].total)),
				        name: res[i].diancanshijian
				    })
				}
				var option = {};
                option = {
                    title: {
                        text: '餐饮收入',
                        left: 'center'
                    },
                    tooltip: {
                      trigger: 'item',
                      formatter: '{b} : {c}'
                    },
                    xAxis: {
                        type: 'category',
                        data: xAxis,
                        axisLabel: {
                            rotate: 40
                        }
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: yAxis,
                        type: 'bar'
                    }]
                };
				zongjiaEchart1.clear()
				// 使用刚指定的配置项和数据显示图表。
				zongjiaEchart1.setOption(option);
				//根据窗口的大小变动图表
				zongjiaEchart1.resize();
			})
		})
	}
	init()
</script>
<style lang="scss">
	.projectTitle{
		padding: 20px 0;
		margin: 20px 0 20px;
		color: #4F1904;
		font-weight: bold;
		display: flex;
		width: 100%;
		font-size: 22px;
		justify-content: center;
		align-items: center;
		height: auto;
	}

	.showIcons {
		transition: transform 0.3s;
		margin-right: 10px;
	}

	.showIcons1 {
		transform: rotate(-180deg);
	}
	
	// 总数盒子
	.count_list{
		padding: 0 0 20px;
		grid-column-gap: 20px;
		display: flex;
		width: 100%;
		justify-content: center;
		align-items: flex-start;
		flex-wrap: wrap;
		// 总数card
		.card_view {
			border: 1px solid #ddd;
			box-shadow: 0px 0px 0px rgba(0,0,0,.12);
			padding-top: 60px;
			flex: 1;
			background: linear-gradient(90deg,#FFFAF2, #FDE9CB);
			width: 100%;
			position: relative;
			box-sizing: border-box;
			height: auto;
			// card头部
			.el-card__header {
				border-radius: 20px 0 0 20px;
				padding: 0 20px;
				top: 20px;
				background: #EED6B4;
				line-height: 40px;
				position: absolute;
				right: 0;
				border-bottom: none;
				// 头部盒子
				.index_card_head {
					display: flex;
					width: 100%;
					justify-content: space-between;
					align-items: center;
					// 标题
					.card_head_title {
						color: #313131;
						font-size: 14px;
						margin-right: 40px;
					}
					// 按钮盒子
					.card_head_right {
						display: flex;
						align-items: center;
						// 按钮
						.el-icon {
							cursor: pointer;
							color: #313131;
							font-weight: 700;
							font-size: 14px;
						}
					}
				}
			}
			// body
			.el-card__body {
				padding: 0;
				// body盒子
				.count_item{
					padding: 30px;
					text-align: center;
					// 总数标题
					.count_title{
						color: #9E9E9E;
						font-size: 18px;
						line-height: 2;
						text-align: left;
					}
					// 总数数字
					.count_num{
						color: #E7B668;
						font-weight: 700;
						font-size: 64px;
						line-height: 1;
						text-align: left;
					}
				}
			}
		}
	}
	// 首页盒子
	.home_view {
		padding: 0px 30px;
		margin: 126px 0 0;
		background: #F9F9F9;
		min-height: 100vh;
		height: auto;
	}
	// 统计图盒子
	.card_list {
		padding: 0 0 20px;
		display: flex;
		width: 100%;
		justify-content: space-between;
		align-items: flex-start;
		flex-wrap: wrap;
		// 统计图card
		.card_view {
			border: 1px solid #ddd;
			box-shadow: 0px 0px 0px rgba(0,0,0,.12);
			padding-top: 60px;
			margin: 0 1% 30px;
			width: 48%;
			position: relative;
			box-sizing: border-box;
			height: auto;
			// 头部
			.el-card__header {
				border-radius: 20px 0px 0px 20px;
				padding: 0px 20px;
				top: 20px;
				background: rgb(238, 214, 180);
				line-height: 40px;
				position: absolute;
				right: 0;
				border-bottom: none;
				// 头部盒子
				.index_card_head {
					display: flex;
					justify-content: space-between;
					align-items: center;
					// 标题
					.card_head_title {
						color: #313131;
						font-size: 14px;
					}
					// 按钮盒子
					.card_head_right {
						display: flex;
						align-items: center;
						// 按钮
						.el-icon{
							cursor: pointer;
							color: #313131;
							font-weight: 700;
							font-size: 14px;
						}
					}
				}
			}
			// body
			.el-card__body {
				padding: 0;
				// body盒子
				.card_item{
					padding: 30px;
					text-align: center;
				}
			}
		}
	}
</style>
