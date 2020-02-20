package com.blog.ln.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.ln.bean.CategoryInfo;
import com.blog.ln.dao.ArticleInfoMapper;
import com.blog.ln.dao.CategoryInfoMapper;
import com.blog.ln.service.CategoryInfoService;

@Service
public class CategoryInfoServiceImpl implements CategoryInfoService {

	/**
	 * 栏目管理
	 */
	@Autowired
	private CategoryInfoMapper categoryinfomapper;
	
	/**
	 * 文章管理
	 */
	@Autowired
	private ArticleInfoMapper  artmapper;
	
	/**
	 * 查询所有栏目信息
	 */
	@Cacheable(cacheNames = "liu", key = "#p0")
	public List<CategoryInfo> list(CategoryInfo info) {
		// TODO Auto-generated method stub
		
		return categoryinfomapper.getCateList();
	}
	
	/**
	 * 添加栏目信息
	 */
	@Override
	@CacheEvict(cacheNames = "liu", allEntries = true)
	public boolean add(CategoryInfo ceteinfo) {
		// TODO Auto-generated method stub
		try {
			int insertSelective = categoryinfomapper.insertSelective(ceteinfo);
			if(insertSelective > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * 根据编号查询栏目信息
	 */
	@Override
	public CategoryInfo getCategoryInfo(Integer categoryId) {
		// TODO Auto-generated method stub
		try {
			return categoryinfomapper.selectByPrimaryKey(categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 栏目修改
	 */
	@Override
	@CacheEvict(cacheNames = "liu", allEntries = true)
	public boolean update(CategoryInfo cateinfo) {
		// TODO Auto-generated method stub
		try {
			int updateByPrimaryKeySelective = categoryinfomapper.updateByPrimaryKeySelective(cateinfo);
			if(updateByPrimaryKeySelective > 0) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据栏目id删除栏目以及栏目下的文章
	 * @param categoryId
	 */
	@Transactional //事务处理
	@CacheEvict(cacheNames = "liu", allEntries = true)
	public void delete(Integer categoryId) throws Exception {
		//删除文章
		artmapper.deleteCategoryId(categoryId);
		//删除栏目
		categoryinfomapper.deleteByPrimaryKey(categoryId);
		
	}

}
