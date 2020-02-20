package com.blog.ln.controller.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.ln.bean.CategoryInfo;
import com.blog.ln.service.CategoryInfoService;

/**
 * 栏目管理的控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/back/category/")
public class CategoryController {
	
	@Autowired
	private CategoryInfoService cateService;
	
	/**
	 * 查询所有栏目信息
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String list(Model model) {
		CategoryInfo catinfo = new CategoryInfo();
		List<CategoryInfo> catelist = cateService.list(catinfo);
		model.addAttribute("catelist", catelist);
		
		return "back/category/category";
	}
	
	/**
	 * 添加栏目信息
	 * @param cateinfo
	 * @param model
	 * @return
	 */
	@RequestMapping("add")
	public String add(CategoryInfo cateinfo, Model model) {
		boolean add = cateService.add(cateinfo);
		if(add) {
			model.addAttribute("info", "添加栏目成功!");
		}else {
			model.addAttribute("info", "添加栏目失败!");
		}
		
		return "back/category/category_info";
	}
	
	/**
	 * 跳转到修改页面同时根据编号查询栏目信息
	 * @param model
	 * @param categoryId
	 * @return
	 */
	@RequestMapping("get/{id}")
	public String loadupdate(Model model, @PathVariable("id") Integer categoryId) {
		CategoryInfo categoryInfo = cateService.getCategoryInfo(categoryId);
		model.addAttribute("categoryInfo", categoryInfo);
		
		return "back/category/category_update";
	}
	
	/**
	 * 栏目修改
	 * @param cateinfo
	 * @param model
	 * @return
	 */
	@RequestMapping("update")
	public String update(CategoryInfo cateinfo, Model model) {
		boolean update = cateService.update(cateinfo);
		
		if(update) {
			model.addAttribute("info", "修改成功！");
		}else {
			model.addAttribute("info", "修改失败！");
		}
		
		return "back/category/category_update";
	}
	
	@RequestMapping("del/{id}")
	public String delete(@PathVariable("id") Integer categoryId, Model model) {
		try {
			cateService.delete(categoryId);
			model.addAttribute("info", "删除栏目成功！");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("info", "删除栏目失败！");
		}
		
		return "back/category/category_info";
	}

}
