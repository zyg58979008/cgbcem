package com.bootdo.stock.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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

import com.bootdo.stock.domain.OutboundDO;
import com.bootdo.stock.service.OutboundService;
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
@RequestMapping("/stock/outbound")
public class OutboundController {
	@Autowired
	private OutboundService outboundService;
	
	@GetMapping()
	@RequiresPermissions("stock:outbound:outbound")
	String Outbound(){
	    return "stock/outbound/outbound";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("stock:outbound:outbound")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OutboundDO> outboundList = outboundService.list(query);
		int total = outboundService.count(query);
		PageUtils pageUtils = new PageUtils(outboundList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("stock:outbound:add")
	String add(){
	    return "stock/outbound/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("stock:outbound:edit")
	String edit(@PathVariable("id") String id,Model model){
		OutboundDO outbound = outboundService.get(id);
		model.addAttribute("outbound", outbound);
	    return "stock/outbound/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("stock:outbound:add")
	public R save( OutboundDO outbound){
		outbound.preInsert();
		if(outboundService.save(outbound)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("stock:outbound:edit")
	public R update( OutboundDO outbound){
		outboundService.update(outbound);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("stock:outbound:remove")
	public R remove( String id){
		if(outboundService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("stock:outbound:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		outboundService.batchRemove(ids);
		return R.ok();
	}
	
}
