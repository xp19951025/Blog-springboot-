package com.blog.ln.controller.front;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.ln.bean.ArticleInfo;
import com.blog.ln.bean.ArticleInfoBean;
import com.blog.ln.bean.CategoryInfo;
import com.blog.ln.bean.MessageInfo;
import com.blog.ln.dao.ArticleInfoBeanRepository;
import com.blog.ln.service.ArticleService;
import com.blog.ln.service.CategoryInfoService;
import com.blog.ln.service.MessageService;
import com.blog.ln.utils.PageBean;

/**
 * 前台展示控制器
 * @author 39710
 *
 */
@Controller
@RequestMapping("/")
public class FrontIndexController {

	/**
	 * 栏目管理
	 */
	@Autowired
	private CategoryInfoService cateService;
	
	/**
	 * 留言
	 */
	@Autowired
	private MessageService messageservice;
	
	/**
	 * 文章管理
	 */
	@Autowired
	private ArticleService artservice;
	
	/**
	  * 全文检索管理
	 */
	@Autowired
	private ArticleInfoBeanRepository er;
	
	@RequestMapping("es")
	public String getlist(String name, Model model){
		if(name != null) {
			init(model);
			
			Pageable page = new PageRequest(0, 20);
			Page<ArticleInfoBean> pages = er.findDistinctArticleInfoBeanByArticleTitleContainingOrArticleContentContaining(name, name, page);
			List<ArticleInfoBean> list = pages.getContent();
			
			model.addAttribute("searchlist",list);
			model.addAttribute("name",name);

			return "/es";
		}else{
			return "redirect:/index";
		}
		
	}
	
	/**
	 * 初始化
	 */
	public void init(Model model) {
		CategoryInfo catinfo = new CategoryInfo();
		List<CategoryInfo> catelist = cateService.list(catinfo);
		model.addAttribute("catelist", catelist);
		
		//查询站长推荐的5条文章记录
		List<ArticleInfo> headartlist = artservice.getArtList();
		model.addAttribute("headartlist", headartlist);
	}
	
	/**
	 * 首页展示信息
	 * @param model
	 * @return
	 */
	@RequestMapping("index")
	public String index(ArticleInfo artinfo, Model model) {
		init(model);
		
		//查询最新的15条文章记录
		List<ArticleInfo> newArticleList = artservice.getNewArticleList();
		model.addAttribute("newArticleList", newArticleList);
		model.addAttribute("artinfo", artinfo);
		
		return "index";
	}
	
	/**
	 * 查询文章详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("listView/{id}")
	public String listView(@PathVariable("id") Integer id, Model model) {
		init(model);
		Integer up = id+1;
		Integer down = id-1;
		
		//上一篇文章查询
		ArticleInfo upart = artservice.getArticleInfo(up);
		//下一篇文章查询
		ArticleInfo downart = artservice.getArticleInfo(down);
		
		ArticleInfo artDate = artservice.getArticleInfo(id);
		//查询作者
		ArticleInfo selectwriter = artservice.selectwriter(id);
		
		model.addAttribute("artDate", artDate);
		model.addAttribute("upart", upart);
		model.addAttribute("downart", downart);
		model.addAttribute("selectwriter", selectwriter);
		
		return "listview";
	}
	
	/**
	 * 根据栏目查询文章信息
	 * @param artinfo
	 * @return
	 */
	@RequestMapping("listcat")
	public String listcat(ArticleInfo artinfo, Model model, Integer page) {
		init(model);
		
		PageBean<ArticleInfo> pagebean = artservice.getArticleList(artinfo, page);
		model.addAttribute("pageBean", pagebean);
		model.addAttribute("artinfo", artinfo);
		
		return "list";
	}
	
	/**
	 * 跳转到留言页面
	 */
	@RequestMapping("loadmeassage")
	public String loadmessage(MessageInfo messageinfo, Model model) {
		init(model);
		List<MessageInfo> list = messageservice.getList(messageinfo);
		model.addAttribute("melist", list);
		model.addAttribute("messageInfo", messageinfo);
		
		return "message";
	}
	
	/**
	 * 添加留言
	 * @param messageinfo
	 * @param model
	 * @return
	 */
	@RequestMapping("addmessage")
	public String addmessage(MessageInfo messageinfo, Model model, HttpSession session) {
		model.addAttribute("messageInfo", messageinfo);
		boolean addmessage = messageservice.addmessage(messageinfo);
		if(addmessage) {
			model.addAttribute("meinfo","留言成功！");
		}else {
			model.addAttribute("meinfo","留言失败！");
		}
		
		return "/frontmessage_info";
	}
	
}
