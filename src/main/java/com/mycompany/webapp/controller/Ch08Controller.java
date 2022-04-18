package com.mycompany.webapp.controller;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mycompany.webapp.dto.Ch08InputForm;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch08")
@Log4j2
@SessionAttributes({"inputForm"}) //새로운 세션저장소에 저장이된다
public class Ch08Controller {
	@RequestMapping("/content")
	public String content() {
		log.info("실행");
		return "ch08/content";
	}
	
	@GetMapping(value="/saveData", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String saveData(String name, HttpSession session) {
		log.info("실행");
		session.setAttribute("name", name);
		
		//성공하면 {"result":"success"}를 보내보자.
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		String json = jsonObject.toString();
		
		return json;
	}

	@GetMapping(value="/readData", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String readData(HttpSession session) {
		String name = (String) session.getAttribute("name");
		
		//{"name":"홍길동"}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		String json = jsonObject.toString();
		
		return json;
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "ch08/loginForm";
	}
	@PostMapping("/login")
	public String login(String mid, String mpassword, HttpSession session) {
		//로그인 성공 시 세션에 회원 아이디를 저장
		if(mid.equals("spring") && mpassword.equals("12345")) {
			session.setAttribute("sessionMid", mid);
		}
		return "redirect:/ch08/content";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		//방법1: 세션에서 주어진 키를 삭제
		session.removeAttribute("sessionMid");

//		//방법2: 세션객체 자체를 제거
//		session.invalidate();
		
		return "redirect:/ch08/content";
	}
	
	@GetMapping("/userinfo")
	public String userinfo(
			HttpSession session,
			@SessionAttribute String sessionMid,
			@SessionAttribute("sessionMid") String mid) {
		
		String memberId = (String) session.getAttribute("sessionMid");

		log.info("memberId: " + memberId);
		log.info("sessionMid: " + sessionMid);
		log.info("mid: " + mid);
		return "redirect:/ch08/content";
	}
	
	@PostMapping(value="/loginAjax", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String loginAjax(String mid, String mpassword, HttpSession session) {
		String result = null;
		if(mid.equals("spring")) {
			if(mpassword.equals("12345")) {
				result = "success";
				session.setAttribute("sessionMid", mid); //잊지말기
			} else {
				result = "wrongMpassword";
			}
		} else {
			result = "wrongMid";
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);	
		String json = jsonObject.toString();
		return json;
	}
	
	@GetMapping(value="/logoutAjax", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String logoutAjax(HttpSession session) {
		session.removeAttribute("sessionMid");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		String json = jsonObject.toString();
		return json;
	}
	
	//2022. 04. 15. 실습----------------------------------------------------
	@GetMapping("/inputStep1")
	public String inputStep1() {
		return "ch08/inputStep1";
	}
	
	// 새로운 세션 저장소에 객체를 저장하는 역할
	// 이때 inputForm이 세션범위에 생성이 된다
	@ModelAttribute("inputForm") // 여기에 주어진 이름으로 세션 저장소에 저장할거임.(맨위의 @SessionAttributes를 보세요)
	public Ch08InputForm getCh08InputForm() {
		log.info("실행"); // 기본적으론 요청때마다 여러번 실행이 된다.
		// 그러나!!!!! 맨위에 @SessionAttributes로 inputForm을 저장해두면 여러번 실행되지 않고, 딱 한번 실행된다.
		// 즉 세션에 기존의 객체가 존재하면 그걸 이용한다.
		// (참고: @SessionAttributes 쓴다고 해서 해당 컨트롤러의 모든 뷰가 inputForm을 사용할 수 있다는
		// 속성이 방해받진 않는다.)
		Ch08InputForm inputForm = new Ch08InputForm();
		return inputForm;
	}

	@PostMapping("/inputStep2")
	public String inputStep2(@ModelAttribute("inputForm") Ch08InputForm inputForm) {
		log.info("data1: " + inputForm.getData1());
		log.info("data2: " + inputForm.getData2());
		return "ch08/inputStep2";
	}
	
	@PostMapping("/inputDone")
	public String inputStep3(@ModelAttribute("inputForm") Ch08InputForm inputForm,
			SessionStatus sessionStatus) {
		log.info("data1: " + inputForm.getData1());
		log.info("data2: " + inputForm.getData2());
		log.info("data3: " + inputForm.getData3());
		log.info("data4: " + inputForm.getData4());
		sessionStatus.setComplete();
		return "redirect:/ch08/content";
	}
}
