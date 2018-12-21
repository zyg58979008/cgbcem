package com.bootdo.base.controller;

import com.bootdo.base.domain.OwnerDO;
import com.bootdo.base.service.OwnerService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.UserDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 业主管理
 *
 * @author ZYG
 * @email
 * @date 2018-09-16
 */

@Controller
@RequestMapping("/base/owner")
public class OwnerController {
	@Autowired
	private OwnerService ownerService;

	@GetMapping()
	@RequiresPermissions("base:owner:owner")
	String Owner(){
		return "base/owner/owner";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:owner:owner")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<OwnerDO> ownerList = ownerService.list(query);
		int total = ownerService.count(query);
		PageUtils pageUtils = new PageUtils(ownerList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("base:owner:add")
	String add(){
		return "base/owner/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:owner:edit")
	String edit(@PathVariable("id") String id,Model model){
		OwnerDO owner = ownerService.get(id);
		model.addAttribute("owner", owner);
		return "base/owner/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save(OwnerDO a){
		UserDO userDO= ShiroUtils.getUser();
		a.setDeptId(userDO.getDeptId());
		a.preInsert();
		if(ownerService.save(a)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:owner:edit")
	public R update(OwnerDO a){
		ownerService.update(a);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("base:owner:remove")
	public R remove(String id){
		if(ownerService.remove(id)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 批量删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:owner:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		ownerService.batchRemove(ids);
		return R.ok();
	}

}
