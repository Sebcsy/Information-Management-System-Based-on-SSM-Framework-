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
	
	//��ѯ���еķ�����Ϣ
	public List<Category> listCategory() {
		return categoryMapper.listCategory();
	}
	
	//���ݸ���id,��ѯ�ӷ�����Ϣ
	public List<Category> listCategoryByParentId(Integer parentId) {
		return categoryMapper.listCategoryByParentId(parentId);
	}
}