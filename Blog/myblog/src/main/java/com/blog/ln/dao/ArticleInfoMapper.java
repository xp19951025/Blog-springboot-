package com.blog.ln.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.blog.ln.bean.ArticleInfo;

/**
 * 文章管理接口
 * @author 39710
 *
 */
@Mapper
public interface ArticleInfoMapper {
    int deleteByPrimaryKey(ArticleInfo articleId);

    int insert(ArticleInfo record);

    int insertSelective(ArticleInfo record);

    ArticleInfo selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(ArticleInfo record);

    int updateByPrimaryKey(ArticleInfo record);
    
    /**
     * 根据栏目id删除文章信息
     * @param categoryId
     * @return
     */
    public int deleteCategoryId(Integer categoryId);
    
    /**
     * 根据条件查询文章信息
     * @param info
     * @return
     */
    public List<ArticleInfo> getArticleList(ArticleInfo info);
    
    /**
     * 查询最新的15条文章信息
     * @param info
     * @return
     */
    public List<ArticleInfo> getNewArticleList(ArticleInfo info);
    
    /**
     * 查询文章总数
     * @param info
     * @return
     */
    public Long getArticleCount(ArticleInfo info);
    
    /**
	 * 根据id查询文章作者信息
	 * @param id
	 * @return
	 */
	public ArticleInfo selectwriter(Integer id);
    
}