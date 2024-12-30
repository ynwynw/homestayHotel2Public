package com.cl.dao;

import com.cl.entity.CanyinbuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.CanyinbuView;


/**
 * 餐饮部
 * 
 * @author 
 * @email 
 * @date 2024-01-19 11:28:04
 */
public interface CanyinbuDao extends BaseMapper<CanyinbuEntity> {
	
	List<CanyinbuView> selectListView(@Param("ew") Wrapper<CanyinbuEntity> wrapper);

	List<CanyinbuView> selectListView(Pagination page,@Param("ew") Wrapper<CanyinbuEntity> wrapper);
	
	CanyinbuView selectView(@Param("ew") Wrapper<CanyinbuEntity> wrapper);
	

}
