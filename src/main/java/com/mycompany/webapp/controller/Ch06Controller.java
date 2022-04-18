package com.mycompany.webapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch06")
@Log4j2
public class Ch06Controller {
	@RequestMapping("/content")
	public String content() {
		log.info("실행");
		return "ch06/content";
	}

	@GetMapping("/forward")
	public String forward() {
		return "ch06/forward";
	}

	@GetMapping("/redirect")
	public String redirect() {
		return "redirect:/";
	}

	@GetMapping("getFragmentHtml")
	public String getFragmentHtml() {
		return "ch06/fragmentHtml";
	}
	
	@GetMapping("getJson1")  // 원시적 방법
	public void getJson1(HttpServletResponse response) throws IOException {
		//{"fileName": "photo6.jpg"} 이런 형태로 보내고 싶단 말이지.
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo6.jpg");
		String json = jsonObject.toString();
		
		// 이 응답의 유형이 뭔지를 먼저 알려줘야 함.
		response.setContentType("application/json; charset=UTF-8");
		// 서버와 브라우저가 통신할때는 입출력스트림을 쓴다.
		PrintWriter pw = response.getWriter();
		pw.write(json); // pw.println(json)써도 됨
		pw.flush();
		pw.close();  // 보내기까지 함. 때문에 리턴에서 뷰를 리턴할 필요가 없음...
	}
	
	@GetMapping(value="getJson2", produces="application/json; charset=UTF-8")
	@ResponseBody //"리턴되는 내용이 곧 응답 본문에 들어가는 걸세"
	public String getJson2() throws IOException {
		//{"fileName": "photo6.jpg"} 이런 형태로 보내고 싶단 말이지.
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo6.jpg");
		String json = jsonObject.toString();
		return json;  // 뷰 이름으로 해석하지 않고 응답body로 해석함
	}

	@GetMapping("/getJson3")
	public String getJson3() {
		return "redirect:/";
	}
}
