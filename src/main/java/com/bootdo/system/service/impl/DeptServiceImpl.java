package com.bootdo.system.service.impl;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.dao.RoleDao;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.domain.RoleDO;
import com.bootdo.system.domain.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.system.dao.DeptDao;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.service.DeptService;



@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	private DeptDao sysDeptMapper;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Override
	public DeptDO get(Long deptId){
		return sysDeptMapper.get(deptId);
	}

	@Override
	public List<DeptDO> list(Map<String, Object> map){
		return sysDeptMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return sysDeptMapper.count(map);
	}

	@Override
	public int save(DeptDO sysDept){
		return sysDeptMapper.save(sysDept);
	}

	@Override
	public int update(DeptDO sysDept){
		return sysDeptMapper.update(sysDept);
	}
	@Override
	public int updateParentId(Long deptId){
		return sysDeptMapper.updateParentId(deptId);
	}

	@Override
	public String[] getDeptIds(Long id) {
		return sysDeptMapper.getDeptIds(id);
	}

	@Override
	public int remove(Long deptId){
		return sysDeptMapper.remove(deptId);
	}

	@Override
	public int batchRemove(Long[] deptIds){
		return sysDeptMapper.batchRemove(deptIds);
	}

	@Override
	public Tree<DeptDO> getTree() {
		List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
		List<DeptDO> sysDepts = sysDeptMapper.list(new HashMap<String,Object>(16));
		for (DeptDO sysDept : sysDepts) {
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree.setId(sysDept.getDeptId().toString());
			tree.setParentId(sysDept.getParentId().toString());
			tree.setText(sysDept.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<DeptDO> t = BuildTree.build(trees,null);
		return t;
	}
	@Override
	public Tree<DeptDO> getTree1() {
		List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
		List<DeptDO> sysDepts = sysDeptMapper.list(new HashMap<String,Object>(16));
		for (DeptDO sysDept : sysDepts) {
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree.setId(sysDept.getDeptId().toString());
			tree.setParentId(sysDept.getParentId().toString());
			tree.setText(sysDept.getName());
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<DeptDO> t = BuildTree.build(trees,null);
		return t;
	}
	@Override
	public Tree<DeptDO> treeByAreaLevel() {
		List<DeptDO> sysDepts;
		UserDO userDO= ShiroUtils.getUser();
		Long deptId=userDO.getDeptId();
		DeptDO deptDO=sysDeptMapper.get(deptId);
		String areaLevel=deptDO.getAreaLevel();
		List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
	//	List<DeptDO> sysDepts = sysDeptMapper.list(new HashMap<String,Object>(16));
		//市级
			sysDepts = sysDeptMapper.listByLevel(deptDO);
		for (DeptDO sysDept : sysDepts) {
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree.setId(sysDept.getDeptId().toString());
			tree.setParentId(sysDept.getParentId().toString());
			tree.setText(sysDept.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
			Tree<DeptDO> t = BuildTree.buildById(trees,deptId);
			return t;

	}

	@Override
	public Tree<DeptDO> treeViewByLevelForFirstOnline() {
		List<DeptDO> sysDepts;
		UserDO userDO= ShiroUtils.getUser();
		Long deptId=userDO.getDeptId();
		DeptDO deptDO=sysDeptMapper.get(deptId);
		String areaLevel=deptDO.getAreaLevel();
		List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
		//	List<DeptDO> sysDepts = sysDeptMapper.list(new HashMap<String,Object>(16));
		//市级
		if(deptId.longValue()==Long.valueOf("29")){
			sysDepts = sysDeptMapper.getListForB(deptDO);
		}else{
			sysDepts = sysDeptMapper.listByLevel(deptDO);
		}
		for (DeptDO sysDept : sysDepts) {
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree.setId(sysDept.getDeptId().toString());
			tree.setParentId(sysDept.getParentId().toString());
			tree.setText(sysDept.getName());
			tree.setAreaLevel(sysDept.getAreaLevel());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		if(deptId.longValue()==Long.valueOf("29")){
			Tree<DeptDO> t = BuildTree.build(trees,null);
			return t;
		}else{
			Tree<DeptDO> t = BuildTree.buildById(trees,deptId);
			return t;
		}

	}
	@Override
	public Tree<DeptDO> getTree(Long id) {
		// 根据roleId查询权限
		List<DeptDO> menus = sysDeptMapper.list(new HashMap<String, Object>(16));
		List<Long> menuIds = userDao.listDeptIdByUserId(id);
		List<Long> temp = menuIds;
		for (DeptDO menu : menus) {
			if (temp.contains(menu.getParentId())) {
				menuIds.remove(menu.getParentId());
			}
		}
		List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
		List<DeptDO> menuDOs = sysDeptMapper.list(new HashMap<String, Object>(16));
		for (DeptDO sysMenuDO : menuDOs) {
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree.setId(sysMenuDO.getDeptId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> state = new HashMap<>(16);
			Long menuId = sysMenuDO.getDeptId();
			if (menuIds.contains(menuId)) {
				state.put("selected", true);
			} else {
				state.put("selected", false);
			}
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<DeptDO> t = BuildTree.build(trees,null);
		return t;
	}
	private boolean checkRole(List<Long> roleDOS){
		boolean hasRole=false;
		for (Long l:roleDOS){
			if(l==1||l==65){
				hasRole=true;
				break;
			}
		}
		return hasRole;
	}
	@Override
	public Tree<DeptDO> treeByUserId() {
		List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
		Map<String, Object> map=new HashMap<>();
		UserDO u=ShiroUtils.getUser();
		Long userId=u.getUserId();
		List<Long> roleDOS=roleDao.listRole(userId);
		if(userId!=1&&userId!=149){
			if(!checkRole(roleDOS)){
				map.put("userId",userId);
			}
		}
		List<DeptDO> sysDepts = sysDeptMapper.list(map);
		for (DeptDO sysDept : sysDepts) {
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree.setId(sysDept.getDeptId().toString());
			tree.setParentId(sysDept.getParentId().toString());
			tree.setText(sysDept.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<DeptDO> t = BuildTree.build(trees,null);
		return t;
	}

	@Override
	public boolean checkDeptHasUser(Long deptId) {
		// TODO Auto-generated method stub
		//查询部门以及此部门的下级部门
		int result = sysDeptMapper.getDeptUserNumber(deptId);
		return result==0?true:false;
	}


}
