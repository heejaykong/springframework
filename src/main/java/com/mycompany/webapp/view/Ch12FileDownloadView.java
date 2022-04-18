package com.mycompany.webapp.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class Ch12FileDownloadView extends AbstractView {
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("실행");
		String filename = (String) model.get("filename");
		String userAgent = (String) model.get("userAgent");
		
		
		//Ch09Controller에서 복붙한 내용---------------
		// request의 메소드를 이용해서 각 정보를 가져올 수 있음.
		String contentType = request.getServletContext().getMimeType(filename);
		String originalFilename = filename;
		String savedFilename = filename;
		
		// 응답 내용의 데이터 타입을 응답헤더에 추가한다.
		response.setContentType(contentType);
		// 다운로드할 파일명을 응답헤더에 추가한다.(한글이 포함된 경우도 처리해줘야 함)
		if(userAgent.contains("Trident") || userAgent.contains("MSIE")) { // IE일때
			originalFilename = URLEncoder.encode(originalFilename, "UTF-8");
		} else { // 나머지(chrome, edge, safari...)
			originalFilename = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
		}
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\"");
		// 파일 또는 바이너리 데이터를 응답 본문에 싣기.
		File file = new File("C:/Temp/uploadedfiles/" + savedFilename);
		if(file.exists()) {
			/*
			InputStream is = new FileInputStream(file);
			ServletOutputStream os = response.getOutputStream();  // 파일데이터이기 때문에 스트림을 사용한다.
			FileCopyUtils.copy(is, os);
			os.flush();
			is.close();
			os.close();*/
			// 위 로직을 한줄로 줄이면 다음과 같이 줄일 수 있음.
			FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
		}
	}
}
