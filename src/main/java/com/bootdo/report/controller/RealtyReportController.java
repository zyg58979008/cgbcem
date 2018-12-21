package com.bootdo.report.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.realty.domain.RoomContractDO;
import com.bootdo.realty.service.RoomContractService;
import com.bootdo.realty.service.RoomService;
import com.bootdo.report.domain.Payment;
import com.bootdo.report.domain.ReportDO;
import com.bootdo.report.domain.RoomContractMoneyDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.wuye.service.WuyefeiManageService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * 房间管理
 *
 */

@Controller
@RequestMapping("/realtyReport")
public class RealtyReportController extends BaseController {
	private String prefix = "report/realty";
	@Autowired
	private RoomService roomService;
	@Autowired
	private RoomContractService roomContractService;
	@GetMapping("/loukuan")
	@RequiresPermissions("report:realty:loukuan")
	String loukuan(Model model) {
		return prefix + "/loukuan";
	}
	@GetMapping("/payment")
	@RequiresPermissions("report:realty:payment")
	String payment(Model model) {
		return prefix + "/payment";
	}
	@GetMapping("/contract")
	@RequiresPermissions("report:realty:contract")
	String contract(Model model) {
		return prefix + "/contract";
	}
	@GetMapping("/loukuanList")
	@ResponseBody
	List<RoomContractDO> loukuanList(Model model) {
		Long deptId=ShiroUtils.getUser().getDeptId();
		List<RoomContractDO> roomContractDOS=roomService.getLoukuan(deptId);
		return roomContractDOS;
	}
	@GetMapping("/paymentList")
	@ResponseBody
	List<Payment> paymentList(@RequestParam Map<String, Object> params) {
		Long deptId=ShiroUtils.getUser().getDeptId();
		params.put("deptId",deptId);
		List<Payment> payments=roomContractService.paymentList(params);
		return payments;
	}
	@ApiOperation(value="获取房间列表", notes="")
	@ResponseBody
	@GetMapping("/contractList")
	public List<RoomContractMoneyDO> contractList(@RequestParam Map<String, Object> params) {
		//查询列表数据
		UserDO userDO= ShiroUtils.getUser();
		String startDate= (String) params.get("startDate");
		String endDate= (String) params.get("endDate");
		params.put("deptId",userDO.getDeptId());
		List<ReportDO> reportDOs = new ArrayList<>();
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
		params.put("reportDOs",reportDOs);
		List<RoomContractMoneyDO> roomContractMoneyDOs = roomContractService.contractList(params);
		return roomContractMoneyDOs;
	}
	@GetMapping("/shopAmortize")
	@RequiresPermissions("report:shopAmortize")
	String shopAmortize(Model model) {
		Calendar calendar=Calendar.getInstance();
		model.addAttribute("year",String.valueOf(calendar.get(Calendar.YEAR)));
		return prefix + "/shopAmortize";
	}
}
