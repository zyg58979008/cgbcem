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

import com.bootdo.stock.domain.CancelDO;
import com.bootdo.stock.service.CancelService;
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
@RequestMapping("/stock/cancel")
public class CancelController {
	@Autowired
	private CancelService cancelService;
	
	@GetMapping()
	@RequiresPermissions("stock:cancel:cancel")
	String Cancel(){
	    return "stock/cancel/cancel";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("stock:cancel:cancel")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CancelDO> cancelList = cancelService.list(query);
		int total = cancelService.count(query);
		PageUtils pageUtils = new PageUtils(cancelList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("stock:cancel:add")
	String add(){
	    return "stock/cancel/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("stock:cancel:edit")
	String edit(@PathVariable("id") String id,Model model){
		CancelDO cancel = cancelService.get(id);
		model.addAttribute("cancel", cancel);
	    return "stock/cancel/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("stock:cancel:add")
	public R save( CancelDO cancel){
		cancel.preInsert();
		if(cancelService.save(cancel)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("stock:cancel:edit")
	public R update( CancelDO cancel){
		cancelService.update(cancel);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("stock:cancel:remove")
	public R remove( String id){
		if(cancelService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("stock:cancel:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		cancelService.batchRemove(ids);
		return R.ok();
	}
	
}
