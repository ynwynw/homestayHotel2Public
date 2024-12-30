package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.KefangbuEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.KefangbuView;


/**
 * 客房部
 *
 * @author 
 * @email 
 * @date 2024-01-19 11:28:04
 */
public interface KefangbuService extends IService<KefangbuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KefangbuView> selectListView(Wrapper<KefangbuEntity> wrapper);
   	
   	KefangbuView selectView(@Param("ew") Wrapper<KefangbuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<KefangbuEntity> wrapper);
   	

}

