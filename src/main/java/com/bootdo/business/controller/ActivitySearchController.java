package com.bootdo.bussiness;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 楼宇管理
 *
 * @author ZYG
 * @email
 * @date 2018-09-16
 */

@Controller
@RequestMapping("/business/activitysearch")
public class ActivitySearchController {
    //private MerchantService merchantService;

    @GetMapping()
    @RequiresPermissions("business:activitysearch:activitysearch")
    String Activity(){
        return "business/activitysearch/activitysearch";
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List ownerList = null;
		/*List<MerchantDO> merchantList = merchantService.list(query);
		int total = areamerchantService.count(query);*/
        //PageUtils pageUtils = new PageUtils(merchantList, total);
        PageUtils pageUtils = new PageUtils(ownerList, 1);
        return pageUtils;
    }

    @GetMapping("/add")
    String add(){
        return "business/activitysearch/add";
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") Integer id,Model model){
		/*MerchantDO building = merchantService.get(id);
		model.addAttribute("building", building);*/
        return "business/activitysearch/edit";
    }

    /**
     * 保存
     */
	/*@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("business:business:add")
	public R save( BuildingDO business){
		if(buildingService.save(business)>0){
			return R.ok();
		}
		return R.error();
	}*/
    /**
     * 修改
     */
	/*@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("business:business:edit")
	public R update( BuildingDO business){
		buildingService.update(business);
		return R.ok();
	}*/

    /**
     * 删除
     */
	/*@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("business:business:remove")
	public R remove( Integer id){
		if(buildingService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}*/

    /**
     * 批量删除
     */
	/*@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("business:business:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		buildingService.batchRemove(ids);
		return R.ok();
	}*/

}
