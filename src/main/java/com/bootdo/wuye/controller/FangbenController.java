package com.bootdo.wuye.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.realty.domain.RoomDO;
import com.bootdo.realty.service.RoomService;
import com.bootdo.system.domain.UserDO;
import com.bootdo.wuye.service.FangbenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 房间管理
 *
 */

@Controller
@RequestMapping("/wuye/fangbenConfig")
public class FangbenController extends BaseController {
	private String prefix = "wuye/fangbenConfig";
	@Autowired
	private RoomService roomService;
	@Autowired
	private FangbenService fangbenService;
	@GetMapping()
	@RequiresPermissions("wuye:fangbenConfig:fangbenfei")
	String room() {
		return prefix + "/config";
	}
	@ApiOperation(value="获取房间列表", notes="")
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		UserDO userDO= ShiroUtils.getUser();
		Query query = new Query(params);
		query.put("deptId",userDO.getDeptId());
		query.put("delFlag","0");
		List<RoomDO> roomList = roomService.list(query);
		int total = roomService.count(query);
		PageUtils pageUtils = new PageUtils(roomList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("wuye:fangbenConfig:add")
	String add(Model model) {
		return  prefix + "/add";
	}


	@GetMapping("/edit/{id}")
	@RequiresPermissions("wuye:fangbenConfig:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		RoomDO room = roomService.get(id);
		model.addAttribute("room", room);
		return  prefix + "/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("wuye:room:add")
	public R save(RoomDO room, HttpRequest request) {
		UserDO userDO=ShiroUtils.getUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buildingId",room.getBuildingId());
		map.put("code",room.getCode());
		if(roomService.count(map)>0) {
			return R.error(1, "该楼宇下已存在该房间编码");
		}
		if (roomService.save(room) > 0) {
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 合并
	 */
	@ResponseBody
	@PostMapping("/batchSave")
	public R batchSave(HttpServletRequest request) throws IOException {
        String jsonStr = request.getParameter("mydata");
        ObjectMapper mapper = new ObjectMapper();
        RoomDO roomDO = mapper.readValue(jsonStr, RoomDO.class);
		List<RoomDO> roomDOList=roomDO.getRoomDOList();
		for (RoomDO r:roomDOList){
			fangbenService.update(r);
		}
        return R.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("wuye:fangbenConfig:edit")
	public R update(RoomDO room) {
		if (roomService.update(room) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("wuye:room:remove")
	public R remove(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", id);
		//查询是否存在合同
		/*if(roomService.count(map)>0) {
			return R.error(1, "包含下级,不允许修改");
		}*/
		/*if(roomService.checkBuildingHasRoom(id)) {
			if (roomService.remove(id) > 0) {
				return R.ok();
			}
		}else {
			return R.error(1, "楼宇包含用户,不允许修改");
		}*/
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("wuye:room:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		roomService.batchRemove(ids);
		return R.ok();
	}

	@GetMapping("/tree/{pId}")
	@ResponseBody
	public Tree<RoomDO> tree(@PathVariable("type") Long type) {
		Tree<RoomDO> tree = new Tree<RoomDO>();
		//tree = roomService.getTree(type);
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/areaTree";
	}
}
