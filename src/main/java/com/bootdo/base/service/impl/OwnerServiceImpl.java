package com.bootdo.base.service.impl;

import com.bootdo.base.dao.OwnerDao;
import com.bootdo.base.domain.OwnerDO;
import com.bootdo.base.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private OwnerDao ownerDao;

    @Override
    public OwnerDO get(String id) {
        return ownerDao.get(id);
    }

    @Override
    public List<OwnerDO> list(Map<String, Object> map) {
        return ownerDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return ownerDao.count(map);
    }

    @Override
    public int save(OwnerDO owner) {
        return ownerDao.save(owner);
    }

    @Override
    public int update(OwnerDO owner) {
        return ownerDao.update(owner);
    }

    @Override
    public int remove(String id) {
        return ownerDao.remove(id);
    }

    @Override
    public int batchRemove(String[] ids) {
        return ownerDao.batchRemove(ids);
    }

    @Override
    public int batchInsert(List<OwnerDO> ownerDOList) {
        return ownerDao.batchInsert(ownerDOList);
    }

    @Override
    public int synchronization(String orderId) {
        return ownerDao.synchronization(orderId);
    }

    @Override
    public void duplicate(String orderId) {
        ownerDao.duplicate(orderId);
    }

    @Override
    public void removeCopy(String orderId) {
        ownerDao.removeCopy(orderId);
    }

}
