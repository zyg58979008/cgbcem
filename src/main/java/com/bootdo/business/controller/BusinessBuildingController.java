package com.bootdo.business.controller;

import com.bootdo.business.service.BusinessBuildingService;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.business.domain.BusinessBuildingDO;
import com.bootdo.system.domain.UserDO;
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
 * 楼宇管理
 *
 */

@Controller
@RequestMapping("/business/building")
public class BusinessBuildingController extends BaseController {
	private String prefix = "business/building";
	@Autowired
	private BusinessBuildingService buildingService;

	@GetMapping()
	@RequiresPermissions("business:building:building")
	String building() {
		return prefix + "/building";
	}

	@ApiOperation(value="获取楼宇列表", notes="")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:building:building")
	public List<BusinessBuildingDO> list() {
		Map<String, Object> query = new HashMap<>(16);
		UserDO userDO= ShiroUtils.getUser();
		query.put("deptId",userDO.getDeptId());
		query.put("delFlag","0");
		List<BusinessBuildingDO> buildingList = buildingService.list(query);
		return buildingList;
	}

	@GetMapping("/add/{pId}")
	@RequiresPermissions("business:building:add")
	String add(@PathVariable("pId") Long pId, Model model) {
		model.addAttribute("pId", pId);
		UserDO userDO= ShiroUtils.getUser();
		if (pId == 0) {
			model.addAttribute("pName", userDO.getDeptName());
		} else {
			model.addAttribute("pName", buildingService.get(pId).getName());
		}
		return  prefix + "/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("business:building:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		BusinessBuildingDO building = buildingService.get(id);
		model.addAttribute("building", building);
		UserDO userDO= ShiroUtils.getUser();
		if(Constant.AREA_ROOT_ID.equals(building.getParentId())) {
			model.addAttribute("parentBuildingName", userDO.getDeptName());
		}else {
			BusinessBuildingDO pararea = buildingService.get(building.getParentId());
			model.addAttribute("parentBuildingName", pararea.getName());
		}
		return  prefix + "/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("business:building:add")
	public R save(BusinessBuildingDO building) {
		if (buildingService.save(building) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("business:building:edit")
	public R update(BusinessBuildingDO building) {
		if (buildingService.update(building) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("business:building:remove")
	public R remove(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", id);
		if(buildingService.count(map)>0) {
			return R.error(1, "包含下级,不允许删除");
		}
		if(buildingService.checkBuildingHasRoom(id)) {
			if (buildingService.remove(id) > 0) {
				return R.ok();
			}
		}else {
			return R.error(1, "楼宇包含房间,不允许删除");
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("business:building:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		buildingService.batchRemove(ids);
		return R.ok();
	}

	@GetMapping("/tree/{type}")
	@ResponseBody
	public Tree<BusinessBuildingDO> tree(@PathVariable("type") Long type) {
		Tree<BusinessBuildingDO> tree = new Tree<BusinessBuildingDO>();
		tree = buildingService.getTree(type);
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/areaTree";
	}

}
