package com.bootdo.base.controller;

import com.bootdo.base.domain.MerchantDO;
import com.bootdo.base.service.MerchantService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.AreaDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.AreaService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 商户管理
 * 
 * @author ZYG
 * @email
 * @date 2018-09-16
 */
 
@Controller
@RequestMapping("/base/merchant")
public class MerchantController {
	@Autowired
	private MerchantService merchantService;
	
	@GetMapping()
	@RequiresPermissions("base:merchant:merchant")
	String Merchant(){
	    return "base/merchant/merchant";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:merchant:merchant")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MerchantDO> merchantList = merchantService.list(query);
		int total = merchantService.count(query);
		PageUtils pageUtils = new PageUtils(merchantList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("base:merchant:add")
	String add(){
	    return "base/merchant/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:merchant:edit")
	String edit(@PathVariable("id") String id,Model model){
		MerchantDO merchant = merchantService.get(id);
		model.addAttribute("merchant", merchant);
	    return "base/merchant/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:merchant:add")
	public R save( MerchantDO merchant){
		UserDO userDO= ShiroUtils.getUser();
		merchant.setDeptId(userDO.getDeptId());
		merchant.preInsert();
		if(merchantService.save(merchant)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:merchant:edit")
	public R update( MerchantDO merchant){
		merchantService.update(merchant);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("base:merchant:remove")
	public R remove(String id){
		if(merchantService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 批量删除
	 */
	/*@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:merchant:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		merchantService.batchRemove(ids);
		return R.ok();
	}*/
	
}
