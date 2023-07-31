package com.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.Article;
import com.entity.Category;

public interface ArticleMapper {
	/**
	 * ��ѯ��������ǰn������
	 * @param n ��ѯ��������
	 * @return �����б�
	 */
	List<Article> listRecentArticle(Integer n);
	Article getArticleById(int commetArticleId);
	/**
	 * ��ѯ���е�����
	 * @return ���б�
	 */
	List<Article> findAll();
	/**
	 * ��������id,��ѯ���������ķ���(�����,С����)
	 * @param articleId ����id
	 * @return �����б�
	 */
	List<Category> listCategoryByArticleId(Integer articleId);
	
	/**
	 * �����±����������
	 * @param article ,�����������շ��ص���������
	 */
	void addArticle(Article article);

	/**
	 * �����·�������������
	 * @param articleId ����id
	 * @param categoryId ����id
	 */
	void addArticleCategory(@Param("articleId") Integer articleId, @Param("categoryId")  Integer categoryId);

	/**
	 * �����±�ǩ�����������
	 * @param articleId ����id
	 * @param tagId ��ǩid
	 */
	void addArticleTag(@Param("articleId")  Integer articleId, @Param("tagId")  Integer tagId);
	
}