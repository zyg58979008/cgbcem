package com.bootdo.wuye.service;


import com.bootdo.common.domain.PayListDO;
import com.bootdo.common.utils.Query;
import com.bootdo.wuye.domain.ExportDO;
import com.bootdo.wuye.domain.QunuanfeiDO;
import com.bootdo.wuye.domain.QunuanfeiDetailDO;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-08 08:29:06
 */
public interface QunuanManageService {

    QunuanfeiDO get(Long id);

    List<QunuanfeiDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(QunuanfeiDO qunuanDO);

    int update(QunuanfeiDO qunuanDO);

    int remove(Long id);

    int batchRemove(Long[] ids);

    int batchInsert(QunuanfeiDO qunuanfeiDO);

    int checkRoomNum(QunuanfeiDO qunuanDO);

    int batchUpdate(QunuanfeiDO qunuanDO);

    int checkQunuanfei(Long id);

    List<QunuanfeiDetailDO> detailList(Query query);

    QunuanfeiDetailDO getDetail(Long id);

    void removeDetail(Long id);

    int updateDetail(QunuanfeiDetailDO qunuanDODetailDO);

    int countDetail(Query query);

    String getId(String s);

    int savePay(PayListDO payListDO);

    void updateCount(Long qunuanDOId);

    void insertDetail(QunuanfeiDO qunuanDO);

    PayListDO getPay(Long id);

    int updatePay(PayListDO payListDO);

    void updateDetailId(Long id, String orderId);

    List<String> getRoomHas(String id);

    QunuanfeiDO getSum(QunuanfeiDO w);

    QunuanfeiDO getPaySum(Map<String, Object> params);

    QunuanfeiDetailDO getSumMoney(Map<String, Object> params);

}
