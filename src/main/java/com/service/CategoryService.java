package com.service;

import java.util.List;

import com.entity.Category;

public interface CategoryService {
	List<Category> listCategory();
	
	//根据父级id,查询子分类信息
	List<Category> listCategoryByParentId(Integer parentId);

}
