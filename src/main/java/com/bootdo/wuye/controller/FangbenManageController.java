package com.bootdo.wuye.controller;

import com.bootdo.base.domain.OwnerDO;
import com.bootdo.base.service.OwnerService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.PayListDO;
import com.bootdo.common.domain.Tree;
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
import com.bootdo.wuye.domain.FangbenDO;
import com.bootdo.wuye.service.FangbenManageService;
import com.bootdo.wuye.service.FangbenService;
import com.bootdo.wuye.service.QunuanManageService;
import com.bootdo.wuye.service.WuyefeiManageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 房间管理
 *
 */

@Controller
@RequestMapping("/wuye/fangbenManage")
public class FangbenManageController extends BaseController {
	private String prefix = "wuye/fangbenManage";
	@Autowired
	private FangbenManageService fangbenManageService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private OwnerService ownerService;

	@Autowired
	private BuildingService buildingService;
	@Autowired
	private RoomContractService contractService;
	@Autowired
	private WuyefeiManageService wuyefeiManageService;
	@Autowired
	private RoomContractService roomContractService;
	@Autowired
	private QunuanManageService qunuanManageService;
	@GetMapping()
	@RequiresPermissions("wuye:fangbenManage:fangbenfei")
	String fangbenDO() {
		return prefix + "/manage";
	}
	@GetMapping("/fangbenPay")
	@RequiresPermissions("wuye:fangbenManage:fangbenPay")
	String fangbenPay() {
		return "wuye/fangbenPay/fangbenPay";
	}
	@GetMapping("/payIndex")
	@RequiresPermissions("wuye:fangbenPay:payIndex")
	String payIndex(){
		return "wuye/pay/fangbenPay";
	}
	@GetMapping("/print/{id}")
	@RequiresPermissions("wuye:fangbenPay:print")
	String print(@PathVariable("id") Long id, Model model){
		PayListDO payListDO=qunuanManageService.getPay(id);
		FangbenDO fangbenDO=fangbenManageService.get(payListDO.getYewuId());
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
		return "wuye/print/fangbenfei";
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
		List<FangbenDO> fangbenDOList = fangbenManageService.list(query);
		int total = fangbenManageService.count(query);
		PageUtils pageUtils = new PageUtils(fangbenDOList, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/listPay")
	public PageUtils listPay(@RequestParam Map<String, Object> params) {
		//查询列表数据
		UserDO userDO= ShiroUtils.getUser();
		Query query = new Query(params);
		query.put("deptId",userDO.getDeptId());
		List<FangbenDO> fangbenDOList = fangbenManageService.listPay(query);
		int total = fangbenManageService.countPay(query);
		PageUtils pageUtils = new PageUtils(fangbenDOList, total);
		return pageUtils;
	}
	@GetMapping("/add")
	@RequiresPermissions("wuye:fangbenManage:add")
	String add(Model model) {
		return  prefix + "/add";
	}
	@GetMapping("/info")
	String info(@RequestParam Map<String, Object> params,Model model){
		params.put("deptId",ShiroUtils.getUser().getDeptId());
		List<RoomDO> roomDOList= roomService.list(params);
		if(roomDOList.size()>0){
			model.addAttribute("room", roomDOList.get(0));
			RoomContractDO roomContractDO=roomContractService.getByRoomId(roomDOList.get(0).getId());
			OwnerDO ownerDO=new OwnerDO();
			ownerDO.setOwnerName(roomContractDO.getName());
			ownerDO.setPhone(roomContractDO.getPhone());
			ownerDO.setIdCard(roomContractDO.getIdCard());
			model.addAttribute("owner", ownerDO);
		}
		model.addAttribute("types", params.get("types"));
		return  "wuye/info/info";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("wuye:fangbenManage:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		FangbenDO fangbenDO = fangbenManageService.get(id);
		model.addAttribute("fangbenDO", fangbenDO);
		return  prefix + "/edit";
	}

	@GetMapping("/pay/{id}")
	@RequiresPermissions("wuye:fangbenManage:pay")
	String pay(@PathVariable("id") Long id, Model model) {
		FangbenDO fangbenDO = fangbenManageService.get(id);
		model.addAttribute("fangbenDO", fangbenDO);
		model.addAttribute("name", ShiroUtils.getUser().getName());
        Date date=new Date();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-yy");
        String d=sdf.format(date);
        model.addAttribute("date", d);
		return  "wuye/fangbenPay/shou";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save(HttpRequest request) {
		UserDO userDO=ShiroUtils.getUser();
		FangbenDO fangbenDO=new FangbenDO();
		fangbenDO.setDeptId(userDO.getDeptId());
		fangbenDO.preInsert();
		fangbenManageService.removeNotPay(fangbenDO);
		fangbenManageService.save(fangbenDO);
		return R.ok();
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
		FangbenDO fangbenDO = fangbenManageService.get(payListDO.getYewuId());
		RoomDO roomDO=roomService.get(fangbenDO.getRoomId());
		Map<String, Object> params=new HashedMap();
		params.put("roomId",roomDO.getId());
		params.put("deptId",ShiroUtils.getUser().getDeptId());
		RoomContractDO roomContractDO=contractService.list(params).get(0);
		UserDO userDO=ShiroUtils.getUser();
		DeptDO deptDO=deptService.get(userDO.getDeptId());
		String payId=wuyefeiManageService.getId("WY");
		BuildingDO buildingDO=buildingService.get(roomDO.getBuildingId());
		BuildingDO parent=buildingService.get(buildingDO.getParentId());
		//缴费信息
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
		payListDO.setRoomCode(roomDO.getCode());
		payListDO.setUnit(roomDO.getUnit());
		payListDO.setFloor(roomDO.getFloor());
		payListDO.setPrint(0);
		payListDO.setArea(Long.valueOf(deptDO.getArea()));
		payListDO.preInsert();
		//房本费
		FangbenDO f=new FangbenDO();
		f.setId(payListDO.getYewuId());
		f.preUpdate();
		String payType=fangbenDO.getPayType();
		String jiaoType=payListDO.getsType();
		Double price=payListDO.getPrice();
		if(jiaoType.equals("4")){
			Double payed=fangbenDO.getWeixiuPayer()+price;
			Double ying=fangbenDO.getWeixiuYing();
			Double unpay=ying-payed;
			f.setWeixiuPayer(payed);
			f.setWeixiuUnpay(unpay);
		}
		if(jiaoType.equals("5")){
			if(payType.equals("1")){
				Double payed=fangbenDO.getJiaoyiPayer();
				Double ying = fangbenDO.getQkjiaoyi();
				Double unpay= Double.valueOf(0);
				if(payed==0){
					payed=price;
					unpay=ying-payed;
				}else {
					payed+=price;
					unpay=ying-payed;
				}
				f.setJiaoyiPayer(payed);
				f.setJiaoyiUnpay(unpay);
				f.setJiaoyiYing(ying);
			}else{
				Double payed=fangbenDO.getJiaoyiPayer()+price;
				Double ying=fangbenDO.getJiaoyiYing();
				Double unpay=ying-payed;
				f.setJiaoyiPayer(payed);
				f.setJiaoyiUnpay(unpay);
			}
		}
		if(jiaoType.equals("6")){
			if(payType.equals("1")){
				Double payed=fangbenDO.getFangbenPayer();
				Double ying = fangbenDO.getQkFangben();
				Double unpay= Double.valueOf(0);
				if(payed==0){
					payed=price;
					unpay=ying-payed;
				}else {
					payed+=price;
					unpay=ying-payed;
				}
				f.setFangbenPayer(payed);
				f.setFangbenUnpay(unpay);
				f.setFangbenYing(ying);
			}else{
				Double payed=fangbenDO.getFangbenPayer()+price;
				Double ying=fangbenDO.getFangbenYing();
				Double unpay=ying-payed;
				f.setFangbenPayer(payed);
				f.setFangbenUnpay(unpay);
			}
		}
		if(jiaoType.equals("7")){
			if(payType.equals("1")){
				Double payed=fangbenDO.getQishuiPayer();
				Double ying = fangbenDO.getQkQishui();
				Double unpay= Double.valueOf(0);
				if(payed==0){
					payed=price;
					unpay=ying-payed;
				}else {
					payed+=price;
					unpay=ying-payed;
				}
				f.setQishuiPayer(payed);
				f.setQishuiUnpay(unpay);
				f.setQishuiYing(ying);
				Double jiaoyiYing=fangbenDO.getJiaoyiYing();
				if(jiaoyiYing<1){
					f.setJiaoyiYing(fangbenDO.getQkjiaoyi());
					f.setJiaoyiUnpay(fangbenDO.getQkjiaoyi());
					f.setJiaoyiPayer(Double.valueOf(0));
				}
				Double fangbenYing=fangbenDO.getFangbenYing();
				if(fangbenYing<1){
					f.setFangbenYing(fangbenDO.getQkFangben());
					f.setFangbenUnpay(fangbenDO.getQkFangben());
					f.setFangbenPayer(Double.valueOf(0));
				}
				Double daibanYing=fangbenDO.getDaibanYing();
				if(daibanYing<1){
					f.setDaibanYing(fangbenDO.getQkDaiban());
					f.setDaibanUnpay(fangbenDO.getQkDaiban());
					f.setDaibanPayer(Double.valueOf(0));
				}
			}else{
				Double payed=fangbenDO.getQishuiPayer()+price;
				Double ying=fangbenDO.getQishuiYing();
				Double unpay=ying-payed;
				f.setQishuiPayer(payed);
				f.setQishuiUnpay(unpay);
			}
		}
		if(jiaoType.equals("8")){
			if(payType.equals("1")){
				Double payed=fangbenDO.getDaibanPayer();
				Double ying = fangbenDO.getQkDaiban();
				Double unpay= Double.valueOf(0);
				if(payed==0){
					payed=price;
					unpay=ying-payed;
				}else {
					payed+=price;
					unpay=ying-payed;
				}
				f.setDaibanPayer(payed);
				f.setDaibanUnpay(unpay);
				f.setDaibanYing(ying);
			}else{
				Double payed=fangbenDO.getDaibanPayer()+price;
				Double ying=fangbenDO.getDaibanYing();
				Double unpay=ying-payed;
				f.setDaibanPayer(payed);
				f.setDaibanUnpay(unpay);
			}
		}
		wuyefeiManageService.savePay(payListDO);
		fangbenManageService.update(f);
		return payListDO;
	}
	@PostMapping( "/chongxiao")
	@ResponseBody
	public R chongxiao( Long id){
		PayListDO payListDO=wuyefeiManageService.getPay(id);
		payListDO.setState("2");
		wuyefeiManageService.updatePay(payListDO);
		FangbenDO fangbenDO=fangbenManageService.get(payListDO.getYewuId());
		Double price=payListDO.getPrice()*(-1);
		payListDO.setPrice(price);
		payListDO.setState("1");
		payListDO.setChongxiaoCode(payListDO.getCode());
		String payId=wuyefeiManageService.getId("WY");
		payListDO.setCode(payId);
		payListDO.preInsert();
		String sType=payListDO.getSType();
		Double receive=Double.valueOf(0);
		Double total=Double.valueOf(0);
		if(sType.equals("4")){
			total=fangbenDO.getWeixiuYing();
			receive=fangbenDO.getWeixiuPayer();
			fangbenDO.setWeixiuPayer(receive+price);
			fangbenDO.setWeixiuUnpay(total-receive-price);
		}
		if(sType.equals("5")){
			total=fangbenDO.getJiaoyiYing();
			receive=fangbenDO.getJiaoyiPayer();
			fangbenDO.setJiaoyiPayer(receive+price);
			fangbenDO.setJiaoyiUnpay(total-receive-price);
		}
		if(sType.equals("6")){
			total=fangbenDO.getFangbenYing();
			receive=fangbenDO.getFangbenPayer();
			fangbenDO.setFangbenPayer(receive+price);
			fangbenDO.setFangbenUnpay(total-receive-price);
		}
		if(sType.equals("7")){
			total=fangbenDO.getQishuiYing();
			receive=fangbenDO.getQishuiPayer();
			fangbenDO.setQishuiPayer(receive+price);
			fangbenDO.setQishuiUnpay(total-receive-price);
		}
		if(sType.equals("8")){
			total=fangbenDO.getDaibanYing();
			receive=fangbenDO.getDaibanPayer();
			fangbenDO.setDaibanPayer(receive+price);
			fangbenDO.setDaibanUnpay(total-receive-price);
		}
		fangbenDO.preUpdate();
		fangbenManageService.update(fangbenDO);
		if(wuyefeiManageService.savePay(payListDO)>0){
			return R.ok();
		}
		return R.error();
	}
	@GetMapping("/payList")
	@ResponseBody
	public PageUtils payList(@RequestParam Map<String, Object> params){
		UserDO userDO=ShiroUtils.getUser();
		params.put("deptId",userDO.getDeptId());
		params.put("types","fangben");
		//查询列表数据
		Query query = new Query(params);
		List<PayListDO> payListDOs = fangbenManageService.pay(query);
		int total = fangbenManageService.countPayList(query);
		PageUtils pageUtils = new PageUtils(payListDOs, total);
		return pageUtils;
	}
	/*缴费详情*/
	@GetMapping("/payListInfo")
	@ResponseBody
	public PageUtils payListInfo(@RequestParam Map<String, Object> params){
		UserDO userDO=ShiroUtils.getUser();
		params.put("deptId",userDO.getDeptId());
		//查询列表数据
		Query query = new Query(params);
		List<PayListDO> payListDOs = fangbenManageService.pay(query);
		int total = fangbenManageService.countPayList(query);
		PageUtils pageUtils = new PageUtils(payListDOs, total);
		return pageUtils;
	}
	/**
	 * 合并
	 */
	@ResponseBody
	@PostMapping("/batchSave")
	public R batchSave(HttpServletRequest request) throws IOException {
        String jsonStr = request.getParameter("mydata");
        ObjectMapper mapper = new ObjectMapper();
        FangbenDO FangbenDO = mapper.readValue(jsonStr, FangbenDO.class);
        return R.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("wuye:fangbenManage:edit")
	public R update(FangbenDO fangbenDO) {
		FangbenDO f=fangbenManageService.get(fangbenDO.getId());
		if((f.getDaibanPayer()+f.getFangbenPayer()+f.getJiaoyiPayer()+f.getWeixiuPayer()+f.getQishuiPayer())>0){
			return R.error("已缴费，不能修改");
		}
		fangbenDO.preUpdate();
		if (fangbenManageService.update(fangbenDO) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("wuye:fangbenManage:remove")
	public R remove(Long id) {
		FangbenDO fangbenDO=fangbenManageService.get(id);
		if((fangbenDO.getDaibanPayer()+fangbenDO.getFangbenPayer()+fangbenDO.getJiaoyiPayer()+fangbenDO.getWeixiuPayer()+fangbenDO.getQishuiPayer())>0){
			return R.error("已缴费，不能删除");
		}
		int i=fangbenManageService.remove(id);
		if(i>0){
			R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("wuye:fangbenManage:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		fangbenManageService.batchRemove(ids);
		return R.ok();
	}
}
