package com.bootdo.business.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.bootdo.base.domain.MerchantDO;
import com.bootdo.base.service.MerchantService;
import com.bootdo.business.domain.*;
import com.bootdo.business.service.*;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.*;
import com.bootdo.business.domain.BusinessBuildingDO;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.DeptService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商铺合同管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-06 20:31:35
 */
 
@Controller
@RequestMapping("/business/contract")
public class ContractController {
	@Autowired
	private ContractService contractService;
	@Autowired
	private AmortizeService amortizeService;
	@Autowired
	private BusinessBuildingService businessBuildingService;
	@Autowired
	private PayListService payListService;
	@Resource
	private ResourceLoader resourceLoader;
	@Autowired
	private DictService dictService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private MerchantService merchantService;
    @Autowired
    private PayCountService payCountService;
	@Autowired
	private DeptService deptService;
	@GetMapping()
	@RequiresPermissions("business:contract:contract")
	String Contract(){
	    return "business/contract/contract";
	}
	@GetMapping("/contractChechangGeneral")
	@RequiresPermissions("business:contract:contractChechangGeneral")
	String ContractChechangGeneral(){
		return "business/contract/contractChechangGeneral";
	}
	@ResponseBody
	@GetMapping("/chechangList")
	@RequiresPermissions("business:contract:contractChechangGeneral")
	public PageUtils chechangList(@RequestParam Map<String, Object> params){
		UserDO userDO=ShiroUtils.getUser();
		params.put("deptId",userDO.getDeptId());
		//查询列表数据
		Query query = new Query(params);
		List<ContractDO> contractList = contractService.cheChangList(query);
		int total = contractService.chechangCount(query);
		PageUtils pageUtils = new PageUtils(contractList, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:contract:contract")
	public PageUtils list(@RequestParam Map<String, Object> params){
		UserDO userDO=ShiroUtils.getUser();
		params.put("deptId",userDO.getDeptId());
		//查询列表数据
        Query query = new Query(params);
		List<ContractDO> contractList = contractService.list(query);
		int total = contractService.count(query);
		PageUtils pageUtils = new PageUtils(contractList, total);
		return pageUtils;
	}

	@GetMapping("/contractreport")
	@RequiresPermissions("business:contract:reportlist")
	String contractreport(){
		return "business/contract/contractreport";
	}
	@ResponseBody
	@GetMapping("/reportlist")
	@RequiresPermissions("business:contract:reportlist")
	public List<ContractDO> reportlist(@RequestParam Map<String, Object> params){
		UserDO userDO=ShiroUtils.getUser();
		params.put("deptId",userDO.getDeptId());
		//查询列表数据
		List<ContractDO> contractList = contractService.listForReport(params);
		ContractDO contractDO = contractService.totalForReport(params);
		contractList.add(contractDO);
		return contractList;
	}
	@GetMapping("/contractDetail")
	String contractDetail(){
		return "business/contract/info";
	}

	@ResponseBody
	@GetMapping("/listDetail")
	public PageUtils listDetail(@RequestParam Map<String, Object> params){
		UserDO userDO=ShiroUtils.getUser();
		params.put("deptId",userDO.getDeptId());
		//查询列表数据
		Query query = new Query(params);
		List<ContractDO> contractList = contractService.listDetail(query);
		int total = contractService.countDetail(query);
		PageUtils pageUtils = new PageUtils(contractList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("business:contract:add")
	String add(){
	    return "business/contract/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("business:contract:edit")
	String edit(@PathVariable("id") Long id,Model model) throws ParseException {
		ContractDO contract = contractService.get(id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		contract.setContractDateString(sdf.format(contract.getContractDate()));
		contract.setContractStartDateString(sdf.format(contract.getContractStartDate()));
		contract.setContractEndDateString(sdf.format(contract.getContractEndDate()));
		model.addAttribute("contract", contract);
	    return "business/contract/edit";
	}
	/*@GetMapping("/renew/{id}")
	@RequiresPermissions("business:contract:renew")
	String renew(@PathVariable("id") Long id,Model model){
		ContractDO contract = contractService.get(id);
		model.addAttribute("contract", contract);
		return "business/contract/renew";
	}*/
	//合同续签
	@GetMapping("/contractadd/{id}")
	@RequiresPermissions("business:contract:contractadd")
	String contractadd(@PathVariable("id") Long id,Model model){
		Date now = new Date();
		ContractDO contract = contractService.get(id);
		contract.setOidOld(contract.getOid());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(contract.getContractEndDate());
		calendar.add(Calendar.DATE, 1);
		contract.setContractDateString(sdf.format(now));
		contract.setContractStartDateString(sdf.format(calendar.getTime()));
		model.addAttribute("contract", contract);
		return "business/contract/contractadd";
	}
	//合同续签
	@ResponseBody
	@PostMapping("/contractaddSave")
	@RequiresPermissions("business:contract:contractadd")
	public R contractaddSave( ContractDO contract){
		if(contract.getYouhui() != null && !contract.getYouhui().equals("")){
			contract.setYouhui(contract.getYouhui().replace("，",","));
		}
		int i = contractService.contractaddSave(contract);
		if(i>0){
			return R.ok();
		}else if(i == -1){
			return R.error(-1,"该合同号已存在");
		}
		return R.error();
	}

	@GetMapping("/contractChechangConfirm/{id}")
	@RequiresPermissions("business:contract:contractChechangConfirm")
	String contractChechangDetail(@PathVariable("id") Long id,Model model){
		ContractDO contract = contractService.get(id);
		model.addAttribute("contract", contract);
		return "business/contract/contractChechangConfirm";
	}
	/*public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf.parse("2018-10-03");
		Date date2 = sdf.parse("2018-10-02");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		String dateFirst = dateFormat.format(date1);
		String dateLast = dateFormat.format(date2);
		int dateFirstIntVal = Integer.parseInt(dateFirst);
		int dateLastIntVal = Integer.parseInt(dateLast);
		if (dateFirstIntVal > dateLastIntVal) {
			System.out.print("dateFirstIntVal大");
		} else if (dateFirstIntVal <= dateLastIntVal) {
			System.out.print("dateLastIntVal大");
		}else if (dateFirstIntVal == dateLastIntVal) {
			System.out.print("一边大");
		}

	}*/
	//撤场缴费
	@GetMapping("/contractChechangPay/{id}")
	@RequiresPermissions("business:contract:contractChechangPay")
	String contractChechangPay(@PathVariable("id") Long id,Model model) throws ParseException {
		ContractDO contract = contractService.get(id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String d=sdf.format(date);
		contract.setContractChechangDateString(sdf.format(contract.getContractChechangDate()));
		contract.setContractStartDateString(sdf.format(contract.getContractStartDate()));
		contract.setContractEndDateString(sdf.format(contract.getContractEndDate()));
		model.addAttribute("contract", contract);
		model.addAttribute("name", ShiroUtils.getUser().getName());
		model.addAttribute("payDate", d);
		return "business/contract/contractChechangPay";
	}
	//合同撤场
	@ResponseBody
	@RequestMapping("/contractChechang")
	public R contractChechang(ContractDO contractOld) throws ParseException {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		List<String> monthList = new ArrayList<String>();
		ContractDO contract = contractService.get(contractOld.getId());
        contractService.saveContractBf(contract.getOid());
        contractService.savePayCountBf(contract.getOid());
        contractService.saveAmortizeBf(contract.getOid());
		contract.setContractChechangDate(contractOld.getContractChechangDate());
		contract.setState("2");
		contract.setChechangOperationDate(now);
		contract.setDiscrepancy(contract.getReceivedZhiliang()+contract.getReceivedZhuangxiu()+contract.getReceivedFuwu()+contract.getReceivedLvyue());
		String dateStart = dateFormat.format(contract.getContractStartDate());
		String dateEnd = dateFormat.format(contract.getContractEndDate());
		String dateChechang = dateFormat.format(contract.getContractChechangDate());
		String dateNow = dateFormat.format(now);

		int dateStartIntVal = Integer.parseInt(dateStart);
		int dateEndIntVal = Integer.parseInt(dateEnd);
		int dateChechangIntVal = Integer.parseInt(dateChechang);
		int dateNowIntVal = Integer.parseInt(dateNow);
		if(contractOld.getContractChechangDate().after(contract.getContractEndDate())){
			return R.error("撤场日期应在合同截止日期之前");
		}
		/*if(contractOld.getContractChechangDate().after(now)){
			return R.error("请在撤场日期之后再进行撤场操作");
		}*/
		if(contractOld.getContractChechangDate().before(contract.getContractStartDate())){
			return R.error("撤场日期必须在合同开始日期之后");
		}
		//合同起止天数差
		long days=(contract.getContractEndDate().getTime()-contract.getContractStartDate().getTime())/(1000*3600*24)+1;
		Double yueFen = DateUtils.getMonths30(contract.getContractStartDate(),contract.getContractEndDate());//月数
		yueFen = UploadUtils.format(yueFen);
		Double wuyeMonth =contract.getFenleiWuye()/yueFen;
		Double zujinMonth =contract.getFenleiZujin()/yueFen;
		Double guanliMonth =contract.getFenleiGuanli()/yueFen;
		Double tanxiaoGuanli = UploadUtils.format(contract.getFenleiGuanli()/days);
		Double tanxiaoWuye = UploadUtils.format(contract.getFenleiWuye()/days);
		Double tanxiaoZujin = UploadUtils.format(contract.getFenleiZujin()/days);
		Double tanxiaoGuanliRest;
		Double tanxiaoWuyeRest;
		Double tanxiaoZujinRest;
		wuyeMonth = UploadUtils.format(wuyeMonth);
		zujinMonth = UploadUtils.format(zujinMonth);
		guanliMonth = UploadUtils.format(guanliMonth);

		long daysChechang=(contract.getContractChechangDate().getTime()-contract.getContractStartDate().getTime())/(1000*3600*24)+1;
		Double yueFenChechang = DateUtils.getMonths30(contract.getContractStartDate(),contract.getContractChechangDate());//月数
		contract.setFenleiZujin(zujinMonth*yueFenChechang);
		contract.setFenleiWuye(wuyeMonth*yueFenChechang);
		contract.setFenleiGuanli(guanliMonth*yueFenChechang);
		contract.setContractPayTotal(contract.getFenleiZujin()+contract.getFenleiWuye()+contract.getFenleiGuanli());
		contract.setFenleiZujinUnreceived(contract.getFenleiZujin()-contract.getFenleiZujinReceived());
		contract.setFenleiGuanliUnreceived(contract.getFenleiGuanli()-contract.getFenleiGuanliReceived());
		contract.setFenleiWuyeUnreceived(contract.getFenleiWuye()-contract.getFenleiWuyeReceived());
		contract.setContractPayTotalUnreceived(contract.getContractPayTotal()-contract.getContractPayTotalReceived());
		contract.setTotal(contract.getContractPayTotalUnreceived()+contract.getReceivableLvyue()+contract.getReceivableFuwu()+contract.getReceivableZhiliang()+contract.getReceivableZhuangxiu()-contract.getReceivedFuwu()-contract.getReceivedLvyue()-contract.getReceivedZhiliang()-contract.getReceivedZhuangxiu());
		int day;
		//撤场时间到合同截止日期之间的摊销

		//如果撤场日期与财务撤场操作在一个月，那么 更新当前撤场月份的摊销为实际摊销金额，以后月份为0
		if(dateNowIntVal == dateChechangIntVal){
			//更新当前时间以后的摊销月份摊销金额为0（也有可能当前时间以后没有摊销）
			contractService.updateTanxiaoForChechang(contract);
			AmortizeDO tanxiaoDO = new AmortizeDO();
			day = contract.getContractChechangDate().getDate();
			tanxiaoDO.setOid(contract.getOid());
			tanxiaoDO.setAmortizePriceZujin(tanxiaoZujin*day);
			tanxiaoDO.setAmortizePriceGuanli(tanxiaoGuanli*day);
			tanxiaoDO.setAmortizePriceWuye(tanxiaoWuye*day);
			tanxiaoDO.setTotal(tanxiaoDO.getAmortizePriceGuanli()+tanxiaoDO.getAmortizePriceWuye()+tanxiaoDO.getAmortizePriceZujin());
			tanxiaoDO.setMonth(sdf.format(now));
			contractService.updateTanxiaoCurrentMonth(tanxiaoDO);
		}else if((dateChechangIntVal < dateNowIntVal)&&(dateNowIntVal <= dateEndIntVal)){
			//更新当前时间以后的摊销月份摊销金额为0（也有可能当前时间以后没有摊销）
			contractService.updateTanxiaoForChechang(contract);
			//计算撤场月份剩余摊销额
			int dayRest;
			if(contract.getContractChechangDate().getDate()==1){
				dayRest = DateUtils.getMaxDay(contract.getContractChechangDate())-1;
			}else {
				dayRest =(int) ( DateUtils.getMaxDay(contract.getContractChechangDate())-Double.valueOf(contract.getContractChechangDate().getDate()))-1;
			}
			tanxiaoGuanliRest = tanxiaoGuanli * dayRest;
			tanxiaoZujinRest = tanxiaoZujin * dayRest;
			tanxiaoWuyeRest = tanxiaoWuye * dayRest;

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(contract.getContractChechangDate());
			calendar.add(Calendar.MONTH, 1);
			monthList = DateUtils.getMonthBetweenTanxiao(calendar.getTime(),now);
			//如果相差的间隔为1，说明在撤场的下一个月进行了操作，所摊金额为撤场当月剩余摊销金额负数
			AmortizeDO tanxiaoDOUpdate = new AmortizeDO();
			if(monthList.size()==1){
				tanxiaoDOUpdate.setAmortizePriceWuye(-tanxiaoWuyeRest);
				tanxiaoDOUpdate.setAmortizePriceGuanli(-tanxiaoGuanliRest);
				tanxiaoDOUpdate.setAmortizePriceZujin(-tanxiaoZujinRest);
				tanxiaoDOUpdate.setTotal(-tanxiaoWuyeRest-tanxiaoGuanliRest-tanxiaoZujinRest);
			}
			else{
				Double zujinTotal = tanxiaoZujinRest;
				Double wuyeTotal = tanxiaoWuyeRest;
				Double guanliTotal = tanxiaoGuanliRest;
				for(int k =1;k<monthList.size();k++){
					AmortizeDO tanxiaoDO = new AmortizeDO();
					AmortizeDO tanxiaoDONew = new AmortizeDO();
					tanxiaoDO.setOid(contract.getOid());
					tanxiaoDO.setMonth(monthList.get(k-1));
					tanxiaoDONew = contractService.getTanxiaoFenleiPrice(tanxiaoDO);
					zujinTotal = tanxiaoDONew.getAmortizePriceZujin() + zujinTotal;
					wuyeTotal = tanxiaoDONew.getAmortizePriceWuye() + wuyeTotal;
					guanliTotal = tanxiaoDONew.getAmortizePriceGuanli() + guanliTotal;
				}
				tanxiaoDOUpdate.setAmortizePriceZujin(-zujinTotal);
				tanxiaoDOUpdate.setAmortizePriceGuanli(-guanliTotal);
				tanxiaoDOUpdate.setAmortizePriceWuye(-wuyeTotal);
				tanxiaoDOUpdate.setTotal(-wuyeTotal-guanliTotal-zujinTotal);
			}
			tanxiaoDOUpdate.setMonth(sdf.format(now));
			tanxiaoDOUpdate.setOid(contract.getOid());
			tanxiaoDOUpdate.preUpdate();
			contractService.updateTanxiaoCurrentMonth(tanxiaoDOUpdate);
		}else if(dateChechangIntVal > dateNowIntVal){
			contractService.updateTanxiaoForChechangD(contract);
			AmortizeDO tanxiaoDOUpdate = new AmortizeDO();
			//计算撤场月份摊销额
			int dayTanxiao;
			dayTanxiao = contract.getContractChechangDate().getDate();
			tanxiaoDOUpdate.setAmortizePriceZujin(dayTanxiao*tanxiaoZujin);
			tanxiaoDOUpdate.setAmortizePriceGuanli(dayTanxiao*tanxiaoGuanli);
			tanxiaoDOUpdate.setAmortizePriceWuye(dayTanxiao*tanxiaoWuye);
			tanxiaoDOUpdate.setTotal(tanxiaoDOUpdate.getAmortizePriceGuanli()+tanxiaoDOUpdate.getAmortizePriceZujin()+tanxiaoDOUpdate.getAmortizePriceWuye());
			tanxiaoDOUpdate.setMonth(sdf.format(contract.getContractChechangDate()));
			tanxiaoDOUpdate.setOid(contract.getOid());
			contractService.updateTanxiaoCurrentMonth(tanxiaoDOUpdate);
		}else{
			AmortizeDO tanxiaoDOInsert = new AmortizeDO();
			//计算撤场月份剩余摊销额
			int dayRest;
			if(contract.getContractChechangDate().getDate()==1){
				dayRest = DateUtils.getMaxDay(contract.getContractChechangDate())-1;
			}else {
				dayRest =(int) ( DateUtils.getMaxDay(contract.getContractChechangDate())-Double.valueOf(contract.getContractChechangDate().getDate()));
			}
			tanxiaoGuanliRest = tanxiaoGuanli * dayRest;
			tanxiaoZujinRest = tanxiaoZujin * dayRest;
			tanxiaoWuyeRest = tanxiaoWuye * dayRest;
			if(dateChechangIntVal == dateEndIntVal){
				tanxiaoDOInsert.setAmortizePriceWuye(-tanxiaoWuyeRest);
				tanxiaoDOInsert.setAmortizePriceGuanli(-tanxiaoGuanliRest);
				tanxiaoDOInsert.setAmortizePriceZujin(-tanxiaoZujinRest);
				tanxiaoDOInsert.setTotal(-tanxiaoWuyeRest-tanxiaoGuanliRest-tanxiaoZujinRest);
			}else{
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(contract.getContractChechangDate());
				calendar.add(Calendar.MONTH, 1);
				monthList = DateUtils.getMonthBetweenTanxiao(calendar.getTime(),contract.getContractEndDate());
					Double zujinTotal = tanxiaoZujinRest;
					Double wuyeTotal = tanxiaoWuyeRest;
					Double guanliTotal = tanxiaoGuanliRest;
					for(int k =1;k<=monthList.size();k++){
						AmortizeDO tanxiaoDO = new AmortizeDO();
						AmortizeDO tanxiaoDONew = new AmortizeDO();
						tanxiaoDO.setOid(contract.getOid());
						tanxiaoDO.setMonth(monthList.get(k-1));
						tanxiaoDONew = contractService.getTanxiaoFenleiPrice(tanxiaoDO);
						zujinTotal = tanxiaoDONew.getAmortizePriceZujin() + zujinTotal;
						wuyeTotal = tanxiaoDONew.getAmortizePriceWuye() + wuyeTotal;
						guanliTotal = tanxiaoDONew.getAmortizePriceGuanli() + guanliTotal;
					}
					tanxiaoDOInsert.setAmortizePriceZujin(-zujinTotal);
					tanxiaoDOInsert.setAmortizePriceGuanli(-guanliTotal);
					tanxiaoDOInsert.setAmortizePriceWuye(-wuyeTotal);
					tanxiaoDOInsert.setTotal(-wuyeTotal-guanliTotal-zujinTotal);
			}
			tanxiaoDOInsert.setBuildingId(contract.getBuildingId());
			tanxiaoDOInsert.setBrand(contract.getBrand());
			tanxiaoDOInsert.setContractCode(contract.getContractCode());
			tanxiaoDOInsert.setContractor(contract.getContractor());
			tanxiaoDOInsert.setShopCode(contract.getShopCode());
			tanxiaoDOInsert.setMonth(sdf.format(now));
			tanxiaoDOInsert.setOid(contract.getOid());
			tanxiaoDOInsert.preInsert();
			contractService.insertTanxiao(tanxiaoDOInsert);
		}
		//对催缴单的表 paycount进行操作，删除撤场日期所在缴费周期之后的收款计算
		PayCountDO payCountDOAfter = new PayCountDO();
		payCountDOAfter.setCountStartDate(contract.getContractChechangDate());
		payCountDOAfter.setOid(contract.getOid());
		payCountService.updateAfterChechang(payCountDOAfter);
		//计算撤场日期所在缴费周期应收和未收：利用总应收和总已收减去当前周期之前的总数
		PayCountDO payCountDOBefore = new PayCountDO();
		PayCountDO payCountDONow = new PayCountDO();
		payCountDOBefore = payCountService.getBeforeChechangTotal(payCountDOAfter);
		payCountDONow = payCountService.getNowChechangTotal(payCountDOAfter);
		payCountDONow.setContractPayTotal(contract.getContractPayTotal() - payCountDOBefore.getContractPayTotal());
		payCountDONow.setContractPayTotalUnreceived(payCountDONow.getContractPayTotal() - payCountDONow.getContractPayTotalReceived());
		payCountDONow.setFenleiGuanli(contract.getFenleiGuanli() - payCountDOBefore.getFenleiGuanli());
		payCountDONow.setFenleiZujin(contract.getFenleiZujin() - payCountDOBefore.getFenleiZujin());
		payCountDONow.setFenleiWuye(contract.getFenleiWuye() - payCountDOBefore.getFenleiWuye());
		payCountDONow.setFenleiGuanliUnreceived(payCountDONow.getFenleiGuanli() - payCountDONow.getFenleiGuanliReceived());
		payCountDONow.setFenleiWuyeUnreceived(payCountDONow.getFenleiWuye() - payCountDONow.getFenleiWuyeReceived());
		payCountDONow.setFenleiZujinUnreceived(payCountDONow.getFenleiZujin() - payCountDONow.getFenleiZujinReceived());
		payCountDONow.preUpdate();
		payCountService.updateNowChechangTotal(payCountDONow);
		ContractLog contractLog=new ContractLog();
		contractLog.setName("撤场");
		contractLog.setOid(contract.getOid());
		contractLog.setContractId(contract.getContractCode());
		contractLog.preInsert();
		contractService.savePayLog(contractLog);
		int i = contractService.update(contract);
		if(i>0){
			contractService.updateToContractState();
			return R.ok();
		}else{
			return R.error();
		}
	}
	@GetMapping("/getMonths")
	@ResponseBody
	public Double getMonths(@RequestParam Map<String, Object> params) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String contractChechangDate= params.get("contractChechangDate").toString();
		String contractStartDate= params.get("contractStartDate").toString();
		Double months=DateUtils.getMonths30(sdf.parse(contractStartDate),sdf.parse(contractChechangDate));
		return months;
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("business:contract:add")
	public R save( ContractDO contract){
		if(contractService.save(contract)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("business:contract:edit")
	public R update( ContractDO contract){
		int i = contractService.updateContract(contract);
		if(i==-1){
			return R.error("服务保证金已缴费无法修改");
		}else if(i==-2){
			return R.error("履约保证金已缴费无法修改");
		}else if(i==-3){
			return R.error("质量保证金已缴费无法修改");
		}else if(i==-4){
			return R.error("装修押金已缴费无法修改");
		}else{
			return R.ok();
		}
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("business:contract:remove")
	public R remove( Long id){
		if(contractService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	/**
	 * 撤场撤销
	 */
	@PostMapping( "/contractChechangCancel")
	@ResponseBody
	@RequiresPermissions("business:contract:contractChechangCancel")
	public R contractChechangCancel(Long id){
		ContractDO contractDO = contractService.get(id);
		if(contractDO.getState().equals("2")){
			contractService.revertAmortize(contractDO.getOid());
			contractService.revertPayCount(contractDO.getOid());
			contractService.revertContract(contractDO.getOid());
			contractService.deleteContractBf(contractDO.getOid());
			contractService.deleteAmortizeForRevert(contractDO);
			contractService.deleteAmortizeBf(contractDO.getOid());
			contractService.deletePayCountBf(contractDO.getOid());
			contractService.deletePayListChechang(contractDO.getOid());
			return R.ok();
		}else{
			return R.error("该合同非中途撤场合同，无法撤销");
		}

	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("business:contract:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		contractService.batchRemove(ids);
		return R.ok();
	}
	@ResponseBody
	@RequiresPermissions("business:contract:download")
	@PostMapping("/download")
	public void downloadTemplate(HttpServletResponse response, HttpServletRequest request) {
		InputStream inputStream = null;
		ServletOutputStream servletOutputStream = null;
		try {
			String filename = "商铺合同.xls";
			String path = "static/file/shopContract.xls";
			org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:"+path);

			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.addHeader("charset", "utf-8");
			response.addHeader("Pragma", "no-cache");
			String encodeName = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encodeName + "\"; filename*=utf-8''" + encodeName);

			inputStream = resource.getInputStream();
			servletOutputStream = response.getOutputStream();
			IOUtils.copy(inputStream, servletOutputStream);
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (servletOutputStream != null) {
					servletOutputStream.close();
					servletOutputStream = null;
				}
				if (inputStream != null) {
					inputStream.close();
					inputStream = null;
				}
				// 召唤jvm的垃圾回收器
				System.gc();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	@ResponseBody
	@RequiresPermissions("business:contractPay:downloadHistory")
	@PostMapping("/downloadHistory")
	public void downloadHistoryTemplate(HttpServletResponse response, HttpServletRequest request) {
		InputStream inputStream = null;
		ServletOutputStream servletOutputStream = null;
		try {
			String filename = "商铺合同历史缴费.xls";
			String path = "static/file/shopPayHistory.xls";
			org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:"+path);

			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.addHeader("charset", "utf-8");
			response.addHeader("Pragma", "no-cache");
			String encodeName = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encodeName + "\"; filename*=utf-8''" + encodeName);

			inputStream = resource.getInputStream();
			servletOutputStream = response.getOutputStream();
			IOUtils.copy(inputStream, servletOutputStream);
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (servletOutputStream != null) {
					servletOutputStream.close();
					servletOutputStream = null;
				}
				if (inputStream != null) {
					inputStream.close();
					inputStream = null;
				}
				// 召唤jvm的垃圾回收器
				System.gc();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	@ResponseBody
	@RequiresPermissions("business:contract:upload")
	@PostMapping("/upload")
	R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
		Date now = new Date();
		Double fenleiZujin;
		Double fenleiWuye;
		//把表单内容转换成流
		InputStream fileInputStream = file.getInputStream();
		HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
		HSSFSheet sheetAt = workbook.getSheetAt(0);
		Row nRow = null;
		String sheetName = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        java.text.DecimalFormat   df=new   java.text.DecimalFormat("0.00");
        String orderId= String.valueOf(System.currentTimeMillis());
		Map<String, Object> map = new HashMap<>(16);
		List<MerchantDO> merchantDOList=new ArrayList<>();
		map.put("type", "bank");
		List<DictDO> dictDOList=dictService.list(map);
		Map<String, Object> map1 = new HashMap<>(16);
		map1.put("type", "shop_pay_cycle");
		List<DictDO> dictDOList1=dictService.list(map1);
		//错误
		int repeat = 0;
		//记录第几行重复
		String num;
		StringBuilder sb = new StringBuilder();
		int end = sheetAt.getLastRowNum();
		if(end==1){
			return R.error("请填写合同信息");
		}
		UserDO userDO= ShiroUtils.getUser();
		Long deptId=userDO.getDeptId();
		List<String> shops=contractService.getShop(deptId);//查询当前项目下所有商铺
		List<String> contractCodes=contractService.getCodes(deptId);//查询当前项目下所有商铺
		//List<String> contractCodes=contractService.getContractCode(deptId);//查询当前项目下所有商铺
		List<ContractDO> shopContractDOList=new ArrayList<>();
        List<String> monthList = new ArrayList<String>();
		List<ToContractDO> toContractDOList = new ArrayList<>();
		boolean checkContract = false;
		ToContractDO toContractDOtemp = new ToContractDO();
		for (int j = 2; j <= end; j++) {
			if (repeat > 0) {
				break;
			}

            List<PayCountDO> payCountDOList = new ArrayList<>();
			int rowNum = j + 1;
			nRow = sheetAt.getRow(j);
			ContractDO s=new ContractDO();
			/*MerchantDO m=new MerchantDO();*/
			ToContractDO toContractDO = new ToContractDO();
			String oId= String.valueOf(System.currentTimeMillis());
			if((nRow.getCell(0) == null|| getCellValue(nRow.getCell(0)).equals(""))&&(nRow.getCell(1) == null|| getCellValue(nRow.getCell(1)).equals(""))&&(nRow.getCell(2) == null|| getCellValue(nRow.getCell(2)).equals(""))){
				if(j==2){
					sb.append("第" + rowNum + "行没有输入录入数据");
					repeat++;
				}
				break;
			}
			int a=nRow.getCell(0).getCellType();
			if (nRow.getCell(0) == null || getCellValue(nRow.getCell(0)).equals("")) {
				sb.append("第" + rowNum + "行没有输入楼宇ID");
				repeat++;
				break;
			}else{
				s.setBuildingId(Long.valueOf(getCellValue(nRow.getCell(0))));
				toContractDO.setBuildingId(Long.valueOf(getCellValue(nRow.getCell(0))));
				toContractDOtemp.setBuildingId(Long.valueOf(getCellValue(nRow.getCell(0))));
			}
			if (nRow.getCell(1) == null || getCellValue(nRow.getCell(1)).equals("")) {
				sb.append("第" + rowNum + "行没有输入合同编码");
				repeat++;
				break;
			}else{
				s.setContractCode(getCellValue(nRow.getCell(1)));
				toContractDO.setContractCode(getCellValue(nRow.getCell(1)));
				toContractDOtemp.setContractCode(getCellValue(nRow.getCell(1)));
			}
			boolean isContainsCode = UploadUtils.checkHasCode(s.getContractCode(),contractCodes);
			if(isContainsCode){
				sb.append("第" + rowNum + "行已拥有相同合同编号，请更改");
				repeat++;
				break;
			}
			if (nRow.getCell(2) == null || getCellValue(nRow.getCell(2)).equals("")) {
				sb.append("第" + rowNum + "行没有输入商铺编码");
				repeat++;
				break;
			}else{
				s.setShopCode(getCellValue(nRow.getCell(2)));
				toContractDO.setShopCode(getCellValue(nRow.getCell(2)));
				toContractDOtemp.setShopCode(getCellValue(nRow.getCell(2)));
			}
			boolean isContains = UploadUtils.checkHasCode(s.getShopCode(),shops);
			if(!isContains){
				sb.append("第" + rowNum + "行不存在该商铺");
				repeat++;
				break;
			}
			if(contractService.getCountForSaledShop(s.getShopCode())>0){
				sb.append("第" + rowNum + "行该商铺已出租");
				repeat++;
				break;
			}
			if (nRow.getCell(3) == null || getCellValue(nRow.getCell(3)).equals("")) {
				sb.append("第" + rowNum + "行没有输入签约人");
				repeat++;
				break;
			}else{
				s.setContractor(getCellValue(nRow.getCell(3)));
				/*m.setJuridicalPerson(getCellValue(nRow.getCell(3)));*/
			}
			if (nRow.getCell(4) == null || getCellValue(nRow.getCell(4)).equals("")) {
				sb.append("第" + rowNum + "行没有输入合同实测面积");
				repeat++;
				break;
			}else{
				s.setContractTrueArea(Double.valueOf(getCellValue(nRow.getCell(4))));
				toContractDO.setContractTrueArea(Double.valueOf(getCellValue(nRow.getCell(4))));
				toContractDOtemp.setContractTrueArea(Double.valueOf(getCellValue(nRow.getCell(4))));
			}
			//合同计租面积包含公摊
			s.setContractRentArea(s.getContractTrueArea()*1.2);
			toContractDO.setContractRentArea(s.getContractRentArea());
			toContractDOtemp.setContractRentArea(s.getContractRentArea());
			if (nRow.getCell(5) == null || getCellValue(nRow.getCell(5)).equals("")) {
				sb.append("第" + rowNum + "行没有输入计租面积");
				repeat++;
				break;
			}else{
				s.setRentArea(Double.valueOf(getCellValue(nRow.getCell(5))));
				toContractDO.setRentArea(Double.valueOf(getCellValue(nRow.getCell(5))));
				toContractDOtemp.setRentArea(Double.valueOf(getCellValue(nRow.getCell(5))));
			}

			if(checkContract){
				toContractDO.setOid(toContractDOtemp.getOid());
				toContractDO.setBuildingId(toContractDOtemp.getBuildingId());
				toContractDO.setContractCode(toContractDOtemp.getContractCode());
				toContractDO.setContractRentArea(toContractDOtemp.getContractRentArea());
				toContractDO.setContractTrueArea(toContractDOtemp.getContractTrueArea());
				toContractDO.setRentArea(toContractDOtemp.getRentArea());
				toContractDO.setShopCode(getCellValue(nRow.getCell(2)));
				toContractDOList.add(toContractDO);
				if((j+1)<=end&&StringUtils.isNotBlank(getCellValue(sheetAt.getRow(j+1).getCell(1)))) {
					if (!getCellValue(sheetAt.getRow(j + 1).getCell(1)).equals(getCellValue(nRow.getCell(1)))) {
						checkContract = false;
					}
				}
				continue;
			}

			if((j+1)<=end&&StringUtils.isNotBlank(getCellValue(sheetAt.getRow(j+1).getCell(1)))){
				if(getCellValue(sheetAt.getRow(j+1).getCell(1)).equals(getCellValue(nRow.getCell(1)))){
					checkContract = true;
				}
			}
			toContractDOtemp.setOid(oId);
			toContractDO.setOid(oId);
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(6)))) {
				s.setBrand(getCellValue(nRow.getCell(6)));
				/*m.setBrand(getCellValue(nRow.getCell(6)));*/
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(7)))) {
				s.setShopType(getCellValue(nRow.getCell(7)));
			}
			if (nRow.getCell(8) == null || getCellValue(nRow.getCell(8)).equals("")) {
				sb.append("第" + rowNum + "行没有输入租金单价");
				repeat++;
				break;
			}else{
				s.setUnitPrice(Double.valueOf(getCellValue(nRow.getCell(8))));
			}
			if(nRow.getCell(8) == null || getCellValue(nRow.getCell(8)).equals("")){
				sb.append("第" + rowNum + "行没有输入收款方式");
				repeat++;
				break;
			}else{
				String payType=getCellValue(nRow.getCell(9));
				payType=getValue(payType,dictDOList1);
				if(payType.equals("")){
					sb.append("第" + rowNum + "行付款方式错误");
					repeat++;
					break;
				}
				s.setPayType(payType);
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(10))))  {
				String yy = getCellValue(nRow.getCell(10)).replace("，",",");
				s.setYouhui(yy);
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(11))))  {
				nRow.getCell(11).setCellType(1);
				String aaa = nRow.getCell(11).getStringCellValue();
				s.setDiscount(Double.valueOf(aaa));
			}else{
				s.setDiscount(10.0);
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(13))))  {
				fenleiZujin = Double.valueOf(getCellValue(nRow.getCell(13)));
			}else{
				fenleiZujin =0.00;
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(14))))  {
				fenleiWuye = Double.valueOf(getCellValue(nRow.getCell(14)));
			}else{
				fenleiWuye = 0.00;
			}
			if (nRow.getCell(15) == null || getCellValue(nRow.getCell(15)).equals("")) {
				sb.append("第" + rowNum + "行没有输入合同开始时间");
				repeat++;
				break;
			}else{
				Date d=new Date();
				try {
					if(!HSSFDateUtil.isCellDateFormatted(nRow.getCell(15))){
						sb.append("第" + rowNum + "行没有输入正确格式的合同开始时间");
						repeat++;
						break;
					}
					d=nRow.getCell(15).getDateCellValue();
				} catch (Exception e) {
					String date=getCellValue(nRow.getCell(15));
					if(!UploadUtils.checkDate(date)){
						sb.append("第" + rowNum + "行没有输入正确格式的合同开始时间");
						repeat++;
						break;
					}else {
						int year= Integer.parseInt(date.substring(0,4));
						int month= Integer.parseInt(date.substring(5,7));
						int day= Integer.parseInt(date.substring(8,10));
						Calendar calendar=Calendar.getInstance();
						calendar.set(year,month-1,day);
						d=calendar.getTime();
					}
				}
				s.setContractStartDate(d);
			}
			if (nRow.getCell(16) == null || getCellValue(nRow.getCell(16)).equals("")) {
				sb.append("第" + rowNum + "行没有输入合同终止时间");
				repeat++;
				break;
			}else{
				Date d=new Date();
				try {
					if(!HSSFDateUtil.isCellDateFormatted(nRow.getCell(16))){
						sb.append("第" + rowNum + "行没有输入正确格式的合同终止时间");
						repeat++;
						break;
					}
					d=nRow.getCell(16).getDateCellValue();
				} catch (Exception e) {
					String date=getCellValue(nRow.getCell(16));
					if(!UploadUtils.checkDate(date)){
						sb.append("第" + rowNum + "行没有输入正确格式的合同终止时间");
						repeat++;
						break;
					}else {
						int year= Integer.parseInt(date.substring(0,4));
						int month= Integer.parseInt(date.substring(5,7));
						int day= Integer.parseInt(date.substring(8,10));
						Calendar calendar=Calendar.getInstance();
						calendar.set(year,month-1,day);
						d=calendar.getTime();
					}
				}
				s.setContractEndDate(d);
			}
			if (s.getContractEndDate().before(s.getContractStartDate())){
				sb.append("第" + rowNum + "行录入的合同开始时间大于合同终止时间，请重新输入");
				repeat++;
				break;
			}
			/*sc = contractService.getDate(s.getBuildingId(),s.getShopCode());
			if(sc != null){
				if((!s.getContractStartDate().after(sc.getContractEndDate()))||(!s.getContractEndDate().before(sc.getContractEndDate()))){}
			}*/

			DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

			DateTime startContract = formatter.parseDateTime(DateFormat.getDateInstance().format(s.getContractStartDate()));
			DateTime endContract = formatter.parseDateTime(DateFormat.getDateInstance().format(s.getContractEndDate()));
			int months = Months.monthsBetween(startContract, endContract).getMonths()+1;
			s.setAmortizeMonths(months);
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(17))))  {
				s.setReceivableLvyue(Double.valueOf(getCellValue(nRow.getCell(17))));
			}else {
				s.setReceivableLvyue(0.00);
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(18))))  {
				s.setReceivableZhiliang(Double.valueOf(getCellValue(nRow.getCell(18))));
			}else {
				s.setReceivableZhiliang(0.00);
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(19))))  {
				s.setReceivableFuwu(Double.valueOf(getCellValue(nRow.getCell(19))));
			}else {
				s.setReceivableFuwu(0.00);
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(20))))  {
				s.setReceivableZhuangxiu(Double.valueOf(getCellValue(nRow.getCell(20))));
			}else {
				s.setReceivableZhuangxiu(0.00);
			}
			if (nRow.getCell(21) == null || getCellValue(nRow.getCell(21)).equals("")) {
				sb.append("第" + rowNum + "行没有输入手机号或格式错误");
				repeat++;
				break;
			}else{
				/*if(!UploadUtils.isPhone(getCellValue(nRow.getCell(20)))){
					sb.append("第" + rowNum + "行输入手机号格式错误");
					repeat++;
					break;
				}*/
				s.setPhone(getCellValue(nRow.getCell(21)));
				/*m.setPhone(getCellValue(nRow.getCell(21)));*/
			}
			if (nRow.getCell(22) == null || getCellValue(nRow.getCell(22)).equals("")) {
				sb.append("第" + rowNum + "行没有输入证件号码");
				repeat++;
				break;
			}else{
				if(!UploadUtils.isIdNum(getCellValue(nRow.getCell(22)))&&
						!UploadUtils.checkTax(getCellValue(nRow.getCell(22)))
						&&!getCellValue(nRow.getCell(22)).contains("-")){
					sb.append("第" + rowNum + "行输入证件号码格式错误");
					repeat++;
					break;
				}
				s.setIdCard(getCellValue(nRow.getCell(22)));
				/*m.setIdCard(getCellValue(nRow.getCell(22)));*/
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(23))))  {
				s.setKaidanCode(getCellValue(nRow.getCell(23)));
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(24))))  {
				s.setKaidanName(getCellValue(nRow.getCell(24)));
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(25))))  {
				s.setPayee(getCellValue(nRow.getCell(25)));
				/*m.setPayee(getCellValue(nRow.getCell(25)));*/
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(26))))  {
				s.setAccount(getCellValue(nRow.getCell(26)));
				/*m.setCardNo(getCellValue(nRow.getCell(26)));*/
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(27))))  {
				String bankType=getCellValue(nRow.getCell(27));
				bankType=getValue(bankType,dictDOList);
				s.setBank(bankType);
				/*m.setOpeningBank(bankType);*/
			}
			if (nRow.getCell(28) == null || getCellValue(nRow.getCell(28)).equals("")) {
				sb.append("第" + rowNum + "行没有输入签约日期");
				repeat++;
				break;
			}else{
				Date d=new Date();
				try {
					if(!HSSFDateUtil.isCellDateFormatted(nRow.getCell(28))){
						sb.append("第" + rowNum + "行没有输入正确格式的签约日期");
						repeat++;
						break;
					}
					d=nRow.getCell(28).getDateCellValue();
				} catch (Exception e) {
					String date=getCellValue(nRow.getCell(28));
					if(!UploadUtils.checkDate(date)){
						sb.append("第" + rowNum + "行没有输入正确格式的签约日期");
						repeat++;
						break;
					}else {
						int year= Integer.parseInt(date.substring(0,4));
						int month= Integer.parseInt(date.substring(5,7));
						int day= Integer.parseInt(date.substring(8,10));
						Calendar calendar=Calendar.getInstance();
						calendar.set(year,month-1,day);
						d=calendar.getTime();
					}
				}
				s.setContractDate(d);
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(29))))  {
				s.setZulinbiao(getCellValue(nRow.getCell(29)));
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(30))))  {
				s.setCopyIdCard(getCellValue(nRow.getCell(30)));
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(31))))  {
				s.setCopyBankCard(getCellValue(nRow.getCell(31)));
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(32))))  {
				s.setTuzhi(getCellValue(nRow.getCell(32)));
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(33))))  {
				s.setRemarks(getCellValue(nRow.getCell(33)));
			}
			if (s.getContractStartDate().before(now)){
				if (s.getContractEndDate().before(now)){
					s.setState("3");//合同状态-失效
				}else{
					s.setState("0");//合同状态-生效
				}
			}else{
				s.setState("1");//合同状态-待生效
			}
			//合同起止天数差
			long days=(s.getContractEndDate().getTime()-s.getContractStartDate().getTime())/(1000*3600*24)+1;

			Double noYouhui = s.getUnitPrice()*s.getRentArea();//每月应收租金：单价×面积
			Double yueFen = DateUtils.getMonths30(s.getContractStartDate(),s.getContractEndDate());//月数
			yueFen = UploadUtils.format(yueFen);
			s.setContractPayTotal(yueFen*noYouhui);
			s.setFenleiZujin(fenleiZujin);
			s.setFenleiWuye(fenleiWuye);
			//计算合同起应收租金
			if(StringUtils.isNoneBlank(s.getYouhui())){
				String[] youhui =null;
				youhui = s.getYouhui().split("[,]");
				int cycle = (int)Math.floor(yueFen/(Integer.valueOf(youhui[0])+Integer.valueOf(youhui[1])));//计算有几个优惠周期：月数÷优惠月数
				Double shiji = yueFen - cycle*Double.valueOf(youhui[1]);//计算应交租金的实际月数：总月数-周期×免月
				s.setContractPayTotal(shiji*noYouhui);
			}
			if(s.getDiscount()!=null && !s.getDiscount().equals("")){
				Double befDiscount = s.getContractPayTotal();
				s.setContractPayTotal(befDiscount*s.getDiscount()*0.1);
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(12))))  {
				s.setContractPayTotal(Double.valueOf(getCellValue(nRow.getCell(12))));
			}
			s.setYouhuiUnitPrice(s.getContractPayTotal()/yueFen/s.getRentArea());//优惠后每月每平租金
			s.setFenleiGuanli(s.getContractPayTotal()-s.getFenleiWuye()-s.getFenleiZujin());
			s.setAmortizePrice(s.getContractPayTotal()/days);
			Double tanxiaoGuanli = s.getFenleiGuanli()/days;
			Double tanxiaoWuye = s.getFenleiWuye()/days;
			Double tanxiaoZujin = s.getFenleiZujin()/days;
			s.setFenleiGuanliUnreceived(s.getFenleiGuanli());
			s.setFenleiWuyeUnreceived(s.getFenleiWuye());
			s.setFenleiZujinUnreceived(s.getFenleiZujin());
            Double priceMonth =s.getContractPayTotal()/yueFen;
            Double wuyeMonth =s.getFenleiWuye()/yueFen;
            Double zujinMonth =s.getFenleiZujin()/yueFen;
			Double guanliMonth =s.getFenleiGuanli()/yueFen;
			priceMonth = UploadUtils.format(priceMonth);
			wuyeMonth = UploadUtils.format(wuyeMonth);
			zujinMonth = UploadUtils.format(zujinMonth);
			guanliMonth = UploadUtils.format(guanliMonth);

			s.setContractPayTotalReceived(0.00);
			s.setContractPayTotalUnreceived(s.getContractPayTotal());
			s.setFenleiZujinReceived(0.00);
			s.setFenleiWuyeReceived(0.00);
			s.setFenleiGuanliReceived(0.00);

			s.setReceivedFuwu(0.00);
			s.setReceivedLvyue(0.00);
			s.setReceivedZhiliang(0.00);
			s.setReceivedZhuangxiu(0.00);
			s.setDiscrepancy(s.getReceivableFuwu()+s.getReceivableLvyue()+s.getReceivableZhiliang()+s.getReceivableZhuangxiu());
			s.setTotal(s.getDiscrepancy()+s.getContractPayTotal());
			s.preInsert();
			s.setDeptId(deptId);
			s.setOrderId(orderId);
			s.setOid(oId);
			shopContractDOList.add(s);
			toContractDOList.add(toContractDO);
			/*m.preInsert();
			m.setDeptId(deptId);
			m.setOrderId(orderId);
			merchantDOList.add(m);*/
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(s.getContractStartDate());
			List<AmortizeDO> tanxiaoDOList = new ArrayList<>();
			int day;
			try {
				monthList = DateUtils.getMonthBetweenTanxiao(s.getContractStartDate(),s.getContractEndDate());
				if(monthList.size()==1){
					AmortizeDO tanxiaoDO = new AmortizeDO();
					if(s.getContractStartDate().getDate()==1&&DateUtils.isLastDayOfMonth(s.getContractEndDate())){
						day = DateUtils.getMaxDay(s.getContractStartDate())-1;
					}else{
						day=(int)(s.getContractEndDate().getDate()-Double.valueOf(s.getContractStartDate().getDate())+1);
					}
					tanxiaoDO.setDay(day);
					tanxiaoDO.setContractCode(s.getContractCode());
					tanxiaoDO.setContractor(s.getContractor());
					tanxiaoDO.setBrand(s.getBrand());
					tanxiaoDO.setAmortizePriceWuye(s.getFenleiWuye());
					tanxiaoDO.setAmortizePriceGuanli(s.getFenleiGuanli());
					tanxiaoDO.setAmortizePriceZujin(s.getFenleiZujin());
					tanxiaoDO.setAmortizePrice(s.getAmortizePrice());
                    tanxiaoDO.setTotal(tanxiaoDO.getAmortizePriceGuanli()+tanxiaoDO.getAmortizePriceWuye()+tanxiaoDO.getAmortizePriceZujin());
					tanxiaoDO.setMonth(sdf.format(s.getContractStartDate()));
					tanxiaoDO.setShopCode(s.getShopCode());
					tanxiaoDO.setOid(oId);
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
							if(s.getContractStartDate().getDate()==1){
								day = DateUtils.getMaxDay(s.getContractStartDate())-1;
							}else {
								day =(int) ( DateUtils.getMaxDay(s.getContractStartDate())-Double.valueOf(s.getContractStartDate().getDate()));
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
							day = s.getContractEndDate().getDate();
							tanxiaoDO.setDay(day);
						/*	wuyeTotal = UploadUtils.format(wuyeTotal);
							zujinTotal = UploadUtils.format(zujinTotal);
							guanliTotal = UploadUtils.format(guanliTotal);*/
							Double one = s.getFenleiWuye();
							Double two = s.getFenleiGuanli();
							Double three = s.getFenleiZujin();
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
						tanxiaoDO.setContractCode(s.getContractCode());
						tanxiaoDO.setContractor(s.getContractor());
						tanxiaoDO.setBrand(s.getBrand());
						tanxiaoDO.setBuildingId(s.getBuildingId());
						tanxiaoDO.setAmortizePrice(s.getAmortizePrice());
                        tanxiaoDO.setTotal(tanxiaoDO.getAmortizePriceGuanli()+tanxiaoDO.getAmortizePriceWuye()+tanxiaoDO.getAmortizePriceZujin());
						tanxiaoDO.setMonth(monthList.get(k-1));
						tanxiaoDO.setShopCode(s.getShopCode());
						tanxiaoDO.setOid(oId);
						tanxiaoDO.preInsert();
						tanxiaoDOList.add(tanxiaoDO);
					}
				}
				if(tanxiaoDOList.size()>0){
					int i=contractService.batchInsertTanxiao(tanxiaoDOList);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}

			if(s.getPayType().equals("1")){
                try {
                    Double sMonth= Double.valueOf(0);
                    Double eMonth= Double.valueOf(0);
					monthList = DateUtils.getMonthBetween(s.getContractStartDate(),s.getContractEndDate(),s.getPayType());
                    if(monthList.size()==1){
						PayCountDO pc=new PayCountDO();
                        pc.setFenleiZujin(s.getFenleiZujin());
                        pc.setFenleiWuye(s.getFenleiWuye());
                        pc.setContractPayTotal(s.getContractPayTotal());
                        pc.setFenleiGuanli(s.getFenleiGuanli());
						pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
						pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
						pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
						pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
                        pc.setCountStartDate(s.getContractStartDate());
                        pc.setCountEndDate(s.getContractEndDate());
						pc.setReceivableZhiliang(s.getReceivableZhiliang());
						pc.setReceivableFuwu(s.getReceivableFuwu());
						pc.setReceivableLvyue(s.getReceivableLvyue());
						pc.setReceivableZhuangxiu(s.getReceivableZhuangxiu());
						pc.setDiscrepancy(s.getDiscrepancy());
						pc.setState(s.getState());
						pc.setBuildingId(s.getBuildingId());
						pc.setContractCode(s.getContractCode());
						pc.setShopCode(s.getShopCode());
						pc.setBrand(s.getBrand());
						pc.setContractor(s.getContractor());
						pc.setUnitPrice(s.getUnitPrice());
						pc.setFenleiGuanliReceived(0.00);
						pc.setFenleiWuyeReceived(0.00);
						pc.setFenleiZujinReceived(0.00);
						pc.preInsert();
						pc.setOid(oId);
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
								pc.setFenleiZujin(s.getFenleiZujin());
								pc.setFenleiWuye(s.getFenleiWuye());
								pc.setContractPayTotal(s.getContractPayTotal());
								pc.setFenleiGuanli(pc.getContractPayTotal()-pc.getFenleiZujin()-pc.getFenleiWuye());
								pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
								pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
								pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
								pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
								pc.setCountStartDate(s.getContractStartDate());
								pc.setCountEndDate(s.getContractEndDate());
							}else{
								if(k==1){
									if(s.getContractStartDate().getDate()==1){
										sMonth= Double.valueOf(1);
									}else {
										sMonth= Double.valueOf(( DateUtils.getMaxDay(s.getContractStartDate())-Double.valueOf(s.getContractStartDate().getDate()))/30);
										sMonth = UploadUtils.format(sMonth);
									}
									st = s.getContractStartDate();
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
                                /*if(DateUtils.isLastDayOfMonth(s.getContractEndDate())){
                                    eMonth=Double.valueOf(1);
                                }else {
                                    eMonth=Double.valueOf(df.format((Double.valueOf(s.getContractEndDate().getDate())/30)));
									eMonth = UploadUtils.format(eMonth);
                                }*/
									st = DateUtils.dayAddOne(en);
									pc.setFenleiZujin(s.getFenleiZujin() - zujinTotal);
									pc.setFenleiWuye(s.getFenleiWuye() - wuyeTotal);
									pc.setContractPayTotal(s.getContractPayTotal() - priceTotal);
									pc.setFenleiGuanli(s.getFenleiGuanli()-guanliTotal);
									pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
									pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
									pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
									pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
									pc.setCountStartDate(st);
									pc.setCountEndDate(s.getContractEndDate());

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
							pc.setReceivableZhiliang(s.getReceivableZhiliang());
							pc.setReceivableFuwu(s.getReceivableFuwu());
							pc.setReceivableLvyue(s.getReceivableLvyue());
							pc.setReceivableZhuangxiu(s.getReceivableZhuangxiu());
							pc.setDiscrepancy(s.getDiscrepancy());
							pc.setState(s.getState());
							pc.setBuildingId(s.getBuildingId());
							pc.setContractCode(s.getContractCode());
							pc.setShopCode(s.getShopCode());
							pc.setBrand(s.getBrand());
							pc.setUnitPrice(s.getUnitPrice());
							pc.setFenleiGuanliReceived(0.00);
							pc.setFenleiWuyeReceived(0.00);
							pc.setFenleiZujinReceived(0.00);
							pc.setBrand(s.getBrand());
							pc.setContractor(s.getContractor());
							pc.setOid(oId);
                            pc.preInsert();
                            payCountDOList.add(pc);
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else if(s.getPayType().equals("2")){
				Double sMonth= Double.valueOf(0);
				Double eMonth= Double.valueOf(0);
				Double zujinTotal = 0.00;
				Double wuyeTotal = 0.00;
				Double priceTotal = 0.00;
				Double guanliTotal = 0.00;
				Date st = new Date();
				Date en = new Date();
				String[] eachMonth;
				int cycle =DateUtils.getMonthF(s.getContractStartDate(),s.getContractEndDate())/3;
				int cyclerest =DateUtils.getMonthF(s.getContractStartDate(),s.getContractEndDate())%3;
				try {
					monthList = DateUtils.getMonthBetween(s.getContractStartDate(),s.getContractEndDate(),s.getPayType());
				for(int k = 1;k<=monthList.size();k++){
					PayCountDO pc=new PayCountDO();
					eachMonth = monthList.get(k-1).split("[|]");

					if(k==1&&k==monthList.size()){
						/*if(s.getContractStartDate().getDate()==1){
							sMonth= Double.valueOf(1);
						}else {
							sMonth= Double.valueOf(( DateUtils.getMaxDay(s.getContractStartDate())-Double.valueOf(s.getContractStartDate().getDate()))/30);
							sMonth = UploadUtils.format(sMonth);
						}if(DateUtils.isLastDayOfMonth(s.getContractEndDate())){
							eMonth=Double.valueOf(1);
						}else {
							eMonth=Double.valueOf(df.format((Double.valueOf(s.getContractEndDate().getDate())/30)));
							eMonth = UploadUtils.format(eMonth);
						}*/
						pc.setFenleiZujin(s.getFenleiZujin());
						pc.setFenleiWuye(s.getFenleiWuye());
						pc.setContractPayTotal(s.getContractPayTotal());
						pc.setFenleiGuanli(s.getFenleiGuanli());
						pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
						pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
						pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
						pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
						pc.setCountStartDate(s.getContractStartDate());
						pc.setCountEndDate(s.getContractEndDate());
					}else{
						if(k==1){
							if(s.getContractStartDate().getDate()==1){
								sMonth= Double.valueOf(1);
							}else {
								sMonth= Double.valueOf(( DateUtils.getMaxDay(s.getContractStartDate())-Double.valueOf(s.getContractStartDate().getDate()))/30);
								sMonth = UploadUtils.format(sMonth);
							}
							st = s.getContractStartDate();
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
								/*if(DateUtils.isLastDayOfMonth(s.getContractEndDate())){
									eMonth=Double.valueOf(1);
								}else {
									eMonth=Double.valueOf(df.format((Double.valueOf(s.getContractEndDate().getDate())/30)));
									eMonth = UploadUtils.format(eMonth);
								}*/
							 	st = DateUtils.dayAddOne(en);
								pc.setFenleiZujin(s.getFenleiZujin() - zujinTotal);
								pc.setFenleiWuye(s.getFenleiWuye() - wuyeTotal);
								pc.setContractPayTotal(s.getContractPayTotal() - priceTotal);
								pc.setFenleiGuanli(s.getFenleiGuanli()-guanliTotal);
								pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
								pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
								pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
								pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
								pc.setCountStartDate(st);
								pc.setCountEndDate(s.getContractEndDate());
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
					pc.setReceivableZhiliang(s.getReceivableZhiliang());
					pc.setReceivableFuwu(s.getReceivableFuwu());
					pc.setReceivableLvyue(s.getReceivableLvyue());
					pc.setReceivableZhuangxiu(s.getReceivableZhuangxiu());
					pc.setDiscrepancy(s.getDiscrepancy());
					pc.setState(s.getState());
					pc.setBuildingId(s.getBuildingId());
					pc.setContractCode(s.getContractCode());
					pc.setShopCode(s.getShopCode());
					pc.setBrand(s.getBrand());
					pc.setUnitPrice(s.getUnitPrice());
					pc.setFenleiGuanliReceived(0.00);
					pc.setFenleiWuyeReceived(0.00);
					pc.setFenleiZujinReceived(0.00);
					pc.preInsert();
					pc.setBrand(s.getBrand());
					pc.setContractor(s.getContractor());
					pc.setOid(oId);
					payCountDOList.add(pc);
				}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if(s.getPayType().equals("3")){
				Double sMonth= Double.valueOf(0);
				Double eMonth= Double.valueOf(0);
				int monthCha = DateUtils.getMonthF(s.getContractStartDate(),s.getContractEndDate());
				if(monthCha<12){
					PayCountDO pc=new PayCountDO();
					pc.setReceivableZhiliang(s.getReceivableZhiliang());
					pc.setReceivableFuwu(s.getReceivableFuwu());
					pc.setReceivableLvyue(s.getReceivableLvyue());
					pc.setReceivableZhuangxiu(s.getReceivableZhuangxiu());
					pc.setDiscrepancy(s.getDiscrepancy());
					pc.setState(s.getState());
					pc.setFenleiZujin(s.getFenleiZujin());
					pc.setFenleiWuye(s.getFenleiWuye());
					pc.setContractPayTotal(s.getContractPayTotal());
					pc.setFenleiGuanli(s.getFenleiGuanli());
					pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
					pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
					pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
					pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
					pc.setCountStartDate(s.getContractStartDate());
					pc.setCountEndDate(s.getContractEndDate());
					pc.setBuildingId(s.getBuildingId());
					pc.setContractCode(s.getContractCode());
					pc.setShopCode(s.getShopCode());
					pc.setBrand(s.getBrand());
					pc.setUnitPrice(s.getUnitPrice());
					pc.setFenleiGuanliReceived(0.00);
					pc.setFenleiWuyeReceived(0.00);
					pc.setFenleiZujinReceived(0.00);
					pc.preInsert();
					pc.setBrand(s.getBrand());
					pc.setContractor(s.getContractor());
					pc.setOid(oId);
					payCountDOList.add(pc);
				}else{
					//如果大于等于12个月的情况
					String[] eachMonth;
					int cycle =DateUtils.getMonthF(s.getContractStartDate(),s.getContractEndDate())/12;
					int cyclerest =DateUtils.getMonthF(s.getContractStartDate(),s.getContractEndDate())%12;
					try {
						monthList = DateUtils.getMonthBetween(s.getContractStartDate(),s.getContractEndDate(),s.getPayType());
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
								/*if(s.getContractStartDate().getDate()==1){
									sMonth= Double.valueOf(1);
								}else {
									sMonth= Double.valueOf(( DateUtils.getMaxDay(s.getContractStartDate())-Double.valueOf(s.getContractStartDate().getDate()))/30);
									sMonth = UploadUtils.format(sMonth);
								}if(DateUtils.isLastDayOfMonth(s.getContractEndDate())){
									eMonth=Double.valueOf(1);
								}else {
									eMonth=Double.valueOf(df.format((Double.valueOf(s.getContractEndDate().getDate())/30)));
									eMonth = UploadUtils.format(eMonth);
								}*/
								pc.setFenleiZujin(s.getFenleiZujin());
								pc.setFenleiWuye(s.getFenleiWuye());
								pc.setContractPayTotal(s.getContractPayTotal());
								pc.setFenleiGuanli(s.getFenleiGuanli());
								pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
								pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
								pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
								pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
								pc.setCountStartDate(s.getContractStartDate());
								pc.setCountEndDate(s.getContractEndDate());
							}else{
								if(k==1){
									if(s.getContractStartDate().getDate()==1){
										sMonth= Double.valueOf(1);
									}else {
										sMonth= Double.valueOf(( DateUtils.getMaxDay(s.getContractStartDate())-Double.valueOf(s.getContractStartDate().getDate()))/30);
										sMonth = UploadUtils.format(sMonth);
									}
									st = s.getContractStartDate();
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
										/*if(DateUtils.isLastDayOfMonth(s.getContractEndDate())){
											eMonth=Double.valueOf(1);
										}else {
											eMonth=Double.valueOf(df.format((Double.valueOf(s.getContractEndDate().getDate())/30)));
											eMonth = UploadUtils.format(eMonth);
										}*/
										st = DateUtils.dayAddOne(en);
										pc.setFenleiZujin(s.getFenleiZujin()-zujinTotal);
										pc.setFenleiWuye(s.getFenleiWuye()-wuyeTotal);
										pc.setContractPayTotal(s.getContractPayTotal()-priceTotal);
										pc.setFenleiGuanli(s.getFenleiGuanli() - guanliTotal);
										pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
										pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
										pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
										pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
										pc.setCountStartDate(st);
										pc.setCountEndDate(s.getContractEndDate());
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
							pc.setReceivableZhiliang(s.getReceivableZhiliang());
							pc.setReceivableFuwu(s.getReceivableFuwu());
							pc.setReceivableLvyue(s.getReceivableLvyue());
							pc.setReceivableZhuangxiu(s.getReceivableZhuangxiu());
							pc.setDiscrepancy(s.getDiscrepancy());
							pc.setState(s.getState());
							pc.setBuildingId(s.getBuildingId());
							pc.setContractCode(s.getContractCode());
							pc.setShopCode(s.getShopCode());
							pc.setBrand(s.getBrand());
							pc.setUnitPrice(s.getUnitPrice());
							pc.setFenleiGuanliReceived(0.00);
							pc.setFenleiWuyeReceived(0.00);
							pc.setFenleiZujinReceived(0.00);
							pc.setBrand(s.getBrand());
							pc.preInsert();
							pc.setContractor(s.getContractor());
							pc.setOid(oId);
							payCountDOList.add(pc);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}else if(s.getPayType().equals("4")){
				Double sMonth= Double.valueOf(0);
				Double eMonth= Double.valueOf(0);
				int monthCha = DateUtils.getMonthF(s.getContractStartDate(),s.getContractEndDate());
				if(monthCha<6){
					PayCountDO pc=new PayCountDO();
					pc.setReceivableZhiliang(s.getReceivableZhiliang());
					pc.setReceivableFuwu(s.getReceivableFuwu());
					pc.setReceivableLvyue(s.getReceivableLvyue());
					pc.setReceivableZhuangxiu(s.getReceivableZhuangxiu());
					pc.setDiscrepancy(s.getDiscrepancy());
					pc.setState(s.getState());
					pc.setFenleiZujin(s.getFenleiZujin());
					pc.setFenleiWuye(s.getFenleiWuye());
					pc.setContractPayTotal(s.getContractPayTotal());
					pc.setFenleiGuanli(s.getFenleiGuanli());
					pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
					pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
					pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
					pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
					pc.setCountStartDate(s.getContractStartDate());
					pc.setCountEndDate(s.getContractEndDate());
					pc.setBuildingId(s.getBuildingId());
					pc.setContractCode(s.getContractCode());
					pc.setShopCode(s.getShopCode());
					pc.setBrand(s.getBrand());
					pc.setUnitPrice(s.getUnitPrice());
					pc.setFenleiGuanliReceived(0.00);
					pc.setFenleiWuyeReceived(0.00);
					pc.setFenleiZujinReceived(0.00);
					pc.setBrand(s.getBrand());
					pc.preInsert();
					pc.setContractor(s.getContractor());
					pc.setOid(oId);
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
					int cycle =DateUtils.getMonthF(s.getContractStartDate(),s.getContractEndDate())/6;
					int cyclerest =DateUtils.getMonthF(s.getContractStartDate(),s.getContractEndDate())%6;
					try {
						monthList = DateUtils.getMonthBetween(s.getContractStartDate(),s.getContractEndDate(),s.getPayType());
						for(int k = 1;k<=monthList.size();k++){
							PayCountDO pc=new PayCountDO();
							eachMonth = monthList.get(k-1).split("[|]");
							if(k==1&&k==monthList.size()){
								/*if(s.getContractStartDate().getDate()==1){
									sMonth= Double.valueOf(1);
								}else {
									sMonth= Double.valueOf(( DateUtils.getMaxDay(s.getContractStartDate())-Double.valueOf(s.getContractStartDate().getDate()))/30);
									sMonth = UploadUtils.format(sMonth);
								}if(DateUtils.isLastDayOfMonth(s.getContractEndDate())){
									eMonth=Double.valueOf(1);
								}else {
									eMonth=Double.valueOf(df.format((Double.valueOf(s.getContractEndDate().getDate())/30)));
									eMonth = UploadUtils.format(eMonth);
								}*/
								pc.setFenleiZujin(s.getFenleiZujin());
								pc.setFenleiWuye(s.getFenleiWuye());
								pc.setContractPayTotal(s.getContractPayTotal());
								pc.setFenleiGuanli(s.getFenleiGuanli());
								pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
								pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
								pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
								pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
								pc.setCountStartDate(s.getContractStartDate());
								pc.setCountEndDate(s.getContractEndDate());
							}else{
								if(k==1){
									if(s.getContractStartDate().getDate()==1){
										sMonth= Double.valueOf(1);
									}else {
										sMonth= Double.valueOf(( DateUtils.getMaxDay(s.getContractStartDate())-Double.valueOf(s.getContractStartDate().getDate()))/30);
										sMonth = UploadUtils.format(sMonth);
									}
									st = s.getContractStartDate();
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
										/*if(DateUtils.isLastDayOfMonth(s.getContractEndDate())){
											eMonth=Double.valueOf(1);
										}else {
											eMonth=Double.valueOf(df.format((Double.valueOf(s.getContractEndDate().getDate())/30)));
											eMonth = UploadUtils.format(eMonth);
										}*/
										st = DateUtils.dayAddOne(en);
										pc.setFenleiZujin(s.getFenleiZujin()-zujinTotal);
										pc.setFenleiWuye(s.getFenleiWuye()-wuyeTotal);
										pc.setContractPayTotal(s.getContractPayTotal()-priceTotal);
										pc.setFenleiGuanli(s.getFenleiGuanli() - guanliTotal);
										pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
										pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
										pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
										pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
										pc.setCountStartDate(st);
										pc.setCountEndDate(s.getContractEndDate());
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
							pc.setReceivableZhiliang(s.getReceivableZhiliang());
							pc.setReceivableFuwu(s.getReceivableFuwu());
							pc.setReceivableLvyue(s.getReceivableLvyue());
							pc.setReceivableZhuangxiu(s.getReceivableZhuangxiu());
							pc.setDiscrepancy(s.getDiscrepancy());
							pc.setState(s.getState());
							pc.setBuildingId(s.getBuildingId());
							pc.setContractCode(s.getContractCode());
							pc.setShopCode(s.getShopCode());
							pc.setBrand(s.getBrand());
							pc.setUnitPrice(s.getUnitPrice());
							pc.setFenleiGuanliReceived(0.00);
							pc.setFenleiWuyeReceived(0.00);
							pc.setFenleiZujinReceived(0.00);
							pc.setBrand(s.getBrand());
							pc.preInsert();
							pc.setContractor(s.getContractor());
							pc.setOid(oId);
							payCountDOList.add(pc);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}

			}else if(s.getPayType().equals("5")){
					PayCountDO pc=new PayCountDO();
					pc.setReceivableZhiliang(s.getReceivableZhiliang());
					pc.setReceivableFuwu(s.getReceivableFuwu());
					pc.setReceivableLvyue(s.getReceivableLvyue());
					pc.setReceivableZhuangxiu(s.getReceivableZhuangxiu());
					pc.setDiscrepancy(s.getDiscrepancy());
					pc.setState(s.getState());
					pc.setFenleiZujin(s.getFenleiZujin());
					pc.setFenleiWuye(s.getFenleiWuye());
					pc.setContractPayTotal(s.getContractPayTotal());
					pc.setFenleiGuanli(s.getFenleiGuanli());
					pc.setFenleiZujinUnreceived(pc.getFenleiZujin());
					pc.setFenleiWuyeUnreceived(pc.getFenleiWuye());
					pc.setContractPayTotalUnreceived(pc.getContractPayTotal());
					pc.setFenleiGuanliUnreceived(pc.getFenleiGuanli());
					pc.setCountStartDate(s.getContractStartDate());
					pc.setCountEndDate(s.getContractEndDate());
					pc.setBuildingId(s.getBuildingId());
					pc.setContractCode(s.getContractCode());
					pc.setShopCode(s.getShopCode());
					pc.setBrand(s.getBrand());
					pc.setUnitPrice(s.getUnitPrice());
					pc.setFenleiGuanliReceived(0.00);
					pc.setFenleiWuyeReceived(0.00);
					pc.setFenleiZujinReceived(0.00);
					pc.setContractor(s.getContractor());
					pc.setOid(oId);
					pc.preInsert();
					payCountDOList.add(pc);
			}
			if(payCountDOList.size()>0){
				int i=payCountService.batchInsert(payCountDOList);
			}
		}
		if (repeat > 0) {
			return R.error(sb.toString());
		} else {
			payCountService.savePayFinal();
			contractService.batchInsertToShop(toContractDOList);
			/*if(merchantDOList.size()>0){
				int i=merchantService.batchInsert(merchantDOList);
				if(i>0){
					merchantService.duplicate(orderId);
					i=merchantService.synchronization(orderId);
					merchantService.removeCopy(orderId);
				}
			}*/
			if(shopContractDOList.size()>0){
				int i=contractService.batchInsert(shopContractDOList);
				contractService.updateToContractState();
				ContractLog contractLog=new ContractLog();
				contractLog.setName("合同录入");
				contractLog.setOrderId(orderId);
				contractLog.preInsert();
				contractService.saveLog(contractLog);
				if(i>0){
					payCountService.deleteCount();
					return R.ok("导入"+i+"条数据");
				}else{
					R.error();
				}
			}else {
				return R.error("没有能导入的合同或者导入的商铺已签署合同");
			}
		}
		return R.error();
	}
	@ResponseBody
	@RequiresPermissions("business:contractPay:uploadHistory")
	@PostMapping("/uploadHistory")
	R uploadHistory(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
		Date now = new Date();
		Double fenleiZujin;
		Double fenleiWuye;
		//把表单内容转换成流
		InputStream fileInputStream = file.getInputStream();
		HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
		HSSFSheet sheetAt = workbook.getSheetAt(0);
		Row nRow = null;
		String sheetName = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		java.text.DecimalFormat   df=new   java.text.DecimalFormat("0.00");
		String orderId= String.valueOf(System.currentTimeMillis());
		//错误
		int repeat = 0;
		//记录第几行重复
		String num;
		StringBuilder sb = new StringBuilder();
		int end = sheetAt.getLastRowNum();
		if(end==1){
			return R.error("请填写合同信息");
		}
		List<ContractDO> shopContractDOList=new ArrayList<>();
		List<ToContractDO> toContractDOList = new ArrayList<>();
		boolean checkContract = false;

		for (int j = 2; j <= end; j++) {
			String contractCodeTemp;
			Double zujin;
			Double wuye;
			Double guanli;
			Double lvyue;
			Double zhiliang;
			Double fuwu;
			Double zhuangxiu;

			if (repeat > 0) {
				break;
			}
			List<PayCountDO> payCountDOListGet = new ArrayList<>();
			int rowNum = j + 1;
			nRow = sheetAt.getRow(j);
			ContractDO s=new ContractDO();
			ContractDO sT=new ContractDO();
			PayCountDO p=new PayCountDO();
			if((nRow.getCell(0) == null|| getCellValue(nRow.getCell(0)).equals(""))&&(nRow.getCell(1) == null|| getCellValue(nRow.getCell(1)).equals(""))&&(nRow.getCell(2) == null|| getCellValue(nRow.getCell(2)).equals(""))){
				break;
			}
			int a=nRow.getCell(0).getCellType();
			if (nRow.getCell(0) == null || getCellValue(nRow.getCell(0)).equals("")) {
				sb.append("第" + rowNum + "行没有输入合同编码");
				repeat++;
				break;
			}else{
				contractCodeTemp = getCellValue(nRow.getCell(0));
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(1)))) {
				zujin = Double.valueOf(getCellValue(nRow.getCell(1)));
			} else{
				zujin = 0.00;
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(2)))) {
				wuye = Double.valueOf(getCellValue(nRow.getCell(2)));
			} else{
				wuye = 0.00;
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(3)))) {
				guanli = Double.valueOf(getCellValue(nRow.getCell(3)));
			} else{
				guanli = 0.00;
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(4)))) {
				lvyue = Double.valueOf(getCellValue(nRow.getCell(4)));
			} else{
				lvyue = 0.00;
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(5)))) {
				zhiliang = Double.valueOf(getCellValue(nRow.getCell(5)));
			} else{
				zhiliang = 0.00;
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(6)))) {
				fuwu = Double.valueOf(getCellValue(nRow.getCell(6)));
			} else{
				fuwu = 0.00;
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(7)))) {
				zhuangxiu = Double.valueOf(getCellValue(nRow.getCell(7)));
			} else{
				zhuangxiu = 0.00;
			}
			s = contractService.getByCode(contractCodeTemp);
			sT.setContractCode(contractCodeTemp);
			sT.setReceivedLvyue(s.getReceivedLvyue()+lvyue);
			sT.setReceivedZhiliang(s.getReceivedZhiliang()+zhiliang);
			sT.setReceivedZhuangxiu(s.getReceivedZhuangxiu()+zhuangxiu);
			sT.setReceivedFuwu(s.getReceivedFuwu()+fuwu);
			sT.setDiscrepancy(s.getDiscrepancy() - lvyue - fuwu - zhiliang -zhuangxiu);
			sT.setFenleiZujinReceived(s.getFenleiZujinReceived()+zujin);
			sT.setFenleiZujinUnreceived(s.getFenleiZujinUnreceived() - zujin);
			sT.setFenleiGuanliReceived(s.getFenleiGuanliReceived()+guanli);
			sT.setFenleiGuanliUnreceived(s.getFenleiGuanliUnreceived() - guanli);
			sT.setFenleiWuyeReceived(s.getFenleiWuyeReceived()+wuye);
			sT.setFenleiWuyeUnreceived(s.getFenleiWuyeUnreceived() - wuye);
			sT.setTotal(s.getTotal()-lvyue-fuwu-zhiliang-zhuangxiu-zujin-guanli-wuye);
			sT.preUpdate();
			sT.setId(s.getId());
			p.setContractCode(contractCodeTemp);
			p.setReceivedLvyue(s.getReceivedLvyue()+lvyue);
			p.setReceivedZhiliang(s.getReceivedZhiliang()+zhiliang);
			p.setReceivedZhuangxiu(s.getReceivedZhuangxiu()+zhuangxiu);
			p.setReceivedFuwu(s.getReceivedFuwu()+fuwu);
			p.setDiscrepancy(s.getDiscrepancy() - lvyue - fuwu - zhiliang -zhuangxiu);
			payCountService.updateFourOther(p);
			contractService.update(sT);
			payCountDOListGet = payCountService.getCode(contractCodeTemp);
			for(int i=0;i<payCountDOListGet.size();i++){
				PayCountDO pay = new PayCountDO();
				pay = payCountDOListGet.get(i);
				if((zujin+guanli+wuye)==0.00){
					break;
				}
				if(zujin>pay.getFenleiZujinUnreceived()){
				    zujin = zujin - pay.getFenleiZujinUnreceived();
                    pay.setFenleiZujinUnreceived(0.00);
                    pay.setFenleiZujinReceived(pay.getFenleiZujin());
                    pay.setContractPayTotalUnreceived(pay.getContractPayTotalUnreceived()-pay.getFenleiZujinReceived());
                    pay.setContractPayTotalReceived(pay.getContractPayTotalReceived() + pay.getFenleiZujinReceived());
                }else{
                    pay.setFenleiZujinReceived(pay.getFenleiZujinReceived()+zujin);
                    pay.setFenleiZujinUnreceived(pay.getFenleiZujinUnreceived() - zujin);
                    pay.setContractPayTotalUnreceived(pay.getContractPayTotalUnreceived()-zujin);
                    pay.setContractPayTotalReceived(pay.getContractPayTotalReceived() + zujin);
                    zujin = 0.00;
                }
                if(guanli>pay.getFenleiGuanliUnreceived()){
                    guanli = guanli - pay.getFenleiGuanliUnreceived();
                    pay.setFenleiGuanliUnreceived(0.00);
                    pay.setFenleiGuanliReceived(pay.getFenleiGuanli());
                    pay.setContractPayTotalUnreceived(pay.getContractPayTotalUnreceived()-pay.getFenleiGuanliReceived());
                    pay.setContractPayTotalReceived(pay.getContractPayTotalReceived() + pay.getFenleiGuanliReceived());
                }else{
                    pay.setFenleiGuanliReceived(pay.getFenleiGuanliReceived()+guanli);
                    pay.setFenleiGuanliUnreceived(pay.getFenleiGuanliUnreceived() - guanli);
                    pay.setContractPayTotalUnreceived(pay.getContractPayTotalUnreceived()-guanli);
                    pay.setContractPayTotalReceived(pay.getContractPayTotalReceived() + guanli);
                    guanli = 0.00;
                }
                if(wuye>pay.getFenleiWuyeUnreceived()){
                    wuye = wuye - pay.getFenleiWuyeUnreceived();
                    pay.setFenleiWuyeUnreceived(0.00);
                    pay.setFenleiWuyeReceived(pay.getFenleiWuye());
                    pay.setContractPayTotalUnreceived(pay.getContractPayTotalUnreceived()-pay.getFenleiWuyeReceived());
                    pay.setContractPayTotalReceived(pay.getContractPayTotalReceived() + pay.getFenleiWuyeReceived());
                }else{
                    pay.setFenleiWuyeReceived(pay.getFenleiWuyeReceived()+wuye);
                    pay.setFenleiWuyeUnreceived(pay.getFenleiWuyeUnreceived() - wuye);
                    pay.setContractPayTotalUnreceived(pay.getContractPayTotalUnreceived()-wuye);
                    pay.setContractPayTotalReceived(pay.getContractPayTotalReceived() + wuye);
                    wuye = 0.00;
                }
                payCountService.update(pay);
			}
		}
		if (repeat > 0) {
			return R.error(sb.toString());
		}else{
            return R.ok();
        }
	}
	//计算月份
	/*private Double getMonthDays(Date startDate,Date endDate) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		Calendar cst = Calendar.getInstance();
		Calendar cen = Calendar.getInstance();
		cst.setTime(startDate);
		cen.setTime(endDate);
		int yearst = cst.get(Calendar.YEAR);
		int yearen = cen.get(Calendar.YEAR);
		int monthst = cst.get(Calendar.MONTH);
		int monthen = cen.get(Calendar.MONTH);
		int dayst = cst.get(Calendar.DAY_OF_MONTH);
		int dayen = cen.get(Calendar.DAY_OF_MONTH);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(startDate.toString().substring(0, 4)));
		calendar.set(Calendar.MONTH, Integer.parseInt(startDate.toString().substring(5, 7)) -1);
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int day = maxDay - dayst + dayen;
		Double partMonth = Double.valueOf(day)/30;
		if(monthst==12){
			yearst = yearst+1;
			monthst = 1;
		}
		else {
			monthst=monthst+1;
		}
		DateTime startContract = formatter.parseDateTime(DateFormat.getDateInstance().format(""+yearst+""+monthst));
		DateTime endContract = formatter.parseDateTime(DateFormat.getDateInstance().format(""+yearen+""+monthen));
		int months = Months.monthsBetween(startContract, endContract).getMonths();

		return partMonth + Double.valueOf(months);
	}*/
	//判断单元格的类型
	private String getCellValue(Cell cell) {
		String cellValue = "";
		DecimalFormat df   = new DecimalFormat("######0.00");
		DecimalFormat df1   = new DecimalFormat("#");
		// System
		switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:
				cellValue = cell.getRichStringCellValue().getString().trim();
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				if(!StringUtils.isIntegerForDouble(cell.getNumericCellValue())){
					cellValue = df.format(cell.getNumericCellValue()).toString();
				}else {
					cellValue = df1.format(cell.getNumericCellValue()).toString();
				}
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				cellValue = cell.getCellFormula();
				break;
			default:
				cellValue = "";
		}
		return cellValue;
	}
	private String getValue(String sellType, List<DictDO> dictDOList) {
		String value="";
		for(DictDO d:dictDOList){
			if(d.getName().equals(sellType)){
				value=d.getValue();
				break;
			}
		}
		return value;
	}
	@GetMapping("/info")
	String info(@RequestParam Map<String, Object> params,Model model){
		ShopDO shopDOList= shopService.get(Long.valueOf(params.get("shopId").toString()));
		model.addAttribute("shop", shopDOList);
		List<MerchantDO> merchantDOList=merchantService.list(params);
		if(merchantDOList.size()>0){
			model.addAttribute("merchant", merchantDOList.get(0));
		}
		return "business/contract/info";
	}
	@GetMapping("/log")
	@ResponseBody
	public PageUtils log(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<ContractLog> contractLogList = contractService.log(query);
		int total = contractService.countLog(query);
		PageUtils pageUtils = new PageUtils(contractLogList, total);
		return pageUtils;
	}
	@GetMapping("/shou/{id}")
	@RequiresPermissions("business:contractPay:shou")
	String shou(@PathVariable("id") Long id,Model model){
		Date date=new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		String d=sdf.format(date);
		model.addAttribute("date", d);
		PayCountDO payCount = payCountService.getByCo(id);
		payCount.setCountStartDateString(sdf.format(payCount.getCountStartDate()));
		payCount.setCountEndDateString(sdf.format(payCount.getCountEndDate()));
		model.addAttribute("payCount", payCount);
		model.addAttribute("name", ShiroUtils.getUser().getName());
		return "business/contractPay/shou";
	}
	@GetMapping("/contractPay")
	@RequiresPermissions("business:contract:contractPay")
	String ContractPay(){
		return "business/contractPay/contractPay";
	}
	@ResponseBody
	@GetMapping("/listPay")
	@RequiresPermissions("business:contract:contractPay")
	public PageUtils listPay(@RequestParam Map<String, Object> params){
		//查询列表数据
		UserDO userDO=ShiroUtils.getUser();
		params.put("deptId",userDO.getDeptId());
		Query query = new Query(params);
		List<PayCountDO> payCountList = payCountService.list(query);
		int total = payCountService.count(query);
		PageUtils pageUtils = new PageUtils(payCountList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/payList")
	public PageUtils payList(@RequestParam Map<String, Object> params){
		//查询列表数据
		UserDO userDO=ShiroUtils.getUser();
		params.put("deptId",userDO.getDeptId());
		Query query = new Query(params);
		List<PayListDO> payCountList = payListService.list(query);
		int total = payListService.count(query);
		PageUtils pageUtils = new PageUtils(payCountList, total);
		return pageUtils;
	}
	/**
	 * 缴费
	 */
	@ResponseBody
	@PostMapping("/pay")
	public PayListDO pay(HttpServletRequest request) throws IOException {
		String jsonStr = request.getParameter("mydata");
		AmortizeDO amortizeDO = new AmortizeDO();
		List<AmortizeDO> amortizeDOs = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		PayListDO payListDO = mapper.readValue(jsonStr, PayListDO.class);
		PayCountDO payCountDO = payCountService.get(payListDO.getYewuId());
		ContractDO contractDO = contractService.getByOid(payCountDO.getOid());
		int ii = payCountService.getUnpayedCount(payCountDO);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		UserDO userDO=ShiroUtils.getUser();
		DeptDO deptDO=deptService.get(userDO.getDeptId());
		String payId=contractService.getId("SP",userDO.getDeptId());
		BusinessBuildingDO buildingDO=businessBuildingService.get(payCountDO.getBuildingId());
		payListDO.setCode(payId);
		payListDO.setOid(payCountDO.getOid());
		payListDO.setShopCode(payCountDO.getShopCode());
		payListDO.setCountStartDate(payCountDO.getCountStartDate());
		payListDO.setCountEndDate(payCountDO.getCountEndDate());
		payListDO.setName(payCountDO.getContractor());
		payListDO.setDeptId(userDO.getDeptId());
		payListDO.setDeptName(userDO.getDeptName());
		payListDO.setState("0");
		payListDO.setBuildingId(payCountDO.getBuildingId());
		payListDO.setBuildingName(buildingDO.getName());
		payListDO.setPrint(0);
		payListDO.setArea(Long.valueOf(deptDO.getArea()));
		payListDO.setBrand(payCountDO.getBrand());
		payListDO.preInsert();
		String sType=payListDO.getSType();
		PayCountDO p=new PayCountDO();
		p.setId(payListDO.getYewuId());
		p.setOid(payCountDO.getOid());
		p.setContractCode(payCountDO.getContractCode());
		Double price;
		Double priceForAmortize;
		ContractLog contractLog=new ContractLog();
        String startDate = sdf.format(payListDO.getCountStartDate());
        String endDate = sdf.format(payListDO.getCountEndDate());
		amortizeDO.setOid(payCountDO.getOid());
		amortizeDO.setStartDate(startDate);
		amortizeDO.setEndDate(endDate);
		amortizeDOs = amortizeService.getForPay(amortizeDO);
		if(sType.equals("1")){
			if(ii > 0){
				payListDO.setRequest("1");
				return payListDO;
			}
			price =payListDO.getFenleiZujinReceived();
			priceForAmortize = price;
			payListDO.setPrice(price);
			for(int i = 0;i < amortizeDOs.size();i++){
				AmortizeDO a = new AmortizeDO();
				a = amortizeDOs.get(i);
				if(priceForAmortize==0.00){
					break;
				}
				if(priceForAmortize>(a.getAmortizePriceZujin()-a.getAmortizePriceZujinReceived())){
					priceForAmortize = UploadUtils.format(priceForAmortize - (a.getAmortizePriceZujin()-a.getAmortizePriceZujinReceived()));
					a.setAmortizePriceZujinReceived(a.getAmortizePriceZujin());
				}else{
					a.setAmortizePriceZujinReceived(a.getAmortizePriceZujinReceived()+priceForAmortize);
					priceForAmortize = 0.00;
				}
				amortizeService.updateForPay(a);
			}
			p.setFenleiZujinReceived(UploadUtils.format(payCountDO.getFenleiZujinReceived()+price));
			p.setContractPayTotalReceived(UploadUtils.format(payCountDO.getContractPayTotalReceived()+price));
			p.setContractPayTotalUnreceived(UploadUtils.format(payCountDO.getContractPayTotalUnreceived()-price));
			p.setFenleiZujinUnreceived(UploadUtils.format(payCountDO.getFenleiZujinUnreceived()-price));
			payCountService.updatePrice(p);
			contractDO.setTotal(UploadUtils.format(contractDO.getTotal()-price));
			contractDO.setFenleiZujinReceived(UploadUtils.format(contractDO.getFenleiZujinReceived()+price));
			contractDO.setContractPayTotalReceived(UploadUtils.format(contractDO.getContractPayTotalReceived()+price));
			contractDO.setContractPayTotalUnreceived(UploadUtils.format(contractDO.getContractPayTotalUnreceived()-price));
			contractDO.setFenleiZujinUnreceived(UploadUtils.format(contractDO.getFenleiZujinUnreceived()-price));
		}else if(sType.equals("2")){
			if(ii > 0){
				payListDO.setRequest("1");
				return payListDO;
			}
			price =payListDO.getFenleiWuyeReceived();
			payListDO.setPrice(price);
			priceForAmortize = price;
			for(int i = 0;i < amortizeDOs.size();i++){
				AmortizeDO a = new AmortizeDO();
				a = amortizeDOs.get(i);
				if(priceForAmortize==0.00){
					break;
				}
				if(priceForAmortize>(a.getAmortizePriceWuye()-a.getAmortizePriceWuyeReceived())){
					priceForAmortize = UploadUtils.format(priceForAmortize - (a.getAmortizePriceWuye()-a.getAmortizePriceWuyeReceived()));
					a.setAmortizePriceWuyeReceived(a.getAmortizePriceWuye());
				}else{
					a.setAmortizePriceWuyeReceived(a.getAmortizePriceWuyeReceived()+priceForAmortize);
					priceForAmortize = 0.00;
				}
				amortizeService.updateForPay(a);
			}
			p.setFenleiWuyeReceived(UploadUtils.format(payCountDO.getFenleiWuyeReceived()+price));
			p.setContractPayTotalReceived(UploadUtils.format(payCountDO.getContractPayTotalReceived()+price));
			p.setContractPayTotalUnreceived(UploadUtils.format(payCountDO.getContractPayTotalUnreceived()-price));
			p.setFenleiWuyeUnreceived(UploadUtils.format(payCountDO.getFenleiWuyeUnreceived()-price));
			payCountService.updatePrice(p);
			contractDO.setTotal(UploadUtils.format(contractDO.getTotal()-price));
			contractDO.setFenleiWuyeReceived(UploadUtils.format(contractDO.getFenleiWuyeReceived()+price));
			contractDO.setFenleiWuyeUnreceived(UploadUtils.format(contractDO.getFenleiWuyeUnreceived()-price));
			contractDO.setContractPayTotalReceived(UploadUtils.format(contractDO.getContractPayTotalReceived()+price));
			contractDO.setContractPayTotalUnreceived(UploadUtils.format(contractDO.getContractPayTotalUnreceived()-price));
		}else if(sType.equals("3")){
			if(ii > 0){
				payListDO.setRequest("1");
				return payListDO;
			}
			price =payListDO.getFenleiGuanliReceived();
			payListDO.setPrice(price);
			priceForAmortize = price;
			for(int i = 0;i < amortizeDOs.size();i++){
				AmortizeDO a = new AmortizeDO();
				a = amortizeDOs.get(i);
				if(priceForAmortize==0.00){
					break;
				}
				if(priceForAmortize>(a.getAmortizePriceGuanli()-a.getAmortizePriceGuanliReceived())){
					priceForAmortize = UploadUtils.format(priceForAmortize - (a.getAmortizePriceGuanli()-a.getAmortizePriceGuanliReceived()));
					a.setAmortizePriceGuanliReceived(a.getAmortizePriceGuanli());
				}else{
					a.setAmortizePriceGuanliReceived(a.getAmortizePriceGuanliReceived()+priceForAmortize);
					priceForAmortize = 0.00;
				}
				amortizeService.updateForPay(a);
			}
			p.setFenleiGuanliReceived(UploadUtils.format(payCountDO.getFenleiGuanliReceived()+price));
			p.setContractPayTotalReceived(UploadUtils.format(payCountDO.getContractPayTotalReceived()+price));
			p.setContractPayTotalUnreceived(UploadUtils.format(payCountDO.getContractPayTotalUnreceived()-price));
			p.setFenleiGuanliUnreceived(UploadUtils.format(payCountDO.getFenleiGuanliUnreceived()-price));
			payCountService.updatePrice(p);
			contractDO.setTotal(UploadUtils.format(contractDO.getTotal()-price));
			contractDO.setFenleiGuanliReceived(UploadUtils.format(contractDO.getFenleiGuanliReceived()+price));
			contractDO.setFenleiGuanliUnreceived(UploadUtils.format(contractDO.getFenleiGuanliUnreceived()-price));
			contractDO.setContractPayTotalReceived(UploadUtils.format(contractDO.getContractPayTotalReceived()+price));
			contractDO.setContractPayTotalUnreceived(UploadUtils.format(contractDO.getContractPayTotalUnreceived()-price));
		}else if(sType.equals("4")){
			price =payListDO.getReceivedZhuangxiuIn();
			payListDO.setPrice(price);
			p.setReceivedZhuangxiu(UploadUtils.format(payCountDO.getReceivedZhuangxiu()+price));
			p.setDiscrepancy(UploadUtils.format(payCountDO.getDiscrepancy()-price));
			payCountService.updateFourOther(p);
			contractDO.setTotal(UploadUtils.format(contractDO.getTotal()-price));
			contractDO.setReceivedZhuangxiu(UploadUtils.format(contractDO.getReceivedZhuangxiu()+price));
			contractDO.setDiscrepancy(UploadUtils.format(contractDO.getDiscrepancy()-price));
		}else if(sType.equals("5")){
			price =payListDO.getReceivedLvyueIn();
			payListDO.setPrice(price);
			p.setReceivedLvyue(UploadUtils.format(payCountDO.getReceivedLvyue()+price));
			p.setDiscrepancy(UploadUtils.format(payCountDO.getDiscrepancy()-price));
			payCountService.updateFourOther(p);
			contractDO.setTotal(UploadUtils.format(contractDO.getTotal()-price));
			contractDO.setReceivedLvyue(UploadUtils.format(contractDO.getReceivedLvyue()+price));
			contractDO.setDiscrepancy(UploadUtils.format(contractDO.getDiscrepancy()-price));
		}else if(sType.equals("6")){
			price =payListDO.getReceivedZhiliangIn();
			payListDO.setPrice(price);
			p.setReceivedZhiliang(UploadUtils.format(payCountDO.getReceivedZhiliang()+price));
			p.setDiscrepancy(UploadUtils.format(payCountDO.getDiscrepancy()-price));
			payCountService.updateFourOther(p);
			contractDO.setTotal(UploadUtils.format(contractDO.getTotal()-price));
			contractDO.setReceivedZhiliang(UploadUtils.format(contractDO.getReceivedZhiliang()+price));
			contractDO.setDiscrepancy(UploadUtils.format(contractDO.getDiscrepancy()-price));
		}else if(sType.equals("7")){
			price =payListDO.getReceivedFuwuIn();
			payListDO.setPrice(price);
			p.setReceivedFuwu(UploadUtils.format(payCountDO.getReceivedFuwu()+price));
			p.setDiscrepancy(UploadUtils.format(payCountDO.getDiscrepancy()-price));
			payCountService.updateFourOther(p);
			contractDO.setTotal(UploadUtils.format(contractDO.getTotal()-price));
			contractDO.setReceivedFuwu(UploadUtils.format(contractDO.getReceivedFuwu()+price));
			contractDO.setDiscrepancy(UploadUtils.format(contractDO.getDiscrepancy()-price));
		}
		contractLog.setName(payListDO.getReceiptBy()+"收"+payListDO.getSTypeName()+""+payListDO.getPrice()+"元");
		contractLog.setContractId(payCountDO.getContractCode());
		contractLog.setOid(payCountDO.getOid());
		contractLog.preInsert();
		payListDO.setFenleiZujinUnreceived(p.getFenleiZujinUnreceived());
		payListDO.setFenleiGuanliUnreceived(p.getFenleiGuanliUnreceived());
		payListDO.setFenleiWuyeUnreceived(p.getFenleiWuyeUnreceived());
		payListDO.setReceivedZhiliang(p.getReceivedZhiliang());
		payListDO.setReceivedZhuangxiu(p.getReceivedZhuangxiu());
		payListDO.setReceivedFuwu(p.getReceivedFuwu());
		payListDO.setReceivedLvyue(p.getReceivedLvyue());
		payListDO.preInsert();
		payListService.save(payListDO);
		contractService.savePayLog(contractLog);
		contractService.update(contractDO);
		return payListDO;
	}

	@GetMapping("/payIndexForChechang")
	@RequiresPermissions("business:contract:payIndexForChechang")
	String payIndexForChechang(){
		return "business/pay/payForChechang";
	}
	@GetMapping("/payListViewForChechang")
	@ResponseBody
	public PageUtils payListViewForChechang(@RequestParam Map<String, Object> params){
		UserDO userDO=ShiroUtils.getUser();
		params.put("deptId",userDO.getDeptId());
		//查询列表数据
		Query query = new Query(params);
		List<PayListDO> payListDOs = contractService.payForChechang(query);
		int total = contractService.countPayListForChechang(query);
		PageUtils pageUtils = new PageUtils(payListDOs, total);
		return pageUtils;
	}
	@GetMapping("/payIndex")
	@RequiresPermissions("business:contract:payIndex")
	String payIndex(){
		return "business/pay/pay";
	}
	@GetMapping("/payListView")
	@ResponseBody
	public PageUtils payListView(@RequestParam Map<String, Object> params){
		UserDO userDO=ShiroUtils.getUser();
		params.put("deptId",userDO.getDeptId());
		//查询列表数据
		Query query = new Query(params);
		List<PayListDO> payListDOs = contractService.pay(query);
		int total = contractService.countPayList(query);
		PageUtils pageUtils = new PageUtils(payListDOs, total);
		return pageUtils;
	}


	@PostMapping("/chongxiao")
	@ResponseBody
	public R chongxiao(Long id){
		PayListDO payListDO=payListService.get(id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		AmortizeDO amortizeDO = new AmortizeDO();
		List<AmortizeDO> amortizeDOs = new ArrayList<>();
		payListDO.setState("2");
		payListService.update(payListDO);
		PayCountDO payCountDO = payCountService.get(payListDO.getYewuId());
		ContractDO contractDO=contractService.getByOid(payCountDO.getOid());
		Double price=payListDO.getPrice();
		Double priceForAmortize;
		payListDO.setPrice(-price);
		payListDO.setState("1");
		payListDO.setChongxiaoCode(payListDO.getCode());
		String payId=contractService.getId("SP",ShiroUtils.getUser().getDeptId());;
		payListDO.setCode(payId);
		payListDO.preInsert();
		payListDO.setOid(payCountDO.getOid());
		String sType=payListDO.getSType();
		String startDate = sdf.format(payListDO.getCountStartDate());
		String endDate = sdf.format(payListDO.getCountEndDate());
		amortizeDO.setOid(payCountDO.getOid());
		amortizeDO.setStartDate(startDate);
		amortizeDO.setEndDate(endDate);
		amortizeDOs = amortizeService.getForPay(amortizeDO);
		switch(sType){
			case "1":
				priceForAmortize = price;
				for(int i = amortizeDOs.size();i > 0;i--){
					AmortizeDO a = new AmortizeDO();
					a = amortizeDOs.get(i-1);
					if(priceForAmortize==0.00){
						break;
					}
					if(priceForAmortize>a.getAmortizePriceZujinReceived()){
						priceForAmortize = UploadUtils.format(priceForAmortize - a.getAmortizePriceZujinReceived());
						a.setAmortizePriceZujinReceived(0.00);
					}else{
						a.setAmortizePriceZujinReceived(UploadUtils.format(a.getAmortizePriceZujinReceived()-priceForAmortize));
						priceForAmortize = 0.00;
					}
					amortizeService.updateForPay(a);
				}
				payCountDO.setContractPayTotalReceived(payCountDO.getContractPayTotalReceived()-price);
				payCountDO.setContractPayTotalUnreceived(payCountDO.getContractPayTotalUnreceived()+price);
				payCountDO.setFenleiZujinReceived(payCountDO.getFenleiZujinReceived()-price);
				payCountDO.setFenleiZujinUnreceived(payCountDO.getFenleiZujinUnreceived()+price);
				payCountService.updatePrice(payCountDO);
				contractDO.setContractPayTotalReceived(contractDO.getContractPayTotalReceived()-price);
				contractDO.setContractPayTotalUnreceived(contractDO.getContractPayTotalUnreceived()+price);
				contractDO.setFenleiZujinReceived(contractDO.getFenleiZujinReceived()-price);
				contractDO.setFenleiZujinUnreceived(contractDO.getFenleiZujinUnreceived()+price);
				contractDO.setTotal(contractDO.getTotal()+price);
				break;
			case "2":
				priceForAmortize = price;
				for(int i = amortizeDOs.size();i > 0;i--){
					AmortizeDO a = new AmortizeDO();
					a = amortizeDOs.get(i-1);
					if(priceForAmortize==0.00){
						break;
					}
					if(priceForAmortize>a.getAmortizePriceWuyeReceived()){
						priceForAmortize = priceForAmortize - a.getAmortizePriceWuyeReceived();
						a.setAmortizePriceWuyeReceived(0.00);
					}else{
						a.setAmortizePriceWuyeReceived(a.getAmortizePriceWuyeReceived()-priceForAmortize);
						priceForAmortize = 0.00;
					}
					amortizeService.updateForPay(a);
				}
				payCountDO.setContractPayTotalReceived(payCountDO.getContractPayTotalReceived()-price);
				payCountDO.setContractPayTotalUnreceived(payCountDO.getContractPayTotalUnreceived()+price);
				payCountDO.setFenleiWuyeReceived(payCountDO.getFenleiWuyeReceived()-price);
				payCountDO.setFenleiWuyeUnreceived(payCountDO.getFenleiWuyeUnreceived()+price);
				payCountService.updatePrice(payCountDO);
				contractDO.setContractPayTotalReceived(contractDO.getContractPayTotalReceived()-price);
				contractDO.setContractPayTotalUnreceived(contractDO.getContractPayTotalUnreceived()+price);
				contractDO.setFenleiWuyeReceived(contractDO.getFenleiWuyeReceived()-price);
				contractDO.setFenleiWuyeUnreceived(contractDO.getFenleiWuyeUnreceived()+price);
				contractDO.setTotal(contractDO.getTotal()+price);
				break;
			case "3":
				priceForAmortize = price;
				for(int i = amortizeDOs.size();i > 0;i--){
					AmortizeDO a = new AmortizeDO();
					a = amortizeDOs.get(i-1);
					if(priceForAmortize==0.00){
						break;
					}
					if(priceForAmortize>a.getAmortizePriceGuanliReceived()){
						priceForAmortize = priceForAmortize - a.getAmortizePriceGuanliReceived();
						a.setAmortizePriceGuanliReceived(0.00);
					}else{
						a.setAmortizePriceGuanliReceived(a.getAmortizePriceGuanliReceived()-priceForAmortize);
						priceForAmortize = 0.00;
					}
					amortizeService.updateForPay(a);
				}
				payCountDO.setContractPayTotalReceived(payCountDO.getContractPayTotalReceived()-price);
				payCountDO.setContractPayTotalUnreceived(payCountDO.getContractPayTotalUnreceived()+price);
				payCountDO.setFenleiGuanliReceived(payCountDO.getFenleiGuanliReceived()-price);
				payCountDO.setFenleiGuanliUnreceived(payCountDO.getFenleiGuanliUnreceived()+price);
				payCountService.updatePrice(payCountDO);
				contractDO.setContractPayTotalReceived(contractDO.getContractPayTotalReceived()-price);
				contractDO.setContractPayTotalUnreceived(contractDO.getContractPayTotalUnreceived()+price);
				contractDO.setFenleiGuanliReceived(contractDO.getFenleiGuanliReceived()-price);
				contractDO.setFenleiGuanliUnreceived(contractDO.getFenleiGuanliUnreceived()+price);
				contractDO.setTotal(contractDO.getTotal()+price);
				break;
			case "4":
				payCountDO.setReceivedZhuangxiu(payCountDO.getReceivedZhuangxiu()-price);
				payCountDO.setDiscrepancy(payCountDO.getDiscrepancy()+price);
				payCountService.updateFourOther(payCountDO);
				contractDO.setTotal(contractDO.getTotal()+price);
				contractDO.setReceivedZhuangxiu(contractDO.getReceivedZhuangxiu()-price);
				contractDO.setDiscrepancy(contractDO.getDiscrepancy()+price);
				break;
			case "5":
				payCountDO.setReceivedLvyue(payCountDO.getReceivedLvyue()-price);
				payCountDO.setDiscrepancy(payCountDO.getDiscrepancy()+price);
				payCountService.updateFourOther(payCountDO);
				contractDO.setTotal(contractDO.getTotal()+price);
				contractDO.setReceivedLvyue(contractDO.getReceivedLvyue()-price);
				contractDO.setDiscrepancy(contractDO.getDiscrepancy()+price);
				break;
			case "6":
				payCountDO.setReceivedZhiliang(payCountDO.getReceivedZhiliang()-price);
				payCountDO.setDiscrepancy(payCountDO.getDiscrepancy()+price);
				payCountService.updateFourOther(payCountDO);
				contractDO.setTotal(contractDO.getTotal()+price);
				contractDO.setReceivedZhiliang(contractDO.getReceivedZhiliang()-price);
				contractDO.setDiscrepancy(contractDO.getDiscrepancy()+price);
				break;
			case "7":
				payCountDO.setReceivedFuwu(payCountDO.getReceivedFuwu()-price);
				payCountDO.setDiscrepancy(payCountDO.getDiscrepancy()+price);
				payCountService.updateFourOther(payCountDO);
				contractDO.setTotal(contractDO.getTotal()+price);
				contractDO.setReceivedFuwu(contractDO.getReceivedFuwu()-price);
				contractDO.setDiscrepancy(contractDO.getDiscrepancy()+price);
				break;
		}

		ContractLog contractLog=new ContractLog();
		contractLog.setName(payListDO.getReceiptBy()+"冲销"+payListDO.getSTypeName()+""+payListDO.getPrice()+"元，冲销编码为"+payListDO.getChongxiaoCode());
		contractLog.setContractId(contractDO.getContractCode());
		contractLog.setOid(contractDO.getOid());
		contractLog.preInsert();
		contractService.savePayLog(contractLog);
		contractService.update(contractDO);
		if(payListService.save(payListDO)>0){
			return R.ok();
		}
		return R.error();
	}
	@GetMapping("/print/{id}")
	@RequiresPermissions("business:contract:print")
	String print(@PathVariable("id") Long id, Model model){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		PayListDO payListDO=payListService.get(id);
		ContractDO contractDO=contractService.getByOid(payListDO.getOid());
		PayCountDO payCountDO=payCountService.get(payListDO.getYewuId());
		payCountDO.setCountStartDateString(sdf.format(payCountDO.getCountStartDate()));
		payCountDO.setCountEndDateString(sdf.format(payCountDO.getCountEndDate()));
		payCountDO.setContractStartDateString(sdf.format(contractDO.getContractStartDate()));
		payCountDO.setContractEndDateString(sdf.format(contractDO.getContractEndDate()));
		String sType=payListDO.getSType();
		String date = null;
		if(sType.equals("1")||sType.equals("2")||sType.equals("3")){
			date = payCountDO.getCountStartDateString()+"-"+payCountDO.getCountEndDateString();
		}else{
			date = payCountDO.getContractStartDateString()+"-"+payCountDO.getContractEndDateString();
		}
		Date pay=payListDO.getPayDate();
		Calendar c=Calendar.getInstance();
		c.setTime(pay);
		if(payListDO.getName()==null){
			payListDO.setName("");
		}
		if(contractDO.getAccount()==null){
			contractDO.setAccount("_____________________");
		}
		if(contractDO.getPayee()==null){
			contractDO.setPayee("________________");
		}
		if(contractDO.getBankName()==null){
			contractDO.setBankName("__________");
		}
		String bankPtype = contractDO.getBankName()+" -"+payListDO.getTypeName();
		int print=payListDO.getPrint()+1;
		int month = c.get(Calendar.MONTH)+1;
		payListDO.setPrint(print);
		model.addAttribute("year","  "+c.get(Calendar.YEAR)+"  ");
		model.addAttribute("month","  "+month+"  ");
		model.addAttribute("day","  "+c.get(Calendar.DATE)+"  ");
		model.addAttribute("pl",payListDO);
		model.addAttribute("ct",contractDO);
		model.addAttribute("date",date);
		model.addAttribute("bankPtype",bankPtype);
		return "business/print/contract";
	}

	@GetMapping("/printForChechang/{id}")
	@RequiresPermissions("business:contract:print")
	String printForChechang(@PathVariable("id") Long id, Model model){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		PayListDO payListDO=payListService.getForChechang(id);
		ContractDO contractDO=contractService.get(payListDO.getYewuId());
		contractDO.setContractStartDateString(sdf.format(contractDO.getContractStartDate()));
		contractDO.setContractChechangDateString(sdf.format(contractDO.getContractChechangDate()));
		String date = null;
		date = contractDO.getContractStartDateString()+"-"+contractDO.getContractChechangDateString();
		Date pay=payListDO.getPayDate();
		Calendar c=Calendar.getInstance();
		c.setTime(pay);
		if(payListDO.getName()==null){
			payListDO.setName("");
		}
		if(contractDO.getAccount()==null){
			contractDO.setAccount("_____________________");
		}
		if(contractDO.getPayee()==null){
			contractDO.setPayee("________________");
		}
		if(contractDO.getBankName()==null){
			contractDO.setBankName("__________");
		}
		String bankPtype = contractDO.getBankName()+" -"+payListDO.getTypeName();
		int print=payListDO.getPrint()+1;
		payListDO.setPrint(print);
		int month = c.get(Calendar.MONTH)+1;
		model.addAttribute("year","  "+c.get(Calendar.YEAR)+"  ");
		model.addAttribute("month","  "+month+"  ");
		model.addAttribute("day","  "+c.get(Calendar.DATE)+"  ");
		model.addAttribute("pl",payListDO);
		model.addAttribute("ct",contractDO);
		model.addAttribute("date",date);
		model.addAttribute("bankPtype",bankPtype);
		return "business/print/contractForChechang";
	}
	@GetMapping("/printForChechangBack/{id}")
	@RequiresPermissions("business:contract:print")
	String printForChechangBack(@PathVariable("id") Long id, Model model){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		PayListDO payListDO=payListService.getForChechang(id);
		ContractDO contractDO=contractService.get(payListDO.getYewuId());
		contractDO.setContractStartDateString(sdf.format(contractDO.getContractStartDate()));
		contractDO.setContractChechangDateString(sdf.format(contractDO.getContractChechangDate()));
		String date = null;
		date = contractDO.getContractStartDateString()+"-"+contractDO.getContractChechangDateString();
		Date pay=payListDO.getPayDate();
		Calendar c=Calendar.getInstance();
		c.setTime(pay);
		if(payListDO.getName()==null){
			payListDO.setName("");
		}
		if(contractDO.getAccount()==null){
			contractDO.setAccount("_____________________");
		}
		if(contractDO.getPayee()==null){
			contractDO.setPayee("________________");
		}
		if(contractDO.getBankName()==null){
			contractDO.setBankName("__________");
		}
		String bankPtype = contractDO.getBankName()+" -"+payListDO.getTypeName();
		int print=payListDO.getPrint()+1;
		payListDO.setPrint(print);
		payListDO.setPrice(payListDO.getPrice()*(-1));
		int month = c.get(Calendar.MONTH)+1;
		model.addAttribute("year","  "+c.get(Calendar.YEAR)+"  ");
		model.addAttribute("month","  "+month+"  ");
		model.addAttribute("day","  "+c.get(Calendar.DATE)+"  ");
		model.addAttribute("pl",payListDO);
		model.addAttribute("ct",contractDO);
		model.addAttribute("date",date);
		model.addAttribute("bankPtype",bankPtype);
		return "business/print/contractForChechangBack";
	}
	@ResponseBody
	@GetMapping("/printCount/{id}")
	R printCount(@PathVariable("id") Long id, Model model) {
		PayListDO payListDO = payListService.get(id);
		int print=payListDO.getPrint()+1;
		PayListDO p=new PayListDO();
		p.setId(id);
		p.setPrint(print);
		payListService.update(p);
		return  R.ok();
	}
	@ResponseBody
	@GetMapping("/printCountForChechang/{id}")
	R printCountForChechang(@PathVariable("id") Long id, Model model) {
		PayListDO payListDO = payListService.getForChechang(id);
		int print=payListDO.getPrint()+1;
		PayListDO p=new PayListDO();
		p.setId(id);
		p.setPrint(print);
		payListService.updateForChechang(p);
		return  R.ok();
	}
	/**
	 * 撤场缴费
	 */
	@ResponseBody
	@PostMapping("/payForChechang")
	public ContractDO payForChechang(HttpServletRequest request) throws IOException {
		String jsonStr = request.getParameter("mydata");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		ObjectMapper mapper = new ObjectMapper();
		ContractDO contractDOWeb = mapper.readValue(jsonStr, ContractDO.class);
		PayListDO payListDO = new PayListDO();//用于存储缴费记录
		PayCountDO payCountDO=new PayCountDO();
		AmortizeDO amortizeDO = new AmortizeDO();
		AmortizeDO amortizeDONew = new AmortizeDO();
		ContractDO contractDOData = contractService.get(contractDOWeb.getId());//根据ID获取合同原始数据
		contractDOData.setsType(contractDOWeb.getsType());
		payCountDO.setOid(contractDOData.getOid());
		payCountDO.setCountStartDate(contractDOData.getContractChechangDate());
		payCountDO = payCountService.getNowChechangTotal(payCountDO);//获取最后的缴费周期的数据，用来更新应收金额

		amortizeDO.setOid(contractDOData.getOid());
		amortizeDO.setMonth(sdf.format(contractDOData.getChechangOperationDate()));
		amortizeDONew = contractService.getTanxiaoFenleiPrice(amortizeDO);//获取撤场操作时的摊销，用来更新缴费后的摊销金额
		UserDO userDO=ShiroUtils.getUser();
		DeptDO deptDO=deptService.get(userDO.getDeptId());
		String payId=contractService.getId("SP",ShiroUtils.getUser().getDeptId());
		BusinessBuildingDO buildingDO=businessBuildingService.get(contractDOData.getBuildingId());
		payListDO.setCode(payId);
		payListDO.setYewuId(contractDOData.getId());
		payListDO.setSType(contractDOWeb.getsType());
		payListDO.setSTypeName(contractDOWeb.getsTypeName());
		payListDO.setOid(contractDOData.getOid());
		payListDO.setShopCode(contractDOData.getShopCode());
		payListDO.setCountStartDate(contractDOData.getContractStartDate());
		payListDO.setCountEndDate(contractDOData.getContractChechangDate());
		payListDO.setName(contractDOData.getContractor());
		payListDO.setType(contractDOWeb.getType());
		payListDO.setTypeName(contractDOWeb.getTypeName());
		payListDO.setDeptId(userDO.getDeptId());
		payListDO.setDeptName(userDO.getDeptName());
		payListDO.setReceiptBy(contractDOWeb.getReceiptBy());
		payListDO.setBuildingId(contractDOData.getBuildingId());
		payListDO.setBuildingName(buildingDO.getName());
		payListDO.setType("1");
		payListDO.setPrint(0);
		payListDO.setArea(Long.valueOf(deptDO.getArea()));
		payListDO.setBrand(contractDOData.getBrand());
		payListDO.preInsert();
		String sType=payListDO.getSType();
		PayCountDO p=new PayCountDO();//最终用来更新缴费周期表
		p.setOid(contractDOData.getOid());
		p.setContractCode(contractDOData.getContractCode());
		Double price;
		Double priceDis;
		ContractLog contractLog=new ContractLog();
		if(sType.equals("1")){
			price =contractDOWeb.getFenleiZujinShould();
			if(price>0){
				payListDO.setState("0");
			}else {
				payListDO.setState("3");
			}
			payListDO.setPrice(price);
			p.setCountStartDate(contractDOData.getContractChechangDate());
			p.setFenleiZujinReceived(price);
			priceDis = UploadUtils.format(price - contractDOData.getFenleiZujinUnreceived());
			payCountService.updatePriceChechang(p);//将缴费周期记录的账更新平
			payCountDO.setFenleiZujin(payCountDO.getFenleiZujin()+priceDis);
			payCountDO.setFenleiZujinReceived(payCountDO.getFenleiZujin());
            payCountDO.setFenleiZujinUnreceived(0.00);
			payCountDO.setContractPayTotal(payCountDO.getContractPayTotal() + priceDis);
			payCountDO.setContractPayTotalReceived(payCountDO.getContractPayTotalReceived()+price);
			payCountService.updatePrice(payCountDO);//更新当前缴费周期应收和已收
			contractDOData.setContractPayTotal(contractDOData.getContractPayTotal() + priceDis);
			contractDOData.setContractPayTotalUnreceived(0.00);
			contractDOData.setContractPayTotalReceived(contractDOData.getContractPayTotalReceived()+price);
			contractDOData.setTotal(contractDOData.getTotal()-contractDOData.getFenleiZujinUnreceived());
			contractDOData.setFenleiZujin(contractDOData.getFenleiZujin()+priceDis);
			contractDOData.setFenleiZujinReceived(contractDOData.getFenleiZujinReceived()+price);
			contractDOData.setFenleiZujinUnreceived(0.00);
			amortizeDONew.setAmortizePriceZujin(amortizeDONew.getAmortizePriceZujin()+priceDis);
			amortizeDONew.setTotal(amortizeDONew.getTotal() + priceDis);
			contractService.updateTanxiaoOperationMonth(amortizeDONew);
		}else if(sType.equals("2")){
			price =contractDOWeb.getFenleiWuyeShould();
			if(price>0){
				payListDO.setState("0");
			}else {
				payListDO.setState("3");
			}
			payListDO.setPrice(price);
			p.setCountStartDate(contractDOData.getContractChechangDate());
			p.setFenleiWuyeReceived(price);
			priceDis = UploadUtils.format(price - contractDOData.getFenleiWuyeUnreceived());
			payCountService.updatePriceChechang(p);//将缴费周期记录的账更新平
			payCountDO.setFenleiWuye(payCountDO.getFenleiWuye()+priceDis);
			payCountDO.setFenleiWuyeReceived(payCountDO.getFenleiWuye());
            payCountDO.setFenleiWuyeUnreceived(0.00);
			payCountDO.setContractPayTotal(payCountDO.getContractPayTotal() + priceDis);
			payCountDO.setContractPayTotalReceived(payCountDO.getContractPayTotalReceived()+price);
			payCountService.updatePrice(payCountDO);//更新当前缴费周期应收和已收
			contractDOData.setContractPayTotal(contractDOData.getContractPayTotal() + priceDis);
			contractDOData.setContractPayTotalUnreceived(0.00);
			contractDOData.setContractPayTotalReceived(contractDOData.getContractPayTotalReceived()+price);
			contractDOData.setTotal(contractDOData.getTotal()-contractDOData.getFenleiWuyeUnreceived());
			contractDOData.setFenleiWuye(contractDOData.getFenleiWuye()+priceDis);
			contractDOData.setFenleiWuyeReceived(contractDOData.getFenleiWuyeReceived()+price);
			contractDOData.setFenleiWuyeUnreceived(0.00);
			amortizeDONew.setAmortizePriceWuye(amortizeDONew.getAmortizePriceWuye()+priceDis);
			amortizeDONew.setTotal(amortizeDONew.getTotal() + priceDis);
			contractService.updateTanxiaoOperationMonth(amortizeDONew);
		}else if(sType.equals("3")){
			price =contractDOWeb.getFenleiGuanliShould();
			if(price>0){
				payListDO.setState("0");
			}else {
				payListDO.setState("3");
			}
			payListDO.setPrice(price);
			p.setCountStartDate(contractDOData.getContractChechangDate());
			p.setFenleiGuanliReceived(price);
			priceDis = UploadUtils.format(price - contractDOData.getFenleiGuanliUnreceived());
			payCountService.updatePriceChechang(p);//将缴费周期记录的账更新平
			payCountDO.setFenleiGuanli(payCountDO.getFenleiGuanli()+priceDis);
			payCountDO.setFenleiGuanliReceived(payCountDO.getFenleiGuanli());
            payCountDO.setFenleiGuanliUnreceived(0.00);
			payCountDO.setContractPayTotal(payCountDO.getContractPayTotal() + priceDis);
			payCountDO.setContractPayTotalReceived(payCountDO.getContractPayTotalReceived()+price);
			payCountService.updatePrice(payCountDO);//更新当前缴费周期应收和已收
			contractDOData.setContractPayTotal(contractDOData.getContractPayTotal() + priceDis);
			contractDOData.setContractPayTotalUnreceived(0.00);
			contractDOData.setContractPayTotalReceived(contractDOData.getContractPayTotalReceived()+price);
			contractDOData.setTotal(contractDOData.getTotal()-contractDOData.getFenleiGuanliUnreceived());
			contractDOData.setFenleiGuanli(contractDOData.getFenleiGuanli()+priceDis);
			contractDOData.setFenleiGuanliReceived(contractDOData.getFenleiGuanliReceived()+price);
			contractDOData.setFenleiGuanliUnreceived(0.00);
			amortizeDONew.setAmortizePriceGuanli(amortizeDONew.getAmortizePriceGuanli()+priceDis);
			amortizeDONew.setTotal(amortizeDONew.getTotal() + priceDis);
			contractService.updateTanxiaoOperationMonth(amortizeDONew);
		}else if(sType.equals("4")){
			price =contractDOWeb.getReceivedZhuangxiuIn();
			if(price>0){
				payListDO.setState("0");
			}else {
				payListDO.setState("3");
			}
			payListDO.setPrice(price);
			p.setReceivedZhuangxiu(UploadUtils.format(payCountDO.getReceivedZhuangxiu()+price));
			p.setReceivableZhuangxiu(0.00);
			p.setDiscrepancy(0.00);
			payCountService.updateFourOther(p);
			contractDOData.setTotal(UploadUtils.format(contractDOData.getTotal()-contractDOData.getReceivableZhuangxiu()));
			contractDOData.setReceivedZhuangxiu(UploadUtils.format(contractDOData.getReceivedZhuangxiu()+price));
			contractDOData.setDiscrepancy(UploadUtils.format(contractDOData.getDiscrepancy()+price));
		}else if(sType.equals("5")){
			price =contractDOWeb.getReceivedLvyueIn();
			if(price>0){
				payListDO.setState("0");
			}else {
				payListDO.setState("3");
			}
			payListDO.setPrice(price);
			p.setReceivedLvyue(UploadUtils.format(payCountDO.getReceivedLvyue()+price));
			p.setReceivableLvyue(0.00);
			p.setDiscrepancy(0.00);
			payCountService.updateFourOther(p);
			contractDOData.setTotal(UploadUtils.format(contractDOData.getTotal()-contractDOData.getReceivableLvyue()));
			contractDOData.setReceivedLvyue(UploadUtils.format(contractDOData.getReceivedLvyue()+price));
			contractDOData.setDiscrepancy(UploadUtils.format(contractDOData.getDiscrepancy()+price));
		}else if(sType.equals("6")){
			price =contractDOWeb.getReceivedZhiliangIn();
			if(price>0){
				payListDO.setState("0");
			}else {
				payListDO.setState("3");
			}
			payListDO.setPrice(price);
			p.setReceivedZhiliang(UploadUtils.format(payCountDO.getReceivedZhiliang()+price));
			p.setReceivableZhiliang(0.00);
			p.setDiscrepancy(0.00);
			payCountService.updateFourOther(p);
			contractDOData.setTotal(UploadUtils.format(contractDOData.getTotal()-contractDOData.getReceivableZhiliang()));
			contractDOData.setReceivedZhiliang(UploadUtils.format(contractDOData.getReceivedZhiliang()+price));
			contractDOData.setDiscrepancy(UploadUtils.format(contractDOData.getDiscrepancy()+price));
		}else if(sType.equals("7")){
			price =contractDOWeb.getReceivedFuwuIn();
			if(price>0){
				payListDO.setState("0");
			}else {
				payListDO.setState("3");
			}
			payListDO.setPrice(price);
			p.setReceivedFuwu(UploadUtils.format(payCountDO.getReceivedFuwu()+price));
			p.setReceivableFuwu(0.00);
			p.setDiscrepancy(0.00);
			payCountService.updateFourOther(p);
			contractDOData.setTotal(UploadUtils.format(contractDOData.getTotal()-contractDOData.getReceivableFuwu()));
			contractDOData.setReceivedFuwu(UploadUtils.format(contractDOData.getReceivedFuwu()+price));
			contractDOData.setDiscrepancy(UploadUtils.format(contractDOData.getDiscrepancy()+price));

		}
		contractLog.setName(payListDO.getReceiptBy()+"收"+payListDO.getSTypeName()+""+payListDO.getPrice()+"元");
		contractLog.setContractId(payCountDO.getContractCode());
		contractLog.setOid(payCountDO.getOid());
		contractLog.preInsert();
		payListDO.setRemarks(contractDOWeb.getRemarks());
		payListDO.setPayDate(contractDOWeb.getPayDate());
		payListDO.preInsert();
		payListService.saveForChechang(payListDO);
		contractService.savePayLog(contractLog);
		contractService.updateChechangContract(contractDOData);
		contractDOData.setYewuId(payListDO.getId());
		return contractDOData;
	}

	@ResponseBody
	@PostMapping("/uploadForShoujiaolv")
	R uploadForShoujiaolv(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
		Date now = new Date();
		Double fenleiZujin;
		Double fenleiWuye;
		//把表单内容转换成流
		InputStream fileInputStream = file.getInputStream();
		HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
		HSSFSheet sheetAt = workbook.getSheetAt(0);
		Row nRow = null;
		String sheetName = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		java.text.DecimalFormat   df=new   java.text.DecimalFormat("0.00");
		String orderId= String.valueOf(System.currentTimeMillis());
		//错误
		int repeat = 0;
		//记录第几行重复
		String num;
		StringBuilder sb = new StringBuilder();
		int end = sheetAt.getLastRowNum();
		if(end==1){
			return R.error("请填写合同信息");
		}
		boolean checkContract = false;

		for (int j = 2; j <= end; j++) {
			String contractCodeTemp;
			Double zujin;
			Double wuye;
			Double guanli;

			if (repeat > 0) {
				break;
			}
			List<AmortizeDO> amortizeDOsGet = new ArrayList<>();
			int rowNum = j + 1;
			nRow = sheetAt.getRow(j);
			ContractDO s=new ContractDO();
			PayCountDO p=new PayCountDO();
			if((nRow.getCell(0) == null|| getCellValue(nRow.getCell(0)).equals(""))&&(nRow.getCell(1) == null|| getCellValue(nRow.getCell(1)).equals(""))&&(nRow.getCell(2) == null|| getCellValue(nRow.getCell(2)).equals(""))){
				break;
			}
			int a=nRow.getCell(0).getCellType();
			if (nRow.getCell(0) == null || getCellValue(nRow.getCell(0)).equals("")) {
				sb.append("第" + rowNum + "行没有输入合同编码");
				repeat++;
				break;
			}else{
				contractCodeTemp = getCellValue(nRow.getCell(0));
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(1)))) {
				zujin = Double.valueOf(getCellValue(nRow.getCell(1)));
			} else{
				zujin = 0.00;
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(2)))) {
				wuye = Double.valueOf(getCellValue(nRow.getCell(2)));
			} else{
				wuye = 0.00;
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(3)))) {
				guanli = Double.valueOf(getCellValue(nRow.getCell(3)));
			} else{
				guanli = 0.00;
			}
			amortizeDOsGet = amortizeService.getByCode(contractCodeTemp);
			for(int i=0;i<amortizeDOsGet.size();i++){
				AmortizeDO amortizeDO = new AmortizeDO();
				amortizeDO = amortizeDOsGet.get(i);
				if((zujin+guanli+wuye)==0.00){
					break;
				}
				if(zujin>(amortizeDO.getAmortizePriceZujin()-amortizeDO.getAmortizePriceZujinReceived())){
					zujin = zujin - (amortizeDO.getAmortizePriceZujin()-amortizeDO.getAmortizePriceZujinReceived());
					amortizeDO.setAmortizePriceZujinReceived(amortizeDO.getAmortizePriceZujin());
				}else{
					amortizeDO.setAmortizePriceZujinReceived(amortizeDO.getAmortizePriceZujinReceived()+zujin);
					zujin = 0.00;
				}
				if(guanli>(amortizeDO.getAmortizePriceGuanli()-amortizeDO.getAmortizePriceGuanliReceived())){
					guanli = guanli - (amortizeDO.getAmortizePriceGuanli()-amortizeDO.getAmortizePriceGuanliReceived());
					amortizeDO.setAmortizePriceGuanliReceived(amortizeDO.getAmortizePriceGuanli());
				}else{
					amortizeDO.setAmortizePriceGuanliReceived(amortizeDO.getAmortizePriceGuanliReceived()+guanli);
					guanli = 0.00;
				}
				if(wuye>(amortizeDO.getAmortizePriceWuye()-amortizeDO.getAmortizePriceWuyeReceived())){
					wuye = wuye - (amortizeDO.getAmortizePriceWuye()-amortizeDO.getAmortizePriceWuyeReceived());
					amortizeDO.setAmortizePriceWuyeReceived(amortizeDO.getAmortizePriceWuye());
				}else{
					amortizeDO.setAmortizePriceWuyeReceived(amortizeDO.getAmortizePriceWuyeReceived()+wuye);
					wuye = 0.00;
				}
				amortizeService.updateForAmortize(amortizeDO);
			}
		}
		if (repeat > 0) {
			return R.error(sb.toString());
		}else{
			return R.ok();
		}
	}

	@GetMapping("/shopShouType")
	@ResponseBody
	public List<DictDO> shopShouType() {
		return contractService.shopShouType();
	};
}
