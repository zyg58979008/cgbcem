package com.bootdo.business.controller;

import com.bootdo.business.domain.ProfitDO;
import com.bootdo.business.service.ProfitService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.UserDO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 房间管理
 *
 */

@Controller
@RequestMapping("/business/profit")
public class ProfitController extends BaseController {
	private String prefix = "business/profit";
	@Autowired
	private ProfitService profitService;
	@GetMapping()
	@RequiresPermissions("business:profit:profit")
	String profit() {
		return prefix + "/profit";
	}
	@ApiOperation(value="获取房间列表", notes="")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:profit:profit")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		UserDO userDO= ShiroUtils.getUser();
		Query query = new Query(params);
		query.put("deptId",userDO.getDeptId());
		query.put("delFlag","0");
		List<ProfitDO> profitDOs = profitService.list(query);
		int total = profitService.count(query);
		PageUtils pageUtils = new PageUtils(profitDOs, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("business:profit:add")
	String add(Model model) {
		Map<String, Object> params=new HashedMap();
		params.put("deptId",ShiroUtils.getUser().getDeptId());
		params.put("delFlag","0");
		List<ProfitDO> profitDOs = profitService.list(params);
		if(profitDOs.size()>0){
			Date d=profitDOs.get(0).getEndDate();
			Calendar c=Calendar.getInstance();
			c.setTime(d);
			int day=c.get(Calendar.DATE);
			c.set(Calendar.DATE,day+1);
			model.addAttribute("startDate", c.getTime());
		}else {
			model.addAttribute("startDate", "");
		}
		return  prefix + "/add";
	}


	@GetMapping("/edit/{id}")
	@RequiresPermissions("business:profit:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		ProfitDO room = profitService.get(id);
		model.addAttribute("room", room);
		return  prefix + "/edit";
	}

	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("business:profit:add")
	public R save(HttpServletRequest request) throws IOException {
		UserDO userDO=ShiroUtils.getUser();
		String jsonStr = request.getParameter("mydata");
		ObjectMapper mapper = new ObjectMapper();
		ProfitDO profitDO = mapper.readValue(jsonStr, ProfitDO.class);
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		map.put("startDate",sdf.format(profitDO.getStartDate()));
		map.put("endDate",sdf.format(profitDO.getEndDate()));
		map.put("deptId",userDO.getDeptId());
		map.put("delFlag","0");
		if(profitService.checkHas(map)>0) {
			return R.error(1, "该日期范围内已录入利润");
		}
		profitDO.preInsert();
		profitDO.setDeptId(ShiroUtils.getUser().getDeptId());
		if (profitService.save(profitDO) > 0) {
			return R.ok();
		}
		return R.error();
	}
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("business:profit:edit")
	public R update(ProfitDO profitDO) {
		profitDO.preUpdate();
		int i=profitService.update(profitDO);
		if(i>0){
			R.ok();
		}
		return R.error();
	}
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("business:profit:remove")
	public R remove(Long id) {
		ProfitDO profitDO=profitService.get(id);
		profitDO.preUpdate();
		profitDO.setDelFlag("1");
		int i=profitService.update(profitDO);
		if(i>0){
			profitService.removeMoney(profitDO.getId());
			profitService.update7(profitDO.getId());
			return R.ok();
		}
		return R.error();
	}
	@PostMapping("/fanzu")
	@ResponseBody
	public R fanzu(Long id) {
		profitService.callLeaseback(id,ShiroUtils.getUser().getDeptId());
		return R.ok();
	}
}
