package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.CanyinbuEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.CanyinbuView;


/**
 * 餐饮部
 *
 * @author 
 * @email 
 * @date 2024-01-19 11:28:04
 */
public interface CanyinbuService extends IService<CanyinbuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CanyinbuView> selectListView(Wrapper<CanyinbuEntity> wrapper);
   	
   	CanyinbuView selectView(@Param("ew") Wrapper<CanyinbuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CanyinbuEntity> wrapper);
   	

}

