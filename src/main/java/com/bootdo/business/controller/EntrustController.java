package com.bootdo.business.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.bootdo.business.domain.EntrustDO;
import com.bootdo.business.domain.LeasebackDO;
import com.bootdo.business.service.EntrustService;
import com.bootdo.common.domain.PayListDO;
import com.bootdo.common.utils.*;
import com.bootdo.realty.domain.BuildingDO;
import com.bootdo.realty.domain.RoomContractDO;
import com.bootdo.realty.domain.RoomDO;
import com.bootdo.realty.service.BuildingService;
import com.bootdo.realty.service.RoomContractService;
import com.bootdo.realty.service.RoomService;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.DeptService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.map.HashedMap;
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

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-27 15:53:01
 */
 
@Controller
@RequestMapping("/business/entrust")
public class EntrustController {

	@Autowired
	private EntrustService entrustService;
	@Autowired
	private RoomContractService contractService;
	@Resource
	private ResourceLoader resourceLoader;
    @Autowired
    private RoomService roomService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private BuildingService buildingService;
	@GetMapping()
	@RequiresPermissions("business:entrust:entrust")
	String Entrust(){
	    return "business/entrust/entrust";
	}
	@GetMapping("/pay")
	@RequiresPermissions("business:entrust:entrustPay")
	String pay(){
		return "business/entrust/entrustPay";
	}
	@GetMapping("/payIndex")
	@RequiresPermissions("business:entrust:payIndex")
	String payIndex(){
		return "business/entrust/fanzuPay";
	}
	@GetMapping("/print/{id}")
	String print(@PathVariable("id") Long id, Model model){
		PayListDO payListDO=entrustService.getPay(id);
		LeasebackDO leasebackDO=entrustService.getLease(payListDO.getYewuId());
		Date pay=payListDO.getPayDate();
		Calendar c=Calendar.getInstance();
		c.setTime(pay);
		if(payListDO.getName()==null){
			payListDO.setName("");
		}
		int print=payListDO.getPrint()+1;
		payListDO.setPrint(print);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		int month = c.get(Calendar.MONTH)+1;
		model.addAttribute("year","  "+c.get(Calendar.YEAR)+"  ");
		model.addAttribute("month","  "+month+"  ");
		model.addAttribute("day","  "+c.get(Calendar.DATE)+"  ");
		model.addAttribute("m",sdf.format(leasebackDO.getMonth()));
		model.addAttribute("p",payListDO);
		return "business/entrust/fanzuPrint";
	}
	@ResponseBody
	@GetMapping("/printCount/{id}")
	R printCount(@PathVariable("id") Long id, Model model) {
		PayListDO payListDO = entrustService.getPay(id);
		int print=payListDO.getPrint()+1;
		PayListDO p=new PayListDO();
		p.setId(id);
		p.setPrint(print);
		entrustService.updatePay(p);
		return  R.ok();
	}
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:entrust:entrust")
	public PageUtils list(@RequestParam Map<String, Object> params){
		params.put("deptId",ShiroUtils.getUser().getDeptId());
		params.put("delFlag","0");
		if(params.get("year")!=null&&!params.get("year").toString().equals("")){
			String startYear=params.get("year").toString().split("-")[0].trim();
			String endYear=params.get("year").toString().split("-")[1].trim();
			params.put("startYear",startYear);
			params.put("endYear",endYear);
		}
		//查询列表数据
        Query query = new Query(params);
		List<EntrustDO> entrustList = entrustService.list(query);
		int total = entrustService.count(query);
		PageUtils pageUtils = new PageUtils(entrustList, total);
		return pageUtils;
	}
	@GetMapping("/payList")
	@ResponseBody
	public PageUtils payList(@RequestParam Map<String, Object> params) {
		UserDO userDO = ShiroUtils.getUser();
		params.put("deptId", userDO.getDeptId());
		//查询列表数据
		Query query = new Query(params);
		List<PayListDO> payListDOs = entrustService.pay(query);
		int total = entrustService.countPayList(query);
		PageUtils pageUtils = new PageUtils(payListDOs, total);
		return pageUtils;
	}
	@GetMapping("/add")
	@RequiresPermissions("business:entrust:add")
	String add(){
	    return "business/entrust/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("business:entrust:edit")
	String edit(@PathVariable("id") Long id,Model model){
		EntrustDO entrust = entrustService.get(id);
		model.addAttribute("entrust", entrust);
	    return "business/entrust/edit";
	}
	@GetMapping("/info/{id}")
	String info(@PathVariable("id") Long id,Model model){
		EntrustDO entrust = entrustService.get(id);
		model.addAttribute("id", entrust.getRoomId());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		model.addAttribute("leasebackStartDate", sdf.format(entrust.getLeasebackStartDate()));
		return "business/entrust/info";
	}
	@GetMapping("/infoPay/{id}")
	String infoPay(@PathVariable("id") Long id,Model model){
		EntrustDO entrust = entrustService.get(id);
		model.addAttribute("id", entrust.getRoomId());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		model.addAttribute("leasebackStartDate", sdf.format(entrust.getLeasebackStartDate()));
		return "business/entrust/infoPay";
	}
    @GetMapping("/fanzu/{id}")
    String pay(@PathVariable("id") Long id,Model model){
        LeasebackDO leasebackDO = entrustService.getLease(id);
        model.addAttribute("leasebackDO", leasebackDO);
        Date date=new Date();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        String d=sdf.format(date);
        model.addAttribute("date", d);
        model.addAttribute("name", ShiroUtils.getUser().getName());
        return "business/entrust/fanzu";
    }
	@ResponseBody
	@GetMapping("/detailList")
	public PageUtils detailList(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<LeasebackDO> leasebackDOs = entrustService.detailList(query);
		int total = entrustService.detailCount(query);
		PageUtils pageUtils = new PageUtils(leasebackDOs, total);
		return pageUtils;
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("business:entrust:add")
	public R save( EntrustDO entrust){
		if(entrustService.save(entrust)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("business:entrust:edit")
	public R update( EntrustDO entrust){
		entrustService.update(entrust);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("business:entrust:remove")
	public R remove( Long id){
		if(entrustService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
    /**
     * 缴费
     */
    @ResponseBody
    @PostMapping("/pay")
    public PayListDO pay(HttpServletRequest request) throws IOException {
        String jsonStr = request.getParameter("mydata");
        ObjectMapper mapper = new ObjectMapper();
        PayListDO payListDO = mapper.readValue(jsonStr, PayListDO.class);
        LeasebackDO leasebackDO = entrustService.getLease(payListDO.getYewuId());
        RoomDO roomDO=roomService.get(leasebackDO.getRoomId());
        UserDO userDO=ShiroUtils.getUser();
        Map<String, Object> params=new HashedMap();
        params.put("roomId",roomDO.getId());
        params.put("deptId", userDO.getDeptId());
        RoomContractDO roomContractDO=contractService.list(params).get(0);
        DeptDO deptDO=deptService.get(userDO.getDeptId());
        String payId=entrustService.getId("FZ",userDO.getDeptId());
        BuildingDO buildingDO=buildingService.get(roomDO.getBuildingId());
        BuildingDO parent=buildingService.get(buildingDO.getParentId());
        payListDO.setCode(payId);
        payListDO.setName(roomContractDO.getName());
        payListDO.setDeptId(userDO.getDeptId());
        payListDO.setDeptName(userDO.getDeptName());
        payListDO.setState("0");
        payListDO.setBuildingId(roomDO.getBuildingId());
        if(parent!=null){
            payListDO.setBuildingName(parent.getName()+"-"+buildingDO.getName());
        }else{
            payListDO.setBuildingName(buildingDO.getName());
        }
        payListDO.setRoomId(roomDO.getId());
        payListDO.setYewuId(leasebackDO.getId());
        payListDO.setRoomCode(roomDO.getCode());
        payListDO.setUnit(roomDO.getUnit());
        payListDO.setFloor(roomDO.getFloor());
        payListDO.setPrint(0);
        payListDO.setArea(Long.valueOf(deptDO.getArea()));
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        payListDO.setDate(sdf.format(leasebackDO.getMonth()));
        payListDO.preInsert();
        Double price=payListDO.getPrice();
        Double payed=leasebackDO.getPayed()+price;
        Double ying=leasebackDO.getYing();
        Double unpay=ying-payed;
        leasebackDO.setPayed(payed);
        leasebackDO.setUnpay(unpay);
        entrustService.savePay(payListDO);
        entrustService.updateDetail(leasebackDO);
        return payListDO;
    }
	@PostMapping( "/chongxiao")
	@ResponseBody
	public R chongxiao( Long id){
		PayListDO payListDO=entrustService.getPay(id);
		payListDO.setState("2");
		entrustService.updatePay(payListDO);
		LeasebackDO leasebackDO=entrustService.getLease(payListDO.getYewuId());
		Double price=payListDO.getPrice()*(-1);
		payListDO.setPrice(price);
		payListDO.setState("1");
		payListDO.setChongxiaoCode(payListDO.getCode());
		String payId=entrustService.getId("FZ",leasebackDO.getDeptId());
		payListDO.setCode(payId);
		payListDO.preInsert();
		Double payed=leasebackDO.getPayed()+price;
		Double ying=leasebackDO.getYing();
		Double unpay=ying-payed;
		leasebackDO.setPayed(payed);
		leasebackDO.setUnpay(unpay);
		entrustService.updateDetail(leasebackDO);
		if(entrustService.savePay(payListDO)>0){
			return R.ok();
		}
		return R.ok();
	}
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("business:entrust:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		entrustService.batchRemove(ids);
		return R.ok();
	}
	/**
	 * <p>Discription:[下载模板功能]</p>
	 * Created on 2018年2月1日 上午11:57:59
	 * @param response response对象
	 * @param request response对象
	 * @author:[全冉]
	 */
	@ResponseBody
	@RequiresPermissions("business:entrust:download")
	@PostMapping("/download")
	public void downloadTemplate(HttpServletResponse response, HttpServletRequest request) {
		InputStream inputStream = null;
		ServletOutputStream servletOutputStream = null;
		try {
			String filename = "委托合同.xls";
			String path = "static/file/weituo.xls";
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
	@PostMapping("/upload")
	R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException, ParseException {
		//把表单内容转换成流
		InputStream fileInputStream = file.getInputStream();
		HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
		HSSFSheet sheetAt = workbook.getSheetAt(0);
		Row nRow = null;
		String orderId= String.valueOf(System.currentTimeMillis());
		//错误
		int repeat = 0;
		//记录第几行重复
		StringBuilder sb = new StringBuilder();
		int end = sheetAt.getLastRowNum();
		if(end==0){
			return R.error("请填写房屋信息");
		}
		UserDO userDO= ShiroUtils.getUser();
		Long deptId=userDO.getDeptId();
		EntrustDO entrustDO=null;
		List<String> rooms=contractService.getRoom(deptId);//查询当前项目下所有房屋
		List<String> strings=entrustService.getEntrustList(deptId);
		List<EntrustDO> entrustDOs=new ArrayList<>();
		List<LeasebackDO> leasebackDOs=new ArrayList<>();
		int y=0;
		int m=0;
		int d=0;
		for (int j = 1; j <= end; j++) {
			if (repeat > 0) {
				break;
			}
			int rowNum = j + 1;
			nRow = sheetAt.getRow(j);
			entrustDO=new EntrustDO();
			Calendar startDate=Calendar.getInstance();
			Calendar endDate=Calendar.getInstance();
			Calendar leasebackStartDate=Calendar.getInstance();
			Calendar leasebackEndDate=Calendar.getInstance();
			if((nRow.getCell(0) == null|| getCellValue(nRow.getCell(0)).equals(""))&&(nRow.getCell(1) == null|| getCellValue(nRow.getCell(1)).equals(""))&&(nRow.getCell(2) == null|| getCellValue(nRow.getCell(2)).equals(""))){
				break;
			}
			//判断第一个单元格是否为空，如果为空  整行都不插入
			if (nRow.getCell(0) == null ||  nRow.getCell(0).getCellType() != HSSFCell.CELL_TYPE_NUMERIC) {
				sb.append("第" + rowNum + "行没有输入楼宇编号");
				repeat++;
				break;
			}else{
				entrustDO.setBuildingId(Long.valueOf(getCellValue((nRow.getCell(0)))));
			}
			if (nRow.getCell(1) == null ||  getCellValue(nRow.getCell(1)).equals("")) {
				sb.append("第" + rowNum + "行没有输入房屋编号");
				repeat++;
				break;
			}else{
				entrustDO.setRoomCode(getCellValue(nRow.getCell(1)));
			}
			String room=""+entrustDO.getBuildingId()+"-"+entrustDO.getRoomCode();
			boolean isContains =UploadUtils.checkHas(room,rooms);
			if(!isContains){
				sb.append("第" + rowNum + "行不存在该房屋");
				repeat++;
				break;
			}
			room=""+entrustDO.getBuildingId()+"_"+entrustDO.getRoomCode()+"_";
			isContains =UploadUtils.checkHas(room,strings);
			if(!isContains){
				sb.append("第" + rowNum + "行房屋已有委托合同");
				repeat++;
				break;
			}
			if (nRow.getCell(2) == null || getCellValue(nRow.getCell(2)).equals("")) {
				sb.append("第" + rowNum + "行没有输入委托起止日期");
				repeat++;
				break;
			}else{
				try {
					if(!HSSFDateUtil.isCellDateFormatted(nRow.getCell(2))){
						sb.append("第" + rowNum + "行没有输入正确格式的委托起止日期");
						repeat++;
						break;
					}
				} catch (Exception e) {
					String date=getCellValue(nRow.getCell(2));
					if(date.contains("-")){
						String sDate=date.split("-")[0];
						sDate=sDate.replace(".","-");
						String eDate=date.split("-")[1];
						eDate=eDate.replace(".","-");
						if(UploadUtils.checkDate(sDate)&&UploadUtils.checkDate(eDate)){
							startDate=UploadUtils.formatDate(sDate);
							y=startDate.get(Calendar.YEAR);
							m=startDate.get(Calendar.MONTH);
							d=startDate.get(Calendar.DATE);
							endDate=UploadUtils.formatDate(eDate);
							if(startDate==null||endDate==null){
								sb.append("第" + rowNum + "行没有输入正确格式的委托起止日期");
								repeat++;
								break;
							}else {
								isContains = UploadUtils.checkHas(room,strings);
								if(isContains){
									ArrayList<String> list = new ArrayList<>();
									SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
									for (String s : strings) {
										if (s.contains(room)){
											String sd=s.split("_")[2];
											String ed=s.split("_")[3];
											Long sd1=sdf.parse(sd).getTime();
											Long ed1=sdf.parse(ed).getTime();
											Long sd2=startDate.getTime().getTime();
											Long ed2=endDate.getTime().getTime();
											if((sd1>=sd2&&sd1<=ed2)||(sd1<=sd2&&ed1>=ed2)||(ed1>=sd2&&ed1<=ed2)){
												sb.append("第" + rowNum + "行委托起止日期和已存在");
												repeat++;
												break;
											}
										}
									}
								}
								int startYear=startDate.get(Calendar.YEAR);
								int endYear=endDate.get(Calendar.YEAR);
								if(endYear-startYear+1>4){
									leasebackStartDate=Calendar.getInstance();
									leasebackStartDate.set(y,m,d);
									leasebackStartDate.add(Calendar.YEAR,3);
									entrustDO.setLeasebackStartDate(leasebackStartDate.getTime());
									entrustDO.setLeasebackEndDate(endDate.getTime());
									entrustDO.setLeasebackStartYear(leasebackStartDate.get(Calendar.YEAR));
									entrustDO.setLeasebackEndYear(endDate.get(Calendar.YEAR));
								}
								entrustDO.setEntrustStartYear(startYear);
								entrustDO.setEntrustEndYear(endYear);
								entrustDO.setEntrustStartDate(startDate.getTime());
								entrustDO.setEntrustEndDate(endDate.getTime());
							}
						}else {
							sb.append("第" + rowNum + "行没有输入正确格式的委托起止日期");
							repeat++;
							break;
						}
					}else{
						sb.append("第" + rowNum + "行没有输入正确格式的委托起止日期");
						repeat++;
						break;
					}
				}
			}
			String type="";//返租约定
			Calendar end2=Calendar.getInstance();//第二年返租结束日期
			Calendar start2=Calendar.getInstance();//第二年返租开始日期
			if (nRow.getCell(3) == null ||  getCellValue(nRow.getCell(3)).equals("")) {
				entrustDO.setType("0");
			}else{
				type =getCellValue(nRow.getCell(3));
				if(!type.equals("0")&&!type.equals("7")){
					sb.append("第" + rowNum + "行输入委托约定错误");
					repeat++;
					break;
				}
				if(type.equals("0")){
					type="1";
					entrustDO.setType("0");
				}else {
					type="2";
					entrustDO.setType("2");
				}
			}
			if(type.equals("2")){
				if (nRow.getCell(4) == null ||  getCellValue(nRow.getCell(4)).equals("")) {
					sb.append("第" + rowNum + "行没有输入返租开始日期");
					repeat++;
					break;
				}else{
					Date dd=new Date();
					try {
						if(!HSSFDateUtil.isCellDateFormatted(nRow.getCell(4))){
							sb.append("第" + rowNum + "行没有输入正确格式的返租开始日期");
							repeat++;
							break;
						}
						dd=nRow.getCell(4).getDateCellValue();
						start2.setTime(dd);
					} catch (Exception e) {
						String date=getCellValue(nRow.getCell(4));
						if(!UploadUtils.checkDate(date)){
							sb.append("第" + rowNum + "行没有输入正确格式的返租开始日期");
							repeat++;
							break;
						}else {
							int year= Integer.parseInt(date.substring(0,4));
							int month= Integer.parseInt(date.substring(5,7));
							int day= Integer.parseInt(date.substring(8,10));
							start2.set(year,month-1,day);
						}
					}
				}
			}
			if(type.equals("2")){
				if (nRow.getCell(5) == null ||  getCellValue(nRow.getCell(5)).equals("")) {
					sb.append("第" + rowNum + "行没有输入两年后返租日期");
					repeat++;
					break;
				}else{
					Date dd=new Date();
					try {
						if(!HSSFDateUtil.isCellDateFormatted(nRow.getCell(5))){
							sb.append("第" + rowNum + "行没有输入正确格式的两年后返租日期");
							repeat++;
							break;
						}
						dd=nRow.getCell(5).getDateCellValue();
						end2.setTime(dd);
					} catch (Exception e) {
						String date=getCellValue(nRow.getCell(5));
						if(!UploadUtils.checkDate(date)){
							sb.append("第" + rowNum + "行没有输入正确格式的两年后返租日期");
							repeat++;
							break;
						}else {
							int year= Integer.parseInt(date.substring(0,4));
							int month= Integer.parseInt(date.substring(5,7));
							int day= Integer.parseInt(date.substring(8,10));
							end2.set(year,month-1,day);
						}
					}
				}
			}
			int n=DateUtils.getMonthNum(startDate.getTime(),endDate.getTime());
			LeasebackDO l=null;
			Calendar c=Calendar.getInstance();
			c.set(y,m,d);
			int day4=0;//第四年和第五年天数
			day4=DateUtils.calcDayOffset(start2,end2);
			for(int i=0;i<n;i++){
				l=new LeasebackDO();
				if(type.equals("1")){
					l.setType("1");
				}
				else if(type.equals("7")){
					l.setType("2");
				}else {
					l.setType("0");
				}
				l.setStartDate(c.getTime());
				//三四年委托不生成返租记录
				if(entrustDO.getLeasebackStartDate()==null) {
				}
				else {
					if(i==0&&n==1){
						//不考虑该情况
					}else{
						if(i==0){
							int lastDay = DateUtils.getMaxDay(c);
							int day=lastDay-c.get(Calendar.DATE)+1;
							l.setDay(day);
							l.setType("0");
							l.setStartDate(startDate.getTime());
							l.setEndDate(DateUtils.getMaxDate(c).getTime());
							l.setYing(Double.valueOf(0));
							l.setUnpay(Double.valueOf(0));
							l.setPayed(Double.valueOf(0));
							l.setMonth(c.getTime());
						}
						else if(i>0&&i<n-1){
							if(type.equals("2")){
								int startYear=start2.get(Calendar.YEAR);
								int startMonth=start2.get(Calendar.MONTH);
								int endYear=end2.get(Calendar.YEAR);
								int endMonth=end2.get(Calendar.MONTH);
								int cYear=c.get(Calendar.YEAR);
								int cMonth=c.get(Calendar.MONTH);
								if(UploadUtils.getMonths(c)<UploadUtils.getMonths(start2)){
									l.setType("0");
									int lastDay = DateUtils.getMaxDay(c);
									l.setDay(lastDay);
									Calendar cal=Calendar.getInstance();
									cal.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),1);
									l.setStartDate(cal.getTime());
									l.setEndDate(DateUtils.getMaxDate(c).getTime());
									l.setYing(Double.valueOf(0));
									l.setUnpay(Double.valueOf(0));
									l.setPayed(Double.valueOf(0));
									l.setMonth(c.getTime());
								}else if(UploadUtils.getMonths(c)==UploadUtils.getMonths(start2)){
									l.setType("2");
									l.setYear("4");
									l.setDayFour(day4);
									int lastDay = DateUtils.getMaxDay(c);
									int day=start2.get(Calendar.DATE);
									l.setDay(lastDay-day+1);
									int year=c.get(Calendar.YEAR);
									int month=c.get(Calendar.MONTH);
									l.setStartDate(start2.getTime());
									l.setEndDate(DateUtils.getMaxDate(c).getTime());
									l.setYing(Double.valueOf(0));
									l.setUnpay(Double.valueOf(0));
									l.setPayed(Double.valueOf(0));
									//第一个返祖月
									l.setStatus("0");
									l.setMonth(c.getTime());
									int startDay=start2.get(Calendar.DATE);
									if(startDay>1){
										Calendar s=Calendar.getInstance();
										s.set(cYear,cMonth,1);
										l.setNormalStartDate(s.getTime());
										Calendar e=Calendar.getInstance();
										e.set(startYear,startMonth,startDay);
										e.add(Calendar.DATE,-1);
										l.setNormalEndDate(e.getTime());
									}
								}else if(UploadUtils.getMonths(c)>UploadUtils.getMonths(start2)&&
										UploadUtils.getMonths(end2)>UploadUtils.getMonths(c)){
									l.setType("2");
									l.setYear("4");
									l.setDayFour(day4);
									int lastDay = DateUtils.getMaxDay(c);
									l.setDay(lastDay);
									Calendar cal=Calendar.getInstance();
									cal.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),1);
									l.setStartDate(cal.getTime());
									l.setEndDate(DateUtils.getMaxDate(c).getTime());
									l.setYing(Double.valueOf(0));
									l.setUnpay(Double.valueOf(0));
									l.setPayed(Double.valueOf(0));
									l.setMonth(c.getTime());
								}else if(UploadUtils.getMonths(end2)==UploadUtils.getMonths(c)){
									l.setType("2");
									l.setYear("5");
									l.setDayFour(day4);
									int lastDay = end2.get(Calendar.DATE);
									l.setDay(lastDay);
									Calendar cal=Calendar.getInstance();
									cal.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),1);
									l.setStartDate(cal.getTime());
									l.setEndDate(end2.getTime());
									l.setYing(Double.valueOf(0));
									l.setUnpay(Double.valueOf(0));
									l.setPayed(Double.valueOf(0));
									l.setStatus("2");
									l.setMonth(c.getTime());
									int startDay=end2.get(Calendar.DATE);
									int maxDay=DateUtils.getMaxDay(end2);
									if(startDay<maxDay){
										Calendar e=Calendar.getInstance();
										e.set(endYear,endMonth,end2.get(Calendar.DATE));
										e.add(Calendar.DATE,1);
										l.setNormalStartDate(e.getTime());
										l.setNormalEndDate(DateUtils.getMaxDate(end2).getTime());
									}
								}
								else {
									l.setType("1");
									int lastDay = DateUtils.getMaxDay(c);
									l.setDay(lastDay);
									Calendar cal=Calendar.getInstance();
									cal.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),1);
									l.setStartDate(cal.getTime());
									l.setEndDate(DateUtils.getMaxDate(c).getTime());
									l.setYing(Double.valueOf(0));
									l.setUnpay(Double.valueOf(0));
									l.setPayed(Double.valueOf(0));
									l.setMonth(c.getTime());
								}
							}else {
								if(i<36){
									l.setType("0");
									int lastDay = DateUtils.getMaxDay(c);
									l.setDay(lastDay);
									Calendar cal=Calendar.getInstance();
									cal.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),1);
									l.setStartDate(cal.getTime());
									l.setEndDate(DateUtils.getMaxDate(c).getTime());
									l.setYing(Double.valueOf(0));
									l.setUnpay(Double.valueOf(0));
									l.setPayed(Double.valueOf(0));
									l.setMonth(c.getTime());
								}
								else {
									l.setType("1");
									int lastDay = DateUtils.getMaxDay(c);
									l.setDay(lastDay);
									Calendar cal=Calendar.getInstance();
									cal.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),1);
									l.setStartDate(cal.getTime());
									l.setEndDate(DateUtils.getMaxDate(c).getTime());
									l.setYing(Double.valueOf(0));
									l.setUnpay(Double.valueOf(0));
									l.setPayed(Double.valueOf(0));
									l.setMonth(c.getTime());
								}
							}
						}else{
							l.setType("1");
							int lastDay = endDate.get(Calendar.DATE);
							l.setDay(lastDay);
							Calendar cal=Calendar.getInstance();
							cal.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),1);
							l.setStartDate(cal.getTime());
							l.setEndDate(endDate.getTime());
							l.setYing(Double.valueOf(0));
							l.setUnpay(Double.valueOf(0));
							l.setPayed(Double.valueOf(0));
							l.setMonth(c.getTime());
						}
					}
				}
				l.setDeptId(deptId);
				l.setOrderId(orderId);
				l.setRoomCode(entrustDO.getRoomCode());
				l.setBuildingId(entrustDO.getBuildingId());
				c.add(Calendar.MONTH,1);
				leasebackDOs.add(l);
			}
			entrustDO.setDeptId(deptId);
			entrustDO.setDelFlag("0");
			entrustDO.setOrderId(orderId);
			entrustDO.preInsert();
			entrustDO.setState("0");
			entrustDOs.add(entrustDO);
		}
		if (repeat > 0) {
			return R.error(sb.toString());
		} else {
			if(leasebackDOs.size()>0){
				int insertLength =leasebackDOs.size();
				int i = 0;
				while (insertLength > 2000) {
					entrustService.batchInsertLeaseback(leasebackDOs.subList(i, i + 2000));
					i = i + 2000;
					insertLength = insertLength - 2000;
				}
				if (insertLength > 0) {
					entrustService.batchInsertLeaseback(leasebackDOs.subList(i, i + insertLength));
				}
			}
			if(entrustDOs.size()>0){
				int i=entrustService.batchInsert(entrustDOs);
				if(i>0){
					return R.ok("导入"+i+"条数据");
				}else{
					R.error();
				}
			}else {
				return R.error("没有能导入的委托合同或者导入的房屋在该时间段已签署合同");
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
	public static void main(String[] a){
		Calendar c=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String b=sdf.format(c.getTime());
		System.out.println(b);
		c.add(Calendar.MONTH,12);
		c.add(Calendar.DATE,-1);
		System.out.println(sdf.format(c.getTime()));
	}
}
