package com.bootdo.base.service.impl;

import com.bootdo.base.dao.MerchantDao;
import com.bootdo.base.domain.MerchantDO;
import com.bootdo.base.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    private MerchantDao merchantDao;

    @Override
    public MerchantDO get(String id) {
        return merchantDao.get(id);
    }

    @Override
    public List<MerchantDO> list(Map<String, Object> map) {
        return merchantDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return merchantDao.count(map);
    }

    @Override
    public int save(MerchantDO merchant) {
        return merchantDao.save(merchant);
    }

    @Override
    public int update(MerchantDO merchant) {
        return merchantDao.update(merchant);
    }

    @Override
    public int remove(String id) {
        return merchantDao.remove(id);
    }

    @Override
    public int batchRemove(String[] ids) {
        return merchantDao.batchRemove(ids);
    }
    @Override
    public int batchInsert(List<MerchantDO> merchantDOList) {
        return merchantDao.batchInsert(merchantDOList);
    }
    @Override
    public int synchronization(String orderId) {
        return merchantDao.synchronization(orderId);
    }
    @Override
    public void duplicate(String orderId) {
        merchantDao.duplicate(orderId);
    }
    @Override
    public void removeCopy(String orderId) {
        merchantDao.removeCopy(orderId);
    }
}
