package com.blog.ln.service;

import java.util.List;

import com.blog.ln.bean.CategoryInfo;

/**
 * 栏目管理的业务逻辑接口
 * @author 39710
 *
 */
public interface CategoryInfoService {

	/**
	 * 查询所有栏目信息
	 * @return
	 */
	public List<CategoryInfo> list(CategoryInfo info);
	
	/**
	 * 添加查询所有栏目信息
	 * @return
	 */
	public boolean add(CategoryInfo cateinfo);
 	
	/**
	 * 根据编号查询栏目信息
	 * @param categoryId
	 * @return
	 */
	public CategoryInfo getCategoryInfo(Integer categoryId);
	
	/**
	 * 栏目修改
	 * @param cateinfo
	 * @return
	 */
	public boolean update(CategoryInfo cateinfo);
	
	/**
	 * 根据栏目id删除栏目以及栏目下的文章
	 * @param categoryId
	 * @throws Exception 
	 */
	public void delete(Integer categoryId) throws Exception;
	
}
