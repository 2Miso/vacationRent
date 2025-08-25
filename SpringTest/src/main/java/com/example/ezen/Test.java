package com.example.ezen;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		Car c = new Car();
		//개발자가 직접 제어
		
		ApplicationContext context = 
				new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/spring/root-context.xml");
		
		Car car = context.getBean(Car.class);
		System.out.println(c);
		System.out.println(car);
		
		System.out.println(c.getName());
		System.out.println(car.getName());
		//제어의역전을 통해 spring이 관리하는 bean객체 꺼내기
	}

}
