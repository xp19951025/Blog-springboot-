package com.blog.ln.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.ln.bean.ArticleInfo;
import com.blog.ln.bean.ArticleInfoBean;
import com.blog.ln.dao.ArticleInfoBeanRepository;
import com.blog.ln.dao.ArticleInfoMapper;
import com.blog.ln.service.ArticleService;
import com.blog.ln.utils.Const;
import com.blog.ln.utils.PageBean;
import com.blog.ln.utils.PageUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleInfoMapper articlemapper;
	
	@Autowired
	private ArticleInfoBeanRepository er;

	/**
	 * 添加文章
	 */
	@Override
	public boolean add(ArticleInfo article) {
		try {
			int insertSelective = articlemapper.insertSelective(article);
			
			//页面提交的数据保存到es中
			ArticleInfoBean aib = new ArticleInfoBean();
			aib.setArticleId(article.getArticleId());
			aib.setArticleTitle(article.getArticleTitle());
			aib.setArticleContent(article.getArticleContent());
			aib.setArticleImg(article.getArticleImg());
			aib.setArticleTime(article.getArticleTime());
			aib.setArticleMark(article.getArticleMark());
			aib.setArticleRecom(article.getArticleRecom());
			er.save(aib);
			
			if(insertSelective > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 文件上传
	 */
	@Override
	public String doPutFile(MultipartFile file) {
		try {
			//获取图片名称
			String filename = file.getOriginalFilename();
			//获取当前时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String format = sdf.format(new Date());
			
			String url = Const.FILE_URL+format+filename;
			
			//jersey
			Client client = new Client();
			WebResource resource = client.resource(url);
			
			//将文件转化为byte
			byte[] buf = file.getBytes();
			resource.put(String.class, buf);
			
			return url;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询文章并分页条件
	 */
	@Override
	public PageBean<ArticleInfo> getArticleList(ArticleInfo artinfo, Integer page) {
		//总记录数
		int allRow = articlemapper.getArticleCount(artinfo).intValue();
		//总页数
		int totalPage = PageUtil.countTotalPage(allRow, Const.PAGE_SIZE);
		//当前第几页
		int currentPage = PageUtil.countCurrentPage(page);
		//起始记录数
		int start = PageUtil.countStart(Const.PAGE_SIZE, currentPage);
		
		if(page>=0) {
			artinfo.setStart(start);
			artinfo.setLength(Const.PAGE_SIZE);
		}else {
			artinfo.setStart(-1);
			artinfo.setLength(-1);
		}
		
		List<ArticleInfo> arts = articlemapper.getArticleList(artinfo);
		
		PageBean<ArticleInfo> pageBean = new PageBean<>();
		pageBean.setAllRow(allRow);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(arts);
		
		return pageBean;
	}

	/**
	 * 文章删除
	 */
	@Override
	public boolean deleteart(ArticleInfo del) {
		try {
			int deleteByPrimaryKey = articlemapper.deleteByPrimaryKey(del);
			
			//删除
			ArticleInfoBean aib = new ArticleInfoBean();
			aib.setArticleId(del.getArticleId());
			er.delete(aib);
			
			if (deleteByPrimaryKey > 0) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * 根据文章编号查询文章信息
	 */
	@Override
	public ArticleInfo getArticleInfo(ArticleInfo artinfo) {
		try {
			return articlemapper.selectByPrimaryKey(artinfo.getArticleId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 修改文章
	 */
	@Override
	public boolean artupdate(ArticleInfo article) {
		try {
			int count = articlemapper.updateByPrimaryKeySelective(article);
			
			//修改操作的数据保存到es中
			ArticleInfoBean aib = new ArticleInfoBean();
			aib.setArticleId(article.getArticleId());
			aib.setArticleTitle(article.getArticleTitle());
			aib.setArticleContent(article.getArticleContent());
			aib.setArticleImg(article.getArticleImg());
			aib.setArticleTime(article.getArticleTime());
			aib.setArticleMark(article.getArticleMark());
			aib.setArticleRecom(article.getArticleRecom());
			System.out.println(article.getArticleRecom());
			er.save(aib);
			
			if(count > 0) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 查询最新的十五条记录信息
	 */
	@Override
	public List<ArticleInfo> getNewArticleList() {
		ArticleInfo info = new ArticleInfo();
		//是否发布
		info.setArticleMark(Const.MARK_YES);
		info.setStart(0);
		info.setLength(15);
		
		List<ArticleInfo> list = articlemapper.getNewArticleList(info);
		
		return list;
	}
	
	/**
	 * 查询站长推荐的5条文章信息
	 */
	@Override
	public List<ArticleInfo> getArtList() {
		ArticleInfo info = new ArticleInfo();
		info.setArticleRecom(Const.MARK_YES);
		info.setArticleMark(Const.MARK_YES);
		info.setStart(0);
		info.setLength(5);
		
		List<ArticleInfo> list = articlemapper.getNewArticleList(info);
		
		return list;
	}

	/**
	 * 根据id查询文章信息
	 */
	@Override
	public ArticleInfo getArticleInfo(Integer artinfo) {
		try {
			return articlemapper.selectByPrimaryKey(artinfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 根据id查询文章作者信息
	 */
	@Override
	public ArticleInfo selectwriter(Integer id) {
		try {
			return articlemapper.selectwriter(id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询文章总数
     * @param info
     * @return
     */
	@Override
	public Long getArticleCount(ArticleInfo info) {
		//总记录数
		return articlemapper.getArticleCount(info);
	}

}
