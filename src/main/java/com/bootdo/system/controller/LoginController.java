package com.bootdo.system.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.DeptService;
import com.bootdo.system.service.MenuService;
import io.swagger.models.auth.In;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MenuService menuService;
	@Autowired
	FileService fileService;
	@Autowired
	DeptService deptService;
	@GetMapping({ "/", "" })
	String welcome(Model model) {

		return "redirect:/login";
	}

	@Log("请求访问主页")
	@GetMapping({ "/index" })
	String index(Model model) {
		List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
		model.addAttribute("menus", menus);
		model.addAttribute("user", ShiroUtils.getUser());
		model.addAttribute("name", getUser().getName());
		FileDO fileDO = fileService.get(getUser().getPicId());
		if(fileDO!=null&&fileDO.getUrl()!=null){
			if(fileService.isExist(fileDO.getUrl())){
				model.addAttribute("picUrl",fileDO.getUrl());
			}else {
				model.addAttribute("picUrl","/img/photo_s1.png");
			}
		}else {
			model.addAttribute("picUrl","/img/photo_s1.png");
		}
		model.addAttribute("username", getUser().getUsername());
		model.addAttribute("deptName", getUser().getDeptName());
		return "index_v1";
	}

	@GetMapping("/login")
	String login() {
		return "login";
	}

	@Log("登录")
	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String username, String password) {

		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}

	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/login";
	}

	/*@GetMapping("/main")
	String main() {

		return "main";
	}*/

	@GetMapping("/main")
	String main(Model model) {
		model.addAttribute("deptId", getDeptId());
		List<Long> ids=ShiroUtils.getUser().getRoleIds();
		boolean checkAdmin=false;
		for(Long i:ids){
			if(i==65){
				checkAdmin=true;
				break;
			}
		}
		if(checkAdmin){
			model.addAttribute("role","admin");
		}else{
			model.addAttribute("role","normal");
		}
		return "main";
	}
	@GetMapping("/changeDept")
	String changeDept() {
		return "changeDept";
	}
	@PostMapping("/changeDeptId")
	@ResponseBody()
	R changeDept(Long id) {
		UserDO userDO=ShiroUtils.getUser();
		DeptDO deptDO=deptService.get(id);
		userDO.setDeptId(id);
		userDO.setDeptName(deptDO.getName());
		return R.ok();
	}
	public  static void  main(String[] a){
		System.out.println(MD5Utils.encrypt("15076374623", "111111"));
	}
}
