package com.blog.ln.bean;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @Document 加上此注解后，默认的情况下这个实体中的所有属性都会被创建索引，并且分词
 * indexName 索引库的名称，建议以项目命名
 * type 类型 建议以实体类名称命名
 * shards 默认的分区数
 * replicas 每个分区的备份数
 * refreshInterval 刷新的间隔时间
 * @author 39710
 *
 */
@Document(indexName = "myblogs", type="AritcleInfoBean", shards = 1, replicas = 0, refreshInterval = "-1")
public class ArticleInfoBean {
	
	@Id
	private Integer articleId;
	
	@Field
	private String articleTitle;

	@Field
    private String articleContent;

	@Field
    private String articleImg;

	@Field
    private Date articleTime;
	
	@Field
	private String articleMark;
	
	@Field
	private String articleRecom;
	
	public String getArticleRecom() {
		return articleRecom;
	}

	public void setArticleRecom(String articleRecom) {
		this.articleRecom = articleRecom;
	}

	public String getArticleMark() {
		return articleMark;
	}

	public void setArticleMark(String articleMark) {
		this.articleMark = articleMark;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public String getArticleImg() {
		return articleImg;
	}

	public void setArticleImg(String articleImg) {
		this.articleImg = articleImg;
	}

	public Date getArticleTime() {
		return articleTime;
	}

	public void setArticleTime(Date articleTime) {
		this.articleTime = articleTime;
	}
	
}
