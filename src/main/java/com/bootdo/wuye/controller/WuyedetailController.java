package com.bootdo.wuye.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 楼宇管理
 * 
 * @author ZYG
 * @email
 * @date 2018-09-16
 */
 
@Controller
@RequestMapping("/wuye/wuyeDetail")
public class WuyedetailController {
	@Autowired
	//private MerchantService merchantService;

	@GetMapping()
	String Merchant(){
	    return "wuye/wuyeDetail/wuye";
	}
	

	@GetMapping("/add")
	String add(){
	    return "wuye/wuye/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("business:building:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		/*MerchantDO building = merchantService.get(id);
		model.addAttribute("building", building);*/
	    return "business/building/edit";
	}
	

}
