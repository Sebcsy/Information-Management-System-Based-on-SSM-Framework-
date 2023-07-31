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
			request.setAttribute("msg", "用户名错误");
			return "login";
		}
		else if(!user.getUserPass().equals(userPass)) {
			request.setAttribute("msg", "密码错误");
			return "login";
		}
		else {
			//用户登录成功能后,把用户相关的信息放到 session中,方便以后使用
			request.getSession().setAttribute("session_user", user);
			
			//如果用户勾选了rememberMe , 添加cookie相关的信息
			//更新用户的最后登录时间
			//更新用户的最后登录ip
			//user.setUserLastLoginTime(new Date());
			//user.setUserLastLoginIp(request.getRemoteAddr());
			//userService.updateUser(user);
			
			//把文章列表数据查出来,带到index页
			List<Article> artileList=articleService.listRecentArticle(5);
			request.setAttribute("artileList", artileList);
			
			//把评论列表数据查出来,带到inex页
			List<Comment> commentList=CommentService.listRecentComment(5);
			request.setAttribute("commentList", commentList);
			System.out.println("请求进入了login....");
			//把评论列表数据查出来,带到inexx页 欠着
			return  "index";    //src/main/webapp/view/index.jsp
			}
		}
	
			@RequestMapping("/logout")
		public String logout(HttpSession session) {
				//一定要清除用户的session
				session.invalidate();
				return "login";
			}


	/**
	 * 从导航上转到用户添加页面
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