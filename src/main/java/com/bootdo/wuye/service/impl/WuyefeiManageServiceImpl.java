package com.bootdo.wuye.service.impl;

import com.bootdo.common.domain.PayListDO;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.UploadUtils;
import com.bootdo.realty.dao.RoomDao;
import com.bootdo.realty.domain.RoomDO;
import com.bootdo.report.domain.AmortizeReportDO;
import com.bootdo.report.domain.ReportDO;
import com.bootdo.wuye.dao.WuyefeiManageDao;
import com.bootdo.wuye.domain.ExportDO;
import com.bootdo.wuye.domain.WuyeAmortize;
import com.bootdo.wuye.domain.WuyefeiDO;
import com.bootdo.wuye.domain.WuyefeiDetailDO;
import com.bootdo.wuye.service.WuyefeiManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class WuyefeiManageServiceImpl implements WuyefeiManageService {
	@Autowired
	private WuyefeiManageDao wuyefeiManageDao;
	@Autowired
	private RoomDao roomDao;
	@Override
	public WuyefeiDO get(Long id){
		return wuyefeiManageDao.get(id);
	}

	@Override
	public List<WuyefeiDO> list(Map<String, Object> map){
		return wuyefeiManageDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return wuyefeiManageDao.count(map);
	}

	@Override
	public int save(WuyefeiDO wuyefei){
		return wuyefeiManageDao.save(wuyefei);
	}

	@Override
	public int update(WuyefeiDO wuyefei){
		return wuyefeiManageDao.update(wuyefei);
	}

	@Override
	public int remove(Long id){
		return wuyefeiManageDao.remove(id);
	}

	@Override
	public int batchRemove(Long[] ids){
		return wuyefeiManageDao.batchRemove(ids);
	}

	@Override
	public void batchInsert(WuyefeiDO wuyefeiDO) {
		wuyefeiManageDao.batchInsert(wuyefeiDO);
	}

	@Override
	public int checkRoomNum(WuyefeiDO wuyefeiDO) {
		return wuyefeiManageDao.checkRoomNum(wuyefeiDO);
	}

	@Override
	public int batchUpdate(WuyefeiDO wuyefei) {
		return wuyefeiManageDao.batchUpdate(wuyefei);
    }

	@Override
	public int checkWuyefei(Long id) {
		return wuyefeiManageDao.checkWuyefei(id);
	}

	@Override
	public List<WuyefeiDetailDO> detailList(Query query) {
		return wuyefeiManageDao.detailList(query);
	}

	@Override
	public WuyefeiDetailDO getDetail(Long id) {
		return wuyefeiManageDao.getDetail(id);
	}

	@Override
	public void removeDetail(Long id) {
		wuyefeiManageDao.removeDetail(id);
	}

	@Override
	public int updateDetail(WuyefeiDetailDO wuyefeiDetailDO) {
		return wuyefeiManageDao.updateDetail(wuyefeiDetailDO);
	}

	@Override
	public int updateDetailInfo(WuyefeiDetailDO wuyefeiDetailDO) {
		WuyefeiDetailDO w=wuyefeiManageDao.getDetail(wuyefeiDetailDO.getId());
		if(w.getPayed()>0){
			return 0;
		}
		int i= 0;
		try {
			i = wuyefeiManageDao.updateDetail(wuyefeiDetailDO);
			RoomDO r=roomDao.get(w.getRoomId());
			List<WuyeAmortize> wuyeAmortizes=new ArrayList<>();
			DecimalFormat df = new DecimalFormat("######0.00");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Long deptId=w.getDeptId();
			Date start=wuyefeiDetailDO.getStartDate();
			Date end=wuyefeiDetailDO.getEndDate();
			int num= DateUtils.getMonthNum(start,end);
			WuyeAmortize wuyeAmortize=new WuyeAmortize();
			wuyeAmortize.setWuyefeiId(w.getWuyefeiId());
			wuyeAmortize.setRoomId(w.getRoomId());
			wuyefeiManageDao.removeAmortize(wuyeAmortize);
			Calendar c=Calendar.getInstance();
			c.setTime(start);
			Double wuyePrice=wuyefeiDetailDO.getYingpay();
			wuyePrice=UploadUtils.format(wuyePrice/12);
			Double p= Double.valueOf(0);
			for(int j=0;j<num;j++){
				wuyeAmortize=new WuyeAmortize();
				wuyeAmortize.setDeptId(deptId);
				wuyeAmortize.setBuildingId(r.getBuildingId());
				wuyeAmortize.setRoomId(r.getId());
				if(j==0&&num==1){
					Calendar lastDay=Calendar.getInstance();
					lastDay.setTime(start);
					int sDay=c.get(Calendar.DATE);
					int eDay=lastDay.get(Calendar.DATE);
					Double day= Double.valueOf(eDay-sDay+1);
					Double m= Double.valueOf(df.format(day/30));
					if(sDay==1){
						m= Double.valueOf(1);
					}
					Double total=Double.valueOf(df.format(wuyePrice*m));
					wuyeAmortize.setYing(total);
					wuyeAmortize.setUnpay(total);
					wuyeAmortize.setPayed((double) 0);
				}else{
					if(j==0){
						Calendar lastDay=Calendar.getInstance();
						lastDay.setTime(start);
						int sDay=c.get(Calendar.DATE);
						int eDay=lastDay.getActualMaximum(Calendar.DAY_OF_MONTH)+1;
						Double day= Double.valueOf(eDay-sDay);
						Double m= Double.valueOf(df.format(day/30));
						if(sDay==1){
							m= Double.valueOf(1);
						}
						Double total=Double.valueOf(df.format(wuyePrice*m));
						p+=total;
						wuyeAmortize.setYing(total);
						wuyeAmortize.setUnpay(total);
						wuyeAmortize.setPayed((double) 0);
					}
					else if(j>0&&j<num-1){
						p+=wuyePrice;
						wuyeAmortize.setYing(Double.valueOf(df.format(wuyePrice)));
						wuyeAmortize.setUnpay(Double.valueOf(df.format(wuyePrice)));
						wuyeAmortize.setPayed((double) 0);
					}else{
						/*Calendar e=Calendar.getInstance();
						e.setTime(end);
						Double day= Double.valueOf(e.get(Calendar.DATE));
						Double m= Double.valueOf(df.format(day/30));
						if(day== DateUtils.getMaxDay(e)){
							m= Double.valueOf(1);
						}*/
						Double total=wuyefeiDetailDO.getYingpay()-p;
						wuyeAmortize.setYing(total);
						wuyeAmortize.setUnpay(total);
						wuyeAmortize.setPayed((double) 0);
					}
				}
				String d=sdf.format(c.getTime());
				wuyeAmortize.setMonth(d);
				wuyeAmortize.setWuyefeiId(w.getWuyefeiId());
				wuyeAmortizes.add(wuyeAmortize);
				c.add(Calendar.MONTH, 1);
			}
			if(wuyeAmortizes.size()>0){
				wuyefeiManageDao.batchAmortizes(wuyeAmortizes);
			}
			wuyefeiManageDao.updateCount(w.getWuyefeiId());
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return 0;
		}
		return i;
	}

	@Override
	public List<AmortizeReportDO> wuyeAmortizeList(List<ReportDO> reportDOs, ReportDO reportDOsin) {
		return wuyefeiManageDao.wuyeAmortizeList(reportDOs,reportDOsin);
	}

	@Override
	public WuyefeiDO getPaySum(Map<String, Object> params) {
		return wuyefeiManageDao.getPaySum(params);
	}

	@Override
	public ExportDO getExport(Long deptId) {
		return wuyefeiManageDao.getExport(deptId);
	}

	@Override
	public void removeExport(Long deptId) {
		wuyefeiManageDao.removeExport(deptId);
	}

	@Override
	public void saveExport(ExportDO exportDO) {
		wuyefeiManageDao.saveExport(exportDO);
	}

	@Override
	public int countDetail(Query query) {
		return wuyefeiManageDao.countDetail(query);
	}

	@Override
	public String getId(String s) {
		return wuyefeiManageDao.getId(s);
	}

	@Override
	public int savePay(PayListDO payListDO) {
		return wuyefeiManageDao.savePay(payListDO);
	}

	@Override
	public void updateCount(Long wuyefeiId) {
		wuyefeiManageDao.updateCount(wuyefeiId);
	}

	@Override
	public void insertDetail(WuyefeiDO wuyefei) {
		wuyefeiManageDao.insertDetail(wuyefei);
	}

	@Override
	public PayListDO getPay(Long id) {
		return wuyefeiManageDao.getPay(id);
	}

	@Override
	public int updatePay(PayListDO payListDO) {
		return wuyefeiManageDao.updatePay(payListDO);
	}

	@Override
	public void updateMoney(Long id) {
		wuyefeiManageDao.updateMoney(id);
	}

	@Override
	public List<RoomDO> getWuyefeiRoom(WuyefeiDO wuyefei) {
		return wuyefeiManageDao.getWuyefeiRoom(wuyefei);
	}

	@Override
	public void batchAmortizes(List<WuyeAmortize> wuyeAmortizes) {
		wuyefeiManageDao.batchAmortizes(wuyeAmortizes);
	}

	@Override
	public int checkHasWuyefei(WuyefeiDO wuyefei) {
		return wuyefeiManageDao.checkHasWuyefei(wuyefei);
	}

	@Override
	public List<WuyeAmortize> getWuyeAmortize(WuyefeiDetailDO wuyefeiDetailDO) {
		return wuyefeiManageDao.getWuyeAmortize(wuyefeiDetailDO);
	}

	@Override
	public void updateAmortize(WuyeAmortize w) {
		wuyefeiManageDao.updateAmortize(w);
	}

	@Override
	public void removeAmortize(WuyeAmortize wuyeAmortize) {
		wuyefeiManageDao.removeAmortize(wuyeAmortize);
	}


	@Override
	public AmortizeReportDO getWuyeAmortizeReport(Map<String, Object> params) {
		return wuyefeiManageDao.getWuyeAmortizeReport(params);
	}

	@Override
	public void batchInsertLastYear(WuyefeiDO wuyefei) {
		wuyefeiManageDao.batchInsertLastYear(wuyefei);
	}


	@Override
	public void removeDetailById(Long id) {
		wuyefeiManageDao.removeDetailById(id);
	}

	@Override
	public R createWuyefei(WuyefeiDO w) {
		Long id=w.getId();
		int i=0;
		try {
			WuyefeiDO wuyefei=wuyefeiManageDao.get(id);
			Date start=wuyefei.getStartDate();
			Date end=wuyefei.getEndDate();
			int num= DateUtils.getMonthNum(start,end);
			Double months=DateUtils.getMonths(start,end);
			Long deptId=wuyefei.getDeptId();
			wuyefei.setMonths(months);
			wuyefei.setIds(w.getIds());
			//第一年生成物业费的房屋，按照头表日期生成物业费
			wuyefeiManageDao.batchInsert(wuyefei);
			//将上个年度的物业费复制到本年度
			wuyefeiManageDao.batchInsertLastYear(wuyefei);
			//获取第一次生成物业费的信息，生成摊销
			List<RoomDO> roomDOList=wuyefeiManageDao.getWuyefeiRoom(wuyefei);
			List<WuyeAmortize> wuyeAmortizes=new ArrayList<>();
			DecimalFormat df = new DecimalFormat("######0.00");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Double p= Double.valueOf(0);
			for(RoomDO r:roomDOList){
                Calendar c=Calendar.getInstance();
                c.setTime(start);
                Double wuyePrice=r.getWuyefei();
                Double buildingArea=r.getBuildingArea();
				p= Double.valueOf(0);
                for(int j=0;j<num;j++){
                    WuyeAmortize wuyeAmortize=new WuyeAmortize();
                    wuyeAmortize.setDeptId(deptId);
                    wuyeAmortize.setBuildingId(r.getBuildingId());
                    wuyeAmortize.setRoomId(r.getId());
                    if(j==0&&num==1){
                        Calendar lastDay=Calendar.getInstance();
                        lastDay.setTime(start);
                        int sDay=c.get(Calendar.DATE);
                        int eDay=lastDay.get(Calendar.DATE);
                        Double day= Double.valueOf(eDay-sDay+1);
                        Double m= Double.valueOf(df.format(day/30));
						if(day== 1){
							m= Double.valueOf(1);
						}
                        Double total=Double.valueOf(df.format(wuyePrice*buildingArea*m));
                        wuyeAmortize.setYing(total);
                        wuyeAmortize.setUnpay(total);
                        wuyeAmortize.setPayed((double) 0);
                    }else{
                        if(j==0){
                            Calendar lastDay=Calendar.getInstance();
                            lastDay.setTime(start);
                            int sDay=c.get(Calendar.DATE);
                            int eDay=lastDay.getActualMaximum(Calendar.DAY_OF_MONTH)+1;
                            Double day= Double.valueOf(eDay-sDay);
                            Double m= Double.valueOf(df.format(day/30));
							if(day== 1){
								m= Double.valueOf(1);
							}
                            Double total=Double.valueOf(df.format(wuyePrice*buildingArea*m));
							p+=total;
                            wuyeAmortize.setYing(total);
                            wuyeAmortize.setUnpay(total);
                            wuyeAmortize.setPayed((double) 0);
                        }
                        else if(j>0&&j<num-1){
                            wuyeAmortize.setYing(Double.valueOf(df.format(wuyePrice*buildingArea)));
                            wuyeAmortize.setUnpay(Double.valueOf(df.format(wuyePrice*buildingArea)));
                            wuyeAmortize.setPayed((double) 0);
							p+=wuyeAmortize.getYing();
                        }else{
                            /*Calendar e=Calendar.getInstance();
                            e.setTime(end);
                            Double day= Double.valueOf(e.get(Calendar.DATE));
                            Double m= Double.valueOf(df.format(day/30));
							if(day== DateUtils.getMaxDay(e)){
								m= Double.valueOf(1);
							}*/
                            Double total=Double.valueOf(df.format(wuyePrice*buildingArea*12))-p;
                            wuyeAmortize.setYing(total);
                            wuyeAmortize.setUnpay(total);
                            wuyeAmortize.setPayed((double) 0);
                        }
                    }
                    String d=sdf.format(c.getTime());
                    wuyeAmortize.setMonth(d);
                    wuyeAmortize.setWuyefeiId(wuyefei.getId());
                    wuyeAmortizes.add(wuyeAmortize);
                    c.add(Calendar.MONTH, 1);
                }
            }
			//获取非第一次生成物业费信息，生成摊销
			List<RoomDO> wuyefeiRoomHas=wuyefeiManageDao.getWuyefeiRoomHas(wuyefei);
			for(RoomDO r:wuyefeiRoomHas){
                start=r.getStartDate();
                end=r.getEndDate();
                int month=DateUtils.getMonthNum(start,end);
                Calendar c=Calendar.getInstance();
                c.setTime(start);
                Double wuyePrice=r.getWuyefei();
                Double buildingArea=r.getBuildingArea();
                for(int j=0;j<month;j++){
                    WuyeAmortize wuyeAmortize=new WuyeAmortize();
                    wuyeAmortize.setDeptId(deptId);
                    wuyeAmortize.setBuildingId(r.getBuildingId());
                    wuyeAmortize.setRoomId(r.getId());
                    if(j==0&&num==1){
                        Calendar lastDay=Calendar.getInstance();
                        lastDay.setTime(end);
                        int sDay=c.get(Calendar.DATE);
                        int eDay=lastDay.get(Calendar.DATE);
                        Double day= Double.valueOf(eDay-sDay+1);
                        Double m= Double.valueOf(df.format(day/30));
                        Double total=Double.valueOf(df.format(wuyePrice*buildingArea*m));
                        wuyeAmortize.setYing(total);
                        wuyeAmortize.setUnpay(total);
                        wuyeAmortize.setPayed((double) 0);
                    }else{
                        if(j==0){
                            Calendar lastDay=Calendar.getInstance();
                            lastDay.setTime(start);
                            int sDay=c.get(Calendar.DATE);
                            int eDay=DateUtils.getMaxDay(lastDay)+1;
                            Double day= Double.valueOf(eDay-sDay);
                            Double m= Double.valueOf(df.format(day/30));
							Double a=wuyePrice;
							Double b=buildingArea;
							Double c1=m;
							System.out.println("a:"+a);
							System.out.println("b:"+b);
							System.out.println("c1:"+c1);
                            Double total=Double.valueOf(df.format(wuyePrice*buildingArea*m));
                            wuyeAmortize.setYing(total);
                            wuyeAmortize.setUnpay(total);
                            wuyeAmortize.setPayed((double) 0);
                        }
                        else if(j>0&&j<num-1){
                            wuyeAmortize.setYing(Double.valueOf(df.format(wuyePrice*buildingArea)));
                            wuyeAmortize.setUnpay(Double.valueOf(df.format(wuyePrice*buildingArea)));
                            wuyeAmortize.setPayed((double) 0);
                        }else{
                            Calendar e=Calendar.getInstance();
                            e.setTime(end);
                            Double day= Double.valueOf(e.get(Calendar.DATE));
                            Double m= Double.valueOf(df.format(day/30));
                            Double total=Double.valueOf(df.format(wuyePrice*buildingArea*m));
                            wuyeAmortize.setYing(total);
                            wuyeAmortize.setUnpay(total);
                            wuyeAmortize.setPayed((double) 0);
                        }
                    }
                    String d=sdf.format(c.getTime());
                    wuyeAmortize.setMonth(d);
                    wuyeAmortize.setWuyefeiId(wuyefei.getId());
                    wuyeAmortizes.add(wuyeAmortize);
                    c.add(Calendar.MONTH, 1);
                }
            }
			wuyefei.setStatus("1");
			wuyefeiManageDao.update(wuyefei);
			wuyefeiManageDao.updateCount(wuyefei.getId());
			if(wuyeAmortizes.size()>0){
                wuyefeiManageDao.batchAmortizes(wuyeAmortizes);
				return R.ok();
            }else {
            	return R.error("没有可生成物业费的房屋");
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error();
		}
	}

	@Override
	public R batchCreate(WuyefeiDO w) {
		Long id=w.getId();
		try {
			WuyefeiDO wuyefei=wuyefeiManageDao.get(id);
			Date start=wuyefei.getStartDate();
			Date end=wuyefei.getEndDate();
			int num= DateUtils.getMonthNum(start,end);
			Double months=DateUtils.getMonths(start,end);
			Long deptId=wuyefei.getDeptId();
			wuyefei.setMonths(months);
			wuyefei.setIds(w.getIds());
			//第一年生成物业费的房屋，按照头表日期生成物业费
			wuyefeiManageDao.batchInsertByRoom(wuyefei);
			//将上个年度的物业费复制到本年度
			wuyefeiManageDao.batchInsertLastYearByRoom(wuyefei);
			//获取第一次生成物业费的信息，生成摊销
			List<RoomDO> roomDOList=wuyefeiManageDao.getWuyefeiRoomByRoom(wuyefei);
			List<WuyeAmortize> wuyeAmortizes=new ArrayList<>();
			DecimalFormat df = new DecimalFormat("######0.00");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			for(RoomDO r:roomDOList){
				Calendar c=Calendar.getInstance();
				c.setTime(start);
				Double wuyePrice=r.getWuyefei();
				Double buildingArea=r.getBuildingArea();
				for(int j=0;j<num;j++){
					WuyeAmortize wuyeAmortize=new WuyeAmortize();
					wuyeAmortize.setDeptId(deptId);
					wuyeAmortize.setBuildingId(r.getBuildingId());
					wuyeAmortize.setRoomId(r.getId());
					if(j==0&&num==1){
						Calendar lastDay=Calendar.getInstance();
						lastDay.setTime(start);
						int sDay=c.get(Calendar.DATE);
						int eDay=lastDay.get(Calendar.DATE);
						Double day= Double.valueOf(eDay-sDay+1);
						Double m= Double.valueOf(df.format(day/30));
						Double total=Double.valueOf(df.format(wuyePrice*buildingArea*m));
						wuyeAmortize.setYing(total);
						wuyeAmortize.setUnpay(total);
						wuyeAmortize.setPayed((double) 0);
					}else{
						if(j==0){
							Calendar lastDay=Calendar.getInstance();
							lastDay.setTime(start);
							int sDay=c.get(Calendar.DATE);
							int eDay=lastDay.getActualMaximum(Calendar.DAY_OF_MONTH)+1;
							Double day= Double.valueOf(eDay-sDay);
							Double m= Double.valueOf(df.format(day/30));
							Double total=Double.valueOf(df.format(wuyePrice*buildingArea*m));
							wuyeAmortize.setYing(total);
							wuyeAmortize.setUnpay(total);
							wuyeAmortize.setPayed((double) 0);
						}
						else if(j>0&&j<num-1){
							wuyeAmortize.setYing(Double.valueOf(df.format(wuyePrice*buildingArea)));
							wuyeAmortize.setUnpay(Double.valueOf(df.format(wuyePrice*buildingArea)));
							wuyeAmortize.setPayed((double) 0);
						}else{
							Calendar e=Calendar.getInstance();
							e.setTime(end);
							Double day= Double.valueOf(e.get(Calendar.DATE));
							Double m= Double.valueOf(df.format(day/30));
							Double total=Double.valueOf(df.format(wuyePrice*buildingArea*m));
							wuyeAmortize.setYing(total);
							wuyeAmortize.setUnpay(total);
							wuyeAmortize.setPayed((double) 0);
						}
					}
					String d=sdf.format(c.getTime());
					wuyeAmortize.setMonth(d);
					wuyeAmortize.setWuyefeiId(wuyefei.getId());
					wuyeAmortizes.add(wuyeAmortize);
					c.add(Calendar.MONTH, 1);
				}
			}
			//获取非第一次生成物业费信息，生成摊销
			List<RoomDO> wuyefeiRoomHas=wuyefeiManageDao.getWuyefeiRoomHasByRoom(wuyefei);
			for(RoomDO r:wuyefeiRoomHas){
				start=r.getStartDate();
				end=r.getEndDate();
				int month=DateUtils.getMonthNum(start,end);
				Calendar c=Calendar.getInstance();
				c.setTime(start);
				Double wuyePrice=r.getWuyefei();
				Double buildingArea=r.getBuildingArea();
				for(int j=0;j<month;j++){
					WuyeAmortize wuyeAmortize=new WuyeAmortize();
					wuyeAmortize.setDeptId(deptId);
					wuyeAmortize.setBuildingId(r.getBuildingId());
					wuyeAmortize.setRoomId(r.getId());
					if(j==0&&num==1){
						Calendar lastDay=Calendar.getInstance();
						lastDay.setTime(end);
						int sDay=c.get(Calendar.DATE);
						int eDay=lastDay.get(Calendar.DATE);
						Double day= Double.valueOf(eDay-sDay+1);
						Double m= Double.valueOf(df.format(day/30));
						Double total=Double.valueOf(df.format(wuyePrice*buildingArea*m));
						wuyeAmortize.setYing(total);
						wuyeAmortize.setUnpay(total);
						wuyeAmortize.setPayed((double) 0);
					}else{
						if(j==0){
							Calendar lastDay=Calendar.getInstance();
							lastDay.setTime(start);
							int sDay=c.get(Calendar.DATE);
							int eDay=DateUtils.getMaxDay(lastDay)+1;
							Double day= Double.valueOf(eDay-sDay);
							Double m= Double.valueOf(df.format(day/30));
							Double total=Double.valueOf(df.format(wuyePrice*buildingArea*m));
							wuyeAmortize.setYing(total);
							wuyeAmortize.setUnpay(total);
							wuyeAmortize.setPayed((double) 0);
						}
						else if(j>0&&j<num-1){
							wuyeAmortize.setYing(Double.valueOf(df.format(wuyePrice*buildingArea)));
							wuyeAmortize.setUnpay(Double.valueOf(df.format(wuyePrice*buildingArea)));
							wuyeAmortize.setPayed((double) 0);
						}else{
							Calendar e=Calendar.getInstance();
							e.setTime(end);
							Double day= Double.valueOf(e.get(Calendar.DATE));
							Double m= Double.valueOf(df.format(day/30));
							Double total=Double.valueOf(df.format(wuyePrice*buildingArea*m));
							wuyeAmortize.setYing(total);
							wuyeAmortize.setUnpay(total);
							wuyeAmortize.setPayed((double) 0);
						}
					}
					String d=sdf.format(c.getTime());
					wuyeAmortize.setMonth(d);
					wuyeAmortize.setWuyefeiId(wuyefei.getId());
					wuyeAmortizes.add(wuyeAmortize);
					c.add(Calendar.MONTH, 1);
				}
			}
			wuyefei.setStatus("1");
			wuyefeiManageDao.update(wuyefei);
			wuyefeiManageDao.updateCount(wuyefei.getId());
			if(wuyeAmortizes.size()>0){
				wuyefeiManageDao.batchAmortizes(wuyeAmortizes);
				return R.ok();
			}else {
				return R.error("没有可生成物业费的房屋");
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error();
		}
	}

	@Override
	public WuyefeiDO getSum(WuyefeiDO w) {
		return wuyefeiManageDao.getSum(w);
	}

	@Override
	public List<String> getWuyefeiDetail(String id) {
		return wuyefeiManageDao.getWuyefeiDetail(id);
	}

	@Override
	public List<WuyeAmortize> getWuyeAmortizeByCode(WuyefeiDetailDO wuyefeiDetailDO) {
		return wuyefeiManageDao.getWuyeAmortizeByCode(wuyefeiDetailDO);
	}

	@Override
	public void updateDetailPay(WuyefeiDetailDO wuyefeiDetailDO) {
		wuyefeiManageDao.updateDetailPay(wuyefeiDetailDO);
	}

	@Override
	public WuyefeiDetailDO getDetailByRoom(WuyefeiDetailDO wuyefeiDetailDO) {
		return wuyefeiManageDao.getDetailByRoom(wuyefeiDetailDO);
	}


}
