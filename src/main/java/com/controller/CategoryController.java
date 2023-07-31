package com.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Category;
import com.service.CategoryService;

@Controller @RequestMapping("/category")
public class CategoryController {
	@Resource
	private CategoryService categoryService;
	
	//查询所有的了分类,以 json格式返回
	@ResponseBody  @RequestMapping("/listSub")
	public List<Category> getSubCategoryList(Integer parentId){
		 List<Category>  categoryList=categoryService.listCategoryByParentId(parentId);
		 return categoryList;
	}	
}