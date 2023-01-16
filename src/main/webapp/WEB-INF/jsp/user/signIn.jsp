<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
<div class="login-box">
	<h1>로그인</h1>
	<form id="loginForm" method="post" action="/user/sign_in">
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">ID</span>
			</div>
			<input type="text" class="form-control" id="loginId" name="loginId">
		</div>
	
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">PW</span>
				</div>
				<input type="password" class="form-control" id="password" name="password">
			</div>
		
		<input type="submit" class="btn btn-block btn-primary" value="로그인"><%-- btn-block: 로그인 박스 영역에 버튼을 가득 채운다. --%>
		<a class="btn btn-block btn-dark" href="/user/sign_up_view">회원가입하기</a>
	</form>
</div>
</div>