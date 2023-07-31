package com.service.impl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.entity.Tag;
import com.mapper.TagMapper;
import com.service.TagService;

@Service
public class TagServiceImpl implements TagService {
	@Resource
	private TagMapper tagMapper;
	
	public List<Tag> listTag() {
		return tagMapper.listTag();
	}

	@Override
	public void addTag(Tag tag) {
		// TODO Auto-generated method stub
		tagMapper.addTag(tag);
	}

	@Override
	public Tag getTagById(Integer tagId) {
		// TODO Auto-generated method stub
		return tagMapper.getTagById(tagId);
	}

	@Override
	public void updateTag(Tag tag) {
		// TODO Auto-generated method stub
		tagMapper.updateTag(tag);
	}

}