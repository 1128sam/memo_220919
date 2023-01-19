<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="sign-up-box">
		<h1>회원가입</h1>
		<form id="signUpForm" method="post" action="/user/sign_up">
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
					<td><input type="password" class="form-control" id="password" name="password"></td>
				</tr>
				<tr>
					<th>* 비밀번호 확인</th>
					<td><input type="password" class="form-control" id="confirmPassword"></td>
				</tr>
				<tr>
					<th>* 이름</th>
					<td><input type="text" class="form-control" id="name" placeholder="이름을 입력하세요" name="name"></td>
				</tr>
				<tr>
					<th>* 이메일 주소</th>
					<td><input type="text" class="form-control" id="email" name="email" placeholder="이메일 주소를 입력하세요"></td>
				</tr>
			</table>
			<button type="button" id="signUpBtn" class="btn btn-primary float-right">회원가입</button>
		</form>
	</div>
</div>

<script>
$(document).ready(function() {
	$('#loginIdCheckBtn').on('click', function() {
		$('#idCheckLength').addClass('d-none');
		$('#idCheckDuplicated').addClass('d-none');
		$('#idCheckOk').addClass('d-none');
		let loginId = $('input[name=loginId]').val().trim();
		if (loginId.length < 4) {
			$('#idCheckLength').removeClass('d-none');
			return;
		}
		
		$.ajax({
			// Request
			/* type:"POST"
			,  */url:"/user/is_duplicated_id"
			, data:{"loginId":loginId}
	
			// Response
			, success:function(data) {
				if (data.result) {
					// 중복
					$('#idCheckDuplicated').removeClass('d-none');
					return false;
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
	
	$('#signUpForm').on('submit', function(e) {
		e.preventDefault(); // 서브밋 기능 중단
		// (submit)과 (preventDefault)를 안하거나 (button)과 preventDefault를 하거나
		
		let loginId = $('#loginId').val().trim();
		let password = $('#password').val();
		let confirmPassword = $('#confirmPassword').val();
		let name = $('#name').val().trim();
		let email = $('#email').val().trim();
		
		if (loginId.length < 1) {
			alert("아이디를 입력하세요");
			return false;
		}
		if (password.length < 1 || confirmPassword.length < 1) {
			alert("비밀번호를 입력하세요");
			return false;
		}
		if (password != confirmPassword) {
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		}
		if (name.length < 1) {
			alert("이름을 입력하세요");
			return false;
		}
		if (email.length < 1) {
			alert("이메일을 입력하세요");
			return false;
		}
		
		// 아이디 중복확인 완료 됐는지 확인 -> idCheckOk d-none을 가지고 있으면 확인하라는 얼럿 띄워야함
		if ($('#idCheckOk').hasClass('d-none')) {
			alert("아이디 중복확인을 다시 해주세요.");
			return false;
		}
		
		// 서버로 보내는 방법 
		// 1) submit
		$(this)[0].submit();   // 화면이 넘어간다
		
		// 2) ajax
		let url = $(this).attr('action');
		let params = $(this).serialize(); // form태그에 있는 name으로 파라미터들 구성
		console.log(params);
		
		$.post(url, params)  // request
		.done(function(data) {
			// response
			if (data.code == 1) {
				// 성공
				alert("가입을 환영합니다! 로그인 해주세요.");
				location.href = "/user/sign_in_view";
			} else {
				// 실패
				alert(data.errorMessage);
			}
		});
		
	});
});
</script>