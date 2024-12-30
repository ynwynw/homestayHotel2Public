package com.cl.dao;

import com.cl.entity.CaiwubuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.CaiwubuView;


/**
 * 财务部
 * 
 * @author 
 * @email 
 * @date 2024-01-19 11:28:04
 */
public interface CaiwubuDao extends BaseMapper<CaiwubuEntity> {
	
	List<CaiwubuView> selectListView(@Param("ew") Wrapper<CaiwubuEntity> wrapper);

	List<CaiwubuView> selectListView(Pagination page,@Param("ew") Wrapper<CaiwubuEntity> wrapper);
	
	CaiwubuView selectView(@Param("ew") Wrapper<CaiwubuEntity> wrapper);
	

}
