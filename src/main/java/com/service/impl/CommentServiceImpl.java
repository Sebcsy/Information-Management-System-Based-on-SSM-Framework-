package com.service.impl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.entity.Article;
import com.entity.Comment;
import com.mapper.ArticleMapper;
import com.mapper.CommentMapper;
import com.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	@Resource
	private CommentMapper commentMapper;
	
	@Resource
	private ArticleMapper artileMapper;

	/**
	 * ��ѯ�����n��������Ϣ,ÿ��������Ϣ��,�����б����۵����µ���Ϣ
	 */
	public List<Comment> listRecentComment(int n) {
		
		List<Comment> commentList= commentMapper.listRecentComment(n);
		
		for(int i=0;i<commentList.size();i++) {
			
			//�õ������۵����µ�id
			int commetArticleId=commentList.get(i).getCommentArticleId();
			
			//��������id�õ�������Ϣ
			Article article=artileMapper.getArticleById(commetArticleId);
			
			//��������Ϣ��������
			commentList.get(i).setArticle(article);
			
		}
		
		return commentList;
	}
}