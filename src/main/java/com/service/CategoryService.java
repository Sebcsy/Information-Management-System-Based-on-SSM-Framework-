package com.service;

import java.util.List;

import com.entity.Category;

public interface CategoryService {
	List<Category> listCategory();
	
	//���ݸ���id,��ѯ�ӷ�����Ϣ
	List<Category> listCategoryByParentId(Integer parentId);

}
