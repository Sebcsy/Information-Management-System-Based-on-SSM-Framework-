package com.service;
import java.util.List;
import com.entity.Article;
import com.github.pagehelper.*;

public interface ArticleService {
	/**
	 * ��ѯ��������ǰn������
	 * @param n ��ѯ��������
	 * @return �����б�
	 */
	List<Article> listRecentArticle(Integer n);
	
	PageInfo<Article> getPageArticleList(Integer pageIndex, Integer pageSize);
	
	public void add(Article article) ;
}