package com.bootdo.stock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.config.Constant;
import com.bootdo.common.domain.Tree;
import io.swagger.annotations.ApiOperation;
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

import com.bootdo.stock.domain.ClassDO;
import com.bootdo.stock.service.ClassService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import static org.apache.naming.SelectorContext.prefix;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-30 14:49:38
 */
 
@Controller
@RequestMapping("/stock/class")
public class ClassController {
	private String prefix = "stock/class";

	@Autowired
	private ClassService classService;
	
	@GetMapping()
	@RequiresPermissions("stock:class:class")
	String Classs(){
	    return prefix + "/class";
	}

	@GetMapping("code")
	String code(){
		return prefix + "/code";
	}

	@ApiOperation(value="获取设备分类列表", notes="")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("stock:class:class")
	public List<ClassDO> list(@RequestParam Map<String, Object> params){
		Map<String, Object> query = new HashMap<>(16);
		List<ClassDO> classList = classService.list(query);
		return classList;
	}
	
	@GetMapping("/add/{pId}")
	@RequiresPermissions("stock:class:add")
	String add(@PathVariable("pId") String pId, Model model){
	    model.addAttribute("pId", pId);
		if (pId.equals("0") ) {
			model.addAttribute("pName", "总分类");
		} else {
			model.addAttribute("pName", classService.get(pId).getName());
		}
		return  prefix + "/add";
	}

	@GetMapping("/edit/{classId}")
	@RequiresPermissions("stock:class:edit")
	String edit(@PathVariable("deptId") String deptId, Model model){
		ClassDO classDO = classService.get(deptId);
		model.addAttribute("class", classDO);
		if(Constant.DEPT_ROOT_ID.equals(classDO.getParentId())) {
			model.addAttribute("parentDeptName", "无");
		}else {
			ClassDO parClass = classService.get(classDO.getParentId());
			model.addAttribute("parentDeptName", parClass.getName());
		}
		return  prefix + "/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("stock:class:add")
	public R save( ClassDO classDO){
		if(classService.save(classDO)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("stock:class:edit")
	public R update( ClassDO classDO){
		classService.update(classDO);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("stock:class:remove")
	public R remove( Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", id);
		map.put("delFlag","0");
		if(classService.count(map)>0) {
			return R.error(1, "包含下级分类,不允许删除");
		}
		if(classService.checkStockHasClass(id)) {
			if (classService.remove(id) > 0) {
				return R.ok();
			}
		}else {
			return R.error(1, "分类正在使用中,不允许删除");
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("stock:class:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		classService.batchRemove(ids);
		return R.ok();
	}
	@GetMapping("/tree")
	@ResponseBody
	public Tree<ClassDO> tree() {
		Tree<ClassDO> tree = new Tree<ClassDO>();
		tree = classService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/deptTree";
	}
}
