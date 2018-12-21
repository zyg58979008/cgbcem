package com.bootdo.business.controller;

import com.bootdo.business.domain.ShopDO;
import com.bootdo.business.service.ShopService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.*;
import com.bootdo.realty.domain.RoomDO;
import com.bootdo.realty.service.RoomContractService;
import com.bootdo.system.domain.UserDO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpRequest;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 房间管理
 *
 */

@Controller
@RequestMapping("/business/shop")
public class ShopController extends BaseController {
	private String prefix = "business/shop";
	@Autowired
	private ShopService roomService;
	@Resource
	private ResourceLoader resourceLoader;
	@Autowired
	private DictService dictService;
	@Autowired
	private RoomContractService contractService;
	@GetMapping()
	@RequiresPermissions("business:shop:shop")
	String room() {
		return prefix + "/shop";
	}
	@GetMapping("/shop")
	@RequiresPermissions("business:shop:shop")
	String shop() {
		return prefix + "/shop";
	}
	@ApiOperation(value="获取房间列表", notes="")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:shop:shop")
	public List<ShopDO> list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		UserDO userDO= ShiroUtils.getUser();
		params.put("deptId",userDO.getDeptId());
		params.put("delFlag","0");
		List<ShopDO> roomList = roomService.list(params);
		return roomList;
	}
/*
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("business:shop:shop")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		UserDO userDO= ShiroUtils.getUser();
		Query query = new Query(params);
		query.put("deptId",userDO.getDeptId());
		query.put("delFlag","0");
		List<ShopDO> roomList = roomService.list(query);
		int total = roomService.count(query);
		PageUtils pageUtils = new PageUtils(roomList, total);
		return pageUtils;
	}
*/

	@GetMapping("/add")
	@RequiresPermissions("business:shop:add")
	String add(Model model) {
		return  prefix + "/add";
	}

	@GetMapping("/merge")
	@RequiresPermissions("business:shop:merge")
	String merge(Model model) {
		return  prefix + "/merge";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("business:shop:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		ShopDO room = roomService.get(id);
		model.addAttribute("room", room);
		return  prefix + "/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("business:shop:add")
	public R save(ShopDO room, HttpRequest request) {
		UserDO userDO=ShiroUtils.getUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buildingId",room.getBuildingId());
		map.put("code",room.getCode());
		if(roomService.count(map)>0) {
			return R.error(1, "该楼宇下已存在该房间编码");
		}
		if (roomService.save(room) > 0) {
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 合并
	 */
	@ResponseBody
	@PostMapping("/batchSave")
	public R batchSave(HttpServletRequest request) throws IOException {
        String jsonStr = request.getParameter("mydata");
        ObjectMapper mapper = new ObjectMapper();
        ShopDO roomDO = mapper.readValue(jsonStr, ShopDO.class);
        roomDO.setDelFlag("0");
		int i=roomService.checkShopContract(roomDO);
		//查询是否存在合同
		if(i>0) {
			return R.error(1, "所选房屋已有合同，不能修改");
		}
        int count=roomService.checkCode(roomDO);
		if(count>0){
            return R.error(1, "新增房间编码已存在，请修改");
        }
        roomService.batchRemove(roomDO.getMergeIds());
        for(ShopDO r:roomDO.getShopDOList()){
            roomService.save(r);

        }
        return R.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("business:shop:edit")
	public R update(ShopDO room) {
		room.setDelFlag("0");
		if(roomService.checkCode(room)>0){
			return R.error(1, "该楼宇下已存在该房间编码");
		}
		if (roomService.update(room) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("business:shop:remove")
	public R remove(@RequestParam("ids") Long[] ids) {
		ShopDO roomDO=new ShopDO();
		roomDO.setMergeIds(ids);
		roomDO.setDeptId(ShiroUtils.getUser().getDeptId());
		int i=roomService.checkShopContract(roomDO);
		Long o=ids[0];
		if(i>0){
			return R.error("该商铺合同未到期不能删除");
		}
		else {
			roomService.batchRemove(ids);
			return R.ok();
		}
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("business:shop:batchRemove")
	public R remove1(@RequestParam("ids[]") Long[] ids) {
		ShopDO roomDO=new ShopDO();
		roomDO.setMergeIds(ids);
		int i=roomService.checkShopContract(roomDO);
		if(i>0){
			return R.error("选中商铺中还有合同未到期的商铺，不能删除");
		}
		else {
			roomService.batchRemove(ids);
			return R.ok();
		}
	}

	@GetMapping("/tree/{pId}")
	@ResponseBody
	public Tree<ShopDO> tree(@PathVariable("type") Long type) {
		Tree<ShopDO> tree = new Tree<ShopDO>();
		//tree = roomService.getTree(type);
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/areaTree";
	}
	@ResponseBody
	@PostMapping("/exportXls")
	public void downloadTemplate(HttpServletResponse response, HttpServletRequest request) {
		InputStream inputStream = null;
		ServletOutputStream servletOutputStream = null;
		try {
			String filename = "商铺信息.xls";
			String path = "static/file/shop.xls";
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
	R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
		//把表单内容转换成流
		InputStream fileInputStream = file.getInputStream();
		HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
		HSSFSheet sheetAt = workbook.getSheetAt(0);
		Long buildingId=0L;
		String code="";
		Integer unit=0;
		Integer floor=0;
		Double building_area=0d;
		Double floor_area=0d;
		Row nRow = null;
		String sheetName = null;
		String orderId= String.valueOf(System.currentTimeMillis());
		//错误
		int repeat = 0;
		//记录第几行重复
		String num;
		StringBuilder sb = new StringBuilder();
		int end = sheetAt.getLastRowNum();
		if(end==0){
			return R.error("请填写商铺信息");
		}
		UserDO userDO=ShiroUtils.getUser();
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "room_type");
		List<DictDO> dictDOList=dictService.list(map);
		Long deptId=userDO.getDeptId();
		ShopDO shopDO=null;
		List<ShopDO> shopDOList=new ArrayList<>();
		for (int j = 1; j <= end; j++) {
			if (repeat > 0) {
				break;
			}
			int rowNum = j + 1;
			nRow = sheetAt.getRow(j);
			shopDO=new ShopDO();
			if((nRow.getCell(0) == null|| getCellValue(nRow.getCell(0)).equals(""))&&(nRow.getCell(1) == null|| getCellValue(nRow.getCell(1)).equals(""))&&(nRow.getCell(2) == null|| getCellValue(nRow.getCell(2)).equals(""))){
				break;
			}
			//判断第一个单元格是否为空，如果为空  整行都不插入
			if (nRow.getCell(0) == null ||  nRow.getCell(0).getCellType() != HSSFCell.CELL_TYPE_NUMERIC) {
				sb.append("第" + rowNum + "行没有输入楼宇编号");
				repeat++;
				break;
			}else{
				shopDO.setBuildingId(Long.valueOf(getCellValue((nRow.getCell(0)))));
			}
			if (nRow.getCell(1) == null ||  getCellValue(nRow.getCell(1)).equals("")) {
				sb.append("第" + rowNum + "行没有输入房屋编号");
				repeat++;
				break;
			}else{
				shopDO.setCode(getCellValue(nRow.getCell(1)));
			}
			/*if (nRow.getCell(2) == null ||  getCellValue(nRow.getCell(2)).equals("")) {
				sb.append("第" + rowNum + "行没有输入单元");
				repeat++;
				break;
			}else{
				shopDO.setUnit(Integer.valueOf(getCellValue(nRow.getCell(2))));
			}*/
			if (nRow.getCell(2) == null ||   getCellValue(nRow.getCell(2)).equals("")) {
				sb.append("第" + rowNum + "行没有输入楼层");
				repeat++;
				break;
			}else{
				shopDO.setFloor(Integer.valueOf(getCellValue(nRow.getCell(2))));
			}
			/*if (nRow.getCell(3) == null ||  getCellValue(nRow.getCell(3)).equals("")) {
				sb.append("第" + rowNum + "行没有输入建筑面积");
				repeat++;
			}else{
				shopDO.setBuildingArea(Double.valueOf(getCellValue(nRow.getCell(3))));
			}*/
			if (nRow.getCell(4) == null ||  getCellValue(nRow.getCell(4)).equals("")) {
				sb.append("第" + rowNum + "行没有输入实测面积");
				repeat++;
			}else{
				DecimalFormat df   = new DecimalFormat("######0.00");
				shopDO.setFloorArea(Double.valueOf(getCellValue(nRow.getCell(4))));
				shopDO.setBuildingArea(Double.valueOf(df.format(shopDO.getFloorArea()*1.2)));
			}
			if (nRow.getCell(5) == null ||  getCellValue(nRow.getCell(5)).equals("")) {
				sb.append("第" + rowNum + "行没有选择房屋类型");
				repeat++;
			}else{
				String roomType=getCellValue(nRow.getCell(5));
				roomType=getValue(roomType,dictDOList);
				shopDO.setRoomType(roomType);
			}
			shopDO.setDeptId(deptId);
			shopDO.setDelFlag("0");
			shopDO.setOrderId(orderId);
			shopDOList.add(shopDO);
		}
		if (repeat > 0) {
			return R.error(sb.toString());
		} else {
			if(shopDOList.size()>0){
				int i=roomService.batchInsert(shopDOList);
				if(i>0){
					roomService.duplicate(orderId);
					i=roomService.synchronization(orderId);
					return R.ok("导入"+i+"条数据");
				}else{
					R.error();
				}
			}
			/*String flag = ExamApi.insertByExcel(questions);
			error = "成功插入<font color='red' > " + flag + " </font>条数据 失败<font color='red'> " + String.valueOf(Integer.valueOf(questions.size()) - Integer.valueOf(flag)) + " </font>条";*/
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
	@ResponseBody
	@PostMapping("/getArea")
	ShopDO getArea(@RequestParam Map<String, Object> params) {
		params.put("deptId",ShiroUtils.getUser().getDeptId());
		ShopDO shopDO = roomService.getArea(params);
		return shopDO;
	}
}
