package com.bootdo.stock.controller;

import java.util.List;
import java.util.Map;

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

import com.bootdo.stock.domain.Product1DO;
import com.bootdo.stock.service.Product1Service;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-03 09:10:32
 */
 
@Controller
@RequestMapping("/stock/product1")
public class Product1Controller {
	@Autowired
	private Product1Service product1Service;
	
	@GetMapping()
	@RequiresPermissions("stock:product1:product1")
	String Product1(){
	    return "stock/product1/product1";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("stock:product1:product1")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<Product1DO> product1List = product1Service.list(query);
		int total = product1Service.count(query);
		PageUtils pageUtils = new PageUtils(product1List, total);
		return pageUtils;
	}

	@GetMapping("/add")
	String add(){
	    return "stock/product1/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("stock:product1:edit")
	String edit(@PathVariable("id") Long id,Model model){
		Product1DO product1 = product1Service.get(id);
		model.addAttribute("product1", product1);
	    return "stock/product1/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("stock:product1:add")
	public R save( Product1DO product1){
		if(product1Service.save(product1)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("stock:product1:edit")
	public R update( Product1DO product1){
		product1Service.update(product1);
		return R.ok();
	}
	private String prefix="stock/product1"  ;
	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/userTree";
	}
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("stock:product1:remove")
	public R remove( Long id){
		if(product1Service.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("stock:product1:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		product1Service.batchRemove(ids);
		return R.ok();
	}
	
}
