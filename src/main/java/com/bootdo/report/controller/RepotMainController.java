package com.bootdo.report.controller;

import com.bootdo.business.domain.PayCountDO;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.report.domain.AmortizeReportDO;
import com.bootdo.report.domain.ReportDO;
import com.bootdo.report.service.RepotMainService;
import com.bootdo.wuye.domain.QunuanfeiDO;
import com.bootdo.wuye.domain.WuyefeiDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/report/main")
public class RepotMainController  extends BaseController {
    private String prefix = "report/main";
    @Autowired
    private RepotMainService repotMainService;

    @ResponseBody
    @GetMapping("/getWuyefeiLv")
    WuyefeiDO getWuyefeiLv(WuyefeiDO w, Model model) {
        w.setDeptId(ShiroUtils.getUser().getDeptId());
        List<WuyefeiDO> wuyefeiDOList = repotMainService.getWuyefei(w);
        if(wuyefeiDOList.size()==0){
            return null;
        }
        w=wuyefeiDOList.get(0);
        w.setDeptId(ShiroUtils.getUser().getDeptId());
        WuyefeiDO wuyefeiDO = repotMainService.getSum(w);
        wuyefeiDO.setEndYear(w.getEndYear());
        wuyefeiDO.setStartYear(w.getStartYear());
        wuyefeiDO.setName(w.getName());
        return wuyefeiDO;
    }
    @ResponseBody
    @GetMapping("/getQunuanfeiLv")
    QunuanfeiDO getQunuanfeiLv(WuyefeiDO w, Model model) {
        w.setDeptId(ShiroUtils.getUser().getDeptId());
        List<WuyefeiDO> wuyefeiDOList = repotMainService.getQunuanfei(w);
        if(wuyefeiDOList.size()==0){
            return null;
        }
        w=wuyefeiDOList.get(0);
        w.setDeptId(ShiroUtils.getUser().getDeptId());
        QunuanfeiDO q = repotMainService.getQunuanfeiSum(w);
        q.setEndYear(String.valueOf(w.getEndYear()));
        q.setStartYear(String.valueOf(w.getStartYear()));
        q.setName(w.getName());
        return q;
    }
    @ResponseBody
    @GetMapping("/getWuyefeiAmortize")
    List<AmortizeReportDO> getWuyefeiAmortize(@RequestParam Map<String, Object> params) {
        int year= Integer.parseInt(params.get("year").toString());
        ReportDO reportDOsin = new ReportDO();
        reportDOsin.setDeptId(ShiroUtils.getUser().getDeptId());
        List<ReportDO> reportDOs = new ArrayList<>();
        for(int i=1;i<=12;i++){
            ReportDO reportDO=new ReportDO();
            reportDO.setName(""+i);
            if(i<10){
                reportDO.setMonth(year+"-0"+i);
            }else {
                reportDO.setMonth(year+"-"+i);
            }
            reportDOs.add(reportDO);
        }
        List<AmortizeReportDO> amortizeReportDO = repotMainService.getWuyefeiAmortize(reportDOs,reportDOsin);
        return amortizeReportDO;
    }
}
