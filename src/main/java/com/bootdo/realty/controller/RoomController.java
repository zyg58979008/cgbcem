package com.bootdo.realty.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.*;
import com.bootdo.realty.domain.RoomDO;
import com.bootdo.realty.service.RoomContractService;
import com.bootdo.realty.service.RoomService;
import com.bootdo.system.domain.UserDO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpRequest;
import org.apache.poi.hssf.usermodel.*;
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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 房间管理
 *
 */

@Controller
@RequestMapping("/realty/room")
public class RoomController extends BaseController {
	private String prefix = "realty/room";
	private String prefix1 = "realty/shop";
	@Autowired
	private RoomService roomService;
	@Resource
	private ResourceLoader resourceLoader;
	@Autowired
	private RoomContractService contractService;
	@Autowired
	private DictService dictService;
	@GetMapping()
	@RequiresPermissions("realty:room:room")
	String room() {
		return prefix + "/room";
	}
	@GetMapping("/shop")
	@RequiresPermissions("realty:room:shop")
	String shop() {
		return prefix + "/shop";
	}
	@ApiOperation(value="获取房间列表", notes="")
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		//查询列表数据
		UserDO userDO= ShiroUtils.getUser();
		Query query = new Query(params);
		query.put("deptId",userDO.getDeptId());
		query.put("delFlag","0");
		List<RoomDO> roomList = roomService.list(query);
		int total = roomService.count(query);
		PageUtils pageUtils = new PageUtils(roomList, total);
		return pageUtils;
	}
	@ResponseBody
	@PostMapping("/getSum")
	String getSum(@RequestParam Map<String, Object> params) {
		params.put("deptId",ShiroUtils.getUser().getDeptId());
		String sum = roomService.getSum(params);
		return sum;
	}
	@GetMapping("/add")
	@RequiresPermissions("realty:room:add")
	String add(Model model) {
		return  prefix + "/add";
	}

	@GetMapping("/merge")
	@RequiresPermissions("realty:room:merge")
	String merge(Model model) {
		return  prefix + "/merge";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("realty:room:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		RoomDO room = roomService.get(id);
		model.addAttribute("room", room);
		return  prefix + "/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("realty:room:add")
	public R save(RoomDO room, HttpRequest request) {
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
        RoomDO roomDO = mapper.readValue(jsonStr, RoomDO.class);
		UserDO userDO=ShiroUtils.getUser();
        roomDO.setDelFlag("0");
		int i=contractService.checkRoom(roomDO.getMergeIds());
		//查询是否存在合同
		if(i>0) {
			return R.error(1, "所选房屋已有合同，不能修改");
		}
        int count=roomService.checkCode(roomDO);
		if(count>0){
            return R.error(1, "新增房间编码已存在，请修改");
        }
        roomService.batchRemove(roomDO.getMergeIds());
        for(RoomDO r:roomDO.getRoomDOList()){
            roomService.save(r);
        }
        return R.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("realty:room:edit")
	public R update(RoomDO room) {
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
	@RequiresPermissions("realty:room:remove")
	public R remove(Long id) {
		Long[] ids=new Long[1];
		ids[0]=id;
		int i=contractService.checkRoom(ids);
		//查询是否存在合同
		if(i>0) {
			return R.error(1, "该房屋已有合同，不能删除");
		}
		if (roomService.remove(id) > 0) {
			return R.ok();
		}
		else {
			return R.error();
		}
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("realty:room:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		int i=contractService.checkRoom(ids);
		//查询是否存在合同
		if(i>0) {
			return R.error(1, "该房屋已有合同，不能删除");
		}
		if (roomService.batchRemove(ids) > 0) {
			return R.ok();
		}
		else {
			return R.error();
		}
	}

	@GetMapping("/tree/{pId}")
	@ResponseBody
	public Tree<RoomDO> tree(@PathVariable("type") Long type) {
		Tree<RoomDO> tree = new Tree<RoomDO>();
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
		UserDO userDO=ShiroUtils.getUser();
		try {
			String filename = "房屋信息.xls";
			String path = "static/file/room.xls";
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
			return R.error("请填写房屋信息");
		}
		UserDO userDO=ShiroUtils.getUser();
		Long deptId=userDO.getDeptId();
		RoomDO roomDO=null;
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "room_type");
		List<DictDO> dictDOList=dictService.list(map);
		List<RoomDO> roomDOList=new ArrayList<>();
		for (int j = 1; j <= end; j++) {
			if (repeat > 0) {
				break;
			}
			int rowNum = j + 1;
			nRow = sheetAt.getRow(j);
			roomDO=new RoomDO();
			if((nRow.getCell(0) == null|| getCellValue(nRow.getCell(0)).equals(""))&&(nRow.getCell(1) == null|| getCellValue(nRow.getCell(1)).equals(""))&&(nRow.getCell(2) == null|| getCellValue(nRow.getCell(2)).equals(""))){
				break;
			}
			//判断第一个单元格是否为空，如果为空  整行都不插入
			if (nRow.getCell(0) == null ||  nRow.getCell(0).getCellType() != HSSFCell.CELL_TYPE_NUMERIC) {
				sb.append("第" + rowNum + "行没有输入楼宇编号");
				repeat++;
				break;
			}else{
				roomDO.setBuildingId(Long.valueOf(getCellValue((nRow.getCell(0)))));
			}
			if (nRow.getCell(1) == null ||  getCellValue(nRow.getCell(1)).equals("")) {
				sb.append("第" + rowNum + "行没有输入房屋编号");
				repeat++;
				break;
			}else{
				roomDO.setCode(getCellValue(nRow.getCell(1)));
			}
			if (nRow.getCell(2) == null ||  getCellValue(nRow.getCell(2)).equals("")) {
				sb.append("第" + rowNum + "行没有输入单元");
				repeat++;
				break;
			}else{
				roomDO.setUnit(Integer.valueOf(getCellValue(nRow.getCell(2))));
			}
			if (nRow.getCell(3) == null ||   getCellValue(nRow.getCell(3)).equals("")) {
				sb.append("第" + rowNum + "行没有输入楼层");
				repeat++;
				break;
			}else{
				roomDO.setFloor(Integer.valueOf(getCellValue(nRow.getCell(3))));
			}
			if (nRow.getCell(4) == null ||  getCellValue(nRow.getCell(4)).equals("")) {
				sb.append("第" + rowNum + "行没有输入建筑面积");
				repeat++;
			}else{
				roomDO.setBuildingArea(Double.valueOf(getCellValue(nRow.getCell(4))));
			}
			if (nRow.getCell(5) == null ||  getCellValue(nRow.getCell(5)).equals("")) {
				sb.append("第" + rowNum + "行没有选择房屋类型");
				repeat++;
			}else{
				String roomType=getCellValue(nRow.getCell(5));
				roomType=getValue(roomType,dictDOList);
				roomDO.setRoomType(roomType);
			}
			roomDO.setDeptId(deptId);
			roomDO.setDelFlag("0");
			roomDO.setOrderId(orderId);
			roomDOList.add(roomDO);
		}
		if (repeat > 0) {
			return R.error(sb.toString());
		} else {
			if(roomDOList.size()>0){
				int i=roomService.batchInsert(roomDOList);
				if(i>0){
					roomService.duplicate(orderId);
					i=roomService.synchronization(orderId);
					roomService.removeCopy(orderId);
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
	@PostMapping("/dictList")
	public List<DictDO> listByType(String type,String buildingId) {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", type);
		map.put("buildingId", buildingId);
		List<DictDO> dictList = roomService.listByType(map);
		return dictList;
	}
}
