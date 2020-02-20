package com.blog.ln.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.blog.ln.bean.ArticleInfo;
import com.blog.ln.bean.UserInfo;
import com.blog.ln.utils.PageBean;

/**
 * 文章管理的业务逻辑接口
 * @author 39710
 *
 */
public interface ArticleService {

	/**
	 * 添加文章信息
	 * @param article
	 * @return
	 */
	public boolean add(ArticleInfo article);
	
	/**
	 * 修改文章信息
	 * @param article
	 * @return
	 */
	public boolean artupdate(ArticleInfo article);
	
	/**
	 * 文件上传
	 * @param file
	 * @return
	 */
	public String doPutFile(MultipartFile file);
	
	/**
	 * 查询文章并分页条件
	 * @param user 查询条件
	 * @param page 页数
	 * @return
	 */
	public PageBean<ArticleInfo> getArticleList(ArticleInfo artinfo,Integer page);
	
	/**
	 * 根据编号查询文章信息
	 * @param artinfo
	 * @return
	 */
	public ArticleInfo getArticleInfo(ArticleInfo artinfo);
	
	/**
	 * 文章删除
	 * @param id
	 * @return
	 */
	public boolean deleteart(ArticleInfo id);
	
	/**
	 * 查询最新的十五条记录信息
	 * @return
	 */
	public List<ArticleInfo> getNewArticleList();
	
	/**
	 * 查询站长推荐文章
	 * @return
	 */
	public List<ArticleInfo> getArtList();
	
	/**
	 * 根据id查询文章信息
	 * @param artinfo
	 * @return
	 */
	public ArticleInfo getArticleInfo(Integer artinfo);
	
	/**
	 * 根据id查询文章作者信息
	 * @param id
	 * @return
	 */
	public ArticleInfo selectwriter(Integer id);
	
	/**
	 * 查询文章总数
     * @param info
     * @return
     */
    public Long getArticleCount(ArticleInfo info);
	
}
