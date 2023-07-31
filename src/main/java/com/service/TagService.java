package com.service;

import java.util.List;

import com.entity.Tag;

public interface TagService {
	public List<Tag> listTag();

	public void addTag(Tag tag);

	public Tag getTagById(Integer tagId);
	
	public void updateTag(Tag tag);
}
