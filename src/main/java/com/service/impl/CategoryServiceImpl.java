package com.service.impl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.entity.Category;
import com.mapper.CategoryMapper;
import com.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Resource
	private CategoryMapper categoryMapper;
	
	//查询所有的分类信息
	public List<Category> listCategory() {
		return categoryMapper.listCategory();
	}
	
	//根据父级id,查询子分类信息
	public List<Category> listCategoryByParentId(Integer parentId) {
		return categoryMapper.listCategoryByParentId(parentId);
	}
}