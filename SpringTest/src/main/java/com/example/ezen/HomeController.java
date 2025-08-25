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
//Controller������̼��� ���������� @Component��� ������̼��� ��ӹ޴´�.
//servlet-context.xml�� ����Ǿ��ִ� component-scan���� ã������, bean���� ��ϵȴ�.
//tomcat�� ����ǰ�, �����ļ����� �����ڰ� ȣ�� �� �� xml������ �����鼭 bean���� ���
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	//get��û���� "/"�� ��û�� ���� �� �����ϴ� ��Ʈ�ѷ� (MVC�� C)
	public String home(Locale locale, Model model) {
		//home�޼���� Locale�� Model�� �����ϰ��ִ�.
		//Locale�� Model���� �������� �����ϴ� Bean�̱� ������
		//"/"�� ��û�Ͽ� home�޼��尡 ����� �� �������� �������� ������ ���ش�.
		//DI -> Dependency Injection(������ ����)
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
		///WEB-INF/views/home.jsp�� ������(MVC�� V)
	}
	
}
