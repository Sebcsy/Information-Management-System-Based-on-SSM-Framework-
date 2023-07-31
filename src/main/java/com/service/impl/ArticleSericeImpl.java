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
		
		//对每个文章,都要把它对应的大分类,小分类信息查出来
		for(Article a:  articleList) {
			//每个文章要添加分类信息, 欠账
			List<Category> categoryList=articleMapper.listCategoryByArticleId(a.getArticleId());
			a.setCategoryList(categoryList);
		}
		
		return  new PageInfo<Article>(articleList);
	}
	
	public void add(Article article) {
		//往文章表中添数据
		articleMapper.addArticle(article);
		
		//往文章分类表中添数据
		List<Category>categorylist=article.getCategoryList();
		for(Category c:categorylist ) {
			articleMapper.addArticleCategory(article.getArticleId(), c.getCategoryId());
		}
		
		//往文章标签表中添数据
		List<Tag> tagList=article.getTagList();
		for(Tag t: tagList) {
			articleMapper.addArticleTag(article.getArticleId(),t.getTagId());
		}
	}
}