package com.mycompany.webapp.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Ch04Dto;
import com.mycompany.webapp.dto.Ch04Member;
import com.mycompany.webapp.validator.Ch04MemberEmailValidator;
import com.mycompany.webapp.validator.Ch04MemberIdValidator;
import com.mycompany.webapp.validator.Ch04MemberJoinFormValidator;
import com.mycompany.webapp.validator.Ch04MemberPasswordValidator;
import com.mycompany.webapp.validator.Ch04MemberTelValidator;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch04")
@Log4j2
public class Ch04Controller {
	@RequestMapping("/content")
	public String content() {
		log.info("실행");
		return "ch04/content";
	}
	
	@PostMapping("/method1")
	public String method1(Ch04Dto dto) {
		log.info(dto.toString());
		return "ch04/content";
	}
	
	// DTO와 Validator를 연결
	@InitBinder("joinForm")
	public void bindCh04MemberJoinFormValidator(WebDataBinder binder) {
//		binder.setValidator(new Ch04MemberJoinFormValidator());
		binder.addValidators(
				new Ch04MemberIdValidator(),
				new Ch04MemberPasswordValidator(),
				new Ch04MemberEmailValidator(),
				new Ch04MemberTelValidator());
	}
	
	@PostMapping("/join")
	public String join(@ModelAttribute("joinForm") @Valid Ch04Member member, Errors errors) {
//		회원가입 처리 후 redirect.
		log.info("실행");
		
		if(errors.hasErrors()) {
			// 다시 입력폼으로 돌아가기
			return "ch04/content";
		}
		// 홈화면으로 보내기
		return "redirect:/";
	}
	
	
	@InitBinder("loginForm")
	public void loginForm(WebDataBinder binder) {
//		binder.setValidator(new Ch04MemberLoginFormValidator());
		binder.addValidators(
				new Ch04MemberIdValidator(),
				new Ch04MemberPasswordValidator()
		);
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") @Valid Ch04Member member, Errors errors) {
//		회원가입 처리 후 redirect.
		log.info("실행");
		
		if(errors.hasErrors()) {
			// 다시 입력폼으로 돌아가기
			return "ch04/content";
		}
		// 홈화면으로 보내기
		return "redirect:/";
	}
}
