package com.controller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.entity.Article;
import com.entity.Category;
import com.entity.Tag;
import com.entity.User;
import com.github.pagehelper.PageInfo;
import com.service.ArticleService;
import com.service.CategoryService;
import com.service.TagService;

import cn.hutool.http.HtmlUtil;
import cn.hutool.json.JSONObject;

/**
 * ������Ϣ,���Ʋ�
 * 
 */
@Controller   @RequestMapping("/article")
public class ArticleController {
	@Resource
	private ArticleService articleService;
	
	@Resource
	private CategoryService cateGoryService;   //��ʱ��û����
	
	@Resource
	private TagService tagService; //��ʱ��û����
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(HttpServletRequest request) {
		
		Article article  =new Article();
		
		//��ǰ�û���id
		User user=(User)request.getSession().getAttribute("session_user");
		article.setArticleUserId(user.getUserId());
		
		//���±���
		article.setArticleTitle(request.getParameter("articleTitle"));
		
		//��������
		article.setArticleContent(request.getParameter("articleContent"));
		
		//����ժҪ
		String s=HtmlUtil.cleanHtmlTag(article.getArticleContent()) ;
		article.setArticleSummary(s.length()>150?s.substring(0,150):s);
	
		//���µķ���ʱ��,�޸�ʱ��
		article.setArticleCreateTime(new Date());
		article.setArticleUpdateTime(new Date());
		
		article.setArticleCommentCount(0);
		article.setArticleLikeCount(0);
		article.setArticleViewCount(0);
		
		//Ĭ�ϵ�����
		article.setArticleOrder(1);
		
		//���µ�״̬
		article.setArticleStatus(Integer.parseInt(request.getParameter("articleStatus")));
		
		//һ������
		int articleParentCategoryId =Integer.parseInt(request.getParameter("articleParentCategoryId"));
		
		//��������
		int articleChildCategoryId =Integer.parseInt(request.getParameter("articleChildCategoryId"));
		
		List<Category>categoryList=new ArrayList<Category> (2);
		categoryList.add(new Category(articleParentCategoryId));
		categoryList.add(new Category(articleChildCategoryId));
		article.setCategoryList(categoryList);
		
		//��ǩ
		String [] tagIds=request.getParameterValues("articleTagIds");
		List<Tag> tagList =new ArrayList<>();
		for(String tagId:tagIds) {
			tagList.add(new Tag(Integer.parseInt(tagId)));
		}
		article.setTagList(tagList);
		
		
		//����׼�����Ժ�,����ҵ���
		articleService.add(article);
		
		//ת�������б�ҳ
		return "forward:/article" ;
		
	}
	
	
	
	
	/**
	 * ת�����·���ҳ�� ,��Ϊ��ҳ����Ҫѡ����������,������ǩ,����Ҫ���ŷ����б�ͱ�ǩ�б��ȥ
	 * 
	 */
	@RequestMapping(value ="/add",method=RequestMethod.GET)
	public String goToAdd(ModelMap m) {
		//������Ϣ
		List<Category> categoryList= cateGoryService.listCategory();
		
		//��ǩ��Ϣ
		List<Tag> tagList= tagService.listTag();
		
		//�ŵ�������
		 m.put("categoryList", categoryList);
		 m.put("tagList", tagList);
			
		//ת��д���µ�ҳ��
		return "/Article/article-add";		
	}
	
	/**
	 * ��ҳ��ѯ������Ϣ
	 * @param pageIndex  ���ڷ�ҳ,��ʾ��ǰ�ǵڼ�ҳ,Ĭ����1
	 * @param pageSize ���ڷ�ҳ,��ʾÿҳ�ж���������,Ĭ����10
	 * @param m
	 * @return  ���ص��� PageInfo���͵�����,�����溬�з�ҳ��Ϣ,�;���Ĳ����������
	 */
	@RequestMapping(value="")
	public String index(
				@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
				@RequestParam(required = false, defaultValue = "10") Integer pageSize,
				ModelMap m ) 
	{
		//��ҳ��ѯ������ص�����,�ŵ��������� Ƿ��
		PageInfo <Article> pageInfo =articleService.getPageArticleList(pageIndex, pageSize);
		m.put("pageUrlPrefix", "article?pageIndex");
		m.put("pageInfo", pageInfo);
		return "/Article/article-list" ;
	}
	
	
	/**
	 * ���ı��༭����Ӧ��ͼƬ�ϴ�
	 * @return ���ı��༭��Ҫ���json���͵�����
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@ResponseBody @RequestMapping("/uploadImg")
	public String uploadArticleImg(MultipartHttpServletRequest request) throws IllegalStateException, IOException {
		//�õ��ͻ��˴�������ͼƬ , imgFile ��һ���̶�����
		MultipartFile file= request.getFile("imgFile");
		
		//�������һ���ļ���
		String fileName=UUID.randomUUID().toString()+".jpg";
		
		//����һ������ļ���Ŀ��
		File destFile=new File("d:/imgupload/"+fileName);
		
		//���ļ��浽ĳ��Ŀ¼��
		file.transferTo(destFile);

		String path="http://localhost:8080/upload/"+fileName;
		

		//ע��,��� JSONObject ����Դ�� hutool ���߰�
		JSONObject obj = new JSONObject();
		obj.put("error", 0);
		obj.put("url", path);

		return  obj.toString();
	}
	
	
}