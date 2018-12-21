package com.bootdo.business.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.stock.domain.ProductDO;
import com.bootdo.stock.service.ProductService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-02 11:05:45
 */
 
@Controller
@RequestMapping("/business/contract1")
public class ContractController1 {

	@GetMapping()
	String Product(){
	    return "business/contract/contract";
	}


	@GetMapping("/add")
	String add(){
	    return "business/product/add";
	}
	@GetMapping("/contract1")
	String add1(){
	    return "business/contract/contract1";
	}
	@GetMapping("/tanxiao")
	String tanxiao(){
		return "business/contract/tanxiao";
	}

	@GetMapping("/edit")
	String edit(Model model){
	    return "business/contract/edit";
	}

	@GetMapping("/edit11")
	String edit12(Model model){
	    return "business/contract/edit11";
	}
	@GetMapping("/edit1")
	String edit11(Model model){
	    return "business/contract/edit1";
	}
	@GetMapping("/pay")
	String edit1(Model model){
	    return "business/contract/pay";
	}
	@GetMapping("/pay1")
	String edit122(Model model){
		return "business/contract/pay1";
	}
	@GetMapping("/log")
	String log(Model model){
	    return "business/contract/log";
	}
	@GetMapping("/add1")
	String log1(Model model){
		return "business/contract/add1";
	}
	@GetMapping("/pays")
	String pays(Model model){
		return "business/contract/add1";
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( ProductDO product){
		return R.ok();
	}
	

}
