package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.CaiwubuEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.CaiwubuView;


/**
 * 财务部
 *
 * @author 
 * @email 
 * @date 2024-01-19 11:28:04
 */
public interface CaiwubuService extends IService<CaiwubuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CaiwubuView> selectListView(Wrapper<CaiwubuEntity> wrapper);
   	
   	CaiwubuView selectView(@Param("ew") Wrapper<CaiwubuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CaiwubuEntity> wrapper);
   	

}
