<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="sign-up-box">
		<h1>회원가입</h1>
		<form method="post" action="/user/sign_up">
			<table class="table">
				<tr>
					<th>* 아이디</th>
					<td>
						<div class="d-flex">
							<input type="text" id="loginId" name="loginId" class="form-control" placeholder="아이디를 입력하세요.">
							<button type="button" id="loginIdCheckBtn" class="btn btn-info">중복확인</button><br>
							
							<div id="idCheckLength" class="small text-danger d-none">ID를 4자 이상 입력해주세요.</div>
							<div id="idCheckDuplicated" class="small text-danger d-none">이미 사용중인 ID입니다.</div>
							<div id="idCheckOk" class="small text-success d-none">사용 가능한 ID 입니다.</div>
						</div>
					</td>
				</tr>
				<tr>
					<th>* 비밀번호</th>
					<td><input type="password" class="form-control" id="password"></td>
				</tr>
				<tr>
					<th>* 비밀번호 확인</th>
					<td><input type="password" class="form-control" id="passwordCheck"></td>
				</tr>
				<tr>
					<th>* 이름</th>
					<td><input type="text" class="form-control" id="name" placeholder="이름을 입력하세요"></td>
				</tr>
				<tr>
					<th>* 이메일 주소</th>
					<td><input type="text" class="form-control" id="email" placeholder="이메일 주소를 입력하세요"></td>
				</tr>
			</table>
			<button type="submit" id="signUpBtn" class="btn btn-primary float-right">회원가입</button>
		</form>
	</div>
</div>

<script>
$(document).ready(function() {
	$('#loginIdCheckBtn').on('click', function() {
		let loginId = $('input[name=loginId]').val().trim();
		if (loginId.length < 4) {
			$('#idCheckLength').removeClass('d-none');
			return;
		}
		
		$.ajax({
			// Request
			type:"POST"
			, url:"/user/is_duplicated_id"
			, data:{"loginId":loginId}
	
			// Response
			, success:function(data) {
				if (data.result) {
					// 중복
					$('#idCheckDuplicated').removeClass('d-none');
				} else {
					// 사용 가능
					$('#idCheckOk').removeClass('d-none');
				}
			}
			, error:function(e) {
				alert("error" + e);
			}
		});
	});
});
</script>