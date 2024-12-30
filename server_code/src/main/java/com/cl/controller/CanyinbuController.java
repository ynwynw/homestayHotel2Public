package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;

import com.cl.entity.CanyinbuEntity;
import com.cl.entity.view.CanyinbuView;

import com.cl.service.CanyinbuService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MD5Util;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 餐饮部
 * 后端接口
 * @author 
 * @email 
 * @date 2024-01-19 11:28:04
 */
@RestController
@RequestMapping("/canyinbu")
public class CanyinbuController {
    @Autowired
    private CanyinbuService canyinbuService;



    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		CanyinbuEntity u = canyinbuService.selectOne(new EntityWrapper<CanyinbuEntity>().eq("canyinbuzhanghao", username));
		if(u==null || !u.getMima().equals(password)) {
			return R.error("账号或密码不正确");
		}
		String token = tokenService.generateToken(u.getId(), username,"canyinbu",  "餐饮部" );
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody CanyinbuEntity canyinbu){
    	//ValidatorUtils.validateEntity(canyinbu);
    	CanyinbuEntity u = canyinbuService.selectOne(new EntityWrapper<CanyinbuEntity>().eq("canyinbuzhanghao", canyinbu.getCanyinbuzhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
		Long uId = new Date().getTime();
		canyinbu.setId(uId);
        canyinbuService.insert(canyinbu);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        CanyinbuEntity u = canyinbuService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	CanyinbuEntity u = canyinbuService.selectOne(new EntityWrapper<CanyinbuEntity>().eq("canyinbuzhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        u.setMima("123456");
        canyinbuService.updateById(u);
        return R.ok("密码已重置为：123456");
    }


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,CanyinbuEntity canyinbu,
		HttpServletRequest request){
        EntityWrapper<CanyinbuEntity> ew = new EntityWrapper<CanyinbuEntity>();

		PageUtils page = canyinbuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, canyinbu), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,CanyinbuEntity canyinbu, 
		HttpServletRequest request){
        EntityWrapper<CanyinbuEntity> ew = new EntityWrapper<CanyinbuEntity>();

		PageUtils page = canyinbuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, canyinbu), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( CanyinbuEntity canyinbu){
       	EntityWrapper<CanyinbuEntity> ew = new EntityWrapper<CanyinbuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( canyinbu, "canyinbu")); 
        return R.ok().put("data", canyinbuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(CanyinbuEntity canyinbu){
        EntityWrapper< CanyinbuEntity> ew = new EntityWrapper< CanyinbuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( canyinbu, "canyinbu")); 
		CanyinbuView canyinbuView =  canyinbuService.selectView(ew);
		return R.ok("查询餐饮部成功").put("data", canyinbuView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CanyinbuEntity canyinbu = canyinbuService.selectById(id);
        return R.ok().put("data", canyinbu);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CanyinbuEntity canyinbu = canyinbuService.selectById(id);
        return R.ok().put("data", canyinbu);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CanyinbuEntity canyinbu, HttpServletRequest request){
    	canyinbu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(canyinbu);
    	CanyinbuEntity u = canyinbuService.selectOne(new EntityWrapper<CanyinbuEntity>().eq("canyinbuzhanghao", canyinbu.getCanyinbuzhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		canyinbu.setId(new Date().getTime());
        canyinbuService.insert(canyinbu);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody CanyinbuEntity canyinbu, HttpServletRequest request){
    	canyinbu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(canyinbu);
    	CanyinbuEntity u = canyinbuService.selectOne(new EntityWrapper<CanyinbuEntity>().eq("canyinbuzhanghao", canyinbu.getCanyinbuzhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		canyinbu.setId(new Date().getTime());
        canyinbuService.insert(canyinbu);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody CanyinbuEntity canyinbu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(canyinbu);
        canyinbuService.updateById(canyinbu);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        canyinbuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
