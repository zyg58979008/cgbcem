package com.bootdo.business.service.impl;

import com.bootdo.business.dao.AmortizeDao;
import com.bootdo.business.dao.PayCountDao;
import com.bootdo.business.domain.*;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.utils.*;
import com.bootdo.report.domain.ReportDO;
import com.bootdo.system.domain.UserDO;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.bootdo.business.dao.ContractDao;
import com.bootdo.business.service.ContractService;



@Service
public class ContractServiceImpl implements ContractService {
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private PayCountDao payCountDao;
	@Autowired
	private AmortizeDao amortizeDao;
	
	@Override
	public ContractDO get(Long id){
		return contractDao.get(id);
	}
	@Override
    public void saveContractBf(String oid){
        contractDao.saveContractBf(oid);
	}

	@Override
    public void savePayCountBf(String oid){
        contractDao.savePayCountBf(oid);
	}

	@Override
    public void saveAmortizeBf(String oid){
        contractDao.saveAmortizeBf(oid);
	}
	@Override
    public void revertContract(String oid){
        contractDao.revertContract(oid);
	}

	@Override
    public void revertPayCount(String oid){
        contractDao.revertPayCount(oid);
	}

	@Override
    public void deleteContractBf(String oid){
        contractDao.deleteContractBf(oid);
	}

	@Override
    public void deleteAmortizeBf(String oid){
        contractDao.deleteAmortizeBf(oid);
	}

	@Override
    public void deletePayListChechang(String oid){
        contractDao.deletePayListChechang(oid);
	}

	@Override
    public void deletePayCountBf(String oid){
        contractDao.deletePayCountBf(oid);
	}

	@Override
    public void revertAmortize(String oid){
        contractDao.revertAmortize(oid);
	}

	@Override
    public void deleteAmortizeForRevert(ContractDO contractDO){
        contractDao.deleteAmortizeForRevert(contractDO);
	}
	@Override
	public List<PayListDO> pay(Map<String, Object> params) {
		return contractDao.pay(params);
	}
    @Override
    public List<PayListDO> payForChechang(Map<String, Object> params) {
        return contractDao.payForChechang(params);
    }

	@Override
	public int save(ContractDO contract) {
		return contractDao.save(contract);
	}

    @Override
    public int countPayList(Query query) {
        return contractDao.countPayList(query);
    }

    @Override
    public int getCountForSaledShop(String shopCode) {
        return contractDao.getCountForSaledShop(shopCode);
    }


    @Override
    public int countPayListForChechang(Query query) {
        return contractDao.countPayListForChechang(query);
    }
	@Override
	public void savePayLog(ContractLog contractLog) {
		contractDao.savePayLog(contractLog);
	}

    @Override
    public void updateTanxiaoForChechang(ContractDO contractDO) {
        contractDao.updateTanxiaoForChechang(contractDO);
    }

    @Override
    public void updateTanxiaoForChechangD(ContractDO contractDO) {
        contractDao.updateTanxiaoForChechangD(contractDO);
    }
    @Override
    public void updateTanxiaoCurrentMonth(AmortizeDO amortizeDO) {
        contractDao.updateTanxiaoCurrentMonth(amortizeDO);
    }

    @Override
    public void updateTanxiaoOperationMonth(AmortizeDO amortizeDO) {
        contractDao.updateTanxiaoOperationMonth(amortizeDO);
    }

    @Override
    public void insertTanxiao(AmortizeDO amortizeDO) {
        contractDao.insertTanxiao(amortizeDO);
    }

	@Override
	public ContractDO getByCode(String code){
		return contractDao.getByCode(code);
	}

    @Override
    public ContractDO getByOid(String oid){
        return contractDao.getByOid(oid);
    }

    @Override
    public AmortizeDO getTanxiaoFenleiPrice(AmortizeDO amortizeDO){
        return contractDao.getTanxiaoFenleiPrice(amortizeDO);
    }

	@Override
	public List<String> getShop(Long deptId) {
		return contractDao.getShop(deptId);
	}

    @Override
    public List<String> getCodes(Long deptId) {
        return contractDao.getCodes(deptId);
    }

    @Override
    public List<ContractDO> reportlist(Long deptId) {
        return contractDao.reportlist(deptId);
    }

	@Override
	public List<ContractDO> list(Map<String, Object> map){
		return contractDao.list(map);
	}

	@Override
	public List<ContractDO> taizhangList(Map<String, Object> map){
		return contractDao.taizhangList(map);
	}

    @Override
    public List<ContractDO> listForReport(Map<String, Object> map){
        return contractDao.listForReport(map);
    }

    @Override
    public ContractDO totalForReport(Map<String, Object> map){
        return contractDao.totalForReport(map);
    }

    @Override
    public List<ContractDO> cheChangList(Map<String, Object> map){
        return contractDao.cheChangList(map);
    }

	@Override
	public int count(Map<String, Object> map){
		return contractDao.count(map);
	}

    @Override
    public int chechangCount(Map<String, Object> map){
        return contractDao.chechangCount(map);
    }

	@Override
	public List<ContractDO> listDetail(Map<String, Object> map){
		return contractDao.listDetail(map);
	}

	@Override
	public int countDetail(Map<String, Object> map){
		return contractDao.countDetail(map);
	}

	@Override
	public int contractaddSave(ContractDO contract){
        Date now = new Date();
        ToContractDO toContractDO = new ToContractDO();
        ContractDO contractDOold = new ContractDO();
        List<PayCountDO> payCountDOList = new ArrayList<>();
        Double fenleiZujin;
        Double fenleiWuye;
        //把表单内容转换成流
        String sheetName = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        String oid= String.valueOf(System.currentTimeMillis());
        contract.setOid(oid);
        //错误
        int repeat = 0;
        //记录第几行重复
        String num;
        UserDO userDO= ShiroUtils.getUser();
        Long deptId=userDO.getDeptId();
        List<String> contractCodes=contractDao.getCodes(deptId);//查询当前项目下所有合同
        //List<String> contractCodes=contractService.getContractCode(deptId);//查询当前项目下所有商铺
        contractDOold = contractDao.getByOid(contract.getOidOld());
        boolean isContains = UploadUtils.checkHasCode(contract.getContractCode(),contractCodes);
        if(isContains){
            return -1;
        }

        DateTime startContract = formatter.parseDateTime(DateFormat.getDateInstance().format(contract.getContractStartDate()));
        DateTime endContract = formatter.parseDateTime(DateFormat.getDateInstance().format(contract.getContractEndDate()));
        int months = Months.monthsBetween(startContract, endContract).getMonths()+1;
        contract.setAmortizeMonths(months);
        //合同计租面积包含公摊
        contract.setContractRentArea(contract.getContractTrueArea()*1.2);
        if (contract.getContractStartDate().before(now)){
            if (contract.getContractEndDate().before(now)){
                contract.setState("3");//合同状态-失效
            }else{
                contract.setState("0");//合同状态-生效
            }
        }else{
            contract.setState("1");//合同状态-待生效
        }
        toContractDO.setState(contract.getState());
        toContractDO.setOidOld(contract.getOidOld());
        toContractDO.setOid(oid);
        toContractDO.setContractCode(contract.getContractCode());
        contractDao.insertAddToCon(toContractDO);
        //合同起止天数差
        long days=(contract.getContractEndDate().getTime()-contract.getContractStartDate().getTime())/(1000*3600*24)+1;
        Double noYouhui = contract.getUnitPrice()*contract.getRentArea();//每月应收租金：单价×面积
        Double yueFen = DateUtils.getMonths30(contract.getContractStartDate(),contract.getContractEndDate());//月数
        yueFen = UploadUtils.format(yueFen);
        if(contract.getContractPayTotal() == null || contract.getContractPayTotal().equals("")){
            contract.setContractPayTotal(yueFen*noYouhui);
            //计算合同起应收租金
            if(contract.getYouhui()!=null||!contract.getYouhui().equals("")){
                String[] youhui =null;
                youhui = contract.getYouhui().split("[,]");
                int cycle = (int)Math.floor(yueFen/(Integer.valueOf(youhui[0])+Integer.valueOf(youhui[1])));//计算有几个优惠周期：月数÷优惠月数
                Double shiji = yueFen - cycle*Double.valueOf(youhui[1]);//计算应交租金的实际月数：总月数-周期×免月
                contract.setContractPayTotal(shiji*noYouhui);
            }
            if(contract.getDiscount()!=null && !contract.getDiscount().equals("")){
                Double befDiscount = contract.getContractPayTotal();
                contract.setContractPayTotal(befDiscount*contract.getDiscount()*0.1);
            }else{
                contract.setDiscount(10.00);
            }
        }
        if(contract.getDiscount()==null || contract.getDiscount().equals("")){
            contract.setDiscount(10.00);
        }
        contract.setYouhuiUnitPrice(contract.getContractPayTotal()/yueFen/contract.getRentArea());//优惠后每月每平租金
        contract.setFenleiGuanli(contract.getContractPayTotal()-contract.getFenleiWuye()-contract.getFenleiZujin());
        contract.setAmortizePrice(contract.getContractPayTotal()/days);

        contract.setContractPayTotalReceived(0.00);
        contract.setContractPayTotalUnreceived(contract.getContractPayTotal());
        contract.setFenleiZujinReceived(0.00);
        contract.setFenleiWuyeReceived(0.00);
        contract.setFenleiGuanliReceived(0.00);
        contract.setFenleiGuanliUnreceived(contract.getFenleiGuanli());
        contract.setFenleiWuyeUnreceived(contract.getFenleiWuye());
        contract.setFenleiZujinUnreceived(contract.getFenleiZujin());
        
        contract.setReceivedFuwu(contractDOold.getReceivedFuwu());
        contract.setReceivedLvyue(contractDOold.getReceivedLvyue());
        contract.setReceivedZhiliang(contractDOold.getReceivedZhiliang());
        contract.setReceivedZhuangxiu(contractDOold.getReceivedZhuangxiu());

        contract.setBuildingId(contractDOold.getBuildingId());
        contract.setDiscrepancy(contractDOold.getDiscrepancy());
        contract.setTotal(contract.getDiscrepancy()+contract.getContractPayTotal());
        contract.preInsert();
        contract.setShopCode(contractDOold.getShopCode());
        contract.setDeptId(deptId);
        contract.setOrderId(oid);
        contract.setOid(oid);
                            // 新合同保存shop_contract结束
        contractDao.updateOld(contract.getOidOld());// 旧合同四金冲0
        //计算摊销开始
        Double tanxiaoGuanli = contract.getFenleiGuanli()/days;
        Double tanxiaoWuye = contract.getFenleiWuye()/days;
        Double tanxiaoZujin = contract.getFenleiZujin()/days;
        List<String> monthList = new ArrayList<String>();
        List<AmortizeDO> tanxiaoDOList = new ArrayList<>();
        int day;
        try {
            monthList = DateUtils.getMonthBetweenTanxiao(contract.getContractStartDate(),contract.getContractEndDate());
            if(monthList.size()==1){
                AmortizeDO tanxiaoDO = new AmortizeDO();
                if(contract.getContractStartDate().getDate()==1&&DateUtils.isLastDayOfMonth(contract.getContractEndDate())){
                    day = DateUtils.getMaxDay(contract.getContractStartDate())-1;
                }else{
                    day=(int)(contract.getContractEndDate().getDate()-Double.valueOf(contract.getContractStartDate().getDate())+1);
                }
                tanxiaoDO.setDay(day);
                tanxiaoDO.setContractCode(contract.getContractCode());
                tanxiaoDO.setContractor(contract.getContractor());
                tanxiaoDO.setBrand(contract.getBrand());
                tanxiaoDO.setAmortizePriceWuye(contract.getFenleiWuye());
                tanxiaoDO.setAmortizePriceGuanli(contract.getFenleiGuanli());
                tanxiaoDO.setAmortizePriceZujin(contract.getFenleiZujin());
                tanxiaoDO.setAmortizePrice(contract.getAmortizePrice());
                tanxiaoDO.setTotal(tanxiaoDO.getAmortizePriceGuanli()+tanxiaoDO.getAmortizePriceWuye()+tanxiaoDO.getAmortizePriceZujin());
                tanxiaoDO.setMonth(sdf.format(contract.getContractStartDate()));
                tanxiaoDO.setShopCode(contract.getShopCode());
                tanxiaoDO.setOid(oid);
                tanxiaoDO.preInsert();
                tanxiaoDOList.add(tanxiaoDO);
            }
            else{
                Double zujinTotal = 0.00;
                Double wuyeTotal = 0.00;
                Double guanliTotal = 0.00;
                for(int k =1;k<=monthList.size();k++){
                    AmortizeDO tanxiaoDO = new AmortizeDO();
                    if(k==1){
                        if(contract.getContractStartDate().getDate()==1){
                            day = DateUtils.getMaxDay(contract.getContractStartDate())-1;
                        }else {
                            day =(int) ( DateUtils.getMaxDay(contract.getContractStartDate())-Double.valueOf(contract.getContractStartDate().getDate()));
                        }
                        tanxiaoDO.setAmortizePriceWuye(tanxiaoWuye*day);
                        tanxiaoDO.setAmortizePriceGuanli(tanxiaoGuanli*day);
                        tanxiaoDO.setAmortizePriceZujin(tanxiaoZujin*day);
                        tanxiaoDO.setDay(day);
                        zujinTotal = zujinTotal + tanxiaoZujin*day;
                        wuyeTotal = wuyeTotal + tanxiaoWuye*day;
                        guanliTotal = guanliTotal + tanxiaoGuanli*day;
                        wuyeTotal = UploadUtils.format(wuyeTotal);
                        zujinTotal = UploadUtils.format(zujinTotal);
                        guanliTotal = UploadUtils.format(guanliTotal);
                    }else if(k==monthList.size()){
                        day = contract.getContractEndDate().getDate();
                        tanxiaoDO.setDay(day);
							wuyeTotal = UploadUtils.format(wuyeTotal);
							zujinTotal = UploadUtils.format(zujinTotal);
							guanliTotal = UploadUtils.format(guanliTotal);
                        Double one = contract.getFenleiWuye();
                        Double two = contract.getFenleiGuanli();
                        Double three = contract.getFenleiZujin();
                        tanxiaoDO.setAmortizePriceWuye(one - wuyeTotal);
                        tanxiaoDO.setAmortizePriceGuanli(two - guanliTotal);
                        tanxiaoDO.setAmortizePriceZujin(three - zujinTotal);
                        tanxiaoDO.setDay(day);
                    }else{
                        day = DateUtils.getMaxDay(sdf.parse(monthList.get(k-1)))-1;
                        tanxiaoDO.setAmortizePriceWuye(tanxiaoWuye*day);
                        tanxiaoDO.setAmortizePriceGuanli(tanxiaoGuanli*day);
                        tanxiaoDO.setAmortizePriceZujin(tanxiaoZujin*day);
                        tanxiaoDO.setDay(day);
                        zujinTotal = zujinTotal + tanxiaoZujin*day;
                        wuyeTotal = wuyeTotal + tanxiaoWuye*day;
                        guanliTotal = guanliTotal + tanxiaoGuanli*day;
                        wuyeTotal = UploadUtils.format(wuyeTotal);
                        zujinTotal = UploadUtils.format(zujinTotal);
                        guanliTotal = UploadUtils.format(guanliTotal);
                    }
                    tanxiaoDO.setContractCode(contract.getContractCode());
                    tanxiaoDO.setContractor(contract.getContractor());
                    tanxiaoDO.setBrand(contract.getBrand());
                    tanxiaoDO.setBuildingId(contract.getBuildingId());
                    tanxiaoDO.setAmortizePrice(contract.getAmortizePrice());
                    tanxiaoDO.setTotal(tanxiaoDO.getAmortizePriceGuanli()+tanxiaoDO.getAmortizePriceWuye()+tanxiaoDO.getAmortizePriceZujin());
                    tanxiaoDO.setMonth(monthList.get(k-1));
                    tanxiaoDO.setShopCode(contract.getShopCode());
                    tanxiaoDO.setOid(oid);
                    tanxiaoDO.preInsert();
                    tanxiaoDOList.add(tanxiaoDO);
                }
            }
            if(tanxiaoDOList.size()>0){
                int i=contractDao.batchInsertTanxiao(tanxiaoDOList);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        
        
        
 
        Double priceMonth =contract.getContractPayTotal()/yueFen;
        Double wuyeMonth =contract.getFenleiWuye()/yueFen;
        Double zujinMonth =contract.getFenleiZujin()/yueFen;
        Double guanliMonth =contract.getFenleiGuanli()/yueFen;
        priceMonth = UploadUtils.format(priceMonth);
        wuyeMonth = UploadUtils.format(wuyeMonth);
        zujinMonth = UploadUtils.format(zujinMonth);
        guanliMonth = UploadUtils.format(guanliMonth);

        if(contract.getPayType().equals("1")){
            try {
                Double sMonth= Double.valueOf(0);
                Double eMonth= Double.valueOf(0);
                monthList = DateUtils.getMonthBetween(contract.getContractStartDate(),contract.getContractEndDate(),contract.getPayType());
                if(monthList.size()==1){
                    PayCountDO pc=new PayCountDO();
                    pc.setFenleiZujin(contract.getFenleiZujin());
                    pc.setFenleiWuye(contract.getFenleiWuye());
                    pc.setContractPayTotal(contract.getContractPayTotal());
                    pc.setContractPayTotalReceived(0.00);
                    pc.setFenleiGuanli(contract.getFenleiGuanli());
                    pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
                    pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
                    pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
                    pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
                    pc.setCountStartDate(contract.getContractStartDate());
                    pc.setCountEndDate(contract.getContractEndDate());
                    pc.setReceivableZhiliang(contract.getReceivableZhiliang());
                    pc.setReceivableFuwu(contract.getReceivableFuwu());
                    pc.setReceivableLvyue(contract.getReceivableLvyue());
                    pc.setReceivableZhuangxiu(contract.getReceivableZhuangxiu());
                    pc.setReceivedZhiliang(contract.getReceivedZhiliang());
                    pc.setReceivedFuwu(contract.getReceivedFuwu());
                    pc.setReceivedLvyue(contract.getReceivedLvyue());
                    pc.setReceivedZhuangxiu(contract.getReceivedZhuangxiu());
                    pc.setDiscrepancy(contract.getDiscrepancy());
                    pc.setState(contract.getState());
                    pc.setBuildingId(contract.getBuildingId());
                    pc.setContractCode(contract.getContractCode());
                    pc.setShopCode(contract.getShopCode());
                    pc.setBrand(contract.getBrand());
                    pc.setContractor(contract.getContractor());
                    pc.setUnitPrice(contract.getUnitPrice());
                    pc.setFenleiGuanliReceived(0.00);
                    pc.setFenleiWuyeReceived(0.00);
                    pc.setFenleiZujinReceived(0.00);
                    pc.preInsert();
                    pc.setOid(oid);
                    payCountDOList.add(pc);
                }
                else{
                    Double zujinTotal = 0.00;
                    Double wuyeTotal = 0.00;
                    Double priceTotal = 0.00;
                    Double guanliTotal = 0.00;
                    Date st = new Date();
                    Date en = new Date();
                    for(int k =1;k<=monthList.size();k++){
                        PayCountDO pc=new PayCountDO();
                        if(k==1&&k==monthList.size()){
                            pc.setFenleiZujin(contract.getFenleiZujin());
                            pc.setFenleiWuye(contract.getFenleiWuye());
                            pc.setContractPayTotal(contract.getContractPayTotal());
                            pc.setFenleiGuanli(pc.getContractPayTotal()-pc.getFenleiZujin()-pc.getFenleiWuye());
                            pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
                            pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
                            pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
                            pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
                            pc.setCountStartDate(contract.getContractStartDate());
                            pc.setCountEndDate(contract.getContractEndDate());
                        }else{
                            if(k==1){
                                if(contract.getContractStartDate().getDate()==1){
                                    sMonth= Double.valueOf(1);
                                }else {
                                    sMonth= Double.valueOf(( DateUtils.getMaxDay(contract.getContractStartDate())-Double.valueOf(contract.getContractStartDate().getDate()))/30);
                                    sMonth = UploadUtils.format(sMonth);
                                }
                                st = contract.getContractStartDate();
                                pc.setFenleiZujin(zujinMonth);
                                pc.setFenleiWuye(wuyeMonth);
                                pc.setContractPayTotal(priceMonth);
                                pc.setFenleiGuanli(guanliMonth);
                                pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
                                pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
                                pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
                                pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
                                pc.setCountStartDate(st);
                                pc.setCountEndDate(DateUtils.getLastDate(st,1));
                                priceTotal = pc.getContractPayTotal() + priceTotal;
                                wuyeTotal = pc.getFenleiWuye() + wuyeTotal;
                                zujinTotal = pc.getFenleiZujin() + zujinTotal;
                                guanliTotal = pc.getFenleiGuanli() + guanliTotal;
                                en = pc.getCountEndDate();
                            }else if(k==monthList.size()){
                                /*if(DateUtils.isLastDayOfMonth(contract.getContractEndDate())){
                                    eMonth=Double.valueOf(1);
                                }else {
                                    eMonth=Double.valueOf(df.format((Double.valueOf(contract.getContractEndDate().getDate())/30)));
									eMonth = UploadUtils.format(eMonth);
                                }*/
                                st = DateUtils.dayAddOne(en);
                                pc.setFenleiZujin(contract.getFenleiZujin() - zujinTotal);
                                pc.setFenleiWuye(contract.getFenleiWuye() - wuyeTotal);
                                pc.setContractPayTotal(contract.getContractPayTotal() - priceTotal);
                                pc.setFenleiGuanli(contract.getFenleiGuanli()-guanliTotal);
                                pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
                                pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
                                pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
                                pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
                                pc.setCountStartDate(st);
                                pc.setCountEndDate(contract.getContractEndDate());

                            }else{
                                st = DateUtils.dayAddOne(en);
                                pc.setFenleiZujin(zujinMonth);
                                pc.setFenleiWuye(wuyeMonth);
                                pc.setContractPayTotal(priceMonth);
                                pc.setFenleiGuanli(guanliMonth);
                                pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
                                pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
                                pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
                                pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
                                pc.setCountStartDate(st);
                                pc.setCountEndDate(DateUtils.getLastDate(st,1));
                                priceTotal = pc.getContractPayTotal() + priceTotal;
                                wuyeTotal = pc.getFenleiWuye() + wuyeTotal;
                                zujinTotal = pc.getFenleiZujin() + zujinTotal;
                                en = pc.getCountEndDate();
                            }
                        }
                        pc.setReceivableZhiliang(contract.getReceivableZhiliang());
                        pc.setReceivableFuwu(contract.getReceivableFuwu());
                        pc.setReceivableLvyue(contract.getReceivableLvyue());
                        pc.setReceivableZhuangxiu(contract.getReceivableZhuangxiu());
                        pc.setReceivedZhiliang(contract.getReceivedZhiliang());
                        pc.setReceivedFuwu(contract.getReceivedFuwu());
                        pc.setReceivedLvyue(contract.getReceivedLvyue());
                        pc.setReceivedZhuangxiu(contract.getReceivedZhuangxiu());
                        pc.setContractPayTotalReceived(0.00);
                        pc.setDiscrepancy(contract.getDiscrepancy());
                        pc.setState(contract.getState());
                        pc.setBuildingId(contract.getBuildingId());
                        pc.setContractCode(contract.getContractCode());
                        pc.setShopCode(contract.getShopCode());
                        pc.setBrand(contract.getBrand());
                        pc.setUnitPrice(contract.getUnitPrice());
                        pc.setFenleiGuanliReceived(0.00);
                        pc.setFenleiWuyeReceived(0.00);
                        pc.setFenleiZujinReceived(0.00);
                        pc.setBrand(contract.getBrand());
                        pc.setContractor(contract.getContractor());
                        pc.setOid(oid);
                        pc.preInsert();
                        payCountDOList.add(pc);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if(contract.getPayType().equals("2")){
            Double sMonth= Double.valueOf(0);
            Double eMonth= Double.valueOf(0);
            Double zujinTotal = 0.00;
            Double wuyeTotal = 0.00;
            Double priceTotal = 0.00;
            Double guanliTotal = 0.00;
            Date st = new Date();
            Date en = new Date();
            String[] eachMonth;
            int cycle =DateUtils.getMonthF(contract.getContractStartDate(),contract.getContractEndDate())/3;
            int cyclerest =DateUtils.getMonthF(contract.getContractStartDate(),contract.getContractEndDate())%3;
            try {
                monthList = DateUtils.getMonthBetween(contract.getContractStartDate(),contract.getContractEndDate(),contract.getPayType());
                for(int k = 1;k<=monthList.size();k++){
                    PayCountDO pc=new PayCountDO();
                    eachMonth = monthList.get(k-1).split("[|]");

                    if(k==1&&k==monthList.size()){
                        pc.setFenleiZujin(contract.getFenleiZujin());
                        pc.setFenleiWuye(contract.getFenleiWuye());
                        pc.setContractPayTotal(contract.getContractPayTotal());
                        pc.setFenleiGuanli(contract.getFenleiGuanli());
                        pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
                        pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
                        pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
                        pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
                        pc.setCountStartDate(contract.getContractStartDate());
                        pc.setCountEndDate(contract.getContractEndDate());
                    }else{
                        if(k==1){
                            if(contract.getContractStartDate().getDate()==1){
                                sMonth= Double.valueOf(1);
                            }else {
                                sMonth= Double.valueOf(( DateUtils.getMaxDay(contract.getContractStartDate())-Double.valueOf(contract.getContractStartDate().getDate()))/30);
                                sMonth = UploadUtils.format(sMonth);
                            }
                            st = contract.getContractStartDate();
                            pc.setFenleiZujin(zujinMonth*3);
                            pc.setFenleiWuye(wuyeMonth*3);
                            pc.setContractPayTotal(priceMonth*3);
                            pc.setFenleiGuanli(guanliMonth*3);
                            pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
                            pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
                            pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
                            pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
                            pc.setCountStartDate(st);
                            pc.setCountEndDate(DateUtils.getLastDate(st,3));
                            priceTotal = pc.getContractPayTotal() + priceTotal;
                            wuyeTotal = pc.getFenleiWuye() + wuyeTotal;
                            zujinTotal = pc.getFenleiZujin() + zujinTotal;
                            guanliTotal = pc.getFenleiGuanli() + guanliTotal;
                            en = pc.getCountEndDate();
                        }else if(k==monthList.size()){
                            st = DateUtils.dayAddOne(en);
                            pc.setFenleiZujin(contract.getFenleiZujin() - zujinTotal);
                            pc.setFenleiWuye(contract.getFenleiWuye() - wuyeTotal);
                            pc.setContractPayTotal(contract.getContractPayTotal() - priceTotal);
                            pc.setFenleiGuanli(contract.getFenleiGuanli()-guanliTotal);
                            pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
                            pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
                            pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
                            pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
                            pc.setCountStartDate(st);
                            pc.setCountEndDate(contract.getContractEndDate());
                        }else{
                            st = DateUtils.dayAddOne(en);
                            pc.setFenleiZujin(zujinMonth*3);
                            pc.setFenleiWuye(wuyeMonth*3);
                            pc.setContractPayTotal(priceMonth*3);
                            pc.setFenleiGuanli(guanliMonth*3);
                            pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
                            pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
                            pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
                            pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
                            pc.setCountStartDate(st);
                            pc.setCountEndDate(DateUtils.getLastDate(st,3));
                            priceTotal = pc.getContractPayTotal() + priceTotal;
                            wuyeTotal = pc.getFenleiWuye() + wuyeTotal;
                            zujinTotal = pc.getFenleiZujin() + zujinTotal;
                            guanliTotal = pc.getFenleiGuanli() + guanliTotal;
                            en = pc.getCountEndDate();
                        }
                    }
                    pc.setReceivableZhiliang(contract.getReceivableZhiliang());
                    pc.setReceivableFuwu(contract.getReceivableFuwu());
                    pc.setReceivableLvyue(contract.getReceivableLvyue());
                    pc.setReceivableZhuangxiu(contract.getReceivableZhuangxiu());
                    pc.setReceivedZhiliang(contract.getReceivedZhiliang());
                    pc.setReceivedFuwu(contract.getReceivedFuwu());
                    pc.setReceivedLvyue(contract.getReceivedLvyue());
                    pc.setReceivedZhuangxiu(contract.getReceivedZhuangxiu());
                    pc.setDiscrepancy(contract.getDiscrepancy());
                    pc.setState(contract.getState());
                    pc.setBuildingId(contract.getBuildingId());
                    pc.setContractCode(contract.getContractCode());
                    pc.setShopCode(contract.getShopCode());
                    pc.setBrand(contract.getBrand());
                    pc.setUnitPrice(contract.getUnitPrice());
                    pc.setFenleiGuanliReceived(0.00);
                    pc.setFenleiWuyeReceived(0.00);
                    pc.setFenleiZujinReceived(0.00);
                    pc.setContractPayTotalReceived(0.00);
                    pc.preInsert();
                    pc.setBrand(contract.getBrand());
                    pc.setContractor(contract.getContractor());
                    pc.setOid(oid);
                    payCountDOList.add(pc);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if(contract.getPayType().equals("3")){
            Double sMonth= Double.valueOf(0);
            Double eMonth= Double.valueOf(0);
            int monthCha = DateUtils.getMonthF(contract.getContractStartDate(),contract.getContractEndDate());
            if(monthCha<12){
                PayCountDO pc=new PayCountDO();
                pc.setReceivableZhiliang(contract.getReceivableZhiliang());
                pc.setReceivableFuwu(contract.getReceivableFuwu());
                pc.setReceivableLvyue(contract.getReceivableLvyue());
                pc.setReceivableZhuangxiu(contract.getReceivableZhuangxiu());
                pc.setReceivedZhiliang(contract.getReceivedZhiliang());
                pc.setReceivedFuwu(contract.getReceivedFuwu());
                pc.setReceivedLvyue(contract.getReceivedLvyue());
                pc.setReceivedZhuangxiu(contract.getReceivedZhuangxiu());
                pc.setDiscrepancy(contract.getDiscrepancy());
                pc.setState(contract.getState());
                pc.setFenleiZujin(contract.getFenleiZujin());
                pc.setFenleiWuye(contract.getFenleiWuye());
                pc.setContractPayTotal(contract.getContractPayTotal());
                pc.setFenleiGuanli(contract.getFenleiGuanli());
                pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
                pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
                pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
                pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
                pc.setCountStartDate(contract.getContractStartDate());
                pc.setCountEndDate(contract.getContractEndDate());
                pc.setBuildingId(contract.getBuildingId());
                pc.setContractCode(contract.getContractCode());
                pc.setShopCode(contract.getShopCode());
                pc.setBrand(contract.getBrand());
                pc.setUnitPrice(contract.getUnitPrice());
                pc.setFenleiGuanliReceived(0.00);
                pc.setFenleiWuyeReceived(0.00);
                pc.setFenleiZujinReceived(0.00);
                pc.setContractPayTotalReceived(0.00);
                pc.preInsert();
                pc.setBrand(contract.getBrand());
                pc.setContractor(contract.getContractor());
                pc.setOid(oid);
                payCountDOList.add(pc);
            }else{
                //如果大于等于12个月的情况
                String[] eachMonth;
                int cycle =DateUtils.getMonthF(contract.getContractStartDate(),contract.getContractEndDate())/12;
                int cyclerest =DateUtils.getMonthF(contract.getContractStartDate(),contract.getContractEndDate())%12;
                try {
                    monthList = DateUtils.getMonthBetween(contract.getContractStartDate(),contract.getContractEndDate(),contract.getPayType());
                    Double zujinTotal = 0.00;
                    Double wuyeTotal = 0.00;
                    Double priceTotal = 0.00;
                    Double guanliTotal = 0.00;
                    Date st = new Date();
                    Date en = new Date();
                    for(int k = 1;k<=monthList.size();k++){
                        PayCountDO pc=new PayCountDO();
                        eachMonth = monthList.get(k-1).split("[|]");
                        if(k==1&&k==monthList.size()){
                            pc.setFenleiZujin(contract.getFenleiZujin());
                            pc.setFenleiWuye(contract.getFenleiWuye());
                            pc.setContractPayTotal(contract.getContractPayTotal());
                            pc.setFenleiGuanli(contract.getFenleiGuanli());
                            pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
                            pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
                            pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
                            pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
                            pc.setCountStartDate(contract.getContractStartDate());
                            pc.setCountEndDate(contract.getContractEndDate());
                        }else{
                            if(k==1){
                                if(contract.getContractStartDate().getDate()==1){
                                    sMonth= Double.valueOf(1);
                                }else {
                                    sMonth= Double.valueOf(( DateUtils.getMaxDay(contract.getContractStartDate())-Double.valueOf(contract.getContractStartDate().getDate()))/30);
                                    sMonth = UploadUtils.format(sMonth);
                                }
                                st = contract.getContractStartDate();
                                pc.setFenleiZujin(zujinMonth*12);
                                pc.setFenleiWuye(wuyeMonth*12);
                                pc.setContractPayTotal(priceMonth*12);
                                pc.setFenleiGuanli(guanliMonth*12);
                                pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
                                pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
                                pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
                                pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
                                pc.setCountStartDate(st);
                                pc.setCountEndDate(DateUtils.getLastDate(st,12));
                                priceTotal = pc.getContractPayTotal() + priceTotal;
                                wuyeTotal = pc.getFenleiWuye() + wuyeTotal;
                                zujinTotal = pc.getFenleiZujin() + zujinTotal;
                                guanliTotal = pc.getFenleiGuanli() + guanliTotal;
                                en = pc.getCountEndDate();
                            }else if(k==monthList.size()){
                                st = DateUtils.dayAddOne(en);
                                pc.setFenleiZujin(contract.getFenleiZujin()-zujinTotal);
                                pc.setFenleiWuye(contract.getFenleiWuye()-wuyeTotal);
                                pc.setContractPayTotal(contract.getContractPayTotal()-priceTotal);
                                pc.setFenleiGuanli(contract.getFenleiGuanli() - guanliTotal);
                                pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
                                pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
                                pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
                                pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
                                pc.setCountStartDate(st);
                                pc.setCountEndDate(contract.getContractEndDate());
                            }else{
                                st = DateUtils.dayAddOne(en);
                                pc.setFenleiZujin(zujinMonth*12);
                                pc.setFenleiWuye(wuyeMonth*12);
                                pc.setContractPayTotal(priceMonth*12);
                                pc.setFenleiGuanli(guanliMonth*12);
                                pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
                                pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
                                pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
                                pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
                                pc.setCountStartDate(st);
                                pc.setCountEndDate(DateUtils.getLastDate(st,12));
                                priceTotal = pc.getContractPayTotal() + priceTotal;
                                wuyeTotal = pc.getFenleiWuye() + wuyeTotal;
                                zujinTotal = pc.getFenleiZujin() + zujinTotal;
                                guanliTotal = pc.getFenleiGuanli() + guanliTotal;
                                en = pc.getCountEndDate();
                            }
                        }
                        pc.setReceivableZhiliang(contract.getReceivableZhiliang());
                        pc.setReceivableFuwu(contract.getReceivableFuwu());
                        pc.setReceivableLvyue(contract.getReceivableLvyue());
                        pc.setReceivableZhuangxiu(contract.getReceivableZhuangxiu());
                        pc.setReceivedZhiliang(contract.getReceivedZhiliang());
                        pc.setReceivedFuwu(contract.getReceivedFuwu());
                        pc.setReceivedLvyue(contract.getReceivedLvyue());
                        pc.setReceivedZhuangxiu(contract.getReceivedZhuangxiu());
                        pc.setDiscrepancy(contract.getDiscrepancy());
                        pc.setState(contract.getState());
                        pc.setBuildingId(contract.getBuildingId());
                        pc.setContractCode(contract.getContractCode());
                        pc.setShopCode(contract.getShopCode());
                        pc.setBrand(contract.getBrand());
                        pc.setUnitPrice(contract.getUnitPrice());
                        pc.setFenleiGuanliReceived(0.00);
                        pc.setFenleiWuyeReceived(0.00);
                        pc.setFenleiZujinReceived(0.00);
                        pc.setContractPayTotalReceived(0.00);
                        pc.setBrand(contract.getBrand());
                        pc.preInsert();
                        pc.setContractor(contract.getContractor());
                        pc.setOid(oid);
                        payCountDOList.add(pc);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }else if(contract.getPayType().equals("4")){
            Double sMonth= Double.valueOf(0);
            Double eMonth= Double.valueOf(0);
            int monthCha = DateUtils.getMonthF(contract.getContractStartDate(),contract.getContractEndDate());
            if(monthCha<6){
                PayCountDO pc=new PayCountDO();
                pc.setReceivableZhiliang(contract.getReceivableZhiliang());
                pc.setReceivableFuwu(contract.getReceivableFuwu());
                pc.setReceivableLvyue(contract.getReceivableLvyue());
                pc.setReceivableZhuangxiu(contract.getReceivableZhuangxiu());
                pc.setReceivedZhiliang(contract.getReceivedZhiliang());
                pc.setReceivedFuwu(contract.getReceivedFuwu());
                pc.setReceivedLvyue(contract.getReceivedLvyue());
                pc.setReceivedZhuangxiu(contract.getReceivedZhuangxiu());
                pc.setDiscrepancy(contract.getDiscrepancy());
                pc.setState(contract.getState());
                pc.setFenleiZujin(contract.getFenleiZujin());
                pc.setFenleiWuye(contract.getFenleiWuye());
                pc.setContractPayTotal(contract.getContractPayTotal());
                pc.setFenleiGuanli(contract.getFenleiGuanli());
                pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
                pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
                pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
                pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
                pc.setCountStartDate(contract.getContractStartDate());
                pc.setCountEndDate(contract.getContractEndDate());
                pc.setBuildingId(contract.getBuildingId());
                pc.setContractCode(contract.getContractCode());
                pc.setShopCode(contract.getShopCode());
                pc.setBrand(contract.getBrand());
                pc.setUnitPrice(contract.getUnitPrice());
                pc.setFenleiGuanliReceived(0.00);
                pc.setFenleiWuyeReceived(0.00);
                pc.setFenleiZujinReceived(0.00);
                pc.setContractPayTotalReceived(0.00);
                pc.setBrand(contract.getBrand());
                pc.preInsert();
                pc.setContractor(contract.getContractor());
                pc.setOid(oid);
                payCountDOList.add(pc);
            }else{
                //如果大于等于6个月的情况
                String[] eachMonth;
                Double zujinTotal = 0.00;
                Double wuyeTotal = 0.00;
                Double priceTotal = 0.00;
                Double guanliTotal = 0.00;
                Date st = new Date();
                Date en = new Date();
                int cycle =DateUtils.getMonthF(contract.getContractStartDate(),contract.getContractEndDate())/6;
                int cyclerest =DateUtils.getMonthF(contract.getContractStartDate(),contract.getContractEndDate())%6;
                try {
                    monthList = DateUtils.getMonthBetween(contract.getContractStartDate(),contract.getContractEndDate(),contract.getPayType());
                    for(int k = 1;k<=monthList.size();k++){
                        PayCountDO pc=new PayCountDO();
                        eachMonth = monthList.get(k-1).split("[|]");
                        if(k==1&&k==monthList.size()){
                            pc.setFenleiZujin(contract.getFenleiZujin());
                            pc.setFenleiWuye(contract.getFenleiWuye());
                            pc.setContractPayTotal(contract.getContractPayTotal());
                            pc.setFenleiGuanli(contract.getFenleiGuanli());
                            pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
                            pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
                            pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
                            pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
                            pc.setCountStartDate(contract.getContractStartDate());
                            pc.setCountEndDate(contract.getContractEndDate());
                        }else{
                            if(k==1){
                                if(contract.getContractStartDate().getDate()==1){
                                    sMonth= Double.valueOf(1);
                                }else {
                                    sMonth= Double.valueOf(( DateUtils.getMaxDay(contract.getContractStartDate())-Double.valueOf(contract.getContractStartDate().getDate()))/30);
                                    sMonth = UploadUtils.format(sMonth);
                                }
                                st = contract.getContractStartDate();
                                pc.setFenleiZujin(zujinMonth*6);
                                pc.setFenleiWuye(wuyeMonth*6);
                                pc.setContractPayTotal(priceMonth*6);
                                pc.setFenleiGuanli(guanliMonth*6);
                                pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
                                pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
                                pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
                                pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
                                pc.setCountStartDate(st);
                                pc.setCountEndDate(DateUtils.getLastDate(st,6));
                                priceTotal = pc.getContractPayTotal() + priceTotal;
                                wuyeTotal = pc.getFenleiWuye() + wuyeTotal;
                                zujinTotal = pc.getFenleiZujin() + zujinTotal;
                                guanliTotal = pc.getFenleiGuanli() + guanliTotal;
                                en = pc.getCountEndDate();
                            }else if(k==monthList.size()){
                                st = DateUtils.dayAddOne(en);
                                pc.setFenleiZujin(contract.getFenleiZujin()-zujinTotal);
                                pc.setFenleiWuye(contract.getFenleiWuye()-wuyeTotal);
                                pc.setContractPayTotal(contract.getContractPayTotal()-priceTotal);
                                pc.setFenleiGuanli(contract.getFenleiGuanli() - guanliTotal);
                                pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
                                pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
                                pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
                                pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
                                pc.setCountStartDate(st);
                                pc.setCountEndDate(contract.getContractEndDate());
                            }else{
                                st = DateUtils.dayAddOne(en);
                                pc.setFenleiZujin(zujinMonth*6);
                                pc.setFenleiWuye(wuyeMonth*6);
                                pc.setContractPayTotal(priceMonth*6);
                                pc.setFenleiGuanli(guanliMonth*6);
                                pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
                                pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
                                pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
                                pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
                                pc.setCountStartDate(st);
                                pc.setCountEndDate(DateUtils.getLastDate(st,6));
                                priceTotal = pc.getContractPayTotal() + priceTotal;
                                wuyeTotal = pc.getFenleiWuye() + wuyeTotal;
                                zujinTotal = pc.getFenleiZujin() + zujinTotal;
                                guanliTotal = pc.getFenleiGuanli() + guanliTotal;
                                en = pc.getCountEndDate();
                            }
                        }
                        pc.setReceivableZhiliang(contract.getReceivableZhiliang());
                        pc.setReceivableFuwu(contract.getReceivableFuwu());
                        pc.setReceivableLvyue(contract.getReceivableLvyue());
                        pc.setReceivableZhuangxiu(contract.getReceivableZhuangxiu());
                        pc.setReceivedZhiliang(contract.getReceivedZhiliang());
                        pc.setReceivedFuwu(contract.getReceivedFuwu());
                        pc.setReceivedLvyue(contract.getReceivedLvyue());
                        pc.setReceivedZhuangxiu(contract.getReceivedZhuangxiu());
                        pc.setDiscrepancy(contract.getDiscrepancy());
                        pc.setState(contract.getState());
                        pc.setBuildingId(contract.getBuildingId());
                        pc.setContractCode(contract.getContractCode());
                        pc.setShopCode(contract.getShopCode());
                        pc.setBrand(contract.getBrand());
                        pc.setUnitPrice(contract.getUnitPrice());
                        pc.setFenleiGuanliReceived(0.00);
                        pc.setFenleiWuyeReceived(0.00);
                        pc.setFenleiZujinReceived(0.00);
                        pc.setContractPayTotalReceived(0.00);
                        pc.setBrand(contract.getBrand());
                        pc.preInsert();
                        pc.setContractor(contract.getContractor());
                        pc.setOid(oid);
                        payCountDOList.add(pc);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        }else if(contract.getPayType().equals("5")){
            PayCountDO pc=new PayCountDO();
            pc.setReceivableZhiliang(contract.getReceivableZhiliang());
            pc.setReceivableFuwu(contract.getReceivableFuwu());
            pc.setReceivableLvyue(contract.getReceivableLvyue());
            pc.setReceivableZhuangxiu(contract.getReceivableZhuangxiu());
            pc.setReceivedZhiliang(contract.getReceivedZhiliang());
            pc.setReceivedFuwu(contract.getReceivedFuwu());
            pc.setReceivedLvyue(contract.getReceivedLvyue());
            pc.setReceivedZhuangxiu(contract.getReceivedZhuangxiu());
            pc.setDiscrepancy(contract.getDiscrepancy());
            pc.setState(contract.getState());
            pc.setFenleiZujin(contract.getFenleiZujin());
            pc.setFenleiWuye(contract.getFenleiWuye());
            pc.setContractPayTotal(contract.getContractPayTotal());
            pc.setFenleiGuanli(contract.getFenleiGuanli());
            pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
            pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
            pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
            pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
            pc.setCountStartDate(contract.getContractStartDate());
            pc.setCountEndDate(contract.getContractEndDate());
            pc.setBuildingId(contract.getBuildingId());
            pc.setContractCode(contract.getContractCode());
            pc.setShopCode(contract.getShopCode());
            pc.setBrand(contract.getBrand());
            pc.setUnitPrice(contract.getUnitPrice());
            pc.setFenleiGuanliReceived(0.00);
            pc.setFenleiWuyeReceived(0.00);
            pc.setFenleiZujinReceived(0.00);
            pc.setContractPayTotalReceived(0.00);
            pc.setContractor(contract.getContractor());
            pc.setOid(oid);
            pc.preInsert();
            payCountDOList.add(pc);
        }
        if(payCountDOList.size()>0){
            int i=payCountDao.batchInsertFinal(payCountDOList);
            /*payCountDao.savePayFinal();
            payCountDao.deleteCount();*/
        }
        ContractLog contractLog=new ContractLog();
        contractLog.setName("合同续签");
        contractLog.setOrderId(oid);
        contractLog.preInsert();
        contractDao.saveLog(contractLog);

		return contractDao.save(contract);
	}
	
	@Override
	public int updateContract(ContractDO contract){
        ContractDO contractDOold = new ContractDO();
        contractDOold = contractDao.get(contract.getId());
        if(!(contractDOold.getReceivableFuwu().equals(contract.getReceivableFuwu()))){
            if(contractDOold.getReceivedFuwu()!=0.00){
                return -1;
            }
        }else if(!(contractDOold.getReceivableLvyue().equals(contract.getReceivableLvyue()))){
            if(contractDOold.getReceivedLvyue()!=0.00){
                return -2;
            }
        }else if(!(contractDOold.getReceivableZhiliang().equals(contract.getReceivableZhiliang()))){
            if(contractDOold.getReceivedZhiliang()!=0.00){
                return -3;
            }
        }else if(!(contractDOold.getReceivableZhuangxiu().equals(contract.getReceivableZhuangxiu()))){
            if(contractDOold.getReceivedZhuangxiu()!=0.00){
                return -4;
            }
        }
        contract.setDiscrepancy(contract.getReceivableFuwu()+contract.getReceivableZhiliang()+contract.getReceivableLvyue()+contract.getReceivableZhuangxiu()-contractDOold.getReceivedLvyue()-contractDOold.getReceivedFuwu()-contractDOold.getReceivedZhiliang()-contractDOold.getReceivedZhuangxiu());
		PayCountDO payCountDO = new PayCountDO();
        payCountDO.setDiscrepancy(contract.getDiscrepancy());
		payCountDO.setContractCode(contract.getContractCode());
		payCountDO.setBrand(contract.getBrand());
		payCountDO.setDelFlag(contract.getDelFlag());
		payCountDO.setOid(contract.getOid());
        payCountDO.setReceivableFuwu(contract.getReceivableFuwu());
        payCountDO.setReceivableLvyue(contract.getReceivableLvyue());
        payCountDO.setReceivableZhiliang(contract.getReceivableZhiliang());
        payCountDO.setReceivableZhuangxiu(contract.getReceivableZhuangxiu());
		payCountDao.update(payCountDO);
		AmortizeDO amortizeDO = new AmortizeDO();
		amortizeDO.setContractCode(contract.getContractCode());
		amortizeDO.setBrand(contract.getBrand());
		amortizeDO.setDelFlag(contract.getDelFlag());
		amortizeDO.setOid(contract.getOid());
		amortizeDao.update(amortizeDO);
		ContractLog contractLog = new ContractLog();
		contractLog.setContractId(contract.getContractCode());
		contractLog.setOid(contract.getOid());
		contractDao.updateLog(contractLog);
		ToContractDO toContractDO = new ToContractDO();
		toContractDO.setContractCode(contract.getContractCode());
		toContractDO.setOid(contract.getOid());
		contractDao.updateToContract(toContractDO);
		return contractDao.update(contract);
	}
    @Override
    public int update(ContractDO contract){
        return contractDao.update(contract);
    }
    @Override
    public int updateChechangContract(ContractDO contract){
        return contractDao.updateChechangContract(contract);
    }

	@Override
	public int remove(Long id){
		return contractDao.remove(id);
	}

	@Override
	public int batchInsert(List<ContractDO> contractDOList) {
		return contractDao.batchInsert(contractDOList);
	}

	@Override
	public void updateToContractState() {
        contractDao.updateToContractState();
	}

	@Override
	public int batchInsertTanxiao(List<AmortizeDO> tanxiaoDOList) {
		return contractDao.batchInsertTanxiao(tanxiaoDOList);
	}

	@Override
	public void batchInsertToShop(List<ToContractDO> toContractDOList) {
		 contractDao.batchInsertToShop(toContractDOList);
	}

	@Override
	public int batchRemove(Long[] ids){
		return contractDao.batchRemove(ids);
	}
	@Override
	public void saveLog(ContractLog contractLog) {
		contractDao.saveLog(contractLog);
	}
	@Override
	public String[]  getDoubleByCo() {
		return contractDao.getDoubleByCo();
	}

	@Override
	public ContractDO getDate(Long buildingId,String shopCode){
		return contractDao.getDate(buildingId,shopCode);
	}
	@Override
	public List<ContractLog> log(Map<String, Object> params) {
		return contractDao.log(params);
	}
	public int countLog(Query query) {
		return contractDao.countLog(query);
	}

	@Override
	public String getId(String contract,Long deptId) {
		return contractDao.getId(contract,deptId);
	}

	@Override
	public List<ReportDO> getNumForType(Long deptId) {
		return contractDao.getNumForType(deptId);
	}

	@Override
	public List<ReportDO> getNumForAreaA(Long deptId) {
		return contractDao.getNumForAreaA(deptId);
	}

	@Override
	public List<ReportDO> getNumForAreaB(Long deptId) {
		return contractDao.getNumForAreaB(deptId);
	}

	@Override
	public List<ReportDO> getNumForShoujiaolv(Long deptId) {
		return contractDao.getNumForShoujiaolv(deptId);
	}

	@Override
	public List<DictDO> shopShouType() {
		return contractDao.shopShouType();
	}
}
