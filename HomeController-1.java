package com.mycom.app;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	//요청주소 : http://localhost:8081/app/test?id=hongid&age=12
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String home(Locale locale, Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("id") String rpid,
			@RequestParam("age") int rpage) {
		logger.info("Welcome home! The client locale is {}.", locale);
		//1. 파라미터 받기
		String id = request.getParameter("id");
		int age = Integer.parseInt(request.getParameter("age"));
		System.out.println("syso 파라미터 id="+id);
		System.out.println("syso 파라미터 age="+(age-1));
		logger.info("@RequestParam(id)는	{}",rpid);	
		logger.info("@RequestParam(age)는	{}",rpage-1);
		
		//2. 비즈니스로직
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		//3.Model
		response.setCharacterEncoding("UTF-8");
		request.setAttribute("name", "홍gd");
		
		//org.springframwork.ui패키지의 Model이용
		model.addAttribute("serverTime", formattedDate );
		
		
		return "home";
	}
	
	
	//접근제한자 속성 리턴유형 메서드명(매개변수리스트) 
	//요청주소 http://localhost:8081/app/test2
	@RequestMapping("/form01")
	public String test2(){
		
		return "form01";
	}
	

	@RequestMapping(value="/from01Result", method = RequestMethod.GET)
	public String from01Result(
			@RequestParam String userName,
			@RequestParam String[] hobby,
			@RequestParam(value ="search", required=false ,  defaultValue="all") String search,
			@RequestParam("keyword") String keyword,
			@RequestParam(value = "pageno", required=false, defaultValue="1") int pageno) {
		
		logger.info("이름: {}", userName);
		logger.info("관심사: {}", hobby);
		
		for(String h : hobby) {
			System.out.println(h);
		}
		
		logger.info("검색분야:	{} , 검색어:	{}",search,keyword);
		logger.info("pageno :	{}",pageno);
		
		return "from01Result";
	}
}






