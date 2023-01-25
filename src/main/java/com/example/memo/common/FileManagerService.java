package com.example.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component // 일반적인 스프링 빈
public class FileManagerService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	// 실제 이미지가 저장될 경로(서버)
	public static final String FILE_UPLOAD_PATH = "D:\\seowonlee\\6_spring_project\\memo\\workspace\\images/"; 
	// static final이 같이 있으면 상수? - 끝에 "/" 있어야 함

	// input: MultipartFile, userLoginId
	// output: image path
	public String saveFile(String userLoginId, MultipartFile file) {
		// 파일 디렉토리 예) test_16205468768/sun.png
		String directoryName = userLoginId + "_" + System.currentTimeMillis() + "/"; // D:\\seowonlee\\6_spring_project\\memo\\workspace\\images/test_16205468768 
		String filePath = FILE_UPLOAD_PATH + directoryName;

		File directory = new File(filePath);
		if (directory.mkdir() == false) { // make directory
			return null; // 폴더 만드는데 실패 시 이미지 패스 null
		}
		
		// 파일 업로드: byte 단위로 업로드된다.
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename()); // 확장자 분해, originalFileName은 사용자가 올린 파일명
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		// 파일 업로드 성공했으면 이미지 url path를 리턴한다.
		// http://localhost:8080/images/test_16205468768/sun.png
		return "/images/" + directoryName + file.getOriginalFilename();
	}
	
	public void deleteFile(String imagePath) { // imagePath : /images/test_16205468768/sun.png
		// \\images/   imagePath에 있는 겹치는  /images/ 구문 제거 
		Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images/", ""));
		if (Files.exists(path)) {
			// 이미지 삭제
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("[이미지 삭제] 이미지 삭제 실패. imagePath:{}", imagePath);
			}

			// 디렉토리(폴더) 삭제
			path = path.getParent();
			if (Files.exists(path)) {
				try {
					Files.delete(path);
				} catch (IOException e) {
					logger.error("[이미지 삭제] 디렉토리 삭제 실패. imagePath:{}", imagePath);
				}
			}
		}
	}
}