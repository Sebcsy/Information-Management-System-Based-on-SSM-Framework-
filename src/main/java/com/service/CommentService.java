package com.service;
import java.util.List;
import com.entity.Comment;

public interface CommentService {
	List<Comment> listRecentComment(int i);
}