package com.blog.ln.controller.back;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.ln.bean.UserInfo;
import com.blog.ln.service.UserInfoService;
import com.blog.ln.utils.PageBean;

/**
 * 用户管理的控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/user/")
public class UserInfoController {
	
	@Autowired
	private UserInfoService userservice;

	/**
	 * 根据分页查询用户信息
	 * @param user
	 * @param model
	 * @param page 
	 * @return
	 */
	@RequestMapping("list")
	public String list(UserInfo user,Model model,Integer page) {
		
		PageBean<UserInfo> pageBean = userservice.getList(user, page);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("userinfo", user);
		
		
		return "back/userinfo/userinfo_list";
	}
	
	//跳转至添加页面
	@RequestMapping("loadadd")
	public String loadadd(Model model, Integer page) {
		model.addAttribute("page",page);
		return "back/userinfo/userinfo_add";
	}
	
	//添加用户信息
	@RequestMapping("add")
	public String add(UserInfo user, Model model, Integer page) {
		boolean add = userservice.add(user);
		
		if(add) {
			model.addAttribute("info", "用户添加成功！");
		}else {
			model.addAttribute("info", "用户添加失败！");
		}
		model.addAttribute("page",page);
		model.addAttribute("user",user);
		return "back/userinfo/userinfo_add";
	}
	
	//加载修改页面
	@RequestMapping("loadUpdate")
	public String loadUpdate(UserInfo user, Model model, Integer page) {
		
		UserInfo userinfo = userservice.getUser(user);
		model.addAttribute("user", userinfo);
		model.addAttribute("page",page);
		return "back/userinfo/userinfo_update";
	}
	
	//修改用户信息
	@RequestMapping("update")
	public String userUpdate(UserInfo user, Model model, Integer page) {
		boolean update = userservice.update(user);
		if(update) {
			model.addAttribute("info", "用户修改成功！");
		}else {
			model.addAttribute("info", "用户修改失败！");
		}
		model.addAttribute("user", user);
		model.addAttribute("page",page);
		return "back/userinfo/userinfo_update";
	}
	
	//删除用户信息
	@RequestMapping("delete")
	public String delete(UserInfo user, Integer page) {
		userservice.delete(user);
		
		return "redirect:/back/user/list?page="+page;
	}
	
}
