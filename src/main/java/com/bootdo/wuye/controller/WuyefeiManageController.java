package com.bootdo.wuye.controller;

import com.bootdo.common.controller.BaseController;
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
import com.bootdo.wuye.domain.ExportDO;
import com.bootdo.wuye.domain.WuyeAmortize;
import com.bootdo.wuye.domain.WuyefeiDO;
import com.bootdo.wuye.domain.WuyefeiDetailDO;
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
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 房间管理
 */

@Controller
@RequestMapping("/wuye/wuyefeiManage")
public class WuyefeiManageController extends BaseController {
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
    private WuyefeiManageService wuyefeiManageService;
    @Autowired
    private FangbenManageService fangbenManageService;
    @Autowired
    private QunuanManageService qunuanManageService;
    private String prefix = "wuye/wuyefeiManage";

    @GetMapping()
    @RequiresPermissions("wuye:wuyefeiManage:manage")
    String room() {
        return prefix + "/manage";
    }

    @GetMapping("/wuyefeiPay")
    @RequiresPermissions("wuye:wuyefeiManage:wuyefeiPay")
    String wuyefeiPay() {
        return "wuye/wuyefeiPay/wuyefeiPay";
    }

    @GetMapping("/payIndex")
    @RequiresPermissions("wuye:wuyefeiPay:payIndex")
    String payIndex() {
        return "wuye/pay/wuyePay";
    }

    @GetMapping("/print/{id}")
    @RequiresPermissions("wuye:wuyePay:print")
    String print(@PathVariable("id") Long id, Model model) {
        PayListDO payListDO = qunuanManageService.getPay(id);
        WuyefeiDetailDO wuyefeiDetailDO = wuyefeiManageService.getDetail(payListDO.getYewuId());
        Date pay = payListDO.getPayDate();
        Calendar c = Calendar.getInstance();
        c.setTime(pay);
        if (payListDO.getName() == null) {
            payListDO.setName("");
        }
        int print = payListDO.getPrint() + 1;
        payListDO.setPrint(print);
        int month = c.get(Calendar.MONTH)+1;
        model.addAttribute("year","  "+c.get(Calendar.YEAR)+"  ");
        model.addAttribute("month","  "+month+"  ");
        model.addAttribute("day", "  " + c.get(Calendar.DATE) + "  ");
        model.addAttribute("p", payListDO);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date s=wuyefeiDetailDO.getStartDate();
        Date e=wuyefeiDetailDO.getEndDate();
        model.addAttribute("name", sdf.format(s)+"——"+sdf.format(e));
        return "wuye/print/wuyefei";
    }

    @ResponseBody
    @PostMapping("/getSum")
    WuyefeiDO getSum(WuyefeiDO w, Model model) {
        w.setDeptId(ShiroUtils.getUser().getDeptId());
        WuyefeiDO wuyefeiDO = wuyefeiManageService.getSum(w);
        return wuyefeiDO;
    }
    @ResponseBody
    @PostMapping("/getPaySum")
    WuyefeiDO getPaySum(@RequestParam Map<String, Object> params) {
        params.put("deptId",ShiroUtils.getUser().getDeptId());
        WuyefeiDO wuyefeiDO = wuyefeiManageService.getPaySum(params);
        return wuyefeiDO;
    }
    @ApiOperation(value = "获取房间列表", notes = "")
    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        UserDO userDO = ShiroUtils.getUser();
        Query query = new Query(params);
        String year = query.get("year").toString();
        if (StringUtils.isNotBlank(year)) {
            query.put("startYear", Integer.valueOf(year.split(" - ")[0]));
            query.put("endYear", Integer.valueOf(year.split(" - ")[1]));
        }
        query.put("delFlag", "0");
        query.put("deptId", ShiroUtils.getUser().getDeptId());
        List<WuyefeiDO> wuyefeiDOList = wuyefeiManageService.list(query);
        int total = wuyefeiManageService.count(query);
        PageUtils pageUtils = new PageUtils(wuyefeiDOList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("wuye:wuyefeiManage:add")
    String add(Model model) {
        Map<String, Object> params = new HashedMap();
        params.put("start", new Date().getYear());
        params.put("delFlag", "0");
        params.put("sort", "start_year");
        params.put("order", "DESC");
        params.put("deptId", ShiroUtils.getUser().getDeptId());
        List<WuyefeiDO> wuyefeiDOList = wuyefeiManageService.list(params);
        if (wuyefeiDOList.size() > 0) {
            Date d = wuyefeiDOList.get(0).getEndDate();
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            int day = c.get(Calendar.DATE);
            c.set(Calendar.DATE, day + 1);
            model.addAttribute("startDate", c.getTime());
        } else {
            model.addAttribute("startDate", "");
        }
        return prefix + "/add";
    }


    @GetMapping("/edit/{id}")
    @RequiresPermissions("wuye:wuyefeiManage:edit")
    String edit(@PathVariable("id") Long id, Model model) {
        WuyefeiDO wuyefeiDO = wuyefeiManageService.get(id);
        model.addAttribute("wuyefei", wuyefeiDO);
        return prefix + "/edit";
    }
    @GetMapping("/export/{id}")
    String export(@PathVariable("id") Long id, Model model) {
        ExportDO exportDO = wuyefeiManageService.getExport(ShiroUtils.getUser().getDeptId());
        model.addAttribute("id", id);
        model.addAttribute("exportDO", exportDO);
        return prefix + "/export";
    }
    @GetMapping("/create/{id}")
    @RequiresPermissions("wuye:wuyefeiManage:create")
    String create(@PathVariable("id") Long id, Model model) {
        WuyefeiDO wuyefeiDO = wuyefeiManageService.get(id);
        model.addAttribute("wuyefei", wuyefeiDO);
        return prefix + "/create";
    }

    @GetMapping("/createSec/{id}")
    @RequiresPermissions("wuye:wuyefeiManage:create")
    String createSec(@PathVariable("id") Long id, Model model) {
        WuyefeiDO wuyefeiDO = wuyefeiManageService.get(id);
        model.addAttribute("wuyefei", wuyefeiDO);
        return prefix + "/createSec";
    }

    @GetMapping("/editDetail/{id}")
    String editDetail(@PathVariable("id") Long id, Model model) {
        WuyefeiDetailDO wuyefeiDetailDO = wuyefeiManageService.getDetail(id);
        model.addAttribute("wuyefeiDetailDO", wuyefeiDetailDO);
        return prefix + "/editDetail";
    }

    @GetMapping("/info/{id}")
    @RequiresPermissions("wuye:wuyefeiManage:info")
    String info(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return prefix + "/info";
    }

    @GetMapping("/payInfo/{id}")
    @RequiresPermissions("wuye:wuyefeiManage:payInfo")
    String payInfo(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "wuye/wuyefeiPay/info";
    }

    @GetMapping("/shou/{id}")
    String pay(@PathVariable("id") Long id, Model model) {
        WuyefeiDetailDO wuyefeiDetail = wuyefeiManageService.getDetail(id);
        model.addAttribute("wuyefeiDetail", wuyefeiDetail);
        model.addAttribute("name", ShiroUtils.getUser().getName());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String d = sdf.format(date);
        model.addAttribute("date", d);
        return "wuye/wuyefeiPay/shou";
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
        WuyefeiDetailDO wuyefeiDetail = wuyefeiManageService.getDetail(payListDO.getYewuId());
        RoomDO roomDO = roomService.get(wuyefeiDetail.getRoomId());
        UserDO userDO = ShiroUtils.getUser();
        Map<String, Object> params = new HashedMap();
        params.put("roomId", roomDO.getId());
        params.put("deptId", userDO.getDeptId());
        RoomContractDO roomContractDO = contractService.list(params).get(0);
        DeptDO deptDO = deptService.get(userDO.getDeptId());
        String payId = wuyefeiManageService.getId("WY");
        BuildingDO buildingDO = buildingService.get(roomDO.getBuildingId());
        BuildingDO parent = buildingService.get(buildingDO.getParentId());
        payListDO.setCode(payId);
        payListDO.setName(roomContractDO.getName());
        payListDO.setDeptId(userDO.getDeptId());
        payListDO.setDeptName(userDO.getDeptName());
        payListDO.setState("0");
        payListDO.setBuildingId(roomDO.getBuildingId());
        if (parent != null) {
            payListDO.setBuildingName(parent.getName() + "-" + buildingDO.getName());
        } else {
            payListDO.setBuildingName(buildingDO.getName());
        }
        payListDO.setRoomId(roomDO.getId());
        payListDO.setYewuId(wuyefeiDetail.getId());
        payListDO.setRoomCode(roomDO.getCode());
        payListDO.setUnit(roomDO.getUnit());
        payListDO.setFloor(roomDO.getFloor());
        payListDO.setsType("1");
        payListDO.setsTypeName("物业费");
        payListDO.setPrint(0);
        payListDO.setArea(Long.valueOf(deptDO.getArea()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        payListDO.setDate(sdf.format(wuyefeiDetail.getStartDate()) + "-" + sdf.format(wuyefeiDetail.getEndDate()));
        payListDO.preInsert();
        Double price = payListDO.getPrice();
        Double receive = wuyefeiDetail.getPayed();
        Double total = wuyefeiDetail.getYingpay();
        wuyefeiDetail.setPayed(receive + price);
        wuyefeiDetail.setUnpay(total - wuyefeiDetail.getPayed());
        if (wuyefeiDetail.getUnpay() == 0) {
            wuyefeiDetail.setState("1");
        }
        //获取摊销数据
        try {
            List<WuyeAmortize> wuyeAmortizes = wuyefeiManageService.getWuyeAmortize(wuyefeiDetail);
            if (wuyeAmortizes.size() > 0) {
                Double payed = wuyefeiDetail.getPayed();
                Double m = Double.valueOf(0);//累加金额
                for (WuyeAmortize w : wuyeAmortizes) {
                    Double money = m + w.getYing();
                    boolean isBreak = false;
                    if (payed >= money) {
                        w.setPayed(w.getYing());
                        w.setUnpay(Double.valueOf(0));
                    } else {
                        w.setPayed(payed - m);
                        w.setUnpay(w.getYing() - w.getPayed());
                        isBreak = true;
                    }
                    m += w.getYing();
                    wuyefeiManageService.updateAmortize(w);
                    if (isBreak) {
                        break;
                    }
                }
            }
            wuyefeiManageService.savePay(payListDO);
            wuyefeiManageService.updateDetail(wuyefeiDetail);
            wuyefeiManageService.updateCount(wuyefeiDetail.getWuyefeiId());
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return payListDO;
        }
        return payListDO;
    }

    @PostMapping("/chongxiao")
    @ResponseBody
    public R chongxiao(Long id) {
        PayListDO payListDO = wuyefeiManageService.getPay(id);
        payListDO.setState("2");
        wuyefeiManageService.updatePay(payListDO);
        WuyefeiDetailDO wuyefeiDetail = wuyefeiManageService.getDetail(payListDO.getYewuId());
        Double price = payListDO.getPrice();
        payListDO.setPrice(-price);
        payListDO.setState("1");
        payListDO.setChongxiaoCode(payListDO.getCode());
        String payId = wuyefeiManageService.getId("WY");
        payListDO.setCode(payId);
        payListDO.preInsert();
        String sType = payListDO.getSType();
        Double receive = wuyefeiDetail.getPayed();
        Double total = wuyefeiDetail.getYingpay();
        wuyefeiDetail.setPayed(receive + payListDO.getPrice());
        wuyefeiDetail.setUnpay(total - wuyefeiDetail.getPayed());
        wuyefeiDetail.setState("0");
        try {
            //获取摊销数据
            List<WuyeAmortize> wuyeAmortizes = wuyefeiManageService.getWuyeAmortize(wuyefeiDetail);
            if (wuyeAmortizes.size() > 0) {
                Double payed = wuyefeiDetail.getPayed();
                Double m = Double.valueOf(0);//累加
                boolean isBreak = false;// 金额
                for (WuyeAmortize w : wuyeAmortizes) {
                    Double money = m + w.getYing();
                    if (isBreak) {
                        w.setPayed(Double.valueOf(0));
                        w.setUnpay(w.getYing());
                    } else {
                        if (payed >= money) {
                            w.setPayed(w.getYing());
                            w.setUnpay(Double.valueOf(0));
                        } else {
                            w.setPayed(payed - m);
                            w.setUnpay(w.getYing() - w.getPayed());
                            isBreak = true;
                        }
                    }
                    m += w.getYing();
                    wuyefeiManageService.updateAmortize(w);

                }
            }
            wuyefeiManageService.updateDetail(wuyefeiDetail);
            if (wuyefeiManageService.savePay(payListDO) > 0) {
                wuyefeiManageService.updateCount(wuyefeiDetail.getWuyefeiId());
                return R.ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return R.error();
    }

    @GetMapping("/payList")
    @ResponseBody
    public PageUtils payList(@RequestParam Map<String, Object> params) {
        UserDO userDO = ShiroUtils.getUser();
        params.put("deptId", userDO.getDeptId());
        params.put("types", "wuye");
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
        params.put("deptId", ShiroUtils.getUser().getDeptId());
        //查询列表数据
        Query query = new Query(params);
        List<WuyefeiDetailDO> wuyefeiDOList = wuyefeiManageService.detailList(query);
        int total = wuyefeiManageService.countDetail(query);
        PageUtils pageUtils = new PageUtils(wuyefeiDOList, total);
        return pageUtils;
    }

    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("wuye:wuyefeiManage:add")
    public R save(HttpServletRequest request) throws IOException {
        String jsonStr = request.getParameter("mydata");
        ObjectMapper mapper = new ObjectMapper();
        WuyefeiDO wuyefei = mapper.readValue(jsonStr, WuyefeiDO.class);
        Long deptId = ShiroUtils.getUser().getDeptId();
        wuyefei.setDeptId(deptId);
        Date start = wuyefei.getStartDate();
        Date end = wuyefei.getEndDate();
        wuyefei.setStartMonth(start.getMonth());
        wuyefei.setEndMonth(end.getMonth());
        /*int i=wuyefeiManageService.checkHasWuyefei(wuyefei);
		if(i>0){
			return R.error("所选周期内已生成物业费，请重新选择");
		}*/
        wuyefei.setPayed(0);
        wuyefei.preInsert();
        wuyefei.setStatus("0");
        wuyefeiManageService.save(wuyefei);
        wuyefei.setMonth(DateUtils.getMonths(start, end));
        return R.ok();
    }

    @PostMapping("/createWuyefei")
    @ResponseBody
    public R createWuyefei(HttpServletRequest request) throws IOException {
        String jsonStr = request.getParameter("mydata");
        ObjectMapper mapper = new ObjectMapper();
        WuyefeiDO wuyefei = mapper.readValue(jsonStr, WuyefeiDO.class);
        R r = wuyefeiManageService.createWuyefei(wuyefei);

        return r;
    }

    @PostMapping("/batchCreate")
    @ResponseBody
    public R batchCreate(HttpServletRequest request) throws IOException {
        String jsonStr = request.getParameter("mydata");
        ObjectMapper mapper = new ObjectMapper();
        WuyefeiDO wuyefei = mapper.readValue(jsonStr, WuyefeiDO.class);
        R r = wuyefeiManageService.batchCreate(wuyefei);

        return r;
    }

    @GetMapping("/getMonths")
    @ResponseBody
    public Double getMonths(@RequestParam Map<String, Object> params) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String start = params.get("start").toString();
        String end = params.get("end").toString();
        Double months = DateUtils.getMonths(sdf.parse(start), sdf.parse(end));
        return months;
    }

    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("wuye:wuyefeiManage:edit")
    public R update(HttpServletRequest request) throws IOException {
        String jsonStr = request.getParameter("mydata");
        ObjectMapper mapper = new ObjectMapper();
        WuyefeiDO wuyefei = mapper.readValue(jsonStr, WuyefeiDO.class);
        wuyefei.preUpdate();
        wuyefeiManageService.update(wuyefei);
        return R.ok();
    }

    @ResponseBody
    @RequestMapping("/updateDetail")
    public R updateDetail(HttpServletRequest request) throws IOException {
        String jsonStr = request.getParameter("mydata");
        ObjectMapper mapper = new ObjectMapper();
        WuyefeiDetailDO wuyefeiDetailDO = mapper.readValue(jsonStr, WuyefeiDetailDO.class);

        if (wuyefeiManageService.updateDetailInfo(wuyefeiDetailDO) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("wuye:wuyefeiManage:remove")
    public R remove(Long id) {
        int i = wuyefeiManageService.checkWuyefei(id);
        if (i > 0) {
            return R.error("已有缴费信息，不能删除");
        } else {
            WuyefeiDO wuyefeiDO = new WuyefeiDO();
            wuyefeiDO.setId(id);
            wuyefeiDO.preUpdate();
            wuyefeiDO.setDelFlag("1");
            i = wuyefeiManageService.update(wuyefeiDO);
            wuyefeiManageService.removeDetailById(id);
            WuyeAmortize wuyeAmortize = new WuyeAmortize();
            wuyeAmortize.setWuyefeiId(wuyefeiDO.getId());
            wuyeAmortize.setRoomId(wuyefeiDO.getRoomId());
            wuyefeiManageService.removeAmortize(wuyeAmortize);
            if (i > 0) {
                return R.ok();
            }
        }
        return R.error();
    }

    @PostMapping("/removeDetail")
    @ResponseBody
    public R removeDetail(Long id) {
        WuyefeiDetailDO wuyefeiDetailDO = wuyefeiManageService.getDetail(id);
        if (wuyefeiDetailDO.getPayed() > 0) {
            return R.error("已有缴费信息，不能删除");
        } else {
            wuyefeiManageService.removeDetail(id);
            WuyeAmortize wuyeAmortize = new WuyeAmortize();
            wuyeAmortize.setWuyefeiId(wuyefeiDetailDO.getWuyefeiId());
            wuyeAmortize.setRoomId(wuyefeiDetailDO.getRoomId());
            wuyefeiManageService.removeAmortize(wuyeAmortize);
            wuyefeiManageService.updateCount(wuyefeiDetailDO.getWuyefeiId());
            return R.ok();
        }
    }

    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("wuye:room:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
        wuyefeiManageService.batchRemove(ids);
        return R.ok();
    }

    @ResponseBody
    @PostMapping("/download")
    public void downloadTemplate(HttpServletResponse response, HttpServletRequest request) {
        InputStream inputStream = null;
        ServletOutputStream servletOutputStream = null;
        try {
            String filename = "导入已收物业费模板.xls";
            String path = "static/file/wuyefei.xls";
            org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:" + path);

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
    R upload(@RequestParam("file") MultipartFile file, @RequestParam("id") String id) throws IOException {
        try {
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
            if (index == 1) {
                return R.error("请填写物业费信息");
            }
            UserDO userDO = ShiroUtils.getUser();
            Long deptId = userDO.getDeptId();
            List<String> rooms = wuyefeiManageService.getWuyefeiDetail(id);//查询当前项目下所有房屋
            List<String> roomsHas = null;
            List<WuyefeiDetailDO> wuyefeiDetailDOs = new ArrayList<>();
            WuyefeiDetailDO wuyefeiDetailDO = null;
            for (int j = 1; j <= index; j++) {
                if (repeat > 0) {
                    break;
                }
                int rowNum = j + 1;
                nRow = sheetAt.getRow(j);
                wuyefeiDetailDO = new WuyefeiDetailDO();
                wuyefeiDetailDO.setWuyefeiId(Long.valueOf(id));
                if ((nRow.getCell(0) == null || getCellValue(nRow.getCell(0)).equals("")) && (nRow.getCell(1) == null || getCellValue(nRow.getCell(1)).equals("")) && (nRow.getCell(2) == null || getCellValue(nRow.getCell(2)).equals(""))) {
                    break;
                }
                if (nRow.getCell(0) == null || getCellValue(nRow.getCell(0)).equals("")) {
                    sb.append("第" + rowNum + "行没有输入楼宇编号");
                    repeat++;
                    break;
                } else {
                    wuyefeiDetailDO.setBuildingId(Long.valueOf(getCellValue(nRow.getCell(0))));
                }
                Double m1 = Double.valueOf(0);
                if (nRow.getCell(1) == null || getCellValue(nRow.getCell(1)).equals("")) {
                    sb.append("第" + rowNum + "行没有输入房屋编号");
                    repeat++;
                    break;
                } else {
                    String room = "" + wuyefeiDetailDO.getBuildingId() + "|" + getCellValue(nRow.getCell(1));
                    String isContains = UploadUtils.checkEquals(room, rooms);
                    if (isContains == null) {
                        sb.append("第" + rowNum + "行不存在该房屋");
                        repeat++;
                        break;
                    }
                    m1 = Double.valueOf(isContains.split("|")[2]);
                    wuyefeiDetailDO.setRoomCode(getCellValue(nRow.getCell(1)));
                }
                if (nRow.getCell(2) == null || getCellValue(nRow.getCell(2)).equals("")) {
                    sb.append("第" + rowNum + "行没有输入已收金额");
                    repeat++;
                    break;
                } else {
                    Double m = Double.valueOf(getCellValue(nRow.getCell(2)));
                    if (m > m1) {
                        sb.append("第" + rowNum + "行已录入已收金额或录入已收金额大于未收金额");
                        repeat++;
                        break;
                    }
                    wuyefeiDetailDO.setPayed(m);
                }
                WuyefeiDetailDO wd = wuyefeiManageService.getDetailByRoom(wuyefeiDetailDO);
                //获取摊销数据
                List<WuyeAmortize> wuyeAmortizes = wuyefeiManageService.getWuyeAmortize(wd);
                if (wuyeAmortizes.size() > 0) {
                    Double payed = wuyefeiDetailDO.getPayed();
                    Double m = Double.valueOf(0);//累加金额
                    for (WuyeAmortize w : wuyeAmortizes) {
                        Double money = m + w.getYing();
                        boolean isBreak = false;
                        if (payed >= money) {
                            w.setPayed(w.getYing());
                            w.setUnpay(Double.valueOf(0));
                        } else {
                            w.setPayed(payed - m);
                            w.setUnpay(w.getYing() - w.getPayed());
                            isBreak = true;
                        }
                        m += w.getYing();
                        wuyefeiManageService.updateAmortize(w);
                        if (isBreak) {
                            break;
                        }
                    }
                }
                Double receive = wd.getPayed();
                Double total = wd.getYingpay();
                wd.setPayed(receive + wuyefeiDetailDO.getPayed());
                wd.setUnpay(total - wd.getPayed());
                if (wd.getUnpay() == 0) {
                    wd.setState("1");
                }
                wuyefeiManageService.updateDetail(wd);
            }
            wuyefeiManageService.updateCount(Long.valueOf(id));
            if (repeat > 0) {
                return R.error(sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        return R.ok();
    }

    //判断单元格的类型
    private String getCellValue(Cell cell) {
        String cellValue = "";
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df1 = new DecimalFormat("#");
        // System
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                cellValue = cell.getRichStringCellValue().getString().trim();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                if (!StringUtils.isIntegerForDouble(cell.getNumericCellValue())) {
                    cellValue = df.format(cell.getNumericCellValue()).toString();
                } else {
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

    @GetMapping("/updateA")
    @ResponseBody
    public R updateA() {
        Map<String, Object> m = new HashedMap();
        m.put("offset", 1);
        m.put("limit", 1);
        Query q = new Query(m);
        q.put("offset", null);
        q.put("limit", null);
        Long deptId = ShiroUtils.getUser().getDeptId();
        q.put("deptId", deptId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        List<WuyeAmortize> wuyeAmortizes = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("######0.00");
        List<WuyefeiDetailDO> wuyefeiDetailDOs = wuyefeiManageService.detailList(q);
        for (WuyefeiDetailDO wuyefeiDetailDO : wuyefeiDetailDOs) {
            Date start = wuyefeiDetailDO.getStartDate();
            Date end = wuyefeiDetailDO.getEndDate();
            int num = DateUtils.getMonthNum(start, end);
            WuyeAmortize wuyeAmortize = new WuyeAmortize();
            Calendar c = Calendar.getInstance();
            c.setTime(start);
            Double wuyePrice = wuyefeiDetailDO.getYingpay();
            wuyePrice = UploadUtils.format(wuyePrice / num);
            Double p = Double.valueOf(0);
            for (int j = 0; j < num; j++) {
                wuyeAmortize = new WuyeAmortize();
                wuyeAmortize.setDeptId(deptId);
                wuyeAmortize.setBuildingId(wuyefeiDetailDO.getBuildingId());
                wuyeAmortize.setRoomId(wuyefeiDetailDO.getRoomId());
                if (j == 0 && num == 1) {
                    Calendar lastDay = Calendar.getInstance();
                    lastDay.setTime(start);
                    int sDay = c.get(Calendar.DATE);
                    int eDay = lastDay.get(Calendar.DATE);
                    Double day = Double.valueOf(eDay - sDay + 1);
                    Double m1 = Double.valueOf(df.format(day / 30));
                    if (sDay == 1) {
                        m1 = Double.valueOf(1);
                    }
                    Double total = Double.valueOf(df.format(wuyePrice * m1));
                    wuyeAmortize.setYing(total);
                    wuyeAmortize.setUnpay(total);
                    wuyeAmortize.setPayed((double) 0);
                } else {
                    if (j == 0) {
                        Calendar lastDay = Calendar.getInstance();
                        lastDay.setTime(start);
                        int sDay = c.get(Calendar.DATE);
                        int eDay = lastDay.getActualMaximum(Calendar.DAY_OF_MONTH) + 1;
                        Double day = Double.valueOf(eDay - sDay);
                        Double m2 = Double.valueOf(df.format(day / 30));
                        if (sDay == 1) {
                            m2 = Double.valueOf(1);
                        }
                        Double total = Double.valueOf(df.format(wuyePrice * m2));
                        p += total;
                        wuyeAmortize.setYing(total);
                        wuyeAmortize.setUnpay(total);
                        wuyeAmortize.setPayed((double) 0);
                    } else if (j > 0 && j < num - 1) {
                        p += wuyePrice;
                        wuyeAmortize.setYing(Double.valueOf(df.format(wuyePrice)));
                        wuyeAmortize.setUnpay(Double.valueOf(df.format(wuyePrice)));
                        wuyeAmortize.setPayed((double) 0);
                    } else {
                        Double total = wuyefeiDetailDO.getYingpay() - p;
                        wuyeAmortize.setYing(total);
                        wuyeAmortize.setUnpay(total);
                        wuyeAmortize.setPayed((double) 0);
                    }
                }
                String d = sdf.format(c.getTime());
                wuyeAmortize.setMonth(d);
                wuyeAmortize.setWuyefeiId(wuyefeiDetailDO.getWuyefeiId());
                wuyeAmortizes.add(wuyeAmortize);
                c.add(Calendar.MONTH, 1);
            }
        }
        wuyefeiManageService.batchAmortizes(wuyeAmortizes);
        for (WuyefeiDetailDO wuyefeiDetailDO : wuyefeiDetailDOs) {
            List<WuyeAmortize> wuyeAmortizes1 = wuyefeiManageService.getWuyeAmortize(wuyefeiDetailDO);
            if (wuyeAmortizes.size() > 0) {
                Double payed = wuyefeiDetailDO.getPayed();
                Double m3 = Double.valueOf(0);//累加金额
                for (WuyeAmortize w : wuyeAmortizes1) {
                    Double money = m3 + w.getYing();
                    boolean isBreak = false;
                    if (payed >= money) {
                        w.setPayed(w.getYing());
                        w.setUnpay(Double.valueOf(0));
                    } else {
                        w.setPayed(payed - m3);
                        w.setUnpay(w.getYing() - w.getPayed());
                        isBreak = true;
                    }
                    m3 += w.getYing();
                    wuyefeiManageService.updateAmortize(w);
                    if (isBreak) {
                        break;
                    }
                }
            }
        }
        return R.ok();
    }
    @PostMapping("exportXls")
    public void exportXls(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("凭证导入导出");
        Long deptId=ShiroUtils.getUser().getDeptId();
        wuyefeiManageService.removeExport(deptId);
        ExportDO exportDO=new ExportDO();
        exportDO.setCode(code);
        exportDO.setName(name);
        exportDO.setDeptId(deptId);
        wuyefeiManageService.saveExport(exportDO);
        Map<String, Object> params=new HashedMap();
        params.put("offset",1);
        params.put("limit",1);
        Query query=new Query(params);
        query.put("wuyefeiId",id);
        query.put("limit",null);
        query.put("offset",null);
        HSSFRow row = null;
        HSSFCell cell = null;
        List<WuyefeiDetailDO> wuyefeiDOList = wuyefeiManageService.detailList(query);
        row = sheet.createRow(0);
        for (int j = 0; j <= 26; j++) {
            cell = row.createCell(j);
        }
        row.getCell(0).setCellValue("制单日期");
        row.getCell(1).setCellValue("凭证类别");
        row.getCell(2).setCellValue("凭证编号");
        row.getCell(3).setCellValue("来源类型");
        row.getCell(4).setCellValue("差异凭证");
        row.getCell(5).setCellValue("附单据数");
        row.getCell(6).setCellValue("摘要");
        row.getCell(7).setCellValue("科目编码");
        row.getCell(8).setCellValue("科目");
        row.getCell(9).setCellValue("币种");
        row.getCell(10).setCellValue("汇率");
        row.getCell(11).setCellValue("数量");
        row.getCell(12).setCellValue("单价");
        row.getCell(13).setCellValue("借贷方向");
        row.getCell(14).setCellValue("原币");
        row.getCell(15).setCellValue("本币");
        row.getCell(16).setCellValue("票据号");
        row.getCell(17).setCellValue("票据日期");
        row.getCell(18).setCellValue("业务单号");
        row.getCell(19).setCellValue("业务日期");
        row.getCell(20).setCellValue("到期日");
        row.getCell(21).setCellValue("业务员编码");
        row.getCell(22).setCellValue("业务员");
        row.getCell(23).setCellValue("银行帐号");
        row.getCell(24).setCellValue("结算方式");
        row.getCell(25).setCellValue("往来单位编码");
        row.getCell(26).setCellValue("往来单位");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
        for (int i = 0; i < wuyefeiDOList.size(); i++) {
            row = sheet.createRow(i+1);
            for (int j = 0; j <= 26; j++) {
                row.createCell(j);
            }
            WuyefeiDetailDO wuyefeiDetailDO=wuyefeiDOList.get(i);
            String zhaiyao=wuyefeiDetailDO.getName()+" "+wuyefeiDetailDO.getBuildingName()+
                    wuyefeiDetailDO.getFloor()+"层"+wuyefeiDetailDO.getRoomCode()+"("+sdf.format(wuyefeiDetailDO.getStartDate())+"-"+sdf.format(wuyefeiDetailDO.getEndDate())+")";
            row.getCell(6).setCellValue(zhaiyao);
            row.getCell(7).setCellValue(code);
            row.getCell(8).setCellValue(name);
            row.getCell(9).setCellValue("人民币");
            row.getCell(10).setCellValue("1.0000");
            row.getCell(13).setCellValue("借方");
            row.getCell(14).setCellValue(wuyefeiDetailDO.getYingpay());
            row.getCell(15).setCellValue(wuyefeiDetailDO.getYingpay());
            row.getCell(26).setCellValue(wuyefeiDetailDO.getBuildingName()+"-"+
                    wuyefeiDetailDO.getFloor()+"层-"+wuyefeiDetailDO.getRoomCode());
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=pingzheng.xls");
        OutputStream ouputStream = response.getOutputStream();
        workbook.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }
}
