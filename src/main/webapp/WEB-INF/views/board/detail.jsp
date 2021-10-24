<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<!-- container -->
<div class="container">
  <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
  <c:if test="${board.user.id == principal.user.id }">
  	<a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
  	<button id="btn-delete" class="btn btn-danger">삭제</button>
  </c:if>
  <br/><br/>
  
  <div>
  	글 번호:<span id="id"><i>${board.id} </i></span>
  	작성자:<span id="id"><i>${board.user.username} </i></span>
  </div>
  <br/><br/>
  
  <div>
    <label for="title">Title</label>
   	<h3>${board.title}</h3>
  </div>
  
  	<div>
  		<label for="content">Content</label>
  	<div>
  		${board.content}
  	</div>
  </div>
  
  <div class="card">
  		<div class="card-body"><textarea class="form-control" rows="1" cols=""></textarea></div>
  		<div class="card-footer"><button class="btn btn-primary">등록</button></div>
  </div>
  <br/>
  <div class="card">
  	<div class="card-header">댓글 리스트</div>
  	<ul id="comment--box" class="list-group">
	  <li id="comment--1" class="list-group-item d-flex justify-content-between">
	  	<div>댓글 내용입니다!!</div>
	  	<div class="d-flex">
	  		<div class="font-italic">작성자: 	 &nbsp;</div>
	  		<button class="badge">삭제</button>
	  	</div>
	  </li>
	</ul>
  </div>
  
  
</div>
<br/>
  <script>
  </script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>
