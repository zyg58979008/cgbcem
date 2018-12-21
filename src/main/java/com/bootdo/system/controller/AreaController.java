package com.bootdo.system.controller;

import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.AreaDO;
import com.bootdo.system.service.AreaService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 区域管理
 *
 */

@Controller
@RequestMapping("/system/sysArea")
public class AreaController extends BaseController {
	private String prefix = "system/area";
	@Autowired
	private AreaService sysAreaService;

	@GetMapping()
	@RequiresPermissions("system:sysArea:sysArea")
	String area() {
		return prefix + "/area";
	}

	@ApiOperation(value="获取区域列表", notes="")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:sysArea:sysArea")
	public List<AreaDO> list() {
		Map<String, Object> query = new HashMap<>(16);
		List<AreaDO> sysAreaList = sysAreaService.list(query);
		return sysAreaList;
	}

	@GetMapping("/add/{pId}")
	@RequiresPermissions("system:sysArea:add")
	String add(@PathVariable("pId") Long pId, Model model) {
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "中国");
		} else {
			model.addAttribute("pName", sysAreaService.get(pId).getName());
		}
		return  prefix + "/add";
	}

	@GetMapping("/edit/{areaId}")
	@RequiresPermissions("system:sysArea:edit")
	String edit(@PathVariable("areaId") Long areaId, Model model) {
		AreaDO sysArea = sysAreaService.get(areaId);
		model.addAttribute("sysArea", sysArea);
		if(Constant.AREA_ROOT_ID.equals(sysArea.getParentId())) {
			model.addAttribute("parentAreaName", "无");
		}else {
			AreaDO pararea = sysAreaService.get(sysArea.getParentId());
			model.addAttribute("parentAreaName", pararea.getName());
		}
		return  prefix + "/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:sysArea:add")
	public R save(AreaDO sysArea) {
		if (sysAreaService.save(sysArea) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:sysArea:edit")
	public R update(AreaDO sysArea) {
		if (sysAreaService.update(sysArea) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("system:sysArea:remove")
	public R remove(Long areaId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", areaId);
		if(sysAreaService.count(map)>0) {
			return R.error(1, "包含下级区域,不允许修改");
		}
		if(sysAreaService.checkAreaHasUser(areaId)) {
			if (sysAreaService.remove(areaId) > 0) {
				return R.ok();
			}
		}else {
			return R.error(1, "区域包含用户,不允许修改");
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:sysArea:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] areaIds) {
		sysAreaService.batchRemove(areaIds);
		return R.ok();
	}

	@GetMapping("/tree")
	@ResponseBody
	public Tree<AreaDO> tree() {
		Tree<AreaDO> tree = new Tree<AreaDO>();
		tree = sysAreaService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/areaTree";
	}

}
