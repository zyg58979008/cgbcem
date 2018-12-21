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

import com.bootdo.business.domain.ActivityGeneralDO;
import com.bootdo.business.domain.ActivityPayListDO;
import com.bootdo.business.service.ActivityPayListService;
import com.bootdo.business.service.ContractService;
import com.bootdo.common.utils.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.business.domain.ActivityDO;
import com.bootdo.business.service.ActivityService;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 活动管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-09 15:52:38
 */
 
@Controller
@RequestMapping("/business/activity")
public class ActivityController {
	@Autowired
	private ActivityService activityService;
	@Autowired
	private ActivityPayListService activityPayListService;
	@Autowired
	private DeptService deptService;
	@Resource
	private ResourceLoader resourceLoader;
	@Autowired
	private ContractService contractService;
	@GetMapping()
	@RequiresPermissions("business:activity:activity")
	String Activity(){
	    return "business/activity/activity";
	}
	@GetMapping("/activityPay")
	@RequiresPermissions("business:activityPay:activityPay")
	String ActivityPay(){
		return "business/activityPay/activityPay";
	}

	@GetMapping("/activityDetail")
	String activityDetail(){
		return "business/activity/activityDetail";
	}


	@GetMapping("/activityDetailPay")
	String activityDetailPay(){
		return "business/activityPay/activityDetailPay";
	}

	@ResponseBody
	@GetMapping("/listDetail")
	public PageUtils listDetail(@RequestParam Map<String, Object> params){
		//查询列表数据
		UserDO userDO=ShiroUtils.getUser();
		params.put("deptId",userDO.getDeptId());
        Query query = new Query(params);
		List<ActivityDO> activityList = activityService.listDetail(query);
		int total = activityService.countDetail(query);
		PageUtils pageUtils = new PageUtils(activityList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/listDetailPay")
	public List<ActivityDO> listDetailPay(@RequestParam Map<String, Object> params){
		//查询列表数据
		UserDO userDO=ShiroUtils.getUser();
		params.put("deptId",userDO.getDeptId());
		List<ActivityDO> activityList = activityService.listDetail(params);
		ActivityDO activityDO = activityService.getTotal(params);
		activityList.add(activityDO);
		return activityList;
	}

	@GetMapping("/detailPay/{id}")
	@RequiresPermissions("business:activity:detailPay")
	String detailPay(@PathVariable("id") Long id,Model model){
		Date date=new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		String d=sdf.format(date);
		model.addAttribute("date", d);
		model.addAttribute("name", ShiroUtils.getUser().getName());
		ActivityDO activityDO = activityService.getDetail(id);
		model.addAttribute("ActivityDO", activityDO);
		return "business/activityPay/shou";
	}

	/**
	 * 缴费
	 */
	@ResponseBody
	@PostMapping("/savepay")
	@RequiresPermissions("business:activity:detailPay")
	public ActivityPayListDO savepay(HttpServletRequest request) throws IOException {
		String jsonStr = request.getParameter("mydata");
		ObjectMapper mapper = new ObjectMapper();
		ActivityPayListDO activityPayListDO = mapper.readValue(jsonStr, ActivityPayListDO.class);
		ActivityDO activityDO = activityService.getDetail(activityPayListDO.getYewuId());
		UserDO userDO=ShiroUtils.getUser();
		DeptDO deptDO=deptService.get(userDO.getDeptId());
		String payId=activityService.getId("HD",userDO.getDeptId());
		activityPayListDO.setCode(payId);
		activityPayListDO.setBuildingId(activityDO.getBuildingId());
		activityPayListDO.setActivityName(activityDO.getActivityName());
		activityPayListDO.setShopCode(activityDO.getShopCode());
		activityPayListDO.setName(activityDO.getContractor());
		activityPayListDO.setDeptId(userDO.getDeptId());
		activityPayListDO.setDeptName(userDO.getDeptName());
		activityPayListDO.setState("0");
		activityPayListDO.setPrint(0);
		activityPayListDO.setArea(Long.valueOf(deptDO.getArea()));
		activityPayListDO.setBrand(activityDO.getBrand());
		String sType=activityPayListDO.getSType();
		Double price;
		switch(sType){
			case "1":
				price = activityPayListDO.getActivityMoneyPrice();
				activityPayListDO.setPrice(price);
				activityDO.setUnreceived(activityDO.getUnreceived()-price);
				activityDO.setActivityMoneyReceived(activityDO.getActivityMoneyReceived()+price);
				activityDO.setActivityMoneyUnreceived(activityDO.getActivityMoneyUnreceived()-price);
				break;
			case "2":
				price = activityPayListDO.getDmDanPrice();
				activityPayListDO.setPrice(price);
				activityDO.setUnreceived(activityDO.getUnreceived()-price);
				activityDO.setDmDanReceived(activityDO.getDmDanReceived()+price);
				activityDO.setDmDanUnreceived(activityDO.getDmDanUnreceived()-price);
				break;
			case "3":
				price = activityPayListDO.getCardMoneyPrice();
				activityPayListDO.setPrice(price);
				activityDO.setUnreceived(activityDO.getUnreceived()-price);
				activityDO.setCardMoneyUnreceived(activityDO.getCardMoneyUnreceived()-price);
				activityDO.setCardMoneyReceived(activityDO.getCardMoneyReceived()+price);
				break;
		}
		activityPayListDO.preInsert();
		activityService.saveList(activityPayListDO);
		activityService.updateDetail(activityDO);
		return activityPayListDO;
	}
	@PostMapping("/chongxiao")
	@ResponseBody
	public R chongxiao(Long id){
		ActivityPayListDO activityPayListDO=activityPayListService.get(id);
		activityPayListDO.setState("2");
		activityPayListService.update(activityPayListDO);
		ActivityDO activityDO = activityService.get(activityPayListDO.getYewuId());
		Double price=activityPayListDO.getPrice();
		activityPayListDO.setPrice(-price);
		activityPayListDO.setState("1");
		activityPayListDO.setChongxiaoCode(activityPayListDO.getCode());
		String payId=activityService.getId("HD",ShiroUtils.getUser().getDeptId());
		activityPayListDO.setCode(payId);
		activityPayListDO.preInsert();
		String sType=activityPayListDO.getSType();
		switch(sType){
			case "1":
				activityDO.setUnreceived(activityDO.getUnreceived()+price);
				activityDO.setActivityMoneyReceived(activityDO.getActivityMoneyReceived()-price);
				activityDO.setActivityMoneyUnreceived(activityDO.getActivityMoneyUnreceived()+price);
				break;
			case "2":
				activityDO.setUnreceived(activityDO.getUnreceived()+price);
				activityDO.setDmDanReceived(activityDO.getDmDanReceived()-price);
				activityDO.setDmDanUnreceived(activityDO.getDmDanUnreceived()+price);
				break;
			case "3":
				activityDO.setUnreceived(activityDO.getUnreceived()+price);
				activityDO.setCardMoneyUnreceived(activityDO.getCardMoneyUnreceived()+price);
				activityDO.setCardMoneyReceived(activityDO.getCardMoneyReceived()-price);
				break;
		}
		activityService.saveList(activityPayListDO);
		if(activityService.updateDetail(activityDO)>0){
			return R.ok();
		}
		return R.error();
	}
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:activity:activity")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		UserDO userDO=ShiroUtils.getUser();
		params.put("deptId",userDO.getDeptId());
		Query query = new Query(params);
		List<ActivityGeneralDO> activityList = activityService.list(query);
		int total = activityService.count(query);
		PageUtils pageUtils = new PageUtils(activityList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("business:activity:add")
	String add(){
	    return "business/activity/add";
	}

	@GetMapping("/editGeneral/{id}")
	@RequiresPermissions("business:activity:editGeneral")
	String editGeneral(@PathVariable("id") Long id,Model model){
		ActivityGeneralDO activityGeneralDO = activityService.getGeneral(id);
		model.addAttribute("activityGeneralDO", activityGeneralDO);
	    return "business/activity/editGeneral";
	}

	@GetMapping("/adnew/{id}")
	@RequiresPermissions("business:activity:adnew")
	String adnew(@PathVariable("id") Long id,Model model){
		ActivityGeneralDO activityGeneralDO = activityService.getGeneral(id);
		model.addAttribute("activityGeneralDO", activityGeneralDO);
		return "business/activity/adnew";
	}
	@ResponseBody
	@PostMapping("/savenew")
	@RequiresPermissions("business:activity:adnew")
	public R savenew( ActivityDO activity){
		UserDO userDO= ShiroUtils.getUser();
		Long deptId=userDO.getDeptId();
		/*List<String> shops=contractService.getShop(deptId);//查询当前项目下所有商铺
		boolean isContains = UploadUtils.checkHasCode(activity.getShopCode(),shops);
		if(!isContains){
			return R.error("不存在该商铺");
		}
		List<String> shopcode=activityService.getShop(activity.getOrderId());//查询当前活动下所有参加商铺
		boolean isContainscode = UploadUtils.checkHasCode(activity.getShopCode(),shopcode);
		if(isContainscode){
			return R.error("该商铺已经在此次活动内");
		}*/
		if(activityService.savenew(activity)>0){
			return R.ok();
		}
		return R.error();
	}
	@ResponseBody
	@RequestMapping("/updatedetail")
	@RequiresPermissions("business:activity:adnew")
	public R updatedetail( ActivityDO activityDO){
		//activityService.updatedetail(activityDO);
		return R.ok();
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("business:activity:add")
	public R save( ActivityDO activity){
		if(activityService.save(activity)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/updateGeneral")
	@RequiresPermissions("business:activity:editGeneral")
	public R update( ActivityGeneralDO activityGeneralDO){
		activityService.updateGeneral(activityGeneralDO);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("business:activity:remove")
	public R remove( Long id){
		if(activityService.removeGeneral(id)>0){
		return R.ok();
		}
		return R.error();
	}
	/**
	 * 删除
	 */
	@PostMapping( "/removeDetail")
	@ResponseBody
	@RequiresPermissions("business:activity:removeDetail")
	public R removeDetail( Long id){
		if(activityService.removeDetail(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("business:activity:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		activityService.batchRemove(ids);
		return R.ok();
	}
	@GetMapping("/info")
	String info(){
		return "business/activity/info";
	}
	@ResponseBody
	@RequiresPermissions("business:activity:download")
	@PostMapping("/download")
	public void downloadTemplate(HttpServletResponse response, HttpServletRequest request) {
		InputStream inputStream = null;
		ServletOutputStream servletOutputStream = null;
		try {
			String filename = "活动登记.xls";
			String path = "static/file/activity.xls";
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
	@RequiresPermissions("business:activity:upload")
	@PostMapping("/upload")
	R upload(@RequestParam("file") MultipartFile file, @RequestParam("activityName")String Parm1,@RequestParam("startDate") Date  startDate,@RequestParam("endDate") Date endDate) throws IOException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//把表单内容转换成流
		InputStream fileInputStream = file.getInputStream();
		HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
		HSSFSheet sheetAt = workbook.getSheetAt(0);
		Row nRow = null;
		String sheetName = null;
		String orderId= String.valueOf(System.currentTimeMillis());
		Map<String, Object> map = new HashMap<>(16);
		//错误
		int repeat = 0;
		//记录第几行重复
		String num;
		StringBuilder sb = new StringBuilder();
		int end = sheetAt.getLastRowNum();
		if(end==0){
			return R.error("请填写活动信息");
		}
		UserDO userDO= ShiroUtils.getUser();
		Long deptId=userDO.getDeptId();
		List<String> shops=contractService.getShop(deptId);//查询当前项目下所有商铺
		List<ActivityDO> activityDOArrayList=new ArrayList<>();
		ActivityGeneralDO activityGeneralDO = new ActivityGeneralDO();
		for (int j = 1; j <= end; j++) {
			if (repeat > 0) {
				break;
			}
			int rowNum = j + 1;
			nRow = sheetAt.getRow(j);
			ActivityDO ac=new ActivityDO();
			if((nRow.getCell(0) == null|| getCellValue(nRow.getCell(0)).equals(""))&&(nRow.getCell(1) == null|| getCellValue(nRow.getCell(1)).equals(""))&&(nRow.getCell(2) == null|| getCellValue(nRow.getCell(2)).equals(""))){
				break;
			}
			int a=nRow.getCell(0).getCellType();
			/*if (nRow.getCell(0) == null || getCellValue(nRow.getCell(0)).equals("")) {
				sb.append("第" + rowNum + "行没有输入商铺编码");
				repeat++;
				break;
			}else{
				ac.setShopCode(getCellValue(nRow.getCell(0)));
			}
			boolean isContains = UploadUtils.checkHasCode(ac.getShopCode(),shops);
			if(!isContains){
				sb.append("第" + rowNum + "行不存在该商铺");
				repeat++;
				break;
			}*/
			if (nRow.getCell(0) == null || getCellValue(nRow.getCell(0)).equals("")) {
				sb.append("第" + rowNum + "行没有输入签约人");
				repeat++;
				break;
			}else{
				ac.setContractor(getCellValue(nRow.getCell(0)));
			}
			if (nRow.getCell(1) == null || getCellValue(nRow.getCell(1)).equals("")) {
				sb.append("第" + rowNum + "行没有输入品牌");
				repeat++;
				break;
			}else{
				ac.setBrand(getCellValue(nRow.getCell(1)));
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(2)))) {
				ac.setShopType(getCellValue(nRow.getCell(2)));
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(3)))) {
				ac.setActivityMoney(Double.valueOf(getCellValue(nRow.getCell(3))));
			}else{
				ac.setActivityMoney(0.00);
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(4)))) {
				ac.setDmDan(Double.valueOf(getCellValue(nRow.getCell(4))));
			}else{
				ac.setDmDan(0.00);
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(5)))) {
				ac.setCardMoney(Double.valueOf(getCellValue(nRow.getCell(5))));
			}else{
				ac.setCardMoney(0.00);
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(6)))) {
				ac.setActivityMoneyReceived(Double.valueOf(getCellValue(nRow.getCell(6))));
			}else{
				ac.setActivityMoneyReceived(0.00);
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(7)))) {
				ac.setDmDanReceived(Double.valueOf(getCellValue(nRow.getCell(7))));
			}else{
				ac.setDmDanReceived(0.00);
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(8)))) {
				ac.setCardMoneyReceived(Double.valueOf(getCellValue(nRow.getCell(8))));
			}else{
				ac.setCardMoneyReceived(0.00);
			}
			ac.setDmDanUnreceived(ac.getDmDan()-ac.getDmDanReceived());
			ac.setActivityMoneyUnreceived(ac.getActivityMoney()-ac.getActivityMoneyReceived());
			ac.setCardMoneyUnreceived(ac.getCardMoney()-ac.getCardMoneyReceived());
			ac.setActivityName(Parm1);
			ac.setStartDate(startDate);
			ac.setEndDate(endDate);
			ac.setOrderId(orderId);
			ac.setUnreceived(ac.getActivityMoneyUnreceived()+ac.getCardMoneyUnreceived()+ac.getDmDanUnreceived());
			ac.preInsert();
			activityDOArrayList.add(ac);
		}
		if (repeat > 0) {
			return R.error(sb.toString());
		} else {
			if(activityDOArrayList.size()>0){
				activityGeneralDO.setOrderId(orderId);
				activityGeneralDO.setActivityName(Parm1);
				activityGeneralDO.setStartDate(startDate);
				activityGeneralDO.setEndDate(endDate);
				activityGeneralDO.preInsert();
				int i=activityService.batchInsert(activityDOArrayList);
				activityService.saveGeneral(activityGeneralDO);
				if(i>0){
					return R.ok("导入"+i+"条数据");
				}else{
					R.error();
				}
			}
		}
		return R.error();
	}
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
	@GetMapping("/activityPayIndex")
	@RequiresPermissions("business:activityPay:activityPayIndex")
	String activityPayIndex(){
		return "business/pay/activityPayIndex";
	}
	@GetMapping("/payListView")
	@ResponseBody
	public PageUtils payListView(@RequestParam Map<String, Object> params){
		//查询列表数据
		UserDO userDO=ShiroUtils.getUser();
		params.put("deptId",userDO.getDeptId());
		Query query = new Query(params);
		List<ActivityPayListDO> activityPayListList = activityPayListService.list(query);
		int total = activityPayListService.count(query);
		PageUtils pageUtils = new PageUtils(activityPayListList, total);
		return pageUtils;
	}
	@GetMapping("/print/{id}")
	@RequiresPermissions("business:activity:print")
	String print(@PathVariable("id") Long id, Model model){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		ActivityPayListDO activityPayListDO=activityPayListService.get(id);
		ActivityDO activityDO=activityService.get(activityPayListDO.getYewuId());
		activityDO.setStartDateString(sdf.format(activityDO.getStartDate()));
		activityDO.setEndDateString(sdf.format(activityDO.getEndDate()));
		String date = null;
		date = activityDO.getStartDateString()+"-"+activityDO.getEndDateString();
		Date pay=activityPayListDO.getPayDate();
		Calendar c=Calendar.getInstance();
		c.setTime(pay);
		int print=activityPayListDO.getPrint()+1;
		activityPayListDO.setPrint(print);
		int month = c.get(Calendar.MONTH)+1;
		model.addAttribute("year","  "+c.get(Calendar.YEAR)+"  ");
		model.addAttribute("month","  "+month+"  ");
		model.addAttribute("day","  "+c.get(Calendar.DATE)+"  ");
		model.addAttribute("acl",activityPayListDO);
		model.addAttribute("ac",activityDO);
		model.addAttribute("date",date);
		return "business/print/activity";
	}
	@ResponseBody
	@GetMapping("/printCount/{id}")
	R printCount(@PathVariable("id") Long id, Model model) {
		ActivityPayListDO activityPayListDO = activityPayListService.get(id);
		int print=activityPayListDO.getPrint()+1;
		ActivityPayListDO a=new ActivityPayListDO();
		a.setId(id);
		a.setPrint(print);
		activityPayListService.update(a);
		return  R.ok();
	}
}
