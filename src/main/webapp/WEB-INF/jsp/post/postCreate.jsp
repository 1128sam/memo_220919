<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="w-50">
		<h1>글쓰기</h1>
		
		<input type="text" id="subject" class="form-control mb-2" placeholder="제목을 입력하세요.">
		<textarea class="form-control my-4" id="content" placeholder="내용을 입력하세요." rows="15"></textarea>
		<div class="d-flex justify-content-end my-2">
			<input type="file" id="file" accept=".jsp, .jpeg, .png, .gif">
		</div>

		<div class="d-flex">
			<button type="button" id="postListBtn" class="btn btn-secondary col-1">목록</button>

			<div class="d-flex justify-content-end">
				<button type="button" id="clearBtn" class="btn btn-light">모두 지우기</button>
				<button type="button" id="postCreateBtn" class="btn btn-primary">저장</button>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		// 목록 버튼
		$('#postListBtn').on('click', function() {
			location.href = "/post/post_list_view";
		});
		
		// 모두 지우기 버튼 => 제목, 글 내용 지운다
		$('#clearBtn').on('click', function() {
			$('#subject').val("");
			$('#content').val("");
		})
		
		$('#postCreateBtn').on('click', function() {
			let subject = $('#subject').val().trim();
			let content = $('#content').val();
			
			if (subject == '') {
				alert("제목을 입력하세요.");
				return;
			}
			
			console.log(content);

			let file = $('#file').val(); // C:\fakepath\p_pysic_mod

			// 파일이 업로드된 경우에만 확장자 체크
			if (file != '') {
				//alert(file.split(".").pop().toLowerCase());
				let ext = file.split(".").pop().toLowerCase();
				if ($.inArray(ext, ['jpg', 'jpeg', 'png', 'gif']) == -1) {
					alert("이미지 파일만 업로드 할 수 있습니다.");
					$('#file').val(""); // 파일을 비운다.
					return;
				}
			}
			
			// 서버 - AJAX
			
			// 이미지를 업로드할 때는 form 태그가 있어야 한다.(자바 스크립트에서 만듬)
			// append로 넣는 값은 폼태그의 name으로 넣는 것과 같다(request parameter)
			
			let formData = new FormData();
			formData.append("subject", subject);
			formData.append("content", content);
			formData.append("file", $('#file')[0].files[0]);

			// ajax 통신으로 formData에 있는 데이터 전송
			$.ajax({
				// request
				type:"POST"
				, url:"/post/create"
				, data:formData // form 객체를 통째로
				, enctype:"multipart/form-data" // 파일 업로드를 위한 필수 설정
				, processData:false // 파일 업로드를 위한 필수 설정
				, contentType:false // 파일 업로드를 위한 필수 설정

				// response
				, success:function(data) {
					if (data.code == 1) {
						// 성공
						// alert("메모가 저장되었습니다.");
						location.href = "/post/post_list_view";
					} else {
						// 실패
						alert(data.errorMessage);
					}
				}
			, error:function(e) {
				alert("메모 저장에 실패했습니다.");
			}
			});
		});
	});
</script>