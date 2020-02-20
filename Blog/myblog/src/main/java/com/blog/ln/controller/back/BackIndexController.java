package com.blog.ln.controller.back;



import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.ln.bean.ArticleInfo;
import com.blog.ln.bean.MessageInfo;
import com.blog.ln.bean.UserInfo;
import com.blog.ln.service.ArticleService;
import com.blog.ln.service.MessageService;
import com.blog.ln.service.UserInfoService;
import com.blog.ln.utils.PageBean;

@Controller
@RequestMapping("/back/")
public class BackIndexController {
	/**
	 * 用户管理的业务逻辑接口
	 * @author 39710
	 *
	 */
	@Autowired
	private UserInfoService userservice;
	
	/**
	 * 文章栏目管理业务逻辑接口
	 */
	@Autowired
	private ArticleService articleservice;
	
	/**
	 * 留言管理接口
	 */
	@Autowired
	private MessageService messageservice;

	@RequestMapping("login")
	public String login(HttpSession session) {
		//清除session值
		session.invalidate();
		return "back/login";
	}
	
	@RequestMapping("index")
	public String index() {
		return "back/index";
	}
	
	@RequestMapping("main")
	public String main(ArticleInfo artinfo, Model model, HttpServletRequest request, MessageInfo messageinfo) {
		//获取ip
		String remoteAddr = request.getRemoteAddr();
		model.addAttribute("remoteAddr",remoteAddr);
		model.addAttribute("usercount", userservice.getCount());
		
		//查询文章总数
		int intValue = articleservice.getArticleCount(artinfo).intValue();
		model.addAttribute("intValue",intValue);
		
		//查询留言总数
		int meaount = messageservice.messamount(messageinfo).intValue();
		model.addAttribute("meaount",meaount);
		
		return "back/main";
	}
	
	/**
	 * 	用户登陆
	 * @param user
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("userlogin")
	public String userlogin(UserInfo user, Model model, HttpSession session) {
		UserInfo login = userservice.isLogin(user);
		
		if(login != null) {
			session.setAttribute("login", login);
			
			//获取登陆时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			session.setAttribute("logintime", sdf.format(date));
			
			return "back/index";
		}else {
			model.addAttribute("info","账号或密码错误");
			return "back/login";
		}
	}
	
}
