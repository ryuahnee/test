package com.mycom.app;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//요청주소  http://localhost:8081/app/test?id=hongid&age=12
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String home(Locale locale, Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("id") String rpid,
			@RequestParam("age") int rpage) {
		logger.info("Welcome home! The client locale is {}.", locale);
		//1.파라미터받기- 기존의 방식을 이용
		/*String 변수명 = request.getParameter("파라미터명");
		  리턴유형이 String이었으므로 필요시 형변환을 해야했다.*/
		String id = request.getParameter("id");
		int age = Integer.parseInt(request.getParameter("age"));
		System.out.println("syso 파라미터id="+id);
		System.out.println("syso 파라미터age="+(age-1));
		
		//1.파라미터-요청메서드안의 매개변수로 @RequestParam("파라미터명") 타입  매개변수명
		//매개변수의 타입을 자유롭게 지정할 수 있어 편리하다
		//@RequestParam("age") int rpage는 ?age=12라는 파라미터명이 age인 값을 받아서 int타입 변수 rpage에 저장
		logger.info("@RequestParam(id)는 {}",rpid); //콘솔
		logger.info("@RequestParam(age)는 {}",rpage-1);//콘솔
		
		//2.비즈니스로직
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		//3.Model
		response.setCharacterEncoding("UTF-8");
		request.setAttribute("name", "홍GD");
		
		//org.springframework.ui패키지의 Model이용
		model.addAttribute("serverTime", formattedDate );
		
		//4.view
		return "home";
	}
	
	//요청주소  http://localhost:포트번호/컨페/form01
	//요청주소  http://localhost:8081/app/form01
	//view  /WEB-INF/views/form01.jsp
	@RequestMapping("/form01")
	public String form01() {
		//1.파라미터받기
		//2.비즈니스로직
		//3.Model
		//4.View
		return "form01";
	}
	
	//요청방식  post
	//요청주소  http://localhost:8081/app/form01Result
	//view  /WEB-INF/views/form01Result.jsp
	@RequestMapping(value="/form01Result",method=RequestMethod.GET)
	public String form01Result(
			@RequestParam String userName,
			@RequestParam("hobby") String[]  hobbies,
			@RequestParam(defaultValue="all") String search,
			@RequestParam String keyword,
			@RequestParam(required=false,defaultValue="1") int pno){
		//1.파라미터받기
		logger.info("이름:{}", userName);//콘솔
		logger.info("관심사:{}", hobbies);//콘솔
		for( String h: hobbies) {
			System.out.println(h);
		}
		logger.info("검색분야:{}, 검색어:{}", search, keyword);
		logger.info("페이지번호:{}", pno);

		//2.비즈니스로직
		//3.Model
		//4.View
		return "form01Result"; //   WEB-INF/views폴더안에  form01Result.jsp를 만들지 않았으므로 404에러가 뜬다
	}
	
}













