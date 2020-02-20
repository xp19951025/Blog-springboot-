package com.blog.ln.controller.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.ln.bean.ArticleInfo;
import com.blog.ln.bean.MessageInfo;
import com.blog.ln.service.MessageService;

/**
 * 留言管理的控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/message/")
public class MessageController {

	@Autowired
	private MessageService messageservice;
	
	/**
	 * 查询所有留言
	 * @param info
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String list(MessageInfo info, Model model) {
		List<MessageInfo> list = messageservice.getList(info);
		model.addAttribute("melist", list);
		model.addAttribute("messageInfo", info);
		
		return "back/message/message_list";
	}
	
	/**
	 * 修改留言状态
	 * @param info
	 * @param model
	 * @return
	 */
	@RequestMapping("update")
	public String update(MessageInfo info, Model model) {
		//修改状态
		messageservice.update(info);
		
		List<MessageInfo> list = messageservice.getList(info);
		model.addAttribute("melist", list);
		
		return "back/message/message_list";
	}
	
	/**
	 * 根据留言id删除
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("deleteme/{id}")
	public String deleteme(@PathVariable("id") Integer id, Model model) {
		System.out.println(id);
		boolean delete = messageservice.deleteme(id);
		if(delete) {
			model.addAttribute("info","留言删除成功！");
		}else {
			model.addAttribute("info","留言删除失败！");
		}
		
		return "back/message/message_info";
	}
	
	
	
}
