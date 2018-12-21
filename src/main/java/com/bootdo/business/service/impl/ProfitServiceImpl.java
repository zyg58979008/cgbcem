package com.bootdo.business.service.impl;

import com.bootdo.business.dao.PayListDao;
import com.bootdo.business.dao.ProfitDao;
import com.bootdo.business.domain.ProfitDO;
import com.bootdo.business.service.ProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by xinxi on 2018-10-26.
 */
@Service
public class ProfitServiceImpl implements ProfitService{

    @Autowired
    private ProfitDao profitDao;

    @Override
    public ProfitDO get(Long id){
        return profitDao.get(id);
    }

    @Override
    public List<ProfitDO> list(Map<String, Object> map){
        return profitDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map){
        return profitDao.count(map);
    }

    @Override
    public int save(ProfitDO profit){
        int i=0;
        i=profitDao.save(profit);
        return i;
    }

    @Override
    public int update(ProfitDO profit){
        return profitDao.update(profit);
    }

    @Override
    public int remove(ProfitDO room) {
        return 0;
    }

    @Override
    public int checkHas(Map<String, Object> map) {
        return profitDao.checkHas(map);
    }

    @Override
    public void removeMoney(Long id) {
        profitDao.removeMoney(id);
    }

    @Override
    public void update7(Long id) {
        profitDao.update7(id);
    }

    @Override
    @Async
    public void callLeaseback(Long id, Long deptId) {
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                profitDao.callLeaseback(id,deptId);
            }
        });
        t.run();

    }

}
