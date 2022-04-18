package com.mycompany.webapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch12")
@Log4j2
public class Ch12Controller {
	@RequestMapping("/content")
	public String content() {
		log.info("실행");
		return "ch12/content";
	}

	@GetMapping("/fileList")
	public String fileList() {
		log.info("실행");
		return "ch12FileListView";
	}
	
	@GetMapping("/fileDownload")
	public String fileDownload(
			@ModelAttribute("filename") String filename,
			@ModelAttribute("userAgent") @RequestHeader("User-Agent") String userAgent) { // 시히벌 길기도 하다
		log.info("실행");
		return "ch12FileDownloadView";
	}
}
