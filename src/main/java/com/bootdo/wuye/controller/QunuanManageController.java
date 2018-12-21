package com.bootdo.wuye.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
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
import com.bootdo.wuye.domain.*;
import com.bootdo.wuye.domain.QunuanfeiDO;
import com.bootdo.wuye.domain.QunuanfeiDetailDO;
import com.bootdo.wuye.service.FangbenManageService;
import com.bootdo.wuye.service.QunuanManageService;
import com.bootdo.wuye.service.WuyefeiManageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 房间管理
 *
 */

@Controller
@RequestMapping("/wuye/qunuanManage")
public class QunuanManageController extends BaseController {
	@Autowired
	private RoomService roomService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private BuildingService buildingService;
	@Resource
	private ResourceLoader resourceLoader;
	@Autowired
	private RoomContractService contractService;
	@Autowired
	private QunuanManageService qunuanManageService;
	@Autowired
	private FangbenManageService fangbenManageService;
	@Autowired
	private WuyefeiManageService wuyefeiManageService;

	private String prefix = "wuye/qunuanManage";

	@GetMapping()
	@RequiresPermissions("wuye:qunuanManage:manage")
	String room() {
		return prefix + "/manage";
	}
	@GetMapping("/qunuanPay")
	@RequiresPermissions("wuye:qunuanManage:qunuanPay")
	String qunuanPay() {
		return "wuye/qunuanPay/qunuanPay";
	}
	@GetMapping("/payIndex")
	@RequiresPermissions("wuye:qunuanPay:payIndex")
	String payIndex(){
		return "wuye/pay/qunuanPay";
	}
	@GetMapping("/print/{id}")
	@RequiresPermissions("wuye:qunuanPay:print")
	String print(@PathVariable("id") Long id, Model model){
		PayListDO payListDO=qunuanManageService.getPay(id);
        QunuanfeiDetailDO qunuanfeiDetailDO=qunuanManageService.getDetail(payListDO.getYewuId());
		QunuanfeiDO qunuanfeiDO=qunuanManageService.get(qunuanfeiDetailDO.getQunuanfeiId());
		Date pay=payListDO.getPayDate();
		Calendar c=Calendar.getInstance();
		c.setTime(pay);
        if(payListDO.getName()==null){
            payListDO.setName("");
        }
        int print=payListDO.getPrint()+1;
        payListDO.setPrint(print);
		int month = c.get(Calendar.MONTH)+1;
		model.addAttribute("year","  "+c.get(Calendar.YEAR)+"  ");
		model.addAttribute("month","  "+month+"  ");
		model.addAttribute("day","  "+c.get(Calendar.DATE)+"  ");
		model.addAttribute("p",payListDO);
        model.addAttribute("name",qunuanfeiDO.getName());
		return "wuye/print/qunuanfei";
	}
	@ApiOperation(value="获取房间列表", notes="")
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		Query query = new Query(params);
		String year=query.get("year").toString();
		if(StringUtils.isNotBlank(year)){
			query.put("startYear",Integer.valueOf(year.split(" - ")[0]));
			query.put("endYear",Integer.valueOf(year.split(" - ")[1]));
		}
		query.put("delFlag","0");
		query.put("deptId",ShiroUtils.getUser().getDeptId());
		List<QunuanfeiDO> qunuanDOList = qunuanManageService.list(query);
		int total = qunuanManageService.count(query);
		PageUtils pageUtils = new PageUtils(qunuanDOList, total);
		return pageUtils;
	}
	@ResponseBody
	@PostMapping("/getSum")
	QunuanfeiDO getSum(QunuanfeiDO w, Model model) {
		w.setDeptId(ShiroUtils.getUser().getDeptId());
		QunuanfeiDO qunuanfeiDO = qunuanManageService.getSum(w);
		return qunuanfeiDO;
	}
	@ResponseBody
	@PostMapping("/getPaySum")
	QunuanfeiDO getPaySum(@RequestParam Map<String, Object> params) {
		params.put("deptId",ShiroUtils.getUser().getDeptId());
		QunuanfeiDO qunuanfeiDO = qunuanManageService.getPaySum(params);
		return qunuanfeiDO;
	}
	@ResponseBody
	@PostMapping("/getSumMoney")
	QunuanfeiDetailDO getSumMoney(@RequestParam Map<String, Object> params) {
		params.put("deptId",ShiroUtils.getUser().getDeptId());
		QunuanfeiDetailDO qunuanfeiDO = qunuanManageService.getSumMoney(params);
		return qunuanfeiDO;
	}
	@GetMapping("/add")
	@RequiresPermissions("wuye:qunuanManage:add")
	String add(Model model) {
		Map<String, Object> params=new HashedMap();
		params.put("start",new Date().getYear());
		params.put("delFlag","0");
		params.put("sort","start_year");
		params.put("order","DESC");
		params.put("deptId",ShiroUtils.getUser().getDeptId());
		List<QunuanfeiDO> qunuanDOList=qunuanManageService.list(params);
		if(qunuanDOList.size()>0){
			Date d=qunuanDOList.get(0).getEndDate();
			Calendar c=Calendar.getInstance();
			c.setTime(d);
			int day=c.get(Calendar.DATE);
			c.set(Calendar.DATE,day+1);
			model.addAttribute("startDate", c.getTime());
		}else {
			model.addAttribute("startDate", "");
		}
		return  prefix + "/add";
	}


	@GetMapping("/edit/{id}")
	@RequiresPermissions("wuye:qunuanManage:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		QunuanfeiDO qunuanDO = qunuanManageService.get(id);
		model.addAttribute("qunuan", qunuanDO);
		return  prefix + "/edit";
	}
	@GetMapping("/editDetail/{id}")
	String editDetail(@PathVariable("id") Long id, Model model) {
		QunuanfeiDetailDO qunuanDetailDO=qunuanManageService.getDetail(id);
		model.addAttribute("qunuanDetailDO", qunuanDetailDO);
		return  prefix + "/editDetail";
	}
	@GetMapping("/info/{id}")
	@RequiresPermissions("wuye:qunuanManage:info")
	String info(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		return  prefix + "/info";
	}
	@GetMapping("/payInfo/{id}")
	@RequiresPermissions("wuye:qunuanManage:payInfo")
	String payInfo(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		return  "wuye/qunuanPay/info";
	}
	@GetMapping("/shou/{id}")
	String pay(@PathVariable("id") Long id,Model model){
		QunuanfeiDetailDO qunuanDetail = qunuanManageService.getDetail(id);
		model.addAttribute("qunuanDetail", qunuanDetail);
		model.addAttribute("name", ShiroUtils.getUser().getName());
        Date date=new Date();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        String d=sdf.format(date);
        model.addAttribute("date", d);
		return "wuye/qunuanPay/shou";
	}
    @ResponseBody
    @GetMapping("/printCount/{id}")
    R printCount(@PathVariable("id") Long id, Model model) {
        PayListDO payListDO = qunuanManageService.getPay(id);
        int print=payListDO.getPrint()+1;
        PayListDO p=new PayListDO();
        p.setId(id);
        p.setPrint(print);
        qunuanManageService.updatePay(p);
        return  R.ok();
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
		QunuanfeiDetailDO qunuanDetail = qunuanManageService.getDetail(payListDO.getYewuId());
		RoomDO roomDO=roomService.get(qunuanDetail.getRoomId());
		UserDO userDO=ShiroUtils.getUser();
		Map<String, Object> params=new HashedMap();
		params.put("roomId",roomDO.getId());
		params.put("deptId", userDO.getDeptId());
		RoomContractDO roomContractDO=contractService.list(params).get(0);
		DeptDO deptDO=deptService.get(userDO.getDeptId());
		String payId=qunuanManageService.getId("WY");
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
		payListDO.setYewuId(qunuanDetail.getId());
		payListDO.setRoomCode(roomDO.getCode());
		payListDO.setUnit(roomDO.getUnit());
		payListDO.setFloor(roomDO.getFloor());
		payListDO.setPrint(0);
		payListDO.setArea(Long.valueOf(deptDO.getArea()));
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		payListDO.setDate(sdf.format(qunuanDetail.getStartDate())+"-"+sdf.format(qunuanDetail.getEndDate()));
		payListDO.preInsert();
		Double price=payListDO.getPrice();
		String jiaoType=payListDO.getsType();
		if(jiaoType.equals("2")){
			Double payed=qunuanDetail.getQunuanPayed()+price;
			Double ying=qunuanDetail.getQunuanYing();
			Double unpay=ying-payed;
			qunuanDetail.setQunuanPayed(payed);
			qunuanDetail.setQunuanUnpay(unpay);
		}
		if(jiaoType.equals("3")){
			Double payed=qunuanDetail.getTingnuanPay()+price;
			Double ying=qunuanDetail.getTingnuanYing();
			Double unpay=ying-payed;
			qunuanDetail.setTingnuanPay(payed);
			qunuanDetail.setTingnuanUnpay(unpay);
		}
		Boolean isOk=false;
		if(qunuanDetail.getQunuanUnpay()<=0||qunuanDetail.getTingnuanUnpay()<=0){
			qunuanDetail.setState("1");
			isOk=true;
		}
		qunuanDetail.preUpdate();
		qunuanManageService.savePay(payListDO);
		qunuanManageService.updateDetail(qunuanDetail);
		qunuanManageService.updateCount(qunuanDetail.getQunuanfeiId());
		return payListDO;
	}
	@PostMapping( "/chongxiao")
	@ResponseBody
	public R chongxiao( Long id){
		PayListDO payListDO=qunuanManageService.getPay(id);
		payListDO.setState("2");
		qunuanManageService.updatePay(payListDO);
		QunuanfeiDetailDO qunuanDetail=qunuanManageService.getDetail(payListDO.getYewuId());
		Double price=payListDO.getPrice()*(-1);
		payListDO.setPrice(price);
		payListDO.setState("1");
		payListDO.setChongxiaoCode(payListDO.getCode());
		String payId=qunuanManageService.getId("WY");
		payListDO.setCode(payId);
		payListDO.preInsert();
		String jiaoType=payListDO.getSType();
		if(jiaoType.equals("2")){
			Double payed=qunuanDetail.getQunuanPayed()+price;
			Double ying=qunuanDetail.getQunuanYing();
			Double unpay=ying-payed;
			qunuanDetail.setQunuanPayed(payed);
			qunuanDetail.setQunuanUnpay(unpay);
		}
		if(jiaoType.equals("3")){
			Double payed=qunuanDetail.getTingnuanPay()+price;
			Double ying=qunuanDetail.getTingnuanYing();
			Double unpay=ying-payed;
			qunuanDetail.setTingnuanPay(payed);
			qunuanDetail.setTingnuanUnpay(unpay);
		}
		if(qunuanDetail.getQunuanUnpay()<=0||qunuanDetail.getTingnuanUnpay()<=0){
			qunuanDetail.setState("1");
		}else {
			qunuanDetail.setState("0");
		}
		qunuanDetail.preUpdate();
		qunuanManageService.updateDetail(qunuanDetail);
		if(qunuanManageService.savePay(payListDO)>0){
			qunuanManageService.updateCount(qunuanDetail.getQunuanfeiId());
			return R.ok();
		}
		return R.error();
	}
	@GetMapping("/payList")
	@ResponseBody
	public PageUtils payList(@RequestParam Map<String, Object> params){
		UserDO userDO=ShiroUtils.getUser();
		params.put("deptId",userDO.getDeptId());
		params.put("types","qunuan");
		//查询列表数据
		Query query = new Query(params);
		List<PayListDO> payListDOs = fangbenManageService.pay(query);
		int total = fangbenManageService.countPayList(query);
		PageUtils pageUtils = new PageUtils(payListDOs, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/detailList")
	public PageUtils detailList(@RequestParam Map<String, Object> params) {
		params.put("deptId",ShiroUtils.getUser().getDeptId());
		//查询列表数据
		Query query = new Query(params);
		List<QunuanfeiDetailDO> qunuanDOList = qunuanManageService.detailList(query);
		int total = qunuanManageService.countDetail(query);
		PageUtils pageUtils = new PageUtils(qunuanDOList, total);
		return pageUtils;
	}

	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("wuye:qunuanManage:edit")
	public R update(QunuanfeiDO q) throws IOException {
		Date startDate=q.getStartDate();
		Date endDate=q.getEndDate();
		Calendar s=Calendar.getInstance();
		Calendar e=Calendar.getInstance();
		s.setTime(startDate);
		e.setTime(endDate);
		q.setStartYear(String.valueOf(s.get(Calendar.YEAR)));
		q.setEndYear(String.valueOf(e.get(Calendar.YEAR)));
		if (qunuanManageService.update(q) > 0) {
			return R.ok();
		}
		return R.error();
		/*String jsonStr = request.getParameter("mydata");
		ObjectMapper mapper = new ObjectMapper();
		QunuanfeiDO qunuan = mapper.readValue(jsonStr, QunuanfeiDO.class);
		qunuan.setDeptId(ShiroUtils.getUser().getDeptId());
		int i=qunuanManageService.checkRoomNum(qunuan);
		return R.ok();*/
	}
	@ResponseBody
	@RequestMapping("/updateDetail")
	public R updateDetail(HttpServletRequest request) throws IOException {
		String jsonStr = request.getParameter("mydata");
		ObjectMapper mapper = new ObjectMapper();
		QunuanfeiDetailDO qunuanDetailDO = mapper.readValue(jsonStr, QunuanfeiDetailDO.class);
		if (qunuanManageService.updateDetail(qunuanDetailDO) > 0) {
			return R.ok();
		}
		return R.error();
	}
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("wuye:qunuanManage:remove")
	public R remove(Long id) {
		int i=qunuanManageService.checkQunuanfei(id);
		if(i>0){
			return R.error("已有缴费信息，不能删除");
		}else {
			QunuanfeiDO qunuanDO=new QunuanfeiDO();
			qunuanDO.setId(id);
			qunuanDO.preUpdate();
			qunuanDO.setDelFlag("1");
			i=qunuanManageService.update(qunuanDO);
			if(i>0){
				return R.ok();
			}
		}
		return R.error();
	}
	@PostMapping("/removeDetail")
	@ResponseBody
	public R removeDetail(Long id) {
		QunuanfeiDetailDO qunuanDetailDO=qunuanManageService.getDetail(id);
		if(qunuanDetailDO.getQunuanPayed()>0||qunuanDetailDO.getTingnuanPay()>0){
			return R.error("已有缴费信息，不能删除");
		}else {
			qunuanManageService.removeDetail(id);
			QunuanfeiDO qunuanDO = qunuanManageService.get(qunuanDetailDO.getQunuanfeiId());
			QunuanfeiDO w=new QunuanfeiDO();
			w.setId(qunuanDO.getId());
			w.setUnpay(qunuanDO.getUnpay()-1);
			qunuanManageService.update(w);
			return R.ok();
		}
	}
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("wuye:qunuanManage:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		qunuanManageService.batchRemove(ids);
		return R.ok();
	}
	@ResponseBody
	@RequiresPermissions("wuye:qunuanManage:download")
	@PostMapping("/download")
	public void downloadTemplate(HttpServletResponse response, HttpServletRequest request) {
		InputStream inputStream = null;
		ServletOutputStream servletOutputStream = null;
		try {
			String filename = "取暖费模板.xls";
			String path = "static/file/heating.xls";
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
	@RequiresPermissions("wuye:qunuanManage:add")
	@PostMapping("/upload")
	R upload(@RequestParam("file") MultipartFile file, @RequestParam("name")String name, @RequestParam("startDate") Date  startDate, @RequestParam("endDate") Date endDate, @RequestParam("id")String id) throws IOException {
		if(StringUtils.isBlank(name)){
			return R.error("请输入取暖费名称");
		}
		if(startDate==null){
			return R.error("请选择开始日期");
		}
		if(endDate==null){
			return R.error("请选择结束日期");
		}
		if(endDate.before(startDate)){
			return R.error("开始日期不能晚于结束日期");
		}
		Calendar s=Calendar.getInstance();
		s.setTime(startDate);
		Calendar e=Calendar.getInstance();
		e.setTime(endDate);
		String start= String.valueOf(s.get(Calendar.YEAR));
		String end=String.valueOf(e.get(Calendar.YEAR));
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//把表单内容转换成流
		InputStream fileInputStream = file.getInputStream();
		HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
		HSSFSheet sheetAt = workbook.getSheetAt(0);
		Row nRow = null;
		String sheetName = null;
		//错误
		int repeat = 0;
		//记录第几行重复
		StringBuilder sb = new StringBuilder();
		int index = sheetAt.getLastRowNum();
		if(index==1){
			return R.error("请填写取暖费信息");
		}
		UserDO userDO= ShiroUtils.getUser();
		Long deptId=userDO.getDeptId();
		List<String> rooms=contractService.getRoom(deptId);//查询当前项目下所有房屋
		List<String> roomsHas=null;
		if(StringUtils.isNotBlank(id)){
			roomsHas=qunuanManageService.getRoomHas(id);
		}
		List<QunuanfeiDetailDO> qunuanfeiDetailDOList=new ArrayList<>();
		QunuanfeiDetailDO qunuanfeiDetailDO=null;
		for (int j = 1; j <= index; j++) {
			if (repeat > 0) {
				break;
			}
			int rowNum = j + 1;
			nRow = sheetAt.getRow(j);
			qunuanfeiDetailDO=new QunuanfeiDetailDO();
			if((nRow.getCell(0) == null|| getCellValue(nRow.getCell(0)).equals(""))&&(nRow.getCell(1) == null|| getCellValue(nRow.getCell(1)).equals(""))&&(nRow.getCell(2) == null|| getCellValue(nRow.getCell(2)).equals(""))){
				break;
			}
			if (nRow.getCell(0) == null || getCellValue(nRow.getCell(0)).equals("")) {
				sb.append("第" + rowNum + "行没有输入楼宇编号");
				repeat++;
				break;
			}else{
				qunuanfeiDetailDO.setBuildingId(Long.valueOf(getCellValue(nRow.getCell(0))));
			}
			if (nRow.getCell(1) == null || getCellValue(nRow.getCell(1)).equals("")) {
				sb.append("第" + rowNum + "行没有输入房屋编号");
				repeat++;
				break;
			}else{
				String room=""+qunuanfeiDetailDO.getBuildingId()+"-"+getCellValue(nRow.getCell(1));
				boolean isContains =UploadUtils.checkHas(room,rooms);
				if(!isContains){
					sb.append("第" + rowNum + "行不存在该房屋");
					repeat++;
					break;
				}
				if(StringUtils.isNotBlank(id)){
					isContains = UploadUtils.checkHas(room,roomsHas);
					if(isContains){
						sb.append("第" + rowNum + "行房屋在当前收费周期已有记录");
						repeat++;
						break;
					}
				}
				qunuanfeiDetailDO.setRoomCode(getCellValue(nRow.getCell(1)));
			}
			if (nRow.getCell(2) == null || getCellValue(nRow.getCell(2)).equals("")) {
				sb.append("第" + rowNum + "行没有输入层高");
				repeat++;
				break;
			}else{
				qunuanfeiDetailDO.setHeight(Double.valueOf(getCellValue(nRow.getCell(2))));
			}
			if (nRow.getCell(3) == null || getCellValue(nRow.getCell(3)).equals("")) {
				sb.append("第" + rowNum + "行没有输入系数");
				repeat++;
				break;
			}else{
				qunuanfeiDetailDO.setRatio(Double.valueOf(getCellValue(nRow.getCell(3))));
			}
			if (nRow.getCell(4) == null || getCellValue(nRow.getCell(4)).equals("")) {
				sb.append("第" + rowNum + "行没有输入单价");
				repeat++;
				break;
			}else{
				qunuanfeiDetailDO.setPrice(Double.valueOf(getCellValue(nRow.getCell(4))));
			}
			if (nRow.getCell(5) == null || getCellValue(nRow.getCell(5)).equals("")) {
				Double ratio=qunuanfeiDetailDO.getRatio();
				Double p= Double.valueOf(formatDouble4(qunuanfeiDetailDO.getPrice()*ratio));
				qunuanfeiDetailDO.setP(p);
			}else{
				qunuanfeiDetailDO.setQunuanUnpay(Double.valueOf(getCellValue(nRow.getCell(5))));
				qunuanfeiDetailDO.setQunuanYing(Double.valueOf(getCellValue(nRow.getCell(5))));
				qunuanfeiDetailDO.setQunuanPayed(Double.valueOf(0));
			}
			if (nRow.getCell(6) == null || getCellValue(nRow.getCell(6)).equals("")) {
				sb.append("第" + rowNum + "行没有输入停暖费");
				repeat++;
				break;
			}else{
				qunuanfeiDetailDO.setTingnuanUnpay(Double.valueOf(getCellValue(nRow.getCell(6))));
				qunuanfeiDetailDO.setTingnuanYing(Double.valueOf(getCellValue(nRow.getCell(6))));
				qunuanfeiDetailDO.setTingnuanPay(Double.valueOf(0));
			}
			qunuanfeiDetailDO.setStartDate(startDate);
			qunuanfeiDetailDO.setStartYear(start);
			qunuanfeiDetailDO.setEndDate(endDate);
			qunuanfeiDetailDO.setEndYear(end);
			qunuanfeiDetailDO.setState("0");
			qunuanfeiDetailDO.setDeptId(deptId);
			qunuanfeiDetailDOList.add(qunuanfeiDetailDO);
		}
		if (repeat > 0) {
			return R.error(sb.toString());
		} else {
			if(qunuanfeiDetailDOList.size()>0){
				QunuanfeiDO qunuanfeiDO=new QunuanfeiDO();
				if(StringUtils.isNotBlank(id)){
					qunuanfeiDO.setId(Long.valueOf(id));
					qunuanfeiDO.setName(name);
					qunuanfeiDO.setStartDate(startDate);
					qunuanfeiDO.setStartYear(start);
					qunuanfeiDO.setEndDate(endDate);
					qunuanfeiDO.setEndYear(end);
					qunuanManageService.update(qunuanfeiDO);
				}else {
					qunuanfeiDO.preInsert();
					qunuanfeiDO.setStartDate(startDate);
					qunuanfeiDO.setStartYear(start);
					qunuanfeiDO.setEndDate(endDate);
					qunuanfeiDO.setEndYear(end);
					qunuanfeiDO.setDeptId(deptId);
					qunuanfeiDO.setName(name);
					qunuanfeiDO.setState("0");
					qunuanfeiDO.setUnpay(qunuanfeiDetailDOList.size());
					qunuanfeiDO.setPayed(0);
					qunuanManageService.save(qunuanfeiDO);
				}
				qunuanfeiDO.setQunuanfeiDetailDOList(qunuanfeiDetailDOList);
				int i=qunuanManageService.batchInsert(qunuanfeiDO);
				qunuanManageService.updateCount(qunuanfeiDO.getId());
				if(i>0){
					return R.ok("导入"+i+"条数据");
				}else{
					R.error();
				}
			}else {
				return R.error("没有能导入的取暖费信息");
			}
		}
		return R.error();
	}
	public  String formatDouble4(double d) {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(d);
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
}
