package com.cl.dao;

import com.cl.entity.KefangbuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.KefangbuView;


/**
 * 客房部
 * 
 * @author 
 * @email 
 * @date 2024-01-19 11:28:04
 */
public interface KefangbuDao extends BaseMapper<KefangbuEntity> {
	
	List<KefangbuView> selectListView(@Param("ew") Wrapper<KefangbuEntity> wrapper);

	List<KefangbuView> selectListView(Pagination page,@Param("ew") Wrapper<KefangbuEntity> wrapper);
	
	KefangbuView selectView(@Param("ew") Wrapper<KefangbuEntity> wrapper);
	

}
