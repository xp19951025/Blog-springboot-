package com.blog.ln.bean;

import java.util.Date;

import com.blog.ln.utils.BaseBean;

public class ArticleInfo extends BaseBean{
    private Integer articleId;

    private Integer userId;

    private Integer categoryId;

    private String articleTitle;

    private String articleContent;

    private String articleImg;

    private String articleRecom;

    private Date articleTime;

    private String articleMark;
    
    private String categoryName;
    
    private String userName;

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }

    public String getArticleImg() {
        return articleImg;
    }

    public void setArticleImg(String articleImg) {
        this.articleImg = articleImg == null ? null : articleImg.trim();
    }

    public String getArticleRecom() {
        return articleRecom;
    }

    public void setArticleRecom(String articleRecom) {
        this.articleRecom = articleRecom == null ? null : articleRecom.trim();
    }

    public Date getArticleTime() {
        return articleTime;
    }

    public void setArticleTime(Date articleTime) {
        this.articleTime = articleTime;
    }

    public String getArticleMark() {
        return articleMark;
    }

    public void setArticleMark(String articleMark) {
        this.articleMark = articleMark == null ? null : articleMark.trim();
    }

	@Override
	public String toString() {
		return "ArticleInfo [articleId=" + articleId + ", userId=" + userId + ", categoryId=" + categoryId
				+ ", articleTitle=" + articleTitle + ", articleContent=" + articleContent + ", articleImg=" + articleImg
				+ ", articleRecom=" + articleRecom + ", articleTime=" + articleTime + ", articleMark=" + articleMark
				+ ", categoryName=" + categoryName + ", userName=" + userName + "]";
	}
    
}