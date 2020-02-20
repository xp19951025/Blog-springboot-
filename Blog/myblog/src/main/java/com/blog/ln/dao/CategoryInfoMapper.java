package com.blog.ln.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.blog.ln.bean.CategoryInfo;

@Mapper
public interface CategoryInfoMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(CategoryInfo record);

    int insertSelective(CategoryInfo record);

    CategoryInfo selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(CategoryInfo record);

    int updateByPrimaryKey(CategoryInfo record);
    
    /**
     * 查询所有栏目信息
     * @return
     */
    public List<CategoryInfo> getCateList();
}