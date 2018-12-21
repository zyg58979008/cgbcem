package com.bootdo.stock.service.impl;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.stock.dao.ClassDao;
import com.bootdo.stock.domain.ClassDO;
import com.bootdo.stock.service.ClassService;



@Service
public class ClassServiceImpl implements ClassService {
	@Autowired
	private ClassDao classDao;
	
	@Override
	public ClassDO get(String id){
		return classDao.get(id);
	}
	
	@Override
	public List<ClassDO> list(Map<String, Object> map){
		return classDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return classDao.count(map);
	}
	
	@Override
	public int save(ClassDO classDO){
		return classDao.save(classDO);
	}
	
	@Override
	public int update(ClassDO classDO){
		return classDao.update(classDO);
	}
	
	@Override
	public int remove(Long id){
		return classDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return classDao.batchRemove(ids);
	}

	@Override
	public Tree<ClassDO> getTree() {
		List<Tree<ClassDO>> trees = new ArrayList<Tree<ClassDO>>();
		List<ClassDO> classDOs = classDao.list(new HashMap<String,Object>(16));
		for (ClassDO classDO : classDOs) {
			Tree<ClassDO> tree = new Tree<ClassDO>();
			tree.setId(classDO.getId().toString());
			tree.setParentId(classDO.getParentId().toString());
			tree.setText(classDO.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<ClassDO> t = BuildTree.build(trees,null);
		return t;
	}

	@Override
	public boolean checkStockHasClass(Long classId) {
		return false;
	}

	@Override
	public String getId() {
		return classDao.getId();
	}

}
