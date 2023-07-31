package com.service.impl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.entity.Article;
import com.entity.Category;
import com.entity.Tag;
import com.github.pagehelper.*;
import com.mapper.ArticleMapper;
import com.service.ArticleService;
@Service
public class ArticleSericeImpl implements ArticleService {
	@Resource
	private ArticleMapper articleMapper;

	public List<Article> listRecentArticle(Integer n) {
		return articleMapper.listRecentArticle(n);
	}
	public PageInfo<Article> getPageArticleList(Integer pageIndex, Integer pageSize) {
		
		PageHelper.startPage(pageIndex,pageSize);
		
		List<Article> articleList =articleMapper.findAll(); 
		
		//��ÿ������,��Ҫ������Ӧ�Ĵ����,С������Ϣ�����
		for(Article a:  articleList) {
			//ÿ������Ҫ��ӷ�����Ϣ, Ƿ��
			List<Category> categoryList=articleMapper.listCategoryByArticleId(a.getArticleId());
			a.setCategoryList(categoryList);
		}
		
		return  new PageInfo<Article>(articleList);
	}
	
	public void add(Article article) {
		//�����±���������
		articleMapper.addArticle(article);
		
		//�����·������������
		List<Category>categorylist=article.getCategoryList();
		for(Category c:categorylist ) {
			articleMapper.addArticleCategory(article.getArticleId(), c.getCategoryId());
		}
		
		//�����±�ǩ����������
		List<Tag> tagList=article.getTagList();
		for(Tag t: tagList) {
			articleMapper.addArticleTag(article.getArticleId(),t.getTagId());
		}
	}
}