<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center">
	<div class="w-50">
		<h1>글 상세수정</h1>
		
		<input type="text" id="subject" class="form-control mb-2" placeholder="제목을 입력하세요." value="${post.subject}">
		<textarea class="form-control my-4" id="content" placeholder="내용을 입력하세요." rows="15">${post.content}</textarea>
		
		<!-- 이미지가 있을 때만 이미지 영역 추가 -->
		<c:if test="${not empty post.imagePath}">
			<div class="mt-2">
				<img src="${post.imagePath}" alt="업로드 이미지" width="300">
			</div>
		</c:if>

		<div class="d-flex justify-content-end my-2">
			<input type="file" id="file" accept=".jsp, .jpeg, .png, .gif">
		</div>

		<div class="d-flex justify-content-between">
			<button type="button" id="postDeleteBtn" class="btn btn-secondary col-1">삭제</button>

			<div class="d-flex justify-content-end">
				<a href="/post/post_list_view" class="btn btn-light">목록으로</a>
				<button type="button" id="postUpdateBtn" class="btn btn-primary" data-post-id="${post.id}">수정</button>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('#postUpdateBtn').on('click', function() {
			let subject = $('#subject').val().trim();
			if (subject == '') {
				alert("제목을 입력해주세요.");
				return;
			}
			
			let content = $('#content').val();
			console.log(content);
			
			let file = $('#file').val();
			console.log(file);
			
			// 파일이 업로드 된 경우 확장자 체크
			if (file != '') {
				let ext = file.split(".").pop().toLowerCase();
				if ($.inArray(ext, ['jpg', 'jpeg', 'png', 'gif']) == -1) {
					alert("이미지 파일만 업로드 할 수 있습니다.");
					$('#file').val(""); // 파일을 비운다.
					return;
				}
			}

			let postId = $(this).data('post-id');
			alert(postId);
			// 폼태그를 자바스크립트에서 만든다
			let formData = new FormData();
			formData.append("postId", postId);
			formData.append("subject", subject);
			formData.append("content", content);
			formData.append("file", $('#file')[0].files[0]);
			
			// AJAX => 서버 통신
			$.ajax({
				// request
				type:"PUT"
				, url:"/post/update"
				, data:formData
				, enctype:"multipart/form-data" // 파일 업로드를 위한 필수 설정
				, processData:false // 파일 업로드를 위한 필수 설정
				, contentType:false // 파일 업로드를 위한 필수 설정

				// response
				, success:function(data) {
					if (data.code == 1) {
						alert("메모가 수정되었습니다.");
						locationo.reload(true); // true: unnecessary
					} else {
						alert(data.errorMessage);
					}
				}
			, error:function(e) {
				alert("메모 수정 시 실패했습니다.");
			}
			});
		});
	});
</script>


<!-- SNS
- 로그인/회원가입
- 글/댓글/좋아요 목록(타임라인)
- 글쓰기
- 댓글쓰기
- 좋아요/해제 API


++
- 팔로우 기능
- 프로필 화면

(보류)
- 글 수정
- 유저 추천

(내일)
- 글 수정/삭제 내일 -->