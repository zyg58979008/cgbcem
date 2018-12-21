package com.bootdo.business.dao;

import com.bootdo.business.domain.ProfitDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by xinxi on 2018-10-26.
 */
@Mapper
public interface ProfitDao {

    ProfitDO get(Long id);

    List<ProfitDO> list(Map<String,Object> map);

    int count(Map<String,Object> map);

    int save(ProfitDO profit);

    int update(ProfitDO profit);

    int remove(Long id);

    int checkHas(Map<String, Object> map);

    void callLeaseback(@Param("id") Long id,@Param("deptId") Long deptId);

    void removeMoney(@Param("id") Long id);

    void update7(@Param("id") Long id);
}
