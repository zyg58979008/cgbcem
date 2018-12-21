package com.bootdo.system.service.impl;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.AreaDao;
import com.bootdo.system.domain.AreaDO;
import com.bootdo.system.service.AreaService;



@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaDao sysAreaMapper;

	@Override
	public AreaDO get(Long areaId){
		return sysAreaMapper.get(areaId);
	}

	@Override
	public List<AreaDO> list(Map<String, Object> map){
		return sysAreaMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return sysAreaMapper.count(map);
	}

	@Override
	public int save(AreaDO sysArea){
		return sysAreaMapper.save(sysArea);
	}

	@Override
	public int update(AreaDO sysArea){
		return sysAreaMapper.update(sysArea);
	}

	@Override
	public int remove(Long areaId){
		return sysAreaMapper.remove(areaId);
	}

	@Override
	public int batchRemove(Long[] areaIds){
		return sysAreaMapper.batchRemove(areaIds);
	}

	@Override
	public Tree<AreaDO> getTree() {
		List<Tree<AreaDO>> trees = new ArrayList<Tree<AreaDO>>();
		List<AreaDO> sysAreas = sysAreaMapper.list(new HashMap<String,Object>(16));
		for (AreaDO sysArea : sysAreas) {
			Tree<AreaDO> tree = new Tree<AreaDO>();
			tree.setId(sysArea.getAreaId().toString());
			tree.setParentId(sysArea.getParentId().toString());
			tree.setText(sysArea.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<AreaDO> t = BuildTree.build(trees,null);
		return t;
	}

	@Override
	public boolean checkAreaHasUser(Long areaId) {
		// TODO Auto-generated method stub
		//查询部门以及此部门的下级部门
		int result = sysAreaMapper.getAreaUserNumber(areaId);
		return result==0?true:false;
	}
	
}
