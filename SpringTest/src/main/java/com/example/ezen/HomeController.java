package com.example.ezen;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
//Controller어노테이션은 내부적으로 @Component라는 어노테이션을 상속받는다.
//servlet-context.xml에 선언되어있는 component-scan으로 찾아지고, bean으로 등록된다.
//tomcat이 실행되고, 디스패쳐서블릿의 생성자가 호출 될 때 xml설정을 읽으면서 bean으로 등록
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	//get요청으로 "/"로 요청이 왔을 때 동작하는 컨트롤러 (MVC중 C)
	public String home(Locale locale, Model model) {
		//home메서드는 Locale과 Model에 의존하고있다.
		//Locale과 Model또한 스프링이 관리하는 Bean이기 때문에
		//"/"로 요청하여 home메서드가 실행될 때 스프링이 의존성을 주입을 해준다.
		//DI -> Dependency Injection(의존성 주입)
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
		///WEB-INF/views/home.jsp로 포워딩(MVC중 V)
	}
	
}
