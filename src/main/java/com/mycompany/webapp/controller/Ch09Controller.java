package com.mycompany.webapp.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.Ch09Dto;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch09")
@Log4j2
public class Ch09Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch09/content";
	}
	
	@PostMapping("/fileupload")
	public String fileupload(String title, String desc, MultipartFile attach) throws IOException {
		log.info("실행");
		log.info("title: " + title);
		log.info("desc: " + desc);
		log.info("file originalname: " + attach.getOriginalFilename());
		log.info("file contenttype: " + attach.getContentType());
		log.info("file size: " + attach.getSize());
		
		// 1. 받은 파일을 DB에 저장할 때 다음과 같이 할 수 있음.
		// (파일의 순수한 바이트 데이터들만 저장하기)
//		byte[] bytes = attach.getBytes(); 또는
//		InputStream is = attach.getInputStream();

		// 2. 받은 파일을 서버의 파일시스템에 저장할 때 다음과 같이 할 수 있음.
		// (DB 저장이 아님. WAS의 파일시스템에 저장하는 것 뿐임)

		// BUT, 같은 경로에 같은 파일이 업로드되면 덮어쓰기되어버림.
		// 이걸 방지하기 위해선 다음과 같이 하는 방법이 있음.(시간이랑 같이 파일명을 기록하면 겹칠일 없다!)
		String savedFilename = new Date().getTime() + "-" + attach.getOriginalFilename();

		File file = new File("C:/Temp/uploadedfiles/" + savedFilename);
		attach.transferTo(file);
		
		return "redirect:/ch09/content";
	}
	
	@PostMapping(value="/fileuploadAjax", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String fileuploadAjax(Ch09Dto dto) throws IllegalStateException, IOException {
		log.info("실행");
		log.info("title: " + dto.getTitle());
		log.info("desc: " + dto.getDesc());
		log.info("file originalname: " + dto.getAttach().getOriginalFilename());
		log.info("file contenttype: " + dto.getAttach().getContentType());
		log.info("file size: " + dto.getAttach().getSize());
		
		String savedFilename = new Date().getTime() + "-" + dto.getAttach().getOriginalFilename();
		File file = new File("C:/Temp/uploadedfiles/" + savedFilename);
		dto.getAttach().transferTo(file);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		jsonObject.put("savedFilename", savedFilename);
		String json = jsonObject.toString();
		
		return json;
	}
}
