package com.bootdo.realty.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.bootdo.base.domain.OwnerDO;
import com.bootdo.base.service.OwnerService;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.PayListDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.*;
import com.bootdo.realty.domain.BuildingDO;
import com.bootdo.realty.domain.RoomContractDO;
import com.bootdo.realty.domain.RoomContractLog;
import com.bootdo.realty.domain.RoomDO;
import com.bootdo.realty.service.BuildingService;
import com.bootdo.realty.service.RoomContractService;
import com.bootdo.realty.service.RoomService;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.DeptService;
import com.bootdo.wuye.domain.WuyeAmortize;
import com.bootdo.wuye.domain.WuyefeiDO;
import com.bootdo.wuye.service.WuyefeiManageService;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
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
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-30 09:05:58
 */
 
@Controller
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/realty/contract")
public class RoomContractController {
	@Autowired
	private RoomContractService contractService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private BuildingService buildingService;
	@Autowired
	private OwnerService ownerService;
	@Autowired
	private DictService dictService;
	@Resource
	private ResourceLoader resourceLoader;
	@Autowired
	private WuyefeiManageService wuyefeiManageService;
	@GetMapping()
	@RequiresPermissions("realty:contract:contract")
	String Contract(){
	    return "realty/contract/contract";
	}
    @GetMapping("/contractPay")
    @RequiresPermissions("realty:contract:contractPay")
    String ContractPay(){
        return "realty/contractPay/contractPay";
    }
	@GetMapping("/payIndex")
	@RequiresPermissions("realty:contract:payIndex")
	String payIndex(){
		return "realty/pay/pay";
	}
	@GetMapping("/print/{id}")
	@RequiresPermissions("realty:pay:print")
	String print(@PathVariable("id") Long id, Model model){
		PayListDO payListDO=contractService.getPay(id);
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
		return "realty/print/fangkuan";
	}
	@GetMapping("/printBack/{id}")
	@RequiresPermissions("realty:pay:print")
	String printBack(@PathVariable("id") Long id, Model model){
		PayListDO payListDO=contractService.getPay(id);
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
		return "realty/print/back";
	}
	@ResponseBody
	@GetMapping("/printCount/{id}")
	R printCount(@PathVariable("id") Long id, Model model) {
		PayListDO payListDO = contractService.getPay(id);
		int print=payListDO.getPrint()+1;
		PayListDO p=new PayListDO();
		p.setId(id);
		p.setPrint(print);
		contractService.updatePay(p);
		return  R.ok();
	}
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("realty:contract:contract")
	public PageUtils list(@RequestParam Map<String, Object> params){
		UserDO userDO=ShiroUtils.getUser();
		params.put("deptId",userDO.getDeptId());
		//查询列表数据
        Query query = new Query(params);
		String buildingId=params.get("buildingId").toString();
		if(StringUtils.isNotBlank(buildingId)){
			String[] ids=deptService.getDeptIds(Long.valueOf(params.get("buildingId").toString()));
			query.put("ids",ids);
		}
		List<RoomContractDO> contractList = contractService.list(query);
		int total = contractService.count(query);
		PageUtils pageUtils = new PageUtils(contractList, total);
		return pageUtils;
	}
    @ResponseBody
    @GetMapping("/listPay")
    @RequiresPermissions("realty:contract:contractPay")
    public PageUtils listPay(@RequestParam Map<String, Object> params){
        UserDO userDO=ShiroUtils.getUser();
        params.put("deptId",userDO.getDeptId());
        //查询列表数据
        Query query = new Query(params);
		String buildingId=params.get("buildingId").toString();
		if(StringUtils.isNotBlank(buildingId)){
			String[] ids=deptService.getDeptIds(Long.valueOf(params.get("buildingId").toString()));
			query.put("ids",ids);
		}
        List<RoomContractDO> contractList = contractService.listPay(query);
        int total = contractService.countPay(query);
        PageUtils pageUtils = new PageUtils(contractList, total);
        return pageUtils;
    }
	@GetMapping("/add")
	@RequiresPermissions("realty:contract:add")
	String add(){
	    return "realty/contract/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("realty:contract:edit")
	String edit(@PathVariable("id") Long id,Model model){
		RoomContractDO contract = contractService.getByRoomId(id);
		model.addAttribute("contract", contract);
	    return "realty/contract/edit";
	}
	@GetMapping("/jiaofangIndex/{id}")
	String jiaofangIndex(@PathVariable("id") Long id,Model model){
		model.addAttribute("id", id);
		return "realty/contract/jiaofangIndex";
	}
	@GetMapping("/shou/{id}")
	@RequiresPermissions("realty:contract:shou")
	String pay(@PathVariable("id") Long id,Model model){
		RoomContractDO contract = contractService.get(id);
		model.addAttribute("contract", contract);
		Date date=new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		String d=sdf.format(date);
		model.addAttribute("date", d);
		model.addAttribute("name", ShiroUtils.getUser().getName());
		return "realty/contractPay/shou";
	}
	@ResponseBody
	@PostMapping("/getPaySum")
	WuyefeiDO getPaySum(@RequestParam Map<String, Object> params) {
		params.put("deptId",ShiroUtils.getUser().getDeptId());
		WuyefeiDO wuyefeiDO = contractService.getPaySum(params);
		return wuyefeiDO;
	}
	@GetMapping("/fu/{id}")
	@RequiresPermissions("realty:contract:shou")
	String fu(@PathVariable("id") Long id,Model model){
		RoomContractDO contract = contractService.getByRoomId(id);
		model.addAttribute("contract", contract);
		Date date=new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		String d=sdf.format(date);
		model.addAttribute("date", d);
		model.addAttribute("name", ShiroUtils.getUser().getName());
		return "realty/contractPay/fu";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("realty:contract:add")
	public R save( RoomContractDO contract){
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
	@RequiresPermissions("realty:contract:edit")
	public R update( RoomContractDO contract){
		RoomContractDO r=contractService.getByRoomId(contract.getRoomId());
		/*if(r.getYishou()!=null&&(r.getYishou()-r.getDepositPayed()-r.getRenchouPayed())>0){
			return R.error("已有房款缴费信息，不能修改");
		}*/
		Double balanceArea=contract.getBalanceArea();
		Double ba=r.getBalanceArea();
		contract.setBuildingArea(r.getBuildingArea());
		boolean updateRoom=false;
		RoomDO room=new RoomDO();
		room.setId(r.getRoomId());
		if(balanceArea!=null){
			//判断退补面积是否有改变，有改变更新退补房款，当前版本退补房款和总房款没有关系
			if(r.getDeptId()!=426){
				contract.setBuildingArea(r.getBuildingArea()+balanceArea);
				room.setBuildingArea(contract.getBuildingArea());
				updateRoom=true;
			}
			contract.setBalancePrice(Double.valueOf(Math.round(balanceArea*contract.getPrice())));
		}
		Double price=contract.getPrice();
		Double isTotal=contract.getTotalPrice1();//是否以总房款为准
		if(isTotal!=null&&isTotal>0){
			Double oldTotal=r.getTotalPrice();
			Double newTotal=contract.getTotalPrice();
			if(oldTotal!=null&&!oldTotal.equals(isTotal)){
				contract.setTotalPrice(isTotal);
				contract.setTotalPriceNoTax(Double.valueOf(Math.round(isTotal/1.1)));
				contract.setTax(Double.valueOf(Math.round(isTotal/1.1*0.1)));
				Double buildArea=contract.getBuildingArea();
				price= Double.valueOf(formatDouble4(isTotal/buildArea));
				contract.setPrice(price);
				contract.setWeishou(contract.getTotalPrice()-r.getYishou());
			}
		}else {
			if(price!=null&&price>0){
				Double p=r.getPrice();
				if(p==null){
					Double totalPrice= Double.valueOf(Math.round(contract.getBuildingArea()*contract.getPrice()));
					contract.setTotalPrice(totalPrice);
					contract.setTotalPriceNoTax(Double.valueOf(Math.round(totalPrice/1.1)));
					contract.setTax(Double.valueOf(Math.round(totalPrice/1.1*0.1)));
					contract.setWeishou(contract.getTotalPrice()-r.getYishou());
				}else{
					Double totalPrice= Double.valueOf(Math.round(contract.getBuildingArea()*contract.getPrice()));
					contract.setTotalPrice(totalPrice);
					contract.setTotalPriceNoTax(Double.valueOf(Math.round(totalPrice/1.1)));
					contract.setTax(Double.valueOf(Math.round(totalPrice/1.1*0.1)));
					contract.setWeishou(contract.getTotalPrice()-r.getYishou());
				}
			}
		}
		String oldPayType=r.getPayType();
		String newPayType=contract.getPayType();
		//先判断是否有值
		if(newPayType!=null){
			//oldPayType为空说明是第一次选支付方式
			if (oldPayType == null||oldPayType.equals("")) {
				Double firstPay=contract.getFirstPay();
				//2为贷款
				if(newPayType.equals("2")&&firstPay!=null&&firstPay>0){
					contract.setFirstReceive(Double.valueOf(0)+r.getDepositPayed()+r.getRenchouPayed());
					contract.setFirstOwn(firstPay-contract.getFirstReceive());
					contract.setLoan(contract.getTotalPrice()-firstPay);
				}
				if(newPayType.equals("1")){

				}
			}else {
				if((oldPayType.equals("1")&&newPayType.equals("2"))){
					Double firstPay=contract.getFirstPay();
					if(firstPay!=null){
						contract.setFirstReceive(r.getDepositPayed()+r.getRenchouPayed());
						contract.setFirstOwn(firstPay-contract.getFirstReceive());
						if(contract.getTotalPrice()==null){
							contract.setLoan(r.getTotalPrice()-firstPay);
						}else {
							contract.setLoan(contract.getTotalPrice()-firstPay);
						}
					}
				}
				if(oldPayType.equals("2")&&newPayType.equals("2")){
					Double firstPay=contract.getFirstPay();
					if(firstPay!=null){
						contract.setFirstReceive(r.getFirstReceive());
						contract.setFirstOwn(firstPay-contract.getFirstReceive());
						if(contract.getTotalPrice()==null){
							contract.setLoan(r.getTotalPrice()-firstPay);
						}else {
							contract.setLoan(contract.getTotalPrice()-firstPay);
						}
					}
				}
				if(oldPayType.equals("2")&&newPayType.equals("1")){
					//contract.setFirstReceive(Double.valueOf(0)+contract.getDepositPayed()+contract.getRenchouPayed());
					//contract.setFirstOwn(contract.getFirstPay()-contract.getFirstReceive());
					contract.setFirstReceive(Double.valueOf(0));
					contract.setFirstOwn(Double.valueOf(0));
					contract.setFirstPay(Double.valueOf(0));
					contract.setLoan(Double.valueOf(0));
				}
			}
		}
		if(contract.getId()!=null){
			String orderId= String.valueOf(System.currentTimeMillis());
			contract.setOrderId(orderId);
			RoomContractLog roomContractLog=new RoomContractLog();
			roomContractLog.setName("合同修改");
			roomContractLog.setOrderId(orderId);
			roomContractLog.preInsert();
			room.setSellType(contract.getSellType());
			roomService.update(room);
			contractService.saveLog(roomContractLog);
			contractService.update(contract);
		}else {
			contract.setRoomId(r.getRoomId());
			contract.setRoomCode(r.getRoomCode());
			contract.setDeptId(r.getDeptId());
			contract.setBuildingId(r.getBuildingId());
			contract.setBuildingArea(contract.getBuildingArea());
			contract.setYishou(Double.valueOf(0));
			contract.setUnit(r.getUnit());
			contract.setFloor(r.getFloor());
			contract.setRoomType(r.getRoomType());
			Double totalPrice=contract.getTotalPrice();
			if(totalPrice!=null){
				contract.setWeishou(contract.getTotalPrice());
			}
			else {
				contract.setWeishou(Double.valueOf(0));
			}
			contract.setDepositPayed(Double.valueOf(0));
			contract.setRenchouPayed(Double.valueOf(0));
			contract.setState("0");
			contract.preInsert();
			String orderId= String.valueOf(System.currentTimeMillis());
			contract.setOrderId(orderId);
			contractService.save(contract);
			room.setContractId(contract.getId());
			room.setSellType(contract.getSellType());
			roomService.update(room);
			RoomContractLog roomContractLog=new RoomContractLog();
			roomContractLog.setName("合同录入");
			roomContractLog.setContractId(contract.getId());
			roomContractLog.preInsert();
			contractService.savePayLog(roomContractLog);
		}
		return R.ok();
	}
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("realty:contract:remove")
	public R remove( Long id){
		RoomContractDO roomContractDO=contractService.get(id);
		/*if(roomContractDO.getYishou()>0){
			return R.error("已有缴费信息，不能删除");
		}*/
		if(contractService.remove(id)>0){
			roomService.updateRoom(roomContractDO.getRoomId());
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 交房
	 */
	@PostMapping( "/jiaofang")
	@ResponseBody
	@RequiresPermissions("realty:contract:jiaofang")
	public R jiaofang(HttpServletRequest request) throws IOException {
		String jsonStr = request.getParameter("mydata");
		ObjectMapper mapper = new ObjectMapper();
		RoomContractDO rc = mapper.readValue(jsonStr, RoomContractDO.class);
		int i=0;
		try {
			RoomContractDO roomContractDO=contractService.get(rc.getId());
			if(roomContractDO.getWeishou()>0){
                return R.error("还有未缴清房款不能交房");
            }
			Map<String, Object> params=new HashedMap();
			Calendar now = Calendar.getInstance();
			now.setTime(rc.getJiaofangDate());
			int year=now.get(Calendar.YEAR);
			int month=now.get(Calendar.MONTH);
			int d=now.get(Calendar.DATE);
			params.put("delFlag","0");
			Long deptId=ShiroUtils.getUser().getDeptId();
			params.put("deptId",deptId);
			params.put("start",""+year);
			List<WuyefeiDO> wuyefeiDOList=wuyefeiManageService.list(params);
			if(wuyefeiDOList.size()>0){
                WuyefeiDO wuyefei=wuyefeiDOList.get(0);
                Calendar start=Calendar.getInstance();
                start.set(year,month,d);
                start.add(Calendar.DATE,1);
                Calendar end=Calendar.getInstance();
                end.set(year,month,d);
				end.add(Calendar.DATE,1);
				end.add(Calendar.MONTH,12);
                end.add(Calendar.DATE,-1);
                wuyefei.setMonth(DateUtils.getMonths(start.getTime(),end.getTime()));
				wuyefei.setRoomId(roomContractDO.getRoomId());
				RoomContractDO contractDO=new RoomContractDO();
				contractDO.setId(rc.getId());
				contractDO.setRoomId(roomContractDO.getRoomId());
				contractDO.setState("1");
				contractDO.setJiaofangDate(new Date());
				contractDO.preUpdate();
				i = contractService.update(contractDO);
                RoomDO r=roomService.get(roomContractDO.getRoomId());
				wuyefei.setStartDate(start.getTime());
				wuyefei.setEndDate(end.getTime());
				wuyefei.setStartYear(start.get(Calendar.YEAR));
				wuyefei.setEndYear(end.get(Calendar.YEAR));
                wuyefeiManageService.insertDetail(wuyefei);
                wuyefeiManageService.updateCount(wuyefei.getId());
                int num=DateUtils.getMonthNum(start.getTime(),end.getTime());
                List<WuyeAmortize> wuyeAmortizes=new ArrayList<>();
                DecimalFormat df   = new DecimalFormat("######0.00");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                Double wuyePrice=r.getWuyefei();
                Double buildingArea=r.getBuildingArea();
                for(int j=0;j<num;j++){
                    WuyeAmortize wuyeAmortize=new WuyeAmortize();
                    wuyeAmortize.setDeptId(deptId);
                    wuyeAmortize.setBuildingId(r.getBuildingId());
                    wuyeAmortize.setRoomId(r.getId());
                    if(j==0&&num==0){
                        int sDay=start.get(Calendar.DATE);
                        int eDay=end.get(Calendar.DATE);
                        int day=eDay-sDay+1;
                        Double m= Double.valueOf(df.format(day/30));
                        wuyeAmortize.setYing(wuyePrice*buildingArea*m);
                        wuyeAmortize.setUnpay(wuyePrice*buildingArea*m);
                        wuyeAmortize.setPayed((double) 0);
                    }else{
                        if(j==0){
                            int sDay=start.get(Calendar.DATE);
                            int eDay=DateUtils.getMaxDay(start)+1;
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
                            Double day= Double.valueOf(end.get(Calendar.DATE));
                            Double m= Double.valueOf(df.format(day/30));
                            Double total=Double.valueOf(df.format(wuyePrice*buildingArea*m));
                            wuyeAmortize.setYing(total);
                            wuyeAmortize.setUnpay(total);
                            wuyeAmortize.setPayed((double) 0);
                        }
                    }
					String m=sdf.format(start.getTime());
					wuyeAmortize.setMonth(m);
					wuyeAmortize.setWuyefeiId(wuyefei.getId());
					wuyeAmortizes.add(wuyeAmortize);
					start.add(Calendar.MONTH, 1);
                }
                wuyefeiManageService.batchAmortizes(wuyeAmortizes);
            }else{
                int y=year+1;
                return R.error("请先生成"+year+"-"+y+"年度的物业费");
            }

			if(i>0){
                RoomContractLog roomContractLog=new RoomContractLog();
                roomContractLog.setName("交房");
                roomContractLog.setContractId(roomContractDO.getId());
                roomContractLog.preInsert();
                contractService.savePayLog(roomContractLog);
            }
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return R.error();
		}
		if(i>0){
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
		RoomContractDO roomContractDO=contractService.getByRoomId(payListDO.getRoomId());
		UserDO userDO=ShiroUtils.getUser();
		DeptDO deptDO=deptService.get(userDO.getDeptId());
		String payId=contractService.getId("FC",userDO.getDeptId());
		BuildingDO buildingDO=buildingService.get(roomContractDO.getBuildingId());
		RoomDO roomDO=roomService.get(roomContractDO.getRoomId());
		BuildingDO parent=buildingService.get(buildingDO.getParentId());
		payListDO.setCode(payId);
		payListDO.setName(roomContractDO.getName());
		payListDO.setDeptId(userDO.getDeptId());
		payListDO.setDeptName(userDO.getDeptName());
		payListDO.setBuildingId(roomContractDO.getBuildingId());
		if(parent!=null){
			payListDO.setBuildingName(parent.getName()+"-"+buildingDO.getName());
		}else{
			payListDO.setBuildingName(buildingDO.getName());
		}
		payListDO.setRoomId(roomContractDO.getRoomId());
		payListDO.setRoomCode(roomContractDO.getRoomCode());
		payListDO.setUnit(roomDO.getUnit());
		payListDO.setFloor(roomDO.getFloor());
		payListDO.setType("1");
		payListDO.setYewuId(roomContractDO.getId());
		payListDO.setPrint(0);
		payListDO.setArea(Long.valueOf(deptDO.getArea()));
		payListDO.preInsert();
		String state=payListDO.getState();
		String sType=payListDO.getSType();
		RoomContractDO r=new RoomContractDO();
		r.setId(payListDO.getYewuId());
		Double price=payListDO.getPrice();
		if(state.equals("3")){
			price*=-1;
		}
		//定金
		if(sType.equals("1")){
			Double Receive=roomContractDO.getDepositPayed()+price;
			r.setDepositPayed(Receive);
		}
		//认筹款
		if(sType.equals("2")){
			Double Receive=roomContractDO.getRenchouPayed()+price;
			r.setRenchouPayed(Receive);
		}
		//首付款
		if((sType.equals("1")||sType.equals("2")||sType.equals("3"))&&roomContractDO.getPayType().equals("2")){
			Double first=roomContractDO.getFirstPay();
			Double firstReceive=roomContractDO.getFirstReceive();
			r.setFirstReceive(firstReceive+price);
			r.setFirstOwn(first-r.getFirstReceive());
		}
		Double receive=roomContractDO.getYishou();
		Double total=roomContractDO.getTotalPrice();
		r.setYishou(receive+price);
		r.setWeishou(total-r.getYishou());
		RoomContractLog roomContractLog=new RoomContractLog();
		roomContractLog.setName(payListDO.getReceiptBy()+"收"+payListDO.getSTypeName()+""+payListDO.getPrice()+"元");
		roomContractLog.setContractId(roomContractDO.getId());
		roomContractLog.preInsert();
		contractService.savePay(payListDO);
		contractService.savePayLog(roomContractLog);
		contractService.update(r);
		return payListDO;
	}
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("realty:contract:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		contractService.batchRemove(ids);
		return R.ok();
	}
	@GetMapping("/info")
	String info(@RequestParam Map<String, Object> params,Model model){
		params.put("deptId",ShiroUtils.getUser().getDeptId());
		List<RoomDO> roomDOList= roomService.list(params);
		if(roomDOList.size()>0){
			model.addAttribute("room", roomDOList.get(0));
			RoomContractDO roomContractDO=contractService.getByRoomId(roomDOList.get(0).getId());
			OwnerDO ownerDO=new OwnerDO();
			ownerDO.setOwnerName(roomContractDO.getName());
			ownerDO.setPhone(roomContractDO.getPhone());
			ownerDO.setIdCard(roomContractDO.getIdCard());
			model.addAttribute("owner", ownerDO);
		}
		return "realty/contract/info";
	}
	@GetMapping("/log")
	@ResponseBody
	public PageUtils log(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<RoomContractLog> roomContractLogList = contractService.log(query);
		int total = contractService.countLog(query);
		PageUtils pageUtils = new PageUtils(roomContractLogList, total);
		return pageUtils;
	}
	@GetMapping("/payList")
	@ResponseBody
	public PageUtils payList(@RequestParam Map<String, Object> params){
		UserDO userDO=ShiroUtils.getUser();
		params.put("deptId",userDO.getDeptId());
		//查询列表数据
		Query query = new Query(params);
		List<PayListDO> payListDOs = contractService.pay(query);
		int total = contractService.countPayList(query);
		PageUtils pageUtils = new PageUtils(payListDOs, total);
		return pageUtils;
	}
	/**
	 * 删除
	 */
	@PostMapping( "/chongxiao")
	@ResponseBody
	public R chongxiao( Long id){
		PayListDO payListDO=contractService.getPay(id);
		String state=payListDO.getState();
		if(state.equals("3")){
			payListDO.setState("4");
		}else {
			payListDO.setState("1");
		}
		contractService.updatePay(payListDO);
		if(state.equals("3")){
			payListDO.setState("5");
		}else {
			payListDO.setState("2");
		}
		RoomContractDO roomContractDO=contractService.getByRoomId(payListDO.getRoomId());
		Double price=payListDO.getPrice();
		payListDO.setPrice(-price);
		if(state.equals("3")){
			payListDO.setPrice(price);
		}
		payListDO.setChongxiaoCode(payListDO.getCode());
		String payId=contractService.getId("FC", ShiroUtils.getUser().getDeptId());
		payListDO.setCode(payId);
		payListDO.preInsert();
		String sType=payListDO.getSType();
		if(state.equals("3")){
			price*=-1;
		}
		//定金
		if(sType.equals("1")){
			Double Receive=roomContractDO.getDepositPayed()-price;
			roomContractDO.setDepositPayed(Receive);
		}
		//认筹款
		if(sType.equals("2")){
			Double Receive=roomContractDO.getRenchouPayed()-price;
			roomContractDO.setRenchouPayed(Receive);
		}
		if((sType.equals("1")||sType.equals("2")||sType.equals("3"))&&roomContractDO.getPayType().equals("2")){
			Double first=roomContractDO.getFirstPay();
			Double firstReceive=roomContractDO.getFirstReceive();
			roomContractDO.setFirstReceive(firstReceive-price);
			roomContractDO.setFirstOwn(first-roomContractDO.getFirstReceive());
		}
		Double receive=roomContractDO.getYishou();
		Double total=roomContractDO.getTotalPrice();
		roomContractDO.setYishou(receive+payListDO.getPrice());
		roomContractDO.setWeishou(total-roomContractDO.getYishou());
		RoomContractLog roomContractLog=new RoomContractLog();
		roomContractLog.setName(payListDO.getReceiptBy()+"冲销"+payListDO.getSTypeName()+""+payListDO.getPrice()+"元，冲销编码为"+payListDO.getChongxiaoCode());
		roomContractLog.setContractId(roomContractDO.getId());
		roomContractLog.preInsert();
		contractService.savePayLog(roomContractLog);
		contractService.update(roomContractDO);
		if(contractService.savePay(payListDO)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * <p>Discription:[下载模板功能]</p>
	 * Created on 2018年2月1日 上午11:57:59
	 * @param response response对象
	 * @param request response对象
	 * @author:[全冉]
	 */
	@ResponseBody
	@RequiresPermissions("realty:contract:download")
	@PostMapping("/download")
	public void downloadTemplate(HttpServletResponse response, HttpServletRequest request) {
		InputStream inputStream = null;
		ServletOutputStream servletOutputStream = null;
		try {
			String filename = "地产合同.xls";
			String path = "static/file/dichan.xls";
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
	@RequiresPermissions("realty:contract:upload")
	@PostMapping("/upload")
	R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
		//把表单内容转换成流
		InputStream fileInputStream = file.getInputStream();
		HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
		HSSFSheet sheetAt = workbook.getSheetAt(0);
		Row nRow = null;
		String sheetName = null;
		String orderId= String.valueOf(System.currentTimeMillis());
		Long[] ids=null;
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "sell_type");
		List<DictDO> dictDOList=dictService.list(map);
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
		List<RoomContractDO> roomContractDOList=new ArrayList<>();
		List<OwnerDO> ownerDOList=new ArrayList<>();
		List<String> roomIds=contractService.getIds(deptId);//查询已有合同的房屋
		List<String> rooms=contractService.getRoom(deptId);//查询当前项目下所有房屋
		for (int j = 2; j <= end; j++) {
			if (repeat > 0) {
				break;
			}
			int rowNum = j + 1;
			nRow = sheetAt.getRow(j);
			RoomContractDO r=new RoomContractDO();
			OwnerDO o=new OwnerDO();
			if((nRow.getCell(0) == null|| getCellValue(nRow.getCell(0)).equals(""))&&(nRow.getCell(1) == null|| getCellValue(nRow.getCell(1)).equals(""))&&(nRow.getCell(2) == null|| getCellValue(nRow.getCell(2)).equals(""))){
				break;
			}
			int a=nRow.getCell(0).getCellType();
			if (nRow.getCell(0) == null || getCellValue(nRow.getCell(0)).equals("")) {
				sb.append("第" + rowNum + "行没有输入楼宇编号");
				repeat++;
				break;
			}else{
				r.setBuildingId(Long.valueOf(getCellValue(nRow.getCell(0))));
			}
			if (nRow.getCell(1) == null || getCellValue(nRow.getCell(1)).equals("")) {
				sb.append("第" + rowNum + "行没有输入房屋编号");
				repeat++;
				break;
			}else{
				r.setRoomCode(getCellValue(nRow.getCell(1)));
			}
			String room=""+r.getBuildingId()+"-"+r.getRoomCode();
			boolean isContains = UploadUtils.checkHas(room,rooms);
			if(!isContains){
				sb.append("第" + rowNum + "行不存在该房屋");
				repeat++;
				break;
			}

			isContains = UploadUtils.checkHas(room,roomIds);
			if(isContains){
				sb.append("第" + rowNum + "行该房屋已有合同");
				repeat++;
				break;
			}
			if (nRow.getCell(2) == null || getCellValue(nRow.getCell(2)).equals("")) {
				sb.append("第" + rowNum + "行没有输入面积");
				repeat++;
				break;
			}else{
				r.setBuildingArea(Double.valueOf(getCellValue(nRow.getCell(2))));
			}
			if (nRow.getCell(3) == null || getCellValue(nRow.getCell(3)).equals("")) {
				sb.append("第" + rowNum + "行没有输入转让类型");
				repeat++;
				break;
			}else{
				String sellType=getCellValue(nRow.getCell(3));
				sellType=getValue(sellType,dictDOList);
				r.setSellType(sellType);
			}
			if (nRow.getCell(4) == null || getCellValue(nRow.getCell(4)).equals("")) {
				sb.append("第" + rowNum + "行没有输入购房者");
				repeat++;
				break;
			}else{
				r.setName(getCellValue(nRow.getCell(4)));
				o.setOwnerName(r.getName());
			}
			if (nRow.getCell(5) == null || getCellValue(nRow.getCell(5)).equals("")) {
				sb.append("第" + rowNum + "行没有输入证件号码");
				repeat++;
				break;
			}else{
				String value=getCellValue(nRow.getCell(5));
				if(value.contains(",")||value.contains("，")){
					value.replace("，",",");
					int length=value.split(",").length;
					for(int z=0;z<length;z++){
						if(!UploadUtils.isIdNum(value.split(",")[z])&&
								!UploadUtils.checkTax(value.split(",")[z])
								&&!value.split(",")[z].contains("-")){
							sb.append("第" + rowNum + "行输入证件号码格式错误");
							repeat++;
							break;
						}
					}

				}else {
					if(!UploadUtils.isIdNum(getCellValue(nRow.getCell(5)))&&
							!UploadUtils.checkTax(getCellValue(nRow.getCell(5)))
							&&!getCellValue(nRow.getCell(5)).contains("-")){
						sb.append("第" + rowNum + "行输入证件号码格式错误");
						repeat++;
						break;
					}
				}
				r.setIdCard(getCellValue(nRow.getCell(5)));
				o.setIdCard(r.getIdCard());
			}
			if (nRow.getCell(6) == null || getCellValue(nRow.getCell(6)).equals("")) {
				sb.append("第" + rowNum + "行没有输入购房者手机号或格式错误");
				repeat++;
				break;
			}else{
				/*if(!UploadUtils.isPhone(getCellValue(nRow.getCell(6)))){
					sb.append("第" + rowNum + "行输入购房者手机号格式错误");
					repeat++;
					break;
				}*/
				r.setPhone(getCellValue(nRow.getCell(6)));
				o.setPhone(getCellValue(nRow.getCell(6)));
			}
			if (nRow.getCell(7) == null || getCellValue(nRow.getCell(7)).equals("")) {
				sb.append("第" + rowNum + "行没有输入购房日期");
				repeat++;
				break;
			}else{
				Date d=new Date();
				try {
					if(!HSSFDateUtil.isCellDateFormatted(nRow.getCell(7))){
						sb.append("第" + rowNum + "行没有输入正确格式的购房日期");
						repeat++;
						break;
					}
					d=nRow.getCell(7).getDateCellValue();
				} catch (Exception e) {
					String date=getCellValue(nRow.getCell(7));
					if(!UploadUtils.checkDate(date)){
						sb.append("第" + rowNum + "行没有输入正确格式的购房日期");
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
				r.setSellDate(d);
			}
			if (nRow.getCell(8) == null || getCellValue(nRow.getCell(8)).equals("")) {
				sb.append("第" + rowNum + "行没有输入销售合同号");
				repeat++;
				break;
			}else{
				r.setCode(getCellValue(nRow.getCell(8)));
			}
			if (nRow.getCell(9) == null || getCellValue(nRow.getCell(9)).equals("")) {
				sb.append("第" + rowNum + "行没有选择付款方式");
				repeat++;
				break;
			}else{
				String payType=getCellValue(nRow.getCell(9));
				if(payType.equals("全款")){
					r.setPayType("1");
				}
				else if(payType.equals("贷款")){
					r.setPayType("2");
				}else {
					r.setPayType("");
				}
			}
			if (nRow.getCell(10) == null || getCellValue(nRow.getCell(10)).equals("")) {
				sb.append("第" + rowNum + "行没有输入成交单价");
				repeat++;
				break;
			}else{
				r.setPrice(Double.valueOf(getCellValue(nRow.getCell(10))));
			}
		/*	if (nRow.getCell(11) == null ||  getCellValue(nRow.getCell(11)).equals("")) {
				sb.append("第" + rowNum + "行没有输入价税合计");
				repeat++;
				break;
			}else{
				r.setTotalPrice(Double.valueOf(getCellValue(nRow.getCell(11))));
				r.setTotalPriceNoTax(Double.valueOf(getCellValue(nRow.getCell(13))));
				r.setTax(Double.valueOf(getCellValue(nRow.getCell(14))));
			}*/


			if (nRow.getCell(12) == null || getCellValue(nRow.getCell(12)).equals("")) {
				r.setTotalPrice(Double.valueOf(Math.round(r.getBuildingArea()*r.getPrice())));
				r.setTotalPriceNoTax(Double.valueOf(Math.round(r.getTotalPrice()/1.1)));
				r.setTax(Double.valueOf(Math.round(r.getTotalPrice()/1.1*0.1)));
			}else{
				r.setBalanceArea(Double.valueOf(getCellValue(nRow.getCell(12))));
				Double area=r.getBuildingArea();
				r.setTotalPrice(Double.valueOf(Math.round(area*r.getPrice())));
				r.setTotalPriceNoTax(Double.valueOf(Math.round(r.getTotalPrice()/1.1)));
				r.setTax(Double.valueOf(Math.round(r.getTotalPrice()/1.1*0.1)));
				r.setBalancePrice(Double.valueOf(Math.round(r.getBalanceArea()*r.getPrice())));
				r.setBalanceUnpay(Double.valueOf(Math.round(r.getBalanceArea()*r.getPrice())));
				r.setBalancePayed(Double.valueOf(0));
			}
			if(r.getPayType().equals("0")){
				r.setFirstPay(r.getTotalPrice());
			}else {
				if (nRow.getCell(11) == null || getCellValue(nRow.getCell(11)).equals("")) {
					r.setFirstPay(r.getTotalPrice());
					r.setFirstReceive((double) 0);
					r.setFirstOwn(r.getTotalPrice());
				}else{
					r.setFirstPay(Double.valueOf(getCellValue(nRow.getCell(11))));
					r.setFirstReceive((double) 0);
					r.setFirstOwn(Double.valueOf(getCellValue(nRow.getCell(11))));
				}
			}
			r.setLoan(r.getTotalPrice()-r.getFirstPay());
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(13)))) {
				r.setSellBy(getCellValue(nRow.getCell(13)));
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(14)))) {
				r.setBumen(getCellValue(nRow.getCell(14)));
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(15)))) {
				r.setRenyuan(getCellValue(nRow.getCell(15)));
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(16)))) {
				r.setLeixing(getCellValue(nRow.getCell(16)));
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(17)))) {
				r.setGuishu(getCellValue(nRow.getCell(17)));
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(18)))) {
				r.setBeizhu(getCellValue(nRow.getCell(18)));
			}
			if (StringUtils.isNotBlank(getCellValue(nRow.getCell(19)))) {
				r.setRemark(getCellValue(nRow.getCell(19)));
			}
			r.preInsert();
			r.setState("0");
			r.setOrderId(orderId);
			roomContractDOList.add(r);
			o.preInsert();
			o.setDeptId(deptId);
            o.setOrderId(orderId);
			ownerDOList.add(o);
		}
		if (repeat > 0) {
			return R.error(sb.toString());
		} else {
			/*if(ownerDOList.size()>0){
				int i=ownerService.batchInsert(ownerDOList);
				if(i>0){
					ownerService.duplicate(orderId);
					i=ownerService.synchronization(orderId);
					ownerService.removeCopy(orderId);
				}
			}*/
			if(roomContractDOList.size()>0){
				int i=contractService.batchInsert(roomContractDOList);
				RoomContractLog roomContractLog=new RoomContractLog();
				roomContractLog.setName("合同录入");
				roomContractLog.setOrderId(orderId);
				roomContractLog.preInsert();
				contractService.saveLog(roomContractLog);
				contractService.updateRoomSellType(orderId);
				roomService.updateArea(orderId);
				if(i>0){
					return R.ok("导入"+i+"条数据");
				}else{
					return R.error();
				}
			}else {
				return R.error("没有能导入的合同或者导入的房屋已签署合同");
			}
		}
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
	public  String formatDouble4(double d) {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(d);
	}

	public boolean findStr(String[] args,String str){
		boolean result = false;
		//第一种：List
		result = Arrays.asList(args).contains(str);
		//第二种：set
		Set<String> sets = new HashSet<String>(Arrays.asList(args));
		result = sets.contains(str);
		//第三种：loop
		for (String s : args) {
			if (s.equals(str)){
				return true;
			}
		}
		//第四种：binarySearch(Arrays的binarySearch方法必须应用于有序数组)
		int res = Arrays.binarySearch(args, str);
		if (res > 0){
			return true;
		}

		return result;
	}
}
