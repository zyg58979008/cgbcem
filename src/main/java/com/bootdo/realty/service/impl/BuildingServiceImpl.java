package com.bootdo.realty.service.impl;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.realty.dao.BuildingDao;
import com.bootdo.realty.dao.RoomDao;
import com.bootdo.realty.domain.BuildingDO;
import com.bootdo.realty.service.BuildingService;
import com.bootdo.system.domain.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingDao sysBuildingMapper;
	@Autowired
	private RoomDao roomDao;

	@Override
	public BuildingDO get(Long id){
		return sysBuildingMapper.get(id);
	}

	@Override
	public List<BuildingDO> list(Map<String, Object> map){
		return sysBuildingMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return sysBuildingMapper.count(map);
	}

	@Override
	public int save(BuildingDO sysBuilding){
		sysBuilding.preInsert();
		return sysBuildingMapper.save(sysBuilding);
	}

	@Override
	public int update(BuildingDO sysBuilding){
		sysBuilding.preUpdate();
		return sysBuildingMapper.update(sysBuilding);
	}
	@Override
	public int updateParentId(Long id){
		return sysBuildingMapper.updateParentId(id);
	}
	@Override
	public int remove(Long id){
		BuildingDO sysBuilding=sysBuildingMapper.get(id);
		sysBuilding.setDelFlag("1");
		return sysBuildingMapper.update(sysBuilding);
	}

	@Override
	public int batchRemove(Long[] ids){
		return sysBuildingMapper.batchRemove(ids);
	}

	@Override
	public Tree<BuildingDO> getTree(Long type) {
		List<Tree<BuildingDO>> trees = new ArrayList<Tree<BuildingDO>>();
		Map<String, Object> query = new HashMap<>(16);
		UserDO userDO= ShiroUtils.getUser();
		if(type.equals("1")){
			query.put("type","1");
		}
		query.put("deptId",userDO.getDeptId());
		query.put("delFlag","0");
		List<BuildingDO> sysBuildings = sysBuildingMapper.list(query);
		for (BuildingDO sysBuilding : sysBuildings) {
			Tree<BuildingDO> tree = new Tree<BuildingDO>();
			tree.setId(sysBuilding.getId().toString());
			tree.setParentId(sysBuilding.getParentId().toString());
			if(sysBuilding.getType().equals("1")){
				tree.setText(sysBuilding.getName());
			}else{
				tree.setText(sysBuilding.getName()+"("+sysBuilding.getId()+")");
			}
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			state.put("type", sysBuilding.getType());
			state.put("roomType", sysBuilding.getRoomType());
			state.put("unit", sysBuilding.getUnit());
			state.put("deptId", sysBuilding.getDeptId());
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<BuildingDO> t = BuildTree.build(trees,userDO.getDeptName());
		return t;
	}
	@Override
	public boolean checkBuildingHasRoom(Long id) {
		// TODO Auto-generated method stub
		//查询部门以及此部门的下级部门
		Map<String, Object> query = new HashMap<>(16);
		UserDO userDO= ShiroUtils.getUser();
		query.put("deptId",userDO.getDeptId());
		query.put("delFlag","0");
		query.put("buildingId",id);
		int result = roomDao.count(query);
		return result==0?true:false;
	}


}
