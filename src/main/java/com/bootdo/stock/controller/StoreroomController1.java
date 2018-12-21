package com.bootdo.stock.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.stock.service.StoreroomService1;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.stock.domain.StoreroomDO;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-02 16:32:30
 */
 
@Controller
@RequestMapping("/stock/storeroom1")
public class StoreroomController1 {
	@Autowired
	private StoreroomService1 storeroomService;
	
	@GetMapping()
	@RequiresPermissions("stock:storeroom:storeroom")
	String Storeroom(){
	    return "stock/storeroom/storeroom";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("stock:storeroom:storeroom")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StoreroomDO> storeroomList = storeroomService.list(query);
		int total = storeroomService.count(query);
		PageUtils pageUtils = new PageUtils(storeroomList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("stock:storeroom:add")
	String add(){
	    return "stock/storeroom/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("stock:storeroom:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StoreroomDO storeroom = storeroomService.get(id);
		model.addAttribute("storeroom", storeroom);
	    return "stock/storeroom/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("stock:storeroom:add")
	public R save( StoreroomDO storeroom){
		if(storeroomService.save(storeroom)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("stock:storeroom:edit")
	public R update( StoreroomDO storeroom){
		storeroomService.update(storeroom);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("stock:storeroom:remove")
	public R remove( Long id){
		if(storeroomService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("stock:storeroom:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		storeroomService.batchRemove(ids);
		return R.ok();
	}
	
}
