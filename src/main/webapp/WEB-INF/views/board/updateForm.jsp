<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<!-- container -->
<div class="container">
<form>
  <input type="hidden" id="id" value="${board.id}"/>
  <div class="form-group">
    <input value="${board.title}" type="text" class="form-control" placeholder="Enter title" id="title">
  </div>
  
  <div class="form-group">
  	<textarea class="form-control" rows="5" id="content">${board.content}</textarea>
  </div>
</form>
<button id="btn-update" class="btn btn-primary">글쓰기 수정 완료</button>
</div>
<br/>
  <script>
    $(document).ready(function() {
        $('#content').summernote({
        	tabsize:2,
        	height:300
        });
        
    });
  </script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>
