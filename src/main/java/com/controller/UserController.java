package com.controller;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Article;
import com.entity.Comment;
import com.entity.User;
import com.service.ArticleService;
import com.service.UserService;
import com.service.CommentService;

@Controller @RequestMapping("/user")
public class UserController {
	@Resource
	UserService  userService;
	@Resource
	ArticleService articleService;
	@Resource
	CommentService CommentService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String goToLogin() {
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		String userNameOrEmail=request.getParameter("userName");
		String userPass=request.getParameter("userPass");
		String rememberMe=request.getParameter("rememberMe");
		
		User user=userService.loginByNameOrEmail(userNameOrEmail);
		if(user==null) {
			request.setAttribute("msg", "�û�������");
			return "login";
		}
		else if(!user.getUserPass().equals(userPass)) {
			request.setAttribute("msg", "�������");
			return "login";
		}
		else {
			//�û���¼�ɹ��ܺ�,���û���ص���Ϣ�ŵ� session��,�����Ժ�ʹ��
			request.getSession().setAttribute("session_user", user);
			
			//����û���ѡ��rememberMe , ���cookie��ص���Ϣ
			//�����û�������¼ʱ��
			//�����û�������¼ip
			//user.setUserLastLoginTime(new Date());
			//user.setUserLastLoginIp(request.getRemoteAddr());
			//userService.updateUser(user);
			
			//�������б����ݲ����,����indexҳ
			List<Article> artileList=articleService.listRecentArticle(5);
			request.setAttribute("artileList", artileList);
			
			//�������б����ݲ����,����inexҳ
			List<Comment> commentList=CommentService.listRecentComment(5);
			request.setAttribute("commentList", commentList);
			System.out.println("���������login....");
			//�������б����ݲ����,����inexxҳ Ƿ��
			return  "index";    //src/main/webapp/view/index.jsp
			}
		}
	
			@RequestMapping("/logout")
		public String logout(HttpSession session) {
				//һ��Ҫ����û���session
				session.invalidate();
				return "login";
			}


	/**
	 * �ӵ�����ת���û����ҳ��
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String goToAdd() {
		return "User/user-add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(User user, MultipartFile photo) throws IOException {	
		user.setUserRegisterTime(new Date());
		user.setUserStatus(1);
		user.setUserPhoto( photo.getBytes() );
		
		userService.addUser(user);	
		return "forward:/user";
	}
	
	
	@RequestMapping("")
	public String userList(ModelMap map) {
		List<User> userList=userService.listUser();
		map.put("userList",userList);
		return "User/user-list";
	}
	
	@RequestMapping("/photo/{userId}")
	public void showPhoto(@PathVariable("userId") Integer userId,HttpServletResponse response) throws IOException {
		User user=userService.getUserById(userId);
		
		response.setContentType("image/jpg");
		ServletOutputStream out =response.getOutputStream();
		out.write(user.getUserPhoto());
		out.flush();
	}
	
}