package com.bootdo.report.controller;

import com.bootdo.business.domain.*;
import com.bootdo.business.service.*;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.*;
import com.bootdo.realty.service.RoomService;
import com.bootdo.report.domain.AmortizeReportDO;
import com.bootdo.report.domain.ReportDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.wuye.service.FangbenService;
import com.bootdo.wuye.service.WuyefeiManageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.User;
import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpRequest;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 房间管理
 *
 */

@Controller
@RequestMapping("/report")
public class ReportController extends BaseController {
	private String prefix = "report";
	@Autowired
	private RoomService roomService;
	@Autowired
	private ContractService contractService;
	@Autowired
	private AmortizeService amortizeService;
	@Autowired
	private WuyefeiManageService wuyefeiManageService;
	@Autowired
	private EntrustService entrustService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private PayCountService payCountService;
	@GetMapping("/wuyeAmortize")
	@RequiresPermissions("report:wuyeAmortize")
	String wuyeAmortize(Model model) {
		Calendar calendar=Calendar.getInstance();
		model.addAttribute("year",String.valueOf(calendar.get(Calendar.YEAR)));
		return prefix + "/wuyeAmortize";
	}
	@ApiOperation(value="获取房间列表", notes="")
	@ResponseBody
	@GetMapping("/wuyeAmortizeList")
	public List<AmortizeReportDO> wuyeAmortizeList(@RequestParam Map<String, Object> params) {
		/*//查询列表数据
		UserDO userDO= ShiroUtils.getUser();
        params.put("deptId",userDO.getDeptId());
		String year= (String) params.get("year");
        for(int i=1;i<=12;i++){
            if(i<10){
                params.put("month"+i,year+"-"+"0"+i);
            }else {
                params.put("month"+i,year+"-"+""+i);
            }
        }
		List<AmortizeReportDO> amortizeReportDOs1=new ArrayList<>();
		params.put("a",amortizeReportDOs1);
		List<AmortizeReportDO> amortizeReportDOs = wuyefeiManageService.wuyeAmortizeList(params);
		AmortizeReportDO amortizeReportDO=wuyefeiManageService.getWuyeAmortizeReport(params);
		if(amortizeReportDOs.size()>0&&amortizeReportDO!=null){
			amortizeReportDOs.add(amortizeReportDO);
		}
		return amortizeReportDOs;*/




		//查询列表数据
		UserDO userDO= ShiroUtils.getUser();
		String startDate= (String) params.get("startDate");
		String endDate= (String) params.get("endDate");
		List<ReportDO> reportDOs = new ArrayList<>();
		ReportDO reportDOsin = new ReportDO();
		reportDOsin.setLastMonth(endDate);
		reportDOsin.setBrand((String)params.get("codeRoom"));
		reportDOsin.setBuildingId((String)params.get("buildingId"));
		reportDOsin.setDeptId(userDO.getDeptId());
		int sY = Integer.valueOf(startDate.substring(0,4));
		int sM = Integer.valueOf(startDate.substring(5,7));
		int eY = Integer.valueOf(endDate.substring(0,4));
		int eM = Integer.valueOf(endDate.substring(5,7));
		int s = sY * 12 +sM;
		int e = eY * 12 +eM;
		int a = e - s + 1;
		for(int i=1;i<=a;i++){
			ReportDO reportDO=new ReportDO();
			reportDO.setName(""+i);
			if(sM<10){
				reportDO.setMonth(sY+"-0"+sM);
			}else {
				reportDO.setMonth(sY+"-"+sM);
			}
			sM++;
			if(sM>12){
				sM =1;
				sY++;
			}
			reportDOs.add(reportDO);
		}
		List<AmortizeReportDO> amortizeReportDOs = wuyefeiManageService.wuyeAmortizeList(reportDOs,reportDOsin);
		return amortizeReportDOs;
	}
	@GetMapping("/shopAmortize")
	@RequiresPermissions("report:shopAmortize")
	String shopAmortize(Model model) {
		Calendar calendar=Calendar.getInstance();
		model.addAttribute("year",String.valueOf(calendar.get(Calendar.YEAR)));
		return prefix + "/shopAmortize";
	}
	@ResponseBody
	@GetMapping("/shopAmortizeList")
	public List<AmortizeDO> shopAmortizeList(@RequestParam Map<String, Object> params) {
		//查询列表数据
		UserDO userDO= ShiroUtils.getUser();
		String startDate= (String) params.get("startDate");
		String endDate= (String) params.get("endDate");
		List<ReportDO> reportDOs = new ArrayList<>();
		ReportDO reportDOsin = new ReportDO();
		reportDOsin.setLastMonth(endDate);
		reportDOsin.setBrand((String)params.get("brand"));
		reportDOsin.setBuildingId((String)params.get("buildingId"));
		reportDOsin.setDeptId(userDO.getDeptId());
		int sY = Integer.valueOf(startDate.substring(0,4));
		int sM = Integer.valueOf(startDate.substring(5,7));
		int eY = Integer.valueOf(endDate.substring(0,4));
		int eM = Integer.valueOf(endDate.substring(5,7));
		int s = sY * 12 +sM;
		int e = eY * 12 +eM;
		int a = e - s + 1;
		for(int i=1;i<=a;i++){
			ReportDO reportDO=new ReportDO();
			reportDO.setName(""+i);
			if(sM<10){
				reportDO.setMonth(sY+"-0"+sM);
			}else {
				reportDO.setMonth(sY+"-"+sM);
			}
			sM++;
			if(sM>12){
				sM =1;
				sY++;
			}
			reportDOs.add(reportDO);
		}

		List<AmortizeDO> amortizeDOs = amortizeService.shopAmortizeList(reportDOs,reportDOsin);
		AmortizeDO amortizeDO=amortizeService.getShopAmortizeReport(reportDOs,reportDOsin);
		if(amortizeDOs.size()>0&&amortizeDO!=null){
			amortizeDOs.add(amortizeDO);
		}
		return amortizeDOs;
	}
	@GetMapping("/lease")
	@RequiresPermissions("report:lease")
	String lease(Model model) {
		Calendar calendar=Calendar.getInstance();
		model.addAttribute("year",String.valueOf(calendar.get(Calendar.YEAR)));
		return prefix + "/lease";
	}
	@ResponseBody
	@GetMapping("/leaseList")
	public List<LeasebackDO> leaseList(@RequestParam Map<String, Object> params) {
		//查询列表数据
		UserDO userDO= ShiroUtils.getUser();
		String startDate= (String) params.get("startDate");
		String endDate= (String) params.get("endDate");
		List<ReportDO> reportDOs = new ArrayList<>();
		ReportDO reportDOsin = new ReportDO();
		reportDOsin.setLastMonth(endDate);
		reportDOsin.setBrand((String)params.get("codeRoom"));
		reportDOsin.setBuildingId((String)params.get("buildingId"));
		reportDOsin.setDeptId(userDO.getDeptId());
		int sY = Integer.valueOf(startDate.substring(0,4));
		int sM = Integer.valueOf(startDate.substring(5,7));
		int eY = Integer.valueOf(endDate.substring(0,4));
		int eM = Integer.valueOf(endDate.substring(5,7));
		int s = sY * 12 +sM;
		int e = eY * 12 +eM;
		int a = e - s + 1;
		for(int i=1;i<=a;i++){
			ReportDO reportDO=new ReportDO();
			reportDO.setName(""+i);
			if(sM<10){
				reportDO.setMonth(sY+"-0"+sM);
			}else {
				reportDO.setMonth(sY+"-"+sM);
			}
			sM++;
			if(sM>12){
				sM =1;
				sY++;
			}
			reportDOs.add(reportDO);
		}
		List<LeasebackDO> leasebackDOs = amortizeService.leaseList(reportDOs,reportDOsin);
		/*if(count>0){
			for(int i=0;i<=count/300;i++){
				reportDOsin.setOffset(i*300);
				reportDOsin.setLimit(300);
				List<LeasebackDO> leasebackDOs1 = new ArrayList<>();
				leasebackDOs1 = amortizeService.leaseList(reportDOs,reportDOsin);
				leasebackDOs.addAll(leasebackDOs1);
			}
		}*/
		/*AmortizeDO amortizeDO=amortizeService.getShopAmortizeReport(reportDOs,reportDOsin);
		if(amortizeDOs.size()>0&&amortizeDO!=null){
			amortizeDOs.add(amortizeDO);
		}*/
		return leasebackDOs;
	}
	@GetMapping("/shopShoujiaolv")
	@RequiresPermissions("report:shopShoujiaolv")
	String shopShoujiaolv(Model model) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		model.addAttribute("findDate",sdf.format(date));
		return prefix + "/shopShoujiaolv";
	}
	@ResponseBody
	@GetMapping("/shopShoujiaolvList")
	public List<AmortizeDO> shopShoujiaolvList(@RequestParam Map<String, Object> params) {
		//查询列表数据
		UserDO userDO= ShiroUtils.getUser();
		Long buildingId=null;
		String findDate= (String) params.get("findDate");
		String brand = (String) params.get("brand");
		String contractor = (String) params.get("contractor");
		if((String) params.get("buildingId") != null){
			buildingId = Long.valueOf((String) params.get("buildingId"));
		}
		List<AmortizeDO> amortizeDOs = new ArrayList<>();
		AmortizeDO amortizeDO = new AmortizeDO();
		amortizeDO.setDeptId(userDO.getDeptId());
		amortizeDO.setFindDate(findDate);
		amortizeDO.setBrand(brand);
		amortizeDO.setContractor(contractor);
		amortizeDO.setBuildingId(buildingId);
		amortizeDOs = amortizeService.getForShoujiaolv(amortizeDO);
		AmortizeDO amortizeDO1 = amortizeService.getForShoujiaolvTotal(amortizeDO);
		amortizeDOs.add(amortizeDO1);
		if(amortizeDOs!=null){
			for(int i=0;i<amortizeDOs.size();i++){
				amortizeDOs.get(i).setPercentString(""+ UploadUtils.format(amortizeDOs.get(i).getPercent()*100)+"%");
			}
		}
		return amortizeDOs;
	}
	@ResponseBody
	@GetMapping("/activityReportList")
	public PageUtils activityReport(@RequestParam Map<String, Object> params){
		//查询列表数据
		UserDO userDO=ShiroUtils.getUser();
		params.put("deptId",userDO.getDeptId());
		Query query = new Query(params);
		List<ActivityGeneralDO> activityList = activityService.list(query);
		int total = activityService.count(query);
		PageUtils pageUtils = new PageUtils(activityList, total);
		return pageUtils;
	}

	@GetMapping("/activityDetailReport")
	String activityDetailReport(){
		return prefix + "/activityDetailReport";
	}


	@ResponseBody
	@GetMapping("/activityDetailReportList")
	public List<ActivityDO> activityDetailReportList(@RequestParam Map<String, Object> params){
		//查询列表数据
		UserDO userDO=ShiroUtils.getUser();
		params.put("deptId",userDO.getDeptId());
		List<ActivityDO> activityList = activityService.listDetail(params);
		return activityList;
	}

	@GetMapping("/activityReport")
	@RequiresPermissions("report:activityReport")
	String activityReport(){
		return prefix + "/activityReport";
	}

	@GetMapping("/shopContract")
	@RequiresPermissions("report:shopContract")
	String shopContract(Model model) {
		Calendar calendar=Calendar.getInstance();
		model.addAttribute("year",String.valueOf(calendar.get(Calendar.YEAR)));
		return prefix + "/shopContract";
	}
	@ResponseBody
	@GetMapping("/shopContractList")
	public List<PayCountDO> shopContractList(@RequestParam Map<String, Object> params) {
		//查询列表数据
		UserDO userDO= ShiroUtils.getUser();
		String startDate= (String) params.get("startDate");
		String endDate= (String) params.get("endDate");
		List<ReportDO> reportDOs = new ArrayList<>();
		ReportDO reportDOsin = new ReportDO();
		reportDOsin.setLastMonth(endDate);
		reportDOsin.setBrand((String)params.get("brand"));
		reportDOsin.setBuildingId((String)params.get("buildingId"));
		reportDOsin.setContractor((String)params.get("contractor"));
		reportDOsin.setDeptId(userDO.getDeptId());
		reportDOsin.setStartDate(startDate);
		reportDOsin.setEndDate(endDate);
		int sY = Integer.valueOf(startDate.substring(0,4));
		int sM = Integer.valueOf(startDate.substring(5,7));
		int eY = Integer.valueOf(endDate.substring(0,4));
		int eM = Integer.valueOf(endDate.substring(5,7));
		int s = sY * 12 +sM;
		int e = eY * 12 +eM;
		int a = e - s + 1;
		for(int i=1;i<=a;i++){
			ReportDO reportDO=new ReportDO();
			reportDO.setName(""+i);
			if(sM<10){
				reportDO.setMonth(sY+"-0"+sM);
			}else {
				reportDO.setMonth(sY+"-"+sM);
			}
			sM++;
			if(sM>12){
				sM =1;
				sY++;
			}
			reportDOs.add(reportDO);
		}

		List<PayCountDO> payCountDOList = payCountService.shopContractList(reportDOs,reportDOsin);
		PayCountDO payCountDO=payCountService.getShopContractReport(reportDOs,reportDOsin);
		if(payCountDOList.size()>0&&payCountDO!=null){
			payCountDOList.add(payCountDO);
		}
		return payCountDOList;
	}

	@GetMapping("/shopContractType")
	@RequiresPermissions("report:shopContractType")
	String shopContractType(Model model) {
		Calendar calendar=Calendar.getInstance();
		model.addAttribute("year",String.valueOf(calendar.get(Calendar.YEAR)));
		return prefix + "/shopContractType";
	}
	@ResponseBody
	@GetMapping("/shopContractTypeList")
	public List<PayCountDO> shopContractTypeList(@RequestParam Map<String, Object> params) {
		//查询列表数据
		UserDO userDO= ShiroUtils.getUser();
		String startDate= (String) params.get("startDate");
		String endDate= (String) params.get("endDate");
		List<ReportDO> reportDOs = new ArrayList<>();
		ReportDO reportDOsin = new ReportDO();
		reportDOsin.setLastMonth(endDate);
		reportDOsin.setBrand((String)params.get("brand"));
		reportDOsin.setBuildingId((String)params.get("buildingId"));
		reportDOsin.setDeptId(userDO.getDeptId());
		reportDOsin.setStartDate(startDate);
		reportDOsin.setEndDate(endDate);
		int sY = Integer.valueOf(startDate.substring(0,4));
		int sM = Integer.valueOf(startDate.substring(5,7));
		int eY = Integer.valueOf(endDate.substring(0,4));
		int eM = Integer.valueOf(endDate.substring(5,7));
		int s = sY * 12 +sM;
		int e = eY * 12 +eM;
		int a = e - s + 1;
		for(int i=1;i<=a;i++){
			ReportDO reportDO=new ReportDO();
			reportDO.setName(""+i);
			if(sM<10){
				reportDO.setMonth(sY+"-0"+sM);
			}else {
				reportDO.setMonth(sY+"-"+sM);
			}
			sM++;
			if(sM>12){
				sM =1;
				sY++;
			}
			reportDOs.add(reportDO);
		}

		List<PayCountDO> payCountDOList = payCountService.shopContractTypeList(reportDOs,reportDOsin);
		PayCountDO payCountDO=payCountService.getShopContractTypeReport(reportDOs,reportDOsin);
		if(payCountDOList.size()>0&&payCountDO!=null){
			payCountDOList.add(payCountDO);
		}
		return payCountDOList;
	}
	@GetMapping("/shopContractTaizhang")
	@RequiresPermissions("report:shopContractTaizhang")
	String shopContractTaizhang(Model model) {
		Calendar calendar=Calendar.getInstance();
		model.addAttribute("year",String.valueOf(calendar.get(Calendar.YEAR)));
		return prefix + "/shopContractTaizhang";
	}
	@ResponseBody
	@GetMapping("/shopContractTaizhangList")
	@RequiresPermissions("report:shopContractTaizhang")
	public List<ContractDO> shopContractTaizhangList(@RequestParam Map<String, Object> params){
		UserDO userDO=ShiroUtils.getUser();
		params.put("deptId",userDO.getDeptId());
		//查询列表数据
		List<ContractDO> contractList = contractService.taizhangList(params);
		return contractList;
	}
	@ResponseBody
	@GetMapping("/getNumForType")
	public List<ReportDO> getNumForType(@RequestParam Map<String, Object> params){
		Map<String, Object> query = new HashMap<>(16);
		UserDO userDO = ShiroUtils.getUser();
		List<ReportDO> num = contractService.getNumForType(userDO.getDeptId());
		return num;
	}
	@ResponseBody
	@GetMapping("/getNumForAreaA")
	public List<ReportDO> getNumForAreaA(@RequestParam Map<String, Object> params){
		Map<String, Object> query = new HashMap<>(16);
		UserDO userDO = ShiroUtils.getUser();
		List<ReportDO> num = contractService.getNumForAreaA(userDO.getDeptId());
		return num;
	}
	@ResponseBody
	@GetMapping("/getNumForAreaB")
	public List<ReportDO> getNumForAreaB(@RequestParam Map<String, Object> params){
		Map<String, Object> query = new HashMap<>(16);
		UserDO userDO = ShiroUtils.getUser();
		List<ReportDO> num = contractService.getNumForAreaB(userDO.getDeptId());
		return num;
	}
	@GetMapping("/shopContractPaymentRate")
	@RequiresPermissions("report:shopContractPaymentRate")
	String shopContractPaymentRate(Model model) {
		Calendar calendar=Calendar.getInstance();
		model.addAttribute("year",String.valueOf(calendar.get(Calendar.YEAR)));
		return prefix + "/shopContractPaymentRate";
	}
	@ResponseBody
	@GetMapping("/shopContractPaymentRateList")
	@RequiresPermissions("report:shopContractPaymentRate")
	public List<ContractDO> shopContractPaymentRateList(@RequestParam Map<String, Object> params){
		UserDO userDO=ShiroUtils.getUser();
		params.put("deptId",userDO.getDeptId());
		//查询列表数据
		List<ContractDO> contractList = contractService.taizhangList(params);
		return contractList;
	}
	@ResponseBody
	@GetMapping("/getNumForShoujiaolv")
	public List<ReportDO> getNumForShoujiaolv(@RequestParam Map<String, Object> params){
		Map<String, Object> query = new HashMap<>(16);
		UserDO userDO = ShiroUtils.getUser();
		List<ReportDO> num = contractService.getNumForShoujiaolv(userDO.getDeptId());
		return num;
	}
	@ResponseBody
	@GetMapping("/getNumForContractEnd")
	public List<PayCountDO> getNumForContractEnd(@RequestParam Map<String, Object> params) {
		//查询列表数据
		UserDO userDO= ShiroUtils.getUser();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String dateString= sdf.format(date);
		List<ReportDO> reportDOs = new ArrayList<>();
		ReportDO reportDOsin = new ReportDO();
		reportDOsin.setDeptId(userDO.getDeptId());
		int sY = Integer.valueOf(dateString.substring(0,4));
		for(int i=1;i<=12;i++){
			ReportDO reportDO=new ReportDO();
			reportDO.setName(""+i);
			if(i<10){
				reportDO.setMonth(sY+"-0"+i);
			}else {
				reportDO.setMonth(sY+"-"+i);
			}
			reportDOs.add(reportDO);
		}
		List<PayCountDO> payCountDOList = payCountService.getNumForContractEnd(reportDOs,reportDOsin);
		return payCountDOList;
	}
	@ResponseBody
	@GetMapping("/getNumForAmortizeMonth")
	public List<PayCountDO> getNumForAmortizeMonth(@RequestParam Map<String, Object> params) {
		//查询列表数据
		UserDO userDO= ShiroUtils.getUser();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String dateString= sdf.format(date);
		List<ReportDO> reportDOs = new ArrayList<>();
		ReportDO reportDOsin = new ReportDO();
		reportDOsin.setDeptId(userDO.getDeptId());
		int sY = Integer.valueOf(dateString.substring(0,4));
		for(int i=1;i<=12;i++){
			ReportDO reportDO=new ReportDO();
			reportDO.setName(""+i);
			if(i<10){
				reportDO.setMonth(sY+"-0"+i);
			}else {
				reportDO.setMonth(sY+"-"+i);
			}
			reportDOs.add(reportDO);
		}
		List<PayCountDO> payCountDOList = payCountService.getNumForAmortizeMonth(reportDOs,reportDOsin);
		return payCountDOList;
	}
}
