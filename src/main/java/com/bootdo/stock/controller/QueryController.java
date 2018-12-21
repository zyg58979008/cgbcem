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

import com.bootdo.stock.domain.QueryDO;
import com.bootdo.stock.service.QueryService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-04-07 21:22:37
 */
 
@Controller
@RequestMapping("/stock/query")
public class QueryController {
	@Autowired
	private QueryService queryService;
	
	@GetMapping()
	String Query(){
	    return "stock/query/query";
	}
	@GetMapping("a")
	String Query1(){
		return "mobile/a";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<QueryDO> queryList;
		int total;
		if(params.get("type").equals("1")){
			if(params.get("parentId")!=null){
				params.put("type","2");
				queryList = queryService.list(params);
				total = queryService.count(params);
			}
			else {
				Query query = new Query(params);
				queryList = queryService.list(query);
				total = queryService.count(query);
			}
		}
       else{
			queryList = queryService.list(params);
			total = queryService.count(params);
		}
		PageUtils pageUtils = new PageUtils(queryList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("stock:query:add")
	String add(){
	    return "stock/query/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("stock:query:edit")
	String edit(@PathVariable("id") String id,Model model){
		QueryDO query = queryService.get(id);
		model.addAttribute("query", query);
	    return "stock/query/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("stock:query:add")
	public R save( QueryDO query){
		if(queryService.save(query)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("stock:query:edit")
	public R update( QueryDO query){
		queryService.update(query);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("stock:query:remove")
	public R remove( String id){
		if(queryService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("stock:query:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		queryService.batchRemove(ids);
		return R.ok();
	}
	
}
