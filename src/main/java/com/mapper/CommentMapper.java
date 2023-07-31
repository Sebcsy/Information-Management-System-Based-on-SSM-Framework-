package com.mapper;
import java.util.List;
import com.entity.Comment;

public interface CommentMapper {
	List<Comment> listRecentComment(int n);
}
