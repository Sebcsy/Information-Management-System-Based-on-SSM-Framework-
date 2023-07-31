<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<rapid:override name="frame-content">
<blockquote class="layui-elem-quote">
	<span class="layui-breadcrumb" lay-separator="/"> <a
		href="/admin">首页</a> <a><cite>文章列表</cite></a>
	</span>
</blockquote>

<div class="layui-tab layui-tab-card">
	<form id="articleForm" method="post">
		<input type="hidden" name="currentUrl" id="currentUrl" value="">
		<table class="layui-table">
			<colgroup>
				<col width="300">
				<col width="150">
				<col width="100">
				<col width="150">
				<col width="100">
				<col width="50">
			</colgroup>
			<thead>
				<tr>
					<th>标题</th>
					<th>所属分类</th>
					<th>状态</th>
					<th>发布时间</th>
					<th>操作</th>
					<th>id</th>
				</tr>
			</thead>
			<tbody>
					<c:forEach var="a" items="${pageInfo.list}">
						<tr>
							<td><a href="/article/${a.articleId }" target="_blank"> ${a.articleTitle }</a></td>
							<td>
								<c:forEach var="c" items="${a.categoryList}">
								 	 <a href="/category/${c.categoryId }" target="_blank">${c.categoryName }</a> &nbsp;
								</c:forEach>	
			  				</td>
							<td>
								<a href="/admin/article?status=1">
								    <c:if test="${a.articleStatus==1 }">
								    	 <span style="color: #5FB878;">已发布</span>
								    </c:if>
								    
								     <c:if test="${a.articleStatus==0 }">
								    	 <span style="color: red">草稿</span>
								    </c:if>
									 
								</a>
							</td>
							<td>  <fmt:formatDate value="${a.articleCreateTime    }" pattern="yyyy-MM-dd HH:mm:ss" /> </td>
							<td><a href="/admin/article/edit/${a.articleId }"
								class="layui-btn layui-btn-mini">编辑</a> <a
								href="javascript:void(0)" onclick="deleteArticle(${a.articleId })"
								class="layui-btn layui-btn-danger layui-btn-mini">删除</a></td>
							<td>33</td>
						</tr>
					</c:forEach>
			</tbody>
		</table>
	</form>
	<c:if test="${pageInfo.pages > 1}">
	    <nav class="navigation pagination" role="navigation">
	        <div class="nav-links">
	            <c:choose>
	                <c:when test="${pageInfo.pages <= 3 }">
	                    <c:set var="begin" value="1"/>
	                    <c:set var="end" value="${pageInfo.pages }"/>
	                </c:when>
	                <c:otherwise>
	                    <c:set var="begin" value="${pageInfo.pageNum-1 }"/>
	                    <c:set var="end" value="${pageInfo.pageNum + 2}"/>
	                    <c:if test="${begin < 2 }">
	                        <c:set var="begin" value="1"/>
	                        <c:set var="end" value="3"/>
	                    </c:if>
	                    <c:if test="${end > pageInfo.pages }">
	                        <c:set var="begin" value="${pageInfo.pages-2 }"/>
	                        <c:set var="end" value="${pageInfo.pages }"/>
	                    </c:if>
	                </c:otherwise>
	            </c:choose>
	                <%--上一页 --%>
	            <c:choose>
	                <c:when test="${pageInfo.pageNum eq 1 }">
	                    <%--当前页为第一页，隐藏上一页按钮--%>
	                </c:when>
	                <c:otherwise>
	                    <a class="page-numbers" href="${pageUrlPrefix}=${pageInfo.pageNum-1}">   
	                        <i class="layui-icon">&#xe603;</i>
	                    </a>
	                </c:otherwise>
	            </c:choose>
	                <%--显示第一页的页码--%>
	            <c:if test="${begin >= 2 }">
	                <a class="page-numbers" href="${pageUrlPrefix}=1">1</a> 
	            </c:if>
	                <%--显示点点点--%>
	            <c:if test="${begin  > 2 }">
	                <span class="page-numbers dots">…</span>
	            </c:if>
	                <%--打印 页码--%>
	            <c:forEach begin="${begin }" end="${end }" var="i">
	                <c:choose>
	                    <c:when test="${i eq pageInfo.pageNum }">
	                        <a class="page-numbers current">${i}</a>
	                    </c:when>
	                    <c:otherwise>
	                        <a class="page-numbers" href="${pageUrlPrefix}=${i}">${i}</a>   
	                    </c:otherwise>
	                </c:choose>
	            </c:forEach>
	                <%-- 显示点点点 --%>
	            <c:if test="${end < pageInfo.pages-1 }">
	                <span class="page-numbers dots">…</span>
	            </c:if>
	                <%-- 显示最后一页的数字 --%>
	            <c:if test="${end < pageInfo.pages }">
	                <a href="${pageUrlPrefix}=${pageInfo.pages}">   ${pageInfo.pages}</a>
	            </c:if>
	                <%--下一页 --%>
	            <c:choose>
	                <c:when test="${pageInfo.pageNum eq pageInfo.pages }">
	                    <%--到了尾页隐藏，下一页按钮--%>
	                </c:when>
	                <c:otherwise>
	                    <a class="page-numbers"  href="${pageUrlPrefix}=${pageInfo.pageNum+1}"> <i class="layui-icon">&#xe602;</i>  </a>
	                </c:otherwise>
	            </c:choose>
	        </div>
	    </nav>
	</c:if>		
</div>
</rapid:override>
<%@ include file="../page.jsp" %>
<%@ include file="../framework.jsp"  %>