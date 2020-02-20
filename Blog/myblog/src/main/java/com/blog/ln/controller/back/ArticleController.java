package com.blog.ln.controller.back;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.blog.ln.bean.ArticleInfo;
import com.blog.ln.bean.CategoryInfo;
import com.blog.ln.bean.UserInfo;
import com.blog.ln.dao.ArticleInfoMapper;
import com.blog.ln.service.ArticleService;
import com.blog.ln.service.CategoryInfoService;
import com.blog.ln.utils.PageBean;

/**
 * 文章管理的控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/article/")
public class ArticleController {

	/**
	 * 栏目管理业务逻辑接口
	 */
	@Autowired
	private CategoryInfoService cateservice;
	
	/**
	 * 文章栏目管理业务逻辑接口
	 */
	@Autowired
	private ArticleService articleservice;
	
	@RequestMapping("list")
	public String list(ArticleInfo artinfo, Model model, Integer page) {
		PageBean<ArticleInfo> pagebean = articleservice.getArticleList(artinfo, page);
		model.addAttribute("pageBean", pagebean);
		model.addAttribute("artinfo", artinfo);
		
		CategoryInfo catinfo = new CategoryInfo();
		List<CategoryInfo> catelist = cateservice.list(catinfo);
		model.addAttribute("catelist",catelist);
		
		return "back/article/article_list";
	}
	
	/**
	 * 加载文章添加页面
	 * @return
	 */
	@RequestMapping("loadadd")
	public String loadadd(Model model) {
		//获取栏目信息
		CategoryInfo catinfo = new CategoryInfo();
		List<CategoryInfo> catelist = cateservice.list(catinfo);
		model.addAttribute("catelist",catelist);
		
		return "back/article/article_add";
	}
	
	/**
	 * 添加文章信息
	 */
	@RequestMapping("add")
	public String add(ArticleInfo article, Model model, HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute("login");
		if(user != null){
			article.setUserId(user.getUserId());
			article.setArticleTime(new Date());
		}
		
		boolean add = articleservice.add(article);
		if(add) {
			model.addAttribute("info", "添加文章成功");
		}else {
			model.addAttribute("info", "添加文章失败");
		}
		
		CategoryInfo catinfo = new CategoryInfo();
		List<CategoryInfo> catelist = cateservice.list(catinfo);
		model.addAttribute("catelist",catelist);
		return "back/article/article_add";
	}
	
	/**
	 * 上传图片
	 * @param upload
	 * @return
	 */
	@RequestMapping("upload")
	@ResponseBody
	public String upload(@RequestParam MultipartFile upload) {
		String url = articleservice.doPutFile(upload);
		
		return url;
	}
	
	/**
	 * 文本编辑器中上传图片(富文本编辑)
	 * @param upload
	 * @return
	 */
	@RequestMapping("uploadfile")
	public void uploadfile(@RequestParam MultipartFile upload, HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("UTF-8"); 
			String url = articleservice.doPutFile(upload);
			out = response.getWriter();
			String callBack = request.getParameter("CKEditorFuncNum");
			out.println("<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction(" + callBack + ",'" + url + "')</script>");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 跳转到修改页面
	 * @param article
	 * @param model
	 * @return
	 */
	@RequestMapping("loadupdate")
	public String loadupdate(ArticleInfo article, Model model, Integer page) {
		ArticleInfo articleInfo = articleservice.getArticleInfo(article);
		model.addAttribute("update", articleInfo);
		
		//查询栏目
		CategoryInfo catinfo = new CategoryInfo();
		List<CategoryInfo> catelist = cateservice.list(catinfo);
		model.addAttribute("catelist",catelist);
		model.addAttribute("page",page);
		
		return "back/article/article_update";
	}
	
	/**
	 * 修改文章
	 * @param article
	 * @param model
	 * @return
	 */
	@RequestMapping("artupdate")
	public String artupdate(ArticleInfo artinfo, Model model, HttpSession session, Integer page) {
		UserInfo user = (UserInfo) session.getAttribute("login");
		if(user != null){
			artinfo.setUserId(user.getUserId());
			artinfo.setArticleTime(new Date());
		}
		
		boolean add = articleservice.artupdate(artinfo);
		if(add) {
			model.addAttribute("info", "修改文章成功");
		}else {
			model.addAttribute("info", "修改文章失败");
		}
		
		CategoryInfo catinfo = new CategoryInfo();
		List<CategoryInfo> catelist = cateservice.list(catinfo);
		model.addAttribute("catelist",catelist);
		model.addAttribute("update",artinfo);
		model.addAttribute("page",page);
		
		return "back/article/article_update";
	}
	
	/**
	 * 根据文章id删除文章
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("deleteart")
	public String deleteart(ArticleInfo id, Model model, Integer page) {
		boolean delete = articleservice.deleteart(id);
		if(delete) {
			model.addAttribute("info","文章删除成功！");
		}else {
			model.addAttribute("info","文章删除失败！");
		}
		model.addAttribute("page",page);
		
		return "back/article/article_info";
	}
	
}
