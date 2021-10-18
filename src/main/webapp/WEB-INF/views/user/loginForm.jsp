<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<!-- container -->
<div class="container">
<form action="/auth/loginProc" method="post">
  <div class="form-group">
    <label for="username">Username:</label>
    <input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
  </div>
  
  <div class="form-group">
    <label for="email">Email address:</label>
    <input type="email" name="email" class="form-control" placeholder="Enter email" id="email">
  </div>
  
  <div class="form-group">
    <label for="password">Password:</label>
    <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
  </div>
  
  <button type="submit" id="btn-login" class="btn btn-primary">로그인</button>
  
  <a href="https://kauth.kakao.com/oauth/authorize?client_id=fabd23473efc0faabd913cffe63733e8&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code"><img height="38px" src="/image/kakao_login_small.png" /></a>
</form>
</div>
<br/>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>
