package com.entity;
public class Tag {
	private Integer tagId;
	private String tagName;
	private String tagDescription; //代表这个标签下有多少文章
	private Integer articleCount;
	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	public Tag(){}
	
	public Tag( Integer tagId) {
		this.tagId=tagId;
	}
	
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getTagDescription() {
		return tagDescription;
	}
	public void setTagDescription(String tagDescription) {
		this.tagDescription = tagDescription;
	}

}