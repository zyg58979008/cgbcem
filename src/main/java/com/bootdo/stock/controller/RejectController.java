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

import com.bootdo.stock.domain.RejectDO;
import com.bootdo.stock.service.RejectService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-03 15:32:04
 */
 
@Controller
@RequestMapping("/stock/reject")
public class RejectController {
	@Autowired
	private RejectService rejectService;
	
	@GetMapping()
	@RequiresPermissions("stock:reject:reject")
	String Reject(){
	    return "stock/reject/reject";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("stock:reject:reject")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RejectDO> rejectList = rejectService.list(query);
		int total = rejectService.count(query);
		PageUtils pageUtils = new PageUtils(rejectList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("stock:reject:add")
	String add(){
	    return "stock/reject/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("stock:reject:edit")
	String edit(@PathVariable("id") String id,Model model){
		RejectDO reject = rejectService.get(id);
		model.addAttribute("reject", reject);
	    return "stock/reject/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("stock:reject:add")
	public R save( RejectDO reject){
		reject.preInsert();
		if(rejectService.save(reject)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("stock:reject:edit")
	public R update( RejectDO reject){
		rejectService.update(reject);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("stock:reject:remove")
	public R remove( String id){
		if(rejectService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("stock:reject:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		rejectService.batchRemove(ids);
		return R.ok();
	}
	
}
