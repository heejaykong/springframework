package com.mycompany.webapp.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.webapp.dto.Ch04Member;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Ch04MemberLoginFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		log.info("실행");
		return Ch04Member.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Ch04Member member = (Ch04Member) target;
		
		// mid 검사
		if(member.getMid() == null || member.getMid().trim().equals("")) {
			errors.rejectValue("mid", null, "mid는 필수입력사항입니다.");
		} else {
			if(member.getMid().length() < 4) {
				errors.rejectValue("mid", null, "mid는 4자 이상이어야 합니다.");
			}
		}
		
		// mpassword 검사
		if(member.getMpassword() == null || member.getMpassword().trim().equals("")) {
			errors.rejectValue("mpassword", null, "mpassword는 필수입력사항입니다.");
		} else {
			if(member.getMpassword().length() < 8) {
				errors.rejectValue("mpassword", null, "mpassword는 8자 이상이어야 합니다.");
			}
		}
	}
	
}
